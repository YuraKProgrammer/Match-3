package models;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public Game() {
        swapDetector = new SwapDetector(detector);
    }

    public GameField field = new GameField(15, 10);

    private int score;

    public int getScore() {
        return score;
    }

    private SwapDetector swapDetector;

    public void setGameField(GameField field){this.field=field;};

    public void setScore(int score) {
        this.score = score;
    }

    public int getFirstSelectedX() {
        return firstSelectedX;
    }

    public void setFirstSelectedX(int firstSelectedX) {
        this.firstSelectedX = firstSelectedX;
    }

    public int getFirstSelectedY() {
        return firstSelectedY;
    }

    public void setFirstSelectedY(int firstSelectedY) {
        this.firstSelectedY = firstSelectedY;
    }

    public int getSecondSelectedX() {
        return secondSelectedX;
    }

    public void setSecondSelectedX(int secondSelectedX) {
        this.secondSelectedX = secondSelectedX;
    }

    public int getSecondSelectedY() {
        return secondSelectedY;
    }

    public void setSecondSelectedY(int secondSelectedY) {
        this.secondSelectedY = secondSelectedY;
    }

    private int firstSelectedX = -1;

    private int firstSelectedY = -1;

    private int secondSelectedX = -1;

    private int secondSelectedY = -1;

    private List<Sequence> list;

    SequenceDetector detector = new SequenceDetector();

    public boolean replacement() {
        Point point1 = new Point(firstSelectedX, firstSelectedY);
        Point point2 = new Point(secondSelectedX, secondSelectedY);
        if (getFirstSelectedX()!=-1 && secondSelectedX!=-1 && firstSelectedY!=-1 && secondSelectedY!=-1) {
            if (field.getChip(firstSelectedX, firstSelectedY)==28 && field.getChip(secondSelectedX, secondSelectedY) == 28){
                field.clean();
                score+=150;
            }
            if (field.getChip(firstSelectedX, firstSelectedY) == 28) {
                int chip = field.getChip(secondSelectedX, secondSelectedY);
                for (var x = 0; x < field.getWidth(); x++) {
                    for (var y = 0; y < field.getHeight(); y++) {
                        if (field.getChip(x, y) == chip) {
                            field.setChip(x, y, 0);
                            score++;
                        }
                    }
                    field.setChip(firstSelectedX,firstSelectedY,0);
                }
            }
            if (field.getChip(secondSelectedX, secondSelectedY) == 28) {
                int chip = field.getChip(firstSelectedX, firstSelectedY);
                for (var x = 0; x < field.getWidth(); x++) {
                    for (var y = 0; y < field.getHeight(); y++) {
                        if (field.getChip(x, y) == chip) {
                            field.setChip(x, y, 0);
                            score++;
                        }
                    }
                    field.setChip(secondSelectedX,secondSelectedY,0);
                }
            }
        }

        if (!swapDetector.check(field, point1, point2)) {
            return false;
        }
        field.swap(point1,point2);
        firstSelectedX = -1;
        secondSelectedX = -1;
        firstSelectedY = -1;
        secondSelectedY = -1;
        return true;
    }


    public boolean checkSequences(){
        list = detector.search(field);
        if (list.isEmpty())
            return false;
        else {
            for (var s: list) {
                field.remove(s);
                score+=s.getWidth()*s.getHeight();
            }
            return true;
        }
    }
}
