/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiTAXI;


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import static com.sun.org.apache.regexp.internal.RETest.test;
import fithnitek.controllers.ServiceDemandeTaxi;
import fithnitek.models.DemandeTaxi;

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
    private JFXTextField destinationa;

    @FXML
    private JFXDatePicker datea;
    @FXML
    private JFXTimePicker periodea;
    @FXML
    private JFXTextField departa;
    @FXML 
   // private JFXComboBox<String> regionmodifier;
    //private ComboBox maha ; 
    private ObservableList<DemandeTaxi> data = FXCollections.observableArrayList();
    List<DemandeTaxi> ls = new ArrayList<>();
    /**
     * Initializes the controller class.
     * @param 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceDemandeTaxi demande = new ServiceDemandeTaxi();
      ls=demande.afficheruser(3);
      System.out.println(ls);
      data.addAll(ls);
      depart.setCellValueFactory(new PropertyValueFactory<>("lieudepart"));
      destination.setCellValueFactory(new PropertyValueFactory<>("lieuarrive"));
      periode.setCellValueFactory(new PropertyValueFactory<>("periode"));
  
      dated.setCellValueFactory(new PropertyValueFactory<>("dated"));
       etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
       demandeTableView.setItems(data);
      
       regiona.getItems().addAll("Ariana","Tunis");
       
    
        
        
         
                   
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
     
     
     
     
     
     
    public void refresh( ) {
        // TODO
        
        
     ServiceDemandeTaxi demande = new ServiceDemandeTaxi();
      ls=demande.afficheruser(3);
      System.out.println(ls);
      data.addAll(ls);
       depart.setCellValueFactory(new PropertyValueFactory<>("lieudepart"));
       destination.setCellValueFactory(new PropertyValueFactory<>("lieuarrive"));
       periode.setCellValueFactory(new PropertyValueFactory<>("periode"));
     
       dated.setCellValueFactory(new PropertyValueFactory<>("dated"));
       etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
       demandeTableView.setItems(data);
       //  regiona.getItems().addAll("option1","option2");
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
    
    
    public  void deleteDemande( )
    {
     ServiceDemandeTaxi demande = new ServiceDemandeTaxi();
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Info Dialog");
        alert1.setHeaderText("Tu n as pas selectioné une demande ");
        
        
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

       demande.supprimer(e.getId());
        //productTable.getItems().clear();
        demandeTableView.getItems().clear();
       // id_afficheroffre.getItems().clear();
       refresh();
       // refresh();
       // id_afficheroffre.refresh();
        }}
         else 
         {
          alert1.showAndWait();
         }
  
    
    
    }
  
    public void clearajout()
            
    {
    
     departa.setText("");
     destinationa.setText("");
  
    datea.getEditor().clear();
    periodea.getEditor().clear();
    
    
    
    }
    public void ajouterDemande() throws ParseException
    { ServiceDemandeTaxi demande = new ServiceDemandeTaxi();
    if("".equals(departa.getText())  || "".equals(destinationa.getText()) || regiona.getSelectionModel().getSelectedItem() == null || datea.getValue() == null || periodea.getValue() == null ) 
    {
    Alert alert1 = new Alert(Alert.AlertType.ERROR);
    alert1.setTitle("Error");
    alert1.setHeaderText("please fill haja  ");
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
        DemandeTaxi d = new DemandeTaxi(3,departa.getText(),destinationa.getText(),regiona.getSelectionModel().getSelectedItem(),aham,maha,0,20);
     demande.ajouter(d);
     
 
     demandeTableView.getItems().clear();
     refresh();
     clearajout();
    
     
    }
    else if(date1.compareTo(actuelle)== -1)
    {
     if(periodetab.after(time01)|| periodetab.equals(time01))
     {DemandeTaxi d = new DemandeTaxi(3,departa.getText(),destinationa.getText(),regiona.getSelectionModel().getSelectedItem(),aham,maha,0,20);
     demande.ajouter(d);

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
   
      public void pdftry1()  
              
      {
      }
    
    public void modifier ( ) throws IOException
    {ServiceDemandeTaxi demande = new ServiceDemandeTaxi();
     DemandeTaxi e = demandeTableView.getSelectionModel().getSelectedItem();
      Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Info Dialog");
       alert1.setHeaderText("Tu n as pas selectioné une demande ");
     if(e!=null)
     {
      if("".equals(departa.getText())  || "".equals(destinationa.getText()) || regiona.getSelectionModel().getSelectedItem() == null || datea.getValue() == null || periodea.getValue() == null ) 
    {
     Alert alert2 = new Alert(Alert.AlertType.ERROR);
    alert2.setTitle("Error");
    alert2.setHeaderText("please fill haja  ");
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
    
     DemandeTaxi d = new DemandeTaxi(3,departa.getText(),destinationa.getText(),regiona.getSelectionModel().getSelectedItem(),aham,maha,0,20);
    demande.modifier(d,e.getId() );
  
     
    demandeTableView.getItems().clear();
    refresh();
    
    
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
