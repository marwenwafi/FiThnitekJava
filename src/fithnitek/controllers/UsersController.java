/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import fithnitek.models.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
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

/**
 * FXML Controller class
 *
 * @author marwe
 */
public class UsersController implements Initializable {

    @FXML
    private JFXButton add;
    @FXML
    private JFXButton modify;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField confirm;
    @FXML
    private JFXTextField surname;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXDatePicker birthdate;
    @FXML
    private JFXButton image;
    @FXML
    private TableView<User> tableview;
    @FXML
    private TableColumn<User, Number> id_c;
    @FXML
    private TableColumn<User, String> username_c;
    @FXML
    private TableColumn<User, String> email_c;
    @FXML
    private TableColumn<User, Number> enabled_c;
    @FXML
    private TableColumn<User, Number> tel_c;
    @FXML
    private TableColumn<User, Number> nbrOffre_c;
    @FXML
    private TableColumn<User, Number> point_c;
    @FXML
    private JFXButton enable;

    private ObservableList<User> data = FXCollections.observableArrayList();
    private UserController uc = new UserController();
    private List<User> list;
    private User rowData;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refresh();
        tableview.setRowFactory( tv -> {
        TableRow<User> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            rowData = row.getItem();
            if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                enableEnableButton();
            }
            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                //loadRowToModify(rowData);
            }
        });
        return row ;
        });
    }    

    @FXML
    private void addObjectif(MouseEvent event) {
        
    }

    @FXML
    private void modifyObjectif(MouseEvent event) {
    }

    @FXML
    private void processKeyEvent(KeyEvent event) {
    }

    @FXML
    private void uploadPicture(MouseEvent event) {
    }

    public void refresh ()
    {tableview.getItems().clear();
        list = uc.afficher();
        data.addAll(list);
        id_c.setCellValueFactory(new PropertyValueFactory<>("id"));
        username_c.setCellValueFactory(new PropertyValueFactory<>("username"));
        email_c.setCellValueFactory(new PropertyValueFactory<>("email"));
        enabled_c.setCellValueFactory(new PropertyValueFactory<>("enabled"));
        tel_c.setCellValueFactory(new PropertyValueFactory<>("tel"));
        nbrOffre_c.setCellValueFactory(new PropertyValueFactory<>("nbroffre"));
        point_c.setCellValueFactory(new PropertyValueFactory<>("points"));
        tableview.setItems(data);
        username.setText("");
        email.setText("");
        password.setText("");
        confirm.setText("");
        surname.setText("");
        tel.setText("");
        birthdate.setValue(null);
        image.setText("Upload picture");
        modify.setDisable(true);
        add.setDisable(false);
        enable.setDisable(true);
    }

    @FXML
    private void enable(MouseEvent event) {
        if (rowData.getEnabled()==0)
            uc.enableDisable(rowData.getUsername(), 1);
        else
            uc.enableDisable(rowData.getUsername(), 0);
        refresh();
    }
    
    private void enableEnableButton() {
        enable.setDisable(false);
    }
    
}
