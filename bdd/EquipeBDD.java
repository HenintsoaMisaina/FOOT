package bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;
import game.Team;
import player.Player;

public class EquipeBDD {

    public static List<Team> findAll() {
        List<Team> equipes = new ArrayList<>();

        String sql = "SELECT id, nom FROM EQUIPE ORDER BY id";

        try (Connection c = Connexion.connect();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");

                Player[] joueurs = JoueurBDD.findByEquipe(id);

                Team team = new Team(nom, joueurs);
                equipes.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipes;
    }
}