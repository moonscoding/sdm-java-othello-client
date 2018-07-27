package controller;

import common.Str;
import javafx.scene.layout.AnchorPane;
import model.Share;
import util.SceneManager;
import model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerIndex implements Initializable {

    @FXML private TextField tfId;
    @FXML private Button btnStart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnStart.setOnAction(event -> handleBtnStart(event));
    }

    public void handleBtnStart(ActionEvent event) {
        /**
         * # User 생성 & 수정
         * # Scene 이동
         * */

        // # User
        String userName = tfId.getText();
        Share share = (Share) SceneManager.getInstance().getStage().getUserData();
        if(share.user == null) {
            User user = new User(userName);
            share.user = user;
        } else {
            share.user.id = userName;
        }

        // # Scene
        SceneManager.getInstance().moveScene("views/standby.fxml");
    }

}
