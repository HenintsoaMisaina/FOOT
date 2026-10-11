package affichage;

import game.Match;
import listener.*;
import javax.swing.JFrame;

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
        
        add(renderer);
        setVisible(true);
        GameLoop loop = new GameLoop(renderer);
        new Thread(loop).start();

        renderer.requestFocus();
    }
}