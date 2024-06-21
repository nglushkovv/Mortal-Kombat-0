/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GaMEPhIcation.mortalkombat0.fabrics;

import com.GaMEPhIcation.mortalkombat0.characters.Fighter;
import com.GaMEPhIcation.mortalkombat0.characters.Fighter.Type;
import com.GaMEPhIcation.mortalkombat0.characters.SonyaBlade;


/**
 *
 * @author Мария
 */
public class SonyaBladeFabric implements FighterFabricInterface {

    @Override
    public Fighter create(Boolean isPlayer) {
        Fighter fighter;
        if (isPlayer) fighter = new SonyaBlade(1, 90, 16, 35, isPlayer, Type.SOLDIER);
        else{
            fighter = new SonyaBlade(1, 90, 16, 24, isPlayer, Type.SOLDIER);
        }    
         
        return fighter;
    }

}
