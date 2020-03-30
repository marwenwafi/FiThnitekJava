/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;
import fithnitek.models.*;

import fithnitek.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author USER
 */
public class ServiceColis implements IColis<Offre_Colis> {
        Connection cnx = DataSource.getInstance().getCnx();

     @Override
    public List<Offre_Colis> afficherOffreColis(int id) {
        List<Offre_Colis> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM offre_colis where   idU = "+id+"";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
     list.add(new Offre_Colis(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getDouble(7),rs.getString(8),rs.getInt(9),rs.getDouble(10)));                       
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
         @Override
     public List<Offre_Colis> afficherAllColis() {
        List<Offre_Colis> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM offre_colis   ";
            PreparedStatement pst = cnx.prepareStatement(requete);
          
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              //  public Offre_Colis(int id_OffreCol, String Date_col, String Lieu_Depart, String Lieu_Arrive, int Prix, double HauteurCol, double LargeurCol, String Voiture, int idU, double LongueurCol) {
     list.add(new Offre_Colis(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getDouble(7),rs.getString(8),rs.getInt(9),rs.getDouble(10)));                       
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
      @Override
    public void ajouterOffreColis(Offre_Colis O) {
        try {
            String requete = "INSERT INTO offre_colis (Date_col,Lieu_Depart,Lieu_Arrive,Prix,Hauteur,Largeur,Voiture,idU,Longueur) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, O.getDate_col());
            pst.setString(2, O.getLieu_Depart());
            pst.setString(3, O.getLieu_Arrive());
            pst.setInt(4,O.getPrix());
            pst.setDouble(5,  O.getHauteurCol()) ; 
            pst.setDouble(6,  O.getLargeurCol());
            pst.setString(7,O.getVoiture());
            pst.setInt(8,O.getIdU());
            pst.setDouble(9,  O.getLongueurCol());
            pst.executeUpdate();
            System.out.println("Offre ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     @Override
    public void supprimerOffreColis(Offre_Colis o) {
          try {
            String requete = "DELETE FROM offre_colis WHERE id_Offre_Col=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, o.getId_OffreCol());
            pst.executeUpdate();
            System.out.println("Offre supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifierOffreColis(Offre_Colis O) {
         try {
            String requete = "UPDATE  offre_colis SET Date_col=?,Lieu_Depart=?,Lieu_Arrive=?,Prix=?,Hauteur=?,Largeur=?,Voiture=?,Longueur=? WHERE id_Offre_Col=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, O.getDate_col());
            pst.setString(2, O.getLieu_Depart());
            pst.setString(3, O.getLieu_Arrive());
            pst.setInt(4,O.getPrix());
            pst.setDouble(5,  O.getHauteurCol()) ; 
            pst.setDouble(6,  O.getLargeurCol());
            pst.setString(7,O.getVoiture());
           // pst.setInt(8,O.getId());
            pst.setDouble(8,  O.getLongueurCol());
            pst.setInt(9,O.getId_OffreCol());

            pst.executeUpdate();
            System.out.println("Offre modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

 

       @Override
    public List<Offre_Colis> RechercheOffreColis(String Date) {
        List<Offre_Colis> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM offre_colis  where  Date_col > "+Date+"  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
     list.add(new Offre_Colis(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getDouble(7),rs.getString(8),rs.getInt(9),rs.getDouble(10)));                       
                     
            }
                               
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
      @Override
    public List<Offre_Colis> RechercheOffreColisDepartArrive(String Depart,String Arrive,int id) {
        List<Offre_Colis> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM offre_colis  where  Lieu_Depart like '%"+Depart+"%' AND Lieu_Arrive like '%"+Arrive+"%' AND idU != "+id+" ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
     list.add(new Offre_Colis(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getDouble(7),rs.getString(8),rs.getInt(9),rs.getDouble(10)));                       
                     
            }
                               
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
        @Override
     public List<Offre_Colis> TrierOffreColis() {
        List<Offre_Colis> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM offre_colis  ORDER BY Date_col ASC   ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
     list.add(new Offre_Colis(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getDouble(7),rs.getString(8),rs.getInt(9),rs.getDouble(10)));                       
                     
            }
                               
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
 

 

    
    
}
