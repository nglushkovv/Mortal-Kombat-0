/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GaMEPhIcation.mortalkombat0.characters;

import com.GaMEPhIcation.mortalkombat0.mechanics.Fight.Action;
import com.GaMEPhIcation.mortalkombat0.mechanics.Item;
import javax.swing.ImageIcon;

/**
 *
 * @author Мария
 */
public abstract class Fighter {
    
    private int level;
    private double health;
    private double maxHealth;
    private double damage;
    private double attack;
    private Boolean debuff = false;
    private int timer;
    private ImageIcon[] sprites;
    private Action state = Action.ATTACK;
    private final Type type;
    private Boolean isBoss = false; 
    private final Boolean isPlayer;
    private final Item[] bag;

    public enum Fighters {
        SONYA_BLADE,
        BARAKA,
        SHAO_KAHN,
        SUB_ZERO,
        LIU_KANG
    
    }
    
    public enum Type {
        WIZARD,
        TANK,
        WARRIOR,
        SOLDIER
    }

    
    public Fighter(int level, double health, int damage, double attack, Boolean isPlayer, Type type){
        this.level=level;
        this.health=health;
        this.damage=damage;
        this.attack=attack;
        this.maxHealth=health;
        this.type = type;
        this.isPlayer = isPlayer;
        this.bag = new Item[]{new Item("Малое зелье лечения", (health * 0.25), 2),
                          new Item("Больше зелье лечения", (health * 0.5), 1),
                          new Item("Крест возрождения", 5000, 1)};
        createSpriteList(isPlayer);

    }
   
    public void setLevel(int level){
        this.level = level;
    }
    
    public void setHealth(double h){
        if (h < 0 && debuff) {
            h *= 1.25;
            damage = -h;
        }
        if(this.health+h < 0) this.health = 0;
        else if(this.health+h > maxHealth) this.health = maxHealth;
        else {
            this.health += h;
            if(h<0) damage = -h;
        }
    }

    public void setDamage(int d){
        this.damage+=d;
    }
    public void setAttack(double a){
        this.attack=a;
    }
    public void setMaxHealth(double h){
        this.maxHealth=h;
    }
    
    public int getLevel(){
        return this.level;
    }
    public double getHealth(){
        return this.health;
    }
    public double getDamage(){
        return this.damage;
    }
    public double getAttack(){
        if (debuff) return (this.attack * 0.5);
        return this.attack;
    }
    public double getMaxHealth(){
        return this.maxHealth;
    }
    
    public void becomeBoss() {
        this.isBoss = true;
        this.setMaxHealth(this.getMaxHealth()*2);
        this.setHealth(maxHealth);
    }
    
    public void becomeStandart() {
        this.isBoss = false;
    }
    
    public abstract String getName();
    
    public ImageIcon[] getSprites() {
        return sprites;
    }
    
    public void setSprites(ImageIcon[] sprites) {
        this.sprites = sprites;
    }
    
    private void createSpriteList(Boolean isPlayer){
       String gif = "gif";
       String png = "png";
       String flipped = "";
       if(!isPlayer) {
           flipped = "flipped/";
       } 
       
       if(this.getPathName().equals("shao_kahn")) gif = "png";
       if(this.getPathName().equals("baraka")) png = "gif";
       
       ImageIcon[] sprites = {
           new ImageIcon(this.getClass().getResource("/characters/"+this.getPathName()+"/"+flipped+"stay." + gif)),
           new ImageIcon(this.getClass().getResource("/characters/"+this.getPathName()+"/"+flipped+"punch." + png)),
           new ImageIcon(this.getClass().getResource("/characters/"+this.getPathName()+"/"+flipped+"block." + png)),
           new ImageIcon(this.getClass().getResource("/characters/"+this.getPathName()+"/"+flipped+"lose." + gif))        
       };
       this.setSprites(sprites);
    }

    public abstract String getPathName();
    
    public Action getState() {
        return state;
    }
    
    public void setState(Action action) {
        this.state = action;
    }
    
    public void decreaseDebuffTimer() {
        if (timer>0) timer -= 1;
        if (timer == 0) deleteDebuff();
    }
    
    public void setDebuff(int level) {
        debuff = true;
        timer = level+1;
    }
    
    public void deleteDebuff() {
        debuff = false;
        
    }
    
    public Type getType() {
        return type;
    }
    
    public Boolean isBoss() {
        return isBoss;
    }
    
    public Boolean isPlayer() {
        return isPlayer;
    }
    
    public Item[] getBag() {
        return bag;
    }
    
    
    

    
    
    
    
    
    
}
