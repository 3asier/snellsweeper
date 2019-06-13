package com.easier.minesweeper;

public class PVector {

    private double x, y;

    public PVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public PVector(PVector pVector) {
        this.x = pVector.x;
        this.y = pVector.y;
    }

    public PVector() {
        x = 0;
        y = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
