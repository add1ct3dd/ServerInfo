package me.add1ct3dd.ServerInfo;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class ServerInfoPlayerListener extends PlayerListener {
	public static ServerInfo plugin;

	public ServerInfoPlayerListener(ServerInfo instance) {
		plugin = instance;
	}
	public void onPlayerChat(PlayerChatEvent chat){
		Player p = chat.getPlayer();
		String message = chat.getMessage();
		String message_lower = message.toLowerCase();
		ChatColor RED = ChatColor.RED;
		ChatColor WHITE = ChatColor.WHITE;
		if (message_lower.contains("hi") & message_lower.contains("server")) {
			p.sendMessage(RED + "[Server]" + WHITE + " Hello " + p.getName());
			chat.setCancelled(true);
		}
	}

}
