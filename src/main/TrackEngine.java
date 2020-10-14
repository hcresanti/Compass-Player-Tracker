package main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.inventory.Inventory;

public class TrackEngine {
	
	public final static double hotRadius = 16;
	public static double iterator = 0;
	
	//Main tracking function
	public static void track() {
		
		//For everyone that 
		for (Player p : Main.targetMap.keySet()) {
			
			//Player data
			Player t = Main.getCurrTarget(p);
			Inventory pInv = p.getInventory();
			
			//Player has tracker in inventory and target outside the hot radius
			if (pInv.contains(Main.getCompass()) && playerDistance(p, t) > hotRadius) {
				p.setCompassTarget(t.getLocation());
			
			//Player has tracker in inventory and target inside the hot radius
			} else if (pInv.contains(Main.getCompass()) && playerDistance(p, t) <= hotRadius) {
				p.setCompassTarget(spin(p));
			}
		}
	}
	
    //Finds the nearest player
    public static Player nearestPlayer(Player caller) {
    	
    	Player nearest = null;
    	double currNearestDist = 1000000;
    	
    	//For each player currently online
    	for (Player target : Bukkit.getOnlinePlayers()) {
    		
    		//Excludes caller from search and compares to current nearest
    		double distance = playerDistance(caller, target);
    		if (target != caller && distance < currNearestDist) {
        		nearest = target;
        		currNearestDist = distance;
    		}
    	}
    	
    	return nearest;
    }
    
    //Finds the distance between Player A and Player B
    public static double playerDistance(Player a, Player b) {
    	return a.getLocation().distance(b.getLocation());
    }	
    
    //Spins the compass (kind of working)
    public static Location spin(Player p) {
    	double pX = p.getLocation().getX();
    	double pY = p.getLocation().getY();
    	Location pLoc = p.getLocation().clone();
    	
    	if (iterator > 6.6) {
    		iterator = 0;
    	}
    	
    	pLoc.setX(pX += 100 * Math.cos(iterator));
    	pLoc.setY(pY += 100 * Math.sin(iterator));
    	iterator += 0.1;
    	
    	return pLoc;
    }
}
