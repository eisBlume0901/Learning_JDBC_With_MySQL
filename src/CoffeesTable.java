import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CoffeesTable
{
    private final Connection connection;

    public CoffeesTable(Connection connection)
    {
        this.connection = connection;
    }
    public void createCoffeeTable()
    {
        String query = "CREATE TABLE IF NOT EXISTS COFFEES " +
                "(" +
                "COF_NAME VARCHAR(32), " +
                "SUP_ID INT NOT NULL, " +
                "PRICE NUMERIC(10,2) NOT NULL, " +
                "SALES INT NOT NULL, " +
                "TOTAL INT NOT NULL, " +
                "PRIMARY KEY (COF_NAME), " +
                "FOREIGN KEY (SUP_ID) REFERENCES SUPPLIERS (SUP_ID)" +
                ")";

        try
        {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Created COFFEES table successfully");
            statement.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
