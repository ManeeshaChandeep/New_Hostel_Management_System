package lk.ijse.hostelManagementSystem.bo.custom;

import lk.ijse.hostelManagementSystem.bo.SuperBO;
import lk.ijse.hostelManagementSystem.dto.RoomDTO;

import java.util.List;

public interface RoomBO extends SuperBO {
    List<RoomDTO> loadAll() throws Exception;

    boolean saveRoom(RoomDTO roomDTO) throws Exception;

    boolean updateRoom(RoomDTO roomDTO) throws Exception;

    boolean deleteRoom(RoomDTO roomDTO) throws Exception;

    String generateNextRoomID() throws Exception;

    boolean getCount(String roomID);
}



