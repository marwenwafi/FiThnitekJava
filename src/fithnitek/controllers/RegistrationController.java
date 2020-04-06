/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import fithnitek.utils.BCryptPasswordEncoder;
import fithnitek.models.*;
import java.net.URL;
import java.io.File;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 *
 * @author marwe
 */
public class RegistrationController implements Initializable{

    @FXML
    private AnchorPane Pane;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirm;
    @FXML
    private TextField surname;
    @FXML
    private TextField tel;
    @FXML
    private DatePicker birthdate;
    
    private String ImageFile = "";
    
    BCryptPasswordEncoder b = new BCryptPasswordEncoder();

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @FXML
    private void uploadPicture(MouseEvent event) throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pictures", "*.png"),
                new FileChooser.ExtensionFilter("Pictures", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(Pane.getScene().getWindow());
        ImageFile = selectedFile.getName();
        System.out.println(selectedFile.getName());
    }
    
    @FXML
    private void register(MouseEvent event) throws Exception {
        
        UserController uc = new UserController();
        MainLoginController mlc = new MainLoginController();
        String un = username.getText();
        String em = email.getText();
        String pass = password.getText();
        String conf = confirm.getText();
        String sur = surname.getText();
        int te = Integer.parseInt(tel.getText());
        Date bd = java.sql.Date.valueOf(birthdate.getValue());
        uc.attemptRegistration(un,em,pass,conf,sur,te,bd,ImageFile);
        
        if (!password.equals(confirm))
        {
            throw new IllegalArgumentException("Password confirmation missmatch");
        }
        else
        {
            String hashedpass = b.hashPassword(password);
            User u = new User(email,username,surname,hashedpass,tel,birthdate,image);
            uc.ajouter(u);
            //mlc.att(username, password);
            System.out.println("User Added Successfully");
        }
        Parent next = FXMLLoader.load(getClass().getResource("/fithnitek/views/mainMenu.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
        
        
    }
    
    @FXML 
    public void processKeyEvent(KeyEvent ev) {
        System.out.println("proce");
        String c = ev.getCharacter();
        if("1234567890".contains(c)) {}
        else {
            ev.consume();
        }
    }
    
    
}
