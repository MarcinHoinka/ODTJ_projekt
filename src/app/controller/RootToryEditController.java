package app.controller;

import java.sql.PreparedStatement;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RootToryEditController {

    @FXML
    private TextField tf_nazwa;

    @FXML
    private TextField tf_adres;

    @FXML
    private TextField tf_miasto;

    @FXML
    private TextField tf_kod;

    @FXML
    private TextField tf_telefon;

    @FXML
    private Button btn_addTor;

    @FXML
    private Button btn_clear;

    @FXML
    private Button btn_goBack;
    
    PreparedStatement ps;
    Stage stage;
    Parent ToryEdit;
    
    @FXML
    void actionGoBack(MouseEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void actionSave(MouseEvent event) throws Exception {
    	if (!tf_nazwa.getText().equals("") && !tf_adres.getText().equals("") && !tf_miasto.getText().equals("") && !tf_kod.getText().equals("")
    			&&!tf_telefon.getText().equals("")) {	
    		LoginController.connection();
    	    	ps = LoginController.conn.prepareStatement("update tory set nazwa = ?, adres = ?, miasto = ?, kod_pocztowy = ? ,telefon = ? where id_tr= ?");
				ps.setString(1, tf_nazwa.getText());
				ps.setString(2, tf_adres.getText());
				ps.setString(3, tf_miasto.getText());
				ps.setString(4, tf_kod.getText());
				ps.setString(5, tf_telefon.getText());
				ps.setInt(6, RootToryController.id_selected);	
				ps.executeUpdate();
    	    } else {
    	    	LoginController.alertError("B³¹d", "Brak danych!", "Wype³nij wszystkie pola.");
    		}
    }
}
