/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GaMEPhIcation.mortalkombat0.mechanics;

import com.GaMEPhIcation.mortalkombat0.characters.Fighter;
import com.GaMEPhIcation.mortalkombat0.characters.Fighter.Fighters;
import com.GaMEPhIcation.mortalkombat0.fabrics.FighterFabric;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author 79175
 */
public final class Location {
    private final int number;
    private final List<Fighter> levelEnemies = new ArrayList<>();
    private Fight fight;
    private int currLevel = 0;
    private final FighterFabric fighterFabric;
    
    
    public Location(int number, int level, Fighters[] enemies){
        this.number = number;
        this.fighterFabric = new FighterFabric();
        randomizeEnemies(level, enemies);
        
        
    };
    
    public void randomizeEnemies(int level, Fighters[] enemies){
        Random random = new Random();
        int numberOfEnemies = (int) ((Math.random() * level) + 2);
        
        for(int i=0; i < numberOfEnemies; i++) {
            Fighter enemy = fighterFabric.create(enemies[random.nextInt(0, enemies.length)],
                                                Boolean.FALSE);
            
            enemy.setAttack(enemy.getAttack() + (2 * number) + (i+1));
            enemy.setMaxHealth(enemy.getMaxHealth() + (2*number) + (i+1));
            enemy.setHealth(enemy.getMaxHealth());
            enemy.setLevel((int)(Math.random() + 1)*level);
            enemy.becomeStandart();
            levelEnemies.add(enemy);
            
        }
        
        levelEnemies.get(levelEnemies.size()-1).becomeBoss();
        
        
    }
    
    public void nextLevel(Player player) {
        if (currLevel != levelEnemies.size()) {
            fight = new Fight(player, levelEnemies.get(currLevel));
            
        }
        else{
            player.addPoints(25*number);
            player.addExperience(10*currLevel+(25*number));
            throw new RuntimeException();
            
        }
        
        currLevel += 1;
        
    }
    
    public Fight getFight() {
        return fight;
    }
    
    public void finishLevel(Player player) {
        player.addExperience(10*currLevel+(10*number));
        player.addPoints(10*number);
        
        
    }
    
    
    
    public List<Fighter> getLevelEnemies() {
        return levelEnemies;
    }
    
    public int getCurrentLevel() {
        return currLevel;
    }
    
    public Boolean isLastLevel() {
        return currLevel == levelEnemies.size();
    }
}
