/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GaMEPhIcation.mortalkombat0.fabrics;

import com.GaMEPhIcation.mortalkombat0.characters.Fighter;
import com.GaMEPhIcation.mortalkombat0.characters.Baraka;
import com.GaMEPhIcation.mortalkombat0.characters.Fighter.Type;




/**
 *
 * @author Мария
 */
public class BarakaFabric implements FighterFabricInterface {

    @Override
    public Fighter create(Boolean isPlayer) {
        Fighter fighter;
        if (isPlayer) fighter = new Baraka(1, 120, 12, 26, isPlayer, Type.TANK);
        else {
            fighter = new Baraka(1, 120, 12, 15, isPlayer, Type.TANK);
        }
        return fighter;
    }
}
