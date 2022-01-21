package models.generators;

import models.ChipImageType;
import models.Level;

import java.util.Random;

public class LevelGenerator {
    Random random = new Random();
    private static final int chipTypes=9;
    private static final int maxCountOfMoves=60;
    private static final int maxCountOfChips=50;
    public Level generate(){
        var level = new Level();
        int chipType = random.nextInt(chipTypes)+1;
        switch (chipType){
            case 1:level.setChipType(ChipImageType.T1); break;
            case 2:level.setChipType(ChipImageType.T2); break;
            case 3:level.setChipType(ChipImageType.T3); break;
            case 4:level.setChipType(ChipImageType.T4); break;
            case 5:level.setChipType(ChipImageType.T5); break;
            case 6:level.setChipType(ChipImageType.T6); break;
            case 7:level.setChipType(ChipImageType.T7); break;
            case 8:level.setChipType(ChipImageType.T8); break;
            case 9:level.setChipType(ChipImageType.T9);
        }
        level.setCountOfMoves(random.nextInt(maxCountOfMoves)+1);
        level.setCountOfChips(random.nextInt(maxCountOfChips)+1);
        return level;
    }
}
