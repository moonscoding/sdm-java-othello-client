package util;

import common.Define;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.Queue;

public class MySocket {

    public SocketChannel socketChannel;
    public Charset charset = Charset.forName("UTF-8");
    private Queue<String> postQueue = new LinkedList<>(); // 전송할 데이터를 담아두는 Queue
    private boolean busy = false; // Queue를 사용하고 있는지

    public MySocket() throws RuntimeException {
        startSocket();
    }

    /**
     * [ Method :: startSocket ]
     *
     * @DES :: Client 소켓을 실행
     * @S.E :: start -> receive 스레드유지
     * */
    public void startSocket() throws RuntimeException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    socketChannel = SocketChannel.open();

                    // ### 블로킹방식 (명시적) ###
                    socketChannel.configureBlocking(true);

                    // ### connect(new InetSocketAddress) ###
                    socketChannel.connect(new InetSocketAddress(Define.HOST , Define.PORT));

                } catch (IOException err) {
                    // TODO 오류 - 서버
                    // throw new RuntimeException(Define.ERROR_A);
                }
                receive();
            }
        };
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("스레드에서 던지는 예외");
                // 스레드에서 던지는 예외

                System.out.println(e.getMessage() == Define.ERROR_A);
                if(e.getMessage() == Define.ERROR_A) {
                    throw new RuntimeException(Define.ERROR_A);
                }
            }
        });
        thread.start();
    }

    /**
     * [ Method :: stopSocket ]
     *
     * @DES :: Client 소켓을 중단
     * @S.E :: 없음
     * */
    public void stopSocket() {
        try {
            if(socketChannel != null && socketChannel.isOpen()) {
                socketChannel.close();
            }
        } catch (IOException e) {
            // TODO 오류 - 서버
        }
    }

    /**
     * [ Method :: receive ]
     *
     * @DES :: Server 소켓의 응답을 받아서 분기처리
     * @S.E :: 없음
     * */
    public void receive() {
        while(true) {
            try {
                ByteBuffer byteBuffer = ByteBuffer.allocate(Define.BUFFER_SIZE); // 1024


                // ### read(ByteButter) ###
                int byteCount = socketChannel.read(byteBuffer);
                if(byteCount == -1) throw new IOException();

                // ### buffer ###
                byteBuffer.flip();
                String response = charset.decode(byteBuffer).toString();
                System.out.println(response);

                try {
                    JSONParser jsonParser = new JSONParser();
                    JSONObject token = (JSONObject) jsonParser.parse(response);
                    String type = token.get(Define.RECEIVE_TYPE).toString();

                    switch (type) {
                        // TODO 분기

                    }
                } catch (ParseException e) {
                    // TODO 오류 - 문자열
                }
            } catch (IOException e) {
                // TODO 오류 - 서버
            }
        }
    }

    /**
     * [ Method :: send ]
     *
     * @DES :: Server 소켓에게 요청을 보냄
     * @IP1 :: request {String}
     * @S.E :: Queue 처리
     * */
    public void send( String request ) {

        postQueue.offer(request);
        if(!busy) {
            busy = true;
            post();
        }
    }

    /**
     * [ Method :: post ]
     *
     * @DES :: Server 소켓에게 실질적으로 요청을 보내는 메소드
     * @S.E :: 스레드가 누적되지 않을까 ?
     * */
    private void post() {
        String request = postQueue.poll();
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {

                    // ### write(ByteButter) ###
                    ByteBuffer byteBuffer = charset.encode( request );
                    socketChannel.write(byteBuffer);

                    // ### Queue 확인 ###
                    if(postQueue.size() > 0) {
                        post();
                    } else {
                        busy = false;
                    }

                } catch (IOException e) {
                    // TODO 오류 - 서버
                }
            }
        };
        thread.start();
    }


}
