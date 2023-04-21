package lk.ijse.hostelManagementSystem.dto;

public class StudentDTO {
     private String id;
     private String name;
     private String address;
     private String contactNo;
     private String dob;

    public StudentDTO() {
    }

    public StudentDTO(String id, String name, String address, String contactNo, String dob) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.dob = dob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}
