/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import GuiTAXI.taxihomeController;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import fithnitek.models.*;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.image.Image;


public class MainMenuController implements Initializable {
    
    protected static User currentUser;
    protected static boolean isAdmin;
    protected static Image pic;

    
    @FXML
    private Label welcomename;
    //private Label usernameLabel;
    //private JFXButton dashboard;
    @FXML
    private ImageView user;
    @FXML
    private ImageView maha;
    @FXML
    private ImageView siwar;
    @FXML
    private ImageView yassin;
    @FXML
    private ImageView jassem;
    @FXML
    private ImageView marwen;
    @FXML
    private ImageView logo;
    @FXML
    private ImageView sourour;
    @FXML
    private ImageView khat;
    @FXML
    private JFXButton upadateprofile;
    @FXML
    private JFXButton logout;
    @FXML
    private JFXButton dashboard;
    
    
    

    public User getCurrentUser() {
        return currentUser;
    }

    public Image getPic() {
        return pic;
    }

    public void setPic(Image pic) {
        MainMenuController.pic = pic;
    }
    
    

    public void setCurrentUser(User currentUser, boolean isAdmin, Image pic) {
        this.currentUser = currentUser;
        this.isAdmin = isAdmin;
        this.pic = pic;
        this.welcomename.setText("Welcome: "+currentUser.getUsername());
        this.user.setImage(pic);
        this.dashboard.setVisible(isAdmin);
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (currentUser != null)
        {
            this.welcomename.setText("Welcome: "+currentUser.getUsername());
            this.user.setImage(pic);
            this.dashboard.setVisible(isAdmin);
        }
    }    

    @FXML
    private void goDashboard(MouseEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fithnitek/views/backend.fxml"));
        Parent next = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void maha(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUIcovoiturage/AfficherOffreCovoiturage.fxml"));
        Parent next = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void siwar(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GuiTAXI/home.fxml"));
        Parent next = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void yassin(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fithnitek_test/Menu.fxml"));
        Parent next = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void jassem(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewsColis/AjouterOffre.fxml"));
        Parent next = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void marwen(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fithnitek/views/leaderboardsFront.fxml"));
        Parent next = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void sourour(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Events/Convertir.fxml"));
        Parent next = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void logOut(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fithnitek/views/mainLogin.fxml"));
        Parent next = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void UpdateProfile(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fithnitek/views/updateprofile.fxml"));
        Parent next = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();   
    }
    
}
