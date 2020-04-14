/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import fithnitek.controllers.MainMenuController;
import fithnitek.controllers.ServiceNotificationEvent;
import fithnitek.models.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author sourour
 */
public class RepondreController implements Initializable {

    @FXML
    private Label Titreevent;
    @FXML
    private VBox vbox;
    private static String titre ;
    ServiceNotificationEvent sv= new ServiceNotificationEvent();
    Event ev;
    int id=0;
    RadioButton reponse1,reponse2;
    @FXML
    private Button reply;
    User user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainMenuController mmc = new MainMenuController();
        user = mmc.getCurrentUser();
        titre=ParticiperController.getTitre();
        id=sv.getidBytitre(titre);
        ev=sv.getEventPart(id);
        Titreevent.setText(titre);
        affichaerquest();
    }
    public void affichaerquest()
    {
        List<Questionnaire> ls= new ArrayList<Questionnaire>();
        ls=sv.afficherQuestList(id);
        vbox.setAlignment(Pos.BASELINE_LEFT);
        for(Questionnaire q : ls){
            Label question=new Label();
            
           // Label chariot=new Label("\n");
            ToggleGroup group = new ToggleGroup();
             reponse1=new RadioButton (q.getReponse1());
            reponse1.setToggleGroup(group);
           // reponse1.setSelected(true);
             reponse2=new RadioButton (q.getReponse2());
             reponse2.setToggleGroup(group);
            reponse1.setAlignment(Pos.CENTER);
            reponse2.setAlignment(Pos.CENTER);
            
            question.setText("    "+q.getQuestion());
            question.setTextFill(Color.WHITE);
            question.setWrapText(true);
            question.setEllipsisString(null);
            
            
            
            
            vbox.getChildren().add(question);
            vbox.getChildren().add(new Label("\n"));
            vbox.getChildren().add(reponse1);
            vbox.getChildren().add(new Label("\n"));
            vbox.getChildren().add(reponse2);
            vbox.getChildren().add(new Label("\n"));
            
        }
    }

    @FXML
    private void reply(ActionEvent event) {
        
        if(reponse1.isSelected()==false && reponse2.isSelected()==false)
        {
            System.out.println("You must answer all questions");
            Alert alt= new Alert(Alert.AlertType.ERROR, "You must answer all questions", ButtonType.OK);
            alt.show();
        }
        else{
            System.out.println("ok");
            
            sv.repondre(id, user.getId());
            Alert alt= new Alert(Alert.AlertType.INFORMATION, "You participated in the event"+ev.getTitre(), ButtonType.OK);
            
            closeButtonAction(event);
        try {
            toFront(event);
        } catch (Exception ex) {
            Logger.getLogger(RepondreController.class.getName()).log(Level.SEVERE, null, ex);
        }
            alt.show();
        }
        
        
        
     }

    @FXML
    private void ignorer(ActionEvent event) {
        closeButtonAction(event);
        try {
            toFront(event);
        } catch (Exception ex) {
            Logger.getLogger(RepondreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    
    /******************* REDIRECT **************************/
    private void closeButtonAction(ActionEvent e){
  
       // Platform.exit();
       final Node source = (Node) e.getSource();
    final Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
 
}
    @FXML
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
