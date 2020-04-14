/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiTAXI;


import java.awt.Desktop;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import fithnitek.models.User;
import fithnitek.utils.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Rawis
 */
public class mypdfjava {
    
    int genererInt(int borneInf, int borneSup){
   Random random = new Random();
   int nb;
   nb = borneInf+random.nextInt(borneSup-borneInf);
   return nb;
}
    
    
    public  String  getUser(Integer id) throws SQLException
    {
        String x="";
       
         Connection cnx = DataSource.getInstance().getCnx();
          String req= "Select username , prenom From fos_user  where id='" +id+"' ";
          PreparedStatement pst = cnx.prepareStatement(req);
          ResultSet rs;
          rs = pst.executeQuery();
          
          if(rs.next())
          {
              x = rs.getString("username") + " " +rs.getString("prenom");
             
    }
          return (x);   
          
}
     
     
     
      public void pdfaffichedemandeuser( Integer user )  
              
      {  Document doc = new Document();
        Connection cnx = DataSource.getInstance().getCnx();
        //
      String sql = "SELECT * FROM demande_taxi D  INNER JOIN fos_user U ON D.iduser=U.id  where D.iduser='"+user+"' ";
       try {
        
      
         PreparedStatement pst;
        
              pst = cnx.prepareStatement(sql);
       
              
          
          ResultSet rs = pst.executeQuery();
          
          
        
           PdfWriter.getInstance(doc, new FileOutputStream("src/images/try1.pdf"));
          
           doc.open();
           Image logo;
        
              logo = Image.getInstance("src/images/logo0.png");
         
           logo.scaleAbsoluteHeight(150);
           logo.scaleAbsoluteWidth(600);
           logo.setAlignment(Image.ALIGN_CENTER);
           
           doc.add(logo);
            doc.add(new Paragraph(" "));
            
            
            String  nom= getUser(user);
            
            
            
            
            String a= "Taxi Request Realized by " + nom ;
            doc.add(new Paragraph(a ));
             doc.add(new Paragraph(" "));
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            PdfPCell cell;
            
        
           
           cell = new PdfPCell( new Phrase("Region",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
           
           
           
           cell = new PdfPCell( new Phrase("Departure ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
            cell = new PdfPCell( new Phrase("Destination ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
           
            cell = new PdfPCell( new Phrase("Date ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
          doc.add(table);
           table.addCell(cell);
          
          
           cell = new PdfPCell( new Phrase("Time ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           doc.add(table);
           table.addCell(cell);
          
           cell = new PdfPCell( new Phrase("Price ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
           
       
          while(rs.next())
          {
          
         
         
           
           
           
         
           
           
           cell = new PdfPCell( new Phrase(rs.getString(5),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
           
           
           
           cell = new PdfPCell( new Phrase(rs.getString(3),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
         
           cell = new PdfPCell( new Phrase(rs.getString(4),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
        
           
           
           
            cell = new PdfPCell( new Phrase(rs.getString(7),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
           
            cell = new PdfPCell( new Phrase(rs.getString(6),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
            String price= String.valueOf(rs.getFloat(9));
            cell = new PdfPCell( new Phrase(price,FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
              
          }
          
           doc.add(table);
           
           doc.close();
           Desktop.getDesktop().open( new File ("src/images/try1.pdf"));
          
        
                   
       }
          
        catch (DocumentException ex) {
           Logger.getLogger(AffichageReservationTaxiController.class.getName()).log(Level.SEVERE, null, ex);
       } catch (FileNotFoundException ex) {
           Logger.getLogger(AffichageReservationTaxiController.class.getName()).log(Level.SEVERE, null, ex);
       } 
       catch (IOException ex) {
           Logger.getLogger(AffichageReservationTaxiController.class.getName()).log(Level.SEVERE, null, ex);
       }
       
          catch (SQLException ex) {
              Logger.getLogger(mypdfjava.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      
      
      
      
      
      
      
      
      public void pdfaffichedemandealluser( int etat  )  
              
      {  Document doc = new Document();
        Connection cnx = DataSource.getInstance().getCnx();
        //
      String sql = "SELECT * FROM demande_taxi D  INNER JOIN fos_user U ON D.iduser=U.id  where etat='"+etat+"'  Order by D.id";
       try {
        
      
         PreparedStatement pst;
        
              pst = cnx.prepareStatement(sql);
       
              
          
          ResultSet rs = pst.executeQuery();
          
          
        
           PdfWriter.getInstance(doc, new FileOutputStream("src/images/back.pdf"));
          
           doc.open();
           Image logo;
        
           logo = Image.getInstance("src/images/logo0.png");
         
           logo.scaleAbsoluteHeight(150);
           logo.scaleAbsoluteWidth(600);
           logo.setAlignment(Image.ALIGN_CENTER);
           
           doc.add(logo);
            doc.add(new Paragraph(" "));
            
            
           // String  nom= getUser(user);
            
            
            
            
           // String a= "Taxi Request Realized by " + nom ;
            //doc.add(new Paragraph(a ));
             doc.add(new Paragraph(" "));
            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);
            PdfPCell cell;
            
           cell = new PdfPCell( new Phrase("Nom",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
           
            cell = new PdfPCell( new Phrase("Prenom",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell); 
           
           
           
           cell = new PdfPCell( new Phrase("Region",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
           
           
           
           cell = new PdfPCell( new Phrase("Departure ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
            cell = new PdfPCell( new Phrase("Destination ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
           
            cell = new PdfPCell( new Phrase("Date ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
          doc.add(table);
           table.addCell(cell);
          
          
           cell = new PdfPCell( new Phrase("Time ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           doc.add(table);
           table.addCell(cell);
          
           cell = new PdfPCell( new Phrase("Price ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
           
       
          while(rs.next())
          {
          
         
         
          cell = new PdfPCell( new Phrase(rs.getString(11),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
           
              cell = new PdfPCell( new Phrase(rs.getString(22),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
         
           
           
           cell = new PdfPCell( new Phrase(rs.getString(5),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
           
           
           
           cell = new PdfPCell( new Phrase(rs.getString(3),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
         
           cell = new PdfPCell( new Phrase(rs.getString(4),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
        
           
           
           
            cell = new PdfPCell( new Phrase(rs.getString(7),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
           
            cell = new PdfPCell( new Phrase(rs.getString(6),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
            String price= String.valueOf(rs.getFloat(9));
            cell = new PdfPCell( new Phrase(price,FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
              
          }
          
           doc.add(table);
           
           doc.close();
           Desktop.getDesktop().open( new File ("src/images/back.pdf"));
          
        
                   
       }
          
        catch (DocumentException ex) {
           Logger.getLogger(AffichageReservationTaxiController.class.getName()).log(Level.SEVERE, null, ex);
       } catch (FileNotFoundException ex) {
           Logger.getLogger(AffichageReservationTaxiController.class.getName()).log(Level.SEVERE, null, ex);
       } 
       catch (IOException ex) {
           Logger.getLogger(AffichageReservationTaxiController.class.getName()).log(Level.SEVERE, null, ex);
       }
       
          catch (SQLException ex) {
              Logger.getLogger(mypdfjava.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      
      
      
      
       public void pdf01( Integer id , Integer etat , String state)  
              
      {  Document doc = new Document();
        Connection cnx = DataSource.getInstance().getCnx();
        //
      String sql = "SELECT * FROM demande_taxi D  INNER JOIN fos_user U ON D.iduser=U.id  where D.iduser='"+id+"' AND D.etat='"+etat+"' Order by D.date_d ";
       try {
        
      
         PreparedStatement pst;
        
              pst = cnx.prepareStatement(sql);
       
              
          
          ResultSet rs = pst.executeQuery();
          
          int nb= genererInt(0,100);
        String nompdf = "src/images/clientfront" + nb + ".pdf" ;
           PdfWriter.getInstance(doc, new FileOutputStream(nompdf));
          
           doc.open();
           Image logo;
        
           logo = Image.getInstance("src/images/logo0.png");
         
           logo.scaleAbsoluteHeight(150);
           logo.scaleAbsoluteWidth(600);
           logo.setAlignment(Image.ALIGN_CENTER);
           
           doc.add(logo);
            doc.add(new Paragraph(" "));
            
            
            String  nom= getUser(id);
            
            
            
            
            String a= " All " +nom+ " 's Taxi Request  "  + state ;
            doc.add(new Paragraph(a ));
             doc.add(new Paragraph(" "));
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            PdfPCell cell;
            /*
           cell = new PdfPCell( new Phrase("Nom",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
           
            cell = new PdfPCell( new Phrase("Prenom",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell); 
           
           */
           
           cell = new PdfPCell( new Phrase("Region",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
           
           
           
           cell = new PdfPCell( new Phrase("Departure ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
            cell = new PdfPCell( new Phrase("Destination ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
           
            cell = new PdfPCell( new Phrase("Date ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
          doc.add(table);
           table.addCell(cell);
          
          
           cell = new PdfPCell( new Phrase("Time ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           doc.add(table);
           table.addCell(cell);
          
           cell = new PdfPCell( new Phrase("Price ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
           
       
          while(rs.next())
          {
          
         /*
         
          cell = new PdfPCell( new Phrase(rs.getString(11),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
           
              cell = new PdfPCell( new Phrase(rs.getString(22),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
         */
           
           
           cell = new PdfPCell( new Phrase(rs.getString(5),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
           
           
           
           cell = new PdfPCell( new Phrase(rs.getString(3),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
         
           cell = new PdfPCell( new Phrase(rs.getString(4),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
        
           
          
           
            cell = new PdfPCell( new Phrase(rs.getString(7),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
           
            cell = new PdfPCell( new Phrase(rs.getString(6),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
            String price= String.valueOf(rs.getFloat(9));
            cell = new PdfPCell( new Phrase(price,FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
              
          }
          
           doc.add(table);
           
           doc.close();
           Desktop.getDesktop().open( new File (nompdf));
          
        
                   
       }
          
        catch (DocumentException ex) {
           Logger.getLogger(AffichageReservationTaxiController.class.getName()).log(Level.SEVERE, null, ex);
       } catch (FileNotFoundException ex) {
           Logger.getLogger(AffichageReservationTaxiController.class.getName()).log(Level.SEVERE, null, ex);
       } 
       catch (IOException ex) {
           Logger.getLogger(AffichageReservationTaxiController.class.getName()).log(Level.SEVERE, null, ex);
       }
       
          catch (SQLException ex) {
              Logger.getLogger(mypdfjava.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
       
       
       
       
       
       
       
       
          public void pdfreservation( Integer id )  
              
      {  Document doc = new Document();
        Connection cnx = DataSource.getInstance().getCnx();
        //
      String sql = "SELECT * FROM reservation_taxis R INNER JOIN  demande_taxi D ON  R.iddemande=D.id  INNER JOIN fos_user U ON D.iduser=U.id  where R.iduser='"+id+"'";
       try {
        
      
         PreparedStatement pst = cnx.prepareStatement(sql);
              
          
          ResultSet rs = pst.executeQuery();
          
          
           PdfWriter.getInstance(doc, new FileOutputStream("src/images/try1.pdf"));
           doc.open();
           Image logo = Image.getInstance("src/images/logo0.png");
           logo.scaleAbsoluteHeight(150);
           logo.scaleAbsoluteWidth(600);
           logo.setAlignment(Image.ALIGN_CENTER);
           
           doc.add(logo);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);
            PdfPCell cell;
            
            
           cell = new PdfPCell( new Phrase("Name",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
           
           
           cell = new PdfPCell( new Phrase("Username",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
           
           cell = new PdfPCell( new Phrase("Region",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
           
           
           
           cell = new PdfPCell( new Phrase("Departure ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
            cell = new PdfPCell( new Phrase("Destination ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
           
            cell = new PdfPCell( new Phrase("Date ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
          doc.add(table);
           table.addCell(cell);
          
          
           cell = new PdfPCell( new Phrase("Time ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           doc.add(table);
           table.addCell(cell);
          
           cell = new PdfPCell( new Phrase("Price ",FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
           table.addCell(cell);
           
           
       
          while(rs.next())
          {
          
         
           cell = new PdfPCell( new Phrase(rs.getString(14),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
          
           cell = new PdfPCell( new Phrase(rs.getString(25),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
           
           
         
           
           
           cell = new PdfPCell( new Phrase(rs.getString(8),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
           
           
           
           cell = new PdfPCell( new Phrase(rs.getString(6),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
         
           cell = new PdfPCell( new Phrase(rs.getString(7),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
        
           
           
           
            cell = new PdfPCell( new Phrase(rs.getString(10),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
           
            cell = new PdfPCell( new Phrase(rs.getString(9),FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
           
            String price= String.valueOf(rs.getFloat(12));
            cell = new PdfPCell( new Phrase(price,FontFactory.getFont("Comic Sans MS", 12)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setBackgroundColor(BaseColor.WHITE);
           table.addCell(cell);
              
          }
          
           doc.add(table);
           
           doc.close();
           Desktop.getDesktop().open( new File ("src/images/try1.pdf"));
          
        
                   
       }
          
        catch (DocumentException ex) {
           Logger.getLogger(AffichageReservationTaxiController.class.getName()).log(Level.SEVERE, null, ex);
       } catch (FileNotFoundException ex) {
           Logger.getLogger(AffichageReservationTaxiController.class.getName()).log(Level.SEVERE, null, ex);
       } 
       catch (IOException ex) {
           Logger.getLogger(AffichageReservationTaxiController.class.getName()).log(Level.SEVERE, null, ex);
       }
      catch (SQLException ex) {
              Logger.getLogger(AffichageReservationTaxiController.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
}
