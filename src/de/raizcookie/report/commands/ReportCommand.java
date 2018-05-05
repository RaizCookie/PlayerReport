package de.raizcookie.report.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import de.raizcookie.report.main.Main;


public class ReportCommand implements CommandExecutor, Listener{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			if(args.length == 2){
				Player p = (Player) sender;
				Player target = Bukkit.getPlayer(args[0]);
				String reason = args[1].toUpperCase();
				if(target != p) {
				if(target != null) {
					if(!target.hasPermission("report.team")) {
						FileConfiguration cfg = Main.getPlugin().getConfig();
						String[] reasons = {"HACKING", "BELEIDIGUNG", "CHATVERHALTEN", "WORTWAHL", "RASSISMUS", "BUGUSING", "TEAMING", "USERNAME", "SKIN", "SPAWN-CAMPING", "SPAM", "PROVOKATION"};
					if(reason.equalsIgnoreCase(reasons[0].toUpperCase())  || reason.equalsIgnoreCase(reasons[1].toUpperCase()) || reason.equalsIgnoreCase(reasons[2].toUpperCase()) || reason.equalsIgnoreCase(reasons[3].toUpperCase()) || reason.equalsIgnoreCase(reasons[4].toUpperCase()) || reason.equalsIgnoreCase(reasons[5].toUpperCase()) || reason.equalsIgnoreCase(reasons[6].toUpperCase()) || reason.equalsIgnoreCase(reasons[7].toUpperCase()) || reason.equalsIgnoreCase(reasons[8].toUpperCase()) || reason.equalsIgnoreCase(reasons[9].toUpperCase()) || reason.equalsIgnoreCase(reasons[10].toUpperCase()) || reason.equalsIgnoreCase(reasons[11].toUpperCase())) {
						p.sendMessage("§3[§cREPORT§3]§cDu hast diese Person erfolgreich §areportet§c!");
						cfg.set(p.getName(), null);
						cfg.set(p.getName() + ".Beschuldigter", target.getName() );
						cfg.set(p.getName() + ".Grund", reason.toUpperCase());
						int i = 0;
						Main.getPlugin().saveConfig();
						for (Player team : Bukkit.getServer().getOnlinePlayers()) {
							if (team.hasPermission("report.receive")) {
								i++;
								team.sendMessage("§3[§cREPORT§3] §a" + p.getName() + " §3hat den Spieler §c" + target.getName() + "§3 wegen §4" + reason.toUpperCase() + " §3reportet!");
							}
						}
						if(i == 0) {
							p.sendMessage("§3[§cREPORT§3] §cImmoment ist leider kein Berechtigter Online, der dein Report bearbeiten kann. Er wurde in den §aLogs §cgespeichert.");
						}
					} else {
						p.sendMessage("§3[§cREPORT§3]§cBitte gebe einen §6Grund §caus dieser Liste an!");
						p.sendMessage("§cHACKING, BELEIDIGUNG, CHATVERHALTEN, WORTWAHL, RASSISMUS, BUGUSING, TEAMING, USERNAME, SKIN, SPAWN-CAMPING, SPAM, PROVOKATION");
					}
				} else {
					p.sendMessage("§3[§cREPORT§3]§cDu kannst kein §6Teammitglied §creporten!");
					}
				} else {
					p.sendMessage("§3[§cREPORT§3]§cDieser §6Spieler §cist nicht online!");
				}
				} else {
					p.sendMessage("§3[§cREPORT§3]§cDu kannst dich nicht §6selber §creporten!");
				}
			} else if (args.length == 1) {
				Player p = (Player) sender;
				p.sendMessage("§3[§cREPORT§3]§cDu musst einen §6Grund §cangeben!");
			} else {
				Player p = (Player) sender;
				p.sendMessage("§3[§cREPORT§3]§cDu musst einen §6Grund §cund/oder einen §6Spieler §cangeben!");
				p.sendMessage("§cHACKING, BELEIDIGUNG, CHATVERHALTEN, WORTWAHL, RASSISMUS, BUGUSING, TEAMING, USERNAME, SKIN, SPAWN-CAMPING, SPAM, PROVOKATION");
				}
			}

		return false;
	}
}

