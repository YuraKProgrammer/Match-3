package models;

public class Game {
    public GameField field = new GameField(15,10);

    private int score;

    public int getScore() {
        return score;
    }

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

    SequenceDetector detector = new SequenceDetector();

    public boolean replacement() {
        //Здесь создать клона игры, где проверить приаедёт ли перестановка к комбинации
        if (checkRows()) {
            if (firstSelectedX > -1 && secondSelectedX > -1) {
                if (!(Math.abs(firstSelectedX - secondSelectedX) == 1 && Math.abs(firstSelectedY - secondSelectedY) == 1)) {
                    if (Math.abs(firstSelectedX - secondSelectedX)<2 && Math.abs(firstSelectedY - secondSelectedY)<2) {
                        var n = field.getChip(secondSelectedX, secondSelectedY);
                        field.setChip(secondSelectedX, secondSelectedY, field.getChip(firstSelectedX, firstSelectedY));
                        field.setChip(firstSelectedX, firstSelectedY, n);
                        firstSelectedX = -1;
                        secondSelectedX = -1;
                        firstSelectedY = -1;
                        secondSelectedY = -1;
                        n = 0;
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    private boolean replacement2(){
        if (firstSelectedX > -1 && secondSelectedX > -1) {
            if (!(Math.abs(firstSelectedX - secondSelectedX) == 1 && Math.abs(firstSelectedY - secondSelectedY) == 1)) {
                if (Math.abs(firstSelectedX - secondSelectedX)<2 && Math.abs(firstSelectedY - secondSelectedY)<2) {
                    var n = field.getChip(secondSelectedX, secondSelectedY);
                    field.setChip(secondSelectedX, secondSelectedY, field.getChip(firstSelectedX, firstSelectedY));
                    field.setChip(firstSelectedX, firstSelectedY, n);
                    firstSelectedX = -1;
                    secondSelectedX = -1;
                    firstSelectedY = -1;
                    secondSelectedY = -1;
                    n = 0;
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    private boolean checkRows(){
        var game1 = new Game();
        for (var x=0; x<field.getWidth(); x++){
            for (var y=0; y<field.getHeight(); y++){
                game1.field.setChip(x,y,field.getChip(x,y));
            }
        }
        game1.setFirstSelectedX(firstSelectedX);
        game1.setSecondSelectedX(secondSelectedX);
        game1.setFirstSelectedY(firstSelectedY);
        game1.setSecondSelectedY(secondSelectedY);
        game1.replacement2();
        if (game1.checkSequences()){
            return true;
        }
        else
            return false;
    }

    public boolean checkSequences(){
        var list = detector.search(field);
        if (list.isEmpty())
            return false;
        else {
            for (var s: list) {
                cleanSequence(s);
            }
            return true;
        }
    }

    private void cleanSequence(Sequence sequence){
        for (var x=sequence.getX(); x<sequence.getX()+sequence.getWidth(); x++){
            for (var y = sequence.getY(); y<sequence.getY()+sequence.getHeight(); y++){
                if (field.getChip(x,y)!=0) {
                    field.setChip(x,y,0);
                    score++;
                }
            }
        }
    }
}
