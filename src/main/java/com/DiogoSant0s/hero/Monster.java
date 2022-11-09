package com.DiogoSant0s.hero;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element {
    public Monster(int x, int y) {super(x, y);}
    public Position move() {
        Random random = new Random();
        switch (random.nextInt(4)) {
            case 0 -> {
                return new Position(position.get_x(), position.get_y() - 1);
            }
            case 1 -> {
                return new Position(position.get_x(), position.get_y() + 1);
            }
            case 2 -> {
                return new Position(position.get_x() - 1, position.get_y());
            }
            case 3 -> {
                return new Position(position.get_x() + 1, position.get_y());
            }
        }
        return new Position(position.get_x(), position.get_y());
    }
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#228B22"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.get_x(), position.get_y()), "M");
    }
}
