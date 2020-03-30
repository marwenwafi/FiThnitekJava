/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.models;


/**
 *
 * @author USER
 */
public class Reservation_Colis {
    private int id_ReservationCol;
    private double HauteurResv;
    private double LargeurResv;
    private double PrixResv;
    private int id;
    private int id_Offre;
    private double LongueurResv;

    public Reservation_Colis(int id_ReservationCol, double HauteurResv, double LargeurResv, double PrixResv, int id, int id_Offre, double LongueurResv) {
        this.id_ReservationCol = id_ReservationCol;
        this.HauteurResv = HauteurResv;
        this.LargeurResv = LargeurResv;
        this.PrixResv = PrixResv;
        this.id = id;
        this.id_Offre = id_Offre;
        this.LongueurResv = LongueurResv;
      
    }
      public Reservation_Colis(double HauteurResv, double LargeurResv,  int id, int id_Offre, double LongueurResv) {
        this.HauteurResv = HauteurResv;
        this.LargeurResv = LargeurResv;
        this.id = id;
        this.id_Offre = id_Offre;
        this.LongueurResv = LongueurResv;
      
    }


    public int getId_ReservationCol() {
        return id_ReservationCol;
    }

    public void setId_ReservationCol(int id_ReservationCol) {
        this.id_ReservationCol = id_ReservationCol;
    }

    public double getHauteurResv() {
        return HauteurResv;
    }

    public void setHauteurResv(double HauteurResv) {
        this.HauteurResv = HauteurResv;
    }

    public double getLargeurResv() {
        return LargeurResv;
    }

    public void setLargeurResv(double LargeurResv) {
        this.LargeurResv = LargeurResv;
    }

    public double getPrixResv() {
        return PrixResv;
    }

    public void setPrixResv(double PrixResv) {
        this.PrixResv = PrixResv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_Offre() {
        return id_Offre;
    }

    public void setId_Offre(int id_Offre) {
        this.id_Offre = id_Offre;
    }

    public double getLongueurResv() {
        return LongueurResv;
    }

    public void setLongueurResv(double LongueurResv) {
        this.LongueurResv = LongueurResv;
    }

    @Override
    public String toString() {
        return "Reservation_Colis{" + "id_ReservationCol=" + id_ReservationCol + ", HauteurResv=" + HauteurResv + ", LargeurResv=" + LargeurResv + ", PrixResv=" + PrixResv + ", id=" + id + ", id_Offre=" + id_Offre + ", LongueurResv=" + LongueurResv + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id_ReservationCol;
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.HauteurResv) ^ (Double.doubleToLongBits(this.HauteurResv) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.LargeurResv) ^ (Double.doubleToLongBits(this.LargeurResv) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.PrixResv) ^ (Double.doubleToLongBits(this.PrixResv) >>> 32));
        hash = 29 * hash + this.id;
        hash = 29 * hash + this.id_Offre;
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.LongueurResv) ^ (Double.doubleToLongBits(this.LongueurResv) >>> 32));
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
        final Reservation_Colis other = (Reservation_Colis) obj;
        if (this.id_ReservationCol != other.id_ReservationCol) {
            return false;
        }
        if (Double.doubleToLongBits(this.HauteurResv) != Double.doubleToLongBits(other.HauteurResv)) {
            return false;
        }
        if (Double.doubleToLongBits(this.LargeurResv) != Double.doubleToLongBits(other.LargeurResv)) {
            return false;
        }
        if (Double.doubleToLongBits(this.PrixResv) != Double.doubleToLongBits(other.PrixResv)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        if (this.id_Offre != other.id_Offre) {
            return false;
        }
        if (Double.doubleToLongBits(this.LongueurResv) != Double.doubleToLongBits(other.LongueurResv)) {
            return false;
        }
        return true;
    }
    

 
}
