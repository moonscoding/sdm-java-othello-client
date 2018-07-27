package implement;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

/**
 * [ Class :: GridAdapter ]
 *
 * @경로 :: /src/implement/GridAdapter.java
 * @목적 :: GridPane Adapter
 * @진행 :: 종료
 * @주의 :: init() 메소드 Override 필요
 * @일자 :: 2018.07.06
 * @작성 :: SDM
 * @참조 :: https://gist.github.com/nsavageJVM/5775128a9682ebcf0e8186ff209f4f8e
 * */
public abstract class GridAdapter<T extends Node> {

    public int column;
    public int row;

    private GridPane origin;
    private T[][] nodes;

    public GridAdapter(GridPane origin, int column, int row) {
        this.origin = origin;
        this.column = column;
        this.row = row;
        nodes = null;
    }

    /* 초기화 */
    public abstract void init();

    /* 모두제거 */
    public void clear() {
        origin.getChildren().clear();
    }

    /* 노드얻기 */
    public T getNodeAt(int column, int row) {
        return nodes[column][row];
    }

    /* 노드수정 */
    public void setNodeAt(T t, int column, int row) {
        nodes[column][row] = t;
        render();
    }

    /* 노드제거 */
    public void removeNode(int column, int row) {
        setNodeAt(null, column, row);
        render();
    }

    /* 업데이트 */
    private void render() {
        List<T> toAdd = new ArrayList<>();
        for (int c = 0; c < column; c++) {
            for (int r = 0; r < row; r++) {
                T t = getNodeAt(column, row);
                if (t != null) {
                    toAdd.add(t);
                    GridPane.setColumnIndex(t, c);
                    GridPane.setRowIndex(t, r);
                }
            }
        }
        origin.getChildren().setAll(toAdd);
    }

    /* Column얻기 */
    public void columnOf(T t) {
        // TODO
    }

    /* Row얻기 */
    public void rowOf(T t) {
        // TODO
    }

    /* Row추가 */
    public void addRow(int rowAdd) {
        // TODO
        render();
    }

    /* Row제거 */
    public void removeRow(int rowRemove) {
        // TODO
        render();
    }

    /* Column추가*/
    public void addColumn(int columnAdd) {
        // TODO
        render();
    }

    /* Column제거 */
    public void removeColumn(int columnRemove) {
        // TODO
        render();
    }

}