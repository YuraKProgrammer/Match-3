package tests;

import models.GameField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class checkVoid_Test {
    @Test
    public void checkVoid_Test1(){
        GameField field= new GameField(4,4);
        field.setChip(0,0,1);
        field.setChip(1,0,2);
        field.setChip(2,0,3);
        field.setChip(3,0,4);
        field.setChip(0,1,5);
        field.setChip(1,1,0);
        field.setChip(2,1,0);
        field.setChip(3,1,0);
        field.setChip(0,2,6);
        field.setChip(1,2,7);
        field.setChip(2,2,8);
        field.setChip(3,2,9);
        field.setChip(0,3,10);
        field.setChip(1,3,11);
        field.setChip(2,3,12);
        field.setChip(3,3,13);
        field.checkVoid();
        Assertions.assertEquals(2, field.getChip(1,1));
        Assertions.assertEquals(3, field.getChip(2,1));
        Assertions.assertEquals(4, field.getChip(3,1));
    }
}
