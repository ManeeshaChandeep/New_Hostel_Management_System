package lk.ijse.hostelManagementSystem.bo.custom;

import lk.ijse.hostelManagementSystem.bo.SuperBO;
import lk.ijse.hostelManagementSystem.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    List<StudentDTO> loadAll() throws Exception;

    boolean saveStudent(StudentDTO studentDTO);

    boolean updateStudent(StudentDTO studentDTO) throws Exception;

    boolean deleteStudent(StudentDTO studentDTO) throws Exception;

    String generateNextStudentID() throws Exception;

    boolean getCount(String studentID);
}


