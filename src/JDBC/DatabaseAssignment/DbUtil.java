package JDBC.DatabaseAssignment;

import java.sql.*;
public class DbUtil {
    private final Connection connection;
    private Statement statement;

    //Database connection details
    final String url = "jdbc:mysql://localhost:3306/student_management_system";
    final String user= "root";
    final String password = "bahati8260";

    //To initialize connection to the database
    public DbUtil() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }
    //To close the connection to the database
    @Override
    protected void finalize() throws Throwable {
        this.closeConnection();
    }
    //To read data from the database

    public ResultSet readDetails(String query) throws SQLException {
        statement = connection.createStatement();
        return statement.executeQuery(query);
    }
    //To write data to the database
    public int writeDetails(String query) throws SQLException {
        statement = connection.createStatement();
        return statement.executeUpdate(query);
    }
    //To delete data from the database
    public int deleteDetails(String query) throws SQLException {
        statement = connection.createStatement();
        return statement.executeUpdate(query);
    }
    //To close the connection
    private void closeConnection() throws SQLException {
        statement.close();
        connection.close();
    }
}
