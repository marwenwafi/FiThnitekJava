/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import fithnitek.models.Reclamation;
import fithnitek.models.Reponse;
import fithnitek.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author yassin
 */
public class ReclamationController implements ReclamationInterface<Reclamation> {
   Connection cnx = DataSource.getInstance().getCnx();
   
   @Override
    public void ajouterReclamation(Reclamation t) {
        try {
            int x=2;
            int y=0;
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String strDate = dateFormat.format(date);
            String requete = "INSERT INTO reclamation (idutilisateur,type,sujet,description,etat,date) VALUES ('" + x + "','" + t.getType() + "','" + t.getSujet() + "','" + t.getDescription() + "','" + y + "','" + strDate + "')";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Reclamation ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerReclamation(Reclamation t) {
        try {
            String requete = "DELETE FROM reclamation WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Reclamation supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifierReclamation(int id) {
        try {
            String requete = "UPDATE reclamation SET etat=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, 1);
            pst.setInt(2, id);
            pst.executeUpdate();
            System.out.println("Reclamation modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Reclamation> afficherReclamation() {
        ObservableList<Reclamation> list=FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM reclamation";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getInt(6),rs.getString(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public ObservableList<Reponse> afficherReponse(int id)
    {
        ObservableList<Reponse> list=FXCollections.observableArrayList();
         try {
            String requete = "SELECT * FROM reponse WHERE id_rec=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reponse(rs.getInt(2),rs.getString(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
        
    }
}

