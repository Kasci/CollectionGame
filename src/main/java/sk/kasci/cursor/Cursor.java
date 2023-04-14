package sk.kasci.cursor;

public class Cursor {

    private int x;
    private int y;

    public Cursor() {
        x = 0;
        y = 0;
    }

    public void move(int dx, int dy) {
        x = getX() + dx;
        y = getY() + dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
