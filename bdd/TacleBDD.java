package bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connexion.Connexion;

public class TacleBDD {

    public static void enregistrer(String nomJoueur, int minute) {
        String sql = "INSERT INTO TACLE (nom_joueur, minute_jeu) VALUES (?, ?)";

        try (Connection c = Connexion.connect();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nomJoueur);
            ps.setInt(2, minute);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}