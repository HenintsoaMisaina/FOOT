package player;

import java.awt.Point;
import game.Ball;

public abstract class Player {
    String nom;
    Point position;
    Double puissance = 12.0;
    Double portee = 25.0;

    public Player() {
    }

    public Player(String name, int x, int y) {
        this.nom = name;
        this.position = new Point(x, y);
    }

    public Double getPuissance() {
        return puissance;
    }

    public void setPuissance(Double puissance) {
        this.puissance = puissance;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String name) {
        this.nom = name;
    }

    public Point getPosition() {
        return this.position;
    }

    public int getPositionX() {
        return this.position.x;
    }

    public int getPositionY() {
        return this.position.y;
    }

    public void setPosition(int x, int y) {
        this.position = new Point(x, y);
    }

    public void setPosition(Point p) {
        this.position = new Point(p);
    }

    public void setPositionY(int y) {
        this.position.y = y;
    }

    public void setPositionX(int x) {
        this.position.x = x;
    }

    public boolean contains(int x, int y) {
        int dim = 12;
        return (x >= (position.x - dim) && x <= (position.x + dim) && y >= (position.y - dim)
                && y <= (position.y + dim));

    }

    public void clamp(int minX, int maxX, int minY, int maxY) {
        if (position.x < minX)
            position.x = minX;
        if (position.x > maxX)
            position.x = maxX;
        if (position.y < minY)
            position.y = minY;
        if (position.y > maxY)
            position.y = maxY;
    }

    public boolean shoot(Ball ball) {

        if (ball.isMoving()) {
            return false;
        }

        double dx = ball.getX() - position.x;
        double dy = ball.getY() - position.y;
        double dist = Math.sqrt(dx * dx + dy * dy);
        if (dist > portee || dist == 0) {
            return false;
        }

        ball.setVelocite(dx / dist * puissance, dy / dist * puissance);
        return true;
    };

}
