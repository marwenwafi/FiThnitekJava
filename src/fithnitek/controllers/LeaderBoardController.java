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
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import fithnitek.models.*;
import fithnitek.utils.DataSource;
import java.io.File;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 *
 * @author marwe
 */
public class LeaderBoardController implements Initializable {
    
    Connection cnx = DataSource.getInstance().getCnx();
    
    private String ImageFile = "";
    @FXML
    private AnchorPane Pane;
    
    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXComboBox<Category> category;
    @FXML
    private JFXButton add;
    @FXML
    private JFXButton modify;
    @FXML
    private JFXTextField size;
    @FXML
    private JFXColorPicker color;
    @FXML
    private JFXButton banner;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXDatePicker start_date;
    @FXML
    private JFXDatePicker end_date;
    @FXML
    private TableView<LeaderBoard> tableview;
    @FXML
    private TableColumn<LeaderBoard, String> title_c;
    @FXML
    private TableColumn<LeaderBoard, String> desc_c;
    @FXML
    private TableColumn<LeaderBoard, Number> size_c;
    @FXML
    private TableColumn<LeaderBoard, Date> start_c;
    @FXML
    private TableColumn<LeaderBoard, Date> end_c;
    @FXML
    private TableColumn<LeaderBoard, Category> category_c;
    
    
    private ObservableList<LeaderBoard> data = FXCollections.observableArrayList();
    private ObservableList<Category> data2;
    List<LeaderBoard> list;
    LeaderBoard rowData;
    CategoryController cc = new CategoryController();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    
    public void ajouter(LeaderBoard l) {
        try {            
            String requete = "INSERT INTO leader_board (category, title, description, size, start_date, end_date, color, image) "
                    + "VALUES ('" +l.getCategory()+"','"+l.getTitle()+"','" +l.getDescription()+"','" + l.getSize() + "','"+ l.getStart_date()+"',"
                    + "'"+l.getEnd_date()+"','"+l.getColor()+"','"+l.getImage()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("LeaderBoard ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimer(LeaderBoard l) {
        try {
            String requete = "DELETE FROM leader_board WHERE idleaderboard=" + l.getIdleaderboard();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("LeaderBoard supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifier(LeaderBoard l) {
        try {
            String requete = "UPDATE leader_board SET category='"+l.getCategory()+"', title='" + l.getTitle()+ "',description='" + l.getDescription()+
                    "',size='" + l.getSize()+ "', start_date='"+ l.getStart_date()+"', end_date='"+l.getEnd_date()+"', color='"+l.getColor()+"'"
                    + ", image= '"+l.getImage()+"' WHERE idleaderboard=" + l.getIdleaderboard();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("LeaderBoard modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    

    public List<LeaderBoard> afficher() {
        List<LeaderBoard> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM leader_board";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new LeaderBoard (rs.getInt("idleaderboard"), rs. getString("title"),rs.getString("description"),
                        rs.getInt("size"),rs.getDate("start_date"),rs.getDate("end_date"),rs.getInt("category"),rs.getString("color"),rs.getString("image")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    
    @FXML
    private void addLeader(MouseEvent event) throws Exception {
        LeaderBoard l = new LeaderBoard(title.getText(), description.getText(), Integer.parseInt(size.getText()),
                java.sql.Date.valueOf(start_date.getValue()), java.sql.Date.valueOf(end_date.getValue()), category.getValue().getId_Category(), color.getValue().toString().substring(0, 8), ImageFile);
        ajouter(l);
        refresh();
    }
    
    @FXML
    private void deleteLeader(MouseEvent event) throws Exception {
        LeaderBoard l = tableview.getSelectionModel().getSelectedItem();
        supprimer(l);
        refresh();
    }
    
    @FXML
    private void modifyLeader(MouseEvent event) throws Exception {
        rowData.setTitle(title.getText());
        rowData.setDescription(description.getText());
        rowData.setSize(Integer.parseInt(size.getText()));
        rowData.setStart_date(java.sql.Date.valueOf(start_date.getValue()));
        rowData.setEnd_date(java.sql.Date.valueOf(end_date.getValue()));
        //Category c = cc.findByID(Integer.parseInt(category.getText()));
        //rowData.setCategory(category.getValue());
        modifier(rowData);
        refresh();
    }
    
    public void refresh ()
    {tableview.getItems().clear();
        list = this.afficher();
        data.addAll(list);
        title_c.setCellValueFactory(new PropertyValueFactory<>("title"));
        desc_c.setCellValueFactory(new PropertyValueFactory<>("description"));
        size_c.setCellValueFactory(new PropertyValueFactory<>("size"));
        start_c.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        end_c.setCellValueFactory(new PropertyValueFactory<>("end_date"));
        category_c.setCellValueFactory(new PropertyValueFactory<>("category"));
        tableview.setItems(data);
        title.setText("");
        description.setText("");
        size.setText("");
        ImageFile = "";
        banner.setText("add banner");
        start_date.setValue(null);
        end_date.setValue(null);
        color.setValue(null);
        modify.setDisable(true);
        add.setDisable(false);
        delete.setDisable(true);

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
    
    @FXML
    private void uploadPicture(MouseEvent event) throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pictures", "*.png"),
                new FileChooser.ExtensionFilter("Pictures", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(Pane.getScene().getWindow());
        ImageFile = selectedFile.getName();
        banner.setText(ImageFile);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
        data2 = FXCollections.observableArrayList(cc.afficher());
        category.getItems().addAll(data2);
        tableview.setRowFactory(tv -> {
        TableRow<LeaderBoard> row = new TableRow<>();
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
    
    public void loadRowToModify(LeaderBoard l)
    {
        title.setText(l.getTitle());
        description.setText(l.getDescription());
        category.getSelectionModel().select(l.getCategory());
        LocalDate mm = LocalDate.parse(l.getStart_date().toString(), formatter);
        start_date.setValue(mm);
        mm = LocalDate.parse(l.getEnd_date().toString(), formatter);
        end_date.setValue(mm);
        size.setText(""+l.getSize());
        modify.setDisable(false);
        add.setDisable(true);
    }
    
    private void enableDelete() {
        delete.setDisable(false);
    }
    
}
