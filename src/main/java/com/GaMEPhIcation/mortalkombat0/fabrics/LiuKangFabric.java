/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GaMEPhIcation.mortalkombat0.fabrics;

import com.GaMEPhIcation.mortalkombat0.characters.Fighter;
import com.GaMEPhIcation.mortalkombat0.characters.Fighter.Type;
import com.GaMEPhIcation.mortalkombat0.characters.LiuKang;



/**
 *
 * @author Мария
 */
public class LiuKangFabric implements FighterFabricInterface {

    @Override
    public Fighter create(Boolean isPlayer) {
        Fighter fighter;
        if (isPlayer) fighter = new LiuKang(1, 90, 16, 28, isPlayer, Type.SOLDIER);
        else{
            fighter= new LiuKang(1, 90, 20, 20, isPlayer, Type.SOLDIER);
        }
        return fighter;
    }
}
