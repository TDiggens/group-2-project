package com.napier.sem;
import java.sql.*;

public class SanityCheck
{
    public static void main(String[] args)
    {
        System.out.println("Do not be alarmed, this is a test. Hello Group 2!");


                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "Chichi!2");
                    Statement stmt = conn.createStatement();
                    String selectQuery = "SELECT name FROM country";
                    ResultSet resultSet = stmt.executeQuery(selectQuery);

                    while (resultSet.next()) {
                        String name = resultSet.getString("name");
                        System.out.println(name);
                    }
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }


            }
}
