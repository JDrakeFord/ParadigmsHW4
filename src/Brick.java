import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Brick extends Sprite {
    public static int brickSize = 50;
    public static BufferedImage brick_image;


    public Brick(int xIn, int yIn)
    {
        x = xIn;
        y = yIn;
        left = x;
        right = brickSize + x;
        top = y;
        bottom = brickSize + y;
        //Load image
        if(brick_image == null) {
            try {
                brick_image =
                        ImageIO.read(new File("brick.jpg"));
            } catch (Exception e) {
                e.printStackTrace(System.err);
                System.exit(1);
            }
        }
    }

    public Brick(Json ob)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        left = x;
        right = brickSize + x;
        top = y;
        bottom = brickSize + y;
        //Load image
        if(brick_image == null) {
            try {
                brick_image =
                        ImageIO.read(new File("brick.jpg"));
            } catch (Exception e) {
                e.printStackTrace(System.err);
                System.exit(1);
            }
        }
    }

    public Json marshall()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }

    @Override
    public String toString()
    {
        return "Brick (x,y) = (" + x + ", " + y + ")";
    }

    boolean isColliding(Sprite s)
    {
        if(s.right < left) {
            return false;
        }
        if(s.left > right) {
            return false;
        }
        if(s.bottom < top) {
            return false;
        }
        return s.top <= bottom;
    }

    @Override
    void update(Link link, ArrayList<Sprite> sprites) {
        if (isColliding(link)) {
            if (link.right > left && link.oldRight <= left) {
                link.absX = link.oldLeft;
            } else if (link.left < right && link.oldLeft >= right) {
                link.absX = link.oldRight - link.width;
            } else if (link.bottom > top && link.oldBottom <= top) {
                link.absY = link.oldTop;
            } else if (link.top < bottom && link.oldTop >= bottom) {
                link.absY = link.oldBottom - link.height;
            }
        }


    }

    @Override
    void draw(Graphics g) {
        g.drawImage(Brick.brick_image, x - View.scrollPosX, y - View.scrollPosY, null);
    }
}
