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
