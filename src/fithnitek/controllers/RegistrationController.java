/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import com.jfoenix.controls.JFXButton;
import static fithnitek.controllers.UsersController.isValid;
import fithnitek.utils.BCryptPasswordEncoder;
import fithnitek.models.*;
import fithnitek.utils.EmailVerification;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.commons.io.FilenameUtils;
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;


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
    @FXML
    private JFXButton image;
    
    private String ImageFile = "";
    
    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    UserController uc = new UserController();
    Alert alert;
    File selectedFile;
    User newUser = null;
    User currentuser;

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainMenuController mmc = new MainMenuController();
        currentuser = mmc.getCurrentUser();
        if(currentuser != null)
        {
            username.setText(currentuser.getUsername());
            email.setText(currentuser.getEmail());
            surname.setText(currentuser.getPrenom());
            tel.setText(currentuser.getTel()+"");
            LocalDate mm = LocalDate.parse(currentuser.getDatedenaissance().toString(), formatter);
            birthdate.setValue(mm);
            image.setText(currentuser.getImage());
            ImageFile = currentuser.getImage();
            selectedFile = new File("C://wamp64/www/PiDev/web/uploads/profiles/"+currentuser.getImage());
        }
    }
    
    @FXML
    private void register(MouseEvent event) throws Exception {
        
        MainLoginController mlc = new MainLoginController();
        String un = username.getText();
        String em = email.getText();
        String pass = password.getText();
        String conf = confirm.getText();
        String sur = surname.getText();
        String te = tel.getText();
        
        User u = uc.findByUsername(un);    
        Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        TextInputDialog verif = new TextInputDialog("Enter verification code");
        verif.setHeaderText("A verification code was sent to your email");
        
        if (un.equals(""))
        {
            alert = new Alert(Alert.AlertType.ERROR, "You Have to insert username", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else if (u!=null)
        {
            alert = new Alert(Alert.AlertType.ERROR, "Username already used!", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else if (!isValid(em))
        {
            alert = new Alert(Alert.AlertType.ERROR, "Email format Invalid!", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else if (!pass.equals(conf) || pass.equals(""))
        {
            alert = new Alert(Alert.AlertType.ERROR, "Password missmatch!", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else if (sur.equals(""))
        {
            alert = new Alert(Alert.AlertType.ERROR, "Surname missing!", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else if (birthdate.getValue()==null || java.sql.Date.valueOf(birthdate.getValue()).after(new java.sql.Date(Calendar.getInstance().getTime().getTime())))
        {
            alert = new Alert(Alert.AlertType.ERROR, "Invalid Birthdate!", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else if (te.equals(""))
        {
            alert = new Alert(Alert.AlertType.ERROR, "Input Telephone number!", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else if (ImageFile.equals(""))
        {
            alert = new Alert(Alert.AlertType.ERROR, "You have to add an image!", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else
        {
            EmailVerification ev = new EmailVerification();
            String code = ev.generateCode();
            ev.sendMail(code,em);            
            verif.showAndWait();
            
            if (verif.getEditor().getText().equals(code))
            {
                Date bd = java.sql.Date.valueOf(birthdate.getValue());
                BufferedImage bImage = ImageIO.read(selectedFile);
                String hashedpass = bcrypt.hashPassword(password.getText());
                String extension = FilenameUtils.getExtension(ImageFile);
                ImageIO.write(bImage, extension, new File("C://wamp64/www/PiDev/web/uploads/profiles/"+un+"."+extension));
                u = new User(em,un,sur,hashedpass,Integer.parseInt(te),bd,un+"."+extension,1,"a:0:{}");
                uc.ajouter(u);
                System.out.println("User Added Successfully");
                Parent next = FXMLLoader.load(getClass().getResource("/fithnitek/views/mainLogin.fxml"));
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(next);
                stage.setScene(scene);
                stage.show();
            }
        }

    }
    
    @FXML
    private void modifyUser(MouseEvent event) throws IOException {
        String un = username.getText();
        String em = email.getText();
        String pass = password.getText();
        String conf = confirm.getText();
        String sur = surname.getText();
        String te = tel.getText();
        
        User u = uc.findByUsername(un);    
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        
        if (un.equals(""))
        {
            alert = new Alert(Alert.AlertType.ERROR, "You Have to insert username", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else if (!isValid(em))
        {
            alert = new Alert(Alert.AlertType.ERROR, "Email format Invalid!", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else if (!pass.equals(conf))
        {
            alert = new Alert(Alert.AlertType.ERROR, "Password missmatch!", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else if (sur.equals(""))
        {
            alert = new Alert(Alert.AlertType.ERROR, "Surname missing!", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else if (birthdate.getValue()==null || java.sql.Date.valueOf(birthdate.getValue()).after(new java.sql.Date(Calendar.getInstance().getTime().getTime())))
        {
            alert = new Alert(Alert.AlertType.ERROR, "Invalid Birthdate!", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else if (te.equals(""))
        {
            alert = new Alert(Alert.AlertType.ERROR, "Input Telephone number!", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else if (ImageFile.equals(""))
        {
            alert = new Alert(Alert.AlertType.ERROR, "You have to add an image!", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else if (u != null)
        {
            if (!u.getUsername().equals(currentuser.getUsername()))
            {
                alert = new Alert(Alert.AlertType.ERROR, "Username already used!", ButtonType.CANCEL);
                alert.showAndWait();
            }
            else
            {
                Date bd = java.sql.Date.valueOf(birthdate.getValue());
                BufferedImage bImage = ImageIO.read(selectedFile);
                String hashedpass = currentuser.getHashedPwd();
                if(!pass.equals(""))
                    hashedpass = bcrypt.hashPassword(pass);
                String extension = FilenameUtils.getExtension(ImageFile);
                ImageIO.write(bImage, extension, new File("C://wamp64/www/PiDev/web/uploads/profiles/"+un+"."+extension));
                u = new User(currentuser.getId(),em,un,sur,hashedpass,Integer.parseInt(te),bd,un+"."+extension,1,"a:0:{}");
                uc.modifier(u);
                Parent next = FXMLLoader.load(getClass().getResource("/fithnitek/views/mainMenu.fxml"));
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(next);
                stage.setScene(scene);
                stage.show();
            }
        }   
    }
    
    @FXML
    private void uploadPicture(MouseEvent event) throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pictures", "*.png","*.jpg"));
        selectedFile = fileChooser.showOpenDialog(Pane.getScene().getWindow());
        if (selectedFile != null)
        {
            ImageFile = selectedFile.getName();
            image.setText(selectedFile.getName());
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
    
    @FXML
    private void back(MouseEvent event) throws Exception {
        Parent next = FXMLLoader.load(getClass().getResource("/fithnitek/views/mainLogin.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void backMainMenu(MouseEvent event) throws Exception {
        Parent next = FXMLLoader.load(getClass().getResource("/fithnitek/views/mainMenu.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }
    
    
}
