package com.example.d4_jdbc;
import domain.Pizza;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class D4JdbcApplication {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/jdbc_assignment";
    static final String USER = "root";
    static final String PASSWORD = "%CicAfcg6qaTBa";

    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;

//insert into pizza (id, name, price) value (7, 'cheesecake pizza', 4);
//select * from pizza;
//delete from pizza where id=7;
//update pizza set price="4.95" where id=7;
//select * from pizza where id=7;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            statement = conn.createStatement();

//            String insertQuery = "insert into pizza (id, name, price) value (7, 'cheesecake pizza', 4);";
//            System.out.println(statement.execute(insertQuery));

            String getAllPizzasQuery = "select * from pizza;";
            List<Pizza> pizzaList = new ArrayList<Pizza>();
            ResultSet resultSet = statement.executeQuery(getAllPizzasQuery);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                float price = resultSet.getFloat("price");
                Pizza pizza = new Pizza(id, name, price);
                pizzaList.add(pizza);
                System.out.println(pizza.toString());
            }
            resultSet.close();

//            String updatePizzaQuery = "update pizza set price=\"4.50\" where id=7;";
//            System.out.println(statement.executeUpdate(updatePizzaQuery));


        } catch (ClassNotFoundException | SQLException e) {
            System.out.printf("driver:%s, url:%s",JDBC_DRIVER, DB_URL);
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
                if (statement != null) statement.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

}
