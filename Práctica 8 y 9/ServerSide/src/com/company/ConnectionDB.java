package com.company;

import java.sql.*;

/**
 * A Java MySQL PreparedStatement INSERT example.
 * Demonstrates the use of a SQL INSERT statement against a
 * MySQL database, called from a Java program, using a
 * Java PreparedStatement.
 *
 * Created by Alvin Alexander, http://alvinalexander.com
 */
public class ConnectionDB
{

    String myDriver = "com.mysql.cj.jdbc.Driver";
    String myUrl = "jdbc:mysql://127.0.0.1:3306/barveria_v2";
    Connection conn;


    public ConnectionDB() throws SQLException, ClassNotFoundException {
        Class.forName(myDriver);
        conn = DriverManager.getConnection(myUrl, "root", "");
        System.out.println("✅ Se ha conectado a la base de datos 🔌");
    }

    public void insertDatabase(Persona persona) throws SQLException {
        String query = "insert into persona (nombre, edad, genero, estatus)"
                + " values (?, ?, ?, ?)";
        PreparedStatement preparedStmt = conn.prepareStatement(query);

        preparedStmt.setString(1, persona.nombre);
        preparedStmt.setInt(2, persona.edad);
        preparedStmt.setString(3, persona.genero);
        preparedStmt.setString(4, Boolean.toString(persona.estatus) );

        preparedStmt.execute();
        conn.close();

        System.out.println("✅ Se ha insertado correctamente el registro a la base de datos");
        System.out.println("✅ Se ha cerrado correctamente la conexión ❌ 🔒 ");

    }


/*

    public static void stonks(String[] args)
    {
        try
        {
            // create a mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/test";

            // create a sql date object so we can use it in our INSERT statement
            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

            // the mysql insert statement
            String query = " insert into users (first_name, last_name, date_created, is_admin, num_points)"
                    + " values (?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, "Barney");
            preparedStmt.setString (2, "Rubble");
            preparedStmt.setDate   (3, startDate);
            preparedStmt.setBoolean(4, false);
            preparedStmt.setInt    (5, 5000);

            // execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }*/
}