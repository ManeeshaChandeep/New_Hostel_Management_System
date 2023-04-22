package lk.ijse.hostelManagementSystem.dao.custom;

import lk.ijse.hostelManagementSystem.dao.SuperDAO;
import lk.ijse.hostelManagementSystem.projection.StudentDetailsDTO;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<StudentDetailsDTO> getUnpaidStudents();
}
