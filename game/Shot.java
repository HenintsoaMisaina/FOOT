package game;

import java.awt.Point;

import javax.swing.text.html.HTMLDocument.BlockElement;

import player.*;

public class Shot {
    Player tireur;
    Point depart;
    Point arrivee;
    Boolean but;
    Boolean in_surface=true;
    int minute;

    public Shot(Player tireur, Point depart, int minute) {
        this.tireur = tireur;
        this.depart = depart;
        this.minute = minute;
    }

    public void terminer(Point arrivee, Boolean but, Boolean in_surface) {
        this.arrivee = arrivee;
        this.but = but;
    }

    public boolean getIn_surface(){
        return this.in_surface;
    }


    public boolean inSurface(Pitch terrain){

        int profondeur = 200;
        int largeurZone = 400;
        int centreY = terrain.getCentreY();
        int dx  = this.depart.x;
        int dy = this.depart.y;
        int xGauche = terrain.getMarge();
        int xDroit = terrain.getMarge() + terrain.getLongueur() - profondeur;
        
        if (dx>xGauche && dx<xGauche+profondeur && dy> (centreY - largeurZone / 2) && dy< (centreY - largeurZone / 2)+largeurZone  ){
            return true;
        }
        if (dx>xDroit && dx<xDroit+profondeur && dy> (centreY - largeurZone / 2) && dy< (centreY - largeurZone / 2)+largeurZone  ){
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "shoot terminer"; /*"Shot{" +
                "tireur= " + tireur.getNom() +
                "minute= " + minute +
                ", depart=(" + depart.x + "," + depart.y + ")" +
                ", arrivee=" + (arrivee != null ? "(" + arrivee.x + "," + arrivee.y + ")" : "null") +
                ", but=" + but +
                '}';*/
    }

    public Player getTireur() {
        return tireur;
    }

    public Point getDepart() {
        return depart;
    }

    public Point getArrivee() {
        return arrivee;
    }

    public Boolean getBut() {
        return but;
    }

    public int getMinute() {
        return minute;
    }
}
