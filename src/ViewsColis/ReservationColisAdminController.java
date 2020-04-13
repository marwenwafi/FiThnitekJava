/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewsColis;

import fithnitek.models.*;
import fithnitek.controllers.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ReservationColisAdminController implements Initializable {

    @FXML
    private Button SupprimerReservation;
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
    private TableColumn<?, ?> TIDU;
    private TableColumn<?, ?> TIDOffre;
    @FXML
    private TableColumn<?, ?> TLongueur;
    @FXML
    private Button AddReservation;
    @FXML
    private Button GoToOffre;
    @FXML
    private TableColumn<?, ?> TDeparture;
    @FXML
    private TableColumn<?, ?> TDestination;
    @FXML
    private TableColumn<?, ?> TUserName;
    @FXML
    private TableColumn<?, ?> TuserNumber;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceReservationColis OC = new ServiceReservationColis();
        ArrayList<Reservation_Colis> OffreArray =  (ArrayList<Reservation_Colis>) OC.afficherReservationColisAll() ; 
        ObservableList<Reservation_Colis> data = FXCollections.observableArrayList(OffreArray);
        afficherReservation.setItems(data);
        TID.setCellValueFactory(new PropertyValueFactory<>("id_ReservationCol"));
        TDeparture.setCellValueFactory(new PropertyValueFactory<>("Arrive"));
        TPrix.setCellValueFactory(new PropertyValueFactory<>("PrixResv"));
        THauteur.setCellValueFactory(new PropertyValueFactory<>("HauteurResv"));
        TLargeur.setCellValueFactory(new PropertyValueFactory<>("LargeurResv"));
        TLongueur.setCellValueFactory(new PropertyValueFactory<>("LongueurResv"));
        TDestination.setCellValueFactory(new PropertyValueFactory<>("Depart"));
        TUserName.setCellValueFactory(new PropertyValueFactory<>("Username"));
        TuserNumber.setCellValueFactory(new PropertyValueFactory<>("Number"));
        
        afficherReservation.setEditable(true);
    }    

    @FXML
    private void SupprimerReservation(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Delete File");
      alert.setHeaderText("Are you sure want to Delete Reservation");
      Optional<ButtonType> option = alert.showAndWait();
       if (option.get() == ButtonType.OK) {
          Reservation_Colis O=afficherReservation.getSelectionModel().getSelectedItem();
          ServiceReservationColis SR=new ServiceReservationColis();
          SR.supprimerOffreReservation(O);
         ServiceReservationColis OC = new ServiceReservationColis();
        ArrayList<Reservation_Colis> OffreArray =  (ArrayList<Reservation_Colis>) OC.afficherReservationColisAll() ; 
        ObservableList<Reservation_Colis> data = FXCollections.observableArrayList(OffreArray);
     afficherReservation.setItems(data);
        TID.setCellValueFactory(new PropertyValueFactory<>("id_ReservationCol"));
        TDeparture.setCellValueFactory(new PropertyValueFactory<>("Arrive"));
        TPrix.setCellValueFactory(new PropertyValueFactory<>("PrixResv"));
        THauteur.setCellValueFactory(new PropertyValueFactory<>("HauteurResv"));
        TLargeur.setCellValueFactory(new PropertyValueFactory<>("LargeurResv"));
        TLongueur.setCellValueFactory(new PropertyValueFactory<>("LongueurResv"));
        TDestination.setCellValueFactory(new PropertyValueFactory<>("Depart"));
        TUserName.setCellValueFactory(new PropertyValueFactory<>("Username"));

        
        afficherReservation.setEditable(true);
       }
        else if (option.get() == ButtonType.CANCEL) {
                 ServiceReservationColis OC = new ServiceReservationColis();
        ArrayList<Reservation_Colis> OffreArray =  (ArrayList<Reservation_Colis>) OC.afficherReservationColisAll() ; 
        ObservableList<Reservation_Colis> data = FXCollections.observableArrayList(OffreArray);
       afficherReservation.setItems(data);
        TID.setCellValueFactory(new PropertyValueFactory<>("id_ReservationCol"));
        TDeparture.setCellValueFactory(new PropertyValueFactory<>("Arrive"));
        TPrix.setCellValueFactory(new PropertyValueFactory<>("PrixResv"));
        THauteur.setCellValueFactory(new PropertyValueFactory<>("HauteurResv"));
        TLargeur.setCellValueFactory(new PropertyValueFactory<>("LargeurResv"));
        TLongueur.setCellValueFactory(new PropertyValueFactory<>("LongueurResv"));
        TDestination.setCellValueFactory(new PropertyValueFactory<>("Depart"));
        TUserName.setCellValueFactory(new PropertyValueFactory<>("Username"));

        
        afficherReservation.setEditable(true);
        }

    }

    @FXML
    private void AddReservation(ActionEvent event) throws IOException {
         Parent home_page_parent =   FXMLLoader.load(getClass().getResource("AfficherAllColisAdmin.fxml"));
   Scene home_page_scene = new Scene(home_page_parent);
   Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
   app_stage.hide(); //optional
   app_stage.setScene(home_page_scene);
   app_stage.show();
    }

    @FXML
    private void GoToOffre(ActionEvent event) throws IOException {
              Parent home_page_parent =   FXMLLoader.load(getClass().getResource("AjouterOffreColisAdmin.fxml"));
                          Scene home_page_scene = new Scene(home_page_parent);
   Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
   app_stage.hide(); //optional
   app_stage.setScene(home_page_scene);
   app_stage.show();
    }
    
}
