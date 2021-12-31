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
                if (k==30)
                    list.add(new Sequence(x,0,1,field.getHeight()));
                if (k==60)
                    list.add(new Sequence(0,y,field.getWidth(),1));
                if (k>=minSequenceLength && k<6) {
                    list.add(new Sequence(x, y, k, 1));
                }
            }
        }
        for (var x=0; x<width; x++){
            for (var y=0; y<height; y++){
                var k = vertical(field,x,y);
                if (k==30)
                    list.add(new Sequence(x,0,1,field.getHeight()));
                if (k==60)
                    list.add(new Sequence(0,y,field.getWidth(),1));
                if (k>=minSequenceLength && k<6)
                    list.add(new Sequence(x,y,1,k));
            }
        }
        return list;
    }

    private int vertical(GameField field, int x, int y){
        var k=0;
        for (var y1=y; y1<field.getHeight(); y1++){
            int chip = field.getChip(x, y);
            if ((field.getChip(x,y1)== chip-18 || field.getChip(x,y1)== chip-9 || field.getChip(x,y1)== chip || field.getChip(x,y1)== chip +9 || field.getChip(x,y1)== chip +18) && chip !=0) {
                if(field.getChip(x,y1)== chip +9)
                    return 30;
                if (  field.getChip(x,y1)== chip +18)
                    return 60;
                k++;
            }
            else
                return k;
        }
        if (k>=minSequenceLength)
            return k;
        return 0;
    }

    private int horizontal(GameField field, int x, int y){
        var k=0;
        for (var x1=x; x1<field.getWidth(); x1++) {
            int chip = field.getChip(x, y);
            if ((field.getChip(x1, y) == chip - 18 || field.getChip(x1, y) == chip - 9 || field.getChip(x1, y) == chip || field.getChip(x1, y) == chip + 9 || field.getChip(x1, y) == chip + 18) && chip != 0){
                if (field.getChip(x1, y) == chip + 9)
                    return 30;
                if (field.getChip(x1, y) == chip + 18)
                    return 60;
            k++;
        }
            else
                return k;
        }
        if (k>=minSequenceLength)
            return k;
        return 0;
    }
}
