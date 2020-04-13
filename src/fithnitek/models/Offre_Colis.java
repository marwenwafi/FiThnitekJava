/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.models;

import java.util.Objects;

/**
 *
 * @author USER
 */
public class Offre_Colis {
    private int id_OffreCol;
    private String Date_col;
    private String Lieu_Depart;
    private String Lieu_Arrive;
    private int Prix;
    private double HauteurCol;
    private double LargeurCol;
    private String Voiture;
    private int idU;
    private double LongueurCol;
    private String Username ;
    private String Email ;
  public Offre_Colis(String Date_col, String Lieu_Depart, String Lieu_Arrive, int Prix, double HauteurCol, double LargeurCol, String Voiture, int idU, double LongueurCol) {
        this.Date_col = Date_col;
        this.Lieu_Depart = Lieu_Depart;
        this.Lieu_Arrive = Lieu_Arrive;
        this.Prix = Prix;
        this.HauteurCol = HauteurCol;
        this.LargeurCol = LargeurCol;
        this.Voiture = Voiture;
        this.idU = idU;
        this.LongueurCol = LongueurCol;
    }

    public Offre_Colis(double HauteurCol) {
        this.HauteurCol = HauteurCol;
    }
    public Offre_Colis(int id_OffreCol, String Date_col, String Lieu_Depart, String Lieu_Arrive, int Prix, double HauteurCol, double LargeurCol, String Voiture, int idU, double LongueurCol) {
        this.id_OffreCol = id_OffreCol;
        this.Date_col = Date_col;
        this.Lieu_Depart = Lieu_Depart;
        this.Lieu_Arrive = Lieu_Arrive;
        this.Prix = Prix;
        this.HauteurCol = HauteurCol;
        this.LargeurCol = LargeurCol;
        this.Voiture = Voiture;
        this.idU = idU;
        this.LongueurCol = LongueurCol;
    }
     public Offre_Colis(int id_OffreCol, String Date_col, String Lieu_Depart, String Lieu_Arrive, int Prix, double HauteurCol, double LargeurCol, String Voiture, int idU, double LongueurCol,String username) {
        this.id_OffreCol = id_OffreCol;
        this.Date_col = Date_col;
        this.Lieu_Depart = Lieu_Depart;
        this.Lieu_Arrive = Lieu_Arrive;
        this.Prix = Prix;
        this.HauteurCol = HauteurCol;
        this.LargeurCol = LargeurCol;
        this.Voiture = Voiture;
        this.idU = idU;
        this.LongueurCol = LongueurCol;
        this.Username = username ; 
        
    }
       public Offre_Colis(int id_OffreCol, String Date_col, String Lieu_Depart, String Lieu_Arrive, int Prix, double HauteurCol, double LargeurCol, String Voiture, int idU, double LongueurCol,String username,String Email) {
        this.id_OffreCol = id_OffreCol;
        this.Date_col = Date_col;
        this.Lieu_Depart = Lieu_Depart;
        this.Lieu_Arrive = Lieu_Arrive;
        this.Prix = Prix;
        this.HauteurCol = HauteurCol;
        this.LargeurCol = LargeurCol;
        this.Voiture = Voiture;
        this.idU = idU;
        this.LongueurCol = LongueurCol;
        this.Username = username ; 
        this.Email=Email;
       
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public int getId_OffreCol() {
        return id_OffreCol;
    }

    public void setId_OffreCol(int id_OffreCol) {
        this.id_OffreCol = id_OffreCol;
    }

    public String getDate_col() {
        return Date_col;
    }

    public void setDate_col(String Date_col) {
        this.Date_col = Date_col;
    }

    public String getLieu_Depart() {
        return Lieu_Depart;
    }

    public void setLieu_Depart(String Lieu_Depart) {
        this.Lieu_Depart = Lieu_Depart;
    }

    public String getLieu_Arrive() {
        return Lieu_Arrive;
    }

    public void setLieu_Arrive(String Lieu_Arrive) {
        this.Lieu_Arrive = Lieu_Arrive;
    }

    public int getPrix() {
        return Prix;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
    }

    public double getHauteurCol() {
        return HauteurCol;
    }

    public void setHauteurCol(double HauteurCol) {
        this.HauteurCol = HauteurCol;
    }

    public double getLargeurCol() {
        return LargeurCol;
    }

    public void setLargeurCol(double LargeurCol) {
        this.LargeurCol = LargeurCol;
    }

    public String getVoiture() {
        return Voiture;
    }

    public void setVoiture(String Voiture) {
        this.Voiture = Voiture;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public double getLongueurCol() {
        return LongueurCol;
    }

    public void setLongueurCol(double LongueurCol) {
        this.LongueurCol = LongueurCol;
    }

    @Override
    public String toString() {
        return "Offre_Colis{" + "id_OffreCol=" + id_OffreCol + ", Date_col=" + Date_col + ", Lieu_Depart=" + Lieu_Depart + ", Lieu_Arrive=" + Lieu_Arrive + ", Prix=" + Prix + ", HauteurCol=" + HauteurCol + ", LargeurCol=" + LargeurCol + ", Voiture=" + Voiture + ", idU=" + idU + ", LongueurCol=" + LongueurCol + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.id_OffreCol;
        hash = 19 * hash + Objects.hashCode(this.Date_col);
        hash = 19 * hash + Objects.hashCode(this.Lieu_Depart);
        hash = 19 * hash + Objects.hashCode(this.Lieu_Arrive);
        hash = 19 * hash + this.Prix;
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.HauteurCol) ^ (Double.doubleToLongBits(this.HauteurCol) >>> 32));
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.LargeurCol) ^ (Double.doubleToLongBits(this.LargeurCol) >>> 32));
        hash = 19 * hash + Objects.hashCode(this.Voiture);
        hash = 19 * hash + this.idU;
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.LongueurCol) ^ (Double.doubleToLongBits(this.LongueurCol) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Offre_Colis other = (Offre_Colis) obj;
        if (this.id_OffreCol != other.id_OffreCol) {
            return false;
        }
        if (this.Prix != other.Prix) {
            return false;
        }
        if (Double.doubleToLongBits(this.HauteurCol) != Double.doubleToLongBits(other.HauteurCol)) {
            return false;
        }
        if (Double.doubleToLongBits(this.LargeurCol) != Double.doubleToLongBits(other.LargeurCol)) {
            return false;
        }
        if (this.idU != other.idU) {
            return false;
        }
        if (Double.doubleToLongBits(this.LongueurCol) != Double.doubleToLongBits(other.LongueurCol)) {
            return false;
        }
        if (!Objects.equals(this.Date_col, other.Date_col)) {
            return false;
        }
        if (!Objects.equals(this.Lieu_Depart, other.Lieu_Depart)) {
            return false;
        }
        if (!Objects.equals(this.Lieu_Arrive, other.Lieu_Arrive)) {
            return false;
        }
        if (!Objects.equals(this.Voiture, other.Voiture)) {
            return false;
        }
        return true;
    }
    


 
}
