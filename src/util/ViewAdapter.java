package util;

import common.Define;
import controller.ControllerGame;
import javafx.application.Platform;
import javafx.scene.control.Button;
import model.Room;
import model.User;

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

    /* entryGame */
    public void setGameHeader(String header) {
        Platform.runLater(() -> {
            this.controllerGame.lbDisplay.setText(header);
        });
    }

    /* toggleBtnReady */
    public void toggleBtnReady() {
        Platform.runLater(() -> {
            // UI 변경코드
            setGameHeader("모든 플레이어 준비완료");
            this.controllerGame.btnGameStart.setDisable(!this.controllerGame.btnGameStart.isDisabled());
        });
    }

    /* startGame */
    public void startGame() {
        Platform.runLater(() -> {
            User user = SceneManager.getInstance().share.user;
            // == 상태변경 ==
            user.setPlay(true);

            // == 팝업 ==
            PopupManager.getInstance().showTooptip("게임시작!");

            // == 버튼변경 ==
            this.controllerGame.btnGameStart.setDisable(true);
            this.controllerGame.btnGameStart.setText("게임중");

            // == 시작코드 ==
            if(user.isTeam())
                setGameHeader("상대 차례");
            else
                setGameHeader("내 차례");
        });
    }

    /* initGame */
    public void resultGame(boolean victory) {
        Platform.runLater(() -> {
            // == 초기화 ==
            this.controllerGame.result(victory);
            setGameHeader("게임 대기중");
        });
    }

    /* updateGame */
    public void updateGame(String position) {
        Platform.runLater(() -> {
            // == 스톤처리 ==
            this.controllerGame.board.setStoneByChallenger(
                    Integer.parseInt(position.substring(0,1)),
                    Integer.parseInt(position.substring(1,2))
            );

            // == 차례변경 ==
            this.controllerGame.board.myTurn = true;
            ViewAdapter.getInstance().setGameHeader("내 차례");
        });
    }

    /* updateStone */
    public void updateStone(int black, int white) {
        this.controllerGame.lbBlack.setText(black + "");
        this.controllerGame.lbWhite.setText(white + "");
    }
}
