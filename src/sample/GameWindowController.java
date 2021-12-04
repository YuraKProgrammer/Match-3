package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.ChipGenerator;
import models.Game;
import models.GameField;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
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

    private Map<Integer,BufferedImage> images = new HashMap<>();

    private static BufferedImage createImage(int width, int height, Color color) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = image.createGraphics();
        g.setColor(color);
        g.fillRect(0, 0, width, height);
        g.dispose();
        return image;
    }

    public void init(Stage stage) {
        _image = createImage(chipSize * game.field.getWidth(), chipSize * game.field.getHeight(), Color.BLACK);
        game.field.fillRandom();
        redraw();
        var imageFolder = getImagesFolder();
        images.put(1,loadImage(imageFolder+"\\1.png"));
        images.put(2,loadImage(imageFolder+"\\2.jpg"));
        images.put(3,loadImage(imageFolder+"\\3.jpg"));
        images.put(4,loadImage(imageFolder+"\\4.jpg"));
        images.put(5,loadImage(imageFolder+"\\5.jpg"));
        images.put(6,loadImage(imageFolder+"\\6.png"));
        images.put(7,loadImage(imageFolder+"\\7.jpg"));
        images.put(8,loadImage(imageFolder+"\\8.jpg"));
        images.put(9,loadImage(imageFolder+"\\9.png"));
    }

    private String getImagesFolder(){
        /*
        String applicationDir = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
            applicationDir = new File(applicationDir).getParent();
            applicationDir = new File(applicationDir).getParent();
            applicationDir = new File(applicationDir).getParent();
            applicationDir =
         */
        String applicationDir="D:\\Школьные задания\\Программирование\\Match-3\\images";
        return applicationDir;
    }

    private void drawChip(int x, int y, boolean selected, int number) {
            g.drawImage(images.get(number), null, x*chipSize,y*chipSize);
            g.setColor(Color.BLACK);
            if (number==0)
                g.fillRect(x * chipSize, y * chipSize, chipSize, chipSize);
            g.drawRect(x * chipSize, y * chipSize, chipSize, chipSize);
            if (selected){
                g.setColor(Color.RED);
                g.drawRect(x * chipSize, y * chipSize, chipSize, chipSize);
                g.drawRect(x * chipSize+1, y * chipSize+1, chipSize-1, chipSize-1);
            }

/*
            g.setColor(color);
            if (selected){
                var r1 = color.getRed()/2;
                var g1 = color.getGreen()/2;
                var b1 = color.getBlue()/2;
                g.setColor(new Color(r1,g1,b1));
            }
            g.fillRect(x * chipSize, y * chipSize, chipSize, chipSize);
            g.setColor(Color.BLACK);
            g.drawRect(x * chipSize, y * chipSize, chipSize, chipSize);
 */
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

    private Color getChipColor(int x, int y) {
        var chip = game.field.getChip(x, y);
        var color = new Color(0,0,0);
        if (chip == 0)
            color = Color.BLACK;
        if (chip == 1)
            color = Color.GRAY;
        if (chip == 2)
            color = Color.MAGENTA;
        if (chip == 3)
            color = Color.RED;
        if (chip == 4)
            color = Color.BLUE;
        if (chip == 5)
            color = Color.YELLOW;
        if (chip == 6)
            color = Color.GREEN;
        if (chip == 7)
            color = Color.CYAN;
        if (chip == 8)
            color = Color.PINK;
        if (chip == 9)
            color = new Color(100, 20, 20);
        return color;
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
                            game.field.checkVoid();
                            redraw();
                            _score.setText(String.valueOf(game.getScore()));
                            System.out.println(game.getScore());
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