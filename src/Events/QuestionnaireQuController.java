/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import fithnitek.controllers.ServiceQuestionnaire;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import fithnitek.models.Questionnaire;
import fithnitek.models.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author sourour
 */
public class QuestionnaireQuController implements Initializable {

    @FXML
    private TextField reponse2;
    @FXML
    private Button Ajouter;
    @FXML
    private TextField reponse1;
    @FXML
    private TableView<Questionnaire> tab;
    @FXML
    private Pagination pagination;
    @FXML
    private TextArea question;
    @FXML
    private TableColumn<Questionnaire, String> questionco;
    @FXML
    private TableColumn<Questionnaire, String> rep1;
    @FXML
    private TableColumn<Questionnaire, String> rep2;
    @FXML
    private TableColumn<Questionnaire, Integer> idev;
    @FXML
    private TableColumn<Questionnaire, Integer> idquest;
    ServiceQuestionnaire sv= new ServiceQuestionnaire();
    private final static int rowsPerPage = 11;
    public  String titre;
    @FXML
    private TextField idevvv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        questionco.setCellValueFactory(new PropertyValueFactory<>("question"));
        rep1.setCellValueFactory(new PropertyValueFactory<>("reponse1"));
        rep2.setCellValueFactory(new PropertyValueFactory<>("reponse2"));
        idev.setCellValueFactory(new PropertyValueFactory<>("idevent"));
        idquest.setCellValueFactory(new PropertyValueFactory<>("id"));
        afficherQuestionnaire();
        tab.getSelectionModel().clearSelection();
        titre=EventQuController.rq;
        getEvent();
        System.err.println(EventQuController.rq);
        
        
        
    }    
    public void getEvent()
    {
        Event e=sv.getEventBytitre(titre);
        System.err.println(e.getId());
        idevvv.setText(""+e.getId());
        
    }

    @FXML
    private void ajouterEvent(ActionEvent event) {
       // try{
        Event e=sv.getEventBytitre(titre);
        Questionnaire q=new Questionnaire();
        q.setQuestion(question.getText());
        q.setReponse1(reponse1.getText());
        q.setReponse2(reponse2.getText());
        q.setIdevent(e);
        if(verifier("Vous devez remlir tous les champs !")==true){
           sv.ajouter(q);
        afficherQuestionnaire(); 
        }
        
       /* }catch(NullPointerException e)
        {
            verifier("Vous devez remlir tous les champs !");
        }*/
        
    }

    @FXML
    private void modifierEvent(ActionEvent event) {
        Questionnaire click = tab.getSelectionModel().getSelectedItems().get(0);
        
            
        
         System.out.println(click.getId());
         System.out.println(click.getId());
        Questionnaire q=new Questionnaire();
        q.setQuestion(question.getText());
        q.setReponse1(reponse1.getText());
        q.setReponse2(reponse2.getText());
        q.setIdevent(click.getIdevent());
        
        q.setId(click.getId());
        if(verifier("Vous devez remlir tous les champs !")==true){
            sv.modifier(q);
        afficherQuestionnaire();
        }
        
        
        
    }

    @FXML
    private void deleteEvent(ActionEvent event) {
        Questionnaire click = tab.getSelectionModel().getSelectedItems().get(0);
        System.out.println("suppression)" +click.getId());
        
        sv.supprimer(click.getId());
        afficherQuestionnaire();
    }

    @FXML
    private void geteventt(MouseEvent event) {
         Questionnaire click = tab.getSelectionModel().getSelectedItems().get(0);
        
       
        question.setText(click.getQuestion());
        reponse1.setText(click.getReponse1());
        reponse2.setText(click.getReponse2());
        idevvv.setText(""+click.getIdevent().getId());
       
        
       
    }
    
    private void afficherQuestionnaire() {
        int x= (sv.afficher().size() / rowsPerPage + 1);
        pagination.setPageCount(x);
        pagination.setPageFactory((Integer param) ->createPage(param));
        
      
        
    }
    
     private Node createPage(int pageIndex) {
        System.out.println("je suis ici");
         ObservableList<Questionnaire> qData = FXCollections.observableArrayList();
         List  <Questionnaire> l=new ArrayList<Questionnaire>();
                l=sv.afficher();
        for(Questionnaire e :l){
            
            qData.add(e);
        } 
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, qData.size());
        tab.setItems(FXCollections.observableArrayList(qData.subList
        (fromIndex, toIndex)
        ));
        System.out.println(tab);

      return  tab;
    }
     
     
     
     public boolean verifier(String s){
        if(!s.equals(""))
        {
            if(question.getText().equals("")|| reponse1.getText().equals("") 
                    || reponse2.getText().equals("") )
            {
            Alert alert = new Alert(Alert.AlertType.ERROR,s);
        alert.initStyle(StageStyle.DECORATED.UTILITY);
        alert.setTitle("Attention");
        alert.showAndWait();
        return false;
        }
        }
        if(question.getText().length()<5)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Le champ question doit contient au mois 5 caractères ");
        alert.initStyle(StageStyle.DECORATED.UTILITY);
        alert.setTitle("Attention");
        alert.showAndWait();
        return false;
            }
        if(idevvv.getText().equals("0") || idevvv.getText().equals(0))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"il faut ajouter un évènement d'abord");
        alert.initStyle(StageStyle.DECORATED.UTILITY);
        alert.setTitle("Attention");
        alert.showAndWait();
        return false;
            
        }
        return true;
        
     }

    @FXML
    private void back(ActionEvent event) {
        closeButtonAction(event);
        try {
            toFront(event);
        } catch (Exception ex) {
            Logger.getLogger(QuestionnaireQuController.class.getName()).log(Level.SEVERE, null, ex);
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
