/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import fithnitek.models.Evaluation;
import fithnitek.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author yassin
 */
public class EvaluationController {
Connection cnx = DataSource.getInstance().getCnx();

public void ajouterRating(Evaluation e)
{
  try {
            
            String requete = "INSERT INTO evaluation (idclient,idutilisateur,note,commentaire) VALUES ('" + e.getIdClient() + "','" + e.getIdUser() + "','" + e.getNote() + "','" + e.getComment() + "')";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate();
            
            System.out.println("Rating envoyee !");
            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
}

public void modifierRating(Evaluation e)
{
try {
            String requete = "UPDATE evaluation SET note='" + e.getNote() + "' ,commentaire='" + e.getComment() + "' WHERE idclient='" + e.getIdClient() + "' and idutilisateur='" + e.getIdUser() + "'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.executeUpdate();
            System.out.println("Rating modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}

public int verifier(Evaluation e)
{int count=0;
try {
            String requete = "SELECT * FROM evaluation WHERE idclient=? and idutilisateur=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, e.getIdClient());
            pst.setInt(2, e.getIdUser());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){count++;}

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

return count;
}

public void rate(Evaluation e)
{
    int test = this.verifier(e);
    if (test==0){this.ajouterRating(e);}
    else if(test>=0){this.modifierRating(e);}
}
public ObservableList<Evaluation> afficherRating(int id) {
       ObservableList<Evaluation> list=FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM evaluation where idutilisateur=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Evaluation(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
}
