/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import fithnitek.models.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainMenuController implements Initializable {
    
    protected User currentUser;

    
    
    @FXML
    private Label welcomename;
    @FXML
    private Label usernameLabel;
    @FXML
    private JFXButton dashboard;
    
    
    

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser, boolean isAdmin) {
        this.currentUser = currentUser;
        this.welcomename.setText("Welcome: ");
        this.usernameLabel.setText(currentUser.getUsername());
        this.dashboard.setVisible(isAdmin);
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void goDashboard(MouseEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fithnitek/views/dashboard.fxml"));
        Parent next = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }
    
}
