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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
/**
 * FXML Controller class
 *
 * @author Rawis
 */
public class espacechauffeurController implements Initializable {
    @FXML
    private JFXComboBox<String> Region;

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
      ServiceDemandeTaxi demande = new ServiceDemandeTaxi();
      ls=demande.afficherclient(user.getId());
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
    }    
    
       public void refresh( ) {
           
      ServiceDemandeTaxi demande = new ServiceDemandeTaxi();
      ls=demande.afficherclient(user.getId());
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
       }
       
      
   
    public void ajouter( )
    {
    
       Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Info Dialog");
        alert1.setHeaderText("Tu n as pas selectioné une demande ");
        
        
         DemandeTaxi  e = reservationTableView.getSelectionModel().getSelectedItem();
         if(e!= null)
         {
       ReservationTaxi res = new ReservationTaxi(user.getId(),e.getId());
       ServiceReservationTaxi r= new ServiceReservationTaxi();
         ServiceDemandeTaxi demande= new ServiceDemandeTaxi();
      demande.modifieretat(e.getId(),1); 
      r.ajouter(res);
      reservationTableView.getItems().clear();
     refresh();
     
         }
         else 
         {
          alert1.showAndWait();
         
         }
    
    
    }   
    
    
    
        public void triedate()
            
    {
    
     ServiceDemandeTaxi demande = new ServiceDemandeTaxi();
      ls=demande.triedate(user.getId());
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
    public void triprix()
            
    {
    
     ServiceDemandeTaxi demande = new ServiceDemandeTaxi();
      ls=demande.trieprix(user.getId());
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
    if(Region.getSelectionModel().getSelectedItem() == null || daterech.getValue() == null ||"".equals(departrech.getText()) ) 
    {
    Alert alert1 = new Alert(Alert.AlertType.ERROR);
    alert1.setTitle("Error");
    alert1.setHeaderText("please fill haja  ");
    alert1.showAndWait();
    }
    else {
        
   LocalDate date = daterech.getValue() ; 
    String maha = date.toString(); 
   ls= demande.recherchedateRegion(user.getId(), maha, Region.getSelectionModel().getSelectedItem(),departrech.getText());
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
           newWindow.setTitle("taxi ");
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

}
  
    
    
    

 





