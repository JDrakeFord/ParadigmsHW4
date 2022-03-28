/*
	Author: Jesse Ford
	Student ID: 010893900
	Date - 2/9/2022
	Assignment: Turtle attack!
	Description:
		Move a turtle model with arrow keys / mouse around a cyan background.
		There is also a button that removes itself upon pressing it.
 */

import java.util.ArrayList;
import java.util.Iterator;

class Model
{
    ArrayList<Sprite> sprites;
    Link link;
    View view;

    Model(Link l)
    {
        sprites = new ArrayList<Sprite>();
        link = l;
    }

    void setView(View v)
    {
        view = v;
    }

    void setLink(Link l)
    {
        link = l;
    }


    public void update(Link l)
    {
        link = l;
        Iterator<Sprite> iter = sprites.iterator();
        while(iter.hasNext())
        {
            Sprite s = iter.next();
            if(s instanceof Brick b) {
                if (isColliding(link, b)) {
                    if (link.right > b.left && link.oldRight <= b.left) {
                        link.absX = link.oldLeft;
                    } else if (link.left < b.right && link.oldLeft >= b.right) {
                        link.absX = link.oldRight - link.width;
                    } else if (link.bottom > b.top && link.oldBottom <= b.top) {
                        link.absY = link.oldTop;
                    } else if (link.top < b.bottom && link.oldTop >= b.bottom) {
                        link.absY = link.oldBottom - link.height;
                    }
                }
            }
        }
        link.update();
    }

    boolean isColliding(Link l, Brick b)
    {
        if(l.right < b.left) {
            return false;
        }
        if(l.left > b.right) {
            return false;
        }
        if(l.bottom < b.top) {
            return false;
        }
        if(l.top > b.bottom) {
            return false;
        }
        return true;
    }

    public Model(Json ob)
    {
        sprites = new ArrayList<Sprite>();
        Json tmpList = ob.get("sprites");
        for(int i = 0; i < tmpList.size(); i++)
        {
            if(tmpList.get(i).toString().charAt(2) == 'x')
                sprites.add(new Brick(tmpList.get(i)));
            else if(tmpList.get(i).toString().charAt(2) == 'a') {
                sprites.add(new Link(tmpList.get(i)));
            }
        }
    }

    public void addBrick(int x, int y)
    {
        sprites.add(new Brick(x, y));
    }

    public void removeBrick(int x, int y)
    {
        sprites.removeIf(b -> b.x == x && b.y == y);
    }

    public boolean isBrick(int x, int y)
    {
        for(Sprite s : sprites)
        {
            if(s instanceof Brick b && b.x == x && b.y == y)
                return true;
        }
        return false;
    }

    public Json marshall()
    {
        Json ob = Json.newObject();
        Json tmpList = Json.newList();
        ob.add("sprites", tmpList);
        for(Sprite s : sprites)
        {
            tmpList.add(s.marshall());
        }
        return ob;
    }


    public void addPot(int x, int y) {
        sprites.add(new Pot(x, y));
    }
}