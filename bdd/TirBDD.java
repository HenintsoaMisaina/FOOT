package bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connexion.Connexion;
import game.Shot;

public class TirBDD {

    public static void enregistrer(Shot shot) {
        int idJoueur = JoueurBDD.findIdByNom(shot.getTireur().getNom());

        if (idJoueur == -1) {
            System.out.println("Joueur introuvable en BDD : " + shot.getTireur().getNom());
            return;
        }

        String sql = "INSERT INTO TIR (id, id_joueur, nom_joueur, minute_jeu, " +
                "x_depart, y_depart, x_arrivee, y_arrivee, but, in_surface) " +
                "VALUES (SEQ_TIR.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ? ,?)";

        try (Connection c = Connexion.connect();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, idJoueur);
            ps.setString(2, shot.getTireur().getNom());
            ps.setInt(3, shot.getMinute());
            ps.setInt(4, shot.getDepart().x);
            ps.setInt(5, shot.getDepart().y);
            ps.setInt(6, shot.getArrivee().x);
            ps.setInt(7, shot.getArrivee().y);
            ps.setInt(8, shot.getBut() ? 1 : 0);
            ps.setInt(9, shot.getIn_surface() ? 1 : 0);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void get_tir_hors_surface() {
        String sql = "SELECT tireur, minute FROM tir WHERE in_surface = 0";
        try (Connection c = Connexion.connect();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String tireur = rs.getString("tireur");
                int minute = rs.getInt("minute");
                System.out.println("Tireur : " + tireur + " | Minute : " + minute + "'");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}