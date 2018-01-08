package app.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import app.model.RootUsersModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RootUsersController {

    @FXML
    private TableView<RootUsersModel> tbl_kursanci;

    @FXML
    private TableColumn<RootUsersModel, Integer> tblc_id;

    @FXML
    private TableColumn<RootUsersModel, String> tblc_imie;

    @FXML
    private TableColumn<RootUsersModel, String> tblc_nazwisko;

    @FXML
    private TableColumn<RootUsersModel, String> tblc_email;

    @FXML
    private TableColumn<RootUsersModel, String> tblc_telefon;

    @FXML
    private TextField tf_imie;

    @FXML
    private TextField tf_nazwisko;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_telefon;

    @FXML
    private Button btn_addKursanta;

    @FXML
    private Button btn_editKursanta;

    @FXML
    private Button btn_deleteKursanta;

    @FXML
    private Button btn_goBack;
    
    @FXML
    private TextField tf_nrGrupy;

    @FXML
    private Button btn_addToGroup;

    public ObservableList<RootUsersModel> dane = FXCollections.observableArrayList();
    PreparedStatement ps;
    Stage stage;
    Integer user_selected;
    static int id_selected;
    Parent User;
    String user_sel;

    
    @FXML
    void actionAdd(MouseEvent event) {
//    	walidacja czy pola nie s¹ puste
    	if (!tf_imie.getText().equals("") && !tf_nazwisko.getText().equals("") 
    			&& !tf_email.getText().equals("") && !tf_telefon.getText().equals("")) {
    		
    	LoginController.connection();
		PreparedStatement ps;
		try {
			ps = LoginController.conn.prepareStatement("insert into kursanci (imie, nazwisko, email, telefon)" + "values (?,?,?,?)");
			ps.setString(1, tf_imie.getText());
			ps.setString(2, tf_nazwisko.getText());
			ps.setString(3, tf_email.getText());
			ps.setString(4, tf_telefon.getText());		
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

    	} else {
    		LoginController.alertError("B³¹d", "Brak danych", "Wszystkie pola musz¹ byæ wype³nione!");
    	}
		tf_imie.clear();
		tf_nazwisko.clear();
		tf_email.clear();
    	tf_telefon.clear();
    	select();
    }

    
    @FXML
    void actionAddToGr(MouseEvent event) {
    	try {
        	user_selected = tbl_kursanci.getSelectionModel().getSelectedItem().getId_k();
        	}
        	catch (Exception e) {
        		LoginController.alertError("B³¹d", "Wybierz kursanta", "Zaznacz kursanta aby dodaæ go do grupy.");
        	}
    	user_sel = user_selected.toString();
    	
    	if (!tf_nrGrupy.getText().equals("")) {
    	LoginController.connection();
		PreparedStatement ps;
		try {
			ps = LoginController.conn.prepareStatement("insert into kursanci_w_grupach (id_gr, id_k)" + "values (?,?)");
			ps.setString(1, tf_nrGrupy.getText());
			ps.setString(2, user_selected.toString());
					
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

    	} else {
    		LoginController.alertError("B³¹d", "Brak danych", "Wszystkie pola musz¹ byæ wype³nione!");
    	}
				
		tf_nrGrupy.clear();
    	select();
    }
   
    
    @FXML
    void actionDelete(MouseEvent event) {
    	try {
        	id_selected = tbl_kursanci.getSelectionModel().getSelectedItem().getId_k(); 
        	}
        	catch (Exception e) {
        		LoginController.alertError("B³¹d", "Zaznacz kursanta", "Aby usun¹æ kursanta musisz go najpierw zaznaczyæ");
        	}
        	
    		LoginController.connection();
        	try {
        	ps = LoginController.conn.prepareStatement("DELETE FROM kursanci WHERE id_k=?");
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
        	id_selected = tbl_kursanci.getSelectionModel().getSelectedItem().getId_k(); 
        	}
        	catch (Exception e) {
        		LoginController.alertError("B³¹d", "Zaznacz kursanta", "Aby usun¹æ kursanta musisz go najpierw zaznaczyæ");
        	}
    	Stage editStage = new Stage();
    	Parent UserEdit = null; 
    	try { 
    		UserEdit = (Parent) FXMLLoader.load(getClass().getResource("/app/view/RootUserEditView.fxml"));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	Scene scene = new Scene(UserEdit);
    	editStage.setScene(scene);
    	editStage.setTitle("Edytuj kursanta");
    	editStage.setResizable(false);
    	editStage.show();
    }

    @FXML
    void actionGoBack(MouseEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public void select() {
    	LoginController.connection();
    	dane.clear();
    	tbl_kursanci.setItems(dane);
    	try {
    	ps = LoginController.conn.prepareStatement("SELECT * FROM kursanci;");
        ResultSet rs = ps.executeQuery();
    	while(rs.next()) {
    			dane.add(new RootUsersModel(
    					rs.getInt("id_k"),
    					rs.getString("imie"),
    					rs.getString("nazwisko"),
    					rs.getString("email"),
    					rs.getString("telefon")));
    	}
    	
    	tblc_id.setCellValueFactory(new PropertyValueFactory<RootUsersModel,Integer>("id_k"));
    	tblc_imie.setCellValueFactory(new PropertyValueFactory<RootUsersModel,String>("imie"));
    	tblc_nazwisko.setCellValueFactory(new PropertyValueFactory<RootUsersModel,String>("nazwisko"));
    	tblc_email.setCellValueFactory(new PropertyValueFactory<RootUsersModel,String>("email"));
    	tblc_telefon.setCellValueFactory(new PropertyValueFactory<RootUsersModel,String>("telefon"));
    	tbl_kursanci.setItems(null);
    	tbl_kursanci.setItems(dane);
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public void initialize() {
    	select();  	
    }
    
    
}
