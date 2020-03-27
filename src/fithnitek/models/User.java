/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.models;

import java.sql.Date;

/**
 *
 * @author marwe
 */
public class User {
    
    protected int id;
    protected String email;
    protected String username;
    protected String prenom;
    protected String hashedPwd;
    protected int tel;
    protected Date datedenaissance;
    protected Date registrationdate;
    protected int nbroffre;
    protected int points;

    //For new user
    public User(String email,String username, String prenom, String hashedPwd, int tel, Date datedenaissance) {
        this.email = email;
        this.username = username;
        this.prenom = prenom;
        this.hashedPwd = hashedPwd;
        this.tel = tel;
        this.datedenaissance = datedenaissance;
        long millis = System.currentTimeMillis();  
        this.registrationdate = new java.sql.Date(millis);
        this.points = 0;
        this.nbroffre = 0;
    }

    //For retrieving a user from DB
    public User(int id,String email, String username, String prenom, String hashedPwd, int tel, Date datedenaissance, Date registrationdate, int nbroffre, int points) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.prenom = prenom;
        this.hashedPwd = hashedPwd;
        this.tel = tel;
        this.datedenaissance = datedenaissance;
        this.registrationdate = registrationdate;
        this.nbroffre = nbroffre;
        this.points = points;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPwd() {
        return hashedPwd;
    }

    public void setHashedPwd(String hashedPwd) {
        this.hashedPwd = hashedPwd;
    }

    
    
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public Date getDatedenaissance() {
        return datedenaissance;
    }

    public void setDatedenaissance(Date datedenaissance) {
        this.datedenaissance = datedenaissance;
    }

    public Date getRegistrationdate() {
        return registrationdate;
    }

    public void setRegistrationdate(Date registrationdate) {
        this.registrationdate = registrationdate;
    }

    public int getNbroffre() {
        return nbroffre;
    }

    public void setNbroffre(int nbroffre) {
        this.nbroffre = nbroffre;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
       @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", prenom=" + prenom + ", tel=" + tel + ", datedenaissance=" + datedenaissance + ", registrationdate=" + registrationdate + ", nbroffre=" + nbroffre + ", points=" + points + '}';
    }
    
    
}
