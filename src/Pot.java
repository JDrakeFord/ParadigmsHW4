import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Pot extends Sprite{
    int left;
    int right;
    int top;
    int bottom;
    public static int potSize = 48;
    public static BufferedImage pot_image;

    @Override
    void update() {

    }

    @Override
    void draw(Graphics g) {
        g.drawImage(Pot.pot_image, x - View.scrollPosX, y - View.scrollPosY, null);
    }

    @Override
    Json marshall() {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return null;
    }

    public Pot(int xIn, int yIn)
    {
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
    }

}
