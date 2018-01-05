package app.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.database.DBConnector;
import app.model.RootKursyModel;
import app.model.RootToryModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RootKursyController {

    @FXML
    private TableView<RootKursyModel> tbl_szkolenia;

    @FXML
    private TableColumn<RootKursyModel, Integer> tblc_id;

    @FXML
    private TableColumn<RootKursyModel, String> tblc_nazwaSz;

    @FXML
    private TableColumn<RootKursyModel, String> tblc_opis;

    @FXML
    private TableColumn<RootKursyModel, Integer> tblc_czasTrw;

    @FXML
    private TextField tf_nazwa;

    @FXML
    private TextField tf_czasTrw;

    @FXML
    private TextArea ta_opisSz;

    @FXML
    private Button btn_addSz;

    @FXML
    private Button btn_editSz;

    @FXML
    private Button btn_deleteSz;

    @FXML
    private Button btn_goBack;

    public ObservableList<RootKursyModel> dane = FXCollections.observableArrayList();
    PreparedStatement ps;
    DBConnector db;
    Stage stage;
    Integer id_selected;
    Parent Kursy;  
    

    @FXML
    void actionAdd(MouseEvent event) {
    	if (!tf_nazwa.getText().equals("") && !tf_czasTrw.getText().equals("") 
    			&& !ta_opisSz.getText().equals("")) {
       	LoginController.connection();
       	PreparedStatement ps;
		try {
			ps = LoginController.conn.prepareStatement("insert into szkolenia (nazwa_sz, opis, czas_trwania)" + "values (?,?,?)");
			ps.setString(1, tf_nazwa.getText());
			ps.setString(2, ta_opisSz.getText());
			ps.setString(3, tf_czasTrw.getText());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		} else {
    		LoginController.alertError("B³¹d", "Brak danych!", "Wszystkie pola musz¹ byæ wype³nione!");
    	}
		tf_nazwa.clear();
		ta_opisSz.clear();
		tf_czasTrw.clear();
    	select();
    }

    @FXML
    void actionDelete(MouseEvent event) {
    	try {
        	id_selected = tbl_szkolenia.getSelectionModel().getSelectedItem().getId_sz(); 
        	}
        	catch (Exception e) {
        		LoginController.alertError("B³¹d", "Wybierz szkolenie", "Aby usun¹æ modu³ szkolenia musisz go najpierw zaznaczyæ");
        		
        	}
        	
    		LoginController.connection();
        	try {
        	ps = LoginController.conn.prepareStatement("DELETE FROM szkolenia WHERE id_sz=?");
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
    void actionGoBack(MouseEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

    private void select() {
    	
    	LoginController.connection();
    	dane.clear();
    	tbl_szkolenia.setItems(dane);
    	try {
    	ps = LoginController.conn.prepareStatement("SELECT id_sz, nazwa_sz, opis, czas_trwania FROM szkolenia;");
        ResultSet rs = ps.executeQuery();
    	while(rs.next()) {
    			dane.add(new RootKursyModel(
    					rs.getInt("id_sz"),
    					rs.getString("nazwa_sz"),
    					rs.getString("opis"),
    					rs.getInt("czas_trwania")));
    	}
    	
//    	wpisujemy wartoœci do obiektów kolumn tabeli
    	tblc_id.setCellValueFactory(new PropertyValueFactory<RootKursyModel,Integer>("id_sz"));
    	tblc_nazwaSz.setCellValueFactory(new PropertyValueFactory<RootKursyModel,String>("nazwa_sz"));
    	tblc_opis.setCellValueFactory(new PropertyValueFactory<RootKursyModel,String>("opis"));
    	tblc_czasTrw.setCellValueFactory(new PropertyValueFactory<RootKursyModel,Integer>("czas_trwania"));
    	
//    	dodanie danych do tabeli view w postaci obiektu ObservableList
    	tbl_szkolenia.setItems(null);
    	tbl_szkolenia.setItems(dane);
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    
    public void initialize() {
    	select();
    }
    
    
}
