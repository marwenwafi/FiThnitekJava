package fithnitek.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import fithnitek.models.Category;
import fithnitek.utils.DataSource;
import fithnitek.models.Objectif;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author marwe
 */
public class ObjectifController implements Initializable {
    
    Connection cnx = DataSource.getInstance().getCnx();
    
    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXButton add;
    @FXML
    private JFXButton modify;
    @FXML
    private JFXDatePicker start_date;
    @FXML
    private JFXDatePicker end_date;
    @FXML
    private JFXTextField but;
    @FXML
    private JFXComboBox<String> type;
    @FXML
    private JFXButton delete;
    @FXML
    private Pane details;
    @FXML
    private TableView<Objectif> tableview;
    @FXML
    private TableColumn<Objectif, Number> id_c;
    @FXML
    private TableColumn<Objectif, String> title_c;
    @FXML
    private TableColumn<Objectif, String> desc_c;
    @FXML
    private TableColumn<Objectif, Number> but_c;
    @FXML
    private TableColumn<Objectif, Date> start_c;
    @FXML
    private TableColumn<Objectif, Date> end_c;
    @FXML
    private TableColumn<Objectif, String> type_c;
    
    private ObservableList<Objectif> data = FXCollections.observableArrayList();
    List<Objectif> list;
    Objectif rowData;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public void ajouter(Objectif o) {
        try {            
            String requete = "INSERT INTO Objectif (titre, description, type, but, etat, start_date, end_date) "
                    + "VALUES ('" +o.getTitre()+"','" +o.getDescription()+"','" + o.getType() + "','"+ o.getBut()+"',"
                    + "'"+o.getEtat()+"','"+o.getStart_date()+"','"+o.getEnd_date()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Objectif ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimer(Objectif o) {
        try {
            String requete = "DELETE FROM objectif WHERE id_objectif=" + o.getIdOjectif();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Objectif supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifier(Objectif o) {
        try {
            String requete = "UPDATE objectif SET titre='" + o.getTitre()+ "',description='" + o.getDescription()+ "',type='" + o.getType()+ "',"
                    + "but='"+ o.getBut()+"',etat='"+o.getEtat()+"',start_date='"+o.getStart_date()+"',end_date='"+o.getEnd_date()+"' WHERE id_objectif=" + o.getIdOjectif();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Objectif modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    

    public List<Objectif> afficher() {
        List<Objectif> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM objectif";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Objectif (rs.getInt("id_objectif"), rs.getString("titre"),rs.getString("description"),rs.getString("type"),
                        rs.getInt("but"),rs.getByte("etat"),rs.getDate("start_date"),rs.getDate("end_date")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    @FXML
    public void processKeyEvent(KeyEvent ev) {
        System.out.println("proce");
        String c = ev.getCharacter();
        if("1234567890".contains(c)) {}
        else {
            ev.consume();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
        tableview.setRowFactory( tv -> {
        TableRow<Objectif> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            rowData = row.getItem();
            if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                enableDelete();
            }
            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                loadRowToModify(rowData);
            }
        });
        return row ;
        });
    }
    
    @FXML
    private void addObjectif(MouseEvent event) throws Exception {
        Objectif c = new Objectif(title.getText(), description.getText(), type.getValue().toString(), Integer.parseInt(but.getText()),new Byte("0"),
                java.sql.Date.valueOf(start_date.getValue()), java.sql.Date.valueOf(end_date.getValue()));
        ajouter(c);
        refresh();
    }
    
    @FXML
    private void modifyObjectif(MouseEvent event) throws Exception {
        rowData.setTitre(title.getText());
        rowData.setDescription(description.getText());
        rowData.setBut(Integer.parseInt(but.getText()));
        rowData.setType(type.getValue());
        rowData.setStart_date(java.sql.Date.valueOf(start_date.getValue()));
        rowData.setEnd_date(java.sql.Date.valueOf(end_date.getValue()));
        modifier(rowData);
        refresh();
    }
        
    @FXML
    private void deleteObjectif(MouseEvent event) throws Exception {
        Objectif c = tableview.getSelectionModel().getSelectedItem();
        supprimer(c);
        refresh();
    }
    
    public void loadRowToModify(Objectif o)
    {
        title.setText(o.getTitre());
        description.setText(o.getDescription());
        type.getSelectionModel().select(o.getType());
        LocalDate mm = LocalDate.parse(o.getStart_date().toString(), formatter);
        start_date.setValue(mm);
        mm = LocalDate.parse(o.getEnd_date().toString(), formatter);
        end_date.setValue(mm);
        but.setText(""+o.getBut());
        modify.setDisable(false);
        add.setDisable(true);
    }
    
    public void refresh ()
    {tableview.getItems().clear();
        list = this.afficher();
        data.addAll(list);
        id_c.setCellValueFactory(new PropertyValueFactory<>("idOjectif"));
        title_c.setCellValueFactory(new PropertyValueFactory<>("titre"));
        desc_c.setCellValueFactory(new PropertyValueFactory<>("description"));
        start_c.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        end_c.setCellValueFactory(new PropertyValueFactory<>("end_date"));
        type_c.setCellValueFactory(new PropertyValueFactory<>("type"));
        but_c.setCellValueFactory(new PropertyValueFactory<>("but"));
        tableview.setItems(data);
        title.setText("");
        description.setText("");
        but.setText("");
        type.getSelectionModel().select(0);
        start_date.setValue(null);
        end_date.setValue(null);
        modify.setDisable(true);
        add.setDisable(false);
        delete.setDisable(true);
    }
    
    
     private void enableDelete() {
        delete.setDisable(false);
    }    
}
