// https://docs.oracle.com/javase/tutorial/jdbc/basics/sqlexception.html
import java.sql.*;
import java.util.Properties;
import static java.lang.System.*;

public class DBConnection
{
    static String userName = "root";
    static String password = "";
    static String databaseType = "mysql";
    static String serverName = "localhost";
    static int portNumber = 3306;
    static Connection connection = null;

    static String databaseName = "jdbc_tutorial_coffee_ms";
    public Connection getConnection()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Set properties of the connection such as its username and password
            Properties userCredentials = new Properties();
            userCredentials.put("user", userName);
            userCredentials.put("password", password);
            connection =
                    DriverManager.getConnection("jdbc:" + databaseType + "://" + serverName +
                                ":" + portNumber + "/" + databaseName, userCredentials);
            out.println("Connected to database successfully");
        }
        catch (SQLException sqlException)
        {
            // Version 1
//            out.println("SQL State: " + sqlException.getSQLState());
//            out.println("Error Code: " + sqlException.getErrorCode());
//            out.println("Message: " + sqlException.getMessage());

            // Version 2
            sqlException.printStackTrace();
        }
        catch (ClassNotFoundException classNotFoundException)
        {
            classNotFoundException.printStackTrace();
        }
        return connection;
    }
    
    public static void createDatabase(Connection connection, String databaseName)
    {
        try
        {
            Statement statement = connection.createStatement();
            String query = "CREATE DATABASE IF NOT EXISTS " + databaseName;
            statement.executeUpdate(query);
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
