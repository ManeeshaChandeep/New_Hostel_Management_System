package lk.ijse.hostelManagementSystem.dao.custom;

import lk.ijse.hostelManagementSystem.dao.CrudDAO;
import lk.ijse.hostelManagementSystem.entity.Reservation;
import org.hibernate.Session;

public interface ReserveDAO extends CrudDAO<Reservation> {
    void setSession(Session session) throws Exception;
    boolean changePaidStatus(String id, String status);

    boolean getStudentCount(String studentID);

    boolean getRoomCount(String studentID);
}
