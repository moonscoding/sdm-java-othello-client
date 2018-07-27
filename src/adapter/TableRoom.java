package adapter;

import implement.TableAdapter;
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

    @Override
    public void add(Room room) {
        super.add(room);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public Room get(int index) {
        return super.get(index);
    }

    @Override
    public void set(int index, Room room) {
        super.set(index, room);
    }

    @Override
    public void remove(int index) {
        super.remove(index);
    }
}
