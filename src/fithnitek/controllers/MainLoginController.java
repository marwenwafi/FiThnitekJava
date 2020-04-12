package fithnitek.controllers;

import fithnitek.models.*;
import fithnitek.utils.BCryptPasswordEncoder;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class MainLoginController implements Initializable {
    
    @FXML
    private Pane content;
    @FXML
    private Label label;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    
    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    Alert alert;
        
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        //Label.setText("Hello World!");
    }
    
    @FXML
    private void show_registration(MouseEvent event) throws Exception {
        Parent register = FXMLLoader.load(getClass().getResource("/fithnitek/views/register.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(register);
    }
    
    @FXML
    private void attemptLogin(MouseEvent event) throws Exception {
        UserController uc = new UserController();
        String user = username.getText();
        String pass = password.getText();
        User u = uc.findByUsername(user);
        if (u == null)
        {
            alert = new Alert(Alert.AlertType.ERROR, "Invalid username!", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else if (u.getEnabled()==0)
        {
            alert = new Alert(Alert.AlertType.ERROR, "Account desactivated! Please Contact Admin at admin@fithnitek.com", ButtonType.CANCEL);
            alert.showAndWait();
        }
        else
        {
            u = uc.findOneUser(user);
            if (!bcrypt.checkPassword(pass, u.getHashedPwd()))
            {
                alert = new Alert(Alert.AlertType.ERROR, "Invalid password!", ButtonType.CANCEL);
                alert.showAndWait();
            }
            
            else
            {
                                boolean isAdmin = u.getRoles().contains("ROLE_ADMIN");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fithnitek/views/mainMenu.fxml"));
                Parent next = loader.load();
                MainMenuController mainController = loader.<MainMenuController>getController();
                File file = new File("C://wamp64/www/PiDev/web/uploads/profiles/"+u.getImage());
                Image image = new Image(file.toURI().toString());
                mainController.setCurrentUser(u,isAdmin,image);
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(next);
                stage.setScene(scene);
                stage.show();

            }
            
        }
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void showforgot(MouseEvent event) throws Exception {
        Parent register = FXMLLoader.load(getClass().getResource("/fithnitek/views/forgotPwd.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(register);
    }
    
}
