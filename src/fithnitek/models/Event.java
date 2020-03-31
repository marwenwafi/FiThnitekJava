/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.models;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author sourour
 */
public class Event implements Serializable {
    private int id ;
    private String titre ;
    private Date dateDebut;
    private Date dateFin;
    private String description;
    private int promotion ;
    private String etat;
    private String url ;    
    private String image ;
    private String operation ;

    public Event() {
    }

    public Event(int id, String titre, Date dateDebut, Date dateFin, String description, int promotion, String etat, String url, String image, String operation) {
        this.id = id;
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.promotion = promotion;
        this.etat = etat;
        this.url = url;
        this.image = image;
        this.operation = operation;
    }
    

    public Event( String titre, Date dateDebut, Date dateFin, String description, int promotion, String etat, String url, String image, String operation) {
        
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.promotion = promotion;
        this.etat = etat;
        this.url = url;
        this.image = image;
        this.operation = operation;
    }
     public Event( String titre, Date dateDebut, Date dateFin, String description, int promotion, String url, String image, String operation) {
        
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.promotion = promotion;
        //this.etat = etat;
        this.url = url;
        this.image = image;
        this.operation = operation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "Event{  titre=" + titre + ", dateDebut=" 
                + dateDebut + ", dateFin=" + dateFin + ", description=" 
                + description + ", promotion=" + promotion + ", etat=" 
                + etat + ", url=" + url + ", image=" + image + ", operation=" + operation + '}';
    }
    
    
    
    
    
    
}
