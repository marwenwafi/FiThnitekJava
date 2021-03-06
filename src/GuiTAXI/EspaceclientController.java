/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiTAXI;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;

//import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import static com.itextpdf.text.pdf.PdfName.F;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import static com.sun.org.apache.regexp.internal.RETest.test;
import fithnitek.controllers.MainMenuController;
import fithnitek.models.DemandeTaxi;
import fithnitek.controllers.ServiceDemandeTaxi;
import fithnitek.models.User;
import fithnitek.utils.DataSource;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.management.Notification;
import org.controlsfx.control.Notifications;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;







/**
 * FXML Controller class
 *
 * @author Rawis
 */
public class EspaceclientController implements Initializable {
    @FXML
    private TableView<DemandeTaxi> demandeTableView;
    @FXML
    private TableColumn<DemandeTaxi, String> depart;
    @FXML
    private TableColumn<DemandeTaxi, String> destination;
    @FXML
    private TableColumn<DemandeTaxi, String> periode;
    @FXML
    private TableColumn<DemandeTaxi, String> etat;
    @FXML
    private TableColumn<DemandeTaxi, String> dated;
    @FXML
    private JFXComboBox<String> regiona;
    @FXML
    private TableColumn<DemandeTaxi, String> regiontab;
    @FXML
    private JFXTextField destinationa;
    @FXML
    private TableColumn<DemandeTaxi, String> price;
    @FXML
    private JFXDatePicker datea;
    @FXML
    private JFXTimePicker periodea;
    @FXML
    private JFXTextField departa;
     @FXML
    private ImageView taswira;
     @FXML
    private JFXComboBox<String> statepdf;
     @FXML
    private JFXTimePicker periodetime;
   // private JFXComboBox<String> regionmodifier;
    //private ComboBox maha ; 
    private ObservableList<DemandeTaxi> data = FXCollections.observableArrayList();
    List<DemandeTaxi> ls = new ArrayList<>();
    User user;
    
    
    /**
     * Initializes the controller class.
     * @param 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MainMenuController mmc = new MainMenuController();
        user = mmc.getCurrentUser();
        System.out.println(user);
        
         Image img =  new Image("images/clientfront.png");
        taswira.setImage(img);
        
        
        ServiceDemandeTaxi demande = new ServiceDemandeTaxi();
      ls=demande.afficheruser(user.getId());
      System.out.println(ls);
      data.addAll(ls);
      depart.setCellValueFactory(new PropertyValueFactory<>("lieudepart"));
      destination.setCellValueFactory(new PropertyValueFactory<>("lieuarrive"));
      price.setCellValueFactory(new PropertyValueFactory<>("prix"));
      regiontab.setCellValueFactory(new PropertyValueFactory<>("region"));
       etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
      dated.setCellValueFactory(new PropertyValueFactory<>("dated"));
       etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
       demandeTableView.setItems(data);
       statepdf.getItems().addAll("Reserved","Unreserved");
            regiona.getItems().addAll("Ariana", "Béja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kebili", "Kef", "Mahdia", "Manouba", "Medenine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan"
);
   
        /*
           String title = "Congratulations sir";
        String message = "You've successfully created your first Tray Notification";
        Notifications notifications = Notifications.SUCCESS;
        
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotification(notification);
        tray.showAndWait();
     
      */
    
        /*
          try {
            cnx = Datasource.getInstance().getCnx();
            ResultSet rss=cnx.createStatement().executeQuery("select * from demande_taxi ");
            while(rss.next())
            {
                data.(new DemandeTaxi(rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getFloat(9)));
             


            }
                   
        } catch (SQLException ex) {
            
        }*/
        
        
         
                   
    }   
    
    
public void playmusic(String filename) 
{
  InputStream in ; 
try 
{
in = new FileInputStream(new File(filename));
AudioStream audios  = new AudioStream(in); 
AudioPlayer.player.start(audios);
}
catch (IOException e)
{
    System.out.print("error");
}
}
    
    
     public  void menu(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = new Scene(root);
         scene.getStylesheets().add(getClass().getResource("styletaxi.css").toExternalForm());
          Stage newWindow = new Stage();
           newWindow.setTitle("Menu ");
           newWindow.setScene(scene);
           newWindow.show();
             
           final Node source = (Node) event.getSource();
           final Stage stage = (Stage) source.getScene().getWindow();
       stage.close();
           
    }
     
     
     public void notification(String image , String titre , String text)
             
     {
     
     // Image check = new Image("images/chech0.png");
     Image check = new Image(image);
     Notifications notificationBuilder= Notifications.create()
             .title(titre)
             .text(text)
              .graphic(new ImageView(check))
              .hideAfter(Duration.seconds(5)) 
             .position(Pos.BOTTOM_RIGHT)
             .onAction(new  EventHandler<ActionEvent>( ) {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("!!!!!! notification  !!!! ");
            }
        });
       notificationBuilder.darkStyle();
 
        String music="src/images/sout.wav";
       playmusic(music);
     notificationBuilder.show();
     
     }
     
     
     
     
    public void refresh( ) {
        // TODO
        
        
     ServiceDemandeTaxi demande = new ServiceDemandeTaxi();
      ls=demande.afficheruser(user.getId());
      System.out.println(ls);
      data.addAll(ls);
       depart.setCellValueFactory(new PropertyValueFactory<>("lieudepart"));
       destination.setCellValueFactory(new PropertyValueFactory<>("lieuarrive"));
       periode.setCellValueFactory(new PropertyValueFactory<>("periode"));
        price.setCellValueFactory(new PropertyValueFactory<>("prix"));
      regiontab.setCellValueFactory(new PropertyValueFactory<>("region"));
       dated.setCellValueFactory(new PropertyValueFactory<>("dated"));
       etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
       demandeTableView.setItems(data);
  
                   
    }   
    
    
    public  void deleteDemande( )
    {
     ServiceDemandeTaxi demande = new ServiceDemandeTaxi();
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Info Dialog");
        alert1.setHeaderText("please select a Request ");
        
        
         DemandeTaxi e = demandeTableView.getSelectionModel().getSelectedItem();
         if(e!= null)
         {
         
         
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to delete this Event");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK)
        {
            
       System.out.println(e.getId());
        String image="images/sup.png";
       String text= "form "+e.getLieudepart() +" to "+  e.getLieuarrive() +" on " + e.getDated() + " at "+ e.getPeriode();
       String music="src/images/sout.wav";
       playmusic(music);
       notification(image," Taxi Request Successfully deleted ",text);
       demande.supprimer(e.getId());
        //productTable.getItems().clear();
        demandeTableView.getItems().clear();
       // id_afficheroffre.getItems().clear();
       refresh();
       clearajout();
       // refresh();
       // id_afficheroffre.refresh();
        }}
         else 
         {
          alert1.showAndWait();
         }
  
    
    
    }
    
    
    
     public void trierselonetat0()
              
              
      {
          
       
     ServiceDemandeTaxi demande = new ServiceDemandeTaxi();
     
     
      
      ls= demande.trieretat(0);
      System.out.println(ls);
        demandeTableView.getItems().clear();
      data.addAll(ls);
        depart.setCellValueFactory(new PropertyValueFactory<>("lieudepart"));
      destination.setCellValueFactory(new PropertyValueFactory<>("lieuarrive"));
      price.setCellValueFactory(new PropertyValueFactory<>("prix"));
      regiontab.setCellValueFactory(new PropertyValueFactory<>("region"));
       etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
      dated.setCellValueFactory(new PropertyValueFactory<>("dated"));
       etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
       demandeTableView.setItems(data);
        departa.setText("");
     destinationa.setText("");
  
    datea.getEditor().clear();
    periodea.getEditor().clear();
    periodetime.getEditor().clear();
    regiona.getEditor().clear();
    statepdf.getEditor().clear();
 
              
      
      }
      
      
      
         public void trierselonetat1()
              
              
      {ServiceDemandeTaxi demande = new ServiceDemandeTaxi();
     
      ls=demande.trieretat(1);
      
       System.out.println(ls);
      demandeTableView.getItems().clear();
      data.addAll(ls);
      depart.setCellValueFactory(new PropertyValueFactory<>("lieudepart"));
      destination.setCellValueFactory(new PropertyValueFactory<>("lieuarrive"));
      price.setCellValueFactory(new PropertyValueFactory<>("prix"));
      regiontab.setCellValueFactory(new PropertyValueFactory<>("region"));
       etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
      dated.setCellValueFactory(new PropertyValueFactory<>("dated"));
       etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
       demandeTableView.setItems(data);
      
  
      
      }
  
    public void clearajout()
            
    {
    
     departa.setText("");
     destinationa.setText("");
  
    datea.getEditor().clear();
    periodea.getEditor().clear();
    periodetime.getEditor().clear();
    regiona.getEditor().clear();
    
    
    }
    public void ajouterDemande() throws ParseException
    { ServiceDemandeTaxi demande = new ServiceDemandeTaxi();
    if("".equals(departa.getText())  || "".equals(destinationa.getText()) || regiona.getSelectionModel().getSelectedItem() == null || datea.getValue() == null || periodea.getValue() == null ||  periodetime.getValue() == null ) 
    {
    Alert alert1 = new Alert(Alert.AlertType.ERROR);
    alert1.setTitle("Error");
    alert1.setHeaderText("You have something missing   ");
    alert1.showAndWait();
    }
    else 
    {  
      ////// dateee /////  
    LocalDate date = datea.getValue() ; 
    String maha = date.toString(); 
    Date actuelle = new Date();
   
    
    ZoneId defaultZoneId = ZoneId.systemDefault();
     Date date1 = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
    ///////// date //////
    
    
    ///// timeeeee******//////
  
    LocalTime time = periodea.getValue();
    Instant instant = time.atDate(date).
    atZone(ZoneId.systemDefault()).toInstant();
    Date periodetab = Date.from(instant);
    
     //Date mahaaa=java.sql.Timestamp.valueOf(time.atDate(date));
    
    //System.out.println();
    
   // LocalTime time = periodea.getValue();
    String aham = time.toString();
    
    Date now = new Date();

    //Time now = new Time();
    DateFormat sdf = new SimpleDateFormat("E MMM dd  HH:mm:ss yyyy");
     String dat1 = sdf.format(now);
    Date time01 = new SimpleDateFormat("E MMM dd  HH:mm:ss yyyy").parse(dat1); 
     
      System.out.println(dat1);
    
 ///// timeeee////////////
   // ZoneId defaultZoneId = ZoneId.systemDefault();
 
    // Date now1 = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
    //Time date2 = Date.from(time.adjustInto(defaultZoneId).);
    //LocalTime now = LocalTime(); 
    if(date1.after(actuelle) )
   // LocalDateTime startOfDay = localDate.atTime(LocalTime.MAX);
  // manich maha 
    //if()
    {
        System.out.println(time01);
        System.out.println(periodetab);
        
          
        //////timeeee///////
            LocalTime timefinal = periodetime.getValue();
          DateFormat fd1= new SimpleDateFormat("HH:mm:ss");
            String heures = timefinal.toString()+":00";
           Date heure0 = new SimpleDateFormat("HH:mm:ss").parse(heures); 
           
           LocalTime timedebut = periodea.getValue();
           String heures1 =timedebut.toString()+":00" ;
           Date heure1 = new SimpleDateFormat("HH:mm:ss").parse(heures1); 
         long prix= heure0.getTime()- heure1.getTime() ; 
       
         long prix1= prix / 4000 ;
       // long prix2= prix1 * multi ; 
       
         float price=(float)prix1;
        float finalprice = price * 0.03F + 0.5F ;
        System.out.println(finalprice);
     
        ///////timeeee   ///////
        
        
        
        
        DemandeTaxi d = new DemandeTaxi(user.getId(),departa.getText(),destinationa.getText(),regiona.getSelectionModel().getSelectedItem(),aham,maha,0,finalprice);
     demande.ajouter(d);
     
     String image="images/chech0.png";
     String text= "form "+departa.getText()+" to "+ destinationa.getText()+ " on " +datea.getValue() + " at " + periodea.getValue();
     notification(image," Taxi Request Successfully Added ",text);
     demandeTableView.getItems().clear();
     refresh();
     clearajout();
    
     
    }
    else if(date1.compareTo(actuelle)== -1)
    {
     if(periodetab.after(time01)|| periodetab.equals(time01))
         
         
     {
         
           
        //////timeeee///////
           LocalTime timefinal = periodetime.getValue();
          DateFormat fd1= new SimpleDateFormat("HH:mm:ss");
            String heures = timefinal.toString()+":00";
           Date heure0 = new SimpleDateFormat("HH:mm:ss").parse(heures); 
           
           LocalTime timedebut = periodea.getValue();
           String heures1 =timedebut.toString()+":00" ;
           Date heure1 = new SimpleDateFormat("HH:mm:ss").parse(heures1); 
         long prix= heure0.getTime()- heure1.getTime() ; 
       
         long prix1= prix / 4000 ;
       // long prix2= prix1 * multi ; 
       
         float price=(float)prix1;
        float finalprice = price * 0.03F + 0.5F ;
        System.out.println(finalprice);
     
        ///////timeeee   ///////
         
         
         
    DemandeTaxi d = new DemandeTaxi(user.getId(),departa.getText(),destinationa.getText(),regiona.getSelectionModel().getSelectedItem(),aham,maha,0,finalprice);
     demande.ajouter(d);
     String image="images/chech0.png";
     
     String text= "form "+departa.getText()+" to "+ destinationa.getText()+ " on " +datea.getValue() + " at " + periodea.getValue();
     notification(image," Taxi Request Successfully Added ",text);
     demandeTableView.getItems().clear();
     refresh();
     clearajout();
     
     }
     else 
    {
    Alert alert1 = new Alert(Alert.AlertType.ERROR);
    alert1.setTitle("Error");
    alert1.setHeaderText("time non valide   ");
    alert1.showAndWait();
    clearajout();
    }
     
    
    }
    else if(date1.before(actuelle))
    {
    Alert alert1 = new Alert(Alert.AlertType.ERROR);
    alert1.setTitle("Error");
    alert1.setHeaderText("date non valide   ");
    alert1.showAndWait();
    }
     
    
    }
    // Integer.valueOf(id_nbrupdate.getText()); 
     
    
    
    
    }
   
    public void esm()
    {DemandeTaxi e = demandeTableView.getSelectionModel().getSelectedItem();
    departa.setText(e.getLieudepart());
    destinationa.setText(e.getLieuarrive());
    //regiona.setSelectionModel;
   // regiona.setSelectionModel(value);
    //dateeeee************///////
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  String date = e.getDated();

  //convert String to LocalDate
  LocalDate localDate = LocalDate.parse(date, formatter);
    datea.setValue(localDate);
    //////////////////**********dateee//////////
 DateTimeFormatter formattert = DateTimeFormatter.ofPattern("HH:mm");
 String time = e.getPeriode();
 LocalTime localTimeObj = LocalTime.parse(time, formattert);
 periodea.setValue(localTimeObj);
    ////timeeeeeeeeeeeeeeeee************
    
    /////***************time//////////
    
    }
   
    
    public void maxdeplacement()
    {
    
    
    
    
    
    
    }
    
    
    
   
      public void pdftry1()  
              
      { 
         mypdfjava pdf= new  mypdfjava();
         if (statepdf.getSelectionModel().getSelectedItem() != null)
         {   if(statepdf.getSelectionModel().getSelectedItem()=="Reserved")
         {
               pdf.pdf01(user.getId(),1,statepdf.getSelectionModel().getSelectedItem());
         }
         else if(statepdf.getSelectionModel().getSelectedItem()== "Unreserved")
         {  pdf.pdf01(user.getId(),0,statepdf.getSelectionModel().getSelectedItem());
         }  
         
         }
        
                 
         
      }
    
    public void modifier ( ) throws IOException , ParseException
    {ServiceDemandeTaxi demande = new ServiceDemandeTaxi();
     DemandeTaxi e = demandeTableView.getSelectionModel().getSelectedItem();
      Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Info Dialog");
       alert1.setHeaderText("please select a Request  ");
     if(e!=null)
     {
      if("".equals(departa.getText())  || "".equals(destinationa.getText()) || regiona.getSelectionModel().getSelectedItem() == null || datea.getValue() == null || periodea.getValue() == null ) 
    {
     Alert alert2 = new Alert(Alert.AlertType.ERROR);
    alert2.setTitle("Error");
    alert2.setHeaderText("You have something missing   ");
    alert2.showAndWait();
    
    
    
    }
     else
      {     
     
     LocalDate date = datea.getValue() ; 
    String maha = date.toString(); 
    LocalTime time = periodea.getValue();
    String aham = time.toString();
      Date actuelle = new Date();
      DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss"); 
    String dat = dateFormat.format(actuelle);
    ZoneId defaultZoneId = ZoneId.systemDefault();
    
    Date date1 = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
    //Time date2 = Date.from(time.adjustInto(defaultZoneId).);
    //LocalTime now = LocalTime(); 
    if(date1.after(actuelle) )
    {
       
               //////timeeee///////
            LocalTime timefinal = periodetime.getValue();
          DateFormat fd1= new SimpleDateFormat("HH:mm:ss");
            String heures = timefinal.toString()+":00";
           Date heure0 = new SimpleDateFormat("HH:mm:ss").parse(heures); 
           
           LocalTime timedebut = periodea.getValue();
           String heures1 =timedebut.toString()+":00" ;
           Date heure1 = new SimpleDateFormat("HH:mm:ss").parse(heures1); 
         long prix= heure0.getTime()- heure1.getTime() ; 
       
         long prix1= prix / 4000 ;
       // long prix2= prix1 * multi ; 
       
         float price=(float)prix1;
        float finalprice = price * 0.03F + 0.5F ;
        System.out.println(finalprice);
     
        ///////timeeee   ///////
        
        
        
        
     DemandeTaxi d = new DemandeTaxi(user.getId(),departa.getText(),destinationa.getText(),regiona.getSelectionModel().getSelectedItem(),aham,maha,0,finalprice);
    demande.modifier(d,e.getId() );
    String image="images/modif.png";
     String text= "Form "+departa.getText()+" to "+ destinationa.getText()+ " on " +datea.getValue() + " at " + periodea.getValue();
     
     notification(image," Taxi Request Successfully  modified  ",text);
     
    demandeTableView.getItems().clear();
    refresh();
    clearajout();
    
    
    }
    else if(date1.before(actuelle))
    {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert1.setTitle("Error");
    alert1.setHeaderText("date non valide   ");
    alert1.showAndWait();
    }
    
   
     
            }
     }
 
   
    else
    {alert1.showAndWait();
    System.out.println("noooo");
      }
    
    }
    
    
    
    
}
