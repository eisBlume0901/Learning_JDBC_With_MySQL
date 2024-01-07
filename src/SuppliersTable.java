import java.sql.*;

import static java.lang.System.*;

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
            out.println("Created SUPPLIERS table successfully");
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
            out.println("Entry has recorded to the SUPPLIERS table successfully");
            preparedStatement.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }


    // https://www.baeldung.com/jdbc-resultset
    public void retrieveMetaDataFromSuppliersResultSet()
    {
        try
        {
            String query = "SELECT * FROM SUPPLIERS";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            Integer columnCount = metaData.getColumnCount();
            String catalogName = null,
                    className = null,
                    label = null,
                    name = null,
                    typeName = null,
                    tableName = null,
                    schemaName = null;
            int type = 0,
                    nullable = 0;
            boolean isAutoIncrement = false,
                    isCaseSensitive = false,
                    isCurrency = false,
                    isDefiniteWritable = false,
                    isReadOnly = false,
                    isSearchable = false,
                    isReadable = false,
                    isSigned = false,
                    isWritable = false;


            for (int columnNumber = 1; columnNumber <= columnCount; columnNumber++)
            {
                catalogName = metaData.getCatalogName(columnNumber);
                className = metaData.getColumnClassName(columnNumber);
                label = metaData.getColumnLabel(columnNumber);
                name = metaData.getColumnName(columnNumber);
                typeName = metaData.getColumnTypeName(columnNumber);
                type = metaData.getColumnType(columnNumber);
                tableName = metaData.getTableName(columnNumber);
                schemaName = metaData.getSchemaName(columnNumber);
                isAutoIncrement = metaData.isAutoIncrement(columnNumber);
                isCaseSensitive = metaData.isCaseSensitive(columnNumber);
                isCurrency = metaData.isCurrency(columnNumber);
                isDefiniteWritable = metaData.isDefinitelyWritable(columnNumber);
                isReadOnly = metaData.isReadOnly(columnNumber);
                isSearchable = metaData.isSearchable(columnNumber);
                isReadable = metaData.isReadOnly(columnNumber);
                isSigned = metaData.isSigned(columnNumber);
                isWritable = metaData.isWritable(columnNumber);
                nullable = metaData.isNullable(columnNumber);
            }

            out.println("Catalog name: " + catalogName);
            out.println("Class name: " + className);
            // add more details this is only for debugging purposes
            out.println("Type: " + type);
            out.println("Is Auto Increment? " + isAutoIncrement);
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public void testResultSetType_Scroll_Sensitive_methods()
    {
        try
        {
            String query = "SELECT * FROM SUPPLIERS";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(query,
                            ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);

            ResultSet resultSet = preparedStatement.executeQuery();

//            while (resultSet.next()) {
//                // iterate through the results from resultSet to last
//                Integer supplierID = resultSet.getInt("SUP_ID");
//                String supplierName = resultSet.getString("SUP_NAME");
//                String street = resultSet.getString("STREET");
//                String city = resultSet.getString("CITY");
//                String state = resultSet.getString("STATE");
//                String zip = resultSet.getString("ZIP");
//            }

            out.println();
            resultSet.beforeFirst(); // jumps back to the starting point, before the first row
            out.println("Before First");
            while (resultSet.next()) {
                // iterate through the results from resultSet to last
                Integer supplierID = resultSet.getInt("SUP_ID");
                String supplierName = resultSet.getString("SUP_NAME");
                String street = resultSet.getString("STREET");
                String city = resultSet.getString("CITY");
                String state = resultSet.getString("STATE");
                String zip = resultSet.getString("ZIP");

                out.println("Supplier ID: " + supplierID);
                out.println("Supplier Name: " + supplierName);
                out.println("Street: " + street);
                out.println("City: " + city);
                out.println("State: " + state);
                out.println("ZIP: " + zip);
            }
            out.println();

            resultSet.afterLast(); // jumps to the end of resultSet
            out.println("After Last");
            while (resultSet.previous()) {
                // iterate through the results from resultSet to last
                Integer supplierID = resultSet.getInt("SUP_ID");
                String supplierName = resultSet.getString("SUP_NAME");
                String street = resultSet.getString("STREET");
                String city = resultSet.getString("CITY");
                String state = resultSet.getString("STATE");
                String zip = resultSet.getString("ZIP");

                out.println("Supplier ID: " + supplierID);
                out.println("Supplier Name: " + supplierName);
                out.println("Street: " + street);
                out.println("City: " + city);
                out.println("State: " + state);
                out.println("ZIP: " + zip);
            }
            out.println();

            resultSet.first(); // navigates to the first row
            out.println("First");
            // iterate through the results from resultSet to last
            Integer supplierID_first = resultSet.getInt("SUP_ID");
            String supplierName_first = resultSet.getString("SUP_NAME");
            String street_first = resultSet.getString("STREET");
            String city_first = resultSet.getString("CITY");
            String state_first = resultSet.getString("STATE");
            String zip_first = resultSet.getString("ZIP");

            out.println("Supplier ID: " + supplierID_first);
            out.println("Supplier Name: " + supplierName_first);
            out.println("Street: " + street_first);
            out.println("City: " + city_first);
            out.println("State: " + state_first);
            out.println("ZIP: " + zip_first);
            out.println();

            resultSet.last(); // goes to the last row
            out.println("Last");
            Integer supplierID_last = resultSet.getInt("SUP_ID");
            String supplierName_last = resultSet.getString("SUP_NAME");
            String street_last = resultSet.getString("STREET");
            String city_last = resultSet.getString("CITY");
            String state_last = resultSet.getString("STATE");
            String zip_last = resultSet.getString("ZIP");

            out.println("Supplier ID: " + supplierID_last);
            out.println("Supplier Name: " + supplierName_last);
            out.println("Street: " + street_last);
            out.println("City: " + city_last);
            out.println("State: " + state_last);
            out.println("ZIP: " + zip_last);
            out.println();

            resultSet.absolute(2); //jumps to 2nd row
            out.println("2nd row");
            Integer supplierID_2 = resultSet.getInt("SUP_ID");
            String supplierName_2 = resultSet.getString("SUP_NAME");
            String street_2 = resultSet.getString("STREET");
            String city_2 = resultSet.getString("CITY");
            String state_2 = resultSet.getString("STATE");
            String zip_2 = resultSet.getString("ZIP");

            out.println("Supplier ID: " + supplierID_2);
            out.println("Supplier Name: " + supplierName_2);
            out.println("Street: " + street_2);
            out.println("City: " + city_2);
            out.println("State: " + state_2);
            out.println("ZIP: " + zip_2);
            out.println();

            resultSet.relative(-1); // jumps to the previous row
            out.println("Relative, -1, previous one");
            Integer supplierID_neg1 = resultSet.getInt("SUP_ID");
            String supplierName_neg1 = resultSet.getString("SUP_NAME");
            String street_neg1 = resultSet.getString("STREET");
            String city_neg1 = resultSet.getString("CITY");
            String state_neg1 = resultSet.getString("STATE");
            String zip_neg1 = resultSet.getString("ZIP");

            out.println("Supplier ID: " + supplierID_neg1);
            out.println("Supplier Name: " + supplierName_neg1);
            out.println("Street: " + street_neg1);
            out.println("City: " + city_neg1);
            out.println("State: " + state_neg1);
            out.println("ZIP: " + zip_neg1);
            out.println();

            resultSet.relative(2); // jumps forward two rows
            out.println("Relative, 2, forward two rows");
            Integer supplierID_pos2 = resultSet.getInt("SUP_ID");
            String supplierName_pos2 = resultSet.getString("SUP_NAME");
            String street_pos2 = resultSet.getString("STREET");
            String city_pos2 = resultSet.getString("CITY");
            String state_pos2 = resultSet.getString("STATE");
            String zip_pos2 = resultSet.getString("ZIP");

            out.println("Supplier ID: " + supplierID_pos2);
            out.println("Supplier Name: " + supplierName_pos2);
            out.println("Street: " + street_pos2);
            out.println("City: " + city_pos2);
            out.println("State: " + state_pos2);
            out.println("ZIP: " + zip_pos2);
            out.println();
            while (resultSet.previous()) {
                // iterates from current row to the first row in backward direction
            }
            resultSet.last();
            int rowCount = resultSet.getRow();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
