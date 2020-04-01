package fithnitek.controllers;

import java.io.IOException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author marwe
 */
public class mainLoginController implements Initializable {
    @FXML
    private Pane content;
    @FXML
    private AnchorPane Pane;
    @FXML
    private Label label;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    
        
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
        String un = username.getText();
        String pass = password.getText();
        uc.attemptLogin(un, pass);
        Parent next = FXMLLoader.load(getClass().getResource("/fithnitek/views/mainMenu.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
