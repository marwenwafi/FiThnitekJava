/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiTAXI ;
import fithnitek.models.DemandeTaxi;
import fithnitek.models.ReservationTaxi;
import fithnitek.controllers.ServiceDemandeTaxi;
import fithnitek.controllers.ServiceReservationTaxi;



import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import fithnitek.controllers.MainMenuController;
import fithnitek.models.User;

import fithnitek.utils.DataSource;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


   

/**
 * FXML Controller class
 *
 * @author Rawis
 */
public class AffichageReservationTaxiController implements Initializable {
   @FXML
    private TableColumn<ReservationTaxi, String> region;

    @FXML
    private TableColumn<ReservationTaxi, String> depart;

    @FXML
    private TableColumn<ReservationTaxi, String> destination;
    @FXML
    private ImageView logo;

    @FXML
    private ImageView taxi;
    @FXML
    private TableColumn<ReservationTaxi, String> periode;

    @FXML
    private TableColumn<ReservationTaxi, String> dated;

    @FXML
    private TableColumn<ReservationTaxi, String> prix;

    @FXML
    private TableColumn<ReservationTaxi, String> client;
    @FXML
    private TableView<ReservationTaxi> reservationTableView;
    private ObservableList<ReservationTaxi> data = FXCollections.observableArrayList();
    List<ReservationTaxi> ls = new ArrayList<>();
    
    User user;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainMenuController mmc = new MainMenuController();
        user = mmc.getCurrentUser();
       ServiceReservationTaxi reser = new ServiceReservationTaxi();
      ls=reser.affichertaxi(user.getId());
      
      System.out.println(ls);
      data.addAll(ls);
      depart.setCellValueFactory(new PropertyValueFactory<>("lieudepart"));
      destination.setCellValueFactory(new PropertyValueFactory<>("lieuarrive"));
      periode.setCellValueFactory(new PropertyValueFactory<>("periode"));
     prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    region.setCellValueFactory(new PropertyValueFactory<>("region"));
      dated.setCellValueFactory(new PropertyValueFactory<>("dated"));
      client.setCellValueFactory(new PropertyValueFactory<>("username"));
      reservationTableView.setItems(data);
        javafx.scene.image.Image img =  new javafx.scene.image.Image("images/taxi.png");
        taxi.setImage(img);
         javafx.scene.image.Image img2 =  new javafx.scene.image.Image("images/logo.png");
        logo.setImage(img2);
      
    
      
      
      
    }
   //int id, float prix, String lieudepart, String lieuarrive, String region, String periode, String dated, String username

    
     public  void deleteReservation( )
    {
     ServiceReservationTaxi res  = new ServiceReservationTaxi();
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Info Dialog");
        alert1.setHeaderText("Tu n as pas selectioné une reservation ");
        
        
         ReservationTaxi e = reservationTableView.getSelectionModel().getSelectedItem();
         if(e!= null)
         {
         
         
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to delete this Reservation");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK)
        {
            
       System.out.println(e.getId());
        res.supprimer(e.getId());
      ServiceDemandeTaxi demande= new ServiceDemandeTaxi();
      demande.modifieretat(e.getIddemande(),0); 
        //productTable.getItems().clear();
        reservationTableView.getItems().clear();
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
     
       public void pdftry1( )  
              
      { 
            mypdfjava pdf= new  mypdfjava();
         pdf.pdfreservation(user.getId());
          
          
         
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
     
      public  void request(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("espaceclientB.fxml"));
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
     
    public void  refresh()
    {
     ServiceReservationTaxi reser = new ServiceReservationTaxi();
      ls=reser.affichertaxi(user.getId());
      System.out.println(ls);
      data.addAll(ls);
     depart.setCellValueFactory(new PropertyValueFactory<>("lieudepart"));
      destination.setCellValueFactory(new PropertyValueFactory<>("lieuarrive"));
      periode.setCellValueFactory(new PropertyValueFactory<>("periode"));
     prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    region.setCellValueFactory(new PropertyValueFactory<>("region"));
      dated.setCellValueFactory(new PropertyValueFactory<>("dated"));
      client.setCellValueFactory(new PropertyValueFactory<>("username"));
      reservationTableView.setItems(data);
    
    
    }
    
    
    
       public  void taxi(ActionEvent event) throws IOException {
      Parent root1 = FXMLLoader.load(getClass().getResource("espacechauffeur.fxml"));
        Scene scene = new Scene(root1);
     scene.getStylesheets().add(getClass().getResource("styletaxi.css").toExternalForm());
          Stage newWindow = new Stage();
           newWindow.setTitle("taxi ");
           newWindow.setScene(scene);
           newWindow.show();
                final Node source = (Node) event.getSource();
           final Stage stage = (Stage) source.getScene().getWindow();
       stage.close();
    }
}
