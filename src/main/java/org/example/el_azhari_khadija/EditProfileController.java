package org.example.el_azhari_khadija;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class EditProfileController {
    private Stage dialogStage;
    private HelloApplication mainApp;
    private User user;
    private  static final String DB_URL="jdbc:mysql://localhost/company";
    private  static final String DB_USERNAME="root";
    private  static final String PASSWORD ="";
    private boolean okClick = false;
    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private Label userHello;

    @FXML
    private TextField username;

    @FXML
    void change(ActionEvent event) {

    }
    public boolean isOkClick() {
        return okClick;
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


    public void setMainApp(HelloApplication mainApp) {
        this.mainApp = mainApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    @FXML
    void cancel(ActionEvent event) {
    dialogStage.close();
    }

    @FXML
    void ok(ActionEvent event) throws SQLException {
        if (isValid()){
            String sql ="UPDATE admin username =?, password =?, email =? WHERE admin_id=?";
            try { Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME,PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                SessionManager sessionManager = SessionManager.getInstance();
                User u = sessionManager.getCurrentUser();
                {
                    preparedStatement.setString(1,username.getText());
                    preparedStatement.setString(2,password.getText());
                    preparedStatement.setString(3,email.getText());
                    preparedStatement.setInt(3,EditProfileController.getID(u.getUsername()));
                    int rowupdate = preparedStatement.executeUpdate();
                    if (rowupdate>0){
                        Alert alert =  new Alert(Alert.AlertType.INFORMATION);
                        alert.initOwner(dialogStage);
                        alert.setTitle("Succes");
                        alert.setHeaderText("info modified successfully");
                        alert.setContentText(":D3");
                        alert.show();
                    }
                }

            } catch (SQLException e) {
              e.printStackTrace();
            }
        }
    }
    private boolean isValid(){
        String message ="";
        if(username.getText()==null) message+="no valid username \n" ;
        if(password.getText()==null) message+="no valid password \n" ;
        if(message.isEmpty()) return true;
        else {
            Alert alert =  new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("invalid fields");
            alert.setHeaderText("please correct invalids fields");
            alert.setContentText(message);
            alert.show();
            return false;
        }
    }
    public static int getID(String u){
        String str = "SELECT admin_id WHERE username = ?";
        int id = 0;
        try {Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(str);
            preparedStatement.setString(1,u);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                id=resultSet.getInt("admin_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
}
