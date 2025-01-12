package org.example.el_azhari_khadija;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;

public class HelloController {
     private  static final String DB_URL="jdbc:mysql://localhost/company";
     private  static final String DB_USERNAME="root";
     private  static final String PASSWORD ="";
     String successStyle = "-fx-border-color: #A9A9A9; -fx-border-width:2; -fx-border-radius:5;";
     String successMessage = String.format("-fx-text-fill:GREEN;");
    String errorMessage = String.format("-fx-text-fill:RED;");
     String errorStyle= String.format("-fx-border-color: RED; -fx-border-width:2; -fx-border-radius:5;");
    @FXML
    private ComboBox<String> combobox;

    @FXML
    private PasswordField password;

    @FXML
    private Button signin;
    @FXML
    private Label labelmsg;

    @FXML
    private TextField username;
    @FXML
    private void  Login() throws IOException, ClassNotFoundException {
        if(username.getText().isBlank() || password.getText().isBlank()){
            labelmsg.setText("username and password are required!!!");
            labelmsg.setStyle(errorMessage);
            if (username.getText().isBlank())username.setStyle(errorStyle);
            else if(password.getText().isBlank()) password.setStyle(errorStyle);
        }
        else if (connectUser(username.getText(), password.getText())){
            User user = new User(username.getText());
            SessionManager sessionManager = SessionManager.getInstance();
            sessionManager.startSession(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("LOGIN SUCCESSFUL");
            alert.setHeaderText("BIENVENUE");
            alert.show();
            HelloApplication helloApplication=new HelloApplication();
            helloApplication.changersc();
        }
      else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cet admin n'existe pas");
            alert.setHeaderText("Va verifier tes identifiants");
            alert.show();
        }
    }
    public static boolean connectUser(String username, String password) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME, PASSWORD);
            System.out.println("base de données connectée");
            String Query = "SELECT * FROM admin WHERE username =? AND password = ?";
            PreparedStatement preparedStatement =connection.prepareStatement(Query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                resultSet.close();
                preparedStatement.close();
                connection.close();
                return  true;
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
//            System.out.println('e');
            e.printStackTrace();
        }
        return false;
    }
}