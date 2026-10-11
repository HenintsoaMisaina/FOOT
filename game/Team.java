package game;

import player.*;

public class Team {
    String nom;
    Player[] players;
    int goals;
 
    public Team(){};

    public Team(String nom, Player[] pl){
        this.nom = nom;
        this.players = pl;
        goals=0;
    }

    public Player[] getPlayers(){
        return this.players;
    }

    public String getNom(){
        return this.nom;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public int getGoals(){
        return this.goals;
    }

    public void setGoals(int g){
        this.goals = g;
    }

    public void incrementGoals(){
        goals++;
    }
}