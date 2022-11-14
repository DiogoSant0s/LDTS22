package com.DiogoSant0s.hero;

public class Position {
    private final int x_;
    private final int y_;
    public Position(int x, int y) {x_ = x; y_ = y;}
    public int get_x(){return x_;}
    public int get_y(){return y_;}
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Position p = (Position) o;
        return x_ == p.get_x() && y_ == p.get_y();
    }
}
