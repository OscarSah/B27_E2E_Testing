package jdbcTests;

import utilities.ConfigurationReader;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        String dbUrl = ConfigurationReader.get("dbUrl");
        String dbUsername = ConfigurationReader.get("dbUsername");
        String dbPassword = ConfigurationReader.get("dbPassword");

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement();
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select first_name,last_name,salary from employees");

//        move pointer to first row
        resultSet.next();
//
//        getting information with column name
       System.out.println(resultSet.getString("first_name"));
        System.out.println(resultSet.getString("last_name"));
//        //getting information with column index (starts from 1)
        System.out.println(resultSet.getString(2));
//
        System.out.println(resultSet.getString("first_name")+" - "+resultSet.getString(2));


        while(resultSet.next()){
            System.out.println(resultSet.getString(1)+" - "+resultSet.getString(2)+" - "+resultSet.getString(3)  );
        }


        //close all connections
        resultSet.close();
        statement.close();
        connection.close();


    }
}
