/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GaMEPhIcation.mortalkombat0.mechanics;

import com.GaMEPhIcation.mortalkombat0.characters.Fighter;
import com.GaMEPhIcation.mortalkombat0.mechanics.Fight.Action;
import static com.GaMEPhIcation.mortalkombat0.mechanics.Fight.Action.ATTACK;
import static com.GaMEPhIcation.mortalkombat0.mechanics.Fight.Action.DEFEND;
import static com.GaMEPhIcation.mortalkombat0.mechanics.Fight.Action.INVENTORY;
import static com.GaMEPhIcation.mortalkombat0.mechanics.Fight.Action.REGENERATE;
import static com.GaMEPhIcation.mortalkombat0.mechanics.Fight.Action.STUNNED;
import static com.GaMEPhIcation.mortalkombat0.mechanics.Fight.Action.WEAKENING;

/**
 *
 * @author 79175
 */
public class MoveManager {
    private final EnemyBehaviour enemyBehaviour;
    private int currentMove;
    private Fighter playerFighter;
    private Fighter enemy;
    private Fight fight;
    private Fighter firstMover;
    private Fighter secondMover;
    private Action enemysAction;
    private int healItemid;
    
    
    public MoveManager() {
        enemyBehaviour = new EnemyBehaviour();
    }
    
    public void prepareFight(Fight fight) {
        this.fight = fight;
        this.enemy = fight.getEnemy();
        this.playerFighter = fight.getPlayer().getFighter();
        playerFighter.setHealth(playerFighter.getMaxHealth());
        
        currentMove = 1;
    }
    
    
    public String manageMoves(Action playersAction) {
        Action firstAction;
        Action secondAction;
        enemysAction = enemyBehaviour.getMove(enemy);
        
        
        
        if(currentMove % 2 != 0) {
            firstMover = playerFighter;
            secondMover = enemy;
            firstAction = playersAction;
            secondAction = enemysAction;
        } else {
            firstMover = enemy;
            secondMover = playerFighter;
            firstAction = enemysAction;
            secondAction = playersAction;
        }
        
        String roundInfo = distributeMoves(firstAction, secondAction);
        currentMove++;
        firstMover.decreaseDebuffTimer();
        secondMover.decreaseDebuffTimer();
        
        return roundInfo;
        
        
    }
    
    private String distributeMoves(Action firstAction, Action secondAction) {
        String information = new String();
        switch(firstAction) {
                case ATTACK -> information = getAttackMoveInformation(secondAction);
                    
                case DEFEND -> information = getDefendMoveInformation(secondAction); 
                    
                case WEAKENING -> information = getWeakeningMoveInformation(secondAction);
                    
                case REGENERATE -> information = getRegenerateMoveInformation(secondAction);  
                    
                case STUNNED -> information = getStunnedMoveInformation(secondAction);
                    
                case INVENTORY -> information = getInventoryMoveInformation(secondAction);
                    


                    
        }
      return information;          
    }
    
   
    private String getAttackMoveInformation(Action secondAction) {
        String information;
        switch (secondAction) {
            case ATTACK, INVENTORY, STUNNED -> {
                fight.attack(firstMover, secondMover, 1);
                information = firstMover.getName() + " наносит урон в размере "
                        + String.valueOf(secondMover.getDamage());
                
                if (secondAction == Action.INVENTORY) fight.useItem(healItemid);
            }
            case WEAKENING -> {
                fight.attack(firstMover, secondMover, 1.15); 
                information =firstMover.getName() +
                        " сбивает попытку ослабления и наносит урон в размере " +
                        String.valueOf(secondMover.getDamage());
            }
            case REGENERATE -> {
                fight.attack(firstMover, secondMover, 2);
                information = firstMover.getName() +
                        " прерывает попытку регенерации и наносит урон в размере " +
                        String.valueOf(secondMover.getDamage());
                
            }
            default -> {
                if(firstMover.isBoss() && Math.random() <= 0.15){
                    fight.attack(firstMover, secondMover, 0.5);
                    information = firstMover.getName() + 
                                 " пробивает блок и наносит "
                                  + secondMover.getDamage() +
                                    " урона. ";
                }
                else {
                    fight.attack(secondMover, firstMover, 0.5);
                    information = secondMover.getName() + 
                                " защищается и наносит контрудар в размере " +
                                  String.valueOf(firstMover.getDamage());
                }
                
                
            }
        }
        return information;
    }    
    
    private String getDefendMoveInformation(Action secondAction) {
        String information = new String();
        
        switch (secondAction) {
            case ATTACK -> {
                if (secondMover.isBoss() && Math.random() < 0.15) {
                    fight.attack(secondMover, firstMover, 0.5);
                    information = secondMover.getName() + 
                                 " пробивает блок и наносит "
                                  + firstMover.getDamage() +
                                    " урона. ";
                }
                else {
                    fight.attack(firstMover, secondMover, 0.5);
                    information = firstMover.getName() +
                            " защищается и наносит контрудар в размере " +
                            String.valueOf(secondMover.getDamage());
                }
                
            }
       

            case WEAKENING -> {
                if (Math.random() < 0.75) {
                    fight.setWeakening(secondMover, firstMover);
                    information = secondMover.getName() + 
                                     " накладывает ОСЛАБЛЕНИЕ на " +
                                     firstMover.getName();
                } else {
                    information = firstMover.getName() +
                                    " избегает ОСЛАБЛЕНИЯ от " + 
                                    secondMover.getName();
                }
            }
            case DEFEND -> {
                if (Math.random() <= 0.5) {
                    secondMover.setState(Action.STUNNED);
                    information = firstMover.getName() + 
                                     " застанил " + 
                                     secondMover.getName();
                    
                }
            }
            case STUNNED -> {
                information = firstMover.getName() + 
                                 " уходит в защиту. " + 
                                 secondMover.getName() +
                                 " оглушен и пропускает ход.";
            }
            case INVENTORY -> information = secondMover.getName() + 
                                               fight.useItem(healItemid) +". " +
                                               firstMover.getName() + " защищается.";                   

            case REGENERATE -> {
                if(Math.random() <= 0.7){
                    fight.regenerate();
                    information = firstMover.getName() + " защищается. " + 
                            secondMover.getName() + " регенерировал. ";}
            }
        
            
        
        }
        return information;
    }

    
    private String getWeakeningMoveInformation(Action secondAction) {
        String information = new String();
        
        switch (secondAction) {
            case ATTACK -> {
                fight.attack(secondMover, firstMover, 1.15);
                information = secondMover.getName() + " сбивает попытку ослабления и наносит урон в размере " +
                                   String.valueOf(firstMover.getDamage());
                
            }

            case DEFEND -> {
                if(Math.random() < 0.75){
                    fight.setWeakening(firstMover, secondMover);
                    information = firstMover.getName() + 
                                    " накладывает ОСЛАБЛЕНИЕ на "
                                    + secondMover.getName();
                } else {
                     information = secondMover.getName() + " избегает ОСЛАБЛЕНИЯ от " + firstMover.getName();
                }
            }
            case WEAKENING -> {
                fight.setWeakening(firstMover, secondMover);
                fight.setWeakening(secondMover, firstMover);
                information = "Соперники наложили эффект ОСЛАБЛЕНИЕ друг на друга";
            }
            case REGENERATE -> {
                fight.setWeakening(secondMover, firstMover);
                if(Math.random() <= 0.7){
                    fight.regenerate();
                    information = secondMover.getName() + 
                                    " регенерировал и получает от "+ 
                                    firstMover.getName() + "эффект ОСЛАБЛЕНИЕ";

                } else {
                    information = firstMover.getName() +
                            "не удалось регенерировать. Получает ослабление от " + secondMover;

                }
            }

            case STUNNED -> {
                fight.setWeakening(firstMover, secondMover);
                information = secondMover.getName() + " накладывает ОСЛАБЛЕНИЕ на  " + 
                        firstMover.getName() +". " + firstMover.getName() +
                        " оглушен и пропускает ход.";
            }
            
            case INVENTORY -> {
                fight.setWeakening(firstMover, secondMover);
                information = secondMover.getName() + 
                                " накладывает ОСЛАБЛЕНИЕ на  " + 
                                 firstMover.getName() + ". " + 
                                 firstMover.getName() + 
                                 fight.useItem(healItemid);
            }
        } return information;
    }
    
    private String getRegenerateMoveInformation(Action secondAction) {
        String information = new String();
        switch (secondAction) {
            case ATTACK -> {
                fight.attack(secondMover, firstMover, 2);
                information = secondMover.getName() + 
                        " прерывает попытку регенерации и наносит урон в размере " +
                        String.valueOf(firstMover.getDamage());
            }

            case DEFEND -> {
                information = firstMover.getName() + " использовал регенерацию и восстановил " +
                            (firstMover.getMaxHealth() - firstMover.getHealth())/2;
                fight.regenerate();
                

                }
            case WEAKENING -> {
                fight.setWeakening(secondMover, firstMover);
                if(Math.random() <= 0.7){
                    fight.regenerate();
                    information = firstMover.getName() + 
                            " регенерировал и получает от "+ secondMover.getName() + "эффект ОСЛАБЛЕНИЕ";

                }else {
                    information = firstMover.getName() +
                            "не удалось регенерировать. Получает ослабление от " + secondMover;

                }

            }
            case STUNNED -> {
                fight.regenerate();
                information = firstMover.getName() + " регенерирует.";
            }
            case INVENTORY -> {
                fight.regenerate();
                information = firstMover.getName() + 
                                 " регенерирует. " + 
                                 secondMover.getName() + 
                                 fight.useItem(healItemid);
                break;
            }
        }
        return information;
    }
        
    private String getStunnedMoveInformation(Action secondAction) {
       String information = new String();
       switch (secondAction) {
            case ATTACK -> {
                fight.attack(secondMover, firstMover, 1);
                information = firstMover.getName() + 
                        " пропускает ход и получает урон в размере " + 
                        String.valueOf(firstMover.getDamage()) +
                        " от " + secondMover.getName();
                
            }
            case DEFEND -> information = firstMover.getName() + 
                        " пропускает ход" ;
            case WEAKENING -> {
                fight.setWeakening(secondMover, firstMover);
                information = firstMover.getName() + 
                        " получает эффект ОСЛАБЛЕНИЕ от " +
                        secondMover.getName();
            }
            case REGENERATE -> {
                if(Math.random() <= 0.7){
                    fight.regenerate();
                    information = firstMover.getName() + " пропускает ход. " + 
                            secondMover.getName() + " регенерировал. ";}
            }
            case INVENTORY -> information = firstMover.getName() + 
                                                " оглушен. " +
                                                secondMover.getName() + 
                                                fight.useItem(healItemid);
                        }           
       return information;                     
    } 
    
    private String getInventoryMoveInformation(Action secondAction) {
        String information = new String();
        switch (secondAction) {
            case ATTACK -> {
                fight.attack(secondMover, firstMover, 1);
                fight.useItem(healItemid);
                information = firstMover.getName() +
                        " получает урон в размере " +
                        String.valueOf(firstMover.getDamage()) +
                        " от " + secondMover.getName();
                
                
            }
            case DEFEND -> information = firstMover.getName() +
                                 fight.useItem(healItemid);
            case WEAKENING -> {
                fight.useItem(healItemid);
                fight.setWeakening(secondMover, firstMover);
                information = firstMover.getName() + 
                        "получает эффект ОСЛАБЛЕНИЕ от " +
                        secondMover.getName();
            }
            case REGENERATE -> {
                if(Math.random() <= 0.7){
                    fight.regenerate();
                    information = firstMover.getName() +
                            fight.useItem(healItemid) +
                            secondMover.getName() +
                            " регенерировал. ";}
                else {
                    information = firstMover.getName() + 
                                     fight.useItem(healItemid) + 
                                     secondMover.getName() + 
                                     " не удалось регенерировать ";
                }
            }    
        }
        return information;
    }
    

    
    
    
    
    
    public void setCurrentMove(int currMove) {
        currentMove = currMove;
    }
    
    public void changeMove() {
        currentMove++;
    }
    
    public int getCurrentMove() {
        return currentMove;
    }
    
    public Action getEnemysAction() {
        return enemysAction;
    }
    
    public String manageUsingItem(int itemID) {
         healItemid = itemID;
         return manageMoves(Action.INVENTORY);
   
    }
    
}
