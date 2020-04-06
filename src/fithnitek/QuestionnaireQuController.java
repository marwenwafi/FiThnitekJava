/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.cell.PropertyValueFactory;

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
        System.err.println(EventQuController.rq);
        
        
        
    }    

    @FXML
    private void ajouterEvent(ActionEvent event) {
        Questionnaire q=new Questionnaire();
        q.setQuestion(question.getText());
        q.setReponse1(reponse1.getText());
        q.setReponse2(reponse2.getText());
        Event e=sv.getEventBytitre(titre);
        q.setIdevent(e);
        
        sv.ajouter(q);
        afficherQuestionnaire();
    }

    @FXML
    private void modifierEvent(ActionEvent event) {
         Questionnaire click = tab.getSelectionModel().getSelectedItems().get(0);
         System.out.println(click.getId());
        Questionnaire q=new Questionnaire();
        q.setQuestion(question.getText());
        q.setReponse1(reponse1.getText());
        q.setReponse2(reponse2.getText());
        q.setIdevent(click.getIdevent());
        q.setId(click.getId());
        sv.modifier(q);
        afficherQuestionnaire();
        
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

   
     
    
}
