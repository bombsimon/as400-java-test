package com.bombsimon.as400test;

import com.ibm.as400.access.AS400JDBCDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class As400Test {
    public static void main(String[] args) {
        DBProperties config = DBProperties.getInstance();

        String driver = "com.ibm.as400.access.AS400JDBCDriver";
        String uri = String.format(
                "jdbc:as400://%s:%d",
                config.getValue("db.hostname"),
                config.getIntValue("db.port")
        );

        try {
            Class.forName(driver);

            System.out.printf("Connecting to AS400 (%s) as %s\n", uri, config.getValue("db.username"));

            Connection conn = DriverManager.getConnection(uri, config.getValue("db.username"), config.getValue("db.password"));
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
