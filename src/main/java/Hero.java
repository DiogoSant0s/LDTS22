import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;

public class Hero {
    Position position;
    public Hero(int x, int y) {

    }

    public Position moveUp() {
        return new Position(position.get_x(), position.get_y() - 1);
    }
    public Position moveDown() {
        return new Position(position.get_x(), position.get_y() + 1);
    }
    public Position moveLeft() {
        return new Position(position.get_x() - 1, position.get_y());
    }
    public Position moveRight() {
        return new Position(position.get_x() + 1, position.get_y());
    }
    public void draw(Screen screen) throws IOException{
        screen.setCharacter(position.get_x(), position.get_y(), TextCharacter.fromCharacter('X')[0]);
    }

    public void SetPosition(Position position) {
        position.setPosition(position);
    }
}
