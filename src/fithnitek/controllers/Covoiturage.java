/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import fithnitek.models.OffreCovoiturage;
import fithnitek.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date; 
/**
 *
 * @author lenovo
 */
public class Covoiturage {
    Connection cnx = DataSource.getInstance().getCnx();

    
    public void ajouteroffrecovoiturage(OffreCovoiturage t) {
        try {
            String requete = "INSERT INTO offre_covoiturage (idutilisateur,destination,depart,date,nbrplaceo,prix,voiture) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getIdutilisateur());
            pst.setString(2, t.getDestination());
            pst.setString(3, t.getDepart());
            pst.setString(4, t.getDate());
            pst.setInt(5, t.getNbrplace());
            pst.setFloat(6, t.getPrix());
            pst.setString(7, t.getVoiture());
            pst.executeUpdate();
            System.out.println("offre ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
public void supprimeroffrecovoiturage(int id) {
        try {
            String requete = "DELETE FROM offre_covoiturage WHERE idoffrecovoiturage=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Offre supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
public void modifieroffrecovoiturage(int id,int idutilisateur,String depart ,String des ,String Date, int nbr , float prix , String voiture) {
        try {
            String requete = "UPDATE offre_covoiturage SET idutilisateur=?,depart=?,destination=?,date=?,nbrplaceo=?,prix=?,voiture=? WHERE idoffrecovoiturage=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(8,id);
            pst.setInt(1,idutilisateur);
            pst.setString(2, depart);
            pst.setString(3, des);
             pst.setString(4, Date);
             pst.setInt(5,nbr);
             pst.setFloat(6,prix);
             pst.setString(7,voiture);
            
            pst.executeUpdate();
            System.out.println("Offre modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
public List<OffreCovoiturage> afficheroffrecovoiturageback() {
        List<OffreCovoiturage> list = new ArrayList<>();

        try {
            String requete = "SELECT * from offre_covoiturage ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new OffreCovoiturage(rs.getInt("idoffrecovoiturage"),rs.getInt("idutilisateur"), rs.getString("destination"), rs.getString("depart"),rs.getString("date"),rs.getInt("nbrplaceo"),rs.getFloat("prix"),rs.getString("voiture")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
public List<OffreCovoiturage> afficherallcovoiturage(int idu) {
        List<OffreCovoiturage> list = new ArrayList<>();
Date actuelle = new Date();
 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 String dat = dateFormat.format(actuelle);
        try {
            String requete = "SELECT * from offre_covoiturage inner join fos_user where offre_covoiturage.idutilisateur = fos_user.id and idutilisateur != ? and nbrplaceo != 0 and date >= ?  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,idu);
            pst.setString(2,dat);
            //pst.setString(2,dat);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new OffreCovoiturage(rs.getInt("idoffrecovoiturage"),rs.getInt("idutilisateur"), rs.getString("destination"), rs.getString("depart"),rs.getString("date"),rs.getInt("nbrplaceo"),rs.getFloat("prix"),rs.getString("voiture"),rs.getString("username"),rs.getInt("tel")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
public List<OffreCovoiturage> affichercovoiturageutilisateur(int idu) {
        List<OffreCovoiturage> list = new ArrayList<>();

        try {
            String requete = "SELECT * from offre_covoiturage where idutilisateur = ? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,idu);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new OffreCovoiturage(rs.getInt("idoffrecovoiturage"),rs.getInt("idutilisateur"), rs.getString("destination"), rs.getString("depart"),rs.getString("date"),rs.getInt("nbrplaceo"),rs.getFloat("prix"),rs.getString("voiture")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
public List<OffreCovoiturage> rechercheoffre(int idu,int nbrplace,String Date,String des ,String depart ) {
        List<OffreCovoiturage> list = new ArrayList<>();

        try {
            String requete = "select * from offre_covoiturage  where nbrplaceo >= ? and idutilisateur != ? and date = ? and destination LIKE ? and depart LIKE ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,nbrplace);
            pst.setInt(2,idu);
            pst.setString(3,Date);
            pst.setString(4,"%"+des+"%");
            pst.setString(5,"%"+depart+"%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new OffreCovoiturage(rs.getInt("idoffrecovoiturage"),rs.getInt("idutilisateur"), rs.getString("destination"), rs.getString("depart"),rs.getString("date"),rs.getInt("nbrplaceo"),rs.getFloat("prix"),rs.getString("voiture")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
public List<OffreCovoiturage> Ordonneroffre(int idu) {
        List<OffreCovoiturage> list = new ArrayList<>();
Date actuelle = new Date();
 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 String dat = dateFormat.format(actuelle);
        try {
            String requete = "SELECT * from offre_covoiturage where idutilisateur != ? and nbrplaceo != 0 and date > ? ORDER BY prix DESC ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setInt(1,idu);
            pst.setString(2,dat);
           
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new OffreCovoiturage(rs.getInt("idoffrecovoiturage"),rs.getInt("idutilisateur"), rs.getString("destination"), rs.getString("depart"),rs.getString("date"),rs.getInt("nbrplaceo"),rs.getFloat("prix"),rs.getString("voiture")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

public List<OffreCovoiturage> Ordonneroffre2(int idu) {
        List<OffreCovoiturage> list = new ArrayList<>();
Date actuelle = new Date();
 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 String dat = dateFormat.format(actuelle);
        try {
            String requete = "SELECT * from offre_covoiturage where idutilisateur != ? and nbrplaceo != 0 and date > ? ORDER BY prix ASC ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setInt(1,idu);
            pst.setString(2,dat);
           
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new OffreCovoiturage(rs.getInt("idoffrecovoiturage"),rs.getInt("idutilisateur"), rs.getString("destination"), rs.getString("depart"),rs.getString("date"),rs.getInt("nbrplaceo"),rs.getFloat("prix"),rs.getString("voiture")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
public void updateplacesoffrecovoiturage(int id,int nbr) {
        try {
            String requete = "UPDATE offre_covoiturage SET nbrplaceo=? WHERE idoffrecovoiturage=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(2,id);
            
           
             pst.setInt(1,nbr);
             
            
            pst.executeUpdate();
            System.out.println("Offre modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


}
