/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import fithnitek.controllers.ServiceEvent;
import fithnitek.models.Event;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jdk.internal.dynalink.support.NameCodec;


/**
 * FXML Controller class
 *
 * @author sourour
 */
public class EventQuController implements Initializable {
    

    @FXML
    private TextField titre;
    @FXML
    private DatePicker dateFin;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private TextField url;
    @FXML
    private Button Ajouter;
    @FXML
    private TextField promotion;
    @FXML
    private TextField description;
    @FXML
    private ChoiceBox<String> option;
    @FXML
    private Button image;
    @FXML
    Pagination pagination;
    
    
    
    @FXML
    private TableColumn<Event, String> titreCo;
    @FXML
    private TableColumn<Event, DatePicker> dd;
    @FXML
    private TableColumn<Event, DatePicker> df;
    @FXML
    private TableColumn<Event, String> erco;
    @FXML
    private TableColumn<Event, String> descco;
    @FXML
    private TableColumn<Event, Integer> promoco;
    @FXML
    private TableColumn<Event, String> imgco;
    @FXML
    private TableColumn<Event, String> opeco;
    @FXML
    private TableColumn<Event, String> urlco;
    @FXML
    private TableView<Event> tab;
    String statusClick ,statusCode;
     ServiceEvent sv= new ServiceEvent();
    @FXML
    private TableColumn<Event, Integer> idevent;
    
    private final static int rowsPerPage = 11;
    public static String rq;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //imgco.setCellValueFactory(new StringP);
        titreCo.setCellValueFactory(new PropertyValueFactory<>("titre"));
        dd.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        df.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        erco.setCellValueFactory(new PropertyValueFactory<>("etat"));
        descco.setCellValueFactory(new PropertyValueFactory<>("description"));
        promoco.setCellValueFactory(new PropertyValueFactory<>("promotion"));
        imgco.setCellValueFactory(new PropertyValueFactory<>("image"));
        opeco.setCellValueFactory(new PropertyValueFactory<>("operation"));
        urlco.setCellValueFactory(new PropertyValueFactory<>("url"));
        option.getItems().add("Questionnaire");
        option.getItems().add("Publicité");
        idevent.setCellValueFactory(new PropertyValueFactory<>("id"));
       // Date d=new Date(System.currentTimeMillis());
        //dateDebut.setValue(LocalDate.parse(d.toString());
        //dateFin.setValue(LocalDate.parse(click.getDateFin().toString()));
        afficherEvent();
        tab.getSelectionModel().clearSelection();
        
        
      

    }    

    @FXML
    private void ajouterEvent(ActionEvent event)  {
        
        QuestionnaireQuController c =new QuestionnaireQuController(); 
        Event e=new Event();
        e.setTitre(titre.getText());
        e.setOperation(option.getValue());//opeco.toString()
       e.setPromotion(Integer.parseInt(promotion.getText()));
        e.setImage("");//imgco.toString()
        e.setDescription(description.getText());
        e.setUrl(url.getText());
        e.setDateDebut(Date.valueOf(dateDebut.getValue()));
       e.setDateFin(Date.valueOf(dateFin.getValue()));
       //System.out.println(e.getId());
       // System.out.println(e.getOperation());
       sv.ajouter(e);
       rq=e.getTitre();
       
       if(e.getOperation().equals("Questionnaire"))
       {
            try {
                gestquest(event);
                //c.getIdevent().getItems().add(1);
                
                //System.out.println(sv.getidBytitre(e.getTitre()));
            } catch (Exception ex) {
                Logger.getLogger(EventQuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    
           
       }
        
        afficherEvent();
        
    }

    @FXML
    private void modifierEvent(ActionEvent event) {
        
         Event click = tab.getSelectionModel().getSelectedItems().get(0);
         System.out.println(click.getId());
        
        Event e=new Event();
        e.setId(click.getId());
        e.setTitre(titre.getText());
        e.setOperation(option.getValue());
        e.setPromotion(Integer.parseInt(promotion.getText()));
        e.setImage("image");
        e.setDescription(description.getText());
        e.setUrl(url.getText());
        e.setDateDebut(Date.valueOf(dateDebut.getValue()));
        e.setDateFin(Date.valueOf(dateFin.getValue()));
         System.out.println("update"+e );
         sv.modifier(e);
         afficherEvent();
       
      
    }
     

    @FXML
    private void deleteEvent(ActionEvent event) {
        Event click = tab.getSelectionModel().getSelectedItems().get(0);
        System.out.println("suppression)" +click.getId());
        
        sv.supprimer(click.getId());
        afficherEvent();
        
    }

    @FXML
    private void ChoisirImage(ActionEvent event) {
    }

    private void afficherEvent() {
        int x= (sv.afficher().size() / rowsPerPage + 1);
        pagination.setPageCount(x);
        pagination.setPageFactory((Integer param) ->createPage(param));
        
      
        
    }
    private Node createPage(int pageIndex) {
        System.out.println("je suis ici");
        ObservableList<Event> eventData = FXCollections.observableArrayList();
        List  <Event> l=new ArrayList<Event>();
                l=sv.afficher();
        for(Event e :l){
            eventData.add(e);
        } 
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, eventData.size());
        tab.setItems(FXCollections.observableArrayList(eventData.subList
        (fromIndex, toIndex)
        ));
        System.out.println(tab);

      return  tab;
    }


    @FXML
    private void geteventt(MouseEvent event) {
        
        Event click = tab.getSelectionModel().getSelectedItems().get(0);
        
        System.out.println(click.getTitre());
        titre.setText(click.getTitre());
        dateDebut.setValue(LocalDate.parse(click.getDateDebut().toString()));
        dateFin.setValue(LocalDate.parse(click.getDateFin().toString()));
        description.setText(click.getDescription());
        promotion.setText(""+click.getPromotion());
        url.setText(click.getUrl());
        option.setValue(click.getOperation());
       
        
       String fileName = generateUniqueFileName()+".pdf";
       click.setImage(fileName);
       System.out.println(click.getImage());
       
        
       
           /* file->move($this->getParameter('events_directory'), $fileName);
            event->setImage($fileName);
            event->setEtat("En attente");*/
        
    }
    String generateUniqueFileName() {
    String filename = "";
    long millis = System.currentTimeMillis();
    String datetime = new Date(System.currentTimeMillis()).toGMTString();
    datetime = datetime.replace(" ", "");
    datetime = datetime.replace(":", "");
    double rndchars =Math.random();
    double rndchars1 =Math.random();
    filename = rndchars + "_" + datetime + "_" + millis +"_"+rndchars1;
    return filename;
}

    @FXML
    private void gestquest(ActionEvent event) 
        throws Exception {               
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QuestionnaireQu.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }
}
}
    

