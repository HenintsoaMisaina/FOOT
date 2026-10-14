package player;

import game.Ball;

public class Attacker extends Player {

    public Attacker(String name, int x, int y) {
        super(name, x, y);
        this.puissance = 29.0;
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
