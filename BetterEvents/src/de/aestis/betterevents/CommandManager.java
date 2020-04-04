package de.aestis.betterevents;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CommandManager implements CommandExecutor {	
	
	FileConfiguration Config = Main.instance.getConfig();	
	
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] argArr) {		
		Player player = Bukkit.getPlayer(sender.getName());	
		WebAPI api = WebAPI.getInstance();
		MessageBuilder msg = MessageBuilder.getInstance();
		
		if (cmd.getName().equalsIgnoreCase("events") && argArr.length == 0) {
			
			sender.sendMessage("/events help");
			
		} else if (argArr[0].equalsIgnoreCase("help")) {
			
			player.sendMessage("");
			player.sendMessage("Events Hilfe:");
			msg.CreateHelpEntry(player, "next", " - Das naechste, geplante Event");
			msg.CreateHelpEntry(player, "week", " - Zeigt dir, was die naechsten 7 Tage ansteht");
			msg.CreateHelpEntry(player, "add", " - WIP! <title> <description> [active]");
			msg.CreateHelpEntry(player, "remove", " - WIP! <id>");
			msg.CreateHelpEntry(player, "reload", " - Admin command");

		} else if (argArr[0].equalsIgnoreCase("next")) {
			
			msg.CreateAsciiArt(player);
			player.sendMessage("");
			sender.sendMessage(ChatColor.WHITE + "<|===== " + ChatColor.RED + "Zeige das naechste Event" + ChatColor.GRAY + " | " + ChatColor.GRAY + "Tipp: Fahre mit der Maus ueber den Titel und die Beschreibung!" + ChatColor.WHITE + " =====|>");
			sender.sendMessage("");
			api.getRequest("&action=next", player, "NEXT");
			
		} else if (argArr[0].equalsIgnoreCase("week")) {
			
			msg.CreateAsciiArt(player);
			player.sendMessage("");
			sender.sendMessage(ChatColor.WHITE + "<|===== " + ChatColor.RED + "Zeige die naechsten 7 Tage" + ChatColor.WHITE + " | " + ChatColor.GRAY + "Tipp: Fahre mit der Maus ueber den Titel und die Beschreibung!" + ChatColor.WHITE + " ====|>");
			sender.sendMessage("");
			api.getRequest("&action=week", player, "WEEK");
			
		} else if (argArr[0].equalsIgnoreCase("list")) {
			
			//api.getRequest("&action=list", player, "LIST");
			
		} else if (argArr[0].equalsIgnoreCase("add")) {
			
			//TODO
			
		} else if (argArr[0].equalsIgnoreCase("remove")) {
			
			//TODO
			
		} else if (argArr[0].equalsIgnoreCase("reload")) {
			
			api.flushResult();	
			sender.sendMessage("BetterEvents " + Main.Version + " successfully reloaded!");
			
		}
		return false;
	}
}
