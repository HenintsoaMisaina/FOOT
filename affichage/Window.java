package affichage;

import game.Match;

import javax.swing.JFrame;

public class Window extends JFrame {

    public Window(Match match) {
        super("IT Football");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Renderer renderer = new Renderer(match);
        add(renderer);

        setVisible(true);
    }
}