package app.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import app.model.RootToryModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RootToryController {

    @FXML
    private TextField tf_nazwa;

    @FXML
    private TextField tf_adres;

    @FXML
    private TextField tf_kod_poczt;

    @FXML
    private TextField tf_miasto;

    @FXML
    private TextField tf_telefon;

    @FXML
    private Button btn_addTor;

    @FXML
    private Button btn_editTor;

    @FXML
    private Button btn_deleteTor;

    @FXML
    private Button btn_goBack;

    @FXML
    private TableView<RootToryModel> tbl_tory;
    
    @FXML
    private TableColumn<RootToryModel, Integer> tblc_id;

    @FXML
    private TableColumn<RootToryModel,String> tblc_nazwa;

    @FXML
    private TableColumn<RootToryModel,String> tblc_adres;

    @FXML
    private TableColumn<RootToryModel,String> tblc_kod;

    @FXML
    private TableColumn<RootToryModel,String> tblc_miasto;

    @FXML
    private TableColumn<RootToryModel,String> tblc_telefon;

    
    public ObservableList<RootToryModel> dane = FXCollections.observableArrayList();
    PreparedStatement ps;
    Stage stage;
    Integer id_selected;
    Parent Tory;
    
    @FXML
    void actionAdd(MouseEvent event) {
	
    	if (!tf_nazwa.getText().equals("") && !tf_adres.getText().equals("") && !tf_kod_poczt.getText().equals("")
    		&& !tf_miasto.getText().equals("") && !tf_telefon.getText().equals("")) {
		LoginController.connection();
		PreparedStatement ps;
		try {
			ps = LoginController.conn.prepareStatement("insert into tory (nazwa, adres, kod_pocztowy, miasto, telefon)" + "values (?,?,?,?,?)");
			ps.setString(1, tf_nazwa.getText());
			ps.setString(2, tf_adres.getText());
			ps.setString(3, tf_kod_poczt.getText());
			ps.setString(4, tf_miasto.getText());
			ps.setString(5, tf_telefon.getText());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
    	}
    	else {
    		LoginController.alertError("B³¹d", "Brak danych!", "Wszystkie pola musz¹ byæ wype³nione" ); 
    	}
		tf_nazwa.clear();
    	tf_adres.clear();
    	tf_kod_poczt.clear();
    	tf_miasto.clear();
    	tf_telefon.clear();
    	select();		
    }
    
    
    @FXML
    void actionDelete(MouseEvent event) {
    	try {
        	id_selected = tbl_tory.getSelectionModel().getSelectedItem().getId_tr(); 
        	}
        	catch (Exception e) {
        		LoginController.alertError("B³¹d", "Wybierz tor", "Aby usun¹æ tor musisz go najpierw zaznaczyæ");
        	}
        	
    		LoginController.connection();
        	try {
        	ps = LoginController.conn.prepareStatement("DELETE FROM tory WHERE id_tr=?");
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
    	try {
        	id_selected = tbl_tory.getSelectionModel().getSelectedItem().getId_tr(); 
        	}
        	catch (Exception e) {
        		LoginController.alertError("B³¹d","Wybierz tor!" ,"Aby edytowaæ tor musisz go najpierw zaznaczyæ");
        	}
    }

    @FXML
    void actionGoBack(MouseEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

    
    private void select() {
    	
    	LoginController.connection();
    	dane.clear();
    	tbl_tory.setItems(dane);
    	try {
    	ps = LoginController.conn.prepareStatement("SELECT * FROM tory;");
        ResultSet rs = ps.executeQuery();
    	while(rs.next()) {
    			dane.add(new RootToryModel(
    					rs.getInt("id_tr"),
    					rs.getString("nazwa"),
    					rs.getString("adres"),
    					rs.getString("miasto"),
    					rs.getString("kod_pocztowy"),
    					rs.getString("telefon")));
    	}
    	
//    	wpisujemy wartoœci do obiektów kolumn tabeli
    	tblc_id.setCellValueFactory(new PropertyValueFactory<RootToryModel,Integer>("id_tr"));
    	tblc_nazwa.setCellValueFactory(new PropertyValueFactory<RootToryModel,String>("nazwa"));
    	tblc_adres.setCellValueFactory(new PropertyValueFactory<RootToryModel,String>("adres"));
    	tblc_kod.setCellValueFactory(new PropertyValueFactory<RootToryModel,String>("miasto"));
    	tblc_miasto.setCellValueFactory(new PropertyValueFactory<RootToryModel,String>("kod_pocztowy"));
    	tblc_telefon.setCellValueFactory(new PropertyValueFactory<RootToryModel,String>("telefon"));
    	
//    	dodanie danych do tabeli view w postaci obiektu ObservableList
    	tbl_tory.setItems(null);
    	tbl_tory.setItems(dane);
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }

    
    public void initialize() {
    	select();
    
//    	tbl_tory.setEditable(true);
//    	tblc_nazwa.setCellFactory(TextFieldTableCell.forTableColumn());
    }	
    
//    String nazwa_sel;
//        
//    public void editNazwa(CellEditEvent edittedCell) {
//  	
//    	nazwa_sel = tbl_tory.getSelectionModel().getSelectedItem().getNazwa();
//    	nazwa_sel.setNazwa(edittedCell.getNewValue().toString());
//    			
//    }
//    
////    ObservableList<RootToryModel> 
//    
//    dane.addListener(new ListChangeListener() 	{
//    @Override
//    public void onChanged(ListChangeListener.Change change) {
//    	System.out.println("Detected a change! ");
//    	while (change.next()) {
//    		System.out.println("Was added? " + change.wasAdded());
//    		System.out.println("Was removed? " + change.wasRemoved());
//    	}
    		
    
}
