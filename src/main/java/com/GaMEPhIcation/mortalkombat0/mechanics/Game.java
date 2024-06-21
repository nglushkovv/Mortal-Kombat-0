/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GaMEPhIcation.mortalkombat0.mechanics;

import com.GaMEPhIcation.mortalkombat0.ExcelWorker;
import com.GaMEPhIcation.mortalkombat0.Result;
import com.GaMEPhIcation.mortalkombat0.characters.Fighter;
import com.GaMEPhIcation.mortalkombat0.fabrics.FighterFabric;
import com.GaMEPhIcation.mortalkombat0.characters.Fighter.Fighters;
import static com.GaMEPhIcation.mortalkombat0.characters.Fighter.Fighters.*;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;




/**
 *
 * @author nglushkovv
 */
public class Game {
    private Player player;
    private Fighters[] enemies;
    private final FighterFabric fighterFabric;
    private final List<Location> locations;
    private final MoveManager moveManager;
    private Location currentLocation;
    private Integer locationsNumber;
    private Integer currentStage = 0;
    private ArrayList<Result> results;
    private ExcelWorker excelWorker;
    
    public Game() {
        fighterFabric = new FighterFabric();
        locations = new ArrayList<>();
        moveManager = new MoveManager();
        results = new ArrayList<>();
        excelWorker = new ExcelWorker(results);
        
        try {
            excelWorker.readExcel();
        } catch (IOException ex) {
            
        }
        
    }
    
    
    
    public void startNewGame(Fighters fighter) {
        
        Fighter playerFighter = fighterFabric.create(fighter, Boolean.TRUE);
        player = new Player("nickname", playerFighter, 0, 0);
        enemies = new Fighters[]
                  {SUB_ZERO,
                   SONYA_BLADE,
                   SHAO_KAHN,
                   BARAKA,
                   LIU_KANG};
    }
    
    public Location prepareLocation() {
        currentLocation = new Location(locations.size()+1, player.getFighter().getLevel(), enemies);
        locations.add(currentLocation);
        currentLocation.nextLevel(player);
        currentStage++;
        return currentLocation;
    }
    
    
    
    public Player getPlayer() {
        return player;
    }
    
    
    public MoveManager getMoveManager() {
        return moveManager;
    }
    
    public Location getCurrentLocation() {
        return currentLocation;
    }
    
    public void setLocationsNumber(int number) {
        locationsNumber = number;
    }
    
    public Boolean isLocationLast() {
        return Objects.equals(currentStage, locationsNumber);
    }
    
    public int getCurrentStage() {
        return currentStage;
    }
    
    public void increasePlayerFighterStats(Boolean isAttack) {
        if (isAttack) {
            player.getFighter().setAttack(player.getFighter().getAttack() +
                    5 * player.getFighter().getLevel());
        } else {
            player.getFighter().setMaxHealth(player.getFighter().getMaxHealth() + 10 * 
                    player.getFighter().getLevel());
        }
    }
    
    public void endGame(String name) throws IOException {
        results.add(new Result(name, player.getPoints()));
        excelWorker.setResults(results);
        excelWorker.WriteToExcel();
        
    }
    
    public ExcelWorker getExcelWorker() {
        return excelWorker;
    }
            

    
}
