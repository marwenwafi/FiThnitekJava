/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek_test;

import fithnitek.controllers.EvaluationController;
import fithnitek.controllers.MainMenuController;
import fithnitek.models.Evaluation;
import fithnitek.models.User;
import fithnitek.utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author yassin
 */
public class EvaluationFXMLController implements Initializable {



    EvaluationController ec=new EvaluationController();
    private Label label;
    @FXML
    private TextArea comment;
    @FXML
    private Rating rate;
    @FXML
    private TableView<Evaluation> tabRating;
    @FXML
    private TableColumn<Evaluation, String> id;
    @FXML
    private TableColumn<Evaluation, String> idu;
    @FXML
    private TableColumn<Evaluation, String> note;
    @FXML
    private TableColumn<Evaluation, String> commentaire;
    ObservableList<Evaluation> oblist=FXCollections.observableArrayList();
    @FXML
    private ProgressBar Pfive;
    @FXML
    private ProgressBar Pone;
    @FXML
    private ProgressBar Ptwo;
    @FXML
    private ProgressBar Pthree;
    @FXML
    private ProgressBar Pfour;
    @FXML
    private Label totalreviw;
    @FXML
    private Label moy;
    @FXML
    private Button submit;
    @FXML
    private AnchorPane back;
    
    User user;
    
    Connection cnx = DataSource.getInstance().getCnx();
    ObservableList<User> list;
    @FXML
    private ComboBox<String> userliste;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /////////users
        MainMenuController mmc = new MainMenuController();
        user = mmc.getCurrentUser();
        
        list =FXCollections.observableArrayList();
        ObservableList<String> listUserName =FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM fos_user";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("id"),rs.getString("username")));
                listUserName.add(rs.getString("username"));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        userliste.setItems(listUserName);
        /////////tableview
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        idu.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        note.setCellValueFactory(new PropertyValueFactory<>("note"));
        commentaire.setCellValueFactory(new PropertyValueFactory<>("comment"));
        oblist = ec.afficherRating(user.getId());
        tabRating.setItems(oblist);
        
        ////stats
        Connection cnx = DataSource.getInstance().getCnx();
        int count = 0;
        int five=0;
        int four=0;
        int three=0;
        int two=0;
        int one=0;
        int total=0;
        
        try {
            String requete = "SELECT * FROM evaluation where idutilisateur=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, user.getId());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                count++;
                total+=rs.getInt(4);
                switch (rs.getInt(4)) {
                    case 5:
                        five++;
                        break;
                    case 4:
                        four++;
                        break;
                    case 3:
                        three++;
                        break;
                    case 2:
                        two++;
                        break;
                    case 1:
                        one++;
                        break;
                    default:
                        break;
                }
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        Pfive.setProgress((float)five/count);
        Pfour.setProgress((float)four/count);
        Pthree.setProgress((float)three/count);
        Ptwo.setProgress((float)two/count);
        Ptwo.setProgress((float)two/count);
        Pone.setProgress((float)one/count);
        
        float average=(float)total/count;
        moy.setText("Average:"+String.valueOf(average));
        totalreviw.setText("total reviews: "+String.valueOf(count));
        
    }    

    @FXML
    private void ratePerson(ActionEvent event) {
        int r=(int)rate.getRating();
       int i =list.get(userliste.getSelectionModel().getSelectedIndex()).getId();
        Evaluation e=new Evaluation(i,user.getId(),r,comment.getText());
        ec.rate(e);
        oblist = ec.afficherRating(user.getId());
        tabRating.setItems(oblist);
        
        ///////refresh
        
        int count = 0;
        int five=0;
        int four=0;
        int three=0;
        int two=0;
        int one=0;
        int total=0;
        
        try {
            String requete = "SELECT * FROM evaluation where idutilisateur=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, user.getId());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                count++;
                total+=rs.getInt(4);
                switch (rs.getInt(4)) {
                    case 5:
                        five++;
                        break;
                    case 4:
                        four++;
                        break;
                    case 3:
                        three++;
                        break;
                    case 2:
                        two++;
                        break;
                    case 1:
                        one++;
                        break;
                    default:
                        break;
                }
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        Pfive.setProgress((float)five/count);
        Pfour.setProgress((float)four/count);
        Pthree.setProgress((float)three/count);
        Ptwo.setProgress((float)two/count);
        Ptwo.setProgress((float)two/count);
        Pone.setProgress((float)one/count);
        
        float average=(float)total/count;
        moy.setText("Average:"+String.valueOf(average));
        totalreviw.setText("total reviews: "+String.valueOf(count)); 
        oblist = ec.afficherRating(user.getId());
        tabRating.setItems(oblist);
    }
    
}
