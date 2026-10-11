package listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.*;
import player.Player;
import affichage.*;

public class SelectionListener implements MouseListener{
    Match match;

    public SelectionListener(Match game){
        this.match = game;
    }

    public void mouseClicked(MouseEvent e){
        int mx= e.getX();
        int my= e.getY();

        for( Player p : match.getTeamA().getPlayers()){
            if (p.contains(mx, my)){
                match.setSelected(p);
                System.out.println(p.getNom()+" "+p.getClass().getSimpleName()+" Séléctionné");
                return ;
            }
        }

        for( Player p : match.getTeamB().getPlayers()){
            if (p.contains(mx, my)){
                match.setSelected(p);
                System.out.println(p.getNom()+" "+p.getClass().getSimpleName()+" Séléctionné");
                return ;
            }
        }

    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    
}
