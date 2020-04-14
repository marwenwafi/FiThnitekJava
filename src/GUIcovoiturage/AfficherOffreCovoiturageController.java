/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIcovoiturage;


import fithnitek.controllers.Covoiturage;
import fithnitek.controllers.MainMenuController;
import fithnitek.models.OffreCovoiturage;
import fithnitek.models.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.*; 

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AfficherOffreCovoiturageController implements Initializable {
       @FXML
   private TextField id_destupdate;
        @FXML
   private TextField id_depupdate;
         @FXML
   private TextField id_priceupdate;
          @FXML
   private TextField id_voitureupdate;
          @FXML
   private TextField id_nbrupdate;
       @FXML
   private DatePicker  id_dateupdate;   
      
       
      
@FXML
    private TableView<OffreCovoiturage> id_afficheroffre;
    @FXML
    private TableColumn<OffreCovoiturage, String> id_destination;
    @FXML
    private TableColumn<OffreCovoiturage, String> id_depart;
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
    User user;
    @FXML
    private AnchorPane id_affichecov;
    @FXML
    private Button id_modifier;
    @FXML
    private Button id_suprimer;
    @FXML
    private Button id_addoffre;
    @FXML
    private Separator id_separateur;
    @FXML
    private BorderPane id_carsharingoffer;
    @FXML
    private BorderPane id_carsharingoffers;
    @FXML
    private Button BCarsharingoffers;
    @FXML
    private Button BYourReservation;
    @FXML
    private Button Bbacktothemenu;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        MainMenuController mmc = new MainMenuController();
        user = mmc.getCurrentUser();
        
        // TODO
        Covoiturage c = new Covoiturage();
        ev = c.affichercovoiturageutilisateur(user.getId());
        //System.out.print(ev);
        data.addAll(ev);
        id_destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        id_depart.setCellValueFactory(new PropertyValueFactory<>("depart"));
        id_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        id_nbr.setCellValueFactory(new PropertyValueFactory<>("nbrplace"));
        id_price.setCellValueFactory(new PropertyValueFactory<>("prix"));
        id_car.setCellValueFactory(new PropertyValueFactory<>("voiture"));
        id_afficheroffre.setItems(data);
        
    }
    public void refresh()
    {
    Covoiturage c = new Covoiturage();
        ev = c.affichercovoiturageutilisateur(user.getId());
        //System.out.print(ev);
        data.addAll(ev);
        id_destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        id_depart.setCellValueFactory(new PropertyValueFactory<>("depart"));
        id_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        id_nbr.setCellValueFactory(new PropertyValueFactory<>("nbrplace"));
        id_price.setCellValueFactory(new PropertyValueFactory<>("prix"));
        id_car.setCellValueFactory(new PropertyValueFactory<>("voiture"));
        id_afficheroffre.setItems(data);
        
    }
    @FXML
    public void supprimeroffrecovoiturage() throws Exception {
       OffreCovoiturage e = id_afficheroffre.getSelectionModel().getSelectedItem();
       if (e == null )
       {
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setTitle("Information Dialog");
       alert.setHeaderText("Please select an offer to delete");
         alert.showAndWait();
       }
       else 
       { Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to delete this offer");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK)
        {
        Covoiturage sp = new Covoiturage();
        
        sp.supprimeroffrecovoiturage(e.getId());
       JavaMail test = new JavaMail();
String email = sp.selectmailuser(e.getIdutilisateur());
String text = "You carsharing offer has been deleted : The details of the Offre are :" +" \n Destination "+e.getDestination()+" \n Departure "+e.getDepart()+" \n Date "+e.getDate()+"\n Price "+e.getPrix()+" \n Places "+e.getNbrplace()+"\n Car "+e.getVoiture() ;
       test.SendMail(email,"Carsharing Deleted",text);
     notification("You have Deleted an carpooling offer","src/GUIcovoiturage/deleteoffre.wav");
        //productTable.getItems().clear();
  
        id_afficheroffre.getItems().clear();
        refresh();
       // id_afficheroffre.refresh();
        }
        
        
    
    else 
    {
    
    }
    }
    }
    
    @FXML
    public void details (MouseEvent event)
    {
       
    OffreCovoiturage e = id_afficheroffre.getSelectionModel().getSelectedItem();
    if(e == null)
    {}
    else 
        
    {id_destupdate.setText(e.getDestination());
id_depupdate.setText(e.getDepart());
String s=String.valueOf(e.getPrix());
id_priceupdate.setText(s);
id_voitureupdate.setText(e.getVoiture());
String s2=String.valueOf(e.getNbrplace());

id_nbrupdate.setText(s2);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

LocalDate mm = LocalDate.parse(e.getDate(), formatter);
id_dateupdate.setValue(mm);}



    }
            
    @FXML
    public void modifieroffrecovoiturage(ActionEvent event) throws Exception    
{
OffreCovoiturage e = id_afficheroffre.getSelectionModel().getSelectedItem();  
System.out.println(e);
if(e == null )
{
Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setTitle("Information Dialog");
       alert.setHeaderText("Please select an offer to update");
         alert.showAndWait();
}
else 
{
    if("".equals(id_destupdate.getText()) || "".equals(id_depupdate.getText()) || "".equals(id_nbrupdate.getText()) ||  "".equals(id_priceupdate.getText()) ||  "".equals(id_voitureupdate.getText()) || id_dateupdate.getValue()== null)
       {
       Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("You must put all the information");
       alert.showAndWait();	
}
else 
{ LocalDate test = id_dateupdate.getValue();
  String dateoffre = test.toString();
  Date actuelle = new Date();
  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  String dat = dateFormat.format(actuelle);
  ZoneId defaultZoneId = ZoneId.systemDefault();
  Date date = Date.from(test.atStartOfDay(defaultZoneId).toInstant());
 try 
 {
     int numSms = Integer.parseInt(id_nbrupdate.getText());
     float prix2 = Float.parseFloat(id_priceupdate.getText());
     if(date.before(actuelle) || numSms <0 || numSms > 6 || prix2 < 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please verify the informations   ");
            alert.showAndWait();
        }
       
        else 
        {
            
            //OffreCovoiturage o = new OffreCovoiturage(5,destination.getText(),depart.getText(),"2020-25-25",Integer.valueOf(nbrplace.getText()),Float.valueOf(prix.getText()),voiture.getText());
            Covoiturage sp = new Covoiturage();	
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to update this offer");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK)
        {
             sp.modifieroffrecovoiturage(e.getId(),user.getId(),id_depupdate.getText(),id_destupdate.getText(),dateoffre, Integer.valueOf(id_nbrupdate.getText()), Float.valueOf(id_priceupdate.getText()),id_voitureupdate.getText());
          JavaMail mail = new JavaMail();
String email = sp.selectmailuser(e.getIdutilisateur());
String text = "You carsharing offer has been update : The details of the Offre are : "+"\n Old Destination "+e.getDestination()+" New Destination :  "+id_destupdate.getText()+" \n Old Departure : "+e.getDepart()+"New Departure : "+id_depupdate.getText() +"\n Old Date :  "+e.getDate()+" New Date : "+dateoffre+" \n Old Price :  "+e.getPrix()+"New Price : "+id_priceupdate.getText()+" \n Old Places : "+e.getNbrplace()+" New places : "+id_nbrupdate.getText()+" \n Old Car : "+e.getVoiture() + "New Car : "+id_voitureupdate.getText() ;
       mail.SendMail(email,"Carsharing Updated",text);
      notification("You have updated an offer ","src/GUIcovoiturage/updateoffre.wav");   
       id_afficheroffre.getItems().clear();
        refresh();
        }
           
           
        }
 }
 catch (NumberFormatException m) {
 Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("The price or the number of places must be a number");
            alert.showAndWait();
   
 }
 }
}

}
    @FXML
    public void ajouteroffrecovoiturage(ActionEvent event) throws IOException, Exception {
if("".equals(id_destupdate.getText()) || "".equals(id_depupdate.getText()) || "".equals(id_nbrupdate.getText()) ||  "".equals(id_priceupdate.getText()) ||  "".equals(id_voitureupdate.getText()) || id_dateupdate.getValue()== null)
       {
       Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("You must put all the information");
       alert.showAndWait();	
}
else 
{ LocalDate test = id_dateupdate.getValue();
  String dateoffre = test.toString();
  Date actuelle = new Date();
  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  String dat = dateFormat.format(actuelle);
  ZoneId defaultZoneId = ZoneId.systemDefault();
  Date date = Date.from(test.atStartOfDay(defaultZoneId).toInstant());
 try 
 {
     int numSms = Integer.parseInt(id_nbrupdate.getText());
     float prix2 = Float.parseFloat(id_priceupdate.getText());
     if(date.before(actuelle) || numSms <0 || numSms > 6  || prix2 < 0 || numSms == 0  || prix2 == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please verify the informations");
            alert.showAndWait();
        }
       
        else 
        {
            OffreCovoiturage o1 = new OffreCovoiturage(user.getId(),id_destupdate.getText(),id_depupdate.getText(),dateoffre,Integer.valueOf(id_nbrupdate.getText()),Float.valueOf(id_priceupdate.getText()),id_voitureupdate.getText());
            System.out.print(o1);
           // OffreCovoiturage o = new OffreCovoiturage(5,destination.getText(),depart.getText(),"2020-25-25",Integer.valueOf(nbrplace.getText()),Float.valueOf(prix.getText()),voiture.getText());
            Covoiturage sp = new Covoiturage();	
            
            sp.ajouteroffrecovoiturage(o1);
            int nbrinit = o1.getNumber() ; 
            int jdid = nbrinit+1 ;
            System.out.println(o1.getIdutilisateur());
            sp.updateplacesoffreuser(o1.getIdutilisateur(),jdid);
            
            JavaMail mail = new JavaMail();
String email = sp.selectmailuser(o1.getIdutilisateur());
String text = "You have just added an offer : The details of the Offre are :" +" \n Destination "+id_destupdate.getText()+" \n Departure "+id_depupdate.getText()+" \n Date "+dateoffre+"\n Price "+id_priceupdate.getText()+" \n Places "+id_nbrupdate.getText()+"\n Car "+id_voitureupdate.getText() ;
       mail.SendMail(email,"Carsharing Offer ",text);
       notification("You have added an offer of carpooling","src/GUIcovoiturage/addedoffre.wav");
          
           id_afficheroffre.getItems().clear();
        refresh();
         
        }
 }
 catch (NumberFormatException e) {
 Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("The price or the number of places must be a number");
            alert.showAndWait();
   
 }
 }
 }
    @FXML
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
    @FXML
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
     playmusic(path);
      // notificationBuilder;
     notificationBuilder.show();
}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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