/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GaMEPhIcation.mortalkombat0.mechanics;

/**
 *
 * @author Мария
 */
public class Item {
    
    private String name;
    private double points;
    private int count;
    
    public Item(String name,double points, int count){
        this.name = name;
        this.count = count;
        this.points = points;
    }
    
    public void setName(String s){
        this.name=s;
    }
    public void setCount(int c){
        this.count=c;
        if (c < 0) this.count = 0;
        
    }
    
    public String getName(){
        return this.name;
    }
    public int getCount(){
        return this.count;
    }
    
    public double getPoints() {
        return points;
    }
    
    public void setPoints(double points) {
        this.points = points;
    }
    
    public Boolean isAvailable() {
        return count > 0;
    }
}
