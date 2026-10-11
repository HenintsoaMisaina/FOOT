package game;

import java.awt.Point;

import player.*;

public class Shot {
    Player tireur;
    Point depart;
    Point arrivee;
    Boolean but;


    public Shot(Player tireur, Point depart) {
        this.tireur = tireur;
        this.depart = depart;
    }

    public void terminer(Point arrivee, Boolean but) {
        this.arrivee = arrivee;
        this.but = but;
    }

    @Override
    public String toString() {
        return "Shot{" +
                "tireur=" + tireur.getNom() +
                ", depart=(" + depart.x + "," + depart.y + ")" +
                ", arrivee=" + (arrivee != null ? "(" + arrivee.x + "," + arrivee.y + ")" : "null") +
                ", but=" + but +
                '}';
    }
}
