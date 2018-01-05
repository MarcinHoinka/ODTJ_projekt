package app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) {

		try {
			Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/view/LoginView.fxml"));
//			LoginView.fxml
//			RootGrupyView
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Bezpieczny kierowca - Logowanie");
			stage.show();
			
		} catch (IOException e) {
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}



