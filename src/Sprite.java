import java.awt.*;

public abstract class Sprite {
    int x, y, left, right, top, bottom, absX, absY;

    abstract void update();
    abstract void draw(Graphics g);
    abstract Json marshall();
}
