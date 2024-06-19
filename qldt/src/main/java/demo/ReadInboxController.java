package demo;

import java.net.URL;
import java.util.ResourceBundle;

import demo.DAO.AccountDAO;
import demo.DAO.ClassSectionDAO;
import demo.DAO.InboxDAO;
import demo.Entity.Inbox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class ReadInboxController implements Initializable{

    @FXML
    private TextArea Read_Body;

    @FXML
    private Label Read_ClassSection;

    @FXML
    private AnchorPane Read_Inbox_Form;

    @FXML
    private Button Read_close;

    @FXML
    private Label Read_sender_name;

    @FXML
    private Label Read_tiltle;

    public void setReadForm(String tiltle, String senderName){
        Inbox inbox = new InboxDAO().getInbox(new AccountDAO().getCurrentAccount().getAccountID(), tiltle);
        String body = inbox.getBody();
        String classSectionName = new ClassSectionDAO().getClassSectionName(inbox.getClassSectionID());

        Read_tiltle.setText(tiltle);
        Read_sender_name.setText(senderName);
        Read_ClassSection.setText(classSectionName);
        Read_Body.setText(body);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Read_close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                InboxController.currentStage.show();
                Read_Inbox_Form.getScene().getWindow().hide();
            }
        });
    }
}
