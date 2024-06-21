/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GaMEPhIcation.mortalkombat0.characters;

/**
 *
 * @author Мария
 */
public class SonyaBlade extends Fighter{
    
    
    public SonyaBlade (int level, int health, int  damage, int attack, Boolean isPlayer, Type type){
        super (level, health, damage, attack, isPlayer, type);
    }
    
    @Override
    public String getName(){
        if (isPlayer()) return "Sonya Blade [Player]";
        return "Sonya Blade";
    }
    
    @Override
    public String getPathName(){
        return "sonya_blade";
    }
    
}
