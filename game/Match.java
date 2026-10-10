package game;

public class Match {
    Team teamA;
    Team teamB;
    Pitch pitch;

    public Match(Team equipe1, Team equipe2, Pitch terrain) {
        this.teamA = equipe1;
        this.teamB = equipe2;
        this.pitch = terrain;
    }

    public Team getTeamA() {
        return this.teamA;
    }

    public Team getTeamB() {
        return this.teamB;
    }

    public Pitch getPitch(){
        return this.pitch;
    }
}