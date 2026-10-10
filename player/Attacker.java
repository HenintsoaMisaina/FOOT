package player;

public class Attacker extends Player{

    public Attacker(String name, int x, int y){
        super(name, x, y);
    }

    @Override 
    public void shoot(){
        System.out.println("je tire : "+this.getClass().getSimpleName()+" "+this.getNom());
    }

}
