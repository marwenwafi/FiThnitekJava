/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author marwe
 */
public class DashboardController implements Initializable {

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
    
}
