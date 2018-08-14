package controller;

import common.Define;
import functional.Callback;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import util.GridBoard;
import model.Share;
import model.User;
import util.PopupManager;
import util.SceneManager;
import util.ViewAdapter;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class ControllerGame implements Initializable {

    /* FXML */
    @FXML BorderPane game;
    public @FXML Label lbDisplay;
    public @FXML Button btnGameStart;
    @FXML Button btnGameEnd;
    public @FXML Label lbBlack;
    public @FXML Label lbWhite;
    @FXML GridPane playGround;

    /* Filed */
    SceneManager sceneManager;
    Share share;
    public GridBoard board;
    Consumer<String> consumer = (String position) -> {
        share.socket.send(Define.URL_REQ_TURN + position);
    };

    /* initialize */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // == SceneManager ==
        sceneManager = SceneManager.getInstance();
        share = (Share) sceneManager.getStage().getUserData();

        // == View Adapter 연동 ==
        ViewAdapter.getInstance().setControllerGame(this);
        init(share.user);

        // == 이벤트 연동 ==
        btnGameStart.setOnAction(event -> handlerBtnGameStart(event));
        btnGameEnd.setOnAction(event -> handlerBtnGameEnd(event));
    }

    /* 초기화 */
    public void init(User user) {
        // == 버튼 초기화 ==
        if(user.isTeam()) {
            btnGameStart.setText("게임시작");
            btnGameStart.setDisable(true);
        } else {
            btnGameStart.setText("게임준비");
            btnGameStart.setDisable(false);
        }

        // == 텍스트 초기화 ==
        lbBlack.setText("2");
        lbWhite.setText("2");

        // == 오셀로판 초기화 ==
        board = new GridBoard(playGround, user.isTeam()/*isWhite*/, consumer);
    }

    /* 결과 */
    public void result(boolean victory) {
        if(share.user.isPlay()) {
            if(victory) {
                PopupManager.getInstance().showTooptip("승리!");
            } else {
                PopupManager.getInstance().showTooptip("패배!");
                // TODO getout!
            }
            share.user.setPlay(false);
        } else {
            PopupManager.getInstance().showTooptip("상대가 나갔습니다.");
        }
        init(share.user);
    }

    /* 게임시작 & 게임준비 */
    private void handlerBtnGameStart(ActionEvent ae) {
        btnGameStart.setDisable(true);
        share.socket.send(Define.URL_REQ_READY);
        if(share.user.isTeam() == Define.CHALLENGER) lbDisplay.setText("준비완료");
    }

    /* 게임종료 */
    private void handlerBtnGameEnd(ActionEvent ae) {
        // TODO 팝업재확인
        Callback cb = ()->{
            if(share.user.isPlay()) share.user.setPlay(false);
            share.socket.send(Define.URL_REQ_LEAVE);
            SceneManager.getInstance().moveScene("views/standby.fxml");
        };
        PopupManager.getInstance().showDialogCallback("주의", "정말 현재방을 나가시겠습니까?", cb);
    }
}
