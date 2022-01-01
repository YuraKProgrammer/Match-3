package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Game;
import models.ImageType;
import models.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class GameWindowController {
    public static final int timerDuration = 100;

    @FXML
    private ImageView _field;
    @FXML
    private Text _score;

    private Scene scene;

    private Graphics2D g;

    private Game game = new Game();

    private BufferedImage _image;

    private int chipSize = 75;

    private int selectedX = -1;

    private int selectedY = -1;

    Settings settings = new Settings();

    private Map<Integer,BufferedImage> images = new HashMap<>();

    private static BufferedImage createImage(int width, int height, Color color) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = image.createGraphics();
        g.setColor(color);
        g.fillRect(0, 0, width, height);
        g.dispose();
        return image;
    }

    public void init(Stage stage, Settings settings) {
        this.settings=settings;
        _image = createImage(chipSize * game.field.getWidth(), chipSize * game.field.getHeight(), Color.BLACK);
        game.field.fillRandom();
        redraw();
        var imageFolder = getImagesFolder();
        for (var i=1; i<29; i++) {
            images.put(i, loadImage(imageFolder + "\\"+i+".png"));
        }
    }

    private String getImagesFolder(){
        String applicationDir="D:\\Школьные задания\\Программирование\\Match-3\\images";
        switch (settings.getImageType()){
            case fruit -> applicationDir=applicationDir+"2";
            case vegetables -> applicationDir=applicationDir+"3";
            case animals -> applicationDir=applicationDir+"4";
            default -> applicationDir=applicationDir;
        }
        return applicationDir;
    }

    private void drawChip(int x, int y, boolean selected, int number) {
            g.drawImage(images.get(number), null, x*chipSize,y*chipSize);
            g.setColor(Color.WHITE);
            if (number==0)
                g.fillRect(x * chipSize, y * chipSize, chipSize, chipSize);
            g.setColor(Color.BLACK);
            g.drawRect(x * chipSize, y * chipSize, chipSize, chipSize);
            if (selected){
                g.setColor(Color.RED);
                g.drawRect(x * chipSize, y * chipSize, chipSize-2, chipSize-2);
                g.drawRect(x * chipSize+1, y * chipSize+1, chipSize-3, chipSize-3);
            }
        }

    private void redraw() {
        g = _image.createGraphics();
        for (var x = 0; x < game.field.getWidth(); x++) {
            for (var y = 0; y < game.field.getHeight(); y++) {
                if (selectedX>-1 && x==selectedX && y==selectedY)
                    drawChip(x, y, true, game.field.getChip(x,y));
                else
                    drawChip(x, y, false, game.field.getChip(x,y));
            }
        }
        _field.setImage(SwingFXUtils.toFXImage(_image, null));
    }
    public void setScene(Scene scene) {
        this.scene = scene;
        this.scene.setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown()) {
                if ((game.getFirstSelectedX() == -1) && (game.getSecondSelectedX() == -1)) {
                    game.setFirstSelectedX((int)Math.floor((e.getX()-198) / chipSize));
                    game.setFirstSelectedY((int)Math.floor((e.getY()-90) / chipSize)+1);
                }
            }
            if (e.isSecondaryButtonDown()) {
                if ((game.getFirstSelectedX() > -1) && (game.getSecondSelectedX() == -1)) {
                    game.setSecondSelectedX((int)Math.floor((e.getX()-198) / chipSize));
                    game.setSecondSelectedY((int)Math.floor((e.getY()-90) / chipSize)+1);
                    if (!game.replacement()) {
                        game.setFirstSelectedX(-1);
                        game.setFirstSelectedY(-1);
                        game.setSecondSelectedX(-1);
                        game.setSecondSelectedY(-1);
                    }
                    game.replacement();
                }
            }
        });
        var timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(timerDuration),
                        ae -> {
                            setSelected();
                            game.replacement();
                            game.checkSequences();
                            game.field.fillVoid();
                            redraw();
                            _score.setText(String.valueOf(game.getScore()));
                        }
                )
        );
        timeline.setCycleCount(Integer.MAX_VALUE);
        timeline.play();
    }

    private void setSelected(){
        if (game.getFirstSelectedX()>-1){
            selectedX=game.getFirstSelectedX();
            selectedY=game.getFirstSelectedY();
        }
        else{
            selectedX=-1;
            selectedY=-1;
        }
    }


    private static BufferedImage loadImage (String fileName) {
        try {
            return ImageIO.read(new File(fileName));
        }
        catch (Exception e){
            return null;
        }
    }
}