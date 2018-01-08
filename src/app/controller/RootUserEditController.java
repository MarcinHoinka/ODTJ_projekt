package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RootUserEditController {

    @FXML
    private TextField tf_imie;

    @FXML
    private TextField tf_nazwisko;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_telefon;

    @FXML
    private TextField tf_login;

    @FXML
    private TextField tf_password;

    @FXML
    private Button btn_addKursanta;

    @FXML
    private Button btn_deleteKursanta;

    @FXML
    private Button btn_goBack;

    Connection conn;
    PreparedStatement ps, ps_logowanie;
    Stage stage;
    Parent UserEdit;

    
//    mo¿na rozdzieliæ na dwa przyciski logowanie i dane usera
    @FXML
    void actionSave(MouseEvent event) throws Exception {

    	if (!tf_imie.getText().equals("") && !tf_nazwisko.getText().equals("") && !tf_email.getText().equals("")
    			&&!tf_telefon.getText().equals("")) {
	
    		LoginController.connection();
    	    	ps = LoginController.conn.prepareStatement("update kursanci set imie = ?, nazwisko = ?, email = ?, telefon = ? where id_k= ?");
				ps.setString(1, tf_imie.getText());
				ps.setString(2, tf_nazwisko.getText());
				ps.setString(3, tf_email.getText());				
				ps.setString(4, tf_telefon.getText());
				ps.setInt(5, RootUsersController.id_selected);	
				ps.executeUpdate();  

				ps_logowanie = LoginController.conn.prepareStatement("update logowanie set login = ?, haslo = ? where id_k= ?");
				ps_logowanie.setString(1, tf_login.getText());
				ps_logowanie.setString(2, tf_password.getText());				
				ps_logowanie.setInt(3, RootUsersController.id_selected);
				ps.executeUpdate();    	    	
    	    	
    	    } else {
    	    Alert insertError = new Alert(AlertType.ERROR);
    		insertError.setTitle("B³¹d danych");
    		insertError.setHeaderText("B³¹d wpisywania");
    		insertError.setContentText("Aby wys³aæ dodaæ kursanta, wype³nij wszystkie pola (je¿eli pole nie ulega zmianie, wpisz poprzedni¹ wartoœæ");
    		insertError.showAndWait();
    		}
    		

    }

    @FXML
    void actionDelete(MouseEvent event) {

    }

    @FXML
    void actionGoBack(MouseEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public void initialize() {
    	System.out.println("Test id_selected: " + RootUsersController.id_selected );
    }
    	
    
    
}
