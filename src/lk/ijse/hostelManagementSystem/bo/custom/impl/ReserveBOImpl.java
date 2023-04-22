package lk.ijse.hostelManagementSystem.bo.custom.impl;

import lk.ijse.hostelManagementSystem.bo.custom.LoginBO;
import lk.ijse.hostelManagementSystem.bo.custom.ReserveBO;

import lk.ijse.hostelManagementSystem.bo.custom.ReserveBO;
import lk.ijse.hostelManagementSystem.dao.DAOFactory;
import lk.ijse.hostelManagementSystem.dao.custom.QueryDAO;
import lk.ijse.hostelManagementSystem.dao.custom.ReserveDAO;
import lk.ijse.hostelManagementSystem.dao.custom.RoomDAO;
import lk.ijse.hostelManagementSystem.dao.custom.StudentDAO;
import lk.ijse.hostelManagementSystem.dto.ReservationDTO;
import lk.ijse.hostelManagementSystem.dto.RoomDTO;
import lk.ijse.hostelManagementSystem.dto.StudentDTO;
import lk.ijse.hostelManagementSystem.entity.Reservation;
import lk.ijse.hostelManagementSystem.entity.Room;
import lk.ijse.hostelManagementSystem.entity.Student;
import lk.ijse.hostelManagementSystem.projection.StudentDetailsDTO;
import lk.ijse.hostelManagementSystem.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ReserveBOImpl implements ReserveBO {
    private Session session;

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Student);
    RoomDAO roomsDAO = (RoomDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Rooms);
    ReserveDAO reservationDAO = (ReserveDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Reservation);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Query);

    @Override
    public List<ReservationDTO> loadAll() throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();
        reservationDAO.setSession(session);

        try {
            List<Reservation> reservations = reservationDAO.loadAll();
            List<ReservationDTO> reservationDTOS=new ArrayList<>();

            for (Reservation reservation:reservations) {
                reservationDTOS.add(
                        new ReservationDTO(
                                reservation.getResID(),
                                reservation.getDate(),
                                new StudentDTO(
                                        reservation.getStudent().getId(),
                                        reservation.getStudent().getName(),
                                        reservation.getStudent().getAddress(),
                                        reservation.getStudent().getContactNo(),
                                        reservation.getStudent().getDob(),
                                        reservation.getStudent().getGender()
                                ),
                                new RoomDTO(
                                        reservation.getRoom().getId(),
                                        reservation.getRoom().getType(),
                                        reservation.getRoom().getKeyMoney(),
                                        reservation.getRoom().getQty()
                                ),
                                reservation.getStatus()
                        )
                );
            }

            return reservationDTOS;
        }catch (Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public boolean saveReservation(ReservationDTO reservationDTO){
        session=SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            reservationDAO.setSession(session);

            reservationDAO.save(
                    new Reservation(
                            reservationDTO.getResID(),
                            reservationDTO.getDate(),
                            new Student(
                                    reservationDTO.getStudent().getId(),
                                    reservationDTO.getStudent().getName(),
                                    reservationDTO.getStudent().getAddress(),
                                    reservationDTO.getStudent().getContactNo(),
                                    reservationDTO.getStudent().getDob(),
                                    reservationDTO.getStudent().getGender()
                            ),
                            new Room(
                                    reservationDTO.getRoom().getId(),
                                    reservationDTO.getRoom().getType(),
                                    reservationDTO.getRoom().getKeyMoney(),
                                    reservationDTO.getRoom().getQty()
                            ),
                            reservationDTO.getStatus()
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
    public boolean updateReservation(ReservationDTO reservationDTO) throws Exception {
        session=SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            reservationDAO.setSession(session);
            reservationDAO.update(
                    new Reservation(
                            reservationDTO.getResID(),
                            reservationDTO.getDate(),
                            new Student(
                                    reservationDTO.getStudent().getId(),
                                    reservationDTO.getStudent().getName(),
                                    reservationDTO.getStudent().getAddress(),
                                    reservationDTO.getStudent().getContactNo(),
                                    reservationDTO.getStudent().getDob(),
                                    reservationDTO.getStudent().getGender()
                            ),
                            new Room(
                                    reservationDTO.getRoom().getId(),
                                    reservationDTO.getRoom().getType(),
                                    reservationDTO.getRoom().getKeyMoney(),
                                    reservationDTO.getRoom().getQty()
                            ),
                            reservationDTO.getStatus()
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
    public StudentDTO getStudent(String id) {
        session= SessionFactoryConfiguration.getInstance().getSession();

        try {
            studentDAO.setSession(session);
            Student student = studentDAO.getObject(id);
            session.close();
            return new StudentDTO(
                    student.getId(),
                    student.getName(),
                    student.getAddress(),
                    student.getContactNo(),
                    student.getDob(),
                    student.getGender()
            );
        } catch (Exception ex){
            session.close();
            return null;
        }
    }

    @Override
    public RoomDTO getRoom(String id) {
        session= SessionFactoryConfiguration.getInstance().getSession();

        try {
            roomsDAO.setSession(session);
            Room room = roomsDAO.getObject(id);
            session.close();
            return new RoomDTO(
                    room.getId(),
                    room.getType(),
                    room.getKeyMoney(),
                    room.getQty()
            );

        } catch (Exception ex){
            session.close();
            return null;
        }
    }

    @Override
    public boolean deleteReservation(ReservationDTO reservationDTO) {
        session=SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            reservationDAO.setSession(session);
            reservationDAO.delete(
                    new Reservation(
                            reservationDTO.getResID(),
                            reservationDTO.getDate(),
                            new Student(
                                    reservationDTO.getStudent().getId(),
                                    reservationDTO.getStudent().getName(),
                                    reservationDTO.getStudent().getAddress(),
                                    reservationDTO.getStudent().getContactNo(),
                                    reservationDTO.getStudent().getDob(),
                                    reservationDTO.getStudent().getGender()
                            ),
                            new Room(
                                    reservationDTO.getRoom().getId(),
                                    reservationDTO.getRoom().getType(),
                                    reservationDTO.getRoom().getKeyMoney(),
                                    reservationDTO.getRoom().getQty()
                            ),
                            reservationDTO.getStatus()
                    )
            );
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            session.close();
            transaction.rollback();
        }

        return false;
    }

    @Override
    public String generateNextReservationID(){
        try{
            session= SessionFactoryConfiguration.getInstance().getSession();
            reservationDAO.setSession(session);
            return reservationDAO.generateID();
        }catch (Exception e){
            session.close();
        }
        return null;
    }

    @Override
    public List<String> getStudentIds() {
        try{
            session= SessionFactoryConfiguration.getInstance().getSession();
            studentDAO.setSession(session);
            return studentDAO.getIds();
        }catch (Exception e) {
            session.close();
            return null;
        }
    }

    @Override
    public List<String> getRoomIds() {
        try{
            session= SessionFactoryConfiguration.getInstance().getSession();
            roomsDAO.setSession(session);
            return roomsDAO.getIds();
        }catch (Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public boolean updateRoomQty(RoomDTO roomDTO) throws Exception {
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
    public List<StudentDetailsDTO> getUnpaidStudents() {
        return queryDAO.getUnpaidStudents();
    }

    @Override
    public boolean changePaidStatus(String id, String status) {
        boolean isUpdated = false;
        session=SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            reservationDAO.setSession(session);
            isUpdated = reservationDAO.changePaidStatus(id,status);
            transaction.commit();
        }catch (Exception e){
            session.close();
            transaction.rollback();
        }


        return isUpdated;
    }
}
