package app.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.javafx.menu.SeparatorMenuItemBase;

import app.model.InstruktorMainModel;
import app.model.UserMainModel;
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

public class UserMainController {

    @FXML
    private TableView<UserMainModel> tbl_grupy;

    @FXML
    private TableColumn<UserMainModel, Integer> tblc_id_gr;

    @FXML
    private TableColumn<UserMainModel, String> tblc_nazwa;

    @FXML
    private TableColumn<UserMainModel, String> tblc_instr;

    @FXML
    private TableColumn<UserMainModel, String> tblc_data;

    @FXML
    private TableColumn<UserMainModel, String> tblc_szkolenie;

    @FXML
    private TableColumn<UserMainModel, String> tblc_tor;

    @FXML
    private TableColumn<UserMainModel, Integer> tblc_czas_tr;

    @FXML
    private Button btn_goBack;

    
    public ObservableList<UserMainModel> dane = FXCollections.observableArrayList();
    PreparedStatement ps;
    Stage stage;
    Parent parent;  
    int id_selected;
    
    
    @FXML
    void actionGoBack(MouseEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

    private void select() {
    	LoginController.connection();
    	dane.clear();
    	tbl_grupy.setItems(dane);
    	try {
    	ps = LoginController.conn.prepareStatement("SELECT id_gr, nazwa_grupy, data_szkolenia, instruktor, szkolenie, tor, czas_trwania FROM grupy_kursanta_log where id_l=?;");
    	ps.setInt(1, LoginController.id_l);    	
    	ResultSet rs = ps.executeQuery();
    	while(rs.next()) { 
    			dane.add(new UserMainModel(
    					rs.getInt("id_gr"),
    					rs.getString("nazwa_grupy"),
    					rs.getString("data_szkolenia"),
    					rs.getString("instruktor"),
    					rs.getString("szkolenie"),
    					rs.getString("tor"),
    					rs.getInt("czas_trwania")));
    	}
    	
    	tblc_id_gr.setCellValueFactory(new PropertyValueFactory<UserMainModel,Integer>("id_gr"));
    	tblc_nazwa.setCellValueFactory(new PropertyValueFactory<UserMainModel,String>("nazwa_grupy"));
    	tblc_data.setCellValueFactory(new PropertyValueFactory<UserMainModel,String>("data_szkolenia"));
    	tblc_instr.setCellValueFactory(new PropertyValueFactory<UserMainModel,String>("instruktor"));
    	tblc_szkolenie.setCellValueFactory(new PropertyValueFactory<UserMainModel,String>("szkolenie"));
    	tblc_tor.setCellValueFactory(new PropertyValueFactory<UserMainModel,String>("tor"));
    	tblc_czas_tr.setCellValueFactory(new PropertyValueFactory<UserMainModel,Integer>("czas_trwania"));

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
