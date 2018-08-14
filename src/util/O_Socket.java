package util;

import common.Define;
import structure.MySocket;

public class O_Socket extends MySocket {

    /* Constructor (1) */
    public O_Socket(String host, short port, int bufferSize) throws RuntimeException {
        super(host, port, bufferSize);
    }

    /* route */
    @Override
    public void route(String response) {
        // @Test
        System.out.println("[수신] " + response);

        // == 분기 ==
        switch (response.substring(0, Define.SIZE_METHOD)) {
            // == 방상태정보 ==
            case Define.URL_RES_UPDATE:
                SocketListener.listenUpdateRoom(response);
                break;

            // == 입장정보 ==
            case Define.URL_RES_ENTRY:
                // TODO 채팅창에설명
                SocketListener.listenEntry(response);
                break;

            // == 입장허가정보 ==
            case Define.URL_RES_ENTRY_ACCEPT:
                // nothing
                break;

            // == 입장거절정보 ==
            case Define.URL_RES_ENTRY_REJECT:
                // TODO 뒤로가기
                break;

            // == 준비완료 (방장에게) ==
            case Define.URL_RES_READY_ACCEPT:
                SocketListener.listenReadyAccept();
                break;

            // == 준비거절 (모든플레이어준비아직안됨) ==
            case Define.URL_RES_READY_REJECT:
                // nothing
                break;

            // == 시작정보 ==
            case Define.URL_RES_START:
                SocketListener.listenGameStart();
                break;

            // == 방장나감정보 ==
            case Define.URL_RES_LEAVE_GUARDIAN:
                SocketListener.listenLeave(true);
                break;

            // == 도전자나감정보 ==
            case Define.URL_RES_LEAVE_CHALLENGER:
                SocketListener.listenLeave(false);
                break;

            // == 턴정보 ==
            case Define.URL_RES_TURN:
                SocketListener.listenTurn(response);
                break;

            // == 채팅정보 ==
            case Define.URL_RES_CHAT:
                // TODO
                break;
        }
    }
}
