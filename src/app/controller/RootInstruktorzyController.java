package app.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import app.model.RootInstruktorzyModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class RootInstruktorzyController {

    @FXML
    private TableView<RootInstruktorzyModel> tbl_instruktorzy;

    @FXML
    private TableColumn<RootInstruktorzyModel, Integer> tblc_id;

    @FXML
    private TableColumn<RootInstruktorzyModel, String> tblc_imie;

    @FXML
    private TableColumn<RootInstruktorzyModel, String> tblc_nazwisko;

    @FXML
    private TableColumn<RootInstruktorzyModel, String> tblc_email;

    @FXML
    private TableColumn<RootInstruktorzyModel, String> tblc_telefon;
    
    @FXML
    private TableColumn<RootInstruktorzyModel, String> tblc_login;

    @FXML
    private TextField tf_imie;

    @FXML
    private TextField tf_nazwisko;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_telefon;

    @FXML
    private Button btn_addInstr;

    @FXML
    private Button btn_editInstr;

    @FXML
    private Button btn_deleteInstr;
    
    @FXML
    private TextField tf_login;

    @FXML
    private PasswordField pf_passwd;
    
    @FXML
    private ComboBox<RootInstruktorzyController> cb_permission;

    @FXML
    private Button btn_addLogowanie;

    @FXML
    private Button btn_goBack;
    
    
    public ObservableList<RootInstruktorzyModel> dane = FXCollections.observableArrayList();
    PreparedStatement ps;
    Stage stage;
    Parent Instruktorzy; 
    int id_selected;
      
    
    @FXML
    void actionAdd(MouseEvent event) {
    	if (!tf_imie.getText().equals("") && !tf_nazwisko.getText().equals("") 
    			&& !tf_email.getText().equals("") && !tf_telefon.getText().equals("")) {
       	LoginController.connection();
       	PreparedStatement ps;
		try {
			ps = LoginController.conn.prepareStatement("insert into instruktorzy (imie, nazwisko, email, telefon)" + "values (?,?,?,?)");
			ps.setString(1, tf_imie.getText());
			ps.setString(2, tf_nazwisko.getText());
			ps.setString(3, tf_email.getText());
			ps.setString(4, tf_telefon.getText());		
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		} else {
    		LoginController.alertError("B³¹d", "Brak danych!", "Wszystkie pola musz¹ byæ wype³nione!");
    	}
		tf_imie.clear();
		tf_nazwisko.clear();
		tf_email.clear();
    	tf_telefon.clear();
    	select();
    }
    
    @FXML
    void actionAddLog(MouseEvent event) {
    	if  (!tf_login.getText().equals("") && !pf_passwd.getText().equals("")) { 
        	try {
            	id_selected = tbl_instruktorzy.getSelectionModel().getSelectedItem().getId_i(); 
            	}
            	catch (Exception e) {
            		LoginController.alertError("B³¹d", "Zaznacz instruktora", "Zaznacz instruktora któremu chcesz nadaæ login i has³o.");
            	}
        	
        	LoginController.connection();
        	PreparedStatement ps;
        	
        	try {
        		ps = LoginController.conn.prepareStatement("insert into logowanie (login, haslo, permission, id_i)" + "values(?,?,?,?)" );
        		ps.setString(1,tf_login.getText());
        		ps.setString(2, pf_passwd.getText());
        		ps.setString(3, "1");
        		ps.setInt(4, id_selected);
        		ps.executeUpdate();
        	} catch (SQLException e) {
    			e.printStackTrace();
        	}
        	}
        	else {
        		LoginController.alertError("B³¹d", "Wpisz dane!", "Wype³nij pola login i has³o!");
        	}
        	tf_login.clear();
        	pf_passwd.clear();
        	id_selected = 0;
        	select();  
    }
    
    @FXML
    void actionDelete(MouseEvent event) {
    	try {
        	id_selected = tbl_instruktorzy.getSelectionModel().getSelectedItem().getId_i(); 
        	}
        	catch (Exception e) {
        		LoginController.alertError("B³¹d", "Wybierz instruktora", "Aby usun¹æ instruktora musisz go najpierw zaznaczyæ");
        	}
        	
        	LoginController.connection();
        	try {
        	ps = LoginController.conn.prepareStatement("DELETE FROM instruktorzy WHERE id_i=?");
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
    	tbl_instruktorzy.setItems(dane);
    	try {
    	ps = LoginController.conn.prepareStatement("SELECT * FROM instruktorzy_root;");
        ResultSet rs = ps.executeQuery();
    	while(rs.next()) {
    			dane.add(new RootInstruktorzyModel(
    					rs.getInt("id_i"),
    					rs.getString("imie"),
    					rs.getString("nazwisko"),
    					rs.getString("email"),
    					rs.getString("telefon"),
    					rs.getString("login")));
    	}
    	
    	tblc_id.setCellValueFactory(new PropertyValueFactory<RootInstruktorzyModel,Integer>("id_i"));
    	tblc_imie.setCellValueFactory(new PropertyValueFactory<RootInstruktorzyModel,String>("imie"));
    	tblc_nazwisko.setCellValueFactory(new PropertyValueFactory<RootInstruktorzyModel,String>("nazwisko"));
    	tblc_email.setCellValueFactory(new PropertyValueFactory<RootInstruktorzyModel,String>("email"));
    	tblc_telefon.setCellValueFactory(new PropertyValueFactory<RootInstruktorzyModel,String>("telefon"));
    	tblc_login.setCellValueFactory(new PropertyValueFactory<RootInstruktorzyModel,String>("login"));
    	tbl_instruktorzy.setItems(null);
    	tbl_instruktorzy.setItems(dane);
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public void initialize() {
    	select();  	
    }
    
}