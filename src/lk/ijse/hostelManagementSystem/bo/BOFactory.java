package lk.ijse.hostelManagementSystem.bo;

import lk.ijse.hostelManagementSystem.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory BOFactory;

    public BOFactory() {
    }

    public static BOFactory getInstance(){
        if (BOFactory ==null) {
            BOFactory =new BOFactory();
        }
        return BOFactory;
    }

    public enum BOTypes {
        Student,Rooms,Reservation,Users,Login
    }


    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case Student :
                return new StudentBOImpl();
            case Rooms:
                return new RoomBOImpl();
            case Reservation:
                return new ReserveBOImpl();
            case Users:
                return new UserBOImpl();
            case Login:
                return new LoginBOImpl();
            default:
                return null;
        }
    }


}