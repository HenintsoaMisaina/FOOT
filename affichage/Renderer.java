package affichage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import game.*;

public class Renderer extends JPanel {
    Match match;

    public Renderer(Match game) {
        this.match = game;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Pitch terrain = match.getPitch();
        Cages cageDroit = terrain.getDroit();
        Cages cageGauche = terrain.getGauche();

        g.setColor(Color.gray);
        g.fillRect(0, 0, getWidth(), getHeight());

        Graphics2D g2d = (Graphics2D) g;
        drawPitch(g2d);
        drawCages(g2d);
    }

    public void drawPitch(Graphics2D g2d) {
        Pitch terrain = match.getPitch();
        g2d.setColor(Color.white);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(terrain.getMarge(), terrain.getMarge(), terrain.getLongueur(), terrain.getLargeur());
        g2d.setColor(new Color(34, 139, 34));
        g2d.fillRect(terrain.getMarge() + 1, terrain.getMarge() + 1, terrain.getLongueur() - 1,
                terrain.getLargeur() - 1);
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
}
