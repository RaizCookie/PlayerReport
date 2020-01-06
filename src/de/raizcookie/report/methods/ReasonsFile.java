package de.raizcookie.report.methods;

import de.raizcookie.report.main.Main;
import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ReasonsFile{
  public static File reasons = new File(Main.getPlugin().getDataFolder().getAbsolutePath(), "reasons.yml");
  public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(reasons);
  
  public static void save(){
    try{
      cfg.save(reasons);
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }
  public static void create(){
    if ((!reasons.exists()) || (reasons.length() < 100L)){
      cfg.set("reasons", "HACKING;BELEIDIGUNG;CHATVERHALTEN;WORTWAHL;RASSISMUS;BUGUSING;TEAMING;USERNAME;SKIN;SPAWN-CAMPING;SPAM;PROVOKATION");
      save();
    }
  }
  public static String reasons() {
	  String reasons = "";
	  for(String returnValues : cfg.getString("reasons").split(";")) {
		  reasons += returnValues + " ";
	  }
	return reasons;
  }
  public static boolean ContainsReason(String reason) {
	  for(String returnValues : cfg.getString("reasons").split(";")) {
		  if(returnValues.equalsIgnoreCase(reason))
			  return true;
	  }
	  return false;
  }
}
