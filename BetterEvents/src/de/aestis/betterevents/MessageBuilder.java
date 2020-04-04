package de.aestis.betterevents;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent.Action;

public class MessageBuilder {

	private static MessageBuilder instance;
	
	public static MessageBuilder getInstance() {
		if (instance == null) {
			instance = new MessageBuilder();
		}
		return instance;
	}
	
	public void CreateAsciiArt (Player sender) {
		sender.sendMessage("  " + "      _                  ______                  _       ");
		sender.sendMessage("  " + "      | |                  |  ____|                 | |      ");
		sender.sendMessage("  " + "      | | ____ ....   __  |  |___.....   ..... ___ __ | |__ ___ ");
		sender.sendMessage("  " + " ....   | |/ _ | '_ \\/ _ \\ |   ___\\ \\ / / _ | '_ \\| __/ __|");
		sender.sendMessage("  " + " | |__| |  __| |  | |  (_)  |  |____\\ V  | __| | | |  |_\\__ \\_");
		sender.sendMessage("  " + " \\___/\\__|_| |_|\\___/|______\\_/\\___|_| |_|\\__|___/" + "    " + Main.Version);
	}
	
	public void CreateEventEntry (Player sender, int eID, String title, String teaser, String date, String mode) {
		String dat = this.PrettifyDates(date);
		
		TextComponent eventTitle = ComponentBuilder( "[EVENT#" + eID + "] ", false, ChatColor.GOLD, "Findet statt am " + dat, "");
		TextComponent eventName = ComponentBuilder(title + ":", false, ChatColor.YELLOW, "", "");
		
		String tsr = teaser;
		if (mode != "NEXT") {
			int ttl = eventTitle.toPlainText().length() + eventName.toPlainText().length();
			if (teaser.length() + ttl > 100) {
				tsr = tsr.substring(0, 100 - ttl);
				tsr += "...";
			}		
		}
		TextComponent eventTeaser = ComponentBuilder(" " + tsr, false, ChatColor.WHITE, teaser, "");
		
		eventTitle.addExtra(eventName);
		eventTitle.addExtra(eventTeaser);
		sender.spigot().sendMessage(eventTitle);
	}
	
	public void CreateDetailView (Player sender, String owner, String date) {
		TextComponent eventOwner = ComponentBuilder(owner, false, ChatColor.DARK_PURPLE, "", "");
		TextComponent eventDate = ComponentBuilder(" @ " + this.PrettifyDates(date), false, ChatColor.WHITE, "", "");
		
		eventOwner.addExtra(eventDate);
		sender.spigot().sendMessage(eventOwner);
	}
	
	public void CreateHelpEntry (Player sender, String arg, String desc) {
		TextComponent fullCommand = ComponentBuilder("/events " + arg, false, ChatColor.AQUA, "/events " + arg + " ausführen", "/events " + arg);
		TextComponent commandDesc = ComponentBuilder(desc, false, ChatColor.DARK_AQUA, "", "");
		
		fullCommand.addExtra(commandDesc);
		sender.spigot().sendMessage(fullCommand);
	}
		
	
	private TextComponent ComponentBuilder (String text, Boolean bold, ChatColor color, String hover, String command) {
		TextComponent cmp = new TextComponent();
		cmp.setText(text);
		cmp.setBold(bold);
		cmp.setColor(color);
		if (hover.length() > 0) cmp.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create()));
		if (command.length() > 0) cmp.setClickEvent(new ClickEvent(Action.RUN_COMMAND, command));
		
		return cmp;
	}
	
	private String PrettifyDates (String input) {
		String rtn = input;
		rtn = rtn.replace(".1.", ". Januar ");
		rtn = rtn.replace(".2.", ". Februar ");
		rtn = rtn.replace(".3.", ". Maerz ");
		rtn = rtn.replace(".4.", ". April ");
		rtn = rtn.replace(".5.", ". Mai ");
		rtn = rtn.replace(".6.", ". Juni ");
		rtn = rtn.replace(".7.", ". Juli ");
		rtn = rtn.replace(".8.", ". August ");
		rtn = rtn.replace(".9.", ". September ");
		rtn = rtn.replace(".10.", ". Oktober ");
		rtn = rtn.replace(".11.", ". November ");
		rtn = rtn.replace(".12.", ". Dezember ");
		
		return rtn;
	}
	
}
