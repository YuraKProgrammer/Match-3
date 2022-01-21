package models;

import models.generators.ChipGenerator;

import java.util.Random;

public class GameField {
    Random random = new Random();

    ChipGenerator generator = new ChipGenerator();

    public int getCountChip1() {
        return countChip1;
    }

    public int getCountChip2() {
        return countChip2;
    }

    public int getCountChip3() {
        return countChip3;
    }

    public int getCountChip4() {
        return countChip4;
    }

    public int getCountChip5() {
        return countChip5;
    }

    public int getCountChip6() {
        return countChip6;
    }

    public int getCountChip7() {
        return countChip7;
    }

    public int getCountChip8() {
        return countChip8;
    }

    public int getCountChip9() {
        return countChip9;
    }

    private int countChip1=0;
    private int countChip2=0;
    private int countChip3=0;
    private int countChip4=0;
    private int countChip5=0;
    private int countChip6=0;
    private int countChip7=0;
    private int countChip8=0;
    private int countChip9=0;

    public GameField(int width, int height){
        this.width=width;
        this.height=height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Chip[][] getChips() {
        return chips;
    }

    public void setChips(Chip[][] chips) {
        this.chips = chips;
    }

    public void setChip(int x, int y, ChipImageType value) {
            if (value==null){
                chips[x][y]=null;
                return;
            }
            if (chips[x][y]==null)
                chips[x][y]=new Chip(value);
            else
                chips[x][y].setImageType(value);
    }

    public Chip getChip(int x, int y) {
        if (chips[x][y]==null)
            return null;
        return chips[x][y];
    }

    public ChipImageType getChipValue(int x, int y) {
        if (chips[x][y]==null)
            return null;
        return chips[x][y].getImageType();
    }

    private int width = 15;

    private int height = 10;

    private Chip[][] chips = new Chip[width][height];

    public void fillRandom() {
        for (var x = 0; x < width; x++) {
            for (var y = 0; y < height; y++) {
                chips[x][y]=new Chip(generator.createChip());
            }
        }
    }
    public void clean(){
        for (var x = 0; x < width; x++) {
            for (var y = 0; y < height; y++) {
                chips[x][y] = null;
            }
        }
    }
    public void fillVoid() {
        for (var x = 0; x < width; x++) {
            for (var y = 0; y < height; y++) {
                if (chips[x][y] == null) {
                    if (y - 1 < 0) {
                        setChip(x,y,generator.createChip());
                        chips[x][y].setImageType(generator.createChip());
                    } else {
                        chips[x][y] = chips[x][y - 1];
                        chips[x][y-1] = null;
                    }
                }
            }
        }
    }

    public void remove(Sequence sequence){
        for (var x=sequence.getX(); x<sequence.getX()+sequence.getWidth(); x++){
            for (var y = sequence.getY(); y<sequence.getY()+sequence.getHeight(); y++){
                if (getChip(x,y)!=null) {
                    switch (chips[x][y].getImageType()){
                        case T1 -> countChip1++;
                        case T2 -> countChip2++;
                        case T3 -> countChip3++;
                        case T4 -> countChip4++;
                        case T5 -> countChip5++;
                        case T6 -> countChip6++;
                        case T7 -> countChip7++;
                        case T8 -> countChip8++;
                        case T9 -> countChip9++;
                    }
                    chips[x][y]=null;
                }
            }
        }
        if (sequence.getWidth()==4) {
            setChip(sequence.getX(),sequence.getY(),sequence.getImageType());
            var chip = getChip(sequence.getX(),sequence.getY());
            chip.setHorizontalBonus(true);
        }
        if (sequence.getHeight()==4){
            setChip(sequence.getX(),sequence.getY(),sequence.getImageType());
            var chip = getChip(sequence.getX(),sequence.getY());
            chip.setVerticalBonus(true);
        }
        if ((sequence.getWidth()==5 || sequence.getHeight()==5) && sequence.getImageType()!=null)
            setChip(sequence.getX(),sequence.getY(),ChipImageType.SUPERSTAR);
    }

    public void swap(Point point1, Point point2){
        var chip1=getChipValue(point1.getX(),point1.getY());
        var chip2=getChipValue(point2.getX(), point2.getY());
        setChip(point1.getX(),point1.getY(),chip2);
        setChip(point2.getX(),point2.getY(),chip1);
    }
}
