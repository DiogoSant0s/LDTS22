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
    private Game arena;
    public Game() {
        try {
            Hero hero = new Hero(10, 10);
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            Screen screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void draw(TextGraphics graphics) throws IOException {
        this.screen.clear();
        this.arena.draw(screen.newTextGraphics());
        this.screen.refresh();
    }
    private void processKey(KeyStroke key) throws IOException {
        arena.processKey(key);
    }
    public void run() throws IOException {
        while (true) {
            draw(screen.newTextGraphics());
            KeyStroke key = screen.readInput();
            processKey(key);
            if (key.getKeyType() == KeyType.EOF) break;
        }
    }
}
