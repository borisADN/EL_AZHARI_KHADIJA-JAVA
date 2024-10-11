package org.example.el_azhari_khadija;

import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.SQLException;

public class ViewAdminController {
    private HelloApplication MainApp;

    public ViewAdminController() {

    }

    public void setMainApp(HelloApplication mainApp) {
        MainApp = mainApp;
    }

    public ViewAdminController(HelloApplication mainApp) {
        MainApp = mainApp;
    }

    @FXML
    void show() throws SQLException, IOException {
        HelloApplication startprofile = new HelloApplication();
        setMainApp(startprofile);
        SessionManager sessionManager = SessionManager.getInstance();
        User Current = sessionManager.getCurrentUser();
        boolean okclick =MainApp.showProfile(Current);
        if (okclick) System.out.println("test fini");


    }
}
