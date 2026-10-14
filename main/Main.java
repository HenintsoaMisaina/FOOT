package main;

import java.util.List;

import affichage.Window;
import bdd.EquipeBDD;
import game.Ball;
import game.Match;
import game.Pitch;
import game.Team;

public class Main {

    public static void main(String[] args) {
        List<Team> equipes = EquipeBDD.findAll();

        Team teamA = equipes.get(0);
        Team teamB = equipes.get(1);

        Pitch pitch = new Pitch();
        Ball ball = new Ball(pitch.getCentreX(), pitch.getCentreY());

        Match match = new Match(teamA, teamB, pitch, ball);

        new Window(match);
    }
}