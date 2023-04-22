package lk.ijse.hostelManagementSystem.bo.custom;

import lk.ijse.hostelManagementSystem.bo.SuperBO;
import lk.ijse.hostelManagementSystem.dto.ReservationDTO;
import lk.ijse.hostelManagementSystem.dto.RoomDTO;
import lk.ijse.hostelManagementSystem.dto.StudentDTO;
import lk.ijse.hostelManagementSystem.projection.StudentDetailsDTO;

import java.util.List;

public interface ReserveBO extends SuperBO {
    List<ReservationDTO> loadAll() throws Exception;

    boolean saveReservation(ReservationDTO reservationDTO);

    boolean updateReservation(ReservationDTO reservationDTO) throws Exception;

    StudentDTO getStudent(String id);

    RoomDTO getRoom(String id);

    boolean deleteReservation(ReservationDTO reservationDTO);

    String generateNextReservationID();

    List<String> getStudentIds();

    List<String> getRoomIds();

    boolean updateRoomQty(RoomDTO roomDTO) throws Exception;

    List<StudentDetailsDTO> getUnpaidStudents();

    boolean changePaidStatus(String id, String status);
}


