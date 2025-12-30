package application;

import java.util.HashMap;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import simulation.Simulator;

public class InterfaceDbChoice extends Stage {
    private Stage stage;
    public InterfaceDbChoice(Stage s){
        this.stage = s;

        setTitle("Choose your database");
        VBox root = new VBox(10);
        root.setId("main-pane");

        Scene scene = new Scene(root, 400, 150);
        scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
        setScene(scene);

        initOwner(this.stage);
        initModality(Modality.WINDOW_MODAL);

        HashMap<String, String> dbsInfosMap = new HashMap<String, String>();

        Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .systemProperties()
            .load();

        for (DotenvEntry e : dotenv.entries()) {
            dbsInfosMap.put(e.getKey(), e.getValue());
        }

        if(dbsInfosMap.get("REMOTENAME")==null && dbsInfosMap.get("LOCALNAME")==null){
            root.getChildren().add(new Label("Error : Please configure your .env"));
        }else{
            HBox buttonBox = new HBox(5);
            buttonBox.getStyleClass().add("need-gap-y");
            if(dbsInfosMap.get("LOCALNAME")!=null){
                Button localButton = new Button(dbsInfosMap.get("LOCALNAME"));
                localButton.getStyleClass().add("submit-button");
                buttonBox.getChildren().add(localButton);

                localButton.setOnAction(b->{
                    System.setProperty("SELECTED_DB", "LOCAL");

                    if (dbsInfosMap.get("LOCALSIMULATOR").equals("ON")){
                        Thread simThread = new Thread(() ->{ new Simulator();});
                        simThread.setDaemon(true); 
                        simThread.start();
                    }
                    this.close();
                });
            }
            if(dbsInfosMap.get("REMOTENAME")!=null){
                Button remoteButton = new Button(dbsInfosMap.get("REMOTENAME"));
                remoteButton.getStyleClass().add("submit-button");
                buttonBox.getChildren().add(remoteButton);

                remoteButton.setOnAction(b->{
                    System.setProperty("SELECTED_DB", "REMOTE");

                    if (dbsInfosMap.get("REMOTESIMULATOR").equals("ON")){
                        Thread simThread = new Thread(() ->{ new Simulator();});
                        simThread.setDaemon(true); 
                        simThread.start();
                    }

                    this.close();
                });
            }
            Label title = new Label("select your database");
            title.getStyleClass().addAll("subtitle", "need-gap-y");
            root.getChildren().addAll(title, buttonBox);
        }
    }
}
