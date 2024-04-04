/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atp;

import java.util.ArrayList;

/**
 *
 * @author ilicm
 */
public abstract class Tournament {
    protected String tourName;
    protected String tourType;
    protected String tourSurface;
    protected boolean playable;
    protected int numOfSets;
    protected ArrayList<Player> contestants;

    public Tournament (String text, ArrayList<Player> contestants){
        String [] token = text.split(",");
        
        if(token.length != 3){
            System.out.println("Error while reading tournament!");
            System.exit(0);
        }  
        this.tourName = token[0];
        this.tourSurface = token[1];
        this.tourType = token[2];
        this.playable = false;
        this.contestants = contestants;
        
        if(this.tourType.equals("Grand Slam")) {
            numOfSets = 3;
        } else { 
            numOfSets = 2;
        }
    }
    
    @Override
    public String toString(){
        String s = this.tourName + "," + this.tourType + "," + this.tourSurface ;
        return s;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }
    
    public String getTourType() {
        return tourType;
    }
    
    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    public String getTourSurface() {
        return tourSurface;
    }
    
    public void setTourSurface(String tourSurface) {
        this.tourSurface = tourSurface;
    }

    public boolean isPlayable() {
        return playable;
    }
    
    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public int getNumOfSets() {
        return numOfSets;
    }
    
    public void setNumOfSets(int numOfSets) {
        this.numOfSets = numOfSets;
    }

    public ArrayList<Player> getContestants() {
        return contestants;
    }
    
    public void setContestants(ArrayList<Player> contestants) {
        this.contestants = contestants;
    }

    abstract public void play();
}
