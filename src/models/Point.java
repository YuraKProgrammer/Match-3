package models;

public class Point {
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }
    private int x;
    private int y;
}
