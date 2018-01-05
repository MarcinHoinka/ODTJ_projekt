package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.database.DBConnector;
import app.model.RootGrupyModel;
import app.model.RootInstruktorzyModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RootGrupyController {

    @FXML
    private TableView<RootGrupyModel> tbl_grupy;

    @FXML
    private TableColumn<RootGrupyModel, Integer> tblc_id_gr;

    @FXML
    private TableColumn<RootGrupyModel, String> tblc_nazwa;

    @FXML
    private TableColumn<RootGrupyModel, String> tblc_data;

    @FXML
    private TableColumn<RootGrupyModel, String> tblc_instruktor;

    @FXML
    private TableColumn<RootGrupyModel, String> tblc_szkolenie;
    
    @FXML
    private TableColumn<RootGrupyModel, String> tblc_tor;

    @FXML
    private TableColumn<RootGrupyModel, Integer> tblc_czas_tr;

    @FXML
    private TextField tf_nazwa;

    @FXML
    private TextField tf_data;
    
    @FXML
    private ComboBox<RootGrupyModel> cb_instr;
    
    @FXML
    private ComboBox<RootGrupyModel> cb_szkolenie;

    @FXML
    private ComboBox<RootGrupyModel> cb_tor;

    @FXML
    private Button btn_showKursanci;
    
    @FXML
    private Button btn_addInstr;

    @FXML
    private Button btn_editInstr;

    @FXML
    private Button btn_deleteInstr;

    @FXML
    private Button btn_goBack;

    @FXML
    private TableView<RootGrupyModel> tbl_kursanci_wgrupach;

    @FXML
    private TableColumn<RootGrupyModel, Integer> tblc_grupa;

    @FXML
    private TableColumn<RootGrupyModel, String> tblc_imie;

    @FXML
    private TableColumn<RootGrupyModel, String> tblc_nazwisko;

    @FXML
    private TableColumn<RootGrupyModel, String> tblc_email;

    @FXML
    private TableColumn<RootGrupyModel, String> tblc_telefon;

    
    public ObservableList<RootGrupyModel> dane = FXCollections.observableArrayList();
    public ObservableList<RootGrupyModel> kursanci = FXCollections.observableArrayList();
    public ObservableList<RootGrupyModel> instruktorzy = FXCollections.observableArrayList();
    ComboBox<String> instruktorzy_cb;
    PreparedStatement ps;

    Stage stage;
    Parent Grupy; 
    int id_selected, grupa_selected;
    String instruktor_c;
    Integer id_i;
    
    
    
    @FXML
    void actionAdd(MouseEvent event) {
    		
    }

    @FXML
    void actionDelete(MouseEvent event) {
    	try {
        	id_selected = tbl_grupy.getSelectionModel().getSelectedItem().getId_gr(); 
        	}
        	catch (Exception e) {
        		LoginController.alertError("B³¹d", "Wybierz grupê", "Aby usun¹æ grupê musisz j¹ najpierw zaznaczyæ");
        	}
        	
        	LoginController.connection();
        	try {
        	ps = LoginController.conn.prepareStatement("DELETE FROM grupy WHERE id_gr=?");
        	ps.setInt(1, id_selected);
        	ps.executeUpdate();
        	}
        	catch (SQLException e) {
        		e.printStackTrace();
        	}
        	select();
    }

    @FXML
    void actionEdit(MouseEvent event) {

    }
    
    @FXML
    void actionShowKr(MouseEvent event) {
    	try {
    	grupa_selected = tbl_grupy.getSelectionModel().getSelectedItem().getId_gr();
    	}
    	catch (Exception e) {
    		LoginController.alertError("B³¹d", "Wybierz grupê", "Zaznacz grupê aby wyœwietliæ uczestników szkolenia.");
    	}
    	LoginController.connection(); 
    	kursanci.clear();
    	tbl_kursanci_wgrupach.setItems(kursanci);
    	try {
        	ps = LoginController.conn.prepareStatement("SELECT grupa, imie, nazwisko, email, telefon FROM kursanci_w_gr_opis WHERE grupa =?");
//        	System.out.println("SELECT grupa, imie, nazwisko, email, telefon FROM kursanci_w_gr_opis WHERE grupa ="+grupa_selected);
        	ps.setInt(1, grupa_selected);
            ResultSet rs = ps.executeQuery();
        	while(rs.next()) {
        			kursanci.add(new RootGrupyModel(
        					rs.getInt("grupa"),
        					rs.getString("imie"),
        					rs.getString("nazwisko"),
        					rs.getString("email"),
        					rs.getString("telefon")));
        	}
        	tblc_grupa.setCellValueFactory(new PropertyValueFactory<RootGrupyModel, Integer>("grupa"));
        	tblc_imie.setCellValueFactory(new PropertyValueFactory<RootGrupyModel, String>("imie"));
        	tblc_nazwisko.setCellValueFactory(new PropertyValueFactory<RootGrupyModel, String>("nazwisko"));
        	tblc_email.setCellValueFactory(new PropertyValueFactory<RootGrupyModel, String>("email"));
        	tblc_telefon.setCellValueFactory(new PropertyValueFactory<RootGrupyModel, String>("telefon"));
        	
        	tbl_kursanci_wgrupach.setItems(null);
        	tbl_kursanci_wgrupach.setItems(kursanci);
        	
    } catch (SQLException e) {
    	e.printStackTrace();
    	}
    }
    
  
    @FXML
    void actionGoBack(MouseEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

    
    private void select() {
    	LoginController.connection();
    	dane.clear();
    	tbl_grupy.setItems(dane);
    	try {
    	ps = LoginController.conn.prepareStatement("SELECT id_gr, nazwa_grupy, data_szkolenia, instruktor, szkolenie, tor, czas_trwania FROM grupy_opis;");
        ResultSet rs = ps.executeQuery();
    	while(rs.next()) {
    			dane.add(new RootGrupyModel(
    					rs.getInt("id_gr"),
    					rs.getString("nazwa_grupy"),
    					rs.getString("data_szkolenia"),
    					rs.getString("instruktor"),
    					rs.getString("szkolenie"),
    					rs.getString("tor"),
    					rs.getInt("czas_trwania")));
    	}
    	
    	tblc_id_gr.setCellValueFactory(new PropertyValueFactory<RootGrupyModel,Integer>("id_gr"));
    	tblc_nazwa.setCellValueFactory(new PropertyValueFactory<RootGrupyModel,String>("nazwa_grupy"));
    	tblc_data.setCellValueFactory(new PropertyValueFactory<RootGrupyModel,String>("data_szkolenia"));
    	tblc_instruktor.setCellValueFactory(new PropertyValueFactory<RootGrupyModel,String>("instruktor"));
    	tblc_szkolenie.setCellValueFactory(new PropertyValueFactory<RootGrupyModel,String>("szkolenie"));
    	tblc_tor.setCellValueFactory(new PropertyValueFactory<RootGrupyModel,String>("tor"));
    	tblc_czas_tr.setCellValueFactory(new PropertyValueFactory<RootGrupyModel,Integer>("telefon"));

    	tbl_grupy.setItems(null);
    	tbl_grupy.setItems(dane);
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
   
//    ###################################################################################################################################
    
    @FXML
    void selectInstr(MouseEvent event) {
    	
// metoda do przekazywania listy instruktorów z tabeli bazodanowej do ComboBox u¿ywanego do dodawania instruktorów do nowych grup
    	
    	instruktorzy_cb = new ComboBox<>();
    	
    	LoginController.connection();
    	instruktorzy.clear();
    	instruktorzy_cb.getItems(instruktorzy);
    	try {
    	ps = LoginController.conn.prepareStatement("SELECT * FROM instruktorzy_cc;");
        ResultSet rs = ps.executeQuery();
    	while(rs.next()) {
    			instruktorzy.add(new RootGrupyModel(
    					rs.getInt("id_i"),
    					rs.getString("instruktor_c")));
    			}
    	instruktorzy_cb.setItems(null);
    	instruktorzy_cb.setItems(instruktorzy);
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
 	

    	

    
    public void initialize() {
    	select();
    	
    	
    }
    
}
