/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author marwe
 */
public class BackendController implements Initializable {

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
    private ImageView user;
    @FXML
    private JFXButton logout;
    @FXML
    private Label welcomename;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void maha(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUIcovoiturage/AfficherOffreCovoiturageBack.fxml"));
        Parent next = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void siwar(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GuiTAXI/espaceclientB.fxml"));
        Parent next = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void yassin(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fithnitek_test/ReponseFXML.fxml"));
        Parent next = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void jassem(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewsColis/AjouterOffreColisAdmin.fxml"));
        Parent next = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void marwen(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fithnitek/views/dashboardMarwen.fxml"));
        Parent next = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void sourour(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Events/EventQu.fxml"));
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
    private void goMainMenu(MouseEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fithnitek/views/mainMenu.fxml"));
        Parent next = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }
    
}
