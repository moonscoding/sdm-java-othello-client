package util;

import implement.Callback;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PopupManager {

    private static PopupManager instance;
    private Stage primaryStage;

    public PopupManager(Stage primaryStage) {
        if(instance != null) return;
        instance = this;
        this.primaryStage = primaryStage;
    }

    /* 싱글톤 객체 전달 */
    public static PopupManager getInstance() {
        return instance;
    }

    /* 팝업 - 툴팁  */
    public void showTooptip(String message) {
        try {
            Popup popup = new Popup();
            Parent parent = FXMLLoader.load(getClass().getResource("../views/modules/tooltip.fxml"));
            parent.setOnMouseClicked(event -> popup.hide());

            // == 텍스트 ==
            Label lbMessage = (Label) parent.lookup("#lbMessage");
            lbMessage.setText(message);

            popup.getContent().add(parent);
            popup.setAutoHide(true);
            System.out.println(primaryStage);
            popup.show(primaryStage);
        } catch (IOException e) {
            // TODO 에러
        }
    }

    /* 팝업 - 다이얼로그 */
    public void showDialog(String title, String message) {
        try {
            Stage dialog = new Stage(StageStyle.UTILITY);
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.initOwner(primaryStage);
            dialog.setTitle(title); // dialog title

            Parent parent = FXMLLoader.load(getClass().getResource("../views/modules/dialog.fxml"));
            Label lbMessage = (Label) parent.lookup("#lbMessage");
            lbMessage.setText(message);

            Button btnOK = (Button) parent.lookup("#btnOK");
            btnOK.setOnAction(event -> dialog.close());
            Scene scene = new Scene(parent);
            dialog.setScene(scene);
            dialog.setResizable(false);
            dialog.show();
        } catch (IOException e) {
            // TODO 에러
        }
    }

    /* 팝업 - 다이얼로그 (선택) */
    public void showDialogCallback(String title, String message, Callback callback) {
        try {
            Stage dialog = new Stage(StageStyle.UTILITY);
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.initOwner(primaryStage);
            dialog.setTitle(title); // dialog title

            Parent parent = FXMLLoader.load(getClass().getResource("../views/modules/dialogSelect.fxml"));

            // == 텍스트연동 ==
            Label lbMessage = (Label) parent.lookup("#lbMessage");
            lbMessage.setText(message);

            // == 생성 ==
            Button btnOK = (Button) parent.lookup("#btnOK");
            btnOK.setOnAction(event -> {
                // TODO something
                callback.call();
                dialog.close();
            });

            // == 취소 ==
            Button btnCancel = (Button) parent.lookup("#btnCancel");
            btnCancel.setOnAction(event -> dialog.close());

            Scene scene = new Scene(parent);
            dialog.setScene(scene);
            dialog.setResizable(false);
            dialog.show();
        } catch (IOException e) {
            // TODO 에러
        }
    }

    // TODO - 일반화할수있을까 ?
    /* 팝업 - 다이얼로그 (방만들기) */
    public void showDialogCreateRoom() {
        try {
            Stage dialog = new Stage(StageStyle.UTILITY);
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.initOwner(primaryStage);
            dialog.setTitle("새로운 연승에 도전하세요!"); // dialog title

            Parent parent = FXMLLoader.load(getClass().getResource("../views/modules/dialogCreateRoom.fxml"));

            // == 생성 ==
            Button btnOK = (Button) parent.lookup("#btnOK");
            btnOK.setOnAction(event -> {
                TextField tfTitle = (TextField) parent.lookup("#tfTitle");

                // == 서버연동 ==

                // == 페이지이동 ==
                SceneManager.getInstance().moveScene("views/game.fxml");
                dialog.close();
            });

            // == 취소 ==
            Button btnCancel = (Button) parent.lookup("#btnCancel");
            btnCancel.setOnAction(event -> dialog.close());

            Scene scene = new Scene(parent);
            dialog.setScene(scene);
            dialog.setResizable(false);
            dialog.show();
        } catch (IOException e) {
            // TODO 에러
        }
    }

}
