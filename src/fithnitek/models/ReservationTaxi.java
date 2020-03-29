/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.models;

/**
 *
 * @author Rawis
 */
public class ReservationTaxi {
     private int iduser;
    private int iddemande;
    private int id;
    private float prix ; 
    private String lieudepart;
    private String lieuarrive;
    private String region ; 
    private String periode ; 
    private String dated;
    private String username ; 

    public ReservationTaxi(int iduser, int iddemande) {
        this.iduser = iduser;
        this.iddemande = iddemande;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIddemande() {
        return iddemande;
    }

    public void setIddemande(int iddemande) {
        this.iddemande = iddemande;
    }

    @Override
    public String toString() {
        return "ReservationTaxi{" + "iduser=" + iduser + ", iddemande=" + iddemande + ", id=" + id + ", prix=" + prix + ", lieudepart=" + lieudepart + ", lieuarrive=" + lieuarrive + ", region=" + region + ", periode=" + periode + ", dated=" + dated + ", username=" + username + '}';
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getLieudepart() {
        return lieudepart;
    }

    public void setLieudepart(String lieudepart) {
        this.lieudepart = lieudepart;
    }

    public String getLieuarrive() {
        return lieuarrive;
    }

    public void setLieuarrive(String lieuarrive) {
        this.lieuarrive = lieuarrive;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getDated() {
        return dated;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ReservationTaxi(int iduser, int iddemande, int id, float prix, String lieudepart, String lieuarrive, String region, String periode, String dated, String username) {
        this.iduser = iduser;
        this.iddemande = iddemande;
        this.id = id;
        this.prix = prix;
        this.lieudepart = lieudepart;
        this.lieuarrive = lieuarrive;
        this.region = region;
        this.periode = periode;
        this.dated = dated;
        this.username = username;
    }

    public ReservationTaxi(int id, int iduser, int iddemande) {
        this.iduser = iduser;
        this.iddemande = iddemande;
        this.id = id;
    }
    
}
