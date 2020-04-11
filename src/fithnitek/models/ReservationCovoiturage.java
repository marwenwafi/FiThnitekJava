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
public class ReservationCovoiturage {
    
    private int idreservationcov ; 
    private int idutilisateurr ; 
    private int idoffrer ; 
    private int numberplacer ; 
    private float prixt; 
    private String destination ; 
    private String depart ; 
    private String date ; 
    private String username ; 
    private String username2 ; 
    private int number  ; 
    private int numberplaceo;
    private int idutilisateur ; 
    public ReservationCovoiturage(int idreservationcov, int idutilisateurr, int idoffrer, int numberplacer, float prixt) {
        this.idreservationcov = idreservationcov;
        this.idutilisateurr = idutilisateurr;
        this.idoffrer = idoffrer;
        this.numberplacer = numberplacer;
        this.prixt = prixt;
    }
public ReservationCovoiturage(int idreservationcov, int idutilisateurr, int idoffrer, int numberplacer, float prixt,String destination,String depart,String date,String username,int number,int numberplaceo,int idutilisateur,String username2) {
        this.idreservationcov = idreservationcov;
        this.idutilisateurr = idutilisateurr;
        this.idoffrer = idoffrer;
        this.numberplacer = numberplacer;
        this.prixt = prixt;
        this.destination=destination;
        this.depart = depart ; 
        this.date =date ; 
        this.username = username ;
        this.number = number ; 
        this.numberplaceo = numberplaceo;
        this.idutilisateur = idutilisateur ; 
        this.username2 = username2 ; 
                }
    public ReservationCovoiturage(int idutilisateurr, int idoffrer, int numberplacer, float prixt) {
        this.idutilisateurr = idutilisateurr;
        this.idoffrer = idoffrer;
        this.numberplacer = numberplacer;
        this.prixt = prixt;
    }

    public int getIdreservationcov() {
        return idreservationcov;
    }

    public int getIdutilisateurr() {
        return idutilisateurr;
    }

    public int getIdoffrer() {
        return idoffrer;
    }

    public int getNumberplacer() {
        return numberplacer;
    }

    public float getPrixt() {
        return prixt;
    }

    public void setIdreservationcov(int idreservationcov) {
        this.idreservationcov = idreservationcov;
    }

    public void setIdutilisateurr(int idutilisateurr) {
        this.idutilisateurr = idutilisateurr;
    }

    public void setIdoffrer(int idoffrer) {
        this.idoffrer = idoffrer;
    }

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public void setNumberplacer(int numberplacer) {
        this.numberplacer = numberplacer;
    }

    public void setPrixt(int prixt) {
        this.prixt = prixt;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ReservationCovoiturage{" + "idreservationcov=" + idreservationcov + ", idutilisateurr=" + idutilisateurr + ", idoffrer=" + idoffrer + ", numberplacer=" + numberplacer + ", prixt=" + prixt + ", destination=" + destination + ", depart=" + depart + ", date=" + date + ", username=" + username + ", username2=" + username2 + ", number=" + number + ", numberplaceo=" + numberplaceo + ", idutilisateur=" + idutilisateur + '}';
    }

    

    public int getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
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

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberplaceo() {
        return numberplaceo;
    }

    public void setNumberplaceo(int numberplaceo) {
        this.numberplaceo = numberplaceo;
    }
    
    

    

    

    
    
    
    
}
