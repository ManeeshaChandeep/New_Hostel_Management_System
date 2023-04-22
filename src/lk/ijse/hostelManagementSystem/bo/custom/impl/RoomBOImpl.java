package lk.ijse.hostelManagementSystem.bo.custom.impl;

import lk.ijse.hostelManagementSystem.bo.custom.LoginBO;
import lk.ijse.hostelManagementSystem.bo.custom.RoomBO;


import lk.ijse.hostelManagementSystem.bo.custom.RoomBO;
import lk.ijse.hostelManagementSystem.dao.DAOFactory;
import lk.ijse.hostelManagementSystem.dao.custom.ReserveDAO;
import lk.ijse.hostelManagementSystem.dao.custom.RoomDAO;
import lk.ijse.hostelManagementSystem.dto.RoomDTO;
import lk.ijse.hostelManagementSystem.entity.Room;
import lk.ijse.hostelManagementSystem.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    private Session session;
    RoomDAO roomsDAO = (RoomDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Rooms);
    ReserveDAO reservationDAO= (ReserveDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Reservation);
    @Override
    public List<RoomDTO> loadAll() throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();
        roomsDAO.setSession(session);
        List<Room> rooms = roomsDAO.loadAll();
        List<RoomDTO> roomDTOS=new ArrayList<>();

        for(Room room: rooms){
            roomDTOS.add(
              new RoomDTO(
                      room.getId(),
                      room.getType(),
                      room.getKeyMoney(),
                      room.getQty()
              )
            );
        }

        return roomDTOS;
    }

    @Override
    public boolean saveRoom(RoomDTO roomDTO) throws Exception {
        session=SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            roomsDAO.setSession(session);
            roomsDAO.save(
                    new Room(
                            roomDTO.getId(),
                            roomDTO.getType(),
                            roomDTO.getKeyMoney(),
                            roomDTO.getQty()
                    )
            );
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
        }

        return false;
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws Exception {
        session=SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            roomsDAO.setSession(session);
            roomsDAO.update(
                    new Room(
                            roomDTO.getId(),
                            roomDTO.getType(),
                            roomDTO.getKeyMoney(),
                            roomDTO.getQty()
                    )
            );
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
        }

        return false;
    }

    @Override
    public boolean deleteRoom(RoomDTO roomDTO) throws Exception {
        session=SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            roomsDAO.setSession(session);
            roomsDAO.delete(
                    new Room(
                            roomDTO.getId(),
                            roomDTO.getType(),
                            roomDTO.getKeyMoney(),
                            roomDTO.getQty()
                    )
            );
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
        }

        return false;
    }

    @Override
    public String generateNextRoomID() throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();
        roomsDAO.setSession(session);
        return roomsDAO.generateID();
    }

    @Override
    public boolean getCount(String roomID){
        session=SessionFactoryConfiguration.getInstance().getSession();
        try {
            reservationDAO.setSession(session);
            return reservationDAO.getRoomCount(roomID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


