package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage PrimaryStage) throws Exception{
        var stage = new Stage();
        var loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("GameWindow.fxml")));
        var root = (Parent)loader.load();
        stage.setTitle("Java Match 3");
        stage.setScene(new Scene(root, 1545, 800));
        stage.show();
        var controller = loader.<GameWindowController>getController();
        controller.init(stage);
        controller.setScene(stage.getScene());
    }

    public static void showError(Exception exc) {
        var alert = new Alert(Alert.AlertType.ERROR, exc.getMessage());
        alert.showAndWait();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
