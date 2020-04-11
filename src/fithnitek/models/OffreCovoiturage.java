/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.models;

/**
 *
 * @author lenovo
 */
public class OffreCovoiturage {
    private int id;
    private int idutilisateur;
    private String destination ;
    private String depart ; 
    private String date ; 
    private int nbrplace ; 
    private float prix ; 
    private String voiture ; 
    private String username ; 
    private int number ; 
    

    public OffreCovoiturage(int idutilisateur, String destination, String depart, String date, int nbrplace, float prix, String voiture) {
        //this.id = id;
        this.idutilisateur = idutilisateur;
        this.destination = destination;
        this.depart = depart;
        this.date = date;
        this.nbrplace = nbrplace;
        this.prix = prix;
        this.voiture = voiture;
    }

    public OffreCovoiturage(int id, int idutilisateur, String destination, String depart, String date, int nbrplace, float prix, String voiture, String username, int number) {
        this.id = id;
        this.idutilisateur = idutilisateur;
        this.destination = destination;
        this.depart = depart;
        this.date = date;
        this.nbrplace = nbrplace;
        this.prix = prix;
        this.voiture = voiture;
        this.username = username;
        this.number = number;
    }
    

    public OffreCovoiturage(int idoffrecovoiturage,int idutilisateur, String destination, String depart, String date, int nbrplace, float prix, String voiture) {
       this.id = idoffrecovoiturage;
        this.idutilisateur = idutilisateur;
        this.destination = destination;
        this.depart = depart;
        this.date = date;
        this.nbrplace = nbrplace;
        this.prix = prix;
        this.voiture = voiture;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNbrplace(int nbrplace) {
        this.nbrplace = nbrplace;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setVoiture(String voiture) {
        this.voiture = voiture;
    }

    public int getId() {
        return id;
    }

    public int getIdutilisateur() {
        return idutilisateur;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepart() {
        return depart;
    }

    public String getDate() {
        return date;
    }

    public int getNbrplace() {
        return nbrplace;
    }

    public float getPrix() {
        return prix;
    }

    public String getVoiture() {
        return voiture;
    }

    @Override
    public String toString() {
        return "OffreCovoiturage{" + "id=" + id + ", idutilisateur=" + idutilisateur + ", destination=" + destination + ", depart=" + depart + ", date=" + date + ", nbrplace=" + nbrplace + ", prix=" + prix + ", voiture=" + voiture + ", username=" + username + ", number=" + number + '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(String prenom) {
        this.number = number;
    }

   
}
