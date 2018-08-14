package util;

import common.Define;
import controller.ControllerGame;
import javafx.application.Platform;
import javafx.scene.control.Button;
import model.Room;

import java.util.Iterator;
import java.util.List;

public class ViewAdapter {

    /* Field */
    public static ViewAdapter instance;
    private TableRoom tableRoom;
    private ControllerGame controllerGame;

    /* Constructor */
    public ViewAdapter() {
        if (instance != null) return;
        instance = this;
    }

    /* get instance */
    public static ViewAdapter getInstance() {
        return instance;
    }

    /* set tableRoom */
    public void setTable(TableRoom tableRoom) {
        this.tableRoom = tableRoom;
    }

    /* set controllerGame */
    public void setControllerGame(ControllerGame controllerGame) {
        this.controllerGame = controllerGame;
    }

    /* updateTable */
    public void updateTable(List<Room> rooms) {
        if (tableRoom != null) {
            tableRoom.clear();
            Iterator<Room> i = rooms.iterator();
            while (i.hasNext()) {
                tableRoom.add(i.next());
            }
        }
    }

    /* toggleBtnReady */
    public void toggleBtnReady() {
        Platform.runLater(() -> {
            // UI 변경코드
            PopupManager.getInstance().showTooptip("모든플레이어 준비완료");
            this.controllerGame.btnGameStart.setDisable(!this.controllerGame.btnGameStart.isDisabled());
        });
    }

    /* startGame */
    public void startGame() {
        Platform.runLater(() -> {
            // UI 변경코드
            SceneManager.getInstance().share.user.setPlay(true);
            PopupManager.getInstance().showTooptip("게임시작!");
            this.controllerGame.btnGameStart.setDisable(true);
            this.controllerGame.btnGameStart.setText("게임중");
        });
    }

    /* initGame */
    public void resultGame(boolean victory) {
        Platform.runLater(() -> {
            // UI 변경코드
            this.controllerGame.result(victory);
        });
    }

    /* updateGame */
    public void updateGame(String position) {
        Platform.runLater(() -> {
            // == UI 변경 ==
            this.controllerGame.board.setStoneByChallenger(
                    Integer.parseInt(position.substring(0,1)),
                    Integer.parseInt(position.substring(1,2))
            );

            // == 차례변경 ==
            this.controllerGame.board.myTurn = true;
        });
    }
}
