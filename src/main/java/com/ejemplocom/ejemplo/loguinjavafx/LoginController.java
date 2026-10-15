package com.ejemplocom.ejemplo.loguinjavafx;

import java.io.IOException;
import javafx.fxml.FXML;
import com.ejemplocom.ejemplo.loguinjavafx.dao.UsuarioDAO;
import com.ejemplocom.ejemplo.loguinjavafx.entidades.Usuario;
import com.ejemplocom.ejemplo.loguinjavafx.util.SesionUsuario;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtClave;
    @FXML private Label lblMensaje;
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    @FXML
    private void ingresar(){
        String correo = txtCorreo.getText().trim();
        String clave = txtClave.getText();   
        if (correo.isEmpty() || clave.isEmpty()) {
            lblMensaje.setText("Complete todos los campos.");
            return;
        }       
        try {
            Usuario usuario = usuarioDAO.autenticar(correo, clave);

            if (usuario == null) {
                lblMensaje.setText("Correo o contraseña incorrectos.");
                txtClave.clear();
                return;
            }

            SesionUsuario.iniciar(usuario);   // guardamos quién entró
            App.setRoot("home");              // navegamos a Home

        } catch (Exception e) {
            lblMensaje.setText("No se pudo conectar con la base de datos.");
            e.printStackTrace();
        }
    }
}
