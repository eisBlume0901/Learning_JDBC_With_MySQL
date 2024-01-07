import java.sql.Connection;

public class Main
{
    public static void main(String[] args)
    {
        DBConnection dbConnection = new DBConnection();
        String databaseName = "jdbc_tutorial_coffee_ms";
        Connection connection = dbConnection.getConnection(databaseName);

        SuppliersTable suppliersTable = new SuppliersTable(connection);
        suppliersTable.createSuppliersTable();

        CoffeesTable coffeesTable = new CoffeesTable(connection);
        coffeesTable.createCoffeeTable();
    }
}
