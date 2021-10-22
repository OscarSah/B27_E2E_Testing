package jdbcTests;

import org.testng.annotations.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class dbutils_practice {


    @Test
    public void test1(){
        //create connection
        DBUtils.createConnection();
        //using method to get result as a list of maps
        List<Map<String, Object>> queryResult = DBUtils.getQueryResultMap("select first_name,last_name,salary from employees");

        //printing the result
        for (Map<String, Object> map : queryResult) {
            System.out.println(map.toString());
        }

        //close connection
        DBUtils.destroy();
    }


    @Test
    public void test2(){
        //create connection
        DBUtils.createConnection();

        Map<String, Object> rowMap = DBUtils.getRowMap("select first_name,last_name,salary,job_id from employees\n" +
                "where employee_id = 100");

        System.out.println(rowMap.toString());


        //close connection
        DBUtils.destroy();
    }

    @Test
    public void test3(){
        DBUtils.createConnection();

        List<Map<String,Object>> departmentsQuery = DBUtils.getQueryResultMap("select * from departments");

      //  System.out.println(departmentsQuery.size());

        System.out.println(departmentsQuery.get(departmentsQuery.size()-1).get("DEPARTMENT_NAME"));

        DBUtils.destroy();

    }
    @Test
    public void test4(){
        DBUtils.createConnection();
        Map<String, Object> rowMap = DBUtils.getRowMap("select department_name, manager_id, location_id from departments where department_id = 110");
        System.out.println("rowMap.toString() = " + rowMap.toString());

       Object cellValue = DBUtils.getCellValue("select department_name from departments where department_id = 110");
       String value =  (String) cellValue;
        System.out.println("value = " + value);
        DBUtils.destroy();


    }

    @Test
    public void test5(){
        DBUtils.createConnection();
        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap("select * from departments");

        System.out.println("queryResultMap.size() = " + queryResultMap.size());

        System.out.println("queryResultMap.get(3).toString() = " + queryResultMap.get(3).toString());

        DBUtils.destroy();


    }

    @Test
    public void test6(){
        DBUtils.createConnection();
        Map<String, Object> rowMap = DBUtils.getRowMap("select first_name, last_name, salary, employee_id from employees where employee_id=114");
        System.out.println("rowMap.toString() = " + rowMap.toString());
        DBUtils.destroy();


    }

    @Test
    public void test7(){
        DBUtils.createConnection();
        Object cellValue = DBUtils.getCellValue("select department_name from departments where department_id=30");
        String value = (String) cellValue;  //  casting
        System.out.println("value = " + value);
        DBUtils.destroy();

    }
}
