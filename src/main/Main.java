package main;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import commands.Track;
import commands.Tracker;
import listeners.EventListener;

public class Main extends JavaPlugin {
	
	public static HashMap<Player, Player> targetMap = new HashMap<>();
	public static final ItemStack compass = new ItemStack(Material.COMPASS);

	@Override
    public void onEnable() {
		
		getLogger().info("Compass Player Tracker enabled!");
		
		//Builds final compass ItemStack
        ItemMeta meta = compass.getItemMeta();
        meta.setDisplayName("Tracker");
        compass.setItemMeta(meta);
    	
    	//Registration
    	getServer().getPluginManager().registerEvents(new EventListener(), this);
    	this.getCommand("tracker").setExecutor(new Tracker());
    	this.getCommand("track").setExecutor(new Track());
    	
    	//Continuously runs tracker
        Bukkit.getScheduler().runTaskTimer(this, new Runnable(){

            @Override
            public void run() {
            	TrackEngine.track();
            }
         
        }, 0, 1L);
    }
    
    @Override
    public void onDisable() {
    	getLogger().info("Compass Player Tracker disabled!");
    }
    
    //Target getters and setters
	public static Player getCurrTarget(Player p) {
		return targetMap.get(p);
	}

	public static void setCurrTarget(Player p, Player target) {
		targetMap.put(p, target);
	}  
	
	public static ItemStack getCompass() {
		return compass;
	}
    
}
