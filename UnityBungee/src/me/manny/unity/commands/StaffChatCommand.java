package me.manny.unity.commands;

import java.util.ArrayList;
import java.util.UUID;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class StaffChatCommand extends Command implements Listener {
	
	public StaffChatCommand() {
		super("staffchat", "unity.commands.staffchat", new String[] { "sc" });
	}

	public static ArrayList<UUID> StaffToggle = new ArrayList<UUID>();

	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
		if (!(sender instanceof ProxiedPlayer));
		ProxiedPlayer player = (ProxiedPlayer)sender;
		if(args.length == 0) {
			if(StaffToggle.contains(player.getUniqueId())) {
				StaffToggle.remove(player.getUniqueId());
			} else {
				StaffToggle.add(player.getUniqueId());
			}
			player.sendMessage(ChatColor.YELLOW + "You are now " + (StaffToggle.contains(player.getUniqueId()) ? ChatColor.GREEN + "able" : ChatColor.RED + "unable") + ChatColor.YELLOW + " to type in staff chat.");
			return;
		}
		ProxiedPlayer p = (ProxiedPlayer)sender;
	    for (ProxiedPlayer g : ProxyServer.getInstance().getPlayers()) {
	    	if(g.hasPermission("unity.commands.staffchat")) {
	    		g.sendMessage(ChatColor.GRAY + "(Staff Chat) " + ChatColor.YELLOW + p.getName() + ChatColor.GOLD + " \u00bb " + ChatColor.WHITE + this.getMessage(args));
	    	}
	    }
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void chat(ChatEvent event) {
		if(event.getMessage().startsWith("/")) {
			return;
		}
		if(StaffToggle.contains(((ProxiedPlayer)event.getSender()).getUniqueId())) {
			ProxiedPlayer p = (ProxiedPlayer)event.getSender();
		    for (ProxiedPlayer g : ProxyServer.getInstance().getPlayers()) {
		    	if(g.hasPermission("unity.commands.staffchat")) {
		    		g.sendMessage(ChatColor.GRAY + "(Staff Chat) " + ChatColor.YELLOW + p.getName() + ChatColor.GOLD + " \u00bb " + ChatColor.WHITE + event.getMessage());
		    	}
		    }
			event.setCancelled(true);
		}
	}
	private String getMessage(String[] args) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]).append(" ");
		}
		return sb.toString();
	}
}