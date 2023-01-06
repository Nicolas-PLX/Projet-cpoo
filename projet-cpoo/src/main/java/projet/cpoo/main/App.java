package projet.cpoo.main;


import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("DactyloGame");
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("/WelcomeScreen.fxml"));
        BorderPane rootLayout = loader.load();
        Scene scene = new Scene(rootLayout);
        scene.getStylesheets().add(App.class.getResource("/basic.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    protected static void changeStage(Node node, String filepath) throws IOException {
        Parent parent = FXMLLoader.load(App.class.getResource(filepath));
        Scene scene = new Scene(parent);
        scene.getStylesheets().add(App.class.getResource("/basic.css").toExternalForm());
        primaryStage = (Stage) node.getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    protected static void exit() {
        Platform.exit();
    }
    
    public static void main(String[] args) throws IOException{
        launch(args);
    }
}
