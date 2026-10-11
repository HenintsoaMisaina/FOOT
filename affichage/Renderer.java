package affichage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import game.*;
import listener.ControlListener;
import player.Player;

public class Renderer extends JPanel {
    Match match;
    ControlListener control;

    Color VERT_CLAIR = new Color(46, 150, 52);
    Color VERT_FONCE = new Color(38, 134, 44);

    public Renderer(Match game) {
        this.match = game;
    }

    public void setControlListener(ControlListener control) {
        this.control = control;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.gray);
        g.fillRect(0, 0, getWidth(), getHeight());

        Graphics2D g2d = (Graphics2D) g;
        drawPitch(g2d);
        drawPenaltyArea(g2d);
        drawCenter(g2d);
        drawCages(g2d);
        drawPlayers(g2d);
        drawBall(g2d);
        drawScore(g2d);
    }

    private void drawScore(Graphics2D g2d) {
        Team a = match.getTeamA();
        Team b = match.getTeamB();

        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.WHITE);

        String score = a.getNom() + "  " + a.getGoals() + " - " + b.getGoals() + "  " + b.getNom();

        FontMetrics fm = g2d.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(score)) / 2;

        g2d.drawString(score, x, 40);
    }

    private void drawPitch(Graphics2D g2d) {
        Pitch terrain = match.getPitch();

        int nbBande = 14;
        int largeurBande = terrain.getLongueur() / nbBande;

        // g2d.setColor(VERT_CLAIR);
        // g2d.fillRect(terrain.getMarge() , terrain.getMarge() ,
        // terrain.getLongueur() ,
        // terrain.getLargeur() );

        for (int i = 0; i < nbBande; i++) {
            g2d.setColor(i % 2 == 0 ? VERT_CLAIR : VERT_FONCE);
            g2d.fillRect(terrain.getMarge() + i * largeurBande, terrain.getMarge(),
                    largeurBande,
                    terrain.getLargeur());
        }

        g2d.setColor(Color.white);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(terrain.getMarge(), terrain.getMarge(), terrain.getLongueur(), terrain.getLargeur());
    }

    private void drawCages(Graphics2D g2d) {
        Pitch terrain = match.getPitch();
        Cages cageGauche = terrain.getGauche();
        Cages cageDroit = terrain.getDroit();
        int hauteur = terrain.getHauteurCage();

        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2));

        g2d.drawRect(cageGauche.getX1() - 20, cageGauche.getY1(), 20, hauteur);
        g2d.drawRect(cageDroit.getX1() + 1, cageDroit.getY1(), 20, hauteur);

    }

    private void drawPenaltyArea(Graphics2D g2d) {
        Pitch terrain = match.getPitch();
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2));

        int profondeur = 200;
        int largeurZone = 400;
        int centreY = terrain.getCentreY();

        int xGauche = terrain.getMarge();
        g2d.drawRect(
                xGauche,
                centreY - largeurZone / 2,
                profondeur,
                largeurZone);

        int xDroit = terrain.getMarge() + terrain.getLongueur() - profondeur;
        g2d.drawRect(
                xDroit,
                centreY - largeurZone / 2,
                profondeur,
                largeurZone);
    }

    private void drawCenter(Graphics2D g2d) {
        Pitch terrain = match.getPitch();
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2));
        int cx = terrain.getCentreX();
        int cy = terrain.getCentreY();
        int rayon = 80;
        g2d.setColor(Color.WHITE);
        g2d.drawOval(cx - rayon, cy - rayon, rayon * 2, rayon * 2);
        g2d.drawLine(cx, terrain.getMarge(), cx, terrain.getMarge() + terrain.getLargeur());
    }

    private void drawPlayers(Graphics2D g2d) {
        int dim = 12;
        int dim2 = 24;

        for (Player p : match.getTeamA().getPlayers()) {
            g2d.setColor(Color.red);
            g2d.fillOval(
                    p.getPosition().x - dim, p.getPosition().y - dim, dim2, dim2);

            if (p == match.getSelected()) {
                g2d.setColor(Color.CYAN);
            } else {
                g2d.setColor(Color.BLACK);
            }
            g2d.setStroke(new BasicStroke(3));
            g2d.drawOval(p.getPosition().x - dim, p.getPosition().y - dim, dim2, dim2);
            g2d.drawString(p.getNom(), p.getPosition().x - dim2, p.getPosition().y + dim2);
        }

        for (Player p : match.getTeamB().getPlayers()) {
            g2d.setColor(Color.blue);
            g2d.fillOval(
                    p.getPosition().x - dim, p.getPosition().y - dim, dim2, dim2);

            if (p == match.getSelected()) {
                g2d.setColor(Color.CYAN);
            } else {
                g2d.setColor(Color.BLACK);
            }
            g2d.setStroke(new BasicStroke(3));
            g2d.drawOval(p.getPosition().x - dim, p.getPosition().y - dim, dim2, dim2);
            g2d.drawString(p.getNom(), p.getPosition().x - dim2, p.getPosition().y + dim2);
        }
    }

    public void drawBall(Graphics2D g2d) {
        Ball ball = match.getBall();
        int r = ball.getRayon();
        int x = ball.getPositionX() - r;
        int y = ball.getPositionY() - r;

        g2d.setColor(Color.white);
        g2d.fillOval(x, y, r * 2, r * 2);

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawOval(x, y, r * 2, r * 2);
    }

    public void update() {
        Player p = match.getSelected();
        match.updateBall();
        if (p == null) {
            return;
        }

        if (control.consumeShoot()) {
            match.shoot(p);
        }

        int vitesse = 4;
        int newX = p.getPositionX();
        int newY = p.getPositionY();

        if (control.isUp()) {
            newY -= vitesse;
        }
        if (control.isDown()) {
            newY += vitesse;
        }
        if (control.isLeft()) {
            newX -= vitesse;
        }
        if (control.isRight()) {
            newX += vitesse;
        }

        Pitch terrain = match.getPitch();
        int rayon = 12;
        if (newX < terrain.getMarge() + rayon) {
            newX = terrain.getMarge() + rayon;
        }
        if (newX > terrain.getMarge() + terrain.getLongueur() - rayon) {
            newX = terrain.getMarge() + terrain.getLongueur() - rayon;
        }
        if (newY < terrain.getMarge() + rayon) {
            newY = terrain.getMarge() + rayon;
        }
        if (newY > terrain.getMarge() + terrain.getLargeur() - rayon) {
            newY = terrain.getMarge() + terrain.getLargeur() - rayon;
        }

        if (match.canMoveTo(p, newX, p.getPositionY())) {
            p.setPositionX(newX);
        }
        if (match.canMoveTo(p, p.getPositionX(), newY)) {
            p.setPositionY(newY);
        }

    }
}
