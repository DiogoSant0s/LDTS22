package com.DiogoSant0s.hero;

import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {
    protected Position position;
    public Element(int x, int y) {
        position = new Position(x, y);
    }
    protected void setPosition(Position position) {this.position = position;}
    protected Position getPosition() {
        return position;
    }

    public abstract void draw(TextGraphics graphics);
}

