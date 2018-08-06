package util;

import structure.TableAdapter;
import javafx.scene.control.TableView;
import model.Room;

public class TableRoom extends TableAdapter<Room> {

    public TableRoom(TableView origin) {
        super(origin);
    }

    @Override
    public void init(String[] columns) {
        super.init(columns);
    }

}
