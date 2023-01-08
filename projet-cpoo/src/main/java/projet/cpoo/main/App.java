package projet.cpoo.main;


import javafx.scene.layout.BorderPane;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projet.cpoo.game.Game;


public final class App extends Application {

    private static Stage primaryStage;
    private static Model model = new Model();

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("DactyloGame");
        FXMLLoader loader = new FXMLLoader();
        String SE = System.getProperty("os.name".toLowerCase()).substring(0, 3);
        if(SE.indexOf("Win") >= 0){
            loader.setLocation(App.class.getResource("/WelcomeScreen.fxml"));
        }else {
            loader.setLocation(App.class.getResource("WelcomeScreen.fxml"));

        }
        BorderPane rootLayout = loader.load();
        Scene scene = new Scene(rootLayout);
        if(SE.indexOf("Win") >= 0){
            scene.getStylesheets().add(App.class.getResource("/basic.css").toExternalForm());
        } else {
            scene.getStylesheets().add(App.class.getResource("basic.css").toExternalForm());
        }

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    protected static void changeStage(Node node, String filepath) throws IOException {
        Parent parent = FXMLLoader.load(App.class.getResource(filepath));
        Scene scene = new Scene(parent);
        String SE = System.getProperty("os.name".toLowerCase()).substring(0, 3);
        if(SE.indexOf("Win") >= 0){
            scene.getStylesheets().add(App.class.getResource("/basic.css").toExternalForm());
        } else {
            scene.getStylesheets().add(App.class.getResource("basic.css").toExternalForm());
        }
        primaryStage = (Stage) node.getScene().getWindow();
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
