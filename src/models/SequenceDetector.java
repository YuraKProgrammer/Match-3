package models;
import java.util.ArrayList;
import java.util.List;

public class SequenceDetector {
    public List<Sequence> search(GameField field){
        var width = field.getWidth();
        var height = field.getHeight();
        var list = new ArrayList<Sequence>();
        for (var x=0; x<width; x++){
            for (var y=0; y<height; y++){
                var k = horizontal(field,x,y);
                if (k>2)
                    list.add(new Sequence(x,y,k,1));
            }
        }
        for (var x=0; x<width; x++){
            for (var y=0; y<height; y++){
                var k = vertical(field,x,y);
                if (k>2)
                    list.add(new Sequence(x,y,1,k));
            }
        }
        return list;
    }

    private int vertical(GameField field, int x, int y){
        var k=0;
        for (var y1=y; y1<field.getHeight(); y1++){
            if (field.getChip(x,y1)==field.getChip(x,y) && field.getChip(x,y)!=0)
                k++;
            else
                return k;
        }
        if (k>2)
            return k;
        return 0;
    }

    private int horizontal(GameField field, int x, int y){
        var k=0;
        for (var x1=x; x1<field.getWidth(); x1++){
            if (field.getChip(x1,y)==field.getChip(x,y) && field.getChip(x,y)!=0)
                k++;
            else
                return k;
        }
        if (k>2)
            return k;
        return 0;
    }
}
