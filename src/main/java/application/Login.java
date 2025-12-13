package application;

import domain.Admin;
import domain.Compute;
import domain.Machine;
import domain.Staff;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Login extends Application {

    @Override
    public void start(Stage primaryStage) {
    	Font.loadFont(getClass().getResourceAsStream("/fonts/estate.ttf"), 14);
    	
        BorderPane root = new BorderPane();
        root.setId("main-pane");

        Label titleLabel = new Label("Silicon map");
        titleLabel.setId("siliconmap-text");
        BorderPane.setMargin(titleLabel, new Insets(20));
        root.setTop(titleLabel);

        VBox loginBox = new VBox();
        loginBox.setAlignment(Pos.CENTER_LEFT);
        loginBox.setSpacing(10);
        loginBox.setMaxWidth(350);

        Label loginTitle = new Label("Login");
        loginTitle.setId("login-title");
        VBox.setMargin(loginTitle, new Insets(0, 0, 20, 0));

        Label userLabel = new Label("Username");
        userLabel.setMaxWidth(Double.MAX_VALUE);
        
        TextField userField = new TextField();
        userField.setId("text-field");

        Label passLabel = new Label("Password");
        passLabel.setMaxWidth(Double.MAX_VALUE);
        
        PasswordField passField = new PasswordField();
        passField.setId("text-field");

        Button btnLogin = new Button("Login");
        btnLogin.setMaxWidth(Double.MAX_VALUE);
        btnLogin.setId("login-button");

        btnLogin.setOnAction(e -> {
            System.out.println("Login: " + userField.getText());
        });

        loginBox.getChildren().addAll(loginTitle, userLabel, userField, passLabel, passField, btnLogin);

        root.setCenter(loginBox);

        Scene scene = new Scene(root, 1000, 800);
        scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}