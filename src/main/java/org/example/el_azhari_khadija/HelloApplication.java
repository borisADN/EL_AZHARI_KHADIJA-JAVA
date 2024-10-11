package org.example.el_azhari_khadija;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
      HelloApplication.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Manager App");
        stage.setScene(scene);
        stage.show();
    }
    public boolean showProfileEdit(User user) throws IOException, SQLException {
        FXMLLoader fxmlLoaderEdit = new FXMLLoader(HelloApplication.class.getResource("EditProfile.fxml"));
        AnchorPane edprofile = fxmlLoaderEdit.load();
        Stage stage1 = new Stage();
        stage1.setTitle("Edit Profile");
        stage1.initModality(Modality.WINDOW_MODAL);
        stage1.initOwner(stage);
        Scene scene = new Scene(edprofile);
        stage1.setScene(scene);
        ProfileController profileController = fxmlLoaderEdit.getController();
        profileController.setDialogStage(stage1);
        profileController.setUser(user);
        profileController.setMainApp(this);
        stage1.show();
        return profileController.isOkClick();
    }

    public boolean showProfile(User user) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Profile.fxml"));
        AnchorPane profile = fxmlLoader.load();
        Stage stage1 = new Stage();
        stage1.setTitle("Wiew Profile");
        stage1.initModality(Modality.WINDOW_MODAL);
        stage1.initOwner(stage);
        Scene scene = new Scene(profile);
        stage1.setScene(scene);
        ProfileController profileController = fxmlLoader.getController();
        profileController.setDialogStage(stage1);
        profileController.setUser(user);
        profileController.setMainApp(this);
        stage1.show();
        return profileController.isOkClick();
    }
    public void changersc() throws IOException {
        FXMLLoader fxmlLoaderLoginAdmin = new FXMLLoader(HelloApplication.class.getResource("ViewAdmin.fxml"));
        Scene scene = new Scene(fxmlLoaderLoginAdmin.load());
        stage.setScene(scene);
        stage.show();
       //stage.setScene(scene);
       //stage.show();
    }
}