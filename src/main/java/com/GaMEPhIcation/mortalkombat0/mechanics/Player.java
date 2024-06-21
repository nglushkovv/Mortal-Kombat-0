/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GaMEPhIcation.mortalkombat0.mechanics;

import com.GaMEPhIcation.mortalkombat0.characters.Fighter;

/**
 *
 * @author 79175
 */
public class Player {
    private String nickname;
    private final Fighter fighter;
    private int experience;
    private int points;
    private int boundary = 50;
    
    public Player(String nickname,
                  Fighter fighter,
                  int experience,
                  int points){
        this.nickname = nickname;
        this.fighter = fighter;
        this.experience = experience;
        this.points = points;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public Fighter getFighter() {
        return fighter;
    }
    
    public int getExperience() {
        return experience;
    }
    
    public int getPoints() {
        return points;
    }
    
    public void addExperience(int exp) {
        experience += exp;
        if (experience >= boundary) {
            boundary += experience;
            fighter.setLevel(fighter.getLevel() + 1);
            
        }
    }
    
    
    
    public void addPoints(int points) {
        this.points += points;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    
}
