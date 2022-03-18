import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Brick {
    int x;
    int y;
    int left;
    int right;
    int top;
    int bottom;
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
}
