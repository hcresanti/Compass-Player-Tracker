package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import main.Main;

public class Tracker implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
		//Gives player one compass
		if (sender instanceof Player) {
            Player player = (Player) sender;
            
            player.getInventory().addItem(Main.getCompass());
        }
        
		return true;
	}
}
