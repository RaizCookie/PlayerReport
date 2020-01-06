package de.raizcookie.report.methods;

import de.raizcookie.report.main.Main;
import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Messages{
  public static File messages = new File(Main.getPlugin().getDataFolder().getAbsolutePath(), "messages.yml");
  public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(messages);
  
  public static void save(){
    try{
      cfg.save(messages);
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }
  
  public static void create(){
    if ((!messages.exists()) || (messages.length() < 100L)){
      cfg.set("report_usage", "&3[&cREPORT&3]&cYou have to specify a &6Reason &cand/or a &6Player!");
      cfg.set("report_self", "&3[&cREPORT&3]&cYou cannot report yourself.");
      cfg.set("report_player_offline", "&3[&cREPORT&3]&cThis &6Player &cis offline.");
      cfg.set("report_no_permission", "&3[&cREPORT&3]&cYou are missing the required permission!");
      cfg.set("report_accuse_team", "&3[&cREPORT&3]&cYou cannot report &6Teammembers &c!");
      cfg.set("report_list", "&3[&cREPORT&3]&cUse one of the reasons &6below&c.");
      cfg.set("report_team_message", "&3[&cREPORT&3] &a<PLAYERNAME> &3has just reported &c<TARGETNAME> &3because of &4<REASON>!");
      cfg.set("report_noAuthorized", "&3[&cREPORT&3] &cThere is no authorized person online. Your report was saved in the &aLogs &c.");
      cfg.set("report_repeated", "&3[&cREPORT&3] &cYou have already reported this &6Player&c.");
      cfg.set("report_success", "§3[§cREPORT§3]§cYou have successfully §areported§c this &6Player&c!");
      save();
    }
  }
  
  public static void check(){
    if (!cfg.contains("report_usage")) {
    	cfg.set("report_usage", "&3[&cREPORT&3]&cYou have to specify a &6Reason &cand/or a &6Player!");
    }
    if (!cfg.contains("report_self")) {
    	cfg.set("report_self", "&3[&cREPORT&3]&cYou cannot report yourself.");
    }
    if (!cfg.contains("report_player_offline")) {
    	cfg.set("report_player_offline", "&3[&cREPORT&3]&cThis &6Player &cis offline.");
      }
    if (!cfg.contains("report_no_permission")) {
      cfg.set("report_no_permission", "&3[&cREPORT&3]&cYou are missing the required permission!");
    }
    if (!cfg.contains("report_accuse_team")) {
    	cfg.set("report_accuse_team", "&3[&cREPORT&3]&cYou cannot report &6Teammembers &c!");
    }
    if (!cfg.contains("report_list")) {
    	cfg.set("report_list", "&3[&cREPORT&3]&cUse one of the reasons &6below&c.");
    }
    if (!cfg.contains("report_team_message")) {
    	cfg.set("report_team_message", "&3[&cREPORT&3] &a<PLAYERNAME> &3has just reported &c<TARGETNAME> &3because of &4<REASON>!");
      }
    if (!cfg.contains("report_repeated")) {
    	cfg.set("report_repeated", "&3[&cREPORT&3] &cYou have already reported this &6Player&c.");
    }
    if (!cfg.contains("report_noAuthorized")) {
    	cfg.set("report_noAuthorized", "&3[&cREPORT&3] &cThere is no authorized person online. Your report was saved in the &aLogs &c.");
    }
    if (!cfg.contains("report_success")) {
        cfg.set("report_success", "§3[§cREPORT§3]§cYou have successfully §areported§c this &6Player&c!");
    }
    save();
  }
}
