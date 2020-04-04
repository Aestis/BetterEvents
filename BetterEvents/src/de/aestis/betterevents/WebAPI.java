package de.aestis.betterevents;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class WebAPI {
	private static WebAPI instance;
	private String result = "NULL";
	
	MessageBuilder msg = MessageBuilder.getInstance();
	
	public static WebAPI getInstance() {
		if (instance == null) {
			instance = new WebAPI();
		}
		return instance;
	}
	
	public void flushResult() {
		this.result = "NULL";
	}
	
	public void getRequest (String endpoint, Player sender, String mode) {
		if (this.result != "NULL") {
			sender.sendMessage("Events could not be fetched.");
			return;
		}
		this.result = "-0";
		Bukkit.getScheduler().runTaskAsynchronously(Main.instance, () -> {
			try {
				URL url = new URL("http://aestis.de/jm/inc/_events.php?mode=plugin" + endpoint);
				HttpURLConnection xhr = (HttpURLConnection) url.openConnection();
				xhr.setRequestMethod("GET");
				BufferedReader red = new BufferedReader(new InputStreamReader(xhr.getInputStream()));
				String response;
				this.result = "";
				while ((response = red.readLine()) != null) this.result += response;
				
				processRequest(sender, mode);
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}		
		});
	}
	
	private void processRequest (Player sender, String mode) throws ParseException {
		
		this.result = this.result.substring(4, this.result.length() - 1);	
		if (this.result.startsWith("-10")) {
			
		}

		String[] sr = this.result.split("~");
		
		for (String s: sr) {
			msg.CreateEventEntry(sender, Integer.parseInt(s.split("\\|")[0]), s.split("\\|")[1], s.split("\\|")[2], s.split("\\|")[3], mode);
			if (mode == "NEXT") {
				msg.CreateDetailView(sender, s.split("\\|")[4], s.split("\\|")[3]);
			}
		}
		sender.sendMessage("");
		this.result = "NULL";
	}
}
