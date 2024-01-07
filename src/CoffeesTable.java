import java.sql.*;
import static java.lang.System.*;

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
            out.println("Created COFFEES table successfully");
            statement.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public void populateCoffeesTable(String COF_NAME, Integer SUP_ID, Double PRICE, Integer SALES, Integer TOTAL)
    {
        try
        {
            String query = "INSERT INTO COFFEES VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, COF_NAME);
            preparedStatement.setInt(2, SUP_ID);
            preparedStatement.setDouble(3, PRICE);
            preparedStatement.setInt(4, SALES);
            preparedStatement.setInt(5, TOTAL);
            preparedStatement.execute();
            out.println("Entry recorded to COFFEES table successfully");
            preparedStatement.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public void viewCoffeesTable()
    {
        try
        {
            String query = "SELECT * FROM COFFEES";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // ResultSet's TYPE_FORWARD_ONLY, CONCUR_READ_ONLY
            while (resultSet.next())
            {
                // getter methods are case-insensitive if we used column names instead of
                // column numbers
                // version 1 - column names
                String coffeeName = resultSet.getString("COF_NAME");
                Integer supplier_ID = resultSet.getInt("SUP_ID");
                Double price = resultSet.getDouble("PRICE");
                Integer sales = resultSet.getInt("SALES");
                Integer total = resultSet.getInt("TOTAL");

                // version 2 - column numbers
                // String coffeeNamnande = resultSet.getSting(1);
                out.println("Coffee name: " +  coffeeName);
                out.println("Supplied from: " + supplier_ID);
                out.println("Price: " + price);
                out.println("Sales: " + sales);
                out.println("Total: " + total);
                out.println();
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }


}
