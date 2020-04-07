package com.bombsimon.as400test;

import com.ibm.as400.access.AS400JDBCDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class As400Test {
    public static void main(String[] args) {
        String driver = "com.ibm.as400.access.AS400JDBCDriver";
        String uri = "jdbc:as400://hostname";

        try {
            Class.forName(driver);

            System.out.println("Connecting to AS400");

            Connection conn = DriverManager.getConnection(uri, "some-user", "some-password");
            Statement stmt = conn.createStatement();

            ResultSet resultSet = stmt.executeQuery("SELECT * FROM table.column LIMIT 1");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
            }

            System.out.println("Closing connection");
            conn.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
