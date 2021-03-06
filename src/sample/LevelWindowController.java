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
import models.*;
import models.detectors.IImagesFolderDetector;
import models.detectors.ImagesFolderDetector;
import models.generators.LevelGenerator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LevelWindowController {
    public static final int timerDuration = 100;

    @FXML
    private ImageView _field;
    @FXML
    private ImageView _chipType;
    @FXML
    private Text _countOfChips;
    @FXML
    private Text _countOfMoves;

    private Scene scene;

    private Graphics2D g;

    private Game game = new Game();

    private BufferedImage _image;

    private BufferedImage _chipTypeImage;

    private int chipSize = 75;

    private int selectedX = -1;

    private int selectedY = -1;

    Settings settings = new Settings();

    private Map<Integer,BufferedImage> images = new HashMap<>();

    private IImagesFolderDetector imagesFolderDetector = new ImagesFolderDetector();

    private Level level = new Level();

    private LevelGenerator levelGenerator = new LevelGenerator();

    private static BufferedImage createImage(int width, int height, Color color) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = image.createGraphics();
        g.setColor(color);
        g.fillRect(0, 0, width, height);
        g.dispose();
        return image;
    }

    public void init(Stage stage, Settings settings) {
        level=levelGenerator.generate();
        this.settings = settings;
        _image = createImage(chipSize * game.field.getWidth(), chipSize * game.field.getHeight(), Color.WHITE);
        _chipTypeImage = createImage(chipSize, chipSize, Color.WHITE);
        game.field.fillRandom();
        redraw();
        try {
            var imageFolder = imagesFolderDetector.getFolder(settings.getImageType());
            for (var i = 1; i < 29; i++) {
                images.put(i, loadImage(imageFolder + "\\" + i + ".png"));
            }
        } catch (Exception e) {
            Main.showError(e);
        }
        drawChipType();
    }

    private void drawChip(int x, int y, boolean selected, Chip chip) {
        int number;
        if (chip==null)
            number=0;
        else {
            number = chip.getImageType().ordinal() + 1;
            if (chip.isVerticalBonus())
                number += 9;
            if (chip.isHorizontalBonus())
                number += 18;
            if (chip.getImageType() == ChipImageType.SUPERSTAR)
                number = 28;
        }
        BufferedImage img = images.get(number);
        g.drawImage(img, null, x*chipSize,y*chipSize);
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

    private  void drawChipType(){
        var g=_chipTypeImage.createGraphics();
        var number=0;
        switch (level.getChipType()){
            case T1 -> number=1;
            case T2 -> number=2;
            case T3 -> number=3;
            case T4 -> number=4;
            case T5 -> number=5;
            case T6 -> number=6;
            case T7 -> number=7;
            case T8 -> number=8;
            case T9 -> number=9;
        }

        BufferedImage img = images.get(number);
        g.drawImage(img, null, 0,0);
        g.dispose();
        _chipType.setImage(SwingFXUtils.toFXImage(_chipTypeImage, null));
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
                            var countOfChips=0;
                            switch (level.getChipType()){
                                case T1 -> countOfChips=game.field.getCountChip1();
                                case T2 -> countOfChips=game.field.getCountChip2();
                                case T3 -> countOfChips=game.field.getCountChip3();
                                case T4 -> countOfChips=game.field.getCountChip4();
                                case T5 -> countOfChips=game.field.getCountChip5();
                                case T6 -> countOfChips=game.field.getCountChip6();
                                case T7 -> countOfChips=game.field.getCountChip7();
                                case T8 -> countOfChips=game.field.getCountChip8();
                                case T9 -> countOfChips=game.field.getCountChip9();
                            }
                            var t1=level.getCountOfChips()-countOfChips;
                            _countOfChips.setText(String.valueOf(t1));
                            if (t1<=0){
                                _countOfChips.setText("++");
                                _countOfMoves.setText("????????????");
                            }
                            var t2 = level.getCountOfMoves() - game.getCountOfMoves();
                            _countOfMoves.setText(t2 + " ??????????");
                            if (t2<=0) {
                                _countOfChips.setText("--");
                                _countOfMoves.setText("????????????????");
                            }
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
