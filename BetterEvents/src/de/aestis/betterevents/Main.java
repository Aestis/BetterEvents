package de.aestis.betterevents;

import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {
	
	public static Main instance;
	public static String Version = "0.1.12";
	
	public void onEnable() {
		instance=this;
		setupConfigs();  
		try {
			getCommand("events").setExecutor((CommandExecutor) new CommandManager());
		} catch (Exception ex) {
			System.out.println("Error whilst enabling: " + ex);
			return;
		}
		System.out.println("BetterEvents v" + Main.Version + " sucessfully enabled.");
	}
	public void onDisable() {
		System.out.println("BetterEvents v" + Main.Version + " disabled.");
	}
	
	private void setupConfigs() {
        FileConfiguration config = getConfig();     
        if (!config.isSet("enabled")) {config.set("enabled", true);}
        if (!config.isSet("broadcast")) {config.set("broadcast", "true");}   
        saveConfig();
    }
}
