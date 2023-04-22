package lk.ijse.hostelManagementSystem.dao.custom;

import lk.ijse.hostelManagementSystem.dao.CrudDAO;
import lk.ijse.hostelManagementSystem.entity.Room;
import org.hibernate.Session;

import java.util.List;

public interface RoomDAO extends CrudDAO<Room> {
    void setSession(Session session) throws Exception;
    List<String> getIds() throws Exception;
}
