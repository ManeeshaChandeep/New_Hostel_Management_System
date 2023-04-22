package lk.ijse.hostelManagementSystem.dao;


import lk.ijse.hostelManagementSystem.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public DAOFactory() {
    }

    public static DAOFactory getInstance(){
        if (daoFactory==null) {
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes {
        Student,Rooms,Reservation,Users,Query
    }


    public  SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case Student :
                return new StudentDAOImpl();
            case Rooms:
                return new RoomDAOImpl();
            case Reservation:
                return new ReserveDAOImpl();
            case Users:
                return new UserDAOImpl();
            case Query:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }


}
