/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIcovoiturage;


import fithnitek.controllers.Covoiturage;
import fithnitek.controllers.MainMenuController;
import fithnitek.controllers.ReservationCovoiturageService;
import fithnitek.models.OffreCovoiturage;
import fithnitek.models.ReservationCovoiturage;
import fithnitek.models.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

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
    private TableView<OffreCovoiturage> id_afficheroffreback;
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
     User user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainMenuController mmc = new MainMenuController();
        user = mmc.getCurrentUser();
        
        Covoiturage c = new Covoiturage();
        ev = c.afficherallcovoiturage(user.getId());
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
       // id_afficheroffreback.setItems(data);
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
        ev = c.afficherallcovoiturage(user.getId());
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
//        id_afficheroffreback.setItems(data);
    }
    public void ordonner1(ActionEvent event)
    {id_afficheroffre.getItems().clear();
    Covoiturage c = new Covoiturage();
        ev = c.Ordonneroffre2(user.getId());
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
        ev = c.Ordonneroffre(user.getId());
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
    
    
    Covoiturage c = new Covoiturage();
 
 if(id_datefind.getValue() == null || "".equals(id_nbrfind.getText()) || "".equals(id_destfind.getText()) || "".equals(id_depfind.getText()) ) 
 {
 Alert alert = new Alert(Alert.AlertType.ERROR);
       alert.setTitle("Error Dialog");
       alert.setHeaderText("Please enter all the informations  ");
         alert.showAndWait();
         refresh(); 
 }
 else 
 {
 try 
 {LocalDate test = id_datefind.getValue();
 String dateoffre = test.toString();
     Date actuelle = new Date();
  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  String dat = dateFormat.format(actuelle);
  ZoneId defaultZoneId = ZoneId.systemDefault();
  Date date = Date.from(test.atStartOfDay(defaultZoneId).toInstant());
 int numSms = Integer.parseInt(id_nbrfind.getText());
  if (Integer.valueOf(id_nbrfind.getText()) > 0 && date.after(actuelle))
        {
        ev = c.rechercheoffre(user.getId(),Integer.valueOf(id_nbrfind.getText()),dateoffre,id_destfind.getText(),id_depfind.getText());
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
        else 
        {
         Alert alert = new Alert(Alert.AlertType.ERROR);
       alert.setTitle("Error Dialog");
       alert.setHeaderText("Please enter a positive number and a recent date  ");
         alert.showAndWait();
         refresh(); 
        }
 }
 catch(NumberFormatException e)
         {
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("The number of places must be a number");
            alert.showAndWait();
         refresh();
         }
 }
 
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
       if(e.getNbrplace() >= Integer.valueOf(id_nbrreserv.getText()) && Integer.valueOf(id_nbrreserv.getText()) > 0 )
       {
       float pricet = Integer.valueOf(id_nbrreserv.getText())*e.getPrix();
       int nnbr = e.getNbrplace()-Integer.valueOf(id_nbrreserv.getText());
       c.updateplacesoffrecovoiturage(e.getId(), nnbr);
       ReservationCovoiturage r = new ReservationCovoiturage(user.getId(),e.getId(),Integer.valueOf(id_nbrreserv.getText()),pricet); 
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
       String not = "You have reserved "+id_nbrreserv.getText()+"places";
       notification(not,"src/GUIcovoiturage/addres.wav");
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
     public void redirectionversreservationback(ActionEvent event) throws IOException
    {
     
           Parent root = FXMLLoader.load(getClass().getResource("AffichageReservationBack.fxml"));
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
    public void redirectionveroffreback(ActionEvent event) throws IOException
    {
     
           Parent root = FXMLLoader.load(getClass().getResource("AfficherOffreCovoiturageBack.fxml"));
           Scene scene = new Scene(root);
           Stage newWindow = new Stage();
           newWindow.setTitle("Carpolling ");
           newWindow.setScene(scene);
           newWindow.show();
        final Node source = (Node) event.getSource();
           final Stage stage = (Stage) source.getScene().getWindow();
       stage.close();
    
    }
    public void playmusic(String filepath) 
{
  InputStream in ; 
try 
{
in = new FileInputStream(new File(filepath));
AudioStream audios  = new AudioStream(in); 
AudioPlayer.player.start(audios);
}
catch (IOException e)
{
    System.out.print("error");
}
}
    public void notification (String text,String path){
Image check = new Image("GUIcovoiturage/logosghir.png");
     Notifications notificationBuilder= Notifications.create()
             .title("Carpooling Notification")
             .text(text)
              .graphic(new ImageView(check))
              .hideAfter(Duration.seconds(9)) 
             .position(Pos.BOTTOM_RIGHT)
             .onAction(new  EventHandler<ActionEvent>( ) {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("enti nzelt ");
            }
        });
      // notificationBuilder;
      playmusic(path);
     notificationBuilder.show();
}
    
    
}
