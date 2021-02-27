package dapp.Service;

import dapp.Entity.ConnectDB;
import dapp.PersonasProyecto.Persona;

import java.sql.SQLException;
import java.util.List;

public class PersonService {
    public List<Persona> personas;
    ConnectDB db;

    public PersonService(ConnectDB db){
        this.db = db;
    }


    public Persona select(int id) throws SQLException, ClassNotFoundException {
        personas = db.getAllPersonas();

        for (Persona persona : personas){
            if(persona.id == id)
                return persona;
        }

        return null;
    }

    public List<Persona> selectAll() throws SQLException, ClassNotFoundException {
        return db.getAllPersonas();
    }

    public void create(Persona persona) throws SQLException {
        db.insertDatabase(persona);
    }

    public void update(Persona persona){
        db.update(persona);
    }

    public void delete(int id) throws SQLException {
        db.remove(id);
    }


}
