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
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class AfficherReservationCovoiturageController implements Initializable {
@FXML
    private TableView<ReservationCovoiturage> id_afficherres;

    @FXML
    private TableColumn<ReservationCovoiturage, Integer> id_numberofplaces;
    @FXML
    private TableColumn<ReservationCovoiturage, Integer> id_pricet;
    @FXML
    private TableColumn<ReservationCovoiturage, String> id_destination;
    @FXML
    private TableColumn<ReservationCovoiturage, String> id_depart;
    @FXML
    private TableColumn<ReservationCovoiturage, String> id_date;
    @FXML
    private TableColumn<ReservationCovoiturage, String> id_username;
    @FXML
    private TableColumn<ReservationCovoiturage, Integer> id_number;
    @FXML
    private ImageView imagelogo ;
    private ObservableList<ReservationCovoiturage> data = FXCollections.observableArrayList();
    List<ReservationCovoiturage> ev = new ArrayList<>();
    List <OffreCovoiturage > oc = new ArrayList<>();
    User user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        MainMenuController mmc = new MainMenuController();
        user = mmc.getCurrentUser();
        
       ReservationCovoiturageService rc = new ReservationCovoiturageService(); 
        ev = rc.afficherreservationcovoiturageutilisateur(user.getId());
        System.out.println(ev);
        data.addAll(ev);
        id_numberofplaces.setCellValueFactory(new PropertyValueFactory<>("numberplacer"));
        id_pricet.setCellValueFactory(new PropertyValueFactory<>("prixt"));
        id_destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        id_depart.setCellValueFactory(new PropertyValueFactory<>("depart"));
        id_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        id_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        id_number.setCellValueFactory(new PropertyValueFactory<>("number"));
        File file = new File("src/GUIcovoiturage/logo.png");
        Image image = new Image(file.toURI().toString());
        imagelogo.setImage(image);
        id_afficherres.setItems(data);
    }
public void supprimerresoffrecovoiturage() throws Exception {
ReservationCovoiturage e = id_afficherres.getSelectionModel().getSelectedItem();
   
        if (e == null)
        {
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Information Dialog");
        alert2.setHeaderText("Please select a reservation");
        alert2.showAndWait();
        }
        else 
        {
            Date actuelle = new Date();
 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 String dat = dateFormat.format(actuelle);
 LocalDate date = LocalDate.parse(e.getDate());
 ZoneId defaultZoneId = ZoneId.systemDefault();
  Date date2 = Date.from(date.atStartOfDay(defaultZoneId).toInstant());

         if(actuelle.after(date2) )  
         {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to delete this Reservation");
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.get() == ButtonType.OK)
        { ReservationCovoiturageService sr = new ReservationCovoiturageService();
          sr.supprimerreservationcovoiturage(e.getIdreservationcov());
           notification("You have deleted an reservation","src/GUIcovoiturage/deleteres.wav");
          id_afficherres.getItems().clear();
          refresh();   
        }
        else {}
        }
        else 
        {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to delete this Reservation");
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.get() == ButtonType.OK)
        {Covoiturage sc = new Covoiturage(); 
         ReservationCovoiturageService sr = new ReservationCovoiturageService();
         sc.updateplacesoffrecovoiturage(e.getIdoffrer(),e.getNumberplacer()+e.getNumberplaceo());
         sr.supprimerreservationcovoiturage(e.getIdreservationcov());
         
         JavaMail test = new JavaMail();
       String email = sc.selectmailuser(e.getIdutilisateur());
       String nom = sr.selectnomuser(e.getIdutilisateurr()); 
       String prenom = sr.selectprenomuser(e.getIdutilisateurr());
       String text = "the user  "+nom+" "+prenom+" Canceled his reservation";
       test.SendMail(email,"Carsharing Reservation",text);
       String email2=sc.selectmailuser(e.getIdutilisateurr());
       String text2 = "You have canceled a reservation " ;
       test.SendMail(email2,"Carsharing Reservation",text2);
       notification("You have deleted an reservation","src/GUIcovoiturage/deleteres.wav");
         id_afficherres.getItems().clear();
         refresh();
        }
         else 
        {
            
        }
        }
        
        }
        }    
    
public void refresh()
    {
    
      ReservationCovoiturageService rc = new ReservationCovoiturageService(); 
        ev = rc.afficherreservationcovoiturageutilisateur(user.getId());
        System.out.println(ev);
        //System.out.print(ev);
        data.addAll(ev);
        id_numberofplaces.setCellValueFactory(new PropertyValueFactory<>("numberplacer"));
        id_pricet.setCellValueFactory(new PropertyValueFactory<>("prixt"));
        id_destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        id_depart.setCellValueFactory(new PropertyValueFactory<>("depart"));
        id_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        id_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        id_number.setCellValueFactory(new PropertyValueFactory<>("number"));
        id_afficherres.setItems(data); 
    }

  public void redirectionversafficherallofre(ActionEvent event) throws IOException
    {
     
           Parent root = FXMLLoader.load(getClass().getResource("AfficherAllOffreCovoiturage.fxml"));
           Scene scene = new Scene(root);
           Stage newWindow = new Stage();
           newWindow.setTitle("Carpolling");
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
