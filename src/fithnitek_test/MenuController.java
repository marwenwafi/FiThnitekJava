/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek_test;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author yassin
 */
public class MenuController implements Initializable {

    @FXML
    private Button contact;
    @FXML
    private Button rate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage window =new Stage();
        window.setScene(scene);
        window.show();
                    final Node source = (Node) event.getSource();
           final Stage stage = (Stage) source.getScene().getWindow();
       stage.close();  
    }

    @FXML
    private void evaluation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EvaluationFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage window =new Stage();
        window.setScene(scene);
        window.show();
                    final Node source = (Node) event.getSource();
           final Stage stage = (Stage) source.getScene().getWindow();
       stage.close();
    }
    
}
