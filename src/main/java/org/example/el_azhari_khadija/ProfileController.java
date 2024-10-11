package org.example.el_azhari_khadija;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class ProfileController {
    private Stage dialogStage;
    private HelloApplication mainApp;
    private  static final String DB_URL="jdbc:mysql://localhost/company";
    private  static final String DB_USERNAME="root";
    private  static final String PASSWORD ="";
    private boolean okClick = false;
    @FXML
    private Label email;

    @FXML
    private Label password;

    @FXML
    private Label userHello;

    @FXML
    private Label username;
    @FXML
    void change(ActionEvent event) {

    }


    private User user;

    public boolean isOkClick() {
        return okClick;
    }

    public void setMainApp(HelloApplication mainApp) {
        this.mainApp = mainApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    void change() throws SQLException, IOException {
    HelloApplication start = new HelloApplication();
    setMainApp(mainApp);
    SessionManager sessionManager = SessionManager.getInstance();
    User c = sessionManager.getCurrentUser();
    mainApp.showProfileEdit(c);
    }

    @FXML
    void ok() {
        okClick = true;
        dialogStage.close();
    }
    public void setUser(User user) throws SQLException {
        SessionManager sessionManager = SessionManager.getInstance();
        this.user = sessionManager.getCurrentUser();
        String selectquery = "SELECT * FROM admin  WHERE username=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
             connection = DriverManager.getConnection(DB_URL,DB_USERNAME,PASSWORD);
            preparedStatement = connection.prepareStatement(selectquery);
            preparedStatement.setString(1,user.getUsername());
            resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                userHello.setText("Bienvenue Mr: "+user.getUsername());
                username.setText(user.getUsername());
                password.setText(resultSet.getString("password"));
                email.setText(resultSet.getString("email"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
            preparedStatement.close();
            resultSet.close();

        }
    }

}
