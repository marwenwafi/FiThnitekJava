/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import fithnitek.controllers.ServiceNotificationEvent;
import fithnitek.models.Cadeaux;
import fithnitek.models.NotificationEvent;
import fithnitek.models.ParticipeEvent;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sourour
 */
public class NotificationetfidController implements Initializable {

    @FXML
    private TableView<NotificationEvent> notifications;
    @FXML
    private TableColumn<String, String> titre;
    @FXML
    private TableColumn<String, String> desc;
    @FXML
    private TableColumn<Date, Date> dateNotification;
    @FXML
    private Pagination pagination;
    @FXML
    private TableView<ParticipeEvent> participants;
    @FXML
    private TableColumn<String, String> eventTitle;
    @FXML
    private TableColumn<String, String> username;
    @FXML
    private Pagination pagination1;
    @FXML
    private TableView<Cadeaux> cadeauxx;
    @FXML
    private TableColumn<String, String> userid;
    @FXML
    private TableColumn<String, String> cadeaux;
    @FXML
    private Pagination pagination2;
    
    public ServiceNotificationEvent sv= new ServiceNotificationEvent();
    public final int rowsPerPage=9;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titre.setCellValueFactory(new PropertyValueFactory<>("title"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateNotification.setCellValueFactory(new PropertyValueFactory<>("notification_date"));
        
        eventTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        
        userid.setCellValueFactory(new PropertyValueFactory<>("username"));
        cadeaux.setCellValueFactory(new PropertyValueFactory<>("cadeaux"));
        
        afficher();
        
    }    
    
     private Node createPage1(int pageIndex) {
        System.out.println("notifications");
         ObservableList<NotificationEvent> qData = FXCollections.observableArrayList();
         List  <NotificationEvent> l=new ArrayList<NotificationEvent>();
                l=sv.afficher();
        for(NotificationEvent e :l){
            
            qData.add(e);
        } 
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, qData.size());
        notifications.setItems(FXCollections.observableArrayList(qData.subList
        (fromIndex, toIndex)
        ));
       return  notifications;
      
    }
     private Node createPage2(int pageIndex) {
        System.out.println("Participants");
        ObservableList<ParticipeEvent> sData = FXCollections.observableArrayList();
         List  <ParticipeEvent> ls=new ArrayList<ParticipeEvent>();
                ls=sv.Participants();
        for(ParticipeEvent e :ls){
            
            sData.add(e);
        }
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex2 = Math.min(fromIndex + rowsPerPage, sData.size());
        participants.setItems(FXCollections.observableArrayList(sData.subList
        (fromIndex, toIndex2)
        ));
      return  participants; 
    }
     private Node createPage3(int pageIndex) {
        System.out.println("Cadeaux");
        ObservableList<Cadeaux> sData = FXCollections.observableArrayList();
         List  <Cadeaux> ls=new ArrayList<Cadeaux>();
                ls=sv.Cadeaux();
        for(Cadeaux e :ls){
            
            sData.add(e);
        }
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex2 = Math.min(fromIndex + rowsPerPage, sData.size());
        cadeauxx.setItems(FXCollections.observableArrayList(sData.subList
        (fromIndex, toIndex2)
        ));
      return  cadeauxx; 
    }

    @FXML
    private void geteventt(MouseEvent event) {
    }
    public void afficher()
    {
        int x= (sv.afficher().size() / rowsPerPage + 1);
        pagination.setPageCount(x);
        pagination.setPageFactory((Integer param) ->createPage1(param));
        
        int x2= (sv.Participants().size() / rowsPerPage + 1);
        pagination1.setPageCount(x2);
        pagination1.setPageFactory((Integer param) ->createPage2(param));
        
        int x3= (sv.Cadeaux().size() / rowsPerPage + 1);
        pagination2.setPageCount(x3);
        pagination2.setPageFactory((Integer param) ->createPage3(param));
        
    }

    @FXML
    private void back(ActionEvent event) {
        closeButtonAction(event);
        try {
            toFront(event);
        } catch (Exception ex) {
            Logger.getLogger(NotificationetfidController.class.getName()).log(Level.SEVERE, null, ex);
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
