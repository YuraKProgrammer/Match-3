package models.detectors;
import models.Chip;
import models.GameField;
import models.Sequence;

import java.util.ArrayList;
import java.util.List;

public class SequenceDetector implements ISequenceDetector {

    public static final int minSequenceLength = 3;
    public static final int maxSequenceLength = 5;

    @Override
    public List<Sequence> search(GameField field){
        var width = field.getWidth();
        var height = field.getHeight();
        var list = new ArrayList<Sequence>();
        for (var x=0; x<width; x++){
            for (var y=0; y<height; y++){
                var n=0;
                var k = getHorizontalCount(field,x,y);
                if (k>=minSequenceLength && k<=maxSequenceLength) {
                    Sequence sequence = new Sequence(x, y, k, 1, field.getChip(x, y).getImageType());
                    for (var x1=x;x1<x+k; x1++){
                        Chip chip1 = field.getChip(x1, y);
                        if (chip1==null)
                            continue;
                        if (chip1.isHorizontalBonus())
                            list.add(new Sequence(0,y,width,1,field.getChip(x,y).getImageType()));
                        if (chip1.isVerticalBonus())
                            list.add((new Sequence(x1,0,1,height,field.getChip(x, y).getImageType())));

                    }
                    list.add(sequence);
                }
            }
        }
        for (var x=0; x<width; x++){
            for (var y=0; y<height; y++){
                var k = getVerticalCount(field,x,y);
                if (k>=minSequenceLength && k<=maxSequenceLength) {
                    Sequence sequence = new Sequence(x, y, 1, k, field.getChip(x, y).getImageType());
                    for(var y1=y; y1<y+k; y1++){
                        Chip chip1 = field.getChip(x, y1);
                        if (chip1==null)
                            continue;
                        if (chip1.isVerticalBonus())
                            list.add(new Sequence(x,0,1,height,field.getChip(x, y).getImageType()));
                        if (chip1.isHorizontalBonus())
                            list.add(new Sequence(0,y1,width,1,field.getChip(x, y).getImageType()));
                    }
                    list.add(sequence);
                }
            }
        }
        return list;
    }

    private int getVerticalCount(GameField field, int x, int y){
        var chip = field.getChip(x, y);
        if (chip==null)
            return 0;
        var k=0;
        for (var y1=y; y1<field.getHeight(); y1++){
            Chip chip1 = field.getChip(x, y1);
            if (chip1 !=null) {
                if (chip1.getImageType() == chip.getImageType()) {
                    k++;
                } else {
                    break;
                }
            }
        }
        return k;
    }

    private int getHorizontalCount(GameField field, int x, int y){
        var chip = field.getChip(x, y);
        if (chip==null)
            return 0;
        var k=0;
        for (var x1=x; x1<field.getWidth(); x1++) {
            Chip chip1 = field.getChip(x1, y);
            if (chip1 !=null) {
                if (chip1.getImageType() == chip.getImageType()) {
                    k++;
                } else {
                    break;
                }
            }
        }
        return k;
    }

    private boolean getVerticalBonus(GameField field){
        for (var x=0; x<field.getWidth(); x++){
            for (var y=0; y< field.getHeight(); y++){
                if(getVerticalCount(field,x,y)<minSequenceLength)
                    return false;
            }
        }
        return false;
    }
    private boolean getHorizontalBonus(GameField field){
        for (var x=0; x<field.getWidth(); x++){
            for (var y=0; y< field.getHeight(); y++){
                return  false;
            }
        }
        return false;
    }
}
