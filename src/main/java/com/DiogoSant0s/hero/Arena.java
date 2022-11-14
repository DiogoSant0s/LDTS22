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
    public int energy = 10;
    public int gold = 0;
    private int LastChance = 1;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
        hero = new Hero(40, 20);
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
    private boolean canHeroMove(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) return false;
        }
        return true;
    }
    private boolean canMonstersMove(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) return false;
        }
        return !hero.getPosition().equals(position);
    }
    private boolean canCoinsSpawn(Position position) {
        if (coins == null) return true;
        for (Coin coin : coins) {
            if (coin.getPosition().equals(position)) return false;
            if (hero.getPosition().equals(position)) return false;
            for (Monster monster : monsters) if (monster.getPosition().equals(position)) return false;
            for (Wall wall : walls) {if (wall.getPosition().equals(position)) return false;}
        }
        return true;
    }
    private boolean canMonstersSpawn(Position position) {
        if (monsters == null) return true;
        for (Monster monster : monsters) {
            if (monster.getPosition().equals(position)) return false;
            if (hero.getPosition().equals(position)) return false;
            for (Coin coin : coins) if (coin.getPosition().equals(position)) return false;
            for (Wall wall : walls) {if (wall.getPosition().equals(position)) return false;}
        }
        return true;
    }
    private void retrieveCoins(Position position) {
        for (Coin coin : coins) {
            if (coin.getPosition().equals(position)) {
                gold++;
                coins.remove(coin);
                System.out.print("You now have ");
                System.out.print(gold);
                System.out.println(" gold coins.");
                break;
            }
        }
    }
    private void verifyMonsterCollisions(Position position) {
        for (Monster monster : monsters) {
            if (monster.getPosition().equals(position)) {
                energy--;
                monsters.remove(monster);
                System.out.print("You have ");
                System.out.print(energy);
                System.out.println(" mana remaining.");
                if (energy == 3 && LastChance == 1) {
                    System.out.println("Be careful. You'll die if you reach zero.");
                }
                if (energy == 1 && LastChance == 1) {
                    System.out.println("Giving you one last chance adventurer. Here, take this. It'll help you on your mission");
                    energy += 5;
                    LastChance--;
                    System.out.print("You have ");
                    System.out.print(energy);
                    System.out.println(" mana remaining.");
                }
                else if (energy == 1 && LastChance == 0){
                    System.out.println("I don't have any more healing items for you, my friend. One more hit and it's over.");
                }
                break;
            }
        }
    }
    public void moveHero(Position position) {
        if (canHeroMove(position)) {
            hero.setPosition(position);
            retrieveCoins(position);
            verifyMonsterCollisions(position);
        }
    }
    private void moveMonsters() {
        for (Monster monster : monsters) {
            Position pos = monster.getPosition();
            monster.setPosition(monster.move());
            if (!canMonstersMove(monster.getPosition())) {
                monster.setPosition(pos);
            }
        }
    }
    public void processKey(KeyStroke key) {
        if (monsters.isEmpty() && LastChance == 1) {
            monsters = createMonsters();
        }
        if (coins.isEmpty()) coins = createCoins();
        switch (key.getKeyType()) {
            case ArrowUp -> {
                moveHero(hero.moveUp());
                moveMonsters();
            }
            case ArrowDown -> {
                moveHero(hero.moveDown());
                moveMonsters();
            }
            case ArrowRight -> {
                moveHero(hero.moveRight());
                moveMonsters();
            }
            case ArrowLeft -> {
                moveHero(hero.moveLeft());
                moveMonsters();
            }
            case Character -> {
                if (key.getCharacter() == 'w') {moveHero(hero.moveUp()); moveMonsters();}
                if (key.getCharacter() == 'a') {moveHero(hero.moveLeft()); moveMonsters();}
                if (key.getCharacter() == 's') {moveHero(hero.moveDown()); moveMonsters();}
                if (key.getCharacter() == 'd') {moveHero(hero.moveRight()); moveMonsters();}
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
        LastChance = 1;
        energy = 10;
        gold = 0;
        hero = new Hero(40, 20);
    }
}