package demo;

import java.io.IOException;

import javafx.fxml.FXML;

public class dashBoardCotroller {
    @FXML
    private void switchToSignUp() throws IOException {
        App.setRoot("SignUp");
    }

    @FXML
    private void dashBoard(){
        
    }

    @FXML
    private void close(){
        System.exit(0);
    }
}
