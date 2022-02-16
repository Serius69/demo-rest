package bo.edu.ucb.insoft.demorest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@RestController
public class HelloController {


    //SEARCH BY NAME
    @GetMapping(path = "/hello")
    public Persona helloworld() {
        Persona result = new Persona();
        result.nombre="Juan";
        return result;
    }
}
