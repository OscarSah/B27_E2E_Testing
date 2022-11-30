package jdbcTests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestsWithOracle {

    Connection connection;
    Statement statement;
    ResultSet resultSet;
    @BeforeMethod
    public void setUp() throws SQLException {
        // let's send a query to spartans
        String dbUrl = "jdbc:oracle:thin:@3.85.103.221:1521:XE";
        String dbUsername = "SP";
        String dbPassword = "SP";
        String query = "select spartan_id, name, gender from spartans"; // three columns, how about if I get more columns
        connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword); // connection string
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
}
    @AfterMethod
    public void tearDown() throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();
}
    @Test
    public void connectionTest() throws SQLException {

        while(resultSet.next()){
            System.out.println(resultSet.getObject(1)+"|"+resultSet.getObject(2)+"|"+resultSet.getObject(3));
        }
    }

    @Test
    public void mapTest(){
        List<Map<String,String>> spartanList = new ArrayList<>();
        Map<String,String> map1 = new HashMap<>();
        map1.put("spartan_id","1");
        map1.put("name","Meade");
        map1.put("gender","Male");
        Map<String,String> map2 = new HashMap<>();
        map2.put("spartan_id","2");
        map2.put("name","Nels");
        map2.put("gender","male");

        spartanList.add(map1);
        spartanList.add(map2);
        System.out.println(spartanList);
    }

    @Test
    public void metaDataTest() throws SQLException {
        DatabaseMetaData dbMetaData = connection.getMetaData();
        System.out.println("dbMetaData.getDatabaseProductName() = " + dbMetaData.getDatabaseProductName());
        System.out.println("dbMetaData.getDriverName() = " + dbMetaData.getDriverName());
        System.out.println("dbMetaData.getDatabaseProductVersion() = " + dbMetaData.getDatabaseProductVersion());

        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();

        String columnName = rsmd.getColumnName(1);

        System.out.println("columnName = " + columnName);
        System.out.println("columnCount = " + columnCount);

    }

    @Test
    public void dynamicListTest() throws SQLException {
       ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();
        List<Map<String,String>> queryList = new ArrayList<>();

        while(resultSet.next()){
            Map<String,String> map = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                 map.put(rsmd.getColumnName(i),resultSet.getString(i));
            }
            queryList.add(map);
        }

        for (Map<String, String> eachRow : queryList) {
            System.out.println("eachRow = " + eachRow);
        }
        // From this point basically I can use JAVA collection methods to read and process my data
    }

/*
The information I need to be able to read the Table
1. How many rows I have? ----- resultset.next() - takes you to the row
2. How many columns I have?
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();
3. I need to my column names
         String columnName = rsmd.getColumnName(1);
4. I need to read the data in that cell  resultset.getObject method to read the cell
 */



}
