package jdbcTests;

import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listofmap_example {
    String dbUrl = ConfigurationReader.get("dbUrl");
    String dbUsername = ConfigurationReader.get("dbUsername");
    String dbPassword = ConfigurationReader.get("dbPassword");

    @Test
    public void MetaDataExample() throws SQLException {

        //list for keeping all rows a map
        List<Map<String,Object>> queryData = new ArrayList<>();

        Map<String,Object> row1 = new HashMap<>();
        row1.put("first_name","Steven");
        row1.put("last_name","King");
        row1.put("salary",24000);
        row1.put("job_id","AD_PRES");

        System.out.println(row1.toString());

        Map<String,Object> row2 = new HashMap<>();
        row2.put("first_name","Neena");
        row2.put("last_name","Kochhar");
        row2.put("salary",17000);
        row2.put("job_id","AD_VP");
        System.out.println(row2.toString());

        System.out.println(row2.get("salary"));

        Map<String,Object> row3 = new HashMap<>();
        row3.put("first_name","Oscar");
        row3.put("last_name","SH");
        row3.put("salary",25000);
        row3.put("job_id","SDET");
        System.out.println(row3.toString());

        //adding rows to my list
        queryData.add(row1);
        queryData.add(row2);
        queryData.add(row3);

        System.out.println("queryData.toString() = " + queryData.toString());

        //get the steven last name directly from the list
        System.out.println(queryData.get(0).get("last_name"));

        // print Neena
        System.out.println("queryData.get(1).get(\"first_name\") = " + queryData.get(1).get("first_name"));


    }

    @Test
    public void MetaDataExample2() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select first_name,last_name,salary,job_id from employees\n" +
                "where rownum <6");

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //list for keeping all rows a map
        List<Map<String,Object>> queryData = new ArrayList<>();
        //move to first row
        resultSet.next();
        Map<String,Object> row1 = new HashMap<>();
        row1.put(rsMetadata.getColumnName(1),resultSet.getString(1));
        row1.put(rsMetadata.getColumnName(2),resultSet.getString(2));
        row1.put(rsMetadata.getColumnName(3),resultSet.getString(3));
        row1.put(rsMetadata.getColumnName(4),resultSet.getString(4));

        System.out.println(row1.toString());
        //move to second row
        resultSet.next();

        Map<String,Object> row2 = new HashMap<>();
        row2.put(rsMetadata.getColumnName(1),resultSet.getString(1));
        row2.put(rsMetadata.getColumnName(2),resultSet.getString(2));
        row2.put(rsMetadata.getColumnName(3),resultSet.getString(3));
        row2.put(rsMetadata.getColumnName(4),resultSet.getString(4));

        System.out.println(row2.toString());

        System.out.println(row2.get("SALARY"));

        //adding rows to my list
        queryData.add(row1);
        queryData.add(row2);


        //get the steven last name directly from the list
        System.out.println(queryData.get(0).get("LAST_NAME"));

        //row3
// move to next row
        resultSet.next();
        Map<String,Object> row3 = new HashMap<>();
        row3.put(rsMetadata.getColumnName(1),resultSet.getString(1));
        row3.put(rsMetadata.getColumnName(2),resultSet.getString(2));
        row3.put(rsMetadata.getColumnName(3),resultSet.getString(3));
        row3.put(rsMetadata.getColumnName(4),resultSet.getString(4));

        queryData.add(row3);
        System.out.println("queryData.toString() = " + queryData.toString());

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }



}
