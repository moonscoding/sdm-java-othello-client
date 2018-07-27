package adapter;


import common.Num;
import javafx.scene.layout.GridPane;
import model.Stone;

import java.util.ArrayList;
import java.util.List;

public class OthelloBoard {

    GridPane playGround;
    Stone[][] board;

    public OthelloBoard(GridPane playGround) {
        this.playGround = playGround;
        init();
    }

    /* xy 좌표에 color 돌을 추가하기 */
    public void addStone(int x, int y, boolean color) {
        if(checkLocation(x, y, color)) {

        } else {
            // TODO 돌을 놓을 수 없는 자리입니다.
        }
    }

    /* xy 좌표에 color 돌을 놓을 수 있느지 */
    boolean checkLocation(int x, int y, boolean color) {
        return false;
    }

    /* 그리드 생성 및 초기화 (-> Ready()) */
    void init() {

        // == 400 x 350 ==
        // == 330 x 330 ==
        // == (330 - (5 * 7)) / 8 => 36 ==
        board = new Stone[Num.BOARD_SIZE][Num.BOARD_SIZE];
        for (int column = 0; column < Num.BOARD_SIZE; column++) {
            for (int row = 0; row < Num.BOARD_SIZE; row++) {
                Stone stone = new Stone();
                 board[column][row] = stone;
            }
        }
        playGround.setVgap(Num.STONE_GAP);
        playGround.setHgap(Num.STONE_GAP);
        ready();
    }

    /* 게임 준비를 위한 설정 */
    private void ready() {
        for (int column = 0; column < Num.BOARD_SIZE; column++) {
            for (int row = 0; row < Num.BOARD_SIZE; row++) {
                getStoneAt(column, row).setUnActive();
            }
        }
        (board[3][3]).setActive(false);
        (board[3][4]).setActive(true);
        (board[4][3]).setActive(true);
        (board[4][4]).setActive(false);
        render();
    }

    /* 특정위치에 돌을 추가합니다. */
    private Stone getStoneAt(int x, int y) {
        return board[x][y];
    }

    /* 특정위치에 돌을 추가합니다. */
    private void setStoneAt(Stone stone, int x, int y) {
        board[x][y] = stone;
        render();
    }

    /* 보드를 정리합니다. */
    void clear() {
         playGround.getChildren().clear();
    }

    /* 돌에 수정사항이 있다면 전체 업데이트 합니다. */
    private void render() {
        clear();
        List<Stone> listStone = new ArrayList<>();
        for (int column = 0; column < Num.BOARD_SIZE; column++) {
            for (int row = 0; row < Num.BOARD_SIZE; row++) {
                Stone stone = getStoneAt(column, row);
                listStone.add(stone);
                GridPane.setColumnIndex(stone, column);
                GridPane.setRowIndex(stone, row);
            }
        }
        playGround.getChildren().setAll(listStone); // (add all at once for better performance)
    }

}
