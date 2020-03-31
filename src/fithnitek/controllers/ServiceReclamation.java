/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FiThnitek.services;

import com.FiThnitek.models.Reclamation;
import com.FiThnitek.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author yassin
 */
public class ServiceReclamation implements IService<Reclamation> {
   Connection cnx = DataSource.getInstance().getCnx();
   
   @Override
    public void ajouterReclamation(Reclamation t) {
        try {
            String requete = "INSERT INTO reclamation (type,sujet,description,etat,date) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String strDate = dateFormat.format(date);
            pst.setString(1, t.getType());
            pst.setString(2, t.getSujet());
            pst.setString(3, t.getDescription());
            pst.setInt(4, t.getEtat());
            pst.setString(5, strDate);
            
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
    public void modifierReclamation(Reclamation t) {
        try {
            String requete = "UPDATE reclamation SET etat=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(2, t.getId());
            pst.setInt(1, 1);
            
            pst.executeUpdate();
            System.out.println("Reclamation modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Reclamation> afficherReclamation() {
        List<Reclamation> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM reclamation";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
}
