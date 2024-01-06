import java.sql.Connection;
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
}
