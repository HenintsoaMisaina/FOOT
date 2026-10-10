package player;

import java.awt.Point;

public abstract class Player{
    String nom  ; 
    Point position;

    public Player(String name, int x, int y){   
        this.nom = name;
        this.position = new Point(x, y);
    }

    public String getNom(){
        return this.nom;
    }
    public void setNom(String name){
        this.nom = name;
    }

    public Point getPosition(){
        return this.position;
    }

    public void setPosition(int x, int y){
        this.position = new Point(x, y);
    }

    public void setPosition(Point p){
        this.position = new Point(p);
    }

    public abstract void shoot();

}
