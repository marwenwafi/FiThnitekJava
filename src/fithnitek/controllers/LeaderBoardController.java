/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import fithnitek.models.LeaderBoard;
import fithnitek.utils.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 *
 * @author marwe
 */
public class LeaderBoardController {
    
    private String ImageFile = "";
    @FXML
    private AnchorPane Pane;
    
    
    
     Connection cnx = DataSource.getInstance().getCnx();
    
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
        System.out.println(selectedFile.getName());
    }
    
}
