package controller;


import common.Common;
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
import util.PopupManager;
import util.SceneManager;
import util.ViewAdapter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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

        // == view adapter (connect socket) ==
        ViewAdapter.getInstance().setTable(tableAdapter);

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
        Consumer<String> consummer = (title) -> {
            if(title.length() > 10) {
                PopupManager.getInstance().showTooptip("방이름은 최대 10글자까지 사용할 수 있습니다.");
                return;
            } else if (title.length() == 0) {
                PopupManager.getInstance().showTooptip("방이름을 입력해주세요.");
                return;
            }

            // == 서버연동 ==
            share.socket.send(Define.URL_REQ_CREATE + Common.fullBlank(title, Define.SIZE_ROOM_TITLE));

            // == 유저상태 ==
            SceneManager sm = SceneManager.getInstance();
            Share share = (Share) sm.getStage().getUserData();
            share.user.setTeam(Define.GUARDIAN);

            // == 화면전환 ==
            SceneManager.getInstance().moveScene("views/game.fxml");
        };
        PopupManager.getInstance().showDialogInput(
                "새로운 연승에 도전하세요!",
                "생성할 방의 이름을 적어주세요.",
                "welcome!",
                consummer
        );
    }

    /* 이름변경 */
    public void handlerBtnGoBack(ActionEvent ae) {
        SceneManager.getInstance().moveScene("views/index.fxml");
    }
}
