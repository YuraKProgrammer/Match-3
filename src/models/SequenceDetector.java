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
                var f=field.getChip(x,y);
                if (f>9){
                    if (f<19){
                        n=1;
                    }
                    if (f<28 && f>18){
                        n=2;
                    }
                }
                var k = horizontal(field,x,y);
                if (k>=minSequenceLength) {
                    if (n==2) {
                    }
                    list.add(new Sequence(x, y, k, 1));
                }
            }
        }
        for (var x=0; x<width; x++){
            for (var y=0; y<height; y++){
                var k = vertical(field,x,y);
                if (k>=minSequenceLength)
                    list.add(new Sequence(x,y,1,k));
            }
        }
        return list;
    }

    private int vertical(GameField field, int x, int y){
        var k=0;
        for (var y1=y; y1<field.getHeight(); y1++){
            if ((field.getChip(x,y1)==field.getChip(x,y) || field.getChip(x,y1)==field.getChip(x,y)+9) && field.getChip(x,y)!=0)
                k++;
            else
                return k;
        }
        if (k>=minSequenceLength)
            return k;
        return 0;
    }

    private int horizontal(GameField field, int x, int y){
        var k=0;
        for (var x1=x; x1<field.getWidth(); x1++){
            if ((field.getChip(x1,y)==field.getChip(x,y) || field.getChip(x1,y)==field.getChip(x,y)+18) && field.getChip(x,y)!=0)
                k++;
            else
                return k;
        }
        if (k>=minSequenceLength)
            return k;
        return 0;
    }
}
