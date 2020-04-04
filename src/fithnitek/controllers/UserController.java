/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import fithnitek.utils.DataSource;
import fithnitek.models.User;
import fithnitek.utils.BCryptPasswordEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 *
 * @author marwe
 */
public class UserController {
    
    Connection cnx = DataSource.getInstance().getCnx();
    
    public void ajouter(User u) {
        try {
            //TODO ADD Password Logic
            
            String requete = "INSERT INTO fos_user (email,email_canonical, username, username_canonical, prenom, tel, datedenaissance, registrationdate, nbroffre,points,enabled,password,roles,image) "
                    + "VALUES ('" +u.getEmail()+"','" +u.getEmail()+"','" + u.getUsername() + "','"+ u.getUsername()+"','" + u.getPrenom() + "','" + u.getTel() 
                    + "','" + u.getDatedenaissance() + "','" + u.getRegistrationdate() + "','" + u.getNbroffre() 
                    + "','" + u.getPoints()+ "',1,'"+u.getHashedPwd()+"','a:0:{}','"+u.getImage()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("User ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimer(User u) {
        try {
            String requete = "DELETE FROM fos_user WHERE id=" + u.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("User supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifier(User u) {
        try {
            String requete = "UPDATE fos_user SET email='" + u.getEmail()+ "',username='" + u.getUsername()+ "',prenom='" + u.getPrenom()+ "',"
                    + "tel='"+ u.getTel()+"', datedenaissance='"+ u.getDatedenaissance()+"',"
                    + "nbroffre='"+u.getNbroffre()+"', points='"+u.getPoints()+"', image='"+u.getImage()+"' WHERE id=" + u.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("User modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    

    public List<User> afficher() {
        List<User> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM fos_user";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new User(rs.getInt("id"), rs. getString("email"),rs.getString("username"),rs.getString("prenom"),
                        rs.getString("password"),rs.getInt("tel"), rs.getDate("datedenaissance"),rs.getDate("registrationdate"),
                        rs.getInt("nbroffre"),rs.getInt("points"),rs.getString("image")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public User oneUser(String username) {
        User u = null;
        try {
            String requete = "SELECT * FROM fos_user where username='"+username+"';";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            rs.next();
                u = new User(rs.getInt("id"), rs. getString("email"),rs.getString("username"),rs.getString("prenom"),
                        rs.getString("password"),rs.getInt("tel"), rs.getDate("datedenaissance"),rs.getDate("registrationdate"),
                        rs.getInt("nbroffre"),rs.getInt("points"),rs.getString("image"));

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return u;
    }
    
    public void attemptLogin(String name, String pass) {
        System.setProperty("java.security.auth.login.config", "jaas.config");       
        try {
                LoginContext lc = new LoginContext("User", new UserCallbackHandler(name, pass));
                System.out.println(lc.toString());
                lc.login();
                Subject subject = lc.getSubject();
                
        } catch (LoginException e) {
                e.printStackTrace();
        }
    }
    
    public void attemptRegistration(String username, String email, String password, String confirm, String surname, int tel, Date birthdate, String image) throws IllegalArgumentException{
        if (!password.equals(confirm))
        {
            throw new IllegalArgumentException("Password confirmation missmatch");
        }
        else
        {
            BCryptPasswordEncoder b = new BCryptPasswordEncoder();
            String hashedpass = b.hashPassword(password);
            User u = new User(email,username,surname,hashedpass,tel,birthdate,image);
            ajouter(u);
            attemptLogin(username, password);
            System.out.println("User Added Successfully");
        }
    }
    
}
