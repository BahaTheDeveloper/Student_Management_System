package JDBC.DatabaseAssignment;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
    private final String registrationNumber;
    private String name;
    private Date dateOfBirth;
    private Gender gender;
    private Date dateOfAdmission;
    private String course;

    public Student(String registrationNumber) {
        this.registrationNumber = registrationNumber;
}
    public Student(String registrationNumber, String name, Date dateOfBirth, Gender gender, Date dateOfAdmission, String course) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.dateOfAdmission = dateOfAdmission;
        this.course = course;
    }


    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public Date getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(Date dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return   registrationNumber +", "+ name+", "+  new SimpleDateFormat("dd-MM-yyyy").format(dateOfBirth)+", "+
                gender +", "+ new SimpleDateFormat("dd-MM-yyyy").format(dateOfAdmission) +", "+ course;
    }
}
