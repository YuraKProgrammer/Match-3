package tests;

import models.Chip;
import models.ChipImageType;
import models.Game;
import models.GameField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Bonuses_Test {

    @Test
    public void line_test1(){
        Game game = new Game();
        GameField field = new GameField(8,8);
        var k=0;
        for (var x=0; x<field.getWidth(); x++){
            for (var y=0; y< field.getHeight(); y++){
                k++;
                if (k>9)
                    k=1;
                    field.setChip(x,y,ChipImageType.values()[k]);
            }
        }
        field.setChip(1,1,ChipImageType.T7);
        field.setChip(2,1,ChipImageType.T7);
        field.getChip(2,1).setHorizontalBonus(true);
        field.setChip(3,1,ChipImageType.T7);
        game.setGameField(field);
        game.checkSequences();
        for (var i=0; i< field.getWidth(); i++) {
            Assertions.assertNull(game.field.getChip(i, 1));
        }
    }
    @Test
    public void line_test2() {
        Game game = new Game();
        GameField field = new GameField(9, 9);
        var k = 0;
        for (var x = 0; x < field.getWidth(); x++) {
            for (var y = 0; y < field.getHeight(); y++) {
                k++;
                if (k > 9)
                    k = 1;
                field.setChip(x, y, ChipImageType.values()[k]);
            }
        }
        field.setChip(1, 1, ChipImageType.T7);
        field.setChip(1, 2, ChipImageType.T7);
        field.getChip(1, 2).setHorizontalBonus(true);
        field.setChip(1, 3, ChipImageType.T7);
        game.setGameField(field);
        game.checkSequences();
        for (var i = 0; i < field.getWidth(); i++) {
            Assertions.assertNull(game.field.getChip(i, 2));
        }
    }
}
