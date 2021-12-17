package models;

public class Level {
    public int getChipType() {
        return ChipType;
    }

    public void setChipType(int chipType) {
        ChipType = chipType;
    }

    public int getCountOfChips() {
        return countOfChips;
    }

    public void setCountOfChips(int countOfChips) {
        this.countOfChips = countOfChips;
    }

    public int getCountOfMoves() {
        return countOfMoves;
    }

    public void setCountOfMoves(int countOfMoves) {
        this.countOfMoves = countOfMoves;
    }

    private int ChipType;
    private int countOfChips;
    private int countOfMoves;
}
