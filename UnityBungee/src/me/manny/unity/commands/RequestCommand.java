package me.manny.unity.commands;

import me.manny.unity.utils.cooldown.Cooldown;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class RequestCommand extends Command {
	
	public RequestCommand() {
		super("Request", "unity.commands.request", new String[0]);
	}
	
	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
		if(args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Usage: /request <Reason>");
			return;
		}
		ProxiedPlayer p = (ProxiedPlayer)sender;
		if(Cooldown.isCooling(p.getUniqueId(), "Request")) {
			sender.sendMessage(ChatColor.YELLOW + "You must wait " + ChatColor.LIGHT_PURPLE + Cooldown.getRemaining(p.getUniqueId(), "Request") + "s" + ChatColor.YELLOW + " before you can report another player.");
			return;
		}
	    	  for (ProxiedPlayer g : ProxyServer.getInstance().getPlayers()) {
	  	    	if(g.hasPermission("unity.commands.request.view")) {
	  	    		g.sendMessage(ChatColor.GOLD.toString() + ChatColor.STRIKETHROUGH + "-----*----------------------------*-----");
	  	    		g.sendMessage(ChatColor.YELLOW + "Request " + ChatColor.GOLD + " - ");
	  	    		g.sendMessage(ChatColor.YELLOW + "  Player" + ChatColor.GOLD + " \u00bb " + ChatColor.WHITE + sender.getName() + ChatColor.GRAY + " (" + ((ProxiedPlayer)sender).getServer().getInfo().getName() + ")");
	  	    		g.sendMessage(ChatColor.YELLOW + "  Reason" + ChatColor.GOLD +  " \u00bb " + this.getMessage(args));
	  	    		g.sendMessage(ChatColor.GOLD.toString() + ChatColor.STRIKETHROUGH + "-----*----------------------------*-----");
	  	    		g.sendMessage(ChatColor.WHITE + sender.getName() + ChatColor.GRAY + "(" + ((ProxiedPlayer)sender).getServer().getInfo().getName() + ")" + ChatColor.YELLOW + " has requested for help with reason " + ChatColor.LIGHT_PURPLE + this.getMessage(args) + ChatColor.YELLOW + ".");
	    	  }
	      }
	    	  
	    	  sender.sendMessage(ChatColor.YELLOW + "Your request has been successfully sent. If you abuse this system you'll recieve a punishment & loose access to teamspeak support, request & report.");
	    	  sender.sendMessage(ChatColor.YELLOW + "Created by " + ChatColor.GOLD + "ItsManny" + ChatColor.YELLOW + "!");
	    	  Cooldown.add(p.getUniqueId(), "Request", 60, System.currentTimeMillis());
	}
	
	private String getMessage(String[] args) {
        final StringBuilder message = new StringBuilder();
        for (int i = 1; i < args.length; ++i) {
            message.append(args[i] + " ");
        }
		return message.toString();
	}
}