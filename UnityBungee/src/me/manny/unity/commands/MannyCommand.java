package me.manny.unity.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class MannyCommand extends Command {
	
	public MannyCommand() {
		super("manny", null, new String[] { "sexy" });
	}
	
	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eUnity Network's Bungee Mainframe created by Manny!"));
	}
}