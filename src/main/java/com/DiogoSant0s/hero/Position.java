package com.DiogoSant0s.hero;

public class Position {
    private int x_;
    private int y_;
    public Position(int x, int y){
    x_ = x;
    y_ = y;
    }
    public int get_x(){
        return x_;
    }
    public int get_y(){
        return y_;
    }
    public void set_x(int x) {x_ = x;}
    public void set_y(int y) {y_ = y;}
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Position p = (Position) o;
        return x_ == p.get_x() && y_ == p.get_y();
    }
}
