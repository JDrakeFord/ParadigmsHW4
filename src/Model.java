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
    boolean fixed = false;

    Model(Link l)
    {
        sprites = new ArrayList<Sprite>();
        link = l;
        sprites.add(link);
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
            s.update(link, sprites);
            if(s.delete)
                iter.remove();
        }
        System.out.println(link);
        link.update(null, sprites);
    }

    public Model(Json ob)
    {
        sprites = new ArrayList<Sprite>();
        Json tmpList = ob.get("sprites");
        for(int i = 0; i < tmpList.size(); i++)
        {
            if(tmpList.get(i).toString().charAt(2) == 'x')
                sprites.add(new Brick(tmpList.get(i)));
            else if(tmpList.get(i).toString().charAt(2) == 'a')
                sprites.add(new Link(tmpList.get(i)));
            else if(tmpList.get(i).toString().charAt(2) == 'X')
                sprites.add(new Pot(tmpList.get(i)));
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