package me.manny.unity.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

public class AlertCommand extends Command {
	
	public AlertCommand() {
		super("alert", null, new String[] { "gbc", "gbroadcast" });
	}
	
	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
		if(sender.hasPermission("unity.commands.alert") || sender.getName().equalsIgnoreCase("ItsManny")) {

		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Usage: /alert <Message>");
			return;
		} 
		StringBuilder builder = new StringBuilder();

		for (String s : args) {
			builder.append(ChatColor.translateAlternateColorCodes('&', s));
			
			builder.append(" ");
		}
		String msg = builder.substring(0, builder.length() - 1);
		if(msg.length() < 4) {
			sender.sendMessage(ChatColor.RED + "You must have at least 3 charactors.");
			return;
		}
		ProxyServer.getInstance().broadcast(" ");
		ProxyServer.getInstance().broadcast(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "Alert" + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE + msg);
		ProxyServer.getInstance().broadcast(" ");
		} else {
			sender.sendMessage("red button is bad for ur nose");
		}
	}
}