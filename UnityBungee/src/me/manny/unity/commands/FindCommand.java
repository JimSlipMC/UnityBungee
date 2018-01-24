package me.manny.unity.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class FindCommand extends Command {
	
	public FindCommand() {
		super("find", "unity.commands.find", new String[0]);
	}
	
	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
		if(args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Usage: /find <Player>");
			return;
		}
		ProxiedPlayer player = ProxyServer.getInstance().getPlayer(args[0]);
		if (player == null || player.getServer() == null) {
	    	  sender.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.STRIKETHROUGH + "-----*----------------------------*-----");
	    	  sender.sendMessage(ChatColor.RED + "Failed to find " + ChatColor.YELLOW + player.getName() + ChatColor.RED + "....");
	    	  sender.sendMessage(ChatColor.GRAY + "They are currently not connected to Unity Network...");
	    	  sender.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.STRIKETHROUGH + "-----*----------------------------*-----");
	      } else {

	    	  sender.sendMessage(ChatColor.GOLD.toString() + ChatColor.STRIKETHROUGH + "-----*----------------------------*-----");
	    	  sender.sendMessage(ChatColor.YELLOW + "Found " + ChatColor.WHITE + player.getName() + ChatColor.YELLOW + " in 3ms");
	    	  sender.sendMessage(ChatColor.YELLOW + "The player is currently located on the server " + ChatColor.LIGHT_PURPLE + player.getServer().getInfo().getName() + ChatColor.YELLOW + ".");
	    	  sender.sendMessage(ChatColor.GOLD.toString() + ChatColor.STRIKETHROUGH + "-----*----------------------------*-----");
	      }
	}
}