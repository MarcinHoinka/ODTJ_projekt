package app.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RootMainController {
	
    @FXML
    private AnchorPane rootMainAP;

    @FXML
    private Label lbl_userName;

    @FXML
    private TextField ta_username;

    @FXML
    private TextField ta_passwd;

    @FXML
    private Button btn_logout;

    @FXML
    private Button btn_goGrupy;

    @FXML
    private Button btn_goKursanci;

    @FXML
    private Button btn_goInstruktorzy;

    @FXML
    private Button btn_goSzkolenia;

    @FXML
    private Button btn_goTory;

    @FXML
    void actionGoGrupy(MouseEvent event) {
    	Stage logStage = new Stage();
    	Parent Grupy = null; 
    	try { 
    		Grupy = (Parent) FXMLLoader.load(getClass().getResource("/app/view/RootGrupyView.fxml"));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	Scene scene = new Scene(Grupy);
    	logStage.setScene(scene);
    	logStage.setTitle("Zarz퉐zanie grupami");
    	logStage.show();
    }

    @FXML
    void actionGoInstruktorzy(MouseEvent event) {
    	Stage logStage = new Stage();
		Parent Instruktorzy = null;
		try {
			Instruktorzy = (Parent) FXMLLoader.load(getClass().getResource("/app/view/RootInstruktorzyView.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(Instruktorzy);
		logStage.setScene(scene);
		logStage.setTitle("Zarz퉐zanie instruktorami");
		logStage.show();
    }

    @FXML
    void actionGoKursanci(MouseEvent event) {
    	Stage logStage = new Stage();
		Parent Users = null;
		try {
			Users = (Parent) FXMLLoader.load(getClass().getResource("/app/view/RootUsersView.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(Users);
		logStage.setScene(scene);
		logStage.setTitle("Zarz퉐zanie kursantami");
		logStage.show();
    }

    @FXML
    void actionGoSzkolenia(MouseEvent event) {
    	Stage logStage = new Stage();
		Parent Kursy = null;
		try {
			Kursy = (Parent) FXMLLoader.load(getClass().getResource("/app/view/RootKursyView.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(Kursy);
		logStage.setScene(scene);
		logStage.setTitle("Zarz퉐zanie szkoleniami");
		logStage.show();
    }
    
    @FXML
    void actionGoTory(MouseEvent event) {
    	Stage logStage = new Stage();
			Parent Tory = null;
			try {
				Tory = (Parent) FXMLLoader.load(getClass().getResource("/app/view/RootToryView.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Scene scene = new Scene(Tory);
			logStage.setScene(scene);
			logStage.setTitle("Zarz퉐zanie torami");
			logStage.show();
    }

    @FXML
    void actionLogOut(MouseEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

}
