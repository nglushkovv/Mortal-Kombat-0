/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GaMEPhIcation.mortalkombat0.fabrics;

import com.GaMEPhIcation.mortalkombat0.characters.Fighter;
import com.GaMEPhIcation.mortalkombat0.characters.Fighter.Type;
import com.GaMEPhIcation.mortalkombat0.characters.ShaoKahn;

/**
 *
 * @author Мария
 */
public class ShaoKahnFabric implements FighterFabricInterface{
    
    @Override
    public Fighter create(Boolean isPlayer) {
        Fighter fighter;
        if (isPlayer) fighter = new ShaoKahn(3, 100, 18, 28,isPlayer, Type.WARRIOR);
        else {
            fighter = new ShaoKahn(3, 100, 18, 18,isPlayer, Type.WARRIOR);
        }
        return fighter;
    }
}
