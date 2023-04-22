package lk.ijse.hostelManagementSystem.util;

import lk.ijse.hostelManagementSystem.entity.Reservation;
import lk.ijse.hostelManagementSystem.entity.Room;
import lk.ijse.hostelManagementSystem.entity.Student;
import lk.ijse.hostelManagementSystem.entity.Users;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class SessionFactoryConfiguration {
    private static SessionFactoryConfiguration sessionFactoryConfiguration;
    private SessionFactory sessionFactory;

    private SessionFactoryConfiguration(){
        sessionFactory = new Configuration().mergeProperties(Utility.getProperties())
                .addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Users.class)
                .buildSessionFactory();
    }

    public static SessionFactoryConfiguration getInstance(){
        //Creating a SessionFactoryConfiguration object if already not created
        return (sessionFactoryConfiguration==null)
                ? sessionFactoryConfiguration=new SessionFactoryConfiguration()
                :sessionFactoryConfiguration;
    }


    public Session getSession() throws HibernateException {
        //Opening a session
        return sessionFactory.openSession();
    }

}

