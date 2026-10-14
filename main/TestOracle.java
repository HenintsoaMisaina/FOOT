package main;

import java.sql.*;
import connexion.Connexion;

public class TestOracle {
    public static void main(String[] args) {
        try (Connection c = Connexion.connect();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM JOUEUR")) {
            rs.next();
            System.out.println("Connexion OK, joueurs en base : " + rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}