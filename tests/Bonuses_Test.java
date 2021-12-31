package tests;

import models.Game;
import models.GameField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Bonuses_Test {
    @Test
    public void line_test1(){
        Game game = new Game();
        GameField field = new GameField(5,5);
        field.setChip(0,0,1);
        field.setChip(1,0,2);
        field.setChip(2,0,3);
        field.setChip(3,0,4);
        field.setChip(4,0,5);
        field.setChip(0,1,6);
        field.setChip(1,1,7);
        field.setChip(2,1,25);
        field.setChip(3,1,7);
        field.setChip(4,1,8);
        field.setChip(0,2,9);
        field.setChip(1,2,1);
        field.setChip(2,2,2);
        field.setChip(3,2,3);
        field.setChip(4,2,4);
        field.setChip(0,3,5);
        field.setChip(0,3,6);
        field.setChip(0,3,7);
        field.setChip(0,3,8);
        field.setChip(0,3,9);
        field.setChip(0,4,1);
        field.setChip(1,4,2);
        field.setChip(2,4,3);
        field.setChip(3,4,4);
        field.setChip(4,4,5);
        game.setGameField(field);
        game.checkSequences();
        Assertions.assertEquals(0,game.field.getChip(0,1));
        Assertions.assertEquals(0,game.field.getChip(1,1));
        Assertions.assertEquals(0,game.field.getChip(2,1));
        Assertions.assertEquals(0,game.field.getChip(3,1));
        Assertions.assertEquals(0,game.field.getChip(4,1));
    }
    @Test
    public void line_test2(){
        Game game = new Game();
        GameField field = new GameField(5,5);
        game.setGameField(field);
        game.checkSequences();
    }
}
