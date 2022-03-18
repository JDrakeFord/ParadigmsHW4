import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Link {
    int height = 70;
    int width = 60;
    int x = 85;
    int y = 165;
    int absX = 85;
    int absY = 165;
    int top = absY;
    int bottom = absY + height;
    int left = absX;
    int right = absX + width;
    double speed = 8.0;
    public int oldRight;
    public int oldLeft;
    public int oldBottom;
    public int oldTop;
    public static BufferedImage[] link_images;

    public Link()
    {
        link_images = new BufferedImage[50];
        for(int i = 0; i < 50; i++)
        {
            if(link_images[i] == null) {
                try {
                    if (i < 9)
                        link_images[i] = ImageIO.read(new File("link_images/link" + "0" + (i + 1) + ".png"));
                    else
                        link_images[i] = ImageIO.read(new File("link_images/link" + (i + 1) + ".png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void update()
    {
        oldRight = right;
        oldLeft = left;
        oldTop = top;
        oldBottom = bottom;

        top = absY;
        bottom = absY + height;
        left = absX;
        right = absX + width;

        if(absX > Game.width) {
            x = absX - Game.width;
            View.scrollPosX = Game.width;
        }
        else {
            x = absX;
            View.scrollPosX = 0;
        }
        if(absY > Game.height) {
            y = absY - Game.height;
            View.scrollPosY = Game.height;
        }
        else {
            y = absY;
            View.scrollPosY = 0;
        }
    }

    @Override
    public String toString()
    {
        return "Link (x,y) = (" + absX + ", " + absY + ")";
    }


}
