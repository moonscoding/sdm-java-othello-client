package util;

import common.Common;
import common.Define;
import model.Room;
import model.User;

import java.util.LinkedList;
import java.util.List;

public class SocketListener {

    /* listenUpdateRoom */
    public static void listenUpdateRoom(String response) {
        List<Room> listRooms = new LinkedList<>();
        for (int i = 1; i < response.length(); i+= Define.SIZE_ROOM) {
            List<String> bodys = Common.cutBody(response.substring(i, Define.SIZE_ROOM+1), Define.URL_RES_SINGLE_ROOM_PROTOCOL);
            listRooms.add(new Room(
                bodys.get(0), // id
                bodys.get(1), // title
                bodys.get(2), // wins
                bodys.get(3), // username
                bodys.get(4) == "1" ? false : true // status
            ));
        }
        ViewAdapter.getInstance().updateTable(listRooms);
    }

    /* listenEntry */
    public static void listenEntry(String response) {
        List<String> bodys = Common.cutBody(response, Define.URL_RES_ENTRY_PROTOCOL);
        ViewAdapter.getInstance().setGameHeader(bodys.get(1) + " 입장");
    }

    /* listenReadyAccept */
    public static void listenReadyAccept() {
        ViewAdapter.getInstance().toggleBtnReady();
    }

    /* lisetnGameStart */
    public static void listenGameStart() {
        ViewAdapter.getInstance().startGame();
    }

    /* listenLeave - 누군가 나갈걸 듣는 입장 */
    public static void listenLeave(boolean team) {
        if(team) {
            // == 방장나감 ==
            SceneManager.getInstance().share.user.setTeam(Define.GUARDIAN);
        } else {
            // == 도전자나감 ==
            // nothing
        }
        ViewAdapter.getInstance().resultGame(true); // 승리
    }

    /* listenTurn */
    public static void listenTurn(String response) {
        List<String> bodys = Common.cutBody(response, Define.URL_REQ_TURN_PROTOCOL);
        ViewAdapter.getInstance().updateGame(bodys.get(1));
    }
}
