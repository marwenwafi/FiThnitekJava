package fithnitek.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleNode;
import fithnitek.models.Category;
import fithnitek.utils.DataSource;
import fithnitek.models.Objectif;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.awt.Color;
import java.text.DecimalFormat;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author marwe
 */
public class ObjectifController implements Initializable {
    
    Connection cnx = DataSource.getInstance().getCnx();
    
    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXButton add;
    @FXML
    private JFXButton modify;
    @FXML
    private JFXButton chart;
    @FXML
    private JFXDatePicker start_date;
    @FXML
    private JFXDatePicker end_date;
    @FXML
    private JFXTextField but;
    @FXML
    private JFXComboBox<String> type;
    @FXML
    private JFXButton delete;
    @FXML
    private Pane details;
    @FXML
    private TableView<Objectif> tableview;
    @FXML
    private TableColumn<Objectif, String> title_c;
    @FXML
    private TableColumn<Objectif, String> desc_c;
    @FXML
    private TableColumn<Objectif, Number> but_c;
    @FXML
    private TableColumn<Objectif, Date> start_c;
    @FXML
    private TableColumn<Objectif, Date> end_c;
    @FXML
    private TableColumn<Objectif, String> type_c;
    @FXML
    private Label progress;
    @FXML
    private ProgressBar progressbar;
    @FXML
    private JFXToggleNode node;
    
    final SwingNode chartSwingNode = new SwingNode();
    String nature="";
    Date st,en;
    float res = 0f;
    
    private ObservableList<Objectif> data = FXCollections.observableArrayList();
    List<Objectif> list;
    Objectif rowData = null;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public void ajouter(Objectif o) {
        try {            
            String requete = "INSERT INTO Objectif (titre, description, type, but, etat, start_date, end_date) "
                    + "VALUES ('" +o.getTitre()+"','" +o.getDescription()+"','" + o.getType() + "','"+ o.getBut()+"',"
                    + "'"+o.getEtat()+"','"+o.getStart_date()+"','"+o.getEnd_date()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Objectif ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimer(Objectif o) {
        try {
            String requete = "DELETE FROM objectif WHERE id_objectif=" + o.getIdOjectif();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Objectif supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifier(Objectif o) {
        try {
            String requete = "UPDATE objectif SET titre='" + o.getTitre()+ "',description='" + o.getDescription()+ "',type='" + o.getType()+ "',"
                    + "but='"+ o.getBut()+"',etat='"+o.getEtat()+"',start_date='"+o.getStart_date()+"',end_date='"+o.getEnd_date()+"' WHERE id_objectif=" + o.getIdOjectif();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Objectif modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    

    public List<Objectif> afficher() {
        List<Objectif> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM objectif";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Objectif (rs.getInt("id_objectif"), rs.getString("titre"),rs.getString("description"),rs.getString("type"),
                        rs.getInt("but"),rs.getByte("etat"),rs.getDate("start_date"),rs.getDate("end_date")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    @FXML
    public void processKeyEvent(KeyEvent ev) {
        System.out.println("proce");
        String c = ev.getCharacter();
        if("1234567890".contains(c)) {}
        else {
            ev.consume();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
        tableview.setRowFactory( tv -> {
        TableRow<Objectif> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            rowData = row.getItem();
            if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                enableDelete();
                showDetails(rowData.getType(),rowData.getStart_date(),rowData.getEnd_date(),rowData.getBut());
            }
            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                loadRowToModify(rowData);
            }
        });
        return row ;
        });
    }
    
    @FXML
    private void addObjectif(MouseEvent event) throws Exception {
        Objectif c = new Objectif(title.getText(), description.getText(), type.getValue().toString(), Integer.parseInt(but.getText()),new Byte("0"),
                java.sql.Date.valueOf(start_date.getValue()), java.sql.Date.valueOf(end_date.getValue()));
        ajouter(c);
        refresh();
    }
    
    @FXML
    private void modifyObjectif(MouseEvent event) throws Exception {
        rowData.setTitre(title.getText());
        rowData.setDescription(description.getText());
        rowData.setBut(Integer.parseInt(but.getText()));
        rowData.setType(type.getValue());
        rowData.setStart_date(java.sql.Date.valueOf(start_date.getValue()));
        rowData.setEnd_date(java.sql.Date.valueOf(end_date.getValue()));
        modifier(rowData);
        refresh();
    }
        
    @FXML
    private void deleteObjectif(MouseEvent event) throws Exception {
        Objectif c = tableview.getSelectionModel().getSelectedItem();
        supprimer(c);
        refresh();
    }
    
    @FXML
    private void showChart(MouseEvent event) throws Exception
    {
        
        JFreeChart chart = null;
        String query = "";
        
        if (nature.contains("Utilisateurs"))
        {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            query = "Select Count(U.id), registrationdate from fos_user U where U.registrationdate Between '"+st+"' AND '"+en+"' GROUP BY U.registrationdate";
            try
            {   
                
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next())
                {
                   
                   dataset.addValue(rs.getInt(1), "", rs.getDate(2));    
                }
                
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            chart = ChartFactory.createBarChart(
                "Performance Users", null /* x-axis label*/, 
                    "New Users" /* y-axis label */, dataset);
            chart.setBackgroundPaint(Color.white);
            CategoryPlot plot = (CategoryPlot) chart.getPlot();

            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setDrawBarOutline(false);
            chart.getLegend().setFrame(BlockBorder.NONE);
        }
        else if (nature.contains("Revenues"))
        {
            DefaultPieDataset dataset =new DefaultPieDataset();
            dataset.setValue("Colis", queryBuilder("Revenues Colis", st, en)); 
            dataset.setValue("Covoiturage",queryBuilder("Revenues Covoiturage", st, en)); 
            dataset.setValue("Taxi",queryBuilder("Revenues Taxi", st, en));
            chart = ChartFactory.createPieChart("Revenues distribution",dataset,true, true,false);
            PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                "Revenues {0} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
            ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);
        }
        else
        {
            DefaultPieDataset dataset =new DefaultPieDataset();
            dataset.setValue("Colis", queryBuilder("Activites Colis", st, en)); 
            dataset.setValue("Covoiturage",queryBuilder("Activites Covoiturage", st, en)); 
            dataset.setValue("Taxi",queryBuilder("Activites Taxi", st, en));
            chart = ChartFactory.createPieChart("Activity distribution",dataset,true, true,false);
            PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                "Activites {0} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
            ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);
        }
        
        
        ChartViewer viewer = new ChartViewer(chart);
        Stage chartStage = new Stage(); 
        chartStage.setScene(new Scene(viewer));
        chartStage.setTitle("Chart"); 
        chartStage.setWidth(800);
        chartStage.setHeight(450);
        chartStage.show(); 
    }
    
    public void loadRowToModify(Objectif o)
    {
        title.setText(o.getTitre());
        description.setText(o.getDescription());
        type.getSelectionModel().select(o.getType());
        LocalDate mm = LocalDate.parse(o.getStart_date().toString(), formatter);
        start_date.setValue(mm);
        mm = LocalDate.parse(o.getEnd_date().toString(), formatter);
        end_date.setValue(mm);
        but.setText(""+o.getBut());
        modify.setDisable(false);
        add.setDisable(true);
    }
    
    public void refresh ()
    {tableview.getItems().clear();
        list = this.afficher();
        data.addAll(list);
        title_c.setCellValueFactory(new PropertyValueFactory<>("titre"));
        desc_c.setCellValueFactory(new PropertyValueFactory<>("description"));
        start_c.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        end_c.setCellValueFactory(new PropertyValueFactory<>("end_date"));
        type_c.setCellValueFactory(new PropertyValueFactory<>("type"));
        but_c.setCellValueFactory(new PropertyValueFactory<>("but"));
        tableview.setItems(data);
        title.setText("");
        description.setText("");
        but.setText("");
        type.getSelectionModel().select(0);
        start_date.setValue(null);
        end_date.setValue(null);
        nature = "";
        rowData = null;
        modify.setDisable(true);
        add.setDisable(false);
        delete.setDisable(true);
    }
    
    
    private void enableDelete() {
        delete.setDisable(false);
    }
    
     private void showDetails(String type,Date start, Date end, int but) {
        res = queryBuilder(type, start, end);
        progress.setText("Progress: "+res);
        progress.setVisible(true);
        progressbar.setProgress(res/but);
        chart.setDisable(false);
        nature = type;
        st = start;
        en = end;
        System.out.println(nature);
     }
         
     
    public float queryBuilder(String type,Date start, Date end)
    {
        String query = "";
        float result = 0f;

        if (type.equals("Nombre Utilisateurs"))
        {
            query = "Select Count(U.id) from fos_user U where U.registrationdate Between '"+start+"' AND '"+end+"'";
        }
        else if (type.equals("Revenues Totales"))
        {
            return queryBuilder("Revenues Colis", start, end)+ queryBuilder("Revenues Covoiturage", start, end) + queryBuilder("Revenues Taxi", start, end);
        }
        else if (type.equals("Activites Totales"))
        {
            return queryBuilder("Activites Colis", start, end)+ queryBuilder("Activites Covoiturage", start, end) + queryBuilder("Activites Taxi", start, end);
        }
        else if (type.equals("Revenues Colis"))
        {
            query = "Select SUM(RC.prix) from reservation_colis RC, offre_colis OC "
                    + "WHERE RC.idOffre = OC.id_offre_col AND OC.date_col Between '"+start+"' AND '"+end+"'";
        }
        else if (type.equals("Activites Colis"))
        {
            query = "SELECT COUNT(RC.id_reservation_colis) FROM reservation_colis RC, offre_colis OC "
                    + "WHERE RC.idOffre = OC.id_offre_col AND OC.date_col Between '"+start+"' AND '"+end+"'";
        }
        else if (type.equals("Revenues Covoiturage"))
        {
            query = "SELECT SUM(R.prixt) FROM reservation_covoiturage R, offre_covoiturage O "
                    + "WHERE R.idoffrer = O.idoffrecovoiturage AND O.date Between '"+start+"' AND '"+end+"'";
        }
        else if (type.equals("Activites Covoiturage"))
        {
            query = "SELECT COUNT(R.idreservationcov) FROM reservation_covoiturage R, offre_covoiturage O "
                    + "WHERE R.idoffrer = O.idoffrecovoiturage AND O.date Between '"+start+"' AND '"+end+"'";
        }
        else if (type.equals("Revenues Taxi"))
        {
            query = "SELECT SUM(T.prix) from demande_taxi T WHERE T.etat=1 AND T.date_d Between '"+start+"' AND '"+end+"'";
        }
        else if (type.equals("Activites Taxi"))
        {
            query = "SELECT COUNT(T.id) FROM demande_taxi T WHERE T.etat=1 AND T.date_d Between '"+start+"' AND '"+end+"'";
        }
        try
        {
            
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        result = rs.getFloat(1);
            System.out.println(result);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return result;
    }

}
