package util;

import common.Config;
import common.Define;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Share;
import model.User;

import java.io.IOException;


/**
 * [ Class :: SceneManager ]
 *
 * @경로 :: /src/util/SceneManager.java
 * @목적 :: Stage & Scene을 관리하는 클래스 (싱클톤으로 구성)
 * @진행 :: 종료
 * @주의 :: 싱클톤객체이고 화면전환을 관장합니다.
 * @일자 :: 2018.07.26
 * @작성 :: SDM
 * @참조 ::
 * */
public class SceneManager {
    private static SceneManager instance;
    Stage stage;
    Scene scene;
    Share share;

    public SceneManager( Stage stage ) {
        if(SceneManager.instance != null) return;
        SceneManager.instance = this;

        // ### 무대설정 ###
        this.stage = stage;
        this.stage.setResizable(false);

        // ### 공유객체 ###
        this.share = new Share();

        // @Test
        switch (Config.PAGE_ENV) {
            case Define.PAGE_INDEX:
                break;
            case Define.PAGE_STANDBY:
                share.user = new User("player01"); // @Test
                break;
            case Define.PAGE_GAME:
                share.user = new User("player01"); // @Test
                break;
        }
        this.stage.setUserData(share);
    }

    public static SceneManager getInstance() {
        return instance;
    }

    public Stage getStage() {
        return this.stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setTitle( String title ) {
        stage.setTitle(title);
    }

    public void moveScene( String location ) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../" + location));
            scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
