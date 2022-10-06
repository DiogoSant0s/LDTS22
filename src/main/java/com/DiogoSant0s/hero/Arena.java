package com.DiogoSant0s.hero;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private final List<Wall> walls;
    private final int width;
    private final int height;
    private final Hero hero;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.walls = createWalls();
        hero = new Hero(10, 10);
    }

    public int getWidth() {return width;}
    public int getHeight() {return height;}

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0, "-"));
            walls.add(new Wall(c, height - 1, "-"));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r, "|"));
            walls.add(new Wall(width - 1, r, "|"));
        }
        return walls;
    }

    public boolean canHeroMove(Position position) {
        return 0 < position.get_x() && position.get_x() < getWidth() - 1 && 0 < position.get_y() && position.get_y() < getHeight() - 1;
    }
    private void moveHero(Position position) {
        if (canHeroMove(position))
            hero.SetPosition(position);
    }

    public void processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.ArrowUp) moveHero(hero.moveUp());
        if (key.getKeyType() == KeyType.ArrowDown) moveHero(hero.moveDown());
        if (key.getKeyType() == KeyType.ArrowRight) moveHero(hero.moveRight());
        if (key.getKeyType() == KeyType.ArrowLeft) moveHero(hero.moveLeft());
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'w') moveHero(hero.moveUp());
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 's') moveHero(hero.moveDown());
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'd') moveHero(hero.moveRight());
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'a') moveHero(hero.moveLeft());
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        for (Wall wall : walls)
            wall.draw(graphics);
        hero.draw(graphics);
    }

    public void reset() {
        
    }
}
