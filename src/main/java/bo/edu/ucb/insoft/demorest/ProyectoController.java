package bo.edu.ucb.insoft.demorest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProyectoController {

    @Autowired
    public DataSource dataSource;

    @GetMapping(path = "/proyecto/{proyectoId}")
    public Proyecto findProyectoById( @PathVariable Integer proyectoId) {
        Proyecto result = new Proyecto();

        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT persona_id, nombre, apellido FROM persona" +
                    "  WHERE id_proyecto = " + proyectoId);  //FIXME SQL INJECTION !!!!!
            if (rs.next()) {
                result.proyectoId = rs.getInt("id_proyecto");
                result.nombre = rs.getString("nombre");
                result.descripcion = rs.getString("descripcion");
                result.montoRecaudar = rs.getInt("monto_recaudar");
                result.fechaInicioId = rs.getInt("id_fecha_inicio");
                result.fechaFinalId = rs.getInt("id_fecha_final");
                result.emprendedorId = rs.getInt("persona_id");
                result.horaInicio = rs.getInt("nombre");
                result.horaFin = rs.getInt("apellido");
                result.estadoId = rs.getInt("apellido");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @GetMapping(path = "/persona")
    public List<Proyecto> findAllPersonas() {
        List<Proyecto> result = new ArrayList<>();

        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_proyecto, nombre, descripcion FROM proyecto");
            while (rs.next()) {
                Proyecto proyecto = new Proyecto();
                proyecto.proyectoId = rs.getInt("persona_id");
                proyecto.nombre = rs.getString("nombre");
                proyecto.descripcion = rs.getString("apellido");
                proyecto.montoRecaudar = rs.getInt("persona_id");
                proyecto.fechaInicioId = rs.getInt("nombre");
                proyecto.fechaFinalId = rs.getInt("apellido");
                proyecto.emprendedorId = rs.getInt("persona_id");
                proyecto.horaInicio = rs.getInt("nombre");
                proyecto.horaFin = rs.getInt("apellido");
                proyecto.estadoId = rs.getInt("apellido");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @PostMapping(path = "/persona")
    public Proyecto createPersona(@RequestBody Proyecto persona) {
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO persona VALUES ("
                    + persona.proyecto_id +", '"
                    + persona.nombre +"', '"+ persona.descripcion+"') ");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return persona;
    }
    // Completar los metodos UPDATE y DELETE

    //UPDATE


    //DELETE
    @GetMapping(path = "/persona/{personaId}")
    public Persona deletePersonaById( @PathVariable Integer personaId) {
        Persona result = new Persona();

        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("DELETE * FROM persona" +
                    "  WHERE persona_id = " + personaId);  //FIXME SQL INJECTION !!!!!
            if (rs.next()) {
                result.personaId = rs.getInt("persona_id");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
