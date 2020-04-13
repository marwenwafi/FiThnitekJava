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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
              
      {  Document doc = new Document();
        Connection cnx = DataSource.getInstance().getCnx();
        //
      String sql = "SELECT * FROM reservation_taxis R INNER JOIN  demande_taxi D ON  R.iddemande=D.id  INNER JOIN fos_user U ON D.iduser=U.id  where R.iduser="+user.getId();
       try {
        
      
         PreparedStatement pst = cnx.prepareStatement(sql);
              
          
          ResultSet rs = pst.executeQuery();
          
          
           PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Lenovo\\Desktop\\FiThnitek\\src\\images\\try1.pdf"));
           doc.open();
           Image logo = Image.getInstance("C:\\Users\\Lenovo\\Desktop\\FiThnitek\\src\\images\\logo0.png");
           logo.scaleAbsoluteHeight(150);
           logo.scaleAbsoluteWidth(600);
           logo.setAlignment(Image.ALIGN_CENTER);
           
           doc.add(logo);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);
            PdfPCell cell;
            
            
           cell = new PdfPCell( new Phrase("Name",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
           
           
           cell = new PdfPCell( new Phrase("Username",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
           
           cell = new PdfPCell( new Phrase("Region",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
           
           
           
           cell = new PdfPCell( new Phrase("Departure ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
            cell = new PdfPCell( new Phrase("Destination ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
           
            cell = new PdfPCell( new Phrase("Date ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
          doc.add(table);
           table.addCell(cell);
          
          
           cell = new PdfPCell( new Phrase("Time ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           doc.add(table);
           table.addCell(cell);
          
           cell = new PdfPCell( new Phrase("Price ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
           
       
          while(rs.next())
          {
          
         
           cell = new PdfPCell( new Phrase(rs.getString(14),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
          
           cell = new PdfPCell( new Phrase(rs.getString(25),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
           
           
         
           
           
           cell = new PdfPCell( new Phrase(rs.getString(8),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
           
           
           
           cell = new PdfPCell( new Phrase(rs.getString(6),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
         
           cell = new PdfPCell( new Phrase(rs.getString(7),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
        
           
           
           
            cell = new PdfPCell( new Phrase(rs.getString(10),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
           
            cell = new PdfPCell( new Phrase(rs.getString(9),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
            String price= String.valueOf(rs.getFloat(12));
            cell = new PdfPCell( new Phrase(price,FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
              
          }
          
           doc.add(table);
           
           doc.close();
           Desktop.getDesktop().open( new File ("C:\\Users\\Lenovo\\Desktop\\FiThnitek\\src\\images\\try1.pdf"));
          
        
                   
       }
          
        catch (DocumentException ex) {
           Logger.getLogger(AffichageReservationTaxiController.class.getName()).log(Level.SEVERE, null, ex);
       } catch (FileNotFoundException ex) {
           Logger.getLogger(AffichageReservationTaxiController.class.getName()).log(Level.SEVERE, null, ex);
       } 
       catch (IOException ex) {
           Logger.getLogger(AffichageReservationTaxiController.class.getName()).log(Level.SEVERE, null, ex);
       }
      catch (SQLException ex) {
              Logger.getLogger(AffichageReservationTaxiController.class.getName()).log(Level.SEVERE, null, ex);
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
    }
     
    public void  refresh()
    {
     ServiceReservationTaxi reser = new ServiceReservationTaxi();
      ls=reser.affichertaxi(user.getId());
      System.out.println(ls);
      data.addAll(ls);
      depart.setCellValueFactory(new PropertyValueFactory<>("id"));
      destination.setCellValueFactory(new PropertyValueFactory<>("iddemande"));
     // periode.setCellValueFactory(new PropertyValueFactory<>("periode"));
     // prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
     //region.setCellValueFactory(new PropertyValueFactory<>("region"));
     // dated.setCellValueFactory(new PropertyValueFactory<>("dated"));
      client.setCellValueFactory(new PropertyValueFactory<>("iduser"));
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
    }
}
