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
    private Screen screen;
    private Arena arena;
    public Game() {
        try {
            arena = new Arena(75, 25);
            TerminalSize terminalSize = new TerminalSize(75, 25);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            TextGraphics graphics = screen.newTextGraphics();
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }
    private void processKey(KeyStroke key) {
        arena.processKey(key);
    }
    public void run() throws IOException {
        while (true) {
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
            switch (key.getKeyType()) {
                case Character:
                    char character = key.getCharacter();
                    if (character == 'q' || character == 'Q') screen.close();
                    else if((character == 'R' || character == 'r')) {
                        arena.reset();
                    }
                    break;
                case EOF:
                    break;
                default:
                    arena.processKey(key);
                    break;
        }
    }
}
