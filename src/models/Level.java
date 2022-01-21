package models;

public class Level {
    public ChipImageType getChipType() {
        return ChipType;
    }

    public void setChipType(ChipImageType chipType) {
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

    private ChipImageType ChipType;
    private int countOfChips;
    private int countOfMoves;
}
