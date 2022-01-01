package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import models.ImageType;
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
        _p1.setOnAction(actionEvent -> {
            detectImageType();
        });
        _p2.setOnAction(actionEvent -> {
            detectImageType();
        });
        _p3.setOnAction(actionEvent -> {
            detectImageType();
        });
        _p4.setOnAction(actionEvent -> {
            detectImageType();
        });
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
        detectImageType();
    }
    private void detectImageType(){
        var selected = _p1.getToggleGroup().getSelectedToggle();
        if (selected == _p1)
            settings.setImageType(ImageType.crystals);
        if (selected == _p2)
            settings.setImageType(ImageType.fruit);
        if (selected == _p3)
            settings.setImageType(ImageType.vegetables);
        if (selected == _p4)
            settings.setImageType(ImageType.animals);
    }
}
