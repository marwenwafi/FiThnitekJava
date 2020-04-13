/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import fithnitek.controllers.ServiceEvent;
import fithnitek.controllers.ServiceNotificationEvent;
import fithnitek.models.Event;
import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author sourour
 */
public class ParticiperController implements Initializable {

    @FXML
    private Label Titreevent;
    @FXML
    private ImageView imagevent;
    @FXML
    private Label Descriptionevent;
    private static String titre ;
    @FXML
    private Label datefin;
    ServiceNotificationEvent sv=new ServiceNotificationEvent();
    Event ev;

    public static String getTitre() {
        return titre;
    }

    public static void setTitre(String titre) {
        ParticiperController.titre = titre;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titre=ConvertirController.getIdof();
        System.out.println("je suis da ParticipeController "+titre);
        myevent();
    }   
    public void myevent(){
        int id= sv.getidBytitre(titre);
        System.out.println("Events.ParticiperController.myevent()"+id);
         ev =sv.getEventPart(id);
        System.out.println(ev);
        Titreevent.setText(ev.getTitre());
        Descriptionevent.setMaxSize(500, 500);
        Descriptionevent.setText(ev.getDescription());
        String s=""+ev.getDateFin().toString();
        datefin.setText("End date: " +s);
        System.err.println(s);
        try {
            Image image = new Image(new FileInputStream("C:\\wamp64\\www\\PiDev\\web\\uploads\\eventsImages\\"+ev.getImage()));
            imagevent.setImage(image);
            imagevent.setFitHeight(235);
            imagevent.setFitWidth(431);
            imagevent.setX(75);
            imagevent.setY(20);
            
        } catch (FileNotFoundException ex) {
            System.out.println("no image ");
        }
        
       
       
    }

   

    

    @FXML
    private void Participer(ActionEvent event) {
        int id = sv.getidBytitre(titre);
        String operation=ev.getOperation();
        
        if(operation.equals("Publicité"))
        {
            openWebpage(ev.getUrl());
           sv.participer(id, 5);
           
        try {
            toFront(event);
        } catch (Exception ex) {
            Logger.getLogger(ParticiperController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        }
        else if(operation.equals("Questionnaire"))
        {
            
            try {
                setTitre(ev.getTitre());
                repondre(event);
            } catch (Exception ex) {
                Logger.getLogger(ParticiperController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        closeButtonAction(event);
    }

    @FXML
    private void ignorer(ActionEvent event) throws Exception {               
    
        closeButtonAction(event);
        toFront(event);
        
        
    }
    /**************Pour tester**************/
    
     private void test(ActionEvent event) 
        throws Exception {               
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Notificationetfid.fxml"));
        //QuestionnaireQu.fxml
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }
    }
     /***************OPEN WEB PAGE*****************/
     public static void openWebpage(String urlString) {
    try {
        Desktop.getDesktop().browse(new URL(urlString).toURI());
    } catch (Exception e) {
        e.printStackTrace();
    }
}
     
     
     /************Now to Repondre*************/
    private void repondre(ActionEvent event) 
        throws Exception {               
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Repondre.fxml"));
        //QuestionnaireQu.fxml
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }
    }
    
    /******************* REDIRECT **************************/
    private void closeButtonAction(ActionEvent e){
  
       // Platform.exit();
       final Node source = (Node) e.getSource();
    final Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
 
}
    
    private void toFront(ActionEvent event) throws Exception {               
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Convertir.fxml"));
        
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }
    }
    
}
