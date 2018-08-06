package controller;

import common.Define;
import model.Share;
import structure.MySocket;
import util.O_Socket;
import util.Request;
import util.Response;
import util.SceneManager;
import model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerIndex implements Initializable {

    /* FXML */
    @FXML private TextField tfId;
    @FXML private Button btnStart;

    /* Field */
    SceneManager sceneManager;
    Share share;

    /* Initialize */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = SceneManager.getInstance();
        share = (Share) sceneManager.getStage().getUserData();

        // == Socket ==
        initSocket();

        // == Click Start ==
        btnStart.setOnAction(event -> handleBtnStart(event));
    }

    /* InitSocket */
    public void initSocket() {
        try {

            if(Define.isDebug) {
                // == Debug ==
                share.socket = new O_Socket(Define.HOST, Define.PORT_DEBUG, Define.BUFFER_SIZE);
            } else {
                // == Production ==
                share.socket = new O_Socket(Define.HOST, Define.PORT_PROP, Define.BUFFER_SIZE);
            }
        } catch (RuntimeException err) {
            // == 서버통신두절 ( 팝업 => 게임종료 ) ==
            System.out.println(err.getMessage());
        }
    }

    /* HandleBtnStart */
    public void handleBtnStart(ActionEvent event) {

        // == User ==
        String userName = tfId.getText();
        Share share = (Share) SceneManager.getInstance().getStage().getUserData();
        if(share.user == null) {
            User user = new User(userName);
            share.user = user;
        } else {
            share.user.id = userName;
        }

        // == Send - User ==
        share.socket.send(Define.URL_REG_URSE + Response.fullBlank(userName, Define.SIZE_USER_NAME));

        // == Scene Move ==
        SceneManager.getInstance().moveScene("views/standby.fxml");
    }

}
