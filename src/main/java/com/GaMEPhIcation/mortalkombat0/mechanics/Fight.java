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
public class Fight {
    private final Player player;
    private final Fighter enemy;
    
    public enum Move{
        PLAYER,
        ENEMY
    }
    
    public enum Action{
        ATTACK,
        DEFEND,
        INVENTORY,
        STUNNED,
        WEAKENING,
        REGENERATE
    }
    
    public Fight(Player player, Fighter enemy) {
        this.player = player;
        this.enemy = enemy;
        
    }
    
    
    public void attack(Fighter attacker, Fighter attacked, double coefficient) {
        attacked.setHealth(-attacker.getAttack() * coefficient);
        attacker.setState(Action.ATTACK);
    }
    
    public void setWeakening(Fighter weakener, Fighter weakened) {
            weakened.setDebuff(weakener.getLevel());
            weakener.setState(Action.ATTACK);
     
        
    }
    
    public void regenerate() {
        enemy.setHealth((enemy.getMaxHealth()-enemy.getHealth())/2);
    }
    
    public String useItem(int id) {
        Item[] bag = player.getFighter().getBag();
        player.getFighter().setHealth(bag[id].getPoints());
        bag[id].setCount(bag[id].getCount()-1);
        return " восстановил " + bag[id].getPoints() + " ";
        
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public Fighter getEnemy() {
        return enemy;
    }
    
    public String getRandomItem() {
        double itemProbability = Math.random();
        double gettingProbability = Math.random();
        
        if(enemy.isBoss()) gettingProbability -= 0.5;
        
        if (itemProbability <= 0.33 && gettingProbability < 0.25){
            player.getFighter().getBag()[0].setCount(player.getFighter().getBag()[0].getCount()+1);
            return player.getFighter().getName() + " выпадает " + player.getFighter().getBag()[0].getName();
        } else if (itemProbability > 0.33 && itemProbability <= 0.66 && gettingProbability < 0.15){
            player.getFighter().getBag()[1].setCount(player.getFighter().getBag()[1].getCount()+1);
            return player.getFighter().getName() + " выпадает " + player.getFighter().getBag()[1].getName();
        } else if (itemProbability > 0.66 && gettingProbability < 0.05){
            player.getFighter().getBag()[2].setCount(player.getFighter().getBag()[2].getCount()+1);
            return player.getFighter().getName() + " выпадает " + player.getFighter().getBag()[2].getName();
        }
        return "";
    }
    
    public void revivePlayer() {
        player.getFighter().setHealth(player.getFighter().getMaxHealth() * 0.05);
        player.getFighter().getBag()[2].setCount(player.getFighter().getBag()[2].getCount()-1);
    }
    
    
    
}
