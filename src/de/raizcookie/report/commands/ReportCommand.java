package de.raizcookie.report.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import de.raizcookie.report.main.Main;
import de.raizcookie.report.methods.Messages;
import de.raizcookie.report.methods.ReasonsFile;


public class ReportCommand implements CommandExecutor, Listener{
	
	  public static File reports = new File(Main.getPlugin().getDataFolder().getAbsolutePath(), "reports.yml");
	  public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(reports);
	  
	  public static void save(){
	    try{
	      cfg.save(reports);
	    }
	    catch (IOException e){
	      e.printStackTrace();
	    }
	  }
	
	public String convertString(String path) {
		return Messages.cfg.getString(path).replace("&", "§");
	}
	
	public int getReportSize(String targetName) {
		int size = 0;
		if(!reports.exists() || cfg.getConfigurationSection(targetName)==null)
			return size;
		size = cfg.getConfigurationSection(targetName).getKeys(false).size();
		return size;
	}
	public boolean hasReported(String targetName, String playerName) {
		if(cfg.getConfigurationSection(targetName)==null)
			return false;
		for(String key : cfg.getConfigurationSection(targetName).getKeys(false)) {
			if(cfg.getString(targetName + "." + key + ".accuser").equalsIgnoreCase(playerName))
				return true;
		}
		return false;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return false;
		}
		Player p = (Player) sender;
		if(args.length != 2){
			p.sendMessage(convertString("report_usage"));
			p.sendMessage("§c" + ReasonsFile.reasons());
			return false;
		}		
		Player target = Bukkit.getPlayer(args[0]);
		String reason = args[1].toUpperCase();
		if(target == p) {
			p.sendMessage(convertString("report_self"));
			return false;
		}
		if(target == null) {
			p.sendMessage(convertString("report_player_offline"));
			return false;
		}
		if(target.hasPermission("report.team")) {
			p.sendMessage(convertString("report_accuse_team"));
			return false;
		}
		if(!ReasonsFile.ContainsReason(reason)) {
			p.sendMessage(convertString("report_list"));
			p.sendMessage("§c" + ReasonsFile.reasons());
			return false;
		}
		if(hasReported(target.getName(), p.getName())) {
			p.sendMessage(convertString("report_repeated"));
			return false;
		}
		int timesReportet = getReportSize(target.getName())+1;
		
		cfg.set(target.getName() + "." + timesReportet + ".accuser", p.getName());
		cfg.set(target.getName() + "." + timesReportet + ".reason", reason.toUpperCase());
		p.sendMessage("§3[§cREPORT§3]§cDu hast diese Person erfolgreich §areportet§c!");
		save();
		int authorized = 0;
		for (Player team : Bukkit.getServer().getOnlinePlayers()) {
			if (team.hasPermission("report.receive")) {
				team.sendMessage(convertString("report_team_message").replace("<PLAYERNAME>", p.getName()).replace("<TARGETNAME>", target.getName()).replace("<REASON>", reason.toUpperCase()));
				authorized++;
			}
		}
		if(authorized == 0) {
			p.sendMessage(convertString("report_noAuthorized"));
		}

		return false;
	}
}

