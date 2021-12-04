package models;

import java.util.Random;

public class ChipGenerator {
    Random random = new Random();
    public int createChip(){
        return random.nextInt(9)+1;
    }
}
