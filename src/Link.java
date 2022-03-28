import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

public class Link extends Sprite {
    int height = 70;
    int width = 60;
    int absX = 85;
    int absY = 165;

    double speed = 8.0;
    int oldRight;
    int oldLeft;
    int oldBottom;
    int oldTop;
    static BufferedImage[] link_images;
    int lastImageIndex = 0;
    Controller controller;
    View view;


    public Link()
    {
        top = absY;
        bottom = absY + height;
        left = absX;
        right = absX + width;
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

    public Link(Json ob)
    {
        absX = (int)ob.getLong("absX");
        absY = (int)ob.getLong("absY");
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        top = (int)ob.getLong("top");
        bottom = (int)ob.getLong("bottom");
        left = (int)ob.getLong("left");
        right = (int)ob.getLong("right");
        link_images = new BufferedImage[50];
        for(int i = 0; i < 50; i++)
        {
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

    void setController(Controller c)
    {
        controller = c;
    }

    void setView(View v)
    {
        view = v;
    }

    @Override
    void update(Link l, ArrayList<Sprite> sprites)
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
            view.scrollPosX = Game.width;
        }
        else {
            x = absX;
            view.scrollPosX = 0;
        }
        if(absY > Game.height) {
            y = absY - Game.height;
            view.scrollPosY = Game.height;
        }
        else {
            y = absY;
            view.scrollPosY = 0;
        }
    }

    int count = 0;
    @Override
    void draw(Graphics g) {
        if(controller.DOWN) {
            if (count == 9)
                count = 0;
            if(!(controller.UP && controller.LEFT && controller.RIGHT)) {
                g.drawImage(Link.link_images[4 + count], x, y, null);
                lastImageIndex = 4 + count;
            }
            count++;
        }
        if(controller.LEFT) {
            if (count == 9)
                count = 0;
            if(!(controller.UP || controller.DOWN || controller.RIGHT)) {
                g.drawImage(Link.link_images[13 + count], x, y, null);
                lastImageIndex = 13 + count;
            }
            count++;
        }
        if(controller.RIGHT) {
            if (count == 9)
                count = 0;
            if(!(controller.UP || controller.LEFT || controller.DOWN)) {
                g.drawImage(Link.link_images[30 + count], x, y, null);
                lastImageIndex = 30 + count;
            }
            count++;
        }
        if(controller.UP) {
            if (count == 9)
                count = 0;
            if(!(controller.DOWN && controller.LEFT && controller.RIGHT)) {
                g.drawImage(Link.link_images[40 + count], x, y, null);
                lastImageIndex = 40 + count;
            }
            count++;
        }
        if(!(controller.UP || controller.DOWN || controller.LEFT || controller.RIGHT))
        {
            g.drawImage(Link.link_images[lastImageIndex], x, y, null);
        }
    }

    @Override
    Json marshall() {
        Json ob = Json.newObject();
        ob.add("absX", absX);
        ob.add("absY", absY);
        ob.add("x", x);
        ob.add("y", y);
        ob.add("top", top);
        ob.add("bottom", bottom);
        ob.add("left", left);
        ob.add("right", right);

        return ob;
    }

    @Override
    public String toString()
    {
        return "Link (x,y) = (" + absX + ", " + absY + ")";
    }


}
