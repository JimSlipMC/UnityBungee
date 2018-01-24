package me.manny.unity.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.md_5.bungee.Util;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ListCommand extends Command  {
	
	public ListCommand() {
		super("list", "unity.commands.list", new String[] { "glist" });
	}
	public 		List<String> online = new ArrayList<String>();
	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
		int players = (Integer.valueOf(ProxyServer.getInstance().getOnlineCount()));

		for(ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
			if(online.contains(player.getName())) {
				online.remove(player.getName());
			}
			if(player.hasPermission("unity.commands.staff")) {
				if(!online.contains(player.getName())) {
					online.add(player.getName());
				} 
			} else {
				if(online.contains(player.getName())) {
					online.remove(player.getName());
				}
			}
		}
		if(args.length == 0) {
			sender.sendMessage(ChatColor.GOLD.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------");
			if(online.size() > 0) {
				sender.sendMessage(ChatColor.YELLOW + "Staff Online" + ChatColor.GOLD + " \u00bb " + ChatColor.WHITE + online.toString().replace("[", "").replace("]", "").replace(", ", ChatColor.YELLOW + ", " + ChatColor.WHITE));
			} else {
				sender.sendMessage(ChatColor.YELLOW + "Staff Online" + ChatColor.GOLD + " \u00bb " + ChatColor.WHITE + "None! :(");
			}
			sender.sendMessage(ChatColor.YELLOW + "The server currently has " + ChatColor.GOLD + ChatColor.BOLD + Integer.valueOf(ProxyServer.getInstance().getOnlineCount()) + ChatColor.YELLOW + " out of " + ChatColor.GOLD + ChatColor.BOLD + "1000" + ChatColor.YELLOW + " players!");
			sender.sendMessage(ChatColor.YELLOW + "Type " + ChatColor.GOLD + "/list <Server>" + ChatColor.YELLOW + " to view a list of a server.");
			sender.sendMessage(ChatColor.GOLD.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------");
			return;
		} else {
		    Map<String, ServerInfo> servers = ProxyServer.getInstance().getServers();
			ServerInfo server = (ServerInfo)servers.get(args[0]);
    		if (server == null) {
    			sender.sendMessage(ChatColor.RED + "The server '" + args[0] + "' is not found!");
    			return;
    		}
    		sender.sendMessage(ChatColor.YELLOW + "The server " + ChatColor.GOLD + ChatColor.BOLD + server.getName() + ChatColor.YELLOW + " has " + ChatColor.GOLD + ChatColor.BOLD + server.getPlayers().size() + ChatColor.YELLOW + " players online.");
    		sender.sendMessage(ChatColor.GOLD + " \u00bb " + ChatColor.YELLOW + "Players Online" + ChatColor.GOLD + ": " + ChatColor.WHITE + Util.format(server.getPlayers(), ChatColor.YELLOW + ", " + ChatColor.WHITE));
		}
	}

}