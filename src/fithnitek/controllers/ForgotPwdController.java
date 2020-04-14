/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import fithnitek.models.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import fithnitek.utils.BCryptPasswordEncoder;
import fithnitek.utils.EmailVerification;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private JFXPasswordField confirmp;
    @FXML
    private JFXButton confirm;
    
    Alert alert;
    UserController uc = new UserController();
    User u = null;
    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sendcode(MouseEvent event) {
        TextInputDialog verif = new TextInputDialog("Enter verification code");
        verif.setHeaderText("A verification code was sent to your email");
        EmailVerification ev = new EmailVerification();
        String code = ev.generateCode();
        String em = email.getText();
        
        if (em.equals("") || !isValid(em) )
        {
            alert = new Alert(Alert.AlertType.ERROR, "Invalid email", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else
        {
            u = uc.findByEmail(em);
            if (u == null)
            {
                alert = new Alert(Alert.AlertType.ERROR, "No such user", ButtonType.CANCEL);
                alert.showAndWait();
            }
            else
            {
                ev.sendMail(code,em);            
                verif.showAndWait();
                if (verif.getEditor().getText().equals(code))
                {
                    email.setVisible(false);
                    send.setVisible(false);
                    password.setVisible(true);
                    confirmp.setVisible(true);
                    confirm.setVisible(true);
                    label.setText("Please enter your new password");
                }
                else
                {
                    alert = new Alert(Alert.AlertType.ERROR, "Incorrect code", ButtonType.CANCEL);
                    alert.showAndWait();
                }
            }
            
        }
        
        
    }

    @FXML
    private void confirmNewPwd(MouseEvent event) throws IOException {
        String pass = password.getText();
        String conf = confirmp.getText();
        if (!pass.equals(conf) || pass.equals(""))
        {
            alert = new Alert(Alert.AlertType.ERROR, "Password missmatch!", ButtonType.CANCEL);
            alert.showAndWait();
        } 
        else
        {
            String hashedpass = bcrypt.hashPassword(password.getText());
            uc.updatePassword(u.getUsername(), hashedpass);
            Parent next = FXMLLoader.load(getClass().getResource("/fithnitek/views/mainLogin.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(next);
            stage.setScene(scene);
            stage.show();
        }
        
    }
    
    
    public static boolean isValid(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$"; 
                              
        Pattern pat = Pattern.compile(emailRegex);
        System.out.println(email);
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    }
}