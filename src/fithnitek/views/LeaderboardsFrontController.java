/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import fithnitek.utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import fithnitek.models.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * FXML Controller class
 *
 * @author marwe
 */
public class LeaderboardsFrontController implements Initializable {
    
    Connection cnx = DataSource.getInstance().getCnx();
    LeaderBoardController lb = new LeaderBoardController();
    CategoryController cc = new CategoryController();

    @FXML
    private AnchorPane Pane;
    @FXML
    private JFXTabPane tabs;
    @FXML
    private Label NoLeaders;
    @FXML
    private ImageView banner;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<LeaderBoard> list = lb.afficher();
        if(list.isEmpty())
            NoLeaders.setVisible(true);
        else
        {
            for (LeaderBoard l : list)
            {
                tabs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
                @Override
                public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                    File file = new File("C://wamp64/www/PiDev/web/uploads/banners/"+list.get(tabs.getSelectionModel().getSelectedIndex()).getImage());
                    Image image = new Image(file.toURI().toString());
                    banner.setImage(image);
                    Pane.setStyle("-fx-background-color: #" + list.get(tabs.getSelectionModel().getSelectedIndex()).getColor());
                }
            });
                tabs.getTabs().add(new Tab(l.getTitle(), tablefactory(queryBuilder(cc.findByID(l.getCategory()), l.getSize(), l.getStart_date(), l.getEnd_date()))));
            }
            
            
            //Loop all tabs and add listener to each tab
        }
    }   
    
    public List<LeaderBoardValue> queryBuilder(Category cat,int size,Date start, Date end)
    {
        String query = "";
        List<LeaderBoardValue> l = new ArrayList<LeaderBoardValue>();

        if (cat.getType().equals("Taxi") && cat.getNature().equals("Revenu"))
        {
            query = "select U.username, SUM(D.prix) AS value from demande_taxi D, reservation_taxis R, fos_user U "
                    + "WHERE D.etat=1 AND R.iddemande = D.id AND U.id = R.iduser AND D.date_d BETWEEN '"+start+"' AND '"+end+"' "
                    + "GROUP BY U.id ORDER BY value DESC";
        }
        else if (cat.getType().equals("Taxi") && cat.getNature().equals("Activite"))
        {
            query = "select U.username, COUNT(D.id) AS value from demande_taxi D, reservation_taxis R, fos_user U "
                    + "WHERE D.etat=1 AND R.iddemande = D.id AND U.id = R.iduser AND D.date_d BETWEEN '"+start+"' AND '"+end+"' "
                    + "GROUP BY U.id ORDER BY value DESC";
        }
        else if (cat.getType().equals("Covoiturage") && cat.getNature().equals("Revenu"))
        {
            query = "select U.username, SUM(R.prixt) AS value from offre_covoiturage O, reservation_covoiturage R, fos_user U "
                    + "WHERE R.idoffrer = O.idoffrecovoiturage AND U.id = O.idutilisateur AND O.date BETWEEN '"+start+"' AND '"+end+"' "
                    + "GROUP BY U.id ORDER BY value DESC";
         }
        else if (cat.getType().equals("Covoiturage") && cat.getNature().equals("Activite"))
        {
            query = "select U.username, COUNT(O.idoffrecovoiturage) AS value offre_covoiturage O, fos_user U "
                    + "WHERE O.idutilisateur = U.id AND O.date BETWEEN '"+start+"' AND '"+end+"' "
                    + "GROUP BY U.id ORDER BY value DESC";
        }
        else if (cat.getType().equals("Colis") && cat.getNature().equals("Revenu"))
        {
            query = "select U.username, SUM(R.prix) AS value from offre_colis O, reservation_colis R, fos_user U "
                    + "WHERE R.idOffre = O.id_offre_col AND U.id = O.idU AND O.date_col BETWEEN '"+start+"' AND '"+end+"' "
                    + "GROUP BY U.id ORDER BY value DESC";
        }
        else if (cat.getType().equals("Colis") && cat.getNature().equals("Activite"))
        {
            query = "select U.username, COUNT(O.id_offre_col) AS value from offre_colis O, fos_user U "
                    + "WHERE O.idU = U.id AND O.date_col BETWEEN '"+start+"' AND '"+end+"' "
                    + "GROUP BY U.id ORDER BY value DESC";
        }
        try
        {
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(query);
        int i=1;
        while(rs.next())
        {
            l.add(new LeaderBoardValue(i, rs.getString(1), rs.getInt(2)));
            i++;
        }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return l;
    }

    
    public TableView<LeaderBoardValue> tablefactory(List<LeaderBoardValue> list)
    {
        ObservableList<LeaderBoardValue> data = FXCollections.observableArrayList();
        data.addAll(list);
        
        TableView<LeaderBoardValue> tableview = new TableView<LeaderBoardValue>();
        
        TableColumn<LeaderBoardValue, Number> r = new TableColumn<LeaderBoardValue, Number>("Rank");
        TableColumn<LeaderBoardValue, String> u = new TableColumn<LeaderBoardValue, String>("Username");
        TableColumn<LeaderBoardValue, Number> v = new TableColumn<LeaderBoardValue, Number>("Activity/Revenues");
        
        r.setCellValueFactory(new PropertyValueFactory<>("rank"));
        u.setCellValueFactory(new PropertyValueFactory<>("username"));
        v.setCellValueFactory(new PropertyValueFactory<>("value"));
        
        r.prefWidthProperty().bind(tableview.widthProperty().divide(3));
        u.prefWidthProperty().bind(tableview.widthProperty().divide(3));
        v.prefWidthProperty().bind(tableview.widthProperty().divide(3));
        
        tableview.getColumns().add(r);;
        tableview.getColumns().add(u);
        tableview.getColumns().add(v);
        
        tableview.setItems(data);
        return tableview;
    }
    
}
