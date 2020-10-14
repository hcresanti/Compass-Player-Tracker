package listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import main.Main;
import main.TrackEngine;

public class EventListener implements Listener {
		
    @EventHandler
    //Starts tracker
    public void onPlayerHoldCompass(PlayerInteractEvent e) {
    	Player player = e.getPlayer();
    	Action action = e.getAction();
    	
    	//Checks to see if the user right clicks
    	if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
    		
    		//Matches item in hand to a compass named "Tracker"
    		ItemStack heldItem = player.getInventory().getItemInMainHand();
    		if (heldItem.getType() == Material.COMPASS && heldItem.getItemMeta().getDisplayName().equals("Tracker")) {
    			
    			//Finds nearest online player and sets them as the current target
    			Player target = TrackEngine.nearestPlayer(player);
    			
    			if (target != null) {
    				Main.setCurrTarget(player, target);
    				player.sendMessage("Now Tracking: " + target.getDisplayName());
    			} else {
    				player.sendMessage("ERROR: Nobody to Track!");
    			}
    			
    		}
    	}
    }
}

	
