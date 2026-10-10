package main;

import player.*;
import affichage.*;
import game.Match;
import game.Pitch;
import game.Team;

class main {
    public static void main(String[] args) {
        
        Player j1 = new Attacker("Benzema", 0, 0) ;
        j1.shoot(); 
        
        
        Window test = new Window(new Match(new Team(), new Team(), new Pitch()));
    }
    
}