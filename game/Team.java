package game;

import player.*;

public class Team {
    String teamName;
    Player[] players;
    int goals;
 
    public Team(){};

    public Team(String nom, Player[] pl){
        this.teamName = nom;
        this.players = pl;
        goals=0;
    }

    public String getTeamName(){
        return this.teamName;
    }

    public void setTeamName(String nom){
        this.teamName = nom;
    }

    public int getGoals(){
        return this.goals;
    }

    public void setGoals(int g){
        this.goals = g;
    }
}