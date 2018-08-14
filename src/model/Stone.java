package model;

import common.Num;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Stone extends Circle {
    // TODO 컨테이너를 상속받아서 내부에 Circle을 가지고 있어야 클릭했을때 여백문제를 해결

    boolean active = false;
    boolean color  = false; // true - black, false - white

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

    /* isActive */
    public boolean isActive() {
        return active;
    }

    /* setActive */
    public void setActive(boolean isWhite) {
        this.color = isWhite;
        this.active = true;
        if(isWhite) this.setFill(Color.WHITE);
        else        this.setFill(Color.BLACK);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(2);
    }

    /* setUnActive */
    public void setUnActive() {
        this.active = false;
        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.TRANSPARENT);
        this.setStrokeWidth(2);
    }

    /* isWhite */
    public boolean isWhite() {
        return this.color;
    }
}
