package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import adapter.OthelloBoard;
import model.Share;
import model.User;
import util.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGame implements Initializable {

    @FXML BorderPane game;
    @FXML Label lbDisplay;
    @FXML Button btnGameStart;
    @FXML Button btnGameEnd;
    @FXML Label lbBlack;
    @FXML Label lbWhite;
    @FXML GridPane playGround;

    SceneManager sceneManager;
    Share share;
    OthelloBoard board;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = SceneManager.getInstance();
        share = (Share) sceneManager.getStage().getUserData();

        /**
         * #초기화 (컨트롤 + 그리드)
         * #준비상태전달vs시작상태전달
         * #게임플레이
         * #게임완전종료vs중단무단종료
         * */

        init(share.user);
        btnGameStart.setOnAction(event -> handlerBtnGameStart(event));
        btnGameEnd.setOnAction(event -> handlerBtnGameEnd(event));
    }

    /* 초기화 */
    private void init(User user) {
        // == 버튼 초기화 ==
        if(user.isTeam()) {
            // 준비가 되야 게임을 시작할 수있음
            btnGameStart.setText("게임시작");
            btnGameStart.setDisable(true);
        } else {
            btnGameStart.setText("게임준비");
        }

        // == 텍스트 초기화 ==
        lbBlack.setText("2");
        lbWhite.setText("2");

        // == 오셀로판 초기화 ==
        board = new OthelloBoard(playGround);
    }

    /* 게임시작 & 게임준비 */
    private void handlerBtnGameStart(ActionEvent ae) {

    }

    /* 게임종료 */
    private void handlerBtnGameEnd(ActionEvent ae) {
        // 팝업으로 재확인
        SceneManager.getInstance().moveScene("views/standby.fxml");
    }
}
