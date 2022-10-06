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


}
