package commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import main.Main;

public class Track implements CommandExecutor {

		@Override
		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	       
			//Gives player one compass
			if (sender instanceof Player) {
	            Player player = (Player) sender;
	            Player target = Bukkit.getPlayer(label);
	            
	            if (player.getInventory().contains(Main.getCompass())) {
	    			
	            	if (target != null) {
	    				Main.setCurrTarget(player, target);
	    				player.sendMessage("Now Tracking: " + target.getDisplayName());
	    			} else {
	    				player.sendMessage("ERROR: Nobody to Track!");
	    			}
	            	
	            } else {
	            	player.sendMessage("ERROR: You don't have a Tracker!");
	            }
	        }
	        
			return true;
		}
}
