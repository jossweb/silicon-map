package application;

import application.components.Charts;
import application.components.PDF;
import domain.Context;
import domain.Machine;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InterfaceMachineInfo extends Stage {
    private Stage stage;
    private Machine machine;
    private Context context;
    public InterfaceMachineInfo(Stage s, Machine m, Context c){
        this.stage = s; 
        this.machine = m;
        this.context = c;

        setTitle(this.machine.getHostname() + " informations");
        VBox root = new VBox(10);
        root.setId("main-pane");

        Scene scene = new Scene(root, 400, 500);
        scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
        setScene(scene);

        initOwner(this.stage);
        initModality(Modality.WINDOW_MODAL);

        Label pageTitle = new Label("Informations");
        pageTitle.getStyleClass().addAll("subtitle", "need-gap-y");

        HBox hostnameBox = new HBox();
        Label hostname = new Label("Hostname : ");
        hostname.getStyleClass().add("bold");
        Label hostnameValue = new Label(this.machine.getHostname());
        hostnameBox.getChildren().addAll(hostname, hostnameValue);

        HBox typeBox = new HBox();
        Label typel = new Label("type : ");
        typel.getStyleClass().add("bold");
        Label typeValue = new Label(this.machine.whoami("unknown type"));
        typeBox.getChildren().addAll(typel, typeValue);

        HBox ipBox = new HBox();
        Label ip = new Label("Ip : ");
        ip.getStyleClass().add("bold");
        Label ipValue = new Label(this.machine.getIpAddress());
        ipBox.getChildren().addAll(ip, ipValue);

        HBox macBox = new HBox();
        Label mac = new Label("Mac : ");
        mac.getStyleClass().add("bold");
        Label macValue = new Label(this.machine.getMacAdress());
        macBox.getChildren().addAll(mac, macValue);

        HBox osBox = new HBox();
        Label os = new Label("Os : ");
        os.getStyleClass().add("bold");
        Label  osValue = new Label(this.machine.getOs());
        osBox.getChildren().addAll(os, osValue);

        HBox statusBox = new HBox();
        Label status = new Label("Status : ");
        status.getStyleClass().add("bold");
        Label statusValue = new Label(this.machine.getStatus());
        statusBox.getChildren().addAll(status, statusValue);


        LineChart<String, Number> chart = new Charts(this.context.getTempList().get(this.machine.getId()), "Temp");

        Button createPdf = new Button("Create data sheet");
        createPdf.getStyleClass().add("submit-button");

        Label indication = new Label("");
        //indication.getStyleClass().add("");
        indication.setVisible(false);


        root.getChildren().addAll(pageTitle, hostnameBox, typeBox, ipBox, macBox, osBox, statusBox, chart, createPdf, indication);

        createPdf.setOnAction(e->{
            PDF newPdf = new PDF(this.machine.getHostname(), this.machine);
            indication.setVisible(true);
            if(newPdf.getSuccess()){
                indication.setText("Document create as " + newPdf.getDocName() + ".pdf");
            }else{
                indication.setText("Error, can't create the document!");
            }        
        });
    }
}
