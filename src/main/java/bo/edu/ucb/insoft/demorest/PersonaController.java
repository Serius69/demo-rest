package bo.edu.ucb.insoft.demorest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonaController {

    @Autowired
    public DataSource dataSource;


    //SEARCH BY NAME
    @GetMapping(path = "/persona/{nombrepersona}")
    public Persona findPersonaByName( @PathVariable String personaNombre) {
        Persona result = new Persona();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nombres FROM persona" +
                    "  WHERE nombres = " + personaNombre);
            if (rs.next()) {
                result.nombre = rs.getString("nombres");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    //SEARCH
    @GetMapping(path = "/persona/{personaid}")
    public Persona findPersonaById( @PathVariable Integer personaId) {
        Persona result = new Persona();

        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id,nombres FROM persona" +
                    "  WHERE id = " + personaId);
            if (rs.next()) {
                result.nombre = rs.getString("nombres");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


    /*
    //listado
    @GetMapping(path = "/persona")
    public List<Persona> findAllPersonas() {
        List<Persona> result = new ArrayList<>();

        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nombres FROM persona");

            //ResultSet rs = stmt.executeQuery("SELECT id, nombres, apellidos, fe,apellido_casado, telefono, fecha_nacimiento, id_direccion, correo_electronico " +
            //        "FROM persona");
            while (rs.next()) {
                Persona persona = new Persona();
                persona.personaId = rs.getInt("id_persona");
                persona.nombre = rs.getString("nombre");
                persona.apellidoPaterno = rs.getString("apellido_paterno");
                persona.apellidoMaterno = rs.getString("apellido_materno");
                persona.apellidoCasado = rs.getString("apellido_casado");
                persona.telefono = rs.getInt("telefono");
                persona.fechaNacimiento = rs.getString("fecha_nacimiento");
                persona.direccionId = rs.getString("id_direccion");
                persona.correoElectronico = rs.getString("correo_electronico");
                result.add(persona);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    //CREATE
    @PostMapping(path = "/persona")
    public Persona createPersona(@RequestBody Persona persona) {
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO persona VALUES ("
                    + persona.personaId +", '"
                    + persona.nombre +"', '"
                    + persona.apellidoPaterno+"', '"
                    + persona.apellidoMaterno+"', '"
                    + persona.apellidoCasado+"', "
                    + persona.telefono +", '"
                    + persona.fechaNacimiento +"', '"
                    + persona.direccionId+"', '"
                    + persona.correoElectronico+"') ");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return persona;
    }

    // Completar los metodos UPDATE y DELETE

    //UPDATE


    //DELETE
    /*
    @GetMapping(path = "/persona/{personaId}")
    public Persona deletePersonaById( @PathVariable Integer personaId) {
        Persona result = new Persona();

        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("DELETE * FROM persona" +
                    "  WHERE persona_id = " + personaId);
            if (rs.next()) {
                result.personaId = rs.getInt("persona_id");
                }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }*/

}
