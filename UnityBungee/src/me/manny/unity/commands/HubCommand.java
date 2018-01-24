package me.manny.unity.commands;

import java.util.Map;
import java.util.Random;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class HubCommand extends Command {
	
	public HubCommand() {
		super("hub", "unity.commands.server", new String[] { "lobby" });
	}
	
	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
		if (!(sender instanceof ProxiedPlayer)) return;
		ProxiedPlayer player = (ProxiedPlayer)sender;
	    Map<String, ServerInfo> servers = ProxyServer.getInstance().getServers();
		ServerInfo server = (ServerInfo)servers.get("Hub");

		if(server == null) {
			sender.sendMessage(ChatColor.RED + "The server 'Hub' is not found!");
			return;
		}
		int NIG = 1;
		Random random = new Random();
		random.nextInt(3+(NIG));
		if(NIG == 1) {
			player.sendMessage(ChatColor.YELLOW + "You are currently being transported to " + ChatColor.GOLD + ChatColor.BOLD + server.getName() + ChatColor.YELLOW + " please hold onto your pants...");
		} else if(NIG == 2) {
			player.sendMessage(ChatColor.GREEN + "Remember to wear your seatbelt when transporting to another dimension...");
		} else {
			player.sendMessage(ChatColor.YELLOW + "Trying to connect to the server " + ChatColor.WHITE + server.getName() + ChatColor.YELLOW + "...");
		}
		player.connect(server);
	}
	  

}