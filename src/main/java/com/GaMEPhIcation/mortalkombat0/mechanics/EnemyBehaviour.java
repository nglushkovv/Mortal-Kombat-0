/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GaMEPhIcation.mortalkombat0.mechanics;

import com.GaMEPhIcation.mortalkombat0.characters.Fighter;
import com.GaMEPhIcation.mortalkombat0.mechanics.Fight.Action;


/**
 *
 * @author nglushkovv
 */
public class EnemyBehaviour {
    private Action action = null;
    private Fighter enemy;
    
    public Action getMove(Fighter enemy) {
        this.enemy = enemy;
        
        switch(enemy.getType()) {
            case WIZARD -> getWizardBehaviour();
            case TANK -> getStandartBehaviour(0.8);
            case SOLDIER -> getStandartBehaviour(0.4);
            case WARRIOR -> getStandartBehaviour(0.5);
            
        }
        
        if(enemy.isBoss() && Math.random() <= 0.35 && enemy.getHealth() < enemy.getMaxHealth()) {
            action = Action.REGENERATE;
            
        }
        
        if(enemy.getState() == Action.STUNNED){
            action = Action.STUNNED;
            enemy.setState(Action.ATTACK);
        }
        
        return action;
        
    } 
    
    public void getWizardBehaviour(){
        double probability = Math.random();
        if (probability > 0.85) {
            action = Action.WEAKENING;
        }
        else {
            getStandartBehaviour(0.7);
        }
        
    }
    
    public void getStandartBehaviour(double probability) {
        if (enemy.getHealth() < enemy.getMaxHealth()/2)
            if (Math.random() > probability) action = Action.ATTACK;
            else{
                action = Action.DEFEND;
                
        }
        else {
            if (Math.random() < 0.65){
                action = Action.ATTACK;
            } else {
                action = Action.DEFEND;
            }
            
                
        }

    }
    
}
