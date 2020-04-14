/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marwe
 */
public class DashboardMarwenController implements Initializable {

    @FXML
    private JFXButton categories;
    @FXML
    private JFXButton leaderboards;
    @FXML
    private JFXButton objectifs;
    @FXML
    private JFXButton users;
    @FXML
    private Pane content;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadCategories(MouseEvent event) throws Exception {
        Parent categories = FXMLLoader.load(getClass().getResource("/fithnitek/views/contentCategories.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(categories);
    }
    
    @FXML
    private void loadLeaderBoards(MouseEvent event) throws Exception {
        Parent leader = FXMLLoader.load(getClass().getResource("/fithnitek/views/contentLeaderBoards.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(leader);
    }
    
    @FXML
    private void loadObjectifs(MouseEvent event) throws Exception {
        Parent objectifs = FXMLLoader.load(getClass().getResource("/fithnitek/views/contentObjectifs.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(objectifs);
    }

    @FXML
    private void loadUsers(MouseEvent event) throws Exception {
        Parent users = FXMLLoader.load(getClass().getResource("/fithnitek/views/contentUsers.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(users);
    }
       
    @FXML
    private void mainBackend(MouseEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fithnitek/views/backend.fxml"));
        Parent next = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }
    
}
