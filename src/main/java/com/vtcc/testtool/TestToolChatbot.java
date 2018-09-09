package com.vtcc.testtool;

import java.io.File;
import java.text.DecimalFormat;

import com.vtcc.testtool.utils.ReadExcelFileUtil;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TestToolChatbot extends Application {

    double n = 0;

    @Override
    public void start(final Stage stage) throws Exception {

        VBox root = new VBox();

        root.setSpacing(10);
        root.setPadding(new Insets(10,10, 10,10));

        Button chooseBtn = new Button("Choose");

        TextField chosen = new TextField();

        ProgressBar progressBar = new ProgressBar();
//        progressBar.setPrefWidth(110);

        Button runBtn = new Button("Run");
        Label percentText = new Label("0%");

        DoubleProperty num = new SimpleDoubleProperty(0);
        num.set(n);

        StringProperty str = new SimpleStringProperty("0%");
        str.set(n * 100 + "%");

        progressBar.progressProperty().bind(num);
        percentText.textProperty().bind(str);


        chooseBtn.setOnAction(event -> {
            FileChooser chooser = new FileChooser();
            File file = chooser.showOpenDialog(stage);
            if (file != null) {
                String fileAsString = file.toString();

                chosen.setText(fileAsString);
            } else {
                chosen.setText(null);
            }
        });

        runBtn.setOnAction(event -> {
//            try{
//                ReadExcelFileUtil.read(chosen.getText());
//            }catch (Exception e){
//                percentText.setText(e.getMessage());
//
//            }

            n = n + 0.1;
            num.set(n);
            str.set(new DecimalFormat("#.00").format(n * 100) + "%");
        });

//        chosen.setPrefWidth(110);
//        HBox hBox = new HBox(10, chosen, chooseBtn);
//        hBox.setPrefWidth(110);
        HBox hBox1 = new HBox(10, progressBar, percentText);
        hBox1.setAlignment(Pos.CENTER);

        root.getChildren().addAll(chosen, chooseBtn, runBtn, progressBar, percentText);

        Scene scene = new Scene(root, 550, 250);

        stage.setTitle("VBox Layout Demo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
