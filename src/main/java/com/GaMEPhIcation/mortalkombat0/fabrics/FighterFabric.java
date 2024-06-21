/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GaMEPhIcation.mortalkombat0.fabrics;

import com.GaMEPhIcation.mortalkombat0.characters.Fighter;
import com.GaMEPhIcation.mortalkombat0.characters.Fighter.Fighters;

/**
 *
 * @author Мария
 */
public class FighterFabric {

    public Fighter create(Fighters character, Boolean isPlayer) {
        FighterFabricInterface fabric = null;

        switch (character) {
            case BARAKA -> fabric = new BarakaFabric();
            case SUB_ZERO -> fabric = new SubZeroFabric();
            case LIU_KANG -> fabric = new LiuKangFabric();
            case SONYA_BLADE -> fabric = new SonyaBladeFabric();
            case SHAO_KAHN -> fabric = new ShaoKahnFabric();
        }
        Fighter fighter = fabric.create(isPlayer);
        return fighter;
    }
}
