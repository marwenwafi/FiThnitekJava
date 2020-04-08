/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek;

import fithnitek.controllers.ServiceEvent;
import fithnitek.models.Event;
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
    
    ServiceEvent sv= new ServiceEvent();
    private String titre ;
    @FXML
    private Label datefin;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titre=EventQuController.eventpart;
        System.err.println(titre);
        myevent();
    }   
    public void myevent(){
        int id= sv.getidBytitre(titre);
        Event ev =sv.getEventById(id);
        Titreevent.setText(ev.getTitre());
        Descriptionevent.setMaxSize(500, 500);
        Descriptionevent.setText(ev.getDescription());
        datefin.setText("Date fin: "+ev.getDateDebut().toString());
        try {
            Image image = new Image(new FileInputStream("C:\\wamp64\\www\\PiDev\\web\\uploads\\eventsImages\\"+ev.getImage()));
            imagevent.setImage(image);
            imagevent.setFitHeight(235);
            imagevent.setFitWidth(431);
            imagevent.setX(75);
            imagevent.setY(20);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ParticiperController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       
    }

    @FXML
    private void convertir(ActionEvent event) 
        throws Exception {               
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Participer.fxml"));
        //QuestionnaireQu.fxml
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void msevents(ActionEvent event) 
        throws Exception {               
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EventQu.fxml"));
        //QuestionnaireQu.fxml
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }
        
    }

    @FXML
    private void Participer(ActionEvent event) {
        int id = sv.getidBytitre(titre);
        
        
        sv.participer(id, 5);
        
        try {
            ignorer(event);
        } catch (Exception ex) {
            Logger.getLogger(ParticiperController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
    }

    @FXML
    private void ignorer(ActionEvent event) throws Exception {               
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EventQu.fxml"));
        
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }
        
    }
    
}
