package com.github.ivanbakurevich;


import java.sql.*;

public class Program {
    public static void main(String[] args) {
/*        String dbUser= "postgres";
        String dbPassword="1111";
        String connectionUrl ="jdbc:postgresql://localhost:5432/fix_users";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(connectionUrl,dbUser,dbPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM fix_user");

            while(resultSet.next()){
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
                System.out.println();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        try {
            Class.forName("org.postgresql.Driver");
            String dbUser="postgres";
            String dbPassword = "1111";
            String connectionUrl="jdbc:postgresql://localhost:5432/";
            Connection connection = DriverManager.getConnection(connectionUrl,dbUser,dbPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT  * FROM fix_user");
            while(resultSet.next()){
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new IllegalArgumentException();
        }
    }
}
