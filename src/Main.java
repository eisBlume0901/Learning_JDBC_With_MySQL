import java.sql.Connection;

public class Main
{
    public static void main(String[] args)
    {
        DBConnection dbConnection = new DBConnection();
        String databaseName = "jdbc_tutorial_coffee_ms";
        Connection connection = dbConnection.getConnection(databaseName);

        SuppliersTable suppliersTable = new SuppliersTable(connection);
//        suppliersTable.createSuppliersTable();
//        suppliersTable.populateSuppliersTable(49, "Superior Coffee", " Party Place", "Mendocino", "CA", "95460");
//        suppliersTable.populateSuppliersTable(101, "Acme, Inc.", "99 Market Street", "Groundsville", "CA", "95199");
        suppliersTable.populateSuppliersTable(150, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966");

//        CoffeesTable coffeesTable = new CoffeesTable(connection);
//        coffeesTable.createCoffeeTable();
    }
}
