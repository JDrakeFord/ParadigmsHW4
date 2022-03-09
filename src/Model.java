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
    ArrayList<Brick> bricks;
    Link link;

    Model(Link l)
    {
        bricks = new ArrayList<Brick>();
        link = l;
    }


    public void update(Link l)
    {
        link = l;
        System.out.println(l);
        Iterator<Brick> iter = bricks.iterator();
        while(iter.hasNext())
        {
            Brick b = iter.next();
            if(isColliding(link, b))
            {
                if(link.right > b.left && link.oldRight <= b.left) {
                    link.absX = link.oldLeft;
                    System.out.println(1);
                }
                else if(link.left < b.right && link.oldLeft >= b.right) {
                    link.absX = link.oldRight - link.width;
                    System.out.println(2);
                }
                else if(link.bottom > b.top && link.oldBottom <= b.top) {
                    link.absY = link.oldTop;
                    System.out.println(3);
                }
                else if(link.top < b.bottom && link.oldTop >= b.bottom) {
                    link.absY = link.oldBottom - link.height;
                    System.out.println(4);
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
        bricks = new ArrayList<Brick>();
        Json tmpList = ob.get("bricks");
        for(int i = 0; i < tmpList.size(); i++)
        {
            bricks.add(new Brick(tmpList.get(i)));
        }
    }

    public void addBrick(int x, int y)
    {
        bricks.add(new Brick(x, y));
    }

    public void removeBrick(int x, int y)
    {
        bricks.removeIf(b -> b.x == x && b.y == y);
    }

    public boolean isBrick(int x, int y)
    {
        for(Brick b : bricks)
        {
            if(b.x == x && b.y == y)
                return true;
        }
        return false;
    }

    public Json marshall()
    {
        Json ob = Json.newObject();
        Json tmpList = Json.newList();
        ob.add("bricks", tmpList);
        for(Brick b : bricks)
        {
            tmpList.add(b.marshall());
        }
        return ob;
    }



}