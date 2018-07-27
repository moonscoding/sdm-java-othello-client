package model;

import common.Define;
import implement.Callback;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import util.PopupManager;
import util.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class Room {

    private String id;
    private SimpleStringProperty title;
    private SimpleStringProperty user;
    private SimpleStringProperty wins;
    private SimpleStringProperty status;
    private SimpleStringProperty count;


    public Room(String id, String title, String user, String wins, boolean status) {
        this.id = id;
        this.title = new SimpleStringProperty(title);
        this.user = new SimpleStringProperty(user);
        this.wins = new SimpleStringProperty(wins);
        if(status != Define.ROOM_STATUS_A) {
            this.status = new SimpleStringProperty(Define.ROOM_STRING_A);
            this.count = new SimpleStringProperty("1");
        } else {
            this.status = new SimpleStringProperty(Define.ROOM_STRING_B);
            this.count = new SimpleStringProperty("2");
        }
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getUser() {
        return user.get();
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public String getWins() {
        return wins.get();
    }

    public void setWins(String wins) {
        this.wins.set(wins);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(boolean status) {
        if(status != Define.ROOM_STATUS_A) {
            this.status.set(Define.ROOM_STRING_A);
            this.count.set("1");
        } else {
            this.status.set(Define.ROOM_STRING_B);
            this.count.set("2");
        }
    }

    public String getCount() {
        return count.get() + "/2";
    }

    /* 방에입장하는함수 */
    public void entry() {
        if (getStatus().equals(Define.ROOM_STRING_A)) {
            // == 대기 ==
            Callback cb = () -> {
                // == 입장 ==
                /**
                 * #유저상태
                 * #서버요청
                 * #화면전환
                 * */
                // == 유저상태 ==
                SceneManager sm = SceneManager.getInstance();
                Share share = (Share) sm.getStage().getUserData();
                share.user.setTeam(false);

                // TODO...
                // == 서버요청 ==

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
