package com.DiogoSant0s.hero;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class Game {
    private final Screen screen;
    private final Arena arena;
    private final TextGraphics graphics;

    public Game() throws IOException {
        arena = new Arena(80, 40);
        TerminalSize terminalSize = new TerminalSize(80, 40);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        graphics = screen.newTextGraphics();
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(graphics);
        screen.refresh();
    }

    private void processKey(KeyStroke key) throws IOException {
        arena.processKey(key);
        GameState();
    }
    private void GameState() throws IOException {
        if (arena.energy == 0) {
            System.out.println("Game over. Better luck next time.");
            screen.close();
        }
        if (arena.gold == 10) {
            System.out.println("Well done, you have won");
            screen.close();
        }
    }

    public void run() throws IOException {
        while (true) {
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
            switch (key.getKeyType()) {
                case Character -> {
                    char character = key.getCharacter();
                    if (character == 'q' || character == 'Q') screen.close();
                    if ((character == 'R' || character == 'r')) arena.reset();
                }
                case Escape -> screen.close();
                default -> arena.processKey(key);
            }
            if (key.getKeyType() == KeyType.EOF) break;
        }
    }
}
