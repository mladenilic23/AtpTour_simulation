/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atp;

import java.util.Comparator;

/**
 *
 * @author ilicm
 */
public class Player implements Comparator<Player>{
    
    private String name;
    private String ability;
    private String preferedSurface;
    private int atpRank;
    private int atpPoints;
    private boolean injured;

    public Player(String text){
        String [] token = text.split(",");
        
        if(token.length!=5){
            System.out.println("Error while reading players!");
            System.exit(0);
        }

        this.atpRank = Integer.parseInt(token[0]);
        this.name = token[1];
        this.ability = token[2];
        this.preferedSurface = token[3];
        this.atpPoints = Integer.parseInt(token[4]);
    }
    
    public String getName() {
        return name;
    }     

    public void setName(String name) {
        this.name = name;
    }
    
    public String getAbility() {
        return ability;
    }
    
    public void setAbility(String ability) {
        this.ability = ability;
    }
    
    public String getPreferedSurface() {
        return preferedSurface;
    }

    public void setPreferedSurface(String preferedSurface) {
        this.preferedSurface = preferedSurface;
    }

    public int getAtpRank() {
        return atpRank;
    }
    
    public void setAtpRank(int atpRank) {
        this.atpRank = atpRank;
    }

    public int getAtpPoints() {
        return atpPoints;
    }
    
    public void setAtpPoints(int atpPoints) {
        this.atpPoints = atpPoints;
    }
    
    public boolean isInjured() {
        return injured;
    }

    public void setInjured(boolean injured) {
        this.injured = injured;
    }
    
    public int servePointChance(Player opponent, String surface) {
        int chance = 50;
        
        chance -= this.atpRank-opponent.atpRank;
        
        if(this.preferedSurface.equals(surface)) {
            chance += 5;
        } 
        if(this.ability.equals("forehand")) {
            chance += 10;
        }
        if(opponent.ability.equals("backhand")) {
            chance -= 8;
        }
        if(this.ability.equals("serve")) {
            chance += 15;
        }
        if(opponent.ability.equals("serve")) {
            chance += 5;
        }
        if(this.ability.equals("mentality")) {
            chance += 5;
        }
        if(opponent.ability.equals("mentality")) {
            chance -= 10;
        }
            
        return chance;
    }
    
    @Override
    public String toString() {
        String s = this.atpRank + "," + this.name + "," + this.ability + "," + this.preferedSurface + "," + this.atpPoints;
        return s;
    }
    
    @Override  
    public int compare(Player player1, Player player2) {
        return -Integer.compare(player1.getAtpPoints(), player2.getAtpPoints());
    } 
}
