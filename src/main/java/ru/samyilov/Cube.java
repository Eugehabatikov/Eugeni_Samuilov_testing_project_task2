package ru.samyilov;

public class Cube {
    private int a;
    private int b;
    private int c;

    public int width() {
        return a;
    }

    public int length() {
        return b;
    }

    public int height() {
        return c;
    }

    public void setWidth(int a) {
        if(a <= 0) {
            throw new IllegalArgumentException();
        }
            this.a = a;
    }

    public void setLength(int b) {
        if(b <= 0) {
            throw new IllegalArgumentException();
        }
        this.b = b;
    }

    public void setHeight(int c) {
        if(c <= 0) {
        throw new IllegalArgumentException();
    }
        this.c = c;
    }
    public int getVolume(){
      return this.a*this.b*this.c;
    }
    public int getSquare(){
        return this.a * this.b * 2 + this.c * this.b * 2 + this.b * this.c * 2;
    }
    public Cube(int length,int width,int height){
        this.setHeight(height);
        this.setLength(length);
        this.setWidth(width);
    }
}
