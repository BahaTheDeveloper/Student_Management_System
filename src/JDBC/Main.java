package JDBC;

import JDBC.DatabaseAssignment.DbUtil;
import JDBC.DatabaseAssignment.Gender;
import JDBC.DatabaseAssignment.Student;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static final String url = "jdbc:mysql://localhost:3306/student_management_system";
    static final String user= "root";
    static final String password = "bahati8260";
    public static void main(String[] args) throws ParseException,  SQLException {

        int choice;
        Scanner scanner = new Scanner(System.in);
        DbUtil dbUtil = new DbUtil();
        System.out.println("STUDENT MANAGEMENT SYSTEM\n");
        do{
            System.out.println(
                    "1. Add Student Details\n"+
                            "2. Search Student Details\n"+
                            "3. Delete Student Details\n"+
                            "4. View Student Details\n"+
                            "0. Exit\n");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    Student student = getStudentDetails(scanner);
                    insertStudentDetails(dbUtil, student);
                    System.out.println();
                    break;
                case 2:
                    searchForStudent(scanner, dbUtil);
                    System.out.println();
                    break;
                case 3:
                    deleteStudent(scanner, dbUtil);
                    System.out.println();
                    break;
                case 4:
                    viewStudent(dbUtil);
                    System.out.println();
                    break;
            }
        }while (choice !=0);
    }


    private static Student getStudentDetails(Scanner scanner) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("Enter the Registration Number:");
        String regNo = scanner.nextLine();
        System.out.println("Enter the Student's Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Date of Birth:  (dd-MM-yyyy)");
        String dob = scanner.nextLine();
        Date dateOfBirth = simpleDateFormat.parse(dob);
        System.out.println("Enter Gender: (Male or Female)");
        String gen = scanner.nextLine();
        Gender gender = Gender.valueOf(gen);
        System.out.println("Enter Date of Admission: (dd-MM-yyyy)");
        String doa = scanner.nextLine();
        Date dateOfAdd = simpleDateFormat.parse(doa);
        System.out.println("Enter Course:");
        String course = scanner.nextLine();

        return new Student(regNo, name, dateOfBirth, gender, dateOfAdd, course);
    }
    private static void insertStudentDetails(DbUtil dbUtil, Student student) throws SQLException {

        String query = "INSERT INTO student_details (registrationNumber, name, dateOfBirth, gender, dateOfAdmission, course) VALUES ( '"+ student.getRegistrationNumber()+"' , '"+ student.getName()+"', '"+ new SimpleDateFormat("dd-MM-yyyy").format(student.getDateOfBirth()) +"', '"+ student.getGender()+"', '"+
                new SimpleDateFormat("dd-MM-yyyy").format(student.getDateOfAdmission())+"', '"+ student.getCourse()+ "' )";

        dbUtil.writeDetails(query);
    }
    private static void searchForStudent(Scanner scanner, DbUtil dbUtil) throws SQLException {
        System.out.println("Enter Registration Number:");
        String regNo = scanner.nextLine();
        String searchQuery = "SELECT * FROM student_details WHERE registrationNumber = '"+regNo+"' ";
        ResultSet resultSet = dbUtil.readDetails(searchQuery);
        while (resultSet.next()){
            System.out.println(
                    "Registration Number -> "+resultSet.getString("registrationNumber") +
                            "\nName -> "+ resultSet.getString("name") +
                            "\nDOB -> "+ resultSet.getString("dateOfBirth") +
                            "\nGender -> "+ resultSet.getString("gender") +
                            "\nDOA -> "+ resultSet.getString("dateOfAdmission") +
                            "\nCourse -> " +resultSet.getString("course"));
        }
    }
    private static void deleteStudent(Scanner scanner, DbUtil dbUtil) throws SQLException {
        System.out.println("Enter Registration Number:");
        String regNo = scanner.nextLine();
        String deleteQuery = "DELETE FROM student_details WHERE registrationNumber = '"+regNo+"' ";
        dbUtil.deleteDetails(deleteQuery);
    }
    private static void viewStudent(DbUtil dbUtil) throws SQLException {
        String query = "SELECT * FROM student_details ";
        ResultSet resultSet = dbUtil.readDetails(query);
        while (resultSet.next()){
            System.out.println(resultSet.getString("registrationNumber") +
                    ", "+ resultSet.getString("name") +
                    ", "+ resultSet.getString("dateOfBirth") +
                    ", "+ resultSet.getString("gender") +
                    ", "+ resultSet.getString("dateOfAdmission") +
                    ", " +resultSet.getString("course"));
        }
    }


}
