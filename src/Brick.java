public class Brick {
    int x;
    int y;
    public static int brickSize = 50;

    public Brick(int xIn, int yIn)
    {
        x = xIn;
        y = yIn;
    }

    public Brick(Json ob)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
    }

    public Json marshall()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }
}
