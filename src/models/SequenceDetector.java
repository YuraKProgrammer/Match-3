package models;
import java.util.ArrayList;
import java.util.List;

public class SequenceDetector implements ISequenceDetector {

    public static final int minSequenceLength = 3;

    @Override
    public List<Sequence> search(GameField field){
        var width = field.getWidth();
        var height = field.getHeight();
        var list = new ArrayList<Sequence>();
        for (var x=0; x<width; x++){
            for (var y=0; y<height; y++){
                var n=0;
                var k = horizontal(field,x,y);
                if (k>=minSequenceLength && k<6) {
                    list.add(new Sequence(x, y, k, 1,field.getChip(x,y).getImageType()));
                }
            }
        }
        for (var x=0; x<width; x++){
            for (var y=0; y<height; y++){
                var k = vertical(field,x,y);
                if (k>=minSequenceLength && k<6)
                    list.add(new Sequence(x,y,1,k,field.getChip(x,y).getImageType()));
            }
        }
        return list;
    }

    private int vertical(GameField field, int x, int y){
        var k=0;
        for (var y1=y; y1<field.getHeight(); y1++){
            var chip = field.getChip(x, y);
            if (chip!=null && field.getChip(x,y1)!=null) {
                if (field.getChip(x, y1).getImageType() == chip.getImageType()) {
                    k++;
                } else
                    return k;
            }
        }
        if (k>=minSequenceLength)
            return k;
        return 0;
    }

    private int horizontal(GameField field, int x, int y){
        var k=0;
        for (var x1=x; x1<field.getWidth(); x1++) {
            var chip = field.getChip(x, y);
            if (chip!=null && field.getChip(x1,y)!=null) {
                if (field.getChip(x1, y).getImageType() == chip.getImageType()) {
                    k++;
                } else
                    return k;
            }
        }
        if (k>=minSequenceLength)
            return k;
        return 0;
    }
}
