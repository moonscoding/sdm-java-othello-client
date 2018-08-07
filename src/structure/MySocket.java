package structure;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.Queue;

public abstract class MySocket {

    /* Field */
    private static MySocket instance;
    public SocketChannel socketChannel;
    public Charset charset = Charset.forName("UTF-8");
    private Queue<String> postQueue = new LinkedList<>(); // 전송할 데이터를 담아두는 Queue
    private int bufferSize;
    private boolean busy = false; // Queue를 사용하고 있는지

    /* Constructor */
    public MySocket(String host, short port, int bufferSize) throws RuntimeException {
        // == singleton ==
        if(instance != null) return;
        instance = this;
        this.bufferSize = bufferSize;
        startSocket(host, port);
    }

    /* GetInstance */
    public static MySocket getInstance() {
        return instance;
    }

    /* StartSocket */
    public void startSocket(String host, short port) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    socketChannel = SocketChannel.open();

                    // ### 블로킹방식 (명시적) ###
                    socketChannel.configureBlocking(true);

                    // ### connect(new InetSocketAddress) ###
                    socketChannel.connect(new InetSocketAddress(host, port));

                } catch (IOException err) {
                    // TODO 오류 - 서버
                    err.printStackTrace();
                }
                receive();
            }
        };
        thread.start();
    }

    /* StopSocket */
    public void stopSocket() {
        try {
            if(socketChannel != null && socketChannel.isOpen()) {
                socketChannel.close();
            }
        } catch (IOException e) {
            // TODO 오류 - 서버
            e.printStackTrace();
        }
    }

    /* Receive */
    public void receive() {
        while(true) {
            try {

                // == read ==
                ByteBuffer byteBuffer = ByteBuffer.allocate(bufferSize);
                int byteCount = socketChannel.read(byteBuffer);
                if(byteCount == -1) throw new IOException();

                byteBuffer.flip();
                String response = charset.decode(byteBuffer).toString();

                // == route ==
                route(response);
            } catch (IOException e) {
                // TODO 오류 - 서버
                e.printStackTrace();
                break;
            }
        }
    }

    /* Route */
    public abstract void route ( String response );

    /* Send */
    public void send( String request ) {

        postQueue.offer(request);
        if(!busy) {
            busy = true;
            post();
        }
    }

    /* Post */
    private void post() {
        String request = postQueue.poll();
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    try {
                        // == write ==
                        System.out.println("[송신] " + request);
                        ByteBuffer byteBuffer = charset.encode( request );
                        socketChannel.write(byteBuffer);

                        // == queue ==
                        if(postQueue.size() > 0) {
                            post();
                        } else {
                            busy = false;
                        }
                    } catch (NotYetConnectedException errA) {
                        errA.printStackTrace();
                    }
                } catch (IOException errB) {
                    // TODO 오류 - 서버
                    errB.printStackTrace();
                }
            }
        };
        thread.start();
    }

}
