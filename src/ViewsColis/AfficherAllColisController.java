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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AfficherAllColisController implements Initializable {

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
    private TextField DepartR;
    @FXML
    private TextField ArriveR;
    @FXML
    private Button Recherche;
    @FXML
    private TextField HauteurResv;
    @FXML
    private TextField LargeurResv;
    @FXML
    private TextField LongueurResv;
    @FXML
    private Button ReserveOffre;
    @FXML
    private Button Trie;
    private TableColumn<?, ?> IdUT;
    @FXML
    private Button ADDOffre;
    @FXML
    private Button MyReservation;
    @FXML
    private DatePicker dateR;
    @FXML
    private Button Triprix;
    @FXML
    private TableColumn<?, ?> TUserName;
    @FXML
    private ImageView imagelogo ;
    
    User user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        MainMenuController mmc = new MainMenuController();
        user = mmc.getCurrentUser();
           ServiceColis OC = new ServiceColis();
           
        ArrayList<Offre_Colis> OffreArray =(ArrayList<Offre_Colis>) OC.afficherOffremshColis(user.getId()); 
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
        TUserName.setCellValueFactory(new PropertyValueFactory<>("Username"));
//        TId.setCellValueFactory(new PropertyValueFactory<>("id_OffreCol"));
   //     IdUT.setCellValueFactory(new PropertyValueFactory<>("idU"));
        afficher.setEditable(true);
        File file = new File("src/ViewsColis/fithnitek.png");
        Image image = new Image(file.toURI().toString());
        imagelogo.setImage(image);
    }    

    @FXML
    private void RechercheDA(ActionEvent event) {

          if(DepartR.getText().isEmpty() || ArriveR.getText().isEmpty() || dateR.getValue()==null ){
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("verifier votre donnes");

            alert.showAndWait();
        }
        else
         {
        DepartR.getText();
        ArriveR.getText();
        String dateString;
        java.sql.Date gettedDatePickerDate;
        gettedDatePickerDate = java.sql.Date.valueOf(dateR.getValue());
        SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd");
	dateString = sdfr.format( gettedDatePickerDate );
 ServiceColis OC = new ServiceColis();
      //  Offre_Colis O=new Offre_Colis(date.getText(),depart.getText(),arrive.getText(),Integer.parseInt(prix.getText()),Double.parseDouble(hauteur.getText()),Double.parseDouble(largeur.getText()),voiture.getText(),u,Double.parseDouble(longueur.getText()));
      ArrayList<Offre_Colis> OffreArray =  (ArrayList<Offre_Colis>) OC.RechercheOffreColisDepartArrive(DepartR.getText(),  ArriveR.getText(),user.getId(),dateString);
       
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
        TUserName.setCellValueFactory(new PropertyValueFactory<>("Username"));
        afficher.setEditable(true);  
      
         }
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
      ServiceReservationColis OC = new ServiceReservationColis();
       Offre_Colis O=afficher.getSelectionModel().getSelectedItem();
       int   id=O.getId_OffreCol();
        Reservation_Colis R=new Reservation_Colis(Double.parseDouble(HauteurResv.getText()),Double.parseDouble(LargeurResv.getText()),user.getId(),id,Double.parseDouble(LongueurResv.getText()));
        OC.ajouterOffreReservation(R);
        afficher();
                }
       else if (option.get() == ButtonType.CANCEL) {
        afficher();

        afficher.setEditable(true);
       }
                }
    }}
    

    @FXML
    private void TrieB(ActionEvent event) {
        ServiceColis OC = new ServiceColis();
      //  Offre_Colis O=new Offre_Colis(date.getText(),depart.getText(),arrive.getText(),Integer.parseInt(prix.getText()),Double.parseDouble(hauteur.getText()),Double.parseDouble(largeur.getText()),voiture.getText(),u,Double.parseDouble(longueur.getText()));
       ArrayList<Offre_Colis> OffreArray =  (ArrayList<Offre_Colis>) OC.TrierOffrewhereColis(user.getId());
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
              TUserName.setCellValueFactory(new PropertyValueFactory<>("Username"));


        afficher.setEditable(true);  
    }

    @FXML
    private void GoToOffre(ActionEvent event) throws IOException {
   Parent home_page_parent =   FXMLLoader.load(getClass().getResource("AjouterOffre.fxml"));
   Scene home_page_scene = new Scene(home_page_parent);
   Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
   app_stage.hide(); //optional
   app_stage.setScene(home_page_scene);
   app_stage.setTitle("My Offre");
   app_stage.show();
    }

    @FXML
    private void GoToMyReservation(ActionEvent event) throws IOException {
   Parent home_page_parent =   FXMLLoader.load(getClass().getResource("ReservationColis.fxml"));
   Scene home_page_scene = new Scene(home_page_parent);
   Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
   app_stage.hide(); //optional
   app_stage.setScene(home_page_scene);
      app_stage.setTitle("My Reservation");

   app_stage.show();
    }

    @FXML
    private void triPrix(ActionEvent event) {
           ServiceColis OC = new ServiceColis();
       ArrayList<Offre_Colis> OffreArray =  (ArrayList<Offre_Colis>) OC.TrierOffreColisPrixwhere(user.getId());
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
       // TId.setCellValueFactory(new PropertyValueFactory<>("id_OffreCol"));
      //  IdUT.setCellValueFactory(new PropertyValueFactory<>("idU"));
        TUserName.setCellValueFactory(new PropertyValueFactory<>("Username"));

        afficher.setEditable(true);  
    }
 void afficher()
 {
          ServiceColis OC = new ServiceColis();
           
        ArrayList<Offre_Colis> OffreArray =(ArrayList<Offre_Colis>) OC.afficherOffremshColis(user.getId()); 
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
        TUserName.setCellValueFactory(new PropertyValueFactory<>("Username"));
//        TId.setCellValueFactory(new PropertyValueFactory<>("id_OffreCol"));
   //     IdUT.setCellValueFactory(new PropertyValueFactory<>("idU"));
        afficher.setEditable(true);
 }
 
 @FXML
    private void BackMainMenu(MouseEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fithnitek/views/mainMenu.fxml"));
        Parent next = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(next);
        stage.setScene(scene);
        stage.show();
    }
}
