/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewsColis;

import fithnitek.models.*;
import fithnitek.controllers.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ReservationColisController implements Initializable {
    @FXML
    private TableView<Reservation_Colis> afficherReservation;
    @FXML
    private TableColumn<?, ?> TID;
    @FXML
    private TableColumn<?, ?> THauteur;
    @FXML
    private TableColumn<?, ?> TLargeur;
    @FXML
    private TableColumn<?, ?> TPrix;
    @FXML
    private TableColumn<?, ?> TIDU;
    @FXML
    private TableColumn<?, ?> TIDOffre;
    @FXML
    private TableColumn<?, ?> TLongueur;
    @FXML
    private Button SupprimerReservation;
    private TextField idReservation;
    @FXML
    private Button AddReservation;
    @FXML
    private Button GoToOffre;
    @FXML
    private ImageView img;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ServiceReservationColis OC = new ServiceReservationColis();
        ArrayList<Reservation_Colis> OffreArray =  (ArrayList<Reservation_Colis>) OC.afficherReservationColis(10) ; 
        ObservableList<Reservation_Colis> data = FXCollections.observableArrayList(OffreArray);
        afficherReservation.setItems(data);
        TID.setCellValueFactory(new PropertyValueFactory<>("id_ReservationCol"));
        TIDU.setCellValueFactory(new PropertyValueFactory<>("Id"));
        TPrix.setCellValueFactory(new PropertyValueFactory<>("PrixResv"));
        THauteur.setCellValueFactory(new PropertyValueFactory<>("HauteurResv"));
        TLargeur.setCellValueFactory(new PropertyValueFactory<>("LargeurResv"));
        TLongueur.setCellValueFactory(new PropertyValueFactory<>("LongueurResv"));
        TIDOffre.setCellValueFactory(new PropertyValueFactory<>("id_Offre"));
        afficherReservation.setEditable(true);
    }    

    @FXML
    private void SupprimerReservation(ActionEvent event) {
        Reservation_Colis O=afficherReservation.getSelectionModel().getSelectedItem();
       int   id=O.getId_ReservationCol();
        ServiceReservationColis SR=new ServiceReservationColis();
        ServiceColis OC = new ServiceColis();
    //    Offre_Colis O=new Offre_Colis(idO,date.getText(),depart.getText(),arrive.getText(),Integer.parseInt(prix.getText()),Double.parseDouble(hauteur.getText()),Double.parseDouble(largeur.getText()),voiture.getText(),u,Double.parseDouble(longueur.getText()));
                SR.supprimerOffreReservation(O);
                ServiceReservationColis SRC = new ServiceReservationColis();
                 ArrayList<Reservation_Colis> OffreArray =  (ArrayList<Reservation_Colis>) SRC.afficherReservationColis(10) ; 
        ObservableList<Reservation_Colis> data = FXCollections.observableArrayList(OffreArray);
        afficherReservation.setItems(data);
        TID.setCellValueFactory(new PropertyValueFactory<>("id_ReservationCol"));
        TIDU.setCellValueFactory(new PropertyValueFactory<>("Id"));
        TPrix.setCellValueFactory(new PropertyValueFactory<>("PrixResv"));
        THauteur.setCellValueFactory(new PropertyValueFactory<>("HauteurResv"));
        TLargeur.setCellValueFactory(new PropertyValueFactory<>("LargeurResv"));
        TLongueur.setCellValueFactory(new PropertyValueFactory<>("LongueurResv"));
        TIDOffre.setCellValueFactory(new PropertyValueFactory<>("id_Offre"));
        afficherReservation.setEditable(true);

    }

    @FXML
    private void AddReservation(ActionEvent event) throws IOException {
                  Parent home_page_parent =   FXMLLoader.load(getClass().getResource("AfficherAllColis.fxml"));
   Scene home_page_scene = new Scene(home_page_parent);
   Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
   app_stage.hide(); //optional
   app_stage.setScene(home_page_scene);
   app_stage.show();

    }

    @FXML
    private void GoToOffre(ActionEvent event) throws IOException {
                  Parent home_page_parent =   FXMLLoader.load(getClass().getResource("AjouterOffre.fxml"));
                          Scene home_page_scene = new Scene(home_page_parent);
   Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
   app_stage.hide(); //optional
   app_stage.setScene(home_page_scene);
   app_stage.show();

    }



 
}
