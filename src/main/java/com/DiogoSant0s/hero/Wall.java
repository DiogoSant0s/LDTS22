package com.DiogoSant0s.hero;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element{
    private final String drawn;
    public Wall(int x, int y, String drawn) {
        super(x, y);
        this.drawn = drawn;}

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF00FF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.get_x(), position.get_y()), drawn);
    }
}
