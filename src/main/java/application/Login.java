package application;

import Dao.SingleConnection;
import domain.Staff;
import error.BadPassword;
import error.UserNotFound;
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

/**
 * JavaFX app startup and login page
 * 
 * @author EVANGELISTA Thomas and FIGUEIRAS Jossua
 */
public class Login extends Application {


    @Override
    public void start(Stage primaryStage) {
    	Font.loadFont(getClass().getResourceAsStream("/fonts/roboto.ttf"), 14);
    
        InterfaceDbChoice dbChoice = new InterfaceDbChoice(primaryStage);
        dbChoice.showAndWait();
        loginPage(primaryStage);
    }
    /**
     * Creates and displays the login interface
     * 
     * @param stage the primary stage of the application
     */
    private void loginPage(Stage stage){
        //start sql connection
        new SingleConnection();

        BorderPane root = new BorderPane();
        root.setId("main-pane");

        Label titleLabel = new Label("Silicon map");
        titleLabel.getStyleClass().add("siliconmap-logo");
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
        userField.getStyleClass().add("text-field");

        Label passLabel = new Label("Password");
        passLabel.setMaxWidth(Double.MAX_VALUE);
        
        PasswordField passField = new PasswordField();
        passField.getStyleClass().add("text-field");

        Button btnLogin = new Button("Login");
        btnLogin.setMaxWidth(Double.MAX_VALUE);
        btnLogin.getStyleClass().add("submit-button");
        btnLogin.setDefaultButton(true);

        Label errorIndication = new Label("");
        errorIndication.setVisible(false);
        


        btnLogin.setOnAction(e -> {
            String username = userField.getText();
            String pass = passField.getText();
            if(username.length() > 0 && pass.length() > 0){
                try{
                    Staff s = Staff.checkPass(username, pass);
                    if(s!=null){
                        //get actual stage
                        Stage currentStage = (Stage) btnLogin.getScene().getWindow();

                        UserInterface nextPage = new UserInterface();
                        nextPage.Dashboard(currentStage, s);

                    }else{
                        System.out.print("Error verification");
                    }
                }catch(UserNotFound unfe){
                    errorIndication.setText("Can't find your user name in database!");
                    errorIndication.setVisible(true);
                }catch(BadPassword bpe){
                    errorIndication.setText("Bad password");
                    errorIndication.setVisible(true);
                }catch(Exception ex){
                    errorIndication.setText("unexpected error");
                    System.out.print("ERROR : " + ex);
                    errorIndication.setVisible(true);
                }finally{
                    passField.setText("");
                }

            }else{
                errorIndication.setText("Please fill in the fields");
                errorIndication.setVisible(true);
            }
            userField.requestFocus();
        });

        loginBox.getChildren().addAll(loginTitle, userLabel, userField, passLabel, passField, btnLogin, errorIndication);

        root.setCenter(loginBox);

        Scene scene = new Scene(root, 1100, 700);
        scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}