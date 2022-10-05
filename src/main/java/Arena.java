import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class Arena {
    private int width;
    private int height;
    private Hero hero;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        hero = new Hero(10, 10);
    }
    public boolean canHeroMove(Position position) {
        return position.get_x() < width && position.get_y() < height;
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
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(""));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
    }
}
