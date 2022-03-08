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

class Model
{
    ArrayList<Brick> bricks;

    Model()
    {
        bricks = new ArrayList<Brick>();
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