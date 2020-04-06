/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author sourour
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private DatePicker dateFin;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private TextField url;
    @FXML
    private Button Ajouter;
    @FXML
    private TextField promotion;
    @FXML
    private TextField description;
    @FXML
    private ChoiceBox<?> option;
    @FXML
    private Button image;
    @FXML
    private TableView<?> dsfbghj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterEvent(ActionEvent event) {
    }

    @FXML
    private void modifierEvent(ActionEvent event) {
    }

    @FXML
    private void deleteEvent(ActionEvent event) {
    }

    @FXML
    private void ChoisirImage(ActionEvent event) {
    }

    @FXML
    private void afficherEvent(ActionEvent event) {
    }
    
}
