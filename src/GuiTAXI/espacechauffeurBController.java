/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiTAXI;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import fithnitek.controllers.MainMenuController;
import fithnitek.models.DemandeTaxi;
import fithnitek.models.ReservationTaxi;
import fithnitek.controllers.ServiceDemandeTaxi;
import fithnitek.controllers.ServiceReservationTaxi;
import fithnitek.models.User;
import fithnitek.utils.DataSource;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
/**
 * FXML Controller class
 *
 * @author Rawis
 */
public class espacechauffeurBController implements Initializable {
    @FXML
    private JFXComboBox<String> Region;
   /* @FXML
    private JFXComboBox<String> user ;*/
    @FXML
  
    private DatePicker daterech;

    @FXML
    private TableView<DemandeTaxi> reservationTableView;

    @FXML
    private TableColumn<DemandeTaxi, String> depart;

    @FXML
    private TableColumn<DemandeTaxi, String> destination;

    @FXML
    private TableColumn<DemandeTaxi, String> periode;

    @FXML
    private TableColumn<DemandeTaxi, String> dated;

    @FXML
    private TableColumn<DemandeTaxi, String> nomclient;
    @FXML
    private TableColumn<DemandeTaxi, String> regiont;
     @FXML
    private TableColumn<DemandeTaxi, String> prix;
    @FXML
    private JFXTextField departrech;
    
    @FXML
    private ImageView taswira1;

    @FXML
    private ImageView taswira2;
    
    //private ComboBox maha ; 
    private ObservableList<DemandeTaxi> data = FXCollections.observableArrayList();
    List<DemandeTaxi> ls = new ArrayList<>();
    User user;
    /**
     * Initializes the controller class.
     */
       

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainMenuController mmc = new MainMenuController();
        user = mmc.getCurrentUser();
        Image img =  new Image("images/taxi.png");
        taswira1.setImage(img);
         Image img2 =  new Image("images/logo.png");
        taswira2.setImage(img2);
      ServiceDemandeTaxi demande = new ServiceDemandeTaxi();
      ls=demande.afficheradmin();
      System.out.println(ls);
      data.addAll(ls);
      depart.setCellValueFactory(new PropertyValueFactory<>("lieudepart"));
      destination.setCellValueFactory(new PropertyValueFactory<>("lieuarrive"));
      periode.setCellValueFactory(new PropertyValueFactory<>("periode"));
      prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
     regiont.setCellValueFactory(new PropertyValueFactory<>("region"));
      dated.setCellValueFactory(new PropertyValueFactory<>("dated"));
      nomclient.setCellValueFactory(new PropertyValueFactory<>("username"));
      reservationTableView.setItems(data);
        Region.getItems().addAll("Ariana","Tunis");
        /*
        Connection cnx = DataSource.getInstance().getCnx();
      String req="SELECT email FROM fos_user ";
       PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
           // String np= rs.getString("username") + rs.getString("nom") ;
          
            
             user.getItems().addAll(rs.getString("email"));
            
            
            }
            
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(EspaceclientBController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
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
           
      ServiceDemandeTaxi demande = new ServiceDemandeTaxi();
      ls=demande.afficheradmin();
      System.out.println(ls);
      data.addAll(ls);
      depart.setCellValueFactory(new PropertyValueFactory<>("lieudepart"));
      destination.setCellValueFactory(new PropertyValueFactory<>("lieuarrive"));
      periode.setCellValueFactory(new PropertyValueFactory<>("periode"));
   prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
   regiont.setCellValueFactory(new PropertyValueFactory<>("region"));
      dated.setCellValueFactory(new PropertyValueFactory<>("dated"));
      nomclient.setCellValueFactory(new PropertyValueFactory<>("username"));
      reservationTableView.setItems(data);
        Region.getItems().addAll("Ariana","Tunis");
        /*
         Connection cnx = DataSource.getInstance().getCnx();
      String req="SELECT email FROM fos_user ";
       PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
           // String np= rs.getString("username") + rs.getString("nom") ;
          
            
             user.getItems().addAll(rs.getString("email"));
            
            
            }
            
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(EspaceclientBController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
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
   
    public void ajouter( )
    {
    
       Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Info Dialog");
        alert1.setHeaderText("Tu n as pas selectioné une demande ");
        
        
         DemandeTaxi  e = reservationTableView.getSelectionModel().getSelectedItem();
       //  if(e!= null || user.getSelectionModel().getSelectedItem() != null)
        if(e!= null ) {
              ReservationTaxi res = new ReservationTaxi(user.getId(),e.getId());
       ServiceReservationTaxi r= new ServiceReservationTaxi();
         ServiceDemandeTaxi demande= new ServiceDemandeTaxi();
      demande.modifieretat(e.getId(),1); 
      
      
       String image="images/chech0.png";
       String text= "form "+e.getLieudepart() +" to "+  e.getLieuarrive() +" on " + e.getDated() + " at "+ e.getPeriode(); 
       String music="src/images/sout.wav";
       playmusic(music);
       notification(image," Taxi Request Successfully  ",text);
      r.ajouter(res);
      reservationTableView.getItems().clear();
     refresh();
     /*
           try {
               Integer iduser= getUser(user.getSelectionModel().getSelectedItem());
           
       ReservationTaxi res = new ReservationTaxi(iduser,e.getId());
       ServiceReservationTaxi r= new ServiceReservationTaxi();
         ServiceDemandeTaxi demande= new ServiceDemandeTaxi();
      demande.modifieretat(e.getId(),1); 
      r.ajouter(res);
      reservationTableView.getItems().clear();
     refresh();
           }
           catch (SQLException ex) {
               Logger.getLogger(espacechauffeurBController.class.getName()).log(Level.SEVERE, null, ex);
           }*/
         }
         
         else 
         {
          alert1.showAndWait();
         
         }
    
    
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
    
        public void triedatePrixHeure()
            
    {
    
     ServiceDemandeTaxi demande = new ServiceDemandeTaxi();
      ls=demande.triedateB();
       System.out.println(ls);
         reservationTableView.getItems().clear();
      data.addAll(ls);
      
      depart.setCellValueFactory(new PropertyValueFactory<>("lieudepart"));
      destination.setCellValueFactory(new PropertyValueFactory<>("lieuarrive"));
      periode.setCellValueFactory(new PropertyValueFactory<>("periode"));
  prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
   regiont.setCellValueFactory(new PropertyValueFactory<>("region"));
      dated.setCellValueFactory(new PropertyValueFactory<>("dated"));
      nomclient.setCellValueFactory(new PropertyValueFactory<>("username"));
      reservationTableView.setItems(data);
        Region.getItems().addAll("Ariana","Tunis");
    
    
    
    
    }
   
    
    public void recherchedateRegion()
   {
     ServiceDemandeTaxi demande = new ServiceDemandeTaxi();
     System.out.println(Region.getSelectionModel().getSelectedItem());
    // System.out.println(daterech.getValue());
     System.out.println(departrech.getText());
    if(Region.getSelectionModel().getSelectedItem() == null || daterech.getValue() == null  ||  "".equals(departrech.getText()) ) 
    {
    Alert alert1 = new Alert(Alert.AlertType.ERROR);
    alert1.setTitle("Error");
    alert1.setHeaderText("please fill haja  ");
    alert1.showAndWait();
    }
    else {
         
   LocalDate date = daterech.getValue() ; 
    String maha = date.toString(); 
  // ls= demande.recherchedateRegionB( maha, Region.getSelectionModel().getSelectedItem(),departrech.getText());
    ls= demande.recherchedateRegionB( maha, Region.getSelectionModel().getSelectedItem(),departrech.getText());
    System.out.println(ls);
      reservationTableView.getItems().clear();
      data.addAll(ls);
      depart.setCellValueFactory(new PropertyValueFactory<>("lieudepart"));
      destination.setCellValueFactory(new PropertyValueFactory<>("lieuarrive"));
      periode.setCellValueFactory(new PropertyValueFactory<>("periode"));
       prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
      regiont.setCellValueFactory(new PropertyValueFactory<>("region"));
      dated.setCellValueFactory(new PropertyValueFactory<>("dated"));
      nomclient.setCellValueFactory(new PropertyValueFactory<>("username"));
      reservationTableView.setItems(data);
       Region.getItems().addAll("Ariana","Tunis");

    
    }
       
     
   
        
   //  System.out.println("saiiiit");
     
        //stage.show();
    }  
  
    public  void taxi(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("affichertaxiR.fxml"));
        Scene scene = new Scene(root);
     scene.getStylesheets().add(getClass().getResource("styletaxi.css").toExternalForm());
          Stage newWindow = new Stage();
           newWindow.setTitle(" Taxi Reservation  ");
           newWindow.setScene(scene);
           newWindow.show();
    }
     public  void menu(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = new Scene(root);
     scene.getStylesheets().add(getClass().getResource("styletaxi.css").toExternalForm());
          Stage newWindow = new Stage();
           newWindow.setTitle("Menu ");
           newWindow.setScene(scene);
           newWindow.show();
    }
     
     
     public  void request(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("espaceclientB.fxml"));
        Scene scene = new Scene(root);
     scene.getStylesheets().add(getClass().getResource("styletaxi.css").toExternalForm());
          Stage newWindow = new Stage();
           newWindow.setTitle("Taxi Request  ");
           newWindow.setScene(scene);
           newWindow.show();
    }

}
  
    
    
    

 




