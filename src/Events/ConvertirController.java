/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import fithnitek.controllers.ServiceEvent;
import fithnitek.controllers.ServiceNotificationEvent;
import fithnitek.models.NotificationEvent;
import java.awt.BorderLayout;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sourour
 */
public class ConvertirController implements Initializable {

    @FXML
    private ChoiceBox<String> coixConv;
    @FXML
    private Label points;
    
     static ServiceNotificationEvent sv =new ServiceNotificationEvent();
    @FXML
    private Label titreEvent;
   
    private static String idof;
    @FXML
    private VBox root1;
    @FXML
    private Pagination pagination;
    public final int itemsPerPage=2;

    public static String getIdof() {
        return idof;
    }

    public static void setIdof(String idof) {
        ConvertirController.idof = idof;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int mespoints = sv.getPointsById(5);
        points.setText(" vous avez "+mespoints+"points.");
        coixConv.getItems().add ("1000=====>5tickets of 1dt");
        coixConv.getItems().add ("5000=====>28 tickets of 1dt");
        coixConv.getItems().add ("10000=====> 60 tickets of 1dt");
        afficherNotifications();
         
    }  
    

    @FXML
    private void Convertir(ActionEvent event) {
        String carnet ="";
        System.out.println("carnet : "+carnet);
        int choix=0;
        if(coixConv.getValue().equals("1000=====>5tickets of 1dt"))
        {
            choix=1000;
            carnet="carnet de 5 tickets de 1dt";
            System.out.println("1000");
        }else if(coixConv.getValue().equals("5000=====>28 tickets of 1dt")){
            choix=5000;
            carnet="carnet de 28 tickets de 1dt";
        }else if (coixConv.getValue().equals("10000=====> 60 tickets of 1dt"))
        {
            choix=10000;
            carnet="carnet de 60 tickets de 1dt";
        }
        if(choix>0)
        {
            sv.ConvertirPoints(carnet, choix, 5);
            int mespoints = sv.getPointsById(5);
            points.setText(" You have "+mespoints+"points.");
        }
        
        
        
    }
    
    
    /******************afiicher Notifications *************************/
    /********************************************************************/
    
    public void afficherNotifications()
    {
        Label v=new Label(" \n");
        root1.getChildren().add(v);
        // VBox root1 = new VBox(10);
        List<NotificationEvent> notif = sv.afficherFront();
        
        root1.setAlignment(Pos.CENTER);
        
        
        if (notif==null){
            Label nul=new Label(" You have no notifications  ");
        root1.getChildren().add(v);
        }
        else{
            for( NotificationEvent e :notif){
            Hyperlink x= new Hyperlink(e.getTitle());
            x.setOnAction(new EventHandler<ActionEvent>() {
                
 
           @Override
           public void handle(ActionEvent event) {
               try {
                   setIdof(e.getTitle());
                   closeButtonAction(event);
                   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Participer.fxml"));      
                   Parent root1 = (Parent) fxmlLoader.load();
                   Stage stage = new Stage();
                     stage.setScene(new Scene(root1));  
                     stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }
               
    }
});
            Label y=new Label("Participate in "+e.getTitle() +" and win " 
                    +e.getDescription() +" points");
            y.setTextFill(Color.WHITE);
            root1.getChildren().add(x);
            root1.getChildren().add(y);
            
            
        }
        
        
            
        }
        Label z=new Label(" \n ");
        root1.getChildren().add(z);
        System.out.println(sv.getEventPart(65));
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

