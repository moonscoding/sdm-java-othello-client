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
                break;

            // == 입장정보 ==
            case Define.URL_RES_ENTRY:
                break;

            // == 입장허가정보 ==
            case Define.URL_RES_ENTRY_ACCEPT:
                break;

            // == 입장거절정보 ==
            case Define.URL_RES_ENTRY_REJECT:
                break;

            // == 준비허가정보(방장에게) ==
            case Define.URL_RES_READY_ACCEPT:
                break;

            // == 준비거절정보 ==
            case Define.URL_RES_READY_REJECT:
                break;

            // == 시작정보 ==
            case Define.URL_RES_START:
                break;

            // == 방장나감정보 ==
            case Define.URL_RES_LEAVE_GUARDIAN:
                break;

            // == 도전자나감정보 ==
            case Define.URL_RES_LEAVE_CHALLENGER:
                break;

            // == 턴정보 ==
            case Define.URL_RES_TURN:
                break;

            // == 채팅정보 ==
            case Define.URL_RES_CHAT:
                break;
        }
    }
}
