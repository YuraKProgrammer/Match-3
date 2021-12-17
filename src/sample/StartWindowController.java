package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import models.Settings;

import java.util.Objects;

public class StartWindowController {
    @FXML
    private Button _sScore;
    @FXML
    private Button _sLevel;
    @FXML
    private RadioButton _p1;
    @FXML
    private RadioButton _p2;
    @FXML
    private RadioButton _p3;
    @FXML
    private RadioButton _p4;
    Settings settings = new Settings();
    Scene scene;
    public void startScoreGame(Stage PrimaryStage) throws Exception{
        var stage = new Stage();
        var loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("GameWindow.fxml")));
        var root = (Parent)loader.load();
        stage.setTitle("Java Match 3");
        stage.setScene(new Scene(root, 1545, 800));
        stage.show();
        var controller = loader.<GameWindowController>getController();
        controller.init(stage, settings);
        controller.setScene(stage.getScene());
    }
    public void startLevelGame(Stage PrimaryStage) throws Exception{
        var stage = new Stage();
        var loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("LevelWindow.fxml")));
        var root = (Parent)loader.load();
        stage.setTitle("Java Match 3");
        stage.setScene(new Scene(root, 1545, 800));
        stage.show();
        var controller = loader.<LevelWindowController>getController();
        controller.init(stage, settings);
        controller.setScene(stage.getScene());
    }
    public void init(Stage stage) {
    }
    public void setScene(Scene scene) {
        this.scene = scene;
        _sScore.setOnAction(actionEvent -> {
            try {
                startScoreGame(new Stage());
            }
            catch (Exception e){
                Main.showError(e);
            }
        });
        _sLevel.setOnAction(actionEvent -> {
            try {
                startLevelGame(new Stage());
            }
            catch (Exception e){
                Main.showError(e);
            }
        });
        if (_p1.isSelected())
            settings.setNumberOfImages(1);
        if (_p2.isSelected())
            settings.setNumberOfImages(2);
        if (_p3.isSelected())
            settings.setNumberOfImages(3);
        if (_p4.isSelected())
            settings.setNumberOfImages(4);
    }
}
