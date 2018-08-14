package util;


import common.Num;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.Stone;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GridBoard {

    /* Field */
    GridPane playGround;
    Stone[][] board;
    List<Stone> reverseStorage;
    boolean myTurn;
    boolean isWhite;
    int MIN_VER = 0;
    int MAX_VER = 7;
    int MIN_HOR = 0;
    int MAX_HOR = 7;
    int countBlack = 2;
    int countWithe = 2;

    /* Constructor(1) */
    public GridBoard(GridPane playGround, boolean isWhite, Consumer<String> consumer) {
        this.playGround = playGround;
        this.isWhite = isWhite;
        this.myTurn = !isWhite;
        this.reverseStorage = new ArrayList<Stone>(Num.BOARD_SIZE * Num.BOARD_SIZE);
        init(consumer);
    }

    /* init (-> Ready()) */
    private void init(Consumer<String> consumer) {

        // == 400 x 350 ==
        // == 330 x 330 ==
        // == (330 - (5 * 7)) / 8 => 36 ==
        board = new Stone[Num.BOARD_SIZE][Num.BOARD_SIZE];
        for (int column = MIN_VER; column < Num.BOARD_SIZE; column++) {
            for (int row = MIN_HOR; row < Num.BOARD_SIZE; row++) {
                Stone stone = new Stone();

                // == click event listener ==
                final int postColumn = column;
                final int postRow = row;
                stone.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getClickCount() == 1) {
                            if (SceneManager.getInstance().share.user.isPlay()) {
                                System.out.println(myTurn);
                                System.out.println(!stone.isActive());
                                boolean check = checkLocation(postColumn, postRow, isWhite);
                                System.out.println(check);
                                if(myTurn && !stone.isActive() && check) {
                                    // == 색상변경 ==
                                    setStoneByGuardian(stone);
                                    // == 서버연동 ==
                                    consumer.accept(postColumn + "" + postRow);
                                    // == 차례종료 ==
                                    myTurn = false;
                                }
                            }
                        }
                    }
                });
                board[column][row] = stone;
            }
        }
        playGround.setVgap(Num.STONE_GAP);
        playGround.setHgap(Num.STONE_GAP);
        ready();
    }

    /* ready */
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

    /* getStoneAt */
    private Stone getStoneAt(int x, int y) {
        return board[x][y];
    }

    /* setStoneAt */
    private void setStoneAt(Stone stone, int x, int y) {
        board[x][y] = stone;
        render();
    }

    /* setStoneColor */
    private void setStoneByGuardian(Stone stone) {
        stone.setActive(isWhite);
        if (isWhite)    countWithe++;
        else            countBlack++;
        updateReverse(isWhite);

    }

    /* setStoneColorByPosition */
    public void setStoneByChallenger(int x, int y) {
        board[x][y].setActive(!isWhite);
        if (isWhite) countBlack++;
        else         countWithe++;
        checkLocation(x, y, !isWhite);
        updateReverse(!isWhite);
    }

    /* checkLocation */
    private boolean checkLocation(int x, int y, boolean isWhite) {

        // == 상 ==
        if (y > MIN_VER + 1 && board[x][y - 1].isActive() && board[x][y - 1].isWhite() != isWhite) {
            boolean isOtherStone = true;
            int moveY = y - 2;
            while (isOtherStone && moveY > MIN_VER - 1) {
                if (board[x][moveY].isActive()) {
                    if (board[x][moveY].isWhite() == isWhite) {
                        isOtherStone = false;
                        for (int i = moveY + 1; i < y; i++)
                            reverseStorage.add(board[x][i]);
                    }
                    moveY--;
                } else {
                    break;
                }
            }
        }

        // == 하 ==
        if (y < MAX_VER - 1 && board[x][y + 1].isActive() && board[x][y + 1].isWhite() != isWhite) {
            boolean isOtherStone = true;
            int moveY = y + 2;
            while (isOtherStone && moveY < MAX_VER + 1) {
                if (board[x][moveY].isActive()) {
                    if (board[x][moveY].isWhite() == isWhite) {
                        isOtherStone = false;
                        for (int i = y+1; i < moveY; i++)
                            reverseStorage.add(board[x][i]);
                    }
                    moveY++;
                } else {
                    break;
                }
            }
        }

        // == 좌 ==
        if (x > MIN_HOR + 1 && board[x - 1][y].isActive() && board[x - 1][y].isWhite() != isWhite) {
            boolean isOtherStone = true;
            int moveX = x - 2;
            while (isOtherStone && moveX > MIN_HOR - 1) {
                if (board[moveX][y].isActive()) {
                    if (board[moveX][y].isWhite() == isWhite) {
                        isOtherStone = false;
                        for (int i = moveX+1; i < x; i++)
                            reverseStorage.add(board[i][y]);
                    }
                    moveX--;
                } else {
                    break;
                }
            }
        }

        // == 우 ==
        if (x < MAX_HOR - 1 && board[x + 1][y].isActive() && board[x + 1][y].isWhite() != isWhite) {
            boolean isOtherStone = true;
            int moveX = x + 2;
            while (isOtherStone && moveX < MAX_HOR + 1) {
                if (board[moveX][y].isActive()) {
                    if (board[moveX][y].isWhite() == isWhite) {
                        isOtherStone = false;
                        for (int i = x+1; i < moveX; i++)
                            reverseStorage.add(board[i][y]);
                    }
                    moveX++;
                } else {
                    break;
                }
            }
        }

        // == 좌상 ==
        if (x > MIN_HOR + 1 && y > MIN_VER + 1 && board[x-1][y-1].isActive() && board[x-1][y-1].isWhite()!=isWhite) {
            boolean isOtherStone = true;
            int moveX = x - 2;
            int moveY = y - 2;
            while (isOtherStone && moveX > MIN_HOR -1 && moveY > MIN_VER -1) {
                if (board[moveX][moveY].isActive()) {
                    if (board[moveX][moveY].isWhite()==isWhite) {
                        isOtherStone = false;
                        for (int i = moveX+1; i<x; i++)
                            reverseStorage.add(board[i][++moveY]);
                    }
                    moveX--;
                    moveY--;
                } else {
                    break;
                }
            }
        }

        // == 좌하 ==
        if (x > MIN_HOR +1 && y < MAX_VER -1 && board[x-1][y+1].isActive() && board[x-1][y+1].isWhite()!=isWhite) {
            boolean isOtherStone = true;
            int moveX = x - 2;
            int moveY = y + 2;
            while (isOtherStone && moveX > MIN_HOR -1 && moveY < MAX_VER +1) {
                if (board[moveX][moveY].isActive()) {
                    if (board[moveX][moveY].isWhite()==isWhite) {
                        isOtherStone = false;
                        for (int i = moveX+1; i<x; i++)
                            reverseStorage.add(board[i][--moveY]);
                    }
                    moveX--;
                    moveY++;
                } else {
                    break;
                }
            }
        }

        // == 우상 ==
        if (x < MAX_HOR -1 && y > MIN_VER + 1 && board[x+1][y-1].isActive() && board[x+1][y-1].isWhite()!=isWhite) {
            boolean isOtherStone = true;
            int moveX = x + 2;
            int moveY = y - 2;
            while (isOtherStone && moveX < MAX_HOR +1 && moveY > MIN_VER -1) {
                if (board[moveX][moveY].isActive()) {
                    if (board[moveX][moveY].isWhite()==isWhite) {
                        isOtherStone = false;
                        int newY = y;
                        for (int i = x+1; i<moveX; i++)
                            reverseStorage.add(board[i][--newY]);
                    }
                    moveX++;
                    moveY--;
                } else {
                    break;
                }
            }
        }

        // == 우하 ==
        if (x < MAX_HOR -1 && y < MAX_VER -1 && board[x+1][y+1].isActive() && board[x+1][y+1].isWhite()!=isWhite) {
            boolean isOtherStone = true;
            int moveX = x + 2;
            int moveY = y + 2;
            while (isOtherStone && moveX < MAX_HOR +1 && moveY < MAX_VER +1) {
                if (board[moveX][moveY].isActive()) {
                    if (board[moveX][moveY].isWhite()==isWhite) {
                        isOtherStone = false;
                        int newY = y;
                        for (int i = x+1; i<moveX; i++)
                            reverseStorage.add(board[i][++newY]);
                    }
                    moveX++;
                    moveY++;
                } else {
                    break;
                }
            }
        }

        return reverseStorage.size() > 0;
    }

    /* updateReverse */
    private void updateReverse(boolean isWhite) {
        reverseStorage.forEach(item-> {
            item.setActive(isWhite);
            if (isWhite) {
                countWithe++;
                countBlack--;
            }
            else {
                countWithe--;
                countBlack++;
            }
        });
        reverseStorage.clear();
    }

    /* clear */
    void clear() {
        playGround.getChildren().clear();
    }

    /* render */
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
