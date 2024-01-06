import java.sql.Connection;

public class Main
{
    public static void main(String[] args)
    {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();

        SuppliersTable suppliersTable = new SuppliersTable(connection);
        suppliersTable.createSuppliersTable();
//        CoffeesTable coffeesTable = new CoffeesTable(connection);
//        coffeesTable.createCoffeeTable();
    }
}
