package implement;

import common.Define;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * [ Class :: TableAdapter ]
 *
 * @경로 :: /src/implement/TableAdapter.java
 * @목적 :: TableView Adapter
 * @진행 :: 진행
 * @주의 :: 없음
 * @일자 :: 2018.07.27
 * @작성 :: SDM
 * @참조 :: 없음
 * */
public abstract class TableAdapter<T> {

    List<T> tableList = new ArrayList<>();
    TableView origin;
    ObservableList list;

    /* 생성자 */
    public TableAdapter(TableView origin) {
        this.origin = origin;
    }

    /* 초기화 */
    public void init(String[] colums) {
        list = FXCollections.observableArrayList();
        for (int i = 0; i < origin.getColumns().size(); i++) {
            TableColumn tc = (TableColumn) origin.getColumns().get(i);
            tc.setCellValueFactory(new PropertyValueFactory(colums[i]));
             tc.setStyle("-fx-alignment:CENTER;");
        }
        origin.setItems(list);
    }

    /* 모두제거 */
    public void clear() {
        tableList.clear();
        render();
    }

    /* 객체얻기 */
    public T get(int index) {
        if(index < 0 || index >= tableList.size()) return null;
        return tableList.get(index);
    }

    /* 객체추가 */
    public void add(T t) {
        tableList.add(t);
        render();
    }

    /* 객체수정 */
    public void set(int index, T t) {
        if(index < 0 || index >= tableList.size()) return;
        tableList.set(index, t);
        render();
    }

    /* 객체제거 */
    public void remove(int index) {
        if(index < 0 || index >= tableList.size()) return;
        tableList.remove(index);
        render();
    }

    /* 업데이트 */
    private void render() {
        list = FXCollections.observableArrayList(tableList);
        origin.setItems(list);
    }

}
