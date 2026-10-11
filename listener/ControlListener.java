package listener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ControlListener extends KeyAdapter implements FocusListener {
    volatile boolean up;
    volatile boolean down;
    volatile boolean left;
    volatile boolean right;
    volatile boolean shoot;

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (KeyEvent.VK_UP == code) {
            up = true;
            // System.out.println("up");
        } else if (KeyEvent.VK_DOWN == code) {
            down = true;
            // System.out.println("down");
        } else if (KeyEvent.VK_LEFT == code) {
            left = true;
            // System.out.println("left");
        } else if (KeyEvent.VK_RIGHT == code) {
            right = true;
            // System.out.println("right");
        } else if (KeyEvent.VK_SPACE == code){
            shoot = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (KeyEvent.VK_UP == code) {
            up = false;
        } else if (KeyEvent.VK_DOWN == code) {
            down = false;
        } else if (KeyEvent.VK_LEFT == code) {
            left = false;
        } else if (KeyEvent.VK_RIGHT == code) {
            right = false;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean consumeShoot(){
        boolean valeur = shoot;
        shoot = false;
        return valeur;
    }
}
