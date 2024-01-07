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
}
