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
        String dbUrl = "jdbc:oracle:thin:@3.239.148.14:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";
        String query = "select first_name,last_name,salary from employees"; // three columns, how about if I get more columns
        connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
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
        List<Map<String,String>> salaryList = new ArrayList<>();
        Map<String,String> map1 = new HashMap<>();
        map1.put("first_name","Steven");
        map1.put("last_name","King");
        map1.put("salary","24000");
        Map<String,String> map2 = new HashMap<>();
        map2.put("first_name","Neena");
        map2.put("last_name","Kochhar");
        map2.put("salary","17000");

        salaryList.add(map1);
        salaryList.add(map2);
        System.out.println(salaryList);
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



}
