package dapp.Entity;

import dapp.PersonasProyecto.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectDB {
    String myDriver = "com.mysql.cj.jdbc.Driver";
    String myUrl = "jdbc:mysql://127.0.0.1:3306/DDAP";
    Connection conn;


    public ConnectDB() throws ClassNotFoundException, SQLException {
        Class.forName(myDriver);
        conn = DriverManager.getConnection(myUrl, "root", "Yasoricm10");
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

        //System.out.println("✅ Se ha insertado correctamente el registro a la base de datos");
        //System.out.println("✅ Se ha cerrado correctamente la conexión ❌ 🔒 ");

    }

    public List<Persona> getAllPersonas() throws SQLException, ClassNotFoundException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM persona");
        List personList = new ArrayList<Persona>();

        while(rs.next()){
            personList.add(new Persona(rs.getInt("id") ,rs.getString("nombre"), rs.getInt("edad"), rs.getString("genero")));
        }

        //System.out.println("✅ Se ha cerrado correctamente la conexión ❌ 🔒 ");

        return personList;
    }



    public void update(Persona persona){
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("UPDATE persona SET persona.nombre='"+ persona.nombre + "', persona.edad ="+ persona.edad +", persona.genero='"+ persona.genero+"', persona.estatus ="+ persona.estatus+" WHERE id = "+persona.id);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void remove(int id) throws SQLException {
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM persona WHERE id = "+id);
            System.out.println("✅ Se ha cerrado correctamente la conexión ❌ 🔒 ");
        }catch (Exception e){
            System.out.println(e);
        }
    }




}

