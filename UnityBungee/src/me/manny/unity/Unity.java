package me.manny.unity;

import java.util.concurrent.TimeUnit;

import me.manny.unity.commands.AlertCommand;
import me.manny.unity.commands.FindCommand;
import me.manny.unity.commands.HCFCommand;
import me.manny.unity.commands.HubCommand;
import me.manny.unity.commands.KitsCommand;
import me.manny.unity.commands.ListCommand;
import me.manny.unity.commands.MannyCommand;
import me.manny.unity.commands.ReportCommand;
import me.manny.unity.commands.RequestCommand;
import me.manny.unity.commands.ServerCommand;
import me.manny.unity.utils.cooldown.Cooldown;
import net.md_5.bungee.api.plugin.Plugin;

public class Unity extends Plugin {
	    
	public void onEnable() {
		getProxy().getPluginManager().registerCommand(this, new AlertCommand());
		getProxy().getPluginManager().registerCommand(this, new FindCommand());
		getProxy().getPluginManager().registerCommand(this, new HCFCommand());
		getProxy().getPluginManager().registerCommand(this, new HubCommand());
		getProxy().getPluginManager().registerCommand(this, new KitsCommand());
		getProxy().getPluginManager().registerCommand(this, new MannyCommand());
		getProxy().getPluginManager().registerCommand(this, new ReportCommand());
		getProxy().getPluginManager().registerCommand(this, new RequestCommand());
		getProxy().getPluginManager().registerCommand(this, new ServerCommand());
		getProxy().getPluginManager().registerCommand(this, new ListCommand());
		//getProxy().getPluginManager().registerCommand(this, new StaffChatCommand());
		
	//	getProxy().getPluginManager().registerListener(this, new StaffChatCommand());
		
		getProxy().getScheduler().schedule(this, new Runnable() {
			@Override
			public void run() {
				Cooldown.handleCooldowns();
			}
		}, 1, TimeUnit.MILLISECONDS.toMillis(1L), TimeUnit.MILLISECONDS);
	}
	
	public void onDisable() { }
	
	
}
