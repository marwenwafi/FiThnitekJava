/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;


import fithnitek.models.ReservationTaxi;
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
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Rawis
 */
public class ServiceReservationTaxi implements  IService <ReservationTaxi> {
  Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(ReservationTaxi r) {
         try {
            String requete = "INSERT INTO `reservation_taxis` ( `iduser`, `iddemande`)  VALUES (?, ?) ";
            PreparedStatement pst = cnx.prepareStatement(requete);
           pst.setInt(1, r.getIduser());
         
                   pst.setInt(2, r.getIddemande());
             
            pst.executeUpdate();
            System.out.println("Reservation taxi  ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
         try {
            String requete = "DELETE FROM reservation_taxis WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Reservation taxi supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(ReservationTaxi t, int id) {
         try {
            String requete = "UPDATE reservation_taxis  SET iduser=?, iddemande=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(3, id);
               pst.setInt(1, t.getIduser());
                  pst.setInt(2, t.getIddemande());
            pst.executeUpdate();
            System.out.println("Demande taxi modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<ReservationTaxi> afficher() {
         List<ReservationTaxi> list = new ArrayList<>();
         try {
            String requete = "SELECT * FROM reservation_taxis ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new ReservationTaxi(rs.getInt(2),rs.getInt(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
    
    }
     Date actuelle = new Date();
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
           String dat = dateFormat.format(actuelle);
    
    public List<ReservationTaxi> affichertaxi(int id) {
              List<ReservationTaxi> list = new ArrayList<>();
//int iduser, int iddemande, int id, float prix, String lieudepart, String lieuarrive, String region, String periode, String dated, String username
        try {//INNER JOIN fos_user on  reservation_taxis.idduser=fos_user.id=?  INNER JOIN (SELECT * from demande_taxi INNER JOIN  reservation_taxis.iddemande=demande_taxi.id 
            //INNER JOIN fos_user U ON D.iduser=U.id
            String requete = "SELECT * FROM reservation_taxis  R INNER JOIN  demande_taxi D ON  R.iddemande=D.id  INNER JOIN fos_user U ON D.iduser=U.id  where R.iduser=? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              list.add(new ReservationTaxi(rs.getInt(3),rs.getInt(2),rs.getInt("id"),rs.getFloat(12),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString("username")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
    }
    
    /*
        public Integer affichernotif(int id) {
             // List<ReservationTaxi> list = new ArrayList<>();
             Integer count =0;
        Date actuelle = new Date();
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
           String dat = dateFormat.format(actuelle);
             Date actuelleh = new Date();
          DateFormat dateFormat1 = new SimpleDateFormat("HH"); 
           String dat1 = dateFormat1.format(actuelleh);
         Date actuellem = new Date();
          DateFormat dateFormatm = new SimpleDateFormat("mm"); 
           String datm = dateFormat1.format(actuellem);
            int minute=Integer.parseInt(datm);
            int minutes=minute + 15 ;
            String minu= String.valueOf(minutes);
            String periode= dat1+ ":" +minu;
    
        try {//INNER JOIN fos_user on  reservation_taxis.idduser=fos_user.id=?  INNER JOIN (SELECT * from demande_taxi INNER JOIN  reservation_taxis.iddemande=demande_taxi.id 
            //INNER JOIN fos_user U ON D.iduser=U.id
            String requete = "SELECT count(*) FROM reservation_taxis  R INNER JOIN  demande_taxi D ON  R.iddemande=D.id INNER JOIN fos_user U ON D.iduser=U.id  where   D.periode =? R.iduser=? And date_d =? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, periode);
            pst.setInt(2, id);
            pst.setString(3, dat);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              list.add(new ReservationTaxi(rs.getInt(3),rs.getInt(2),rs.getInt("id"),rs.getFloat(12),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString("username")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

       // return list;
    
    }*/
    
    public List<ReservationTaxi> afficheradmin() {
              List<ReservationTaxi> list = new ArrayList<>();
//int iduser, int iddemande, int id, float prix, String lieudepart, String lieuarrive, String region, String periode, String dated, String username
        try {//INNER JOIN fos_user on  reservation_taxis.idduser=fos_user.id=?  INNER JOIN (SELECT * from demande_taxi INNER JOIN  reservation_taxis.iddemande=demande_taxi.id 
            //INNER JOIN fos_user U ON D.iduser=U.id
            String requete = "SELECT * FROM reservation_taxis  R INNER JOIN  demande_taxi D ON  R.iddemande=D.id  INNER JOIN fos_user U ON D.iduser=U.id   ";
            PreparedStatement pst = cnx.prepareStatement(requete);
           // pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              list.add(new ReservationTaxi(rs.getInt(3),rs.getInt(2),rs.getInt("id"),rs.getFloat(12),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString("username")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
    }
    
    
    public List<ReservationTaxi> maxReq(int id)
    {
              List<ReservationTaxi> list = new ArrayList<>();

        try {
            String requete = "SELECT * , count(lieudepart,lieuarrive) FROM reservation_taxis  R INNER JOIN  demande_taxi D ON  R.iddemande=D.id  INNER JOIN fos_user U ON D.iduser=U.id  where R.iduser=? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              list.add(new ReservationTaxi(rs.getInt(3),rs.getInt(2),rs.getInt("id"),rs.getFloat(12),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString("username")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
    
    }
    
    
    
    
     
   /* public  ResultSet  affichepdf(int id) {
          
//int iduser, int iddemande, int id, float prix, String lieudepart, String lieuarrive, String region, String periode, String dated, String username
       //INNER JOIN fos_user on  reservation_taxis.idduser=fos_user.id=?  INNER JOIN (SELECT * from demande_taxi INNER JOIN  reservation_taxis.iddemande=demande_taxi.id 
            //INNER JOIN fos_user U ON D.iduser=U.id
            String requete = "SELECT * FROM reservation_taxis  R INNER JOIN  demande_taxi D ON  R.iddemande=D.id  INNER JOIN fos_user U ON D.iduser=U.id  where R.iduser=? ";
            PreparedStatement pst;
      try {
          pst = cnx.prepareStatement(requete);
           pst.setInt(1, id);
          ResultSet rs = pst.executeQuery();
            return rs;
      } catch (SQLException ex) {
          Logger.getLogger(ServiceReservationTaxi.class.getName()).log(Level.SEVERE, null, ex);
      }
           
           

     

      
    return rs;
    }*/
}
