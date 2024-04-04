/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author ilicm
 */
public class Championship {
    
    private ArrayList<Player> players;
    private ArrayList<Tournament> tournaments;

    public Championship() {
        players = new ArrayList<Player>();
        tournaments = new ArrayList<Tournament>();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    
    public ArrayList<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(ArrayList<Tournament> tournaments) {
        this.tournaments = tournaments;
    }
 
    public void updateAtpRanks() {
        Collections.sort(players, players.get(0));

        for(int pos = 1 ; pos <= players.size(); pos++) {
            players.get(pos-1).setAtpRank(pos);
        }
        
    }
    
    public void recoverPlayers() {
        for(Player player : players) {
            if(player.isInjured()) {
                player.setInjured(false);
            }
        }
    }
    
    public void loadFiles() throws FileNotFoundException, IOException {
        
        String sP = System.getProperty("file.separator");
        
	File files = new File("."+ sP +"src" + sP + "atp" + sP + "players.txt");
        
        String s;
        
	if(files.exists()){
            BufferedReader in = new BufferedReader(new FileReader(files));
            
            while((s = in.readLine()) != null) {
                players.add(new Player(s));
            }
        } else {
            System.out.println("File players.txt doesn't exists!");
            System.exit(0);
	}
        
        files = new File("."+ sP +"src" + sP + "atp" + sP + "tournaments.txt");
        
	if(files.exists()){
            BufferedReader in = new BufferedReader(new FileReader(files));
            
            while((s = in.readLine()) != null) {
                tournaments.add(new SeasonTournament(s, players));
            } 
        } else {
            System.out.println("File tournament.txt doesn't exists");
            System.exit(0);
        }
    }
    
}
