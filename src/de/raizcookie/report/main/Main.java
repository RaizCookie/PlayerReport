package de.raizcookie.report.main;

import org.bukkit.plugin.java.JavaPlugin;

import de.raizcookie.report.commands.ReportCommand;
import de.raizcookie.report.methods.Messages;
import de.raizcookie.report.methods.ReasonsFile;

public class Main extends JavaPlugin{
	private static Main plugin;
	public void onEnable() {
		plugin = this;
		if(!ReasonsFile.reasons.exists())
			ReasonsFile.create();
		if(!Messages.messages.exists())
			Messages.create();
		Messages.check();
		getCommand("report").setExecutor(new ReportCommand());
	}
	public static Main getPlugin() {
		return plugin;
	}
}
