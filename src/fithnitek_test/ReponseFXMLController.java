/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek_test;

import fithnitek.controllers.ReponseController;
import fithnitek.models.Reclamation;
import fithnitek.models.Reponse;
import fithnitek.models.TwilloSms;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author yassin
 */
public class ReponseFXMLController implements Initializable {

    private Label label;
    @FXML
    private TableView<Reclamation> tabrec;
    @FXML
    private TextArea reponse;
    @FXML
    private Button repondre;
    @FXML
    private TableColumn<Reclamation, String> idRec;
    @FXML
    private TableColumn<Reclamation, String> idUser;
    @FXML
    private TableColumn<Reclamation, String> type;
    @FXML
    private TableColumn<Reclamation, String> sujet;
    @FXML
    private TableColumn<Reclamation, String> desc;
    @FXML
    private TableColumn<Reclamation, String> etat;
    @FXML
    private TableColumn<Reclamation, String> date;
    
    ObservableList<Reclamation> oblist=FXCollections.observableArrayList();
    ReponseController rc= new ReponseController();
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idRec.setCellValueFactory(new PropertyValueFactory<>("id"));
        idUser.setCellValueFactory(new PropertyValueFactory<>("idUtilisateur"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        oblist = rc.afficherReclamation();
        tabrec.setItems(oblist);
        
        
        FilteredList<Reclamation> filteredData = new FilteredList<>(rc.afficherReclamation(), b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(reclamation -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (reclamation.getSujet().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (reclamation.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                else if (reclamation.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(reclamation.getIdUtilisateur()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tabrec.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tabrec.setItems(sortedData);
    }    

    @FXML
    private void repondre(ActionEvent event) {
        String rep=reponse.getText();
        Reclamation R=tabrec.getSelectionModel().getSelectedItem();
        Reponse Rr= new Reponse(R.getId(),rep);
        rc.ajouterReponse(Rr);
       
        TwilloSms sms =new TwilloSms();
        sms.sendSms("id:"+Rr.getIdRec()+" Reply from admin:"+Rr.getReply());
        oblist = rc.afficherReclamation();
        tabrec.setItems(oblist);
    }
    
    
}
