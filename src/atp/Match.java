/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atp;

import java.util.Random;

/**
 *
 * @author ilicm
 */
public class Match {
    
    private Player p1;
    private Player p2;
    private String matchSurface;
    private int winSetNum;
    
    private int p1Sets;
    private int p1Gems;
    private int p2Sets;
    private int p2Gems;
    private int p1ScorePerSet[];
    private int p2ScorePerSet[];
    private Random rng;
            
    public Match(Player p1, Player p2, String matchSurface, int winSetNum) {
        this.p1 = p1;
        this.p2 = p2;
        this.matchSurface = matchSurface;
        this.winSetNum = winSetNum;

        if(winSetNum == 3) {
            p1ScorePerSet = new int[5]; 
            p2ScorePerSet = new int[5]; 
        } else if (winSetNum == 2) {
            p1ScorePerSet = new int[3]; 
            p2ScorePerSet = new int[3]; 
        }
    }

    public Player playMatch() {
        p1Sets = 0;
        p2Sets = 0;   
        int counter = 0;
        Random randomNum = new Random();
       
        if(randomNum.nextInt(101) == 0) { //verovatnoca 1%
            //U slucaju povrede
            if(randomNum.nextInt(2) == 0) { //verovatnoca 50%
                p1.setInjured(true);
                System.out.println("Oh no! What a shame! " + p1.getName() + " got injured.\n ");
                return p2;
            } else {
                p2.setInjured(true);
                System.out.println("Oh no! What a shame! " + p2.getName() + " got injured.\n ");
                return p1;
            }
        } else {
            //Tok meca
            while(true) {
                playSet();
                p1ScorePerSet[counter] = p1Gems;
                p2ScorePerSet[counter] = p2Gems;
                counter++;
                
                //WINNER!!!!
                if(p1Sets == winSetNum) {
                    return p1;
                }
                else if(p2Sets == winSetNum) {
                    return p2;
                }
            }
        }
    }
        
    private void playSet() {
        p1Gems = 0;   
        p2Gems = 0;
        
        //Svi moguce kombinacije gemova
        while (true) {
            playGame();

            if ((p1Gems == 6 && p2Gems < 5) || (p1Gems == 7 && p2Gems == 5)) {
                p1Sets++; // p1 uzima set
                break;
            } else if ((p2Gems == 6 && p1Gems < 5) || (p2Gems == 7 && p1Gems == 5)) {
                p2Sets++; //p2 uzima set
                break;
            } else if (p1Gems == 6 && p2Gems == 6) {
                playTieBreak(); //idemo u tie break!!!!!
                break;
            }
        }
    }
    
    private void playGame() {
        int p1Points = 0;
        int p2Points = 0;
    
        while (p1Points < 5 && p2Points < 5) {
            boolean isP1Serving = (p1Gems + p2Gems) % 2 == 0; //ko ce prvi servirati???
            
            //ko ce imati vece sanse prilikom serviranja???
            if (isP1Serving ? chanceEvent(p1.servePointChance(p2, matchSurface)) : chanceEvent(p2.servePointChance(p1, matchSurface))) {
                p1Points++;
            } else {
                p2Points++;
            }

            //Sve moguce kombinacije poena
            if ((p1Points == 4 && p2Points < 3) || (p1Points < 3 && p2Points == 4)) {
                p1Gems += (p1Points == 4) ? 1 : 0;
                p2Gems += (p2Points == 4) ? 1 : 0;
                break;
            } else if (p1Points == 4 && p2Points == 4) {
                p1Points = 3;
                p2Points = 3;
            } else if (p1Points == 5 || p2Points == 5) {
                p1Gems += (p1Points == 5) ? 1 : 0;
                p2Gems += (p2Points == 5) ? 1 : 0;
            }
        }
    }
        
    private void playTieBreak() {
        int p1Points = 0;
        int p2Points = 0;
        
        while (true) {
            boolean isP1Serving = (p1Points + p2Points) % 2 == 0; //ko ce prvi servirati???
            
            //ko ce imati vece sanse prilikom serviranja???
            if (isP1Serving ? chanceEvent(p1.servePointChance(p2, matchSurface)) : chanceEvent(p2.servePointChance(p1, matchSurface))) {
                p1Points++;
            } else {
                p2Points++;
            }

            //Sve moguce kombinacije poena            
            if ((p1Points >= 7 && (p1Points - p2Points) >= 2) || (p2Points >= 7 && (p2Points - p1Points) >= 2)) {
                if (p1Points > p2Points) {
                    p1Gems++;
                    p1Sets++;
                } else {
                    p2Gems++;
                    p2Sets++;
                }
                break;
            }
        }
    }   
       
    private boolean chanceEvent(int probability) {
        Random rng = new Random();
        int chance = rng.nextInt(100+1);
        
        return chance <= probability;
    }
    
    public void printMatchResult() {
        printPlayerResult(p1, p1ScorePerSet, p1Sets, p2ScorePerSet);
        printPlayerResult(p2, p2ScorePerSet, p2Sets, p1ScorePerSet);
        
        System.out.println();
    }

    //dodata metoda zbog preglednosti
    private void printPlayerResult(Player player, int[] scorePerSet, int setsWon, int[] opponentScores) {
        System.out.printf("%-20s", player.getName());    
        
        for (int i = 0; i < scorePerSet.length; i++) {
            if (scorePerSet[i] != 0 || opponentScores[i] != 0) {
                System.out.printf("%d ", scorePerSet[i]);
            } 
        }
        System.out.println(" " + setsWon);
    }
}
