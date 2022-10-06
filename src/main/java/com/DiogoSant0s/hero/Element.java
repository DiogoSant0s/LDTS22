package com.DiogoSant0s.hero;

import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {
    public Element(int x, int y) {
        position = new Position(x, y);
    }

    protected void setPosition(int x, int y) {
        position.set_x(x);
        position.set_y(y);
    }

    public abstract void draw(TextGraphics graphics);

    protected Position getPosition() {
        return position;
    }

    protected Position position;
}

