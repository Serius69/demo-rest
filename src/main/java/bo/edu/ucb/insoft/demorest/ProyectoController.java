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
    public Proyecto findPersonaById( @PathVariable Integer personaId) {
        Proyecto result = new Proyecto();

        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT persona_id, nombre, apellido FROM persona" +
                    "  WHERE persona_id = " + personaId);  //FIXME SQL INJECTION !!!!!
            if (rs.next()) {
                result.proyecto_id = rs.getInt("id_proyecto");
                result.nombre = rs.getString("nombre");
                result.descripcion = rs.getString("descripcion");
                result.monto_recaudar = rs.getInt("monto_recaudar");
                result.fecha_inicio_id = rs.getInt("id_fecha_inicio");
                result.fecha_final_id = rs.getInt("id_fecha_final");
                result.emprendedor_id = rs.getInt("persona_id");
                result.hora_inicio = rs.getInt("nombre");
                result.hora_fin = rs.getInt("apellido");
                result.estado_id = rs.getInt("apellido");
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
                proyecto.proyecto_id = rs.getInt("persona_id");
                proyecto.nombre = rs.getString("nombre");
                proyecto.descripcion = rs.getString("apellido");
                proyecto.monto_recaudar = rs.getInt("persona_id");
                proyecto.fecha_inicio_id = rs.getInt("nombre");
                proyecto.fecha_final_id = rs.getInt("apellido");
                proyecto.emprendedor_id = rs.getInt("persona_id");
                proyecto.hora_inicio = rs.getInt("nombre");
                proyecto.hora_fin = rs.getInt("apellido");
                proyecto.estado_id = rs.getInt("apellido");
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
}
