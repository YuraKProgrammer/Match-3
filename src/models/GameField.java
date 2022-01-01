package models;

import java.util.Random;

public class GameField {
    Random random = new Random();

    ChipGenerator generator = new ChipGenerator();

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

    public int[][] getChips() {
        return chips;
    }

    public void setChips(int[][] chips) {
        this.chips = chips;
    }

    public void setChip(int x, int y, int value) {
        chips[x][y] = value;
    }

    public int getChip(int x, int y) {
        return chips[x][y];
    }

    private int width = 15;

    private int height = 10;

    private int[][] chips = new int[width][height];

    public void fillRandom() {
        for (var x = 0; x < width; x++) {
            for (var y = 0; y < height; y++) {
                chips[x][y] = generator.createChip();
            }
        }
    }
    public void clean(){
        for (var x = 0; x < width; x++) {
            for (var y = 0; y < height; y++) {
                chips[x][y] = 0;
            }
        }
    }
    public void fillVoid() {
        for (var x = 0; x < width; x++) {
            for (var y = 0; y < height; y++) {
                if (chips[x][y] == 0) {
                    if (y - 1 < 0) {
                        chips[x][y] = generator.createChip();
                    } else {
                        chips[x][y] = chips[x][y - 1];
                        chips[x][y-1] = 0;
                    }
                }
            }
        }
    }

    public void remove(Sequence sequence){
        int bonusH=getChip(sequence.getX(),sequence.getY())+18;
        int bonusV=getChip(sequence.getX(),sequence.getY())+9;
        for (var x=sequence.getX(); x<sequence.getX()+sequence.getWidth(); x++){
            for (var y = sequence.getY(); y<sequence.getY()+sequence.getHeight(); y++){
                if (getChip(x,y)!=0) {
                    setChip(x,y,0);
                }
            }
        }
        if (sequence.getWidth()==4)
            setChip(sequence.getX(),sequence.getY(),bonusH);
        if (sequence.getHeight()==4)
            setChip(sequence.getX(),sequence.getY(),bonusV);
        if ((sequence.getWidth()==5 || sequence.getHeight()==5))
            setChip(sequence.getX(),sequence.getY(),28);
    }

    public void swap(Point point1, Point point2){
        var chip1=getChip(point1.getX(),point1.getY());
        var chip2=getChip(point2.getX(), point2.getY());
        setChip(point1.getX(),point1.getY(),chip2);
        setChip(point2.getX(),point2.getY(),chip1);
    }
}
