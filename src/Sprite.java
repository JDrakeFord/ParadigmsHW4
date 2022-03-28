import java.awt.*;
import java.util.ArrayList;

public abstract class Sprite {
    int x, y, left, right, top, bottom;
    boolean delete = false;

    abstract void update(Link l, ArrayList<Sprite> sprites);
    abstract void draw(Graphics g);
    abstract Json marshall();
}
