package main;

import affichage.Window;
import game.Match;
import game.Pitch;
import game.Team;
import game.Ball;
import player.Attacker;
import player.Defender;
import player.Goalkeeper;
import player.Midfielder;
import player.Player;

public class Main {

    public static void main(String[] args) {

        Pitch pitch = new Pitch();
        Ball ball = new Ball(pitch.getCentreX(), pitch.getCentreY());
        
        // === FC BARCELONA ===

        Player[] equipeA = new Player[] {

                // Gardien
                new Goalkeeper("Ter Stegen", 150, 500),

                // Défenseurs
                new Defender("Kounde", 300, 200),
                new Defender("Araujo", 300, 400),
                new Defender("Cubarsi", 300, 600),
                new Defender("Balde", 300, 800),

                // Milieux
                new Midfielder("Pedri", 550, 300),
                new Midfielder("De Jong", 550, 500),
                new Midfielder("Gavi", 550, 700),

                // Attaquants
                new Attacker("Raphinha", 750, 300),
                new Attacker("Lewandowski", 750, 500),
                new Attacker("Lamine Yamal", 750, 700)
        };

        // === REAL MADRID ===

        Player[] equipeB = new Player[] {

                // Gardien
                new Goalkeeper("Courtois", 1450, 500),

                // Défenseurs
                new Defender("Carvajal", 1300, 200),
                new Defender("Rudiger", 1300, 400),
                new Defender("Militao", 1300, 600),
                new Defender("Mendy", 1300, 800),

                // Milieux
                new Midfielder("Valverde", 1050, 300),
                new Midfielder("Bellingham", 1050, 500),
                new Midfielder("Tchouameni", 1050, 700),

                // Attaquants
                new Attacker("Rodrygo", 850, 300),
                new Attacker("Mbappe", 850, 500),
                new Attacker("Vinicius Jr", 850, 700)
        };

        Team teamA = new Team("FC Barcelona", equipeA);
        Team teamB = new Team("Real Madrid", equipeB);

        Match match = new Match(teamA, teamB, pitch, ball);

        new Window(match);
    }
}