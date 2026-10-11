package engine;

import affichage.*;

public class GameLoop implements Runnable {
    Renderer renderer;
    boolean running = true;

    public GameLoop(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void run() {
        while (running) {
            renderer.update();
            renderer.repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void stop() {
        running = false;
    }
}
