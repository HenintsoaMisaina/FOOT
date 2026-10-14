package player;

import javax.swing.plaf.metal.MetalBorders.PaletteBorder;

import game.Ball;

public class Defender extends Player {
    public Defender(String name, int x, int y) {
        super(name, x, y);
        this.puissance = 20.0;
    }

    @Override
    public boolean shoot(Ball ball) {

        if (ball.isMoving()) {
            return false;
        }

        double dx = ball.getX() - position.x;
        double dy = ball.getY() - position.y;
        double dist = Math.sqrt(dx * dx + dy * dy);
        if (dist > portee || dist == 0) {
            return false;
        }

        ball.setVelocite(dx / dist * puissance, dy / dist * puissance);
        return true;
    };

    @Override 
    public boolean tacle(Player p){
        System.out.println(this.getNom() + " a taclé : "+p.getNom() );
        return true;
    }

}
