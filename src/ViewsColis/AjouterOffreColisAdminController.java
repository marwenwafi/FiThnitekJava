/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewsColis;

import fithnitek.models.*;
import fithnitek.controllers.*;
import fithnitek.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterOffreColisAdminController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private TextField arrive;
    @FXML
    private TextField prix;
    @FXML
    private TextField hauteur;
    @FXML
    private TextField largeur;
    @FXML
    private TextField longueur;
    @FXML
    private TextField voiture;
    @FXML
    private TextField depart;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
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
    @FXML
    private DatePicker Datep;
    private TableColumn<?, ?> Tidu;
    @FXML
    private ComboBox<String> ComboMail;
    @FXML
    private TableColumn<?, ?> TDrive;
    @FXML
    private TableColumn<?, ?> TEmail;
    @FXML
    private Button gocolisoffre;
    @FXML
    private Button Reservation2;
    @FXML
    private Button backtomenuback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ServiceColis OC = new ServiceColis();
        ArrayList<Offre_Colis> OffreArray =  (ArrayList<Offre_Colis>) OC.afficherAllColis() ; 
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
    
        TDrive.setCellValueFactory(new PropertyValueFactory<>("username"));
        TEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        afficher.setEditable(true);
          Connection cnx = DataSource.getInstance().getCnx(); 
        try {
            String requete = "SELECT email FROM fos_user   ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                           
                ComboMail.getItems().addAll(rs.getString("email"));
            }
                               
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }    

    @FXML
    private void clickAjouter(ActionEvent event) throws SQLException {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
LocalDate localDate = Datep.getValue();
Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
Date dateP = Date.from(instant);
   
    if ( arrive.getText().isEmpty() || depart.getText().isEmpty() || hauteur.getText().isEmpty() || longueur.getText().isEmpty() || largeur.getText().isEmpty() || prix.getText().isEmpty()|| voiture.getText().isEmpty() ||  Datep.getValue()==null)
    {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("champ vide");
            alert.showAndWait();
    }
    else
    {
        if( (Double.parseDouble(hauteur.getText()) <= 0)  || (Double.parseDouble(largeur.getText()) <= 0)  ||(Double.parseDouble(longueur.getText()) <= 0)  ||(Integer.parseInt(prix.getText()) <= 0)  || dateP.before(date)   ){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez renseinger les champs avant d'ajouter un Offre");
            alert.showAndWait();
        }
        else
        {
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajouter Colis");
        alert.setContentText("ADD Colis Done");
        alert.showAndWait();
        arrive.getText();
        depart.getText();
        hauteur.getText();
        longueur.getText();
        largeur.getText();
        prix.getText();
        voiture.getText();
        String CM=ComboMail.getSelectionModel().getSelectedItem();
        
        int u=getUser(CM);
        String dateString;
java.sql.Date gettedDatePickerDate;
        gettedDatePickerDate = java.sql.Date.valueOf(Datep.getValue());
        SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd");
	dateString = sdfr.format( gettedDatePickerDate );
        ServiceColis OC = new ServiceColis();
        Offre_Colis O=new Offre_Colis(dateString,depart.getText(),arrive.getText(),Integer.parseInt(prix.getText()),Double.parseDouble(hauteur.getText()),Double.parseDouble(largeur.getText()),voiture.getText(),u,Double.parseDouble(longueur.getText()));
        OC.ajouterOffreColis(O);
         // public Offre_Colis(String Date_col, String Lieu_Depart, String Lieu_Arrive, int Prix, double HauteurCol, double LargeurCol, String Voiture, int idU, double LongueurCol) {
       ArrayList<Offre_Colis> OffreArray =  (ArrayList<Offre_Colis>) OC.afficherAllColis(); 
        ObservableList<Offre_Colis> data = FXCollections.observableArrayList(OffreArray);
        afficher.setItems(data);
        afficher.setItems(data);
        TDepart.setCellValueFactory(new PropertyValueFactory<>("Lieu_Depart"));
        TDate.setCellValueFactory(new PropertyValueFactory<>("Date_col"));
        TArrive.setCellValueFactory(new PropertyValueFactory<>("Lieu_Arrive"));
        TPrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        THauteur.setCellValueFactory(new PropertyValueFactory<>("hauteurCol"));
        TLargeur.setCellValueFactory(new PropertyValueFactory<>("LargeurCol"));
        TLongueur.setCellValueFactory(new PropertyValueFactory<>("LongueurCol"));
        TVoiture.setCellValueFactory(new PropertyValueFactory<>("Voiture"));
    
        TDrive.setCellValueFactory(new PropertyValueFactory<>("username"));
        TEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        afficher.setEditable(true);
        }
    }
    }

    @FXML
    private void modifierOffre(ActionEvent event) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
LocalDate localDate = Datep.getValue();
Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
Date dateP = Date.from(instant);
    if ( arrive.getText().isEmpty() || depart.getText().isEmpty() || hauteur.getText().isEmpty() || longueur.getText().isEmpty() || largeur.getText().isEmpty() || prix.getText().isEmpty()|| voiture.getText().isEmpty())
    {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez renseinger les champs avant modifier un Offre");

            alert.showAndWait();
        }
        else
         {
                 if( (Double.parseDouble(hauteur.getText()) <= 0)  || (Double.parseDouble(largeur.getText()) <= 0)  ||(Double.parseDouble(longueur.getText()) <= 0)  ||(Integer.parseInt(prix.getText()) <= 0)  || dateP.before(date)   ){
                    
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez renseinger les champs avant modifier un Offre");

            alert.showAndWait();
        }
        else 
        {
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Delete File");
      alert.setHeaderText("Are you sure want to Update Offre");
      Optional<ButtonType> option = alert.showAndWait();
 
    
        if (option.get() == ButtonType.OK) {
        arrive.getText();
        depart.getText();
        hauteur.getText();
        longueur.getText();
        largeur.getText();
        String dateString;
        java.sql.Date gettedDatePickerDate;
        gettedDatePickerDate = java.sql.Date.valueOf(Datep.getValue());
        SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd");
	dateString = sdfr.format( gettedDatePickerDate );
        prix.getText();
        voiture.getText();
       ServiceColis OC = new ServiceColis();
       
       Offre_Colis O=afficher.getSelectionModel().getSelectedItem();
       int   id=O.getId_OffreCol();
       int u=O.getIdU();

       Offre_Colis M=new Offre_Colis(id,dateString,depart.getText(),arrive.getText(),Integer.parseInt(prix.getText()),Double.parseDouble(hauteur.getText()),Double.parseDouble(largeur.getText()),voiture.getText(),u,Double.parseDouble(longueur.getText()));
       OC.modifierOffreColis(M);
       //    ServiceColis OC = new ServiceColis();
        ArrayList<Offre_Colis> OffreArray =  (ArrayList<Offre_Colis>) OC.afficherAllColis() ; 
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
    
        TDrive.setCellValueFactory(new PropertyValueFactory<>("username"));
        TEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        afficher.setEditable(true);
         }
        else if (option.get() == ButtonType.CANCEL) {
          ServiceColis OC = new ServiceColis();
       int u=10;
       Offre_Colis O=afficher.getSelectionModel().getSelectedItem();
       int   id=O.getId_OffreCol();
        ArrayList<Offre_Colis> OffreArray =  (ArrayList<Offre_Colis>) OC.afficherAllColis() ; 
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
    
        TDrive.setCellValueFactory(new PropertyValueFactory<>("username"));
        TEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        afficher.setEditable(true);
      } 
        
    }
         }}
    
    @FXML
    private void SupprimerOffre(ActionEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Delete File");
      alert.setHeaderText("Are you sure want to Delete Offre");
      Optional<ButtonType> option = alert.showAndWait();
       if (option.get() == ButtonType.OK) {
           Offre_Colis O=afficher.getSelectionModel().getSelectedItem();
        ServiceColis OC = new ServiceColis();
       
       OC.supprimerOffreColis(O);
       ArrayList<Offre_Colis> OffreArray =  (ArrayList<Offre_Colis>) OC.afficherAllColis() ; 
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
    
        TDrive.setCellValueFactory(new PropertyValueFactory<>("username"));
        TEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        afficher.setEditable(true);
       }
       else if (option.get() == ButtonType.CANCEL) {
           ServiceColis OC = new ServiceColis();
           ArrayList<Offre_Colis> OffreArray =  (ArrayList<Offre_Colis>) OC.afficherAllColis() ; 
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
    
        TDrive.setCellValueFactory(new PropertyValueFactory<>("username"));
        TEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        afficher.setEditable(true);
       }
    }

    @FXML
    private void GoToReservation(ActionEvent event) throws IOException {
          Parent home_page_parent =   FXMLLoader.load(getClass().getResource("ReservationColisAdmin.fxml"));
                        Scene home_page_scene = new Scene(home_page_parent);
   Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
   app_stage.hide(); //optional
   app_stage.setScene(home_page_scene);
   app_stage.show();
    }
    @FXML
    public void MAHA()
 {
     Offre_Colis O=afficher.getSelectionModel().getSelectedItem();
     arrive.setText(O.getLieu_Arrive());
     depart.setText(O.getLieu_Depart());
     hauteur.setText(String.valueOf(O.getHauteurCol()));
     longueur.setText(String.valueOf(O.getLongueurCol()));
     largeur.setText(String.valueOf(O.getLargeurCol()));
     prix.setText(String.valueOf(O.getPrix())); 
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
LocalDate mm = LocalDate.parse(O.getDate_col(), formatter);
Datep.setValue(mm);
voiture.setText(O.getVoiture());
 }
    public  Integer  getUser(String email) throws SQLException
    {
        Integer x=0;
       
         Connection cnx = DataSource.getInstance().getCnx();
          String req= "Select id From fos_user  where email='" +email+"' ";
          PreparedStatement pst = cnx.prepareStatement(req);
          ResultSet rs;
          rs = pst.executeQuery();
          
          if(rs.next())
          {
              x = rs.getInt(1);
             
    }
          return (x);   
          
}
    
    @FXML
    private void backendMenu(MouseEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fithnitek/views/backend.fxml"));
        Parent next = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gocolisoffre(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewsColis/AfficherAllColisAdmin.fxml"));
        Parent next = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }

}
