package app.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import app.model.InstruktorMainModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InstruktorMainController {

    @FXML
    private TableView<InstruktorMainModel> tbl_grupy;

    @FXML
    private TableColumn<InstruktorMainModel, Integer> tblc_id_gr;

    @FXML
    private TableColumn<InstruktorMainModel, String> tblc_nazwa;

    @FXML
    private TableColumn<InstruktorMainModel, String> tblc_data;

    @FXML
    private TableColumn<InstruktorMainModel, String> tblc_szkolenie;

    @FXML
    private TableColumn<InstruktorMainModel, String> tblc_tor;

    @FXML
    private TableColumn<InstruktorMainModel, String> tblc_czas_tr;

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
    private TableView<InstruktorMainModel> tbl_kursanci_wgrupach;

    @FXML
    private TableColumn<InstruktorMainModel, Integer> tblc_grupa;

    @FXML
    private TableColumn<InstruktorMainModel, String> tblc_imie;

    @FXML
    private TableColumn<InstruktorMainModel, String> tblc_nazwisko;

    @FXML
    private TableColumn<InstruktorMainModel, String> tblc_email;

    @FXML
    private TableColumn<InstruktorMainModel, String> tblc_telefon;
    

    public ObservableList<InstruktorMainModel> dane = FXCollections.observableArrayList();
    public ObservableList<InstruktorMainModel> kursanci = FXCollections.observableArrayList();
    PreparedStatement ps;
    Stage stage;
    Parent parent;  
    int id_selected, gr_selected;
    
//    metoda pobiera zmienn¹ statyczn¹ id_l z LoginController wed³ug którego wysy³a selecta do bazy zwracaj¹cego grupy pryzpisane do danego instruktora 
    
    @FXML
    void actionShowKr(MouseEvent event) {
    	try {
        	gr_selected = tbl_grupy.getSelectionModel().getSelectedItem().getId_gr();
        	}
        	catch (Exception e) {
        		LoginController.alertError("B³¹d", "Wybierz grupê", "Zaznacz grupê aby wyœwietliæ uczestników szkolenia.");
        	}
        	LoginController.connection(); 
        	kursanci.clear();
        	tbl_kursanci_wgrupach.setItems(kursanci);
        	try {
            	ps = LoginController.conn.prepareStatement("SELECT grupa, imie, nazwisko, email, telefon FROM kursanci_w_gr_opis WHERE grupa =?");
            	ps.setInt(1, gr_selected);
                ResultSet rs = ps.executeQuery();
            	while(rs.next()) {
            			kursanci.add(new InstruktorMainModel(
            					rs.getInt("grupa"),
            					rs.getString("imie"),
            					rs.getString("nazwisko"),
            					rs.getString("email"),
            					rs.getString("telefon")));
            	}
            	tblc_grupa.setCellValueFactory(new PropertyValueFactory<InstruktorMainModel, Integer>("grupa"));
            	tblc_imie.setCellValueFactory(new PropertyValueFactory<InstruktorMainModel, String>("imie"));
            	tblc_nazwisko.setCellValueFactory(new PropertyValueFactory<InstruktorMainModel, String>("nazwisko"));
            	tblc_email.setCellValueFactory(new PropertyValueFactory<InstruktorMainModel, String>("email"));
            	tblc_telefon.setCellValueFactory(new PropertyValueFactory<InstruktorMainModel, String>("telefon"));
            	
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

   
// metoda wykonuje zapytanie do DB i wyœwietla je w tabeli w widoku
    
    private void select() {
    	LoginController.connection();
    	dane.clear();
    	tbl_grupy.setItems(dane);
    	try {
    	ps = LoginController.conn.prepareStatement("SELECT id_gr, nazwa_grupy, data_szkolenia, szkolenie, tor, czas_trwania FROM grupy_instruktora_log where id_l=?;");
    	ps.setInt(1, LoginController.id_l);
    	
    	
    	ResultSet rs = ps.executeQuery();
    	while(rs.next()) { 
    			dane.add(new InstruktorMainModel(
    					rs.getInt("id_gr"),
    					rs.getString("nazwa_grupy"),
    					rs.getString("data_szkolenia"),
    					rs.getString("szkolenie"),
    					rs.getString("tor"),
    					rs.getInt("czas_trwania")));
    	}
    	
    	tblc_id_gr.setCellValueFactory(new PropertyValueFactory<InstruktorMainModel,Integer>("id_gr"));
    	tblc_nazwa.setCellValueFactory(new PropertyValueFactory<InstruktorMainModel,String>("nazwa_grupy"));
    	tblc_data.setCellValueFactory(new PropertyValueFactory<InstruktorMainModel,String>("data_szkolenia"));
    	tblc_szkolenie.setCellValueFactory(new PropertyValueFactory<InstruktorMainModel,String>("szkolenie"));
    	tblc_tor.setCellValueFactory(new PropertyValueFactory<InstruktorMainModel,String>("tor"));
    	tblc_czas_tr.setCellValueFactory(new PropertyValueFactory<InstruktorMainModel,String>("czas_trwania"));

    	tbl_grupy.setItems(null);
    	tbl_grupy.setItems(dane);
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public void initialize() {
    	select();  	
    }
    
    
}
