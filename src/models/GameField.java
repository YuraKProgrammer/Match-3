package models;

import models.bonuses.Lightning;
import models.bonuses.SimilBonus;

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
/*
    public boolean checkRowInLines() {
        for (var x = 0; x < width; x++) {
            for (var y = 0; y < height; y++) {
                var v = checkVertical(x,y);
                if (v==3){
                    chips[x][y]=0;
                    chips[x][y+1]=0;
                    chips[x][y+2]=0;
                    score++;
                    return true;
                }
            }
        }
        for (var y = 0; y < height; y++) {
            for (var x = 0; x < height; x++) {
                var h = checkHorizontal(x,y);
                if (h==3){
                    chips[x][y]=0;
                    chips[x+1][y]=0;
                    chips[x+2][y]=0;
                    score++;
                    return true;
                }
            }
        }
        return false;
    }

    private int checkVertical(int startX ,int startY){
        var startChip=chips[startX][startY];
        var k=0;
        if (startY+2<height) {
            for (var y = startY; y < startY + 3; y++) {
                if (chips[startX][y] == startChip) {
                    k++;
                }
                else
                    return 0;
            }
            return k;
        }
        return  k;
    }

    private int checkHorizontal(int startX ,int startY){
        var startChip=chips[startX][startY];
        var k=0;
        if (startX+2<width) {
            for (var x = startX; x < startX + 3; x++) {
                if (chips[x][startY] == startChip)
                    k++;
                else
                    k = 0;
            }
        }
        return  k;
    }
*/
    public void checkVoid() {
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
}
