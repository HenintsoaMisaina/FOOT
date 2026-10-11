package game;

import java.awt.Point;

public class Ball {

    double x;
    double y;
    double vx;
    double vy;
    int rayon = 8;

    static final double FROTTEMENT = 0.98;
    static final double SEUIL_ARRET = 0.1;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.vx = 0;
        this.vy = 0;
    }

    public void update() {
        x += vx;
        y += vy;

        vx *= FROTTEMENT;
        vy *= FROTTEMENT;

        if (Math.abs(vx) < SEUIL_ARRET)
            vx = 0;
        if (Math.abs(vy) < SEUIL_ARRET)
            vy = 0;
    }

    public void setVelocite(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public void stop() {
        vx = 0;
        vy = 0;
    }

    public boolean isMoving() {
        return vx != 0 || vy != 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Point getPosition() {
        return new Point(getPositionX(), getPositionY());
    }

    public int getPositionX() {
        return (int) Math.round(x);
    }

    public int getPositionY() {
        return (int) Math.round(y);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPositionX(int x) {
        this.x = x;
    }

    public void setPositionY(int y) {
        this.y = y;
    }

    public int getRayon() {
        return rayon;
    }

    public boolean contains(int px, int py) {
        double dx = px - x;
        double dy = py - y;
        return dx * dx + dy * dy <= rayon * rayon;
    }
}