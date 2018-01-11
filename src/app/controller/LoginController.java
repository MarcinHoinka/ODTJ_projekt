package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.database.DBConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {

   @FXML
    private TextField tf_login;

    @FXML
    private TextField pf_passwd;

    @FXML
    private Button btn_login;

    DBConnector db;
    PreparedStatement ps;
    Parent parent;

    // zmienn¹ statyczn¹, która przechowuje dane id_l
    public static int id_l;
    static Connection conn;
    
    
//    metoda po³¹czenia z DB
    public static void connection() {
    	DBConnector db = new DBConnector();
 		db = new DBConnector();
 		conn = db.connInit();
 	}
    
//  metoda do obs³ugi b³êdów w poszczególnych scenach
    public static void alertError(String t, String h, String c) {
  		Alert a = new Alert(AlertType.ERROR);
      	a.setTitle(t);
      	a.setHeaderText(h);
      	a.setContentText(c);
      	a.showAndWait();
      }
    
    @FXML
    void actionLogIn(MouseEvent event) {
    	connection();
	
	try {
		ps = conn.prepareStatement("select permission, id_l from logowanie where login=? and haslo=?;");
		ps.setString(1, tf_login.getText());
		ps.setString(2, pf_passwd.getText());
		ResultSet rs = ps.executeQuery();
		String perm;
		if (rs.next()) {
			perm = rs.getString("permission");
			id_l = rs.getInt("id_l");
			
			if (perm.equals("0")) {
				Stage stageUser = new Stage();
				
				try {
					parent = (Parent) FXMLLoader.load(getClass().getResource("/app/view/RootMainView.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				Scene scene = new Scene(parent);
				stageUser.setScene(scene);
				stageUser.setResizable(false);
				stageUser.setTitle("Panel administratora");
				stageUser.show();
			}
			else if (perm.equals("1")) {
				Stage stageUser = new Stage();
				
				try {
					parent = (Parent) FXMLLoader.load(getClass().getResource("/app/view/InstruktorMainView.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				Scene scene = new Scene(parent);
				stageUser.setScene(scene);
				stageUser.setResizable(false);
				stageUser.setTitle("Panel instruktora");
				stageUser.show();
			}					
			else {
				Stage stageAdmin = new Stage();
				
				try {
					parent = (Parent) FXMLLoader.load(getClass().getResource("/app/view/UserMainView.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				Scene scene = new Scene(parent);
				stageAdmin.setScene(scene);
				stageAdmin.setTitle("Panel kursanta");
				stageAdmin.show();
			}
		}
		else {
		alertError("B³¹d", "B³¹d logowania", "Login lub has³o s¹ niepoprawne. \nSpróbuj ponownie lub skontaktuj siê z Administratorem.");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	tf_login.clear();
	pf_passwd.clear();
	
    }
}
