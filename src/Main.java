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
//        suppliersTable.populateSuppliersTable(150, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966");
        suppliersTable.retrieveMetaDataFromSuppliersResultSet();

        CoffeesTable coffeesTable = new CoffeesTable(connection);
//        coffeesTable.createCoffeeTable();
//        coffeesTable.populateCoffeesTable("Colombian", 101, 7.99, 0,0);
//        coffeesTable.populateCoffeesTable("French _Roast", 49,8.99,0,0);
//        coffeesTable.populateCoffeesTable("Espresso", 150,9.99, 0,0);
//        coffeesTable.populateCoffeesTable("Colombian_Decaf",101,8.99,0,0);
//        coffeesTable.populateCoffeesTable("French_Roast_Decaf",49, 9.99,0,0);
        coffeesTable.viewCoffeesTable();
    }
}
