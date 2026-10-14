package bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;
import player.*;

public class JoueurBDD {

    public static Player[] findByEquipe(int idEquipe) {
        List<Player> joueurs = new ArrayList<>();

        String sql = "SELECT nom, poste, x_initial, y_initial " +
                "FROM JOUEUR WHERE id_equipe = ? ORDER BY id";

        try (Connection c = Connexion.connect();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, idEquipe);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String nom = rs.getString("nom");
                    String poste = rs.getString("poste");
                    int x = rs.getInt("x_initial");
                    int y = rs.getInt("y_initial");

                    Player p = null;
                    if (poste.equals("GARDIEN")) {
                        p = new Goalkeeper(nom, x, y);
                    } else if (poste.equals("DEFENSEUR")) {
                        p = new Defender(nom, x, y);
                    } else if (poste.equals("MILIEU")) {
                        p = new Midfielder(nom, x, y);
                    } else if (poste.equals("ATTAQUANT")) {
                        p = new Attacker(nom, x, y);
                    }

                    if (p != null) {
                        joueurs.add(p);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return joueurs.toArray(new Player[0]);
    }

    public static int findIdByNom(String nom) {
        String sql = "SELECT id FROM JOUEUR WHERE nom = ?";

        try (Connection c = Connexion.connect();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nom);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}