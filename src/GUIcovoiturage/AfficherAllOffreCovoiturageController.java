/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIcovoiturage;

import fithnitek.models.OffreCovoiturage;
import fithnitek.models.ReservationCovoiturage;
import fithnitek.controllers.Covoiturage;
import fithnitek.controllers.ReservationCovoiturageService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AfficherAllOffreCovoiturageController implements Initializable {
    @FXML
   private TextField id_destfind;
        @FXML
   private TextField id_depfind;
         @FXML
   private TextField id_nbrfind;
         @FXML
   private DatePicker  id_datefind;
         @FXML
   private TextField id_nbrreserv ;
         
         
    @FXML
    private TableView<OffreCovoiturage> id_afficheroffre;
    @FXML
    private TableColumn<OffreCovoiturage, String> id_destination;
    @FXML
    private TableColumn<OffreCovoiturage, String> id_depart;
     @FXML
    private TableColumn<OffreCovoiturage, String> id_drivername;
     
     @FXML
    private TableColumn<OffreCovoiturage, String> id_date;
      @FXML
    private TableColumn<OffreCovoiturage, Number> id_nbr;
       @FXML
    private TableColumn<OffreCovoiturage,Float> id_price;
        @FXML
    private TableColumn<OffreCovoiturage, String> id_car;
private ObservableList<OffreCovoiturage> data = FXCollections.observableArrayList();
    List<OffreCovoiturage> ev = new ArrayList<>();
    @FXML
    private ImageView imagelogo ;
     @FXML
    private ImageView imagelogo2 ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Covoiturage c = new Covoiturage();
        ev = c.afficherallcovoiturage(5);
        System.out.println(ev);
        //System.out.print(ev);
        data.addAll(ev);
        id_destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        id_depart.setCellValueFactory(new PropertyValueFactory<>("depart"));
        id_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        id_nbr.setCellValueFactory(new PropertyValueFactory<>("nbrplace"));
        id_price.setCellValueFactory(new PropertyValueFactory<>("prix"));
        id_car.setCellValueFactory(new PropertyValueFactory<>("voiture"));
        id_drivername.setCellValueFactory(new PropertyValueFactory<>("username"));
        id_afficheroffre.setItems(data);
        File file = new File("src/GUIcovoiturage/logo.png");
        Image image = new Image(file.toURI().toString());
        imagelogo.setImage(image);
        File file2 = new File("src/GUIcovoiturage/icon_peer2peer_440x440.png");
        Image image2 = new Image(file2.toURI().toString());
        imagelogo2.setImage(image2);
    }
    public void refresh ()
    {id_afficheroffre.getItems().clear();
    Covoiturage c = new Covoiturage();
        ev = c.afficherallcovoiturage(5);
        System.out.println(ev);
        //System.out.print(ev);
        data.addAll(ev);
        id_destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        id_depart.setCellValueFactory(new PropertyValueFactory<>("depart"));
        id_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        id_nbr.setCellValueFactory(new PropertyValueFactory<>("nbrplace"));
        id_price.setCellValueFactory(new PropertyValueFactory<>("prix"));
        id_car.setCellValueFactory(new PropertyValueFactory<>("voiture"));
        id_drivername.setCellValueFactory(new PropertyValueFactory<>("username"));
        id_afficheroffre.setItems(data);
    }
    public void ordonner1(ActionEvent event)
    {id_afficheroffre.getItems().clear();
    Covoiturage c = new Covoiturage();
        ev = c.Ordonneroffre2(5);
        System.out.print(ev);
        data.addAll(ev);
        id_destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        id_depart.setCellValueFactory(new PropertyValueFactory<>("depart"));
        id_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        id_nbr.setCellValueFactory(new PropertyValueFactory<>("nbrplace"));
        id_price.setCellValueFactory(new PropertyValueFactory<>("prix"));
        id_car.setCellValueFactory(new PropertyValueFactory<>("voiture"));
          id_drivername.setCellValueFactory(new PropertyValueFactory<>("username"));
        id_afficheroffre.setItems(data);
        
    }
    public void ordonner2(ActionEvent event)
    {id_afficheroffre.getItems().clear();
    Covoiturage c = new Covoiturage();
        ev = c.Ordonneroffre(5);
        System.out.print(ev);
        data.addAll(ev);
        id_destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        id_depart.setCellValueFactory(new PropertyValueFactory<>("depart"));
        id_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        id_nbr.setCellValueFactory(new PropertyValueFactory<>("nbrplace"));
        id_price.setCellValueFactory(new PropertyValueFactory<>("prix"));
        id_car.setCellValueFactory(new PropertyValueFactory<>("voiture"));
          id_drivername.setCellValueFactory(new PropertyValueFactory<>("username"));
        id_afficheroffre.setItems(data);
        
    }
    public void rechercheoffrecovoiturage(ActionEvent event)
    {id_afficheroffre.getItems().clear();
    LocalDate test = id_datefind.getValue();
    String dateoffre = test.toString();
    Covoiturage c = new Covoiturage();
  // ev = c.rechercheoffre(4,2,"2025-25-25","c","t");

        ev = c.rechercheoffre(5,Integer.valueOf(id_nbrfind.getText()),dateoffre,id_destfind.getText(),id_depfind.getText());
        System.out.print(ev);
        data.addAll(ev);
        id_destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        id_depart.setCellValueFactory(new PropertyValueFactory<>("depart"));
        id_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        id_nbr.setCellValueFactory(new PropertyValueFactory<>("nbrplace"));
        id_price.setCellValueFactory(new PropertyValueFactory<>("prix"));
        id_car.setCellValueFactory(new PropertyValueFactory<>("voiture"));
          id_drivername.setCellValueFactory(new PropertyValueFactory<>("username"));
        id_afficheroffre.setItems(data);
        
    }
    public void ajoutreservationcovoiturage(ActionEvent event) throws Exception
    {
       OffreCovoiturage e = id_afficheroffre.getSelectionModel().getSelectedItem(); 
               if (e == null )
               {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setTitle("Information Dialog");
       alert.setHeaderText("Please select an offer ");
         alert.showAndWait();
               }
               else 
               {
                   ReservationCovoiturageService cr = new ReservationCovoiturageService() ;
       Covoiturage c = new Covoiturage(); 
       if(e.getNbrplace() >= Integer.valueOf(id_nbrreserv.getText()))
       {
       float pricet = Integer.valueOf(id_nbrreserv.getText())*e.getPrix();
       int nnbr = e.getNbrplace()-Integer.valueOf(id_nbrreserv.getText());
       c.updateplacesoffrecovoiturage(e.getId(), nnbr);
       ReservationCovoiturage r = new ReservationCovoiturage(5,e.getId(),Integer.valueOf(id_nbrreserv.getText()),pricet); 
       cr.ajouterrerservationocovoiturage(r);
       JavaMail test = new JavaMail();
       String email = c.selectmailuser(e.getIdutilisateur());
       String nom = cr.selectnomuser(r.getIdutilisateurr()); 
       String prenom = cr.selectprenomuser(r.getIdutilisateurr());
       String text = "the user "+nom+prenom+"has reserved : "+id_nbrreserv.getText()+" places "+"\n the offer is  : "
              +e.getDestination()+"\n Departure : "+e.getDepart()+"\n Date : "+e.getDate();
       test.SendMail(email,"Carsharing Reservation",text);
       String email2=c.selectmailuser(r.getIdutilisateurr());
       String text2 = "You Have just reserved : "+id_nbrreserv.getText()+" places for this offer"+"\n Destination : "+e.getDestination()+"\n Departure : "+e.getDepart()+"\n Date : "+e.getDate()+" \n Price : "+pricet+"\n Drive name : "+e.getUsername()+"\n Driver Number : "+e.getNumber() ;
       test.SendMail(email2,"Carsharing Reservation",text2);
       refresh();
       }
       else 
       {
        Alert alert = new Alert(Alert.AlertType.ERROR);
       alert.setTitle("Error Dialog");
       alert.setHeaderText("Please enter a number < Number of places Available ");
         alert.showAndWait();
       }
       
       }
       
    
    }
    public void redirectionversreservation(ActionEvent event) throws IOException
    {
     
           Parent root = FXMLLoader.load(getClass().getResource("AfficherReservationCovoiturage.fxml"));
           Scene scene = new Scene(root);
           Stage newWindow = new Stage();
           newWindow.setTitle("Carpolling ");
           newWindow.setScene(scene);
           newWindow.show();
        final Node source = (Node) event.getSource();
           final Stage stage = (Stage) source.getScene().getWindow();
       stage.close();
    
    }
    public void redirectionveroffre(ActionEvent event) throws IOException
    {
     
           Parent root = FXMLLoader.load(getClass().getResource("AfficherOffreCovoiturage.fxml"));
           Scene scene = new Scene(root);
           Stage newWindow = new Stage();
           newWindow.setTitle("Carpolling ");
           newWindow.setScene(scene);
           newWindow.show();
        final Node source = (Node) event.getSource();
           final Stage stage = (Stage) source.getScene().getWindow();
       stage.close();
    
    }
    
    
}
