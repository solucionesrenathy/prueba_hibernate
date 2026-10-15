package it.pkg;

import it.pkg.entidades.Usuario;
import it.pkg.util.SesionUsuario;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
public class HomeController {

    @FXML private Label lblBienvenida;
    
    @FXML
    private void initialize(){
        Usuario usuario = SesionUsuario.getUsuarioActual();
        if (usuario != null) {
            lblBienvenida.setText("Bienvenido, " + usuario.getNombre() + " " + usuario.getApellido());
        }
    }
    
     @FXML
    private void cerrarSesion() throws IOException {
        SesionUsuario.cerrar();     // eliminamos la sesión
        App.setRoot("login");       // volvemos al login
    }
    
}