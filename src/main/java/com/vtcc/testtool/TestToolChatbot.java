package com.vtcc.testtool;

import java.io.File;

import com.vtcc.testtool.utils.ReadExcelFileUtil;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TestToolChatbot extends Application {

    @Override
    public void start(final Stage stage) throws Exception {
        Button chooseBtn = new Button("Choose");
        TextField chosen = new TextField();
        ProgressBar progressBar = new ProgressBar();
        Button runBtn = new Button("Run");
        Label percentText = new Label("0%");

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
            try{
                ReadExcelFileUtil.read(chosen.getText());
            }catch (Exception e){
                percentText.setText(e.getMessage());

            }
        });

        VBox layout = new VBox(10, chooseBtn, chosen, runBtn, progressBar, percentText);
        layout.setMinWidth(400);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));
        stage.setScene(new Scene(layout));
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
