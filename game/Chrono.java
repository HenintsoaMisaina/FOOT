package game;

import javax.swing.Timer;

public class Chrono {
    static final int DUREE_JEU_SEC = 2 * 60; 

    volatile int secondes;     
    volatile boolean termine;
    Timer timer;

    public Chrono() {
        timer = new Timer(1000, e -> {
            secondes++;
            if (secondes >= DUREE_JEU_SEC) {
                secondes = DUREE_JEU_SEC;
                termine = true;
                timer.stop();
            }
        });
    }

    public void demarrer() {
        timer.start();
    }

    public int getMinute() {
        return secondes / 60;
    }

    public String getAffichage() {
        return String.format("%02d:%02d", secondes / 60, secondes % 60);
    }

    public boolean isTermine() {
        return termine;
    }
}