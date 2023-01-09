package it.develhope.jdbc;

import java.sql.*;

public class DriverJDBC {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/newdb";
    private static final String USER = "developer";
    private static final String PASSWORD = "developer1?";

    public static void main(String[] args) {

        Connection conn = null;

        try { //main try-block

            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            System.out.printf("Connected to database %s successfully.\n\n", conn.getCatalog());

            Statement statement = conn.createStatement();

            try { //alter table try-block
                statement.executeUpdate("ALTER TABLE students ADD country varchar(30);");
                System.out.println("Add country column to students table.\n");
            } catch(SQLException e){
                System.out.println(e.getMessage() + "\n"); //Duplicate column name 'country' (if table 'students' exists)
            }

            statement.executeUpdate("UPDATE students SET country = 'Italy' WHERE student_id = 1");
            statement.executeUpdate("UPDATE students SET country = 'Italy' WHERE student_id = 2");
            statement.executeUpdate("UPDATE students SET country = 'Germany' WHERE student_id = 3");
            statement.executeUpdate("UPDATE students SET country = 'Germany' WHERE student_id = 4");

            System.out.println("Updated country for students.");

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if(conn != null){
                    conn.close();
                }
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }

    }

}
