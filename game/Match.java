package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import player.*;

public class Match {
    Team teamA;
    Team teamB;
    Pitch pitch;
    Player selected;
    Ball ball;
    Shot tirEnCours;
    List<Shot> historique = new ArrayList<>();

    public Ball getBall() {
        return this.ball;
    }

    public Match(Team equipe1, Team equipe2, Pitch terrain, Ball ball) {
        this.teamA = equipe1;
        this.teamB = equipe2;
        this.pitch = terrain;
        this.ball = ball;
    }

    public void terminerTir(boolean but){
        tirEnCours.terminer(ball.getPosition(), but);
        historique.add(tirEnCours);
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

    public void updateBall() {
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
        for (Player p : teamA.getPlayers()) {
            if (p == joueur) {
                continue;
            }
            int px = p.getPositionX() - x;
            int py = p.getPositionY() - y;
            if (px * px + py * py < tour) {
                return false;
            }
        }

        for (Player p : teamB.getPlayers()) {
            if (p == joueur) {
                continue;
            }
            int px = p.getPositionX() - x;
            int py = p.getPositionY() - y;
            if (px * px + py * py < tour) {
                return false;
            }
        }

        int distMin = dim + ball.getRayon();
        int bx = ball.getPositionX() - x;
        int by = ball.getPositionY() - y;
        if (bx * bx + by * by < distMin * distMin) {
            return false;
        }

        return true;
    }

    public void shoot(Player p) {
        Point depart = ball.getPosition();
        if (p.shoot(ball)) {
            tirEnCours = new Shot(p, depart);
        }
    }
}