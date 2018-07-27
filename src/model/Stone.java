package model;

import common.Num;
import javafx.scene.effect.Light;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Stone extends Circle {
    // TODO 컨테이너를 상속받아서 내부에 Circle을 가지고 있어야 클릭했을때 여백문제를 해결할 수 있습니다.

    boolean active = false;
    boolean color; // true - black, false - white

    /* 생성자 */
    public Stone() {
        super();
        init();
    }

    /* 공통 초기화 함수 */
    private void init() {
        this.setRadius(Num.STONE_RADIUS);
        this.setStroke(Color.BLACK);
        this.setStyle("-fx-border-color: black");
    }

    /* 돌을 활성화 하는 함수 */
    public void setActive(boolean color) {
        this.color = color;
        this.active = true;
        if(color)   this.setFill(Color.BLACK);
        else        this.setFill(Color.WHITE);
        this.setStrokeWidth(2);
    }

    /* 돌을 비활성화 하는 함수 */
    public void setUnActive() {
        this.active = false;
        this.setFill(Color.TRANSPARENT);
        this.setStrokeWidth(0);
    }
}
