/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import com.jfoenix.controls.*;
import fithnitek.utils.DataSource;
import fithnitek.models.Category;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author marwe
 */
public class CategoryController implements Initializable {
    
    Connection cnx = DataSource.getInstance().getCnx();
    Category rowData;
    
    @FXML
    private TableView<Category> tableview;
    @FXML
    private TableColumn<Category, Number> id_c;
    @FXML
    private TableColumn<Category, String> title_c;
    @FXML
    private TableColumn<Category, String> desc_c;
    @FXML
    private TableColumn<Category, String> type_c;
    @FXML
    private TableColumn<Category, String> nature_c;
    @FXML
    private JFXTextField title;
    @FXML
    private JFXComboBox<String> type;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXComboBox<String> nature;
    @FXML
    private JFXButton add;
    @FXML
    private JFXButton modify;
    @FXML
    private JFXButton delete;
    
    private ObservableList<Category> data = FXCollections.observableArrayList();
    List<Category> list;
       
    
    
    public void ajouter(Category c) {
        try {            
            String requete = "INSERT INTO Category (title, description, type, nature) "
                    + "VALUES ('" +c.getTitle()+"','" +c.getDescription()+"','" + c.getType() + "','"+ c.getNature()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Category ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimer(Category c) {
        try {
            String requete = "DELETE FROM category WHERE id_category=" + c.getId_Category();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Category supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifier(Category c) {
        try {
            String requete = "UPDATE category SET title='" + c.getTitle()+ "',description='" + c.getDescription()+ "',type='" + c.getType()+ "',"
                    + "nature='"+ c.getNature()+"' WHERE id_category=" + c.getId_Category();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Category modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    

    public List<Category> afficher() {
        List<Category> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM category";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Category (rs.getInt("id_category"), rs. getString("title"),rs.getString("description"),rs.getString("type"),rs.getString("nature")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public Category findByID(int id) {
        Category c = null;
        try {
            String requete = "SELECT * FROM category where id_category="+id+";";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            rs.next();
                c = new Category(rs.getInt("id_category"), rs.getString("title"),rs.getString("description"),rs.getString("type"),rs.getString("nature"));

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return c;
    }
    
    
    
    @FXML
    private void addCategory(MouseEvent event) throws Exception {
        Category c = new Category(title.getText(), description.getText(), type.getValue().toString(), nature.getValue().toString());
        ajouter(c);
        refresh();
    }
    
    @FXML
    private void deleteCategory(MouseEvent event) throws Exception {
        Category c = tableview.getSelectionModel().getSelectedItem();
        supprimer(c);
        refresh();
    }
    
    @FXML
    private void modifyCategory(MouseEvent event) throws Exception {
        rowData.setTitle(title.getText());
        rowData.setDescription(description.getText());
        rowData.setType(type.getValue());
        rowData.setNature(nature.getValue());
        modifier(rowData);
        refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
        tableview.setRowFactory( tv -> {
        TableRow<Category> row = new TableRow<>();
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
    
    
    
    public void loadRowToModify(Category c)
    {
        title.setText(c.getTitle());
        description.setText(c.getDescription());
        type.getSelectionModel().select(c.getType());
        nature.getSelectionModel().select(c.getNature());
        modify.setDisable(false);
        add.setDisable(true);
    }
    
    public void refresh ()
    {tableview.getItems().clear();
        list = this.afficher();
        data.addAll(list);
        id_c.setCellValueFactory(new PropertyValueFactory<>("id_Category"));
        title_c.setCellValueFactory(new PropertyValueFactory<>("title"));
        desc_c.setCellValueFactory(new PropertyValueFactory<>("description"));
        type_c.setCellValueFactory(new PropertyValueFactory<>("type"));
        nature_c.setCellValueFactory(new PropertyValueFactory<>("nature"));
        tableview.setItems(data);
        title.setText("");
        description.setText("");
        type.getSelectionModel().select(0);
        nature.getSelectionModel().select(0);
        modify.setDisable(true);
        add.setDisable(false);
        delete.setDisable(true);
    }

    private void enableDelete() {
        delete.setDisable(false);
    }
}
