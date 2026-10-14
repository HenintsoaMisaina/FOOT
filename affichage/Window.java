package affichage;

import game.Match;
import listener.*;
import javax.swing.*;
import java.awt.*;

import engine.GameLoop;

public class Window extends JFrame {

    public Window(Match match) {
        super("IT Football");
        setSize(1600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Renderer renderer = new Renderer(match);
        SelectionListener selection = new SelectionListener(match);
        renderer.addMouseListener(selection);

        ControlListener control = new ControlListener();
        renderer.setControlListener(control);
        renderer.addKeyListener(control);
        renderer.setFocusable(true);
        setLayout(new BorderLayout());
        add(renderer, BorderLayout.CENTER);

        JPanel historique = new JPanel();
        historique.setLayout(new BoxLayout(historique, BoxLayout.Y_AXIS));
        historique.setBackground(Color.WHITE);
        historique.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JScrollPane scrollPane = new JScrollPane(historique);
        scrollPane.setPreferredSize(new Dimension(200, 0)); 

        add(scrollPane, BorderLayout.EAST);

        setVisible(true);
        GameLoop loop = new GameLoop(renderer);
        new Thread(loop).start();

        renderer.requestFocus();
    }
}