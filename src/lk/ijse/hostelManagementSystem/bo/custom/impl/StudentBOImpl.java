package lk.ijse.hostelManagementSystem.bo.custom.impl;

import lk.ijse.hostelManagementSystem.bo.custom.StudentBO;


import lk.ijse.hostelManagementSystem.bo.custom.StudentBO;
import lk.ijse.hostelManagementSystem.dao.DAOFactory;
import lk.ijse.hostelManagementSystem.dao.custom.ReserveDAO;
import lk.ijse.hostelManagementSystem.dao.custom.StudentDAO;
import lk.ijse.hostelManagementSystem.dto.StudentDTO;
import lk.ijse.hostelManagementSystem.entity.Student;
import lk.ijse.hostelManagementSystem.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    private Session session;
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Student);
    ReserveDAO reservationDAO= (ReserveDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Reservation);
    @Override
    public List<StudentDTO> loadAll() throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        List<Student> students = studentDAO.loadAll();
        List<StudentDTO>studentDTOS=new ArrayList<>();

        for (Student student:students) {
            studentDTOS.add(
                    new StudentDTO(
                            student.getId(),
                            student.getName(),
                            student.getAddress(),
                            student.getContactNo(),
                            student.getDob(),
                            student.getGender()
                    )
            );
        }

        return studentDTOS;
    }

    @Override
    public boolean saveStudent(StudentDTO studentDTO) {
        session= SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            studentDAO.setSession(session);
            String id = studentDAO.save(
                    new Student(
                            studentDTO.getId(),
                            studentDTO.getName(),
                            studentDTO.getAddress(),
                            studentDTO.getContactNo(),
                            studentDTO.getDob(),
                            studentDTO.getGender()
                    ));
            transaction.commit();
            session.close();
            if (id!=null){
                return true;
            }
        }catch (Exception e){
            transaction.rollback();
        }

        return false;
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws Exception {
        session=SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            studentDAO.setSession(session);
            studentDAO.update(
                    new Student(
                            studentDTO.getId(),
                            studentDTO.getName(),
                            studentDTO.getAddress(),
                            studentDTO.getContactNo(),
                            studentDTO.getDob(),
                            studentDTO.getGender()
                    ));
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
        }

        return false;
    }

    @Override
    public boolean deleteStudent(StudentDTO studentDTO) throws Exception {
        session=SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            studentDAO.setSession(session);
            studentDAO.delete(
                    new Student(
                            studentDTO.getId(),
                            studentDTO.getName(),
                            studentDTO.getAddress(),
                            studentDTO.getContactNo(),
                            studentDTO.getDob(),
                            studentDTO.getGender()
                    ));
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
        }

        return false;
    }

    @Override
    public String generateNextStudentID() throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        return studentDAO.generateID();
    }

    @Override
    public boolean getCount(String studentID){
        session=SessionFactoryConfiguration.getInstance().getSession();
        try {
            reservationDAO.setSession(session);
            return reservationDAO.getStudentCount(studentID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}