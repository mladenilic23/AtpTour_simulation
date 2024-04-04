/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atp;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author ilicm
 */
public class Simulate {
    
    public static void main(String args[]) throws IOException {
        Championship ch = new Championship();
        ch.loadFiles();
        Scanner sc = new Scanner(System.in);
        int numOfTour = 0;
        String input;
        int num;
        
        while (true) {
            System.out.print("Enter number of tournament: ");
            input = sc.nextLine();
            try {
                numOfTour = Integer.parseInt(input);
                if (numOfTour < 4 || numOfTour > 13){ 
                    System.out.println("Number must be between 4 and 13.");
                }else {
                    break; 
                }
            } catch (NumberFormatException e) { 
                System.out.println("Invalid entry.");
            }
        }
        for(int i = 0; i < numOfTour; i++) {
            
            String tour = null;
            
            System.out.println("Chose " + numOfTour + " tournaments!\n");
            System.out.println("==========TOURNAMENTS==============:");
            System.out.println("1.Australian Open");
            System.out.println("2.Indian Wells Masters");
            System.out.println("3.Miami Open");
            System.out.println("4.Monte-Carlo Masters");
            System.out.println("5.Madrid Open");
            System.out.println("6.Italian Open");
            System.out.println("7.French Open");
            System.out.println("8.Wimbledon");            
            System.out.println("9.Canadian Open");
            System.out.println("10.Cincinnati Open");
            System.out.println("11.US Open");
            System.out.println("12.Shanghai Masters");
            System.out.println("13.Paris Masters");
            System.out.println("===================================\n");
            
            System.out.print("Choose serial number of tournament: ");
            num = sc.nextInt();
            
            switch(num){
            
                case 1:
                    tour = "Australian Open,hard,Grand Slam";
                break;
                case 2:
                    tour = "Indian Wells Masters,hard,Masters1000";
                break;
                case 3:
                    tour = "Miami Open,hard,Masters1000";
                break;
                case 4:
                    tour = "Monte-Carlo Masters,clay,Masters1000";
                break;
                case 5:
                    tour = "Madrid Open,clay,Masters1000";
                break;
                case 6:
                    tour = "Italian Open,clay,Masters1000";
                break;
                case 7:
                    tour = "French Open,clay,Grand Slam";
                break;
                case 8:
                    tour = "Wimbledon,grass,Grand Slam";
                break;
                case 9:
                    tour = "Canadian Open,hard,Masters1000";
                break;
                case 10:
                    tour = "Cincinnati Open,hard,Masters1000";
                break;
                case 11:
                    tour = "US Open,hard,Grand Slam";
                break;
                case 12:
                    tour = "Shanghai Masters,hard,Masters1000";
                break;
                case 13:
                    tour = "Paris Masters,hard,Masters1000";
                break;
                default:
                    System.out.println("Not an option!");
                break;
            }
            
            for(Tournament tournament : ch.getTournaments()) {
                if(tournament.getTourName().equals(tour.split(",")[0])) {
                    System.out.println("\n" + tour + "\n");
                    
                    if(tournament.isPlayable()) {
                        System.out.println("Already played.");
                        i--;
                        break;
                    } else {
                        tournament.setPlayable(true);
                        SeasonTournament st = new SeasonTournament(tour, ch.getPlayers());
                        st.play();
                        ch.updateAtpRanks();
                        ch.recoverPlayers();
                        break;
                    }          
                } 
            }
        }
    
        //ATP LIST
        ch.updateAtpRanks();
        
        System.out.println("=============ATP list================\n");           
        for(Player players : ch.getPlayers()) {
            System.out.println(players.getAtpRank() + ". " + players.getName() + " "  + players.getAtpPoints());
        }
        System.out.println("\n=====================================");
        
        System.out.println("\nWorld No.1 is "  + ch.getPlayers().get(0).getName() + " with " + ch.getPlayers().get(0).getAtpPoints() + " points.");
    
    }
}
