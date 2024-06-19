package demo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InboxController implements Initializable{
    public static Stage currentStage;

    @FXML
    private Button Inbox_read;

    @FXML
    private Label sender_name;

    @FXML
    private Label tiltle_inbox;

    public void setData(String name, String tiltle){
        sender_name.setText(name);
        tiltle_inbox.setText(tiltle);
    }

    public String getTiltle(){
        return tiltle_inbox.getText();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Inbox_read.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ReadInbox.fxml"));
                    AnchorPane root = loader.load();
                    ReadInboxController controller = loader.getController();
                    controller.setReadForm(tiltle_inbox.getText(), sender_name.getText());
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UNDECORATED);

                    currentStage = (Stage) Inbox_read.getScene().getWindow();
                    currentStage.hide();

                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
