package demo;

import java.io.IOException;

import javafx.fxml.FXML;

public class dashBoardCotroller {
    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("SignIn");
    }

    @FXML
    private void dashBoard(){
        
    }

    @FXML
    private void close(){
        System.exit(0);
    }
}
