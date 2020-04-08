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
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    
    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    Alert alert;

    

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
        String te = tel.getText();
        
        User u = uc.findByUsername(un);    
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        
        if (un=="")
        {
            alert = new Alert(Alert.AlertType.ERROR, "You Have to insert username", ButtonType.CANCEL);
            alert.showAndWait();
        }
        if (u!=null)
        {
            alert = new Alert(Alert.AlertType.ERROR, "Username already used!", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else if (!isValid(em))
        {
            alert = new Alert(Alert.AlertType.ERROR, "Email format Invalid!", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else if (!pass.equals(conf) || pass=="" )
        {
            alert = new Alert(Alert.AlertType.ERROR, "Password missmatch!", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else if (birthdate.getValue()==null || java.sql.Date.valueOf(birthdate.getValue()).after(new java.sql.Date(Calendar.getInstance().getTime().getTime())))
        {
            alert = new Alert(Alert.AlertType.ERROR, "Invalid Birthdate!", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else if (te=="")
        {
            alert = new Alert(Alert.AlertType.ERROR, "Input Telephone number!", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else
        {
            Date bd = java.sql.Date.valueOf(birthdate.getValue());
            String hashedpass = bcrypt.hashPassword(password.getText());
            u = new User(em,un,sur,hashedpass,Integer.parseInt(te),bd,ImageFile,1,"a:0:{}");
            uc.ajouter(u);
            //mlc.att(username, password);
            System.out.println("User Added Successfully");
            Parent next = FXMLLoader.load(getClass().getResource("/fithnitek/views/mainLogin.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(next);
            stage.setScene(scene);
            stage.show();
        }

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
