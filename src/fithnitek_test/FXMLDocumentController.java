/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek_test;

import fithnitek.controllers.MainMenuController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import fithnitek.models.Reclamation;
import fithnitek.controllers.ReclamationController;
import fithnitek.models.Reponse;
import fithnitek.models.User;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 *
 * @author yassin
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField sujet;
    @FXML
    private TextArea description;
    
    @FXML
    private TableView<Reclamation> listeReclamation;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private ComboBox rectype;
    @FXML
    private TableColumn<Reclamation, String> idrec;
    @FXML
    private TableColumn<Reclamation, String> iduser;
    @FXML
    private TableColumn<Reclamation, String> taffich;
    @FXML
    private TableColumn<Reclamation, String> saffich;
    @FXML
    private TableColumn<Reclamation, String> daffich;
    @FXML
    private TableColumn<Reclamation, String> dateaffiche;
    
    ReclamationController rc= new ReclamationController();
    ObservableList<Reclamation> oblist=FXCollections.observableArrayList();
    ObservableList<Reponse> reponseliste=FXCollections.observableArrayList();
    @FXML
    private TableView<Reponse> reply;
    @FXML
    private TableColumn<Reponse, String> id_rec;
    @FXML
    private TableColumn<Reponse, String> repons;
    @FXML
    private Button show;
    
    User user;
    
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainMenuController mmc = new MainMenuController();
        user = mmc.getCurrentUser();
        
        ObservableList<String> list=FXCollections.observableArrayList("technique","service","autre");
        rectype.setItems(list);
        
        idrec.setCellValueFactory(new PropertyValueFactory<>("id"));
        iduser.setCellValueFactory(new PropertyValueFactory<>("idUtilisateur"));
        taffich.setCellValueFactory(new PropertyValueFactory<>("type"));
        saffich.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        daffich.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateaffiche.setCellValueFactory(new PropertyValueFactory<>("date"));
        oblist = rc.afficherReclamation();
        listeReclamation.setItems(oblist);
        
        
    }    

    @FXML
    private void ajouterReclamationAction(ActionEvent event) {
     Reclamation R = new Reclamation(rectype.getSelectionModel().getSelectedItem().toString(),sujet.getText(),description.getText());   
     
     rc.ajouterReclamation(R);
     oblist = rc.afficherReclamation();
        listeReclamation.setItems(oblist);
    }

    @FXML
    private void supprimerReclamationAction(ActionEvent event) {
        Reclamation R=listeReclamation.getSelectionModel().getSelectedItem();
        rc.supprimerReclamation(R);
        oblist = rc.afficherReclamation();
        listeReclamation.setItems(oblist);
        
    }

    @FXML
    private void afficherreponse(ActionEvent event) {
        id_rec.setCellValueFactory(new PropertyValueFactory<>("idRec"));
        repons.setCellValueFactory(new PropertyValueFactory<>("reply"));
        Reclamation R=listeReclamation.getSelectionModel().getSelectedItem();
        reponseliste=rc.afficherReponse(R.getId());
        reply.setItems(reponseliste);
    }
    
}
