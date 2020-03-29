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
public class DemandeTaxi {
      private int id;
    private int iduser;
    private String lieudepart;
    private String lieuarrive;
    private String region ; 
    private String periode ; 
       
    private String username ; 

    public DemandeTaxi(int iduser, String lieudepart, String lieuarrive, String region, String periode, String dated, int etat, float prix) {
        this.iduser = iduser;
        this.lieudepart = lieudepart;
        this.lieuarrive = lieuarrive;
        this.region = region;
        this.periode = periode;
        this.dated = dated;
        this.etat = etat;
        this.prix = prix;
    }

  

    public String getPeriode() {
        return periode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }
    private String dated;
    private int  etat ; 
    private float prix ; 

    public DemandeTaxi(int id, int iduser, String lieudepart, String lieuarrive, String region, String periode,  String dated, int etat, float prix) {
        this.id = id;
        this.iduser = iduser;
        this.lieudepart = lieudepart;
        this.lieuarrive = lieuarrive;
        this.region = region;
        this.periode = periode;
       // this.username = username;
        this.dated = dated;
        this.etat = etat;
        this.prix = prix;
    }
      public DemandeTaxi(int id, int iduser, String lieudepart, String lieuarrive, String region, String periode, String dated, int etat, float prix, String username) {
        this.id = id;
        this.iduser = iduser;
        this.lieudepart = lieudepart;
        this.lieuarrive = lieuarrive;
        this.region = region;
        this.periode = periode;
        this.username = username;
        this.dated = dated;
        this.etat = etat;
        this.prix = prix;
    }




    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
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

    public String getDated() {
        return dated;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }



    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "DemandeTaxi{" + "id=" + id + ", iduser=" + iduser + ", lieudepart=" + lieudepart + ", lieuarrive=" + lieuarrive + ", region=" + region + ", periode=" + periode + ", username=" + username + ", dated=" + dated + ", etat=" + etat + ", prix=" + prix + '}';
    }

    

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
