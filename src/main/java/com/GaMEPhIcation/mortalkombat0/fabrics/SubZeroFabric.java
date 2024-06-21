/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GaMEPhIcation.mortalkombat0.fabrics;

import com.GaMEPhIcation.mortalkombat0.characters.Fighter;
import com.GaMEPhIcation.mortalkombat0.characters.Fighter.Type;
import com.GaMEPhIcation.mortalkombat0.characters.SubZero;



/**
 *
 * @author Мария
 */
public class SubZeroFabric implements FighterFabricInterface {

    @Override
    public Fighter create(Boolean isPlayer) {
        Fighter fighter;
        if (isPlayer) fighter = new SubZero(1, 95, 16, 24, isPlayer, Type.WIZARD);
        else {
            fighter = new SubZero(1, 95, 16, 18, isPlayer, Type.WIZARD);
        }
        return fighter;
    }

}
