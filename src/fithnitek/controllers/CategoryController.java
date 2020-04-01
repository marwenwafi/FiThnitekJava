/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import fithnitek.models.User;
import fithnitek.utils.DataSource;
import fithnitek.models.Category;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marwe
 */
public class CategoryController {
    
    Connection cnx = DataSource.getInstance().getCnx();
    
    public void ajouter(Category c) {
        try {            
            String requete = "INSERT INTO Category (title, description, type, nature) "
                    + "VALUES ('" +c.getTitle()+"','" +c.getDescription()+"','" + c.getType() + "','"+ c.getNature()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Category ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimer(Category c) {
        try {
            String requete = "DELETE FROM category WHERE id_category=" + c.getId_Category();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Category supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifier(Category c) {
        try {
            String requete = "UPDATE category SET title='" + c.getTitle()+ "',description='" + c.getDescription()+ "',type='" + c.getType()+ "',"
                    + "nature='"+ c.getNature()+"' WHERE id_category=" + c.getId_Category();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Category modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    

    public List<Category> afficher() {
        List<Category> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM category";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Category (rs.getInt("id_category"), rs. getString("title"),rs.getString("description"),rs.getString("type"),rs.getString("nature")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
}
