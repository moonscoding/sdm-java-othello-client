package controller;


import util.TableRoom;
import common.Define;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import model.Room;
import model.Share;
import structure.MySocket;
import util.PopupManager;
import util.SceneManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerStandBy implements Initializable {

    @FXML
    private Button btnRoomCreate;
    @FXML
    private Button btnGoBack;
    @FXML
    private TableView<Room> tableRoom;

    SceneManager sceneManager;
    Share share;
    List<Room> rooms = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = SceneManager.getInstance();
        share = (Share) sceneManager.getStage().getUserData();

        initTableView();
        requestTableView();

        btnRoomCreate.setOnAction(event -> handlerBtnRoomCreate(event));
        btnGoBack.setOnAction(event -> handlerBtnGoBack(event));
    }

    /* tableview 초기화 */
    public void initTableView() {
        TableRoom tableAdapter = new TableRoom(tableRoom);
        tableAdapter.init(Define.STANDBY_TABLE_COLUMNS);

        tableAdapter.add(new Room("id1", "title", "player01", "5", true));
        tableAdapter.add(new Room("id1", "title", "player01", "5", false));

        tableRoom.setRowFactory( tv -> {
            TableRow<Room> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    row.getItem().entry();
                }
            });
            return row ;
        });
    }

    /* table 정보요청 */
    private void requestTableView() {
        share.socket.send(Define.URL_REQ_UPDATE);
    }

    /* 방만들기 */
    public void handlerBtnRoomCreate(ActionEvent ae) {
        /**
         * # 팝업생성
         * # 방으로이동
         * */
        PopupManager.getInstance().showDialogCreateRoom();
    }

    /* 이름변경 */
    public void handlerBtnGoBack(ActionEvent ae) {
        SceneManager.getInstance().moveScene("views/index.fxml");
    }
}
