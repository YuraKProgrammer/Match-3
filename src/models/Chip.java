package models;

public class Chip {
    public ChipImageType getImageType() {
        return imageType;
    }

    public void setImageType(ChipImageType imageType) {
        this.imageType = imageType;
    }

    private ChipImageType imageType;

    public Chip(ChipImageType imageType){
        this.imageType=imageType;
    }

    public boolean isVerticalBonus(){
        return isVerticalBonus;
    }
    public boolean isHorizontalBonus(){
        return isHorizontalBonus;
    }

    public void setVerticalBonus(boolean verticalBonus) {
        isVerticalBonus = verticalBonus;
    }

    public void setHorizontalBonus(boolean horizontalBonus) {
        isHorizontalBonus = horizontalBonus;
    }

    private boolean isVerticalBonus;

    private boolean isHorizontalBonus;

    public boolean isSuperStar(){
        return imageType==ChipImageType.SUPERSTAR;
    }
}
