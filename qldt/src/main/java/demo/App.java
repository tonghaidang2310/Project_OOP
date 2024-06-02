package demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene = null;
    private static Stage stage = null;

    private double x = 0;
    private double y = 0;

    @Override
    public void start(Stage s) throws IOException {
        stage = s;
        Parent root = loadFXML("SignIn");
        scene = new Scene(root);

        root.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

            stage.setOpacity(0.8);
        });

        root.setOnMouseReleased((MouseEvent event) -> {
            stage.setOpacity(1.0);
        });

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void setRoot(String string) {
        Parent root = loadFXML(string);
        if (root != null) {
            scene.setRoot(root);
            stage.sizeToScene();
        }
    }

    public static Parent loadFXML(String fxml) {
        try {
            return FXMLLoader.load(App.class.getResource(fxml + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}