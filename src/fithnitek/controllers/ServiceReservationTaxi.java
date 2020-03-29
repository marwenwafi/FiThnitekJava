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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rawis
 */
public class ServiceReservationTaxi {
     Connection cnx = DataSource.getInstance().getCnx();
  
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
    public List<ReservationTaxi> affichertaxi(int id) {
              List<ReservationTaxi> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM reservation_taxis  where iduser=? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new ReservationTaxi(rs.getInt("id"),rs.getInt("iduser"),rs.getInt("iddemande")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
    }
}
