/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewsColis;

import fithnitek.models.*;
import fithnitek.controllers.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AfficherAllColisAdminController implements Initializable {

    @FXML
    private Button ReserveOffre;
    @FXML
    private TableView<Offre_Colis> afficher;
    @FXML
    private TableColumn<?, ?> TDate;
    @FXML
    private TableColumn<?, ?> TDepart;
    @FXML
    private TableColumn<?, ?> TArrive;
    @FXML
    private TableColumn<?, ?> TPrix;
    @FXML
    private TableColumn<?, ?> THauteur;
    @FXML
    private TableColumn<?, ?> TLargeur;
    @FXML
    private TableColumn<?, ?> TLongueur;
    @FXML
    private TableColumn<?, ?> TVoiture;
    private TableColumn<?, ?> TId;
    private TableColumn<?, ?> IdUT;
    @FXML
    private TextField HauteurResv;
    @FXML
    private TextField LargeurResv;
    @FXML
    private TextField LongueurResv;
    @FXML
    private Button Trie;
    @FXML
    private Button ADDOffre;
    @FXML
    private Button MyReservation;
    @FXML
    private Button rechercheDate;
    @FXML
    private DatePicker datein;
    @FXML
    private TextField LargeurR;
    @FXML
    private TextField HauteurR;
    @FXML
    private TextField CarR;
    @FXML
    private TextField LongueurR;
    @FXML
    private TableColumn<?, ?> NameUserDriveT;
    @FXML
    private AnchorPane anchortrie;
    @FXML
    private ImageView imagelogo;
    
    User user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            ServiceColis OC = new ServiceColis();
        ArrayList<Offre_Colis> OffreArray =(ArrayList<Offre_Colis>) OC.afficherAllColis() ; 
        ObservableList<Offre_Colis> data = FXCollections.observableArrayList(OffreArray);
        afficher.setItems(data);
        TDepart.setCellValueFactory(new PropertyValueFactory<>("Lieu_Depart"));
        TDate.setCellValueFactory(new PropertyValueFactory<>("Date_col"));
        TArrive.setCellValueFactory(new PropertyValueFactory<>("Lieu_Arrive"));
        TPrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        THauteur.setCellValueFactory(new PropertyValueFactory<>("hauteurCol"));
        TLargeur.setCellValueFactory(new PropertyValueFactory<>("LargeurCol"));
        TLongueur.setCellValueFactory(new PropertyValueFactory<>("LongueurCol"));
        TVoiture.setCellValueFactory(new PropertyValueFactory<>("Voiture"));
        NameUserDriveT.setCellValueFactory(new PropertyValueFactory<>("username"));
        afficher.setEditable(true);
         File file = new File("src/ViewsColis/fithnitek.png");
        Image image = new Image(file.toURI().toString());
        imagelogo.setImage(image);
    }    

    @FXML
    private void ReserveOffreB(ActionEvent event) {
           if( LongueurResv.getText().isEmpty() || LargeurResv.getText().isEmpty() || HauteurResv.getText().isEmpty() )
        {Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champ vide");

            alert.showAndWait();
        }
            else{
                if( (Double.parseDouble(HauteurResv.getText()) <= 0)  || (Double.parseDouble(LargeurResv.getText()) <= 0)  ||(Double.parseDouble(LongueurResv.getText()) <= 0)   ){
Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez renseinger les champs avant de reserve un Offre");

            alert.showAndWait();

                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Delete File");
      alert.setHeaderText("Are you sure want to Reserve Offre");
      Optional<ButtonType> option = alert.showAndWait();
      if (option.get() == ButtonType.OK) {  
                
           HauteurResv.getText();
    LargeurResv.getText();
     LongueurResv.getText();
    int u=9;
      ServiceReservationColis OC = new ServiceReservationColis();
       Offre_Colis O=afficher.getSelectionModel().getSelectedItem();
       int   id=O.getId_OffreCol();
    
        Reservation_Colis R=new Reservation_Colis(Double.parseDouble(HauteurResv.getText()),Double.parseDouble(LargeurResv.getText()),user.getId(),id,Double.parseDouble(LongueurResv.getText()));
        OC.ajouterOffreReservation(R);
         ServiceColis m = new ServiceColis();
        ArrayList<Offre_Colis> OffreArray =(ArrayList<Offre_Colis>) m.afficherAllColis() ; 
        ObservableList<Offre_Colis> data = FXCollections.observableArrayList(OffreArray);
         afficher.setItems(data);
        TDepart.setCellValueFactory(new PropertyValueFactory<>("Lieu_Depart"));
        TDate.setCellValueFactory(new PropertyValueFactory<>("Date_col"));
        TArrive.setCellValueFactory(new PropertyValueFactory<>("Lieu_Arrive"));
        TPrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        THauteur.setCellValueFactory(new PropertyValueFactory<>("hauteurCol"));
        TLargeur.setCellValueFactory(new PropertyValueFactory<>("LargeurCol"));
        TLongueur.setCellValueFactory(new PropertyValueFactory<>("LongueurCol"));
        TVoiture.setCellValueFactory(new PropertyValueFactory<>("Voiture"));
        NameUserDriveT.setCellValueFactory(new PropertyValueFactory<>("username"));
        afficher.setEditable(true);
    }
        else if (option.get() == ButtonType.CANCEL) {
            ServiceColis m = new ServiceColis();
        ArrayList<Offre_Colis> OffreArray =(ArrayList<Offre_Colis>) m.afficherAllColis() ; 
        ObservableList<Offre_Colis> data = FXCollections.observableArrayList(OffreArray);
         afficher.setItems(data);
        TDepart.setCellValueFactory(new PropertyValueFactory<>("Lieu_Depart"));
        TDate.setCellValueFactory(new PropertyValueFactory<>("Date_col"));
        TArrive.setCellValueFactory(new PropertyValueFactory<>("Lieu_Arrive"));
        TPrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        THauteur.setCellValueFactory(new PropertyValueFactory<>("hauteurCol"));
        TLargeur.setCellValueFactory(new PropertyValueFactory<>("LargeurCol"));
        TLongueur.setCellValueFactory(new PropertyValueFactory<>("LongueurCol"));
        TVoiture.setCellValueFactory(new PropertyValueFactory<>("Voiture"));
        NameUserDriveT.setCellValueFactory(new PropertyValueFactory<>("username"));
        afficher.setEditable(true);
            
        }
                }
    }
    }
    @FXML
    private void TrieB(ActionEvent event) {
          ServiceColis OC = new ServiceColis();
       ArrayList<Offre_Colis> OffreArray =  (ArrayList<Offre_Colis>) OC.TrierOffreColisPrix();
        ObservableList<Offre_Colis> data = FXCollections.observableArrayList(OffreArray);
         afficher.setItems(data);
        TDepart.setCellValueFactory(new PropertyValueFactory<>("Lieu_Depart"));
        TDate.setCellValueFactory(new PropertyValueFactory<>("Date_col"));
        TArrive.setCellValueFactory(new PropertyValueFactory<>("Lieu_Arrive"));
        TPrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        THauteur.setCellValueFactory(new PropertyValueFactory<>("hauteurCol"));
        TLargeur.setCellValueFactory(new PropertyValueFactory<>("LargeurCol"));
        TLongueur.setCellValueFactory(new PropertyValueFactory<>("LongueurCol"));
        TVoiture.setCellValueFactory(new PropertyValueFactory<>("Voiture"));
        NameUserDriveT.setCellValueFactory(new PropertyValueFactory<>("username"));
        afficher.setEditable(true);
    }

    @FXML
    private void GoToOffre(ActionEvent event) throws IOException {
    Parent home_page_parent =   FXMLLoader.load(getClass().getResource("AjouterOffreColisAdmin.fxml"));
                          Scene home_page_scene = new Scene(home_page_parent);
   Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
   app_stage.hide(); //optional
   app_stage.setScene(home_page_scene);
   app_stage.setTitle("My Offre");
   app_stage.show();
    }
    

    @FXML
    private void GoToMyReservation(ActionEvent event) throws IOException {
         Parent home_page_parent =   FXMLLoader.load(getClass().getResource("ReservationColisAdmin.fxml"));
                        Scene home_page_scene = new Scene(home_page_parent);
   Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
   app_stage.hide(); //optional
   app_stage.setScene(home_page_scene);
      app_stage.setTitle("My Reservation");

   app_stage.show();
    }

    @FXML
    private void RechercheDate(ActionEvent event) {
          if( LargeurR.getText().isEmpty() || HauteurR.getText().isEmpty() || CarR.getText().isEmpty() || LongueurR.getText().isEmpty() || datein.getValue()==null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champ vide");

            alert.showAndWait();
        }
          else
          {
            if( (Double.parseDouble(LargeurR.getText()) <= 0)  || (Double.parseDouble( HauteurR.getText()) <= 0)  ||(Double.parseDouble(LongueurR.getText()) <= 0)      ){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez renseinger les champs avant d'ajouter un Offre");
            alert.showAndWait();
        }
            else
            {
             
    LargeurR.getText();
    HauteurR.getText();
    CarR.getText();
    LongueurR.getText();
         String dateString;
        java.sql.Date gettedDatePickerDate;
        gettedDatePickerDate = java.sql.Date.valueOf(datein.getValue());
        SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd");
	dateString = sdfr.format( gettedDatePickerDate );
       
         ServiceColis OC = new ServiceColis();
         //      int id=9; 
          System.out.println(dateString);
             ArrayList<Offre_Colis> OffreArray =  (ArrayList<Offre_Colis>) OC.RechercheOffreColisAdmin(Double.parseDouble(HauteurR.getText()),Double.parseDouble(LargeurR.getText()),Double.parseDouble(LongueurR.getText()),CarR.getText(),dateString);
             ObservableList<Offre_Colis> data = FXCollections.observableArrayList(OffreArray);
         afficher.setItems(data);
        TDepart.setCellValueFactory(new PropertyValueFactory<>("Lieu_Depart"));
        TDate.setCellValueFactory(new PropertyValueFactory<>("Date_col"));
        TArrive.setCellValueFactory(new PropertyValueFactory<>("Lieu_Arrive"));
        TPrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        THauteur.setCellValueFactory(new PropertyValueFactory<>("hauteurCol"));
        TLargeur.setCellValueFactory(new PropertyValueFactory<>("LargeurCol"));
        TLongueur.setCellValueFactory(new PropertyValueFactory<>("LongueurCol"));
        TVoiture.setCellValueFactory(new PropertyValueFactory<>("Voiture"));
        NameUserDriveT.setCellValueFactory(new PropertyValueFactory<>("username"));
        afficher.setEditable(true);
    }
          }
    }
   
  
}

    

    

