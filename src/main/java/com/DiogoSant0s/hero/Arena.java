package com.DiogoSant0s.hero;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private final List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;
    private final int width;
    private final int height;
    private Hero hero;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
        hero = new Hero(40, 15);
    }

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
    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Coin coin = new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            if (canCoinsSpawn(coin.getPosition())) coins.add(coin);
        }
        return coins;
    }
    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Monster monster = new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            if (canMonstersSpawn(monster.getPosition())) monsters.add(monster);
        }
        return monsters;
    }

    public boolean canHeroMove(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) return false;
        }
        return true;
    }
    public boolean canCoinsSpawn(Position position) {
        if (coins == null) return true;
        for (Coin coin : coins) {
            if (coin.getPosition().equals(position)) return false;
            if (hero.getPosition().equals(position)) return false;
            for (Monster monster : monsters) if (monster.getPosition().equals(position)) return false;
        }
        return true;
    }
    public boolean canMonstersSpawn(Position position) {
        if (monsters == null) return true;
        for (Monster monster : monsters) {
            if (monster.getPosition().equals(position)) return false;
            if (hero.getPosition().equals(position)) return false;
            for (Coin coin : coins) if (coin.getPosition().equals(position)) return false;
        }
        return true;
    }
    private void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowUp -> moveHero(hero.moveUp());
            case ArrowDown -> moveHero(hero.moveDown());
            case ArrowRight -> moveHero(hero.moveRight());
            case ArrowLeft -> moveHero(hero.moveLeft());
            case Character -> {
                if (key.getCharacter() == 'w') moveHero(hero.moveUp());
                if (key.getCharacter() == 'a') moveHero(hero.moveLeft());
                if (key.getCharacter() == 's') moveHero(hero.moveDown());
                if (key.getCharacter() == 'd') moveHero(hero.moveRight());
            }
        }
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#C0C0C0"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        for (Wall wall : walls)
            wall.draw(graphics);
        for (Coin coin: coins)
            coin.draw(graphics);
        for (Monster monster: monsters)
            monster.draw(graphics);
        hero.draw(graphics);
    }

    public void reset() {
        coins = createCoins();
        monsters = createMonsters();
        hero = new Hero(40, 15);
    }
}
