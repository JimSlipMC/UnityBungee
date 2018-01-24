package me.manny.unity.commands;

import java.util.Map;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ServerCommand extends Command {
	
	public ServerCommand() {
		super("server", "unity.commands.server", new String[] { "join" });
	}
	
	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
		if (!(sender instanceof ProxiedPlayer)) return;
		ProxiedPlayer player = (ProxiedPlayer)sender;
	    Map<String, ServerInfo> servers = ProxyServer.getInstance().getServers();
	    if (args.length == 0) {
	    	player.sendMessage(ChatColor.YELLOW + "You are currently connected to the server " + ChatColor.WHITE + player.getServer().getInfo().getName() + ChatColor.YELLOW + ".");
	    	player.sendMessage(ChatColor.YELLOW + "Type " + ChatColor.GOLD + "/server <Server>" + ChatColor.YELLOW + " to connect to a diffrent server.");
	    	} else {
	    		ServerInfo server = (ServerInfo)servers.get(args[0]);
	    		if (server == null) {
	    		player.sendMessage(ChatColor.RED + "The server '" + args[0] + "' is not found!");
	    		} else if (!server.canAccess(player)) {
	    			player.sendMessage(ChatColor.RED + "You don't have access to connect to that server!");
	    	} else {
	    		player.sendMessage(ChatColor.YELLOW + "Trying to connect to the server " + ChatColor.WHITE + server.getName() + ChatColor.YELLOW + "...");
	    		player.connect(server);
	    	}
	    }
	}
	  

}