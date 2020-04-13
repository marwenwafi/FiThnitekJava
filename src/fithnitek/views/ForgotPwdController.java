/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author marwe
 */
public class ForgotPwdController implements Initializable {

    @FXML
    private AnchorPane Pane;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXButton send;
    @FXML
    private Text label;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField confirm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sendcode(MouseEvent event) {
    }

    @FXML
    private void confirmNewPwd(MouseEvent event) {
    }
    
}
