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

public class UsuarioController {

    @Autowired
    public DataSource dataSource;

    @GetMapping(path = "/usuario/{usuarioId}")
    public Usuario findUsuarioById( @PathVariable Integer usuarioId) {
        Usuario result = new Usuario();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_usuario, id_tipo_usuario, usuario,contrasena " +
                    "   FROM usuario" +
                    "   WHERE id_usuario = " + usuarioId);
            if (rs.next()) {
                result.usuarioId = rs.getInt("id_usuario");
                result.tipoUsuarioId = rs.getString("id_tipo_usuario");
                result.usuario = rs.getString("usuario");
                result.contrasena = rs.getString("contrasena");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    //seleccionar la tabla usuario
    @GetMapping(path = "/usuario")
    public List<Proyecto> findAllUsuarios() {
        List<Proyecto> result = new ArrayList<>();

        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_usuario, id_tipo_usuario, usuario, descripcion FROM usuario");
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.usuarioId = rs.getInt("id_usuario");
                usuario.tipoUsuarioId = rs.getString("id_tipo_usuario");
                usuario.usuario = rs.getString("usuario");
                usuario.contrasena = rs.getString("contrasena");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    //crear un nuevo usuario en la base de datos
    @PostMapping(path = "/usuario")
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO usuario VALUES ("
                    + usuario.usuarioId +", '"
                    + usuario.tipoUsuarioId +"', '"
                    + usuario.usuario +"', '"
                    + usuario.contrasena+"') ");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return usuario;
    }
    // Completar los metodos UPDATE y DELETE

    //actualizar tabla usuario
    @PostMapping(path = "/usuario")
    public Usuario updateUsuario(@RequestBody Usuario usuario) {
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE INTO usuario " +
                    " SET id_tipo_usuario = " + usuario.tipoUsuarioId +","
                    + "usuario = " + usuario.usuario +","
                    + "contrasena = " + usuario.contrasena +" "
                    + "WHERE id_usuario = " + usuario.usuarioId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return usuario;
    }
    //DELETE
    @GetMapping(path = "/usuario/{usuarioId}")
    public Usuario deleteUsuarioById( @PathVariable Integer usuarioId) {
        Usuario result = new Usuario();

        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("DELETE * FROM usuario" +
                    "  WHERE id_usuario = " + usuarioId);
            if (rs.next()) {
                result.usuarioId = rs.getInt("persona_id");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
