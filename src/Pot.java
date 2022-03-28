import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Pot extends Sprite{
    public static int potSize = 48;
    public static BufferedImage pot_image;
    public static BufferedImage broken_pot_image;
    BufferedImage currentImage;
    double speed = 10.0;
    int brokenFrames = 20;
    enum Direction{UP, DOWN, LEFT, RIGHT, NONE}
    boolean broken = false;
    Direction direction;

    @Override
    void update(Link link, ArrayList<Sprite> sprites) {
        if(direction == Direction.RIGHT)
            x += speed;
        else if(direction == Direction.LEFT)
            x -= speed;
        else if(direction == Direction.UP)
            y -= speed;
        else if(direction == Direction.DOWN)
            y += speed;
        left = x;
        right = potSize + x;
        top = y;
        bottom = potSize + y;
        if(isColliding(link)){
            if(link.right > left && link.oldRight <= left)
                direction = Direction.RIGHT;
            else if(link.left < right && link.oldLeft >= right)
                direction = Direction.LEFT;
            else if(link.bottom > top && link.oldBottom <= top)
                direction = Direction.DOWN;
            else if(link.top < bottom && link.oldTop >= bottom)
                direction = Direction.UP;
        }
        for(Sprite s : sprites)
        {
            if(s instanceof Brick b && isColliding(b))
                breakPot();
            if(s instanceof Pot p && isColliding(p) && !p.equals(this))
            {
                p.direction = direction;
                direction = Direction.NONE;
            }
        }
        if(broken)
        {
            if(brokenFrames == 0)
                delete = true;
            else
                brokenFrames--;
        }

    }

    void breakPot()
    {
        direction = Direction.NONE;
        broken = true;
        currentImage = broken_pot_image;
    }

    @Override
    void draw(Graphics g) {
        g.drawImage(currentImage, x - View.scrollPosX, y - View.scrollPosY, null);
    }

    @Override
    Json marshall() {
        Json ob = Json.newObject();
        ob.add("X", x);
        ob.add("Y", y);
        return ob;
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

    public Pot(Json ob)
    {
        direction = Direction.NONE;
        x = (int)ob.getLong("X");
        y = (int)ob.getLong("Y");
        left = x;
        right = potSize + x;
        top = y;
        bottom = potSize + y;
        if(pot_image == null){
            try {
                pot_image = ImageIO.read(new File("pot.png"));
            } catch (Exception e) {
                e.printStackTrace(System.err);
                System.exit(1);
            }
        }
        if(broken_pot_image == null)
        {
            try {
                broken_pot_image = ImageIO.read(new File("pot_broken.png"));
            } catch (Exception e) {
                e.printStackTrace(System.err);
                System.exit(1);
            }
        }
        currentImage = pot_image;
    }

    public Pot(int xIn, int yIn)
    {
        direction = Direction.NONE;
        x = xIn;
        y = yIn;
        left = x;
        right = potSize + x;
        top = y;
        bottom = potSize + y;
        if(pot_image == null){
            try {
                pot_image = ImageIO.read(new File("pot.png"));
            } catch (Exception e) {
                e.printStackTrace(System.err);
                System.exit(1);
            }
        }
        if(broken_pot_image == null)
        {
            try {
                broken_pot_image = ImageIO.read(new File("pot_broken.png"));
            } catch (Exception e) {
                e.printStackTrace(System.err);
                System.exit(1);
            }
        }
        currentImage = pot_image;
    }

    @Override
    public String toString()
    {
        return "Left: " + left + ", Right: " + right + ", Top: " + top + ", Bottom: " + bottom;
    }


}
