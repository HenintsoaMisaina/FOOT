package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import bdd.TacleBDD;
import bdd.TirBDD;
import player.*;

public class Match {
    Team teamA;
    Team teamB;
    Chrono chrono = new Chrono();
    Pitch pitch;
    Player selected;
    Ball ball;
    Shot tirEnCours;
    List<Shot> historique = new ArrayList<>();

    public Chrono getChrono() {
        return chrono;
    }

    public Ball getBall() {
        return this.ball;
    }

    public Match(Team equipe1, Team equipe2, Pitch terrain, Ball ball) {
        this.teamA = equipe1;
        this.teamB = equipe2;
        this.pitch = terrain;
        this.ball = ball;
        chrono.demarrer();
    }

    public void afficherHistorique() {
        for (int i = 0; i < historique.size(); i++) {
            Shot current = (Shot) historique.get(i);

            System.out.println("ALERT SHOOT HORS SURFACE : Shot{" +
                    " tireur= " + current.tireur.getNom() +
                    " minute= " + current.minute +
                    ", depart = (" + current.depart.x + "," + current.depart.y + ")" +
                    ", arrivee = "
                    + (current.arrivee != null ? "(" + current.arrivee.x + "," + current.arrivee.y + " ) " : " null ") +
                    ", but = " + current.but +
                    " } ");
        }
    }

    public void terminerTir(boolean but) {
        if (!tirEnCours.inSurface(pitch) && chrono.getMinute() < 45) {
            historique.add(tirEnCours);
            afficherHistorique();
            tirEnCours.in_surface = false;
        }
        tirEnCours.terminer(ball.getPosition(), but, tirEnCours.in_surface);
        TirBDD.enregistrer(tirEnCours);

        System.out.println(tirEnCours.toString());
        tirEnCours = null;
    }

    public Team getTeamA() {
        return this.teamA;
    }

    public Team getTeamB() {
        return this.teamB;
    }

    public Pitch getPitch() {
        return this.pitch;
    }

    public Player getSelected() {
        return this.selected;
    }

    public void setSelected(Player p) {
        this.selected = p;
    }

    private void remiseAuCentre() {
        ball.setPosition(pitch.getCentreX(), pitch.getCentreY());
        ball.stop();
    }

    public void updatePhysique() {
        ball.update();

        int r = ball.getRayon();
        int minX = pitch.getMarge() + r;
        int maxX = pitch.getMarge() + pitch.getLongueur() - r;
        int minY = pitch.getMarge() + r;
        int maxY = pitch.getMarge() + pitch.getLargeur() - r;

        Cages gauche = pitch.getGauche();
        Cages droit = pitch.getDroit();

        int yHaut = gauche.getY1();
        int yBas = gauche.getY2();
        Boolean dansCage = ball.getY() >= yHaut && ball.getY() <= yBas;

        if (ball.getX() < minX) {
            if (dansCage) {
                if (ball.getX() < pitch.getMarge()) {
                    teamB.incrementGoals();
                    terminerTir(true);
                    remiseAuCentre();
                    System.out.println("Score A : " + teamA.getGoals() + " - Score B : " + teamB.getGoals());
                    return;
                }
            } else {
                ball.setX(minX);
                // ball.setVx(0);
                ball.setVx(-ball.getVx());
            }
        } else if (ball.getX() > maxX) {
            if (dansCage) {
                if (ball.getX() > pitch.getMarge() + pitch.getLongueur()) {
                    teamA.incrementGoals();
                    terminerTir(true);
                    remiseAuCentre();
                    System.out.println("Score A : " + teamA.getGoals() + " - Score B : " + teamB.getGoals());
                    return;
                }
            } else {
                ball.setX(maxX);
                // ball.setVx(0);
                ball.setVx(-ball.getVx());
            }

        }

        if (ball.getY() < minY) {
            ball.setY(minY);
            // ball.setVy(0);
            ball.setVy(-ball.getVy());
        } else if (ball.getY() > maxY) {
            ball.setY(maxY);
            // ball.setVy(0);
            ball.setVy(-ball.getVy());
        }

        if (ball.isMoving()) {
            int bx = ball.getPositionX();
            int by = ball.getPositionY();
            int rayon = ball.getRayon();
            int tailleMin = rayon + 12;
            for (Player p : teamA.getPlayers()) {
                int dx = bx - p.getPositionX();
                int dy = by - p.getPositionY();
                if ((dx * dx + dy * dy) < tailleMin * tailleMin) {
                    arreterContre(p, tailleMin);
                    return;
                }
            }
            for (Player p : teamB.getPlayers()) {
                int dx = bx - p.getPositionX();
                int dy = by - p.getPositionY();
                if ((dx * dx + dy * dy) < tailleMin * tailleMin) {
                    arreterContre(p, tailleMin);
                    return;
                }
            }
        }
    }

    public void updateBall() {
        updatePhysique();
        if (tirEnCours != null && !ball.isMoving()) {
            terminerTir(false);
        }
    }

    private void arreterContre(Player p, int distMin) {
        double dx = ball.getX() - p.getPositionX();
        double dy = ball.getY() - p.getPositionY();
        double dist = Math.sqrt(dx * dx + dy * dy);
        if (dist > 0) {
            ball.setX(p.getPositionX() + dx / dist * distMin);
            ball.setY(p.getPositionY() + dy / dist * distMin);
        }
        ball.stop();
    }

    public boolean canMoveTo(Player joueur, int x, int y) {
        int dim = 12;
        int tour = (2 * dim) * (2 * dim);

        for (Team t : new Team[] { teamA, teamB }) {
            for (Player p : t.getPlayers()) {
                if (p == joueur)
                    continue;

                int nx = p.getPositionX() - x;
                int ny = p.getPositionY() - y;
                int apres = nx * nx + ny * ny;
                if (apres >= tour)
                    continue; 

                int ax = p.getPositionX() - joueur.getPositionX();
                int ay = p.getPositionY() - joueur.getPositionY();
                int avant = ax * ax + ay * ay;
                if (apres < avant)
                    return false; 
            }
        }

        int distMin = dim + ball.getRayon();
        int bx = ball.getPositionX() - x;
        int by = ball.getPositionY() - y;
        int apresBalle = bx * bx + by * by;
        if (apresBalle < distMin * distMin) {
            int cx = ball.getPositionX() - joueur.getPositionX();
            int cy = ball.getPositionY() - joueur.getPositionY();
            if (apresBalle < cx * cx + cy * cy)
                return false;
        }
        return true;
    }

    public void shoot(Player p) {
        if (chrono.isTermine())
            return;
        Point depart = ball.getPosition();
        if (p.shoot(ball)) {
            tirEnCours = new Shot(p, depart, chrono.getMinute());
        }
    }

    static final int PORTEE_TACLE = 32;

    public Player joueurAuContact(Player p) {
        Player plusProche = null;
        int meilleur = 5000;

        for (Team t : new Team[] { teamA, teamB }) {
            for (Player autre : t.getPlayers()) {
                if (autre == p) {
                    continue;
                }
                int dx = autre.getPositionX() - p.getPositionX();
                int dy = autre.getPositionY() - p.getPositionY();
                int d2 = dx * dx + dy * dy;
                if (d2 <= PORTEE_TACLE * PORTEE_TACLE && d2 < meilleur) {
                    meilleur = d2;
                    plusProche = autre;
                }
            }
        }
        return plusProche;
    }

    public void tacle(Player p) {
        if (chrono.isTermine())
            return;
        Player cible = joueurAuContact(p);
        if (cible != null && p.tacle(cible)) {
            TacleBDD.enregistrer(p.getNom(), chrono.getMinute());
        }
    }
}