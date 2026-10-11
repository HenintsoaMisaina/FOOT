package game;

import java.awt.Point;

public class Pitch {
    int longueur = 1400;
    int largeur = 800;
    int marge = 100;
    int hauteurCage = 140;
    Cages gauche;
    Cages droit;

    public Pitch() {
        int centreY = marge + largeur / 2;

        this.gauche = new Cages(
                new Point(marge, centreY - hauteurCage / 2),
                new Point(marge, centreY + hauteurCage / 2));

        int xDroit = marge + longueur;
        this.droit = new Cages(
                new Point(xDroit, centreY - hauteurCage / 2),
                new Point(xDroit, centreY + hauteurCage / 2));
    }

    public int getCentreX() {
        return marge + longueur / 2;
    }

    public int getCentreY() {
        return marge + largeur / 2;
    }

    public int getHauteurCage() {
        return this.hauteurCage;
    }

    public Cages getGauche() {
        return gauche;
    }

    public Cages getDroit() {
        return droit;
    }

    public int getLongueur() {
        return longueur;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getMarge() {
        return marge;
    }
}
