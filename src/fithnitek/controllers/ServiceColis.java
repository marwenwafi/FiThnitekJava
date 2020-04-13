/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import fithnitek.models.*;

import fithnitek.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fithnitek.models.Offre_Colis;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


/**
 *
 * @author USER
 */
public class ServiceColis implements IColis<Offre_Colis> {
       Connection cnx = DataSource.getInstance().getCnx();
     @Override
    public List<Offre_Colis> afficherOffremshColis(int id) {
        List<Offre_Colis> list = new ArrayList<>();
         Date actuelle = new Date();
        // DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         String dat = dateFormat.format(actuelle);
        try {
          
            String requete = "SELECT * FROM offre_colis OC INNER JOIN fos_user fos on OC.idU=fos.id where idU != '"+id+"' And Hauteur > 0 And Largeur > 0 And Longueur > 0 And Date_col > '"+dat+"'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
     list.add(new Offre_Colis(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getDouble(7),rs.getString(8),rs.getInt(9),rs.getDouble(10),rs.getString("username")));                       
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
     @Override
    public List<Offre_Colis> afficherOffreColis(int id) {
        List<Offre_Colis> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM offre_colis where   idU = "+id+"";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
     list.add(new Offre_Colis(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getDouble(7),rs.getString(8),rs.getInt(9),rs.getDouble(10)));                       
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
         @Override
     public List<Offre_Colis> afficherAllColis() {
        List<Offre_Colis> list = new ArrayList<>();
Date actuelle = new Date();
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         String dat = dateFormat.format(actuelle);
        try {
            String requete = "SELECT * FROM offre_colis OC INNER JOIN fos_user fos on OC.idU=fos.id where  Hauteur > 0 And Largeur > 0 And Longueur > 0 And Date_col > '"+dat+"'  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
          
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              //  public Offre_Colis(int id_OffreCol, String Date_col, String Lieu_Depart, String Lieu_Arrive, int Prix, double HauteurCol, double LargeurCol, String Voiture, int idU, double LongueurCol) {
     list.add(new Offre_Colis(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getDouble(7),rs.getString(8),rs.getInt(9),rs.getDouble(10),rs.getString("username"),rs.getString("email")));                       
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
      public String GetMail ()  throws SQLException {
             String email="SELECT email FROM offre_colis OC INNER JOIN fos_user fos on OC.idU=fos.id";
                        PreparedStatement pst = cnx.prepareStatement(email);
            ResultSet rs = pst.executeQuery();

               rs.next();    
               String mail=rs.getString(1);
               return mail;
     
    }
      @Override
    public void ajouterOffreColis(Offre_Colis O) {
        try {
           
            String requete = "INSERT INTO offre_colis (Date_col,Lieu_Depart,Lieu_Arrive,Prix,Hauteur,Largeur,Voiture,idU,Longueur) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, O.getDate_col());
            pst.setString(2, O.getLieu_Depart());
            pst.setString(3, O.getLieu_Arrive());
            pst.setInt(4,O.getPrix());
            pst.setDouble(5,  O.getHauteurCol()) ; 
            pst.setDouble(6,  O.getLargeurCol());
            pst.setString(7,O.getVoiture());
            pst.setInt(8,O.getIdU());
            pst.setDouble(9,  O.getLongueurCol());
            pst.executeUpdate();
            System.out.println("Offre ajoutée !");
                String qrCodeText = "tu es ajouter un offre votre id est ="+O.getIdU()+", votre offre compose de : Largeur="+O.getLargeurCol()+",Longueur="+O.getLongueurCol()+",Hauteur ="+O.getHauteurCol()+",Prix="+O.getPrix()+",de Date ="+O.getDate_col()+"";
		String filePath = "C:/Users/marwe/Documents/NetBeansProjects/FiThnitek/src/images/ADDPackage.png";
		int size = 400;
		String fileType = "png";
		File qrFile = new File(filePath);
               //  String qrFile="C:/Users/USER/Desktop/image";
               createQRImage(qrFile,qrCodeText, size,fileType); 
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  catch (WriterException ex) {
               Logger.getLogger(ServiceColis.class.getName()).log(Level.SEVERE, null, ex);
           } catch (IOException ex) {
               Logger.getLogger(ServiceColis.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
     @Override
    public void supprimerOffreColis(Offre_Colis o) {
          try {
            String requete = "DELETE FROM offre_colis WHERE id_Offre_Col=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, o.getId_OffreCol());
            pst.executeUpdate();
            System.out.println("Offre supprimée !");
              String qrCodeText = "Delete Package ="+o.getId_OffreCol()+"";
		String filePath = "C:/Users/marwe/Documents/NetBeansProjects/FiThnitek/src/images/DeletePackage.png";
		int size = 400;
		String fileType = "png";
		File qrFile = new File(filePath);
               //  String qrFile="C:/Users/USER/Desktop/image";
               createQRImage(qrFile,qrCodeText, size,fileType); 

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  catch (WriterException ex) {
               Logger.getLogger(ServiceColis.class.getName()).log(Level.SEVERE, null, ex);
           } catch (IOException ex) {
               Logger.getLogger(ServiceColis.class.getName()).log(Level.SEVERE, null, ex);
           }
    }

    @Override
    public void modifierOffreColis(Offre_Colis O) {
         try {
            String requete = "UPDATE  offre_colis SET Date_col=?,Lieu_Depart=?,Lieu_Arrive=?,Prix=?,Hauteur=?,Largeur=?,Voiture=?,Longueur=? WHERE id_Offre_Col=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, O.getDate_col());
            pst.setString(2, O.getLieu_Depart());
            pst.setString(3, O.getLieu_Arrive());
            pst.setInt(4,O.getPrix());
            pst.setDouble(5,  O.getHauteurCol()) ; 
            pst.setDouble(6,  O.getLargeurCol());
            pst.setString(7,O.getVoiture());
           // pst.setInt(8,O.getId());
            pst.setDouble(8,  O.getLongueurCol());
            pst.setInt(9,O.getId_OffreCol());

            pst.executeUpdate();
            System.out.println("Offre modifiée !");
               String qrCodeText = "Update offre your id user est ="+O.getIdU()+", votre new Information is : Largeur="+O.getLargeurCol()+",Longueur="+O.getLongueurCol()+",Hauteur ="+O.getHauteurCol()+",Prix="+O.getPrix()+",de Date ="+O.getDate_col()+"";
		String filePath = "C:/Users/marwe/Documents/NetBeansProjects/FiThnitek/src/images/UpdatePackage.png";
		int size = 400;
		String fileType = "png";
		File qrFile = new File(filePath);
               //  String qrFile="C:/Users/USER/Desktop/image";
               createQRImage(qrFile,qrCodeText, size,fileType); 

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  catch (WriterException ex) {
               Logger.getLogger(ServiceColis.class.getName()).log(Level.SEVERE, null, ex);
           } catch (IOException ex) {
               Logger.getLogger(ServiceColis.class.getName()).log(Level.SEVERE, null, ex);
           }
    }

 

    
      @Override
    public List<Offre_Colis> RechercheOffreColisDepartArrive(String Depart,String Arrive,int id,String Date) {
        List<Offre_Colis> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM offre_colis OC INNER JOIN fos_user fos on OC.idU=fos.id where idU != '"+id+"' And Hauteur > 0 And Largeur > 0 And Longueur > 0 And Lieu_Depart like '%"+Depart+"%' AND Lieu_Arrive like '%"+Arrive+"%'  AND Date_col = '"+Date+"'  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
     list.add(new Offre_Colis(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getDouble(7),rs.getString(8),rs.getInt(9),rs.getDouble(10),rs.getString("username")));                       
                     
            }
                               
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
     @Override
     public List<Offre_Colis> RechercheOffreColisAdmin(Double Hauteur,Double Largeur,Double Longueur,String car,String Date) {
        List<Offre_Colis> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM offre_colis OC INNER JOIN fos_user fos on OC.idU=fos.id where  Voiture like '%"+car+"%' AND Hauteur >= "+Hauteur+" AND Largeur >= "+Largeur+" AND Longueur >= "+Longueur+"  AND Date_col = '"+Date+"'  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
     list.add(new Offre_Colis(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getDouble(7),rs.getString(8),rs.getInt(9),rs.getDouble(10),rs.getString("username")));                       
                     
            }
                               
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
        @Override
     public List<Offre_Colis> TrierOffreColis() {
        List<Offre_Colis> list = new ArrayList<>();
  Date actuelle = new Date();
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         String dat = dateFormat.format(actuelle);
        try {
            String requete = "SELECT * FROM offre_colis OC INNER JOIN fos_user fos on OC.idU=fos.id where Hauteur > 0 And Largeur > 0 And Longueur > 0 And Date_col > '"+dat+"' ORDER BY Date_col ASC   ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
     list.add(new Offre_Colis(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getDouble(7),rs.getString(8),rs.getInt(9),rs.getDouble(10),rs.getString("username")));                       
                     
            }
                               
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
      @Override
     public List<Offre_Colis> TrierOffreColisPrix() {
        List<Offre_Colis> list = new ArrayList<>();
  Date actuelle = new Date();
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         String dat = dateFormat.format(actuelle);
        try {
            String requete = "SELECT * FROM offre_colis OC INNER JOIN fos_user fos on OC.idU=fos.id where Hauteur > 0 And Largeur > 0 And Longueur > 0 And Date_col > '"+dat+"' ORDER BY Prix ASC   ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
     list.add(new Offre_Colis(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getDouble(7),rs.getString(8),rs.getInt(9),rs.getDouble(10),rs.getString("username")));                       
                     
            }
                               
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
 

       @Override
     public List<Offre_Colis> TrierOffrewhereColis(int id) {
        List<Offre_Colis> list = new ArrayList<>();
Date actuelle = new Date();
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         String dat = dateFormat.format(actuelle);
        try {
            String requete = "SELECT * FROM offre_colis OC INNER JOIN fos_user fos on OC.idU=fos.id where idU != '"+id+"' And Hauteur > 0 And Largeur > 0 And Longueur > 0 And Date_col > '"+dat+"'  ORDER BY Date_col ASC  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
     list.add(new Offre_Colis(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getDouble(7),rs.getString(8),rs.getInt(9),rs.getDouble(10),rs.getString("username")));                       
                     
            }
                               
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    @Override
     public List<Offre_Colis> TrierOffreColisPrixwhere(int id) {
        List<Offre_Colis> list = new ArrayList<>();
Date actuelle = new Date();
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         String dat = dateFormat.format(actuelle);
        try {
            String requete = "SELECT * FROM offre_colis OC INNER JOIN fos_user fos on OC.idU=fos.id where idU != '"+id+"' And Hauteur > 0 And Largeur > 0 And Longueur > 0 And Date_col > '"+dat+"' ORDER BY Prix ASC  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
     list.add(new Offre_Colis(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getDouble(7),rs.getString(8),rs.getInt(9),rs.getDouble(10),rs.getString("username")));                       
                     
            }
                               
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }  
    
 
public void createQRImage(File qrFile, String qrCodeText, int size, String fileType)
			throws WriterException, IOException {
		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ImageIO.write(image, fileType, qrFile);
	}
    
    
}
