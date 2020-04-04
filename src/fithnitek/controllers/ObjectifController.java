/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import fithnitek.models.Category;
import fithnitek.utils.DataSource;
import fithnitek.models.Objectif;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author marwe
 */
public class ObjectifController {
    
    Connection cnx = DataSource.getInstance().getCnx();
    
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
}
