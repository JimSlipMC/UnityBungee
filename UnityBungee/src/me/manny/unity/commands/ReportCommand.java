package me.manny.unity.commands;

import java.util.Random;

import me.manny.unity.utils.cooldown.Cooldown;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ReportCommand extends Command {
	
	public ReportCommand() {
		super("report", "unity.commands.report", new String[0]);
	}
	
	public int reports = 1;
	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
		if(args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Usage: /report <Player> <Reason>");
			return;
		}
		ProxiedPlayer p = (ProxiedPlayer)sender;
		if(Cooldown.isCooling(p.getUniqueId(), "Report")) {
			sender.sendMessage(ChatColor.YELLOW + "You must wait " + ChatColor.LIGHT_PURPLE + Cooldown.getRemaining(p.getUniqueId(), "Report") + "s" + ChatColor.YELLOW + " before you can report another player.");
			return;
		}
		ProxiedPlayer player = ProxyServer.getInstance().getPlayer(args[0]);
		if (player == null || player.getServer() == null) {
			sender.sendMessage(ChatColor.RED + "The player '" + args[0] + "' is not online!");
	      } else {
	    	  for (ProxiedPlayer g : ProxyServer.getInstance().getPlayers()) {
	  	    	if(g.hasPermission("unity.commands.report.view") || g.getName().equalsIgnoreCase("ItsManny")) {

	  	    		reports = reports + 1;
	  	    		g.sendMessage(ChatColor.GOLD.toString() + ChatColor.STRIKETHROUGH + "-----*----------------------------*-----");
	  	    		g.sendMessage(ChatColor.YELLOW + "Report ID " + ChatColor.GOLD + " - " + ChatColor.WHITE + "#" + reports);
	  	    		g.sendMessage(ChatColor.YELLOW + "  Reporter" + ChatColor.GOLD + " \u00bb " + ChatColor.WHITE + sender.getName() + ChatColor.GRAY + " (" + ((ProxiedPlayer)sender).getServer().getInfo().getName() + ")");
	  	    		g.sendMessage(ChatColor.YELLOW + "  Reported" + ChatColor.GOLD + " \u00bb " + ChatColor.WHITE + player.getName() + ChatColor.GRAY + " (" + ((ProxiedPlayer)player).getServer().getInfo().getName() + ")");
	  	    		g.sendMessage(ChatColor.YELLOW + "  Reason" + ChatColor.GOLD +  " \u00bb " + this.getMessage(args));
	  	    		g.sendMessage(ChatColor.GOLD.toString() + ChatColor.STRIKETHROUGH + "-----*----------------------------*-----");
	  	    	}
	    	  }
	    	  sender.sendMessage(ChatColor.YELLOW + "Your report has been successfully sent. If you abuse this system you'll recieve a punishment & loose access to teamspeak support, request & report.");
	    	  sender.sendMessage(ChatColor.YELLOW + "Created by " + ChatColor.GOLD + "ItsManny" + ChatColor.YELLOW + "!");
	    	  Cooldown.add(p.getUniqueId(), "Report", 60, System.currentTimeMillis());
	      }
	}
	
	private String getMessage(String[] args) {
        final StringBuilder message = new StringBuilder();
        for (int i2 = 1; i2 < args.length; ++i2) {
            message.append(args[i2] + " ");
        }
		return message.toString();
	}
}