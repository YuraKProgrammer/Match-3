package models;

import java.util.Random;

public class ChipGenerator {
    Random random = new Random();
    public ChipImageType createChip(){
        switch (random.nextInt(9)){
            case 0: return ChipImageType.T1;
            case 1: return ChipImageType.T2;
            case 2: return ChipImageType.T3;
            case 3: return ChipImageType.T4;
            case 4: return ChipImageType.T5;
            case 5: return ChipImageType.T6;
            case 6: return ChipImageType.T7;
            case 7: return ChipImageType.T8;
            case 8: return ChipImageType.T9;
            default: return null;
        }
    }
}
