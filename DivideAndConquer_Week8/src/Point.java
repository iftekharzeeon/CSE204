public class Point {
    private int x;
    private int y;
    private int id;

    public Point() {
        x = 0;
        y = 0;
        id = -1;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public int getId() {
        return id;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
