import common.Config;
import common.Define;
import common.Str;
import util.PopupManager;
import util.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;
import util.ViewAdapter;

public class Main extends Application {

    private static SceneManager sceneManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneManager sceneManager = new SceneManager(primaryStage);
        sceneManager.setTitle(Str.APP_NAME);
        new PopupManager(primaryStage);
        new ViewAdapter();

        switch (Config.PAGE_ENV) {
            case Define.PAGE_INDEX:
                sceneManager.moveScene("views/index.fxml");
                break;
            case Define.PAGE_STANDBY:
                sceneManager.moveScene("views/standby.fxml"); // @Test
                break;
            case Define.PAGE_GAME:
                sceneManager.moveScene("views/game.fxml"); // @Test
                break;
        }
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

}
