package fithnitek.models;

import java.sql.Date;

/**
 *
 * @author marwe
 */
public class Objectif {
    
    private int idOjectif;
    private String titre;
    private String description;
    private String type;
    private int but;
    private byte etat;
    private Date start_date;
    private Date end_date;

    //For Retrieving an Objectif
    public Objectif(int idOjectif, String titre, String description, String type, int but, byte etat, Date start_date, Date end_date) {
        this.idOjectif = idOjectif;
        this.titre = titre;
        this.description = description;
        this.type = type;
        this.but = but;
        this.etat = etat;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    //For New Objectif
    public Objectif(String titre, String description, String type, int but, byte etat, Date start_date, Date end_date) {
        this.titre = titre;
        this.description = description;
        this.type = type;
        this.but = but;
        this.etat = etat;
        this.start_date = start_date;
        this.end_date = end_date;
    }
        

    public int getIdOjectif() {
        return idOjectif;
    }

    public void setIdOjectif(int idOjectif) {
        this.idOjectif = idOjectif;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBut() {
        return but;
    }

    public void setBut(int but) {
        this.but = but;
    }

    public byte getEtat() {
        return etat;
    }

    public void setEtat(byte etat) {
        this.etat = etat;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return "Objectif{" + "idOjectif=" + idOjectif + ", titre=" + titre + ", description=" + description + ", type=" + type + ", but=" + but + ", etat=" + etat + ", start_date=" + start_date + ", end_date=" + end_date + '}';
    }
    
}
