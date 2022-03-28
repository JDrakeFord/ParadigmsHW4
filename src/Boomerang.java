import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Boomerang extends Sprite{
    int boomerangSize = 20;
    public static BufferedImage boomerang_image;
    double speed = 10.0;
    enum Direction{UP, DOWN, LEFT, RIGHT}
    Direction direction;

    public Boomerang(int xIn, int yIn, Direction d)
    {
        x = xIn;
        y = yIn;
        left = x;
        right = boomerangSize + x;
        top = y;
        bottom = boomerangSize + y;
        //Load image
        if(boomerang_image == null){
            try {
                boomerang_image = ImageIO.read(new File("boomerang1.png"));
            } catch (Exception e) {
                e.printStackTrace(System.err);
                System.exit(1);
            }
        }
        direction = d;
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
    void update(Link l, ArrayList<Sprite> sprites) {
        switch (direction) {
            case RIGHT: x += speed; break;
            case LEFT: x -= speed; break;
            case UP: y -= speed; break;
            case DOWN: y += speed; break;
        }
        left = x;
        right = boomerangSize + x;
        top = y;
        bottom = boomerangSize + y;
        for(Sprite s : sprites)
        {
            if(isColliding(s))
            {
                if(s instanceof Brick)
                    delete = true;
                else if(s instanceof Pot p) {
                    p.breakPot();
                    delete = true;
                }
            }
        }
    }

    @Override
    void draw(Graphics g) {
        g.drawImage(boomerang_image, x - View.scrollPosX, y - View.scrollPosY, null);
    }

    String enumToString(Direction d)
    {
        if(d == Direction.DOWN)
            return "DOWN";
        else if(d == Direction.UP)
            return "UP";
        else if(d == Direction.LEFT)
            return "LEFT";
        else if(d == Direction.RIGHT)
            return "RIGHT";
        return "ERROR";
    }

    @Override
    Json marshall() {
        return null;
    }

    @Override
    public String toString(){
        return "Boomerang: Direction: " + enumToString(direction);
    }
}
