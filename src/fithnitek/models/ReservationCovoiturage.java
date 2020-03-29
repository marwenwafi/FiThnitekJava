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
    private int number  ; 
    private int numberplaceo;
    public ReservationCovoiturage(int idreservationcov, int idutilisateurr, int idoffrer, int numberplacer, float prixt) {
        this.idreservationcov = idreservationcov;
        this.idutilisateurr = idutilisateurr;
        this.idoffrer = idoffrer;
        this.numberplacer = numberplacer;
        this.prixt = prixt;
    }
public ReservationCovoiturage(int idreservationcov, int idutilisateurr, int idoffrer, int numberplacer, float prixt,String destination,String depart,String date,String username,int number,int numberplaceo) {
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
        return "ReservationCovoiturage{" + "idreservationcov=" + idreservationcov + ", idutilisateurr=" + idutilisateurr + ", idoffrer=" + idoffrer + ", numberplacer=" + numberplacer + ", prixt=" + prixt + ", destination=" + destination + ", depart=" + depart + ", date=" + date + ", username=" + username + ", number=" + number + ", numberplaceo=" + numberplaceo + '}';
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
