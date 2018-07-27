package controller;


import adapter.TableRoom;
import common.Define;
import common.Str;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Room;
import model.Share;
import util.MySocket;
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
    MySocket socket;
    Share share;
    List<Room> rooms = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = SceneManager.getInstance();
        share = (Share) sceneManager.getStage().getUserData();

        initSocket();
        initTableView();

        btnRoomCreate.setOnAction(event -> handlerBtnRoomCreate(event));
        btnGoBack.setOnAction(event -> handlerBtnGoBack(event));
    }


    /* socket 초기화 */
    public void initSocket() {
        try {
            socket = new MySocket();
        } catch (RuntimeException err) {

            // == 서버통신두절 ( Popup처리 ) ==
            System.out.println(err.getMessage());
        }
        share.socket = socket;
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
