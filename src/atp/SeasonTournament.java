/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atp;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author ilicm
 */
public class SeasonTournament extends Tournament {
    
    private ArrayList<Player> roundOf16;
    private ArrayList<Player> quaterFinalists;
    private ArrayList<Player> semiFinalists;
    private ArrayList<Player> finalists;

    public SeasonTournament(String text, ArrayList<Player> contestants) {
        super(text, contestants);
        roundOf16 = contestants;
        quaterFinalists = new ArrayList<Player>();
        semiFinalists = new ArrayList<Player>();
        finalists = new ArrayList<Player>();
    }
    @Override
    public void play() {
        super.setPlayable(true);
        playRound("Round of 16", roundOf16, quaterFinalists, 180, 100);
        playRound("Quarterfinals", quaterFinalists, semiFinalists, 360, 200);
        playRound("Semifinals", semiFinalists, finalists, 720, 400);
        playFinal();
    }

    private void playRound(String roundName, ArrayList<Player> currentRound, ArrayList<Player> nextRound, int pointsGS, int pointsMaster) {
        System.out.println("========== " + roundName + " ============== \n");
        
        Collections.shuffle(currentRound);
        
        for (int i = 0; i < currentRound.size(); i += 2) {
            
            Match match = new Match(currentRound.get(i), currentRound.get(i + 1), super.tourSurface, super.numOfSets);
            Player winner = match.playMatch();
            nextRound.add(winner); // igrac prosao u sledecu rundu (dodat na sledecu listu)
            match.printMatchResult();
            
            if (winner == currentRound.get(i)) {
                currentRound.get(i + 1).setAtpPoints(currentRound.get(i + 1).getAtpPoints() + (super.tourType.equals("Grand Slam") ? pointsGS : pointsMaster));
            } else {
                currentRound.get(i).setAtpPoints(currentRound.get(i).getAtpPoints() + (super.tourType.equals("Grand Slam") ? pointsGS : pointsMaster));
            }
        }
    }

    private void playFinal() {
        System.out.println("========== Final ============== \n");
        
        Match match = new Match(finalists.get(0), finalists.get(1), super.tourSurface, super.numOfSets);
        Player winner = match.playMatch();
        match.printMatchResult();
        
        int pointsWinner = super.tourType.equals("Grand Slam") ? 2000 : 1000;
        int pointsLoser = super.tourType.equals("Grand Slam") ? 1200 : 650;
        
        if (winner == finalists.get(0)) {
            finalists.get(1).setAtpPoints(finalists.get(1).getAtpPoints() + pointsLoser);
            finalists.get(0).setAtpPoints(finalists.get(0).getAtpPoints() + pointsWinner);
        } else {
            finalists.get(0).setAtpPoints(finalists.get(0).getAtpPoints() + pointsLoser);
            finalists.get(1).setAtpPoints(finalists.get(1).getAtpPoints() + pointsWinner);
        }
    }
}
