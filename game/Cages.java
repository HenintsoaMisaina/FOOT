package game;

import java.awt.Point;

public class Cages {
    Point poteau1;
    Point poteau2;

    public Cages(Point p1, Point p2) {
        this.poteau1 = new Point(p1);
        this.poteau2 = new Point(p2);
    }

    public Point getPoteau1() {
        return this.poteau1;
    }

    public Point getPoteau2() {
        return this.poteau2;
    }

    public int getX1() {
        return poteau1.x;
    }

    public int getY1() {
        return poteau1.y;
    }

    public int getX2() {
        return poteau2.x;
    }

    public int getY2() {
        return poteau2.y;
    }

}
