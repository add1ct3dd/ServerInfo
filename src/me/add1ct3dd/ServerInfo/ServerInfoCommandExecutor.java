package me.add1ct3dd.ServerInfo;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ServerInfoCommandExecutor implements CommandExecutor {
	
	public static ServerInfo plugin;
	
	public ServerInfoCommandExecutor (ServerInfo instance){
		plugin = instance;
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] split) {
		
		ChatColor RED = ChatColor.RED;
		ChatColor WHITE = ChatColor.WHITE;
		String sprefix = RED + "[" + WHITE + "Server" + RED + "]" + WHITE + " ";
		
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}
		String cmdName = cmd.getName().toLowerCase(); 
		if (cmdName.equals("admins")) {
			if (player == null) {
				sender.sendMessage("This command can only be run by a player!");
			} else {
				String admins = plugin.CONFIG.getString("admins");
				sender.sendMessage(sprefix + admins);
			}
			return true; 
		} else if (cmdName.equals("mods")) {
			if (player == null) {
				sender.sendMessage("This command can only be run by a player!");
			} else {
				String mods = plugin.CONFIG.getString("mods");
				sender.sendMessage(sprefix + mods);
			}
			return true;
		} else if (cmdName.equals("builders")) {
			if (player == null) {
				sender.sendMessage("This command can only be run by a player!");
			} else {
				String builders = plugin.CONFIG.getString("builders");
				sender.sendMessage(sprefix + builders);
			}
			return true;
		} else if (cmdName.equals("info")) {
			if(split.length >= 1) {
				String subcommand = split[0];
				if (subcommand.equalsIgnoreCase("reload")) {
					if (!checkPerm(sender, "reload")) return true;
					plugin.getConfiguration().load();
					sender.sendMessage(ChatColor.GREEN + "Configuration reloaded.");
				}
				else if (subcommand.equalsIgnoreCase("save")) {
					if (!checkPerm(sender, "save")) return true;
					plugin.saveConfig();
					sender.sendMessage(ChatColor.GREEN + "Configuration saved.");
				}
			}
		}
		return false;
	}
	private boolean checkPerm(CommandSender sender, String subnode) {
	    boolean ok = sender.hasPermission("ServerInfo." + subnode);
	    if (!ok) {
	        sender.sendMessage(ChatColor.RED + "You do not have permissions to do that.");
	    }
	    return ok;
	}
}


