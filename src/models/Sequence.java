package models;

/**
 * Непрерывная последовательность фигурок одного типа
 */
public class Sequence {
    public Sequence(int x, int y, int width, int height, ChipImageType imageType){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.imageType=imageType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private int x;
    private int y;
    private int width;
    private int height;

    public ChipImageType getImageType() {
        return imageType;
    }

    public void setImageType(ChipImageType imageType) {
        this.imageType = imageType;
    }

    private ChipImageType imageType;
}
