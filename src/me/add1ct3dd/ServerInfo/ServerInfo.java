package me.add1ct3dd.ServerInfo;

import java.util.logging.Logger;
import org.bukkit.util.config.Configuration;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("deprecation")
public class ServerInfo extends JavaPlugin {
	
	public static ServerInfo plugin;
	public Configuration CONFIG;
	public final Logger logger = Logger.getLogger("Minecraft");
	public final ServerInfoPlayerListener playerListener = new ServerInfoPlayerListener(this);
	public final ServerInfoCommandExecutor myExecutor = new ServerInfoCommandExecutor(this);

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " is now disabled.");
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		CONFIG = getConfiguration();
		
		getCommand("admins").setExecutor(myExecutor);
		getCommand("mods").setExecutor(myExecutor);
		getCommand("builders").setExecutor(myExecutor);
		getCommand("info").setExecutor(myExecutor);
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_CHAT, this.playerListener, Event.Priority.Normal, this);
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled.");
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		return true;
	}
}
