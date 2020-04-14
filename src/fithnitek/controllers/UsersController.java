/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import fithnitek.models.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import static fithnitek.controllers.RegistrationController.isValid;
import fithnitek.utils.BCryptPasswordEncoder;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.commons.io.FilenameUtils;

/**
 * FXML Controller class
 *
 * @author marwe
 */
public class UsersController implements Initializable {
    
    @FXML
    private Pane pane;
    @FXML
    private JFXButton add;
    @FXML
    private JFXButton modify;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField confirm;
    @FXML
    private JFXTextField surname;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXDatePicker birthdate;
    @FXML
    private JFXButton image;
    @FXML
    private TableView<User> tableview;
    @FXML
    private TableColumn<User, String> username_c;
    @FXML
    private TableColumn<User, String> email_c;
    @FXML
    private TableColumn<User, Number> enabled_c;
    @FXML
    private TableColumn<User, Number> tel_c;
    @FXML
    private TableColumn<User, Number> nbrOffre_c;
    @FXML
    private TableColumn<User, Number> point_c;
    @FXML
    private JFXButton enable;

    private ObservableList<User> data = FXCollections.observableArrayList();
    private UserController uc = new UserController();
    private List<User> list;
    private User rowData;
    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    Alert alert;
    File selectedFile = null;
    private String ImageFile = "";
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refresh();
        tableview.setRowFactory( tv -> {
        TableRow<User> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            rowData = row.getItem();
            if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                enableEnableButton();
            }
            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                loadRowToModify(rowData);
            }
        });
        return row ;
        });
    }    

     @FXML
    private void addUser(MouseEvent event) throws IOException {
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
            Date bd = java.sql.Date.valueOf(birthdate.getValue());
            BufferedImage bImage = ImageIO.read(selectedFile);
            String hashedpass = bcrypt.hashPassword(password.getText());
            String extension = FilenameUtils.getExtension(ImageFile);
            ImageIO.write(bImage, extension, new File("C://wamp64/www/PiDev/web/uploads/profiles/"+un+"."+extension));
            u = new User(em,un,sur,hashedpass,Integer.parseInt(te),bd,un+"."+extension,1,"a:0:{}");
            uc.ajouter(u);
            refresh();        
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
            if (!u.getUsername().equals(rowData.getUsername()))
            {
                alert = new Alert(Alert.AlertType.ERROR, "Username already used!", ButtonType.CANCEL);
                alert.showAndWait();
            }
            else
            {
                Date bd = java.sql.Date.valueOf(birthdate.getValue());
                BufferedImage bImage = ImageIO.read(selectedFile);
                String hashedpass = rowData.getHashedPwd();
                if(!pass.equals(""))
                    hashedpass = bcrypt.hashPassword(pass);
                String extension = FilenameUtils.getExtension(ImageFile);
                ImageIO.write(bImage, extension, new File("C://wamp64/www/PiDev/web/uploads/profiles/"+un+"."+extension));
                u = new User(rowData.getId(),em,un,sur,hashedpass,Integer.parseInt(te),bd,un+"."+extension,1,"a:0:{}");
                System.out.println(u);
                uc.modifier(u);
                refresh();
            }
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

    @FXML
    private void uploadPicture(MouseEvent event) throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pictures", "*.png","*.jpg"));
        selectedFile = fileChooser.showOpenDialog(pane.getScene().getWindow());
        if (selectedFile != null)
        {
            ImageFile = selectedFile.getName();
            image.setText(selectedFile.getName());
        }
    }

    public void refresh ()
    {tableview.getItems().clear();
        list = uc.afficher();
        data.addAll(list);
        username_c.setCellValueFactory(new PropertyValueFactory<>("username"));
        email_c.setCellValueFactory(new PropertyValueFactory<>("email"));
        enabled_c.setCellValueFactory(new PropertyValueFactory<>("enabled"));
        tel_c.setCellValueFactory(new PropertyValueFactory<>("tel"));
        nbrOffre_c.setCellValueFactory(new PropertyValueFactory<>("nbroffre"));
        point_c.setCellValueFactory(new PropertyValueFactory<>("points"));
        tableview.setItems(data);
        username.setText("");
        email.setText("");
        password.setText("");
        confirm.setText("");
        surname.setText("");
        tel.setText("");
        birthdate.setValue(null);
        image.setText("Upload picture");
        modify.setDisable(true);
        add.setDisable(false);
        enable.setDisable(true);
        rowData = null;
    }

    public void loadRowToModify(User u)
    {
        username.setText(u.getUsername());
        email.setText(u.getEmail());
        surname.setText(u.getPrenom());
        tel.setText(u.getTel()+"");
        LocalDate mm = LocalDate.parse(u.getDatedenaissance().toString(), formatter);
        birthdate.setValue(mm);
        image.setText(u.getImage());
        ImageFile = u.getImage();
        selectedFile = new File("C://wamp64/www/PiDev/web/uploads/profiles/"+u.getImage());
        modify.setDisable(false);
        add.setDisable(true);
    }
    
    
    @FXML
    private void enable(MouseEvent event) {
        if (rowData.getEnabled()==0)
            uc.enableDisable(rowData.getUsername(), 1);
        else
            uc.enableDisable(rowData.getUsername(), 0);
        refresh();
    }
    
    private void enableEnableButton() {
        enable.setDisable(false);
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
