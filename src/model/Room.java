package model;

import common.Common;
import common.Define;
import functional.Callback;
import javafx.beans.property.SimpleStringProperty;
import structure.MySocket;
import util.O_Socket;
import util.PopupManager;
import util.SceneManager;

public class Room {

    /* Field */
    private String id;
    private SimpleStringProperty wins;
    private SimpleStringProperty user;
    private SimpleStringProperty title;
    private SimpleStringProperty status;
    private SimpleStringProperty count;

    /* Constructor (1) */
    public Room(String id, String title, String user, String wins, boolean status) {
        this.id = id;
        this.wins = new SimpleStringProperty(wins);
        this.title = new SimpleStringProperty(title);
        this.user = new SimpleStringProperty(user);
        if(status != Define.ROOM_STATUS_A) {
            this.status = new SimpleStringProperty(Define.ROOM_STRING_A);
            this.count = new SimpleStringProperty("1");
        } else {
            this.status = new SimpleStringProperty(Define.ROOM_STRING_B);
            this.count = new SimpleStringProperty("2");
        }
    }

    /* get id */
    public String getId() {
        return id;
    }

    /* get title */
    public String getTitle() {
        return title.get();
    }

    /* set title */
    public void setTitle(String title) {
        this.title.set(title);
    }

    /* get user */
    public String getUser() {
        return user.get();
    }

    /* set user */
    public void setUser(String user) {
        this.user.set(user);
    }

    /* get wins */
    public String getWins() {
        return wins.get();
    }

    /* set wins */
    public void setWins(String wins) {
        this.wins.set(wins);
    }

    /* get status */
    public String getStatus() {
        return status.get();
    }

    /* set status */
    public void setStatus(boolean status) {
        if(status != Define.ROOM_STATUS_A) {
            this.status.set(Define.ROOM_STRING_A);
            this.count.set("1");
        } else {
            this.status.set(Define.ROOM_STRING_B);
            this.count.set("2");
        }
    }

    /* get count */
    public String getCount() {
        return count.get() + "/2";
    }

    /* entry */
    public void entry() {
        if (getStatus().equals(Define.ROOM_STRING_A)) {
            // == 대기 ==
            Callback cb = () -> {
                // == 유저상태 ==
                SceneManager sm = SceneManager.getInstance();
                Share share = (Share) sm.getStage().getUserData();
                share.user.setTeam(Define.CHALLENGER);

                // == 서버요청 ==
                O_Socket.getInstance().send(Define.URL_REQ_ENTRY + Common.fullBlank(getId(), Define.SIZE_ROOM_ID));

                // == 화면전환 ==
                sm.moveScene("views/game.fxml");
            };
            PopupManager.getInstance().showDialogCallback("도전", "게임에 도전하시겠습니까?", cb);
        } else {
            // == 진행 ==
            PopupManager.getInstance().showDialog("불가", "이미 게임이 진행중입니다.");
        }
    }

}
