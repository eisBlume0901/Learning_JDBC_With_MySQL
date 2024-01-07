import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SuppliersTable
{
    private Connection connection;

    public SuppliersTable(Connection connection)
    {
        this.connection = connection;
    }
    public void createSuppliersTable()
    {
        try
        {
            String query = "CREATE TABLE IF NOT EXISTS SUPPLIERS " +
                    "(" +
                    "SUP_ID INT NOT NULL, " +
                    "SUP_NAME VARCHAR(40) NOT NULL, " +
                    "STREET VARCHAR(40) NOT NULL, " +
                    "CITY VARCHAR(20) NOT NULL, " +
                    "STATE CHAR(2) NOT NULL, " +
                    "ZIP CHAR(5), " +
                    "PRIMARY KEY (SUP_ID)" +
                    ")";

            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Created SUPPLIERS table successfully");
            statement.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public void populateSuppliersTable(Integer SUP_ID, String SUP_NAME, String STREET, String CITY, String STATE, String ZIP)
    {
        try
        {
            String query = "INSERT INTO SUPPLIERS VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, SUP_ID);
            preparedStatement.setString(2, SUP_NAME);
            preparedStatement.setString(3, STREET);
            preparedStatement.setString(4, CITY);
            preparedStatement.setString(5, STATE);
            preparedStatement.setString(6, ZIP);
            preparedStatement.execute();
            System.out.println("Entry has recorded to the SUPPLIERS table successfully");
            preparedStatement.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
