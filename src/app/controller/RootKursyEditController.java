package app.controller;


import java.sql.PreparedStatement;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RootKursyEditController {

    @FXML
    private TextField tf_nazwa;

    @FXML
    private TextField tf_czasTrw;

    @FXML
    private TextArea ta_opisSz;

    @FXML
    private Button btn_addTor;

    @FXML
    private Button btn_goBack;

    PreparedStatement ps;
    Stage stage;
    Parent KursyEdit;
    
    @FXML
    void actionGoBack(MouseEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void actionSave(MouseEvent event) throws Exception {
    	if (!tf_nazwa.getText().equals("") && !tf_czasTrw.getText().equals("")) {	
    		LoginController.connection();
    	    	ps = LoginController.conn.prepareStatement("update szkolenia set nazwa_sz = ?, czas_trwania = ?, opis = ? where id_sz = ?");
				ps.setString(1, tf_nazwa.getText());
				ps.setString(2, tf_czasTrw.getText());
				ps.setString(3, ta_opisSz.getText());
				ps.setInt(4, RootKursyController.id_selected);	
				ps.executeUpdate();
    	    } else {
    	    	LoginController.alertError("B³¹d", "Brak danych!", "Wype³nij wszystkie pola.");
    		}
    }
    

}
