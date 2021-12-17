package models;

import java.util.Random;

public class LevelGenerator {
    Random random = new Random();
    public Level generate(){
        var level = new Level();
        level.setChipType(random.nextInt(9));
        level.setCountOfMoves(random.nextInt(50));
        level.setCountOfChips(random.nextInt(40));
        return level;
    }
}
