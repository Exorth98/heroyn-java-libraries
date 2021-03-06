package fr.exorth;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SurveyConfigManager {
	
	private SurveyMain plugin = SurveyMain.getPlugin(SurveyMain.class);

	public FileConfiguration cfg;
	public File file;
	
	public String fileName;
	public String folder;
	
	public SurveyConfigManager(String folder, String fileName) {
		
		this.fileName=fileName;
		this.folder=folder;
		
	}

	public void setupCustomConfig() {
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}

		file = new File(plugin.getDataFolder(), folder+"/"+fileName+".yml");

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//plugin.saveResource("TutoFile.yml", true);
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The "+folder+"/"+fileName+".yml file has been created");
		}

		cfg = YamlConfiguration.loadConfiguration(file);
	}

	public FileConfiguration getCustomConfig() {
		return cfg;
	}

	public void saveCustomConfig() {
		try {
			cfg.save(file);
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "The "+folder+"/"+fileName+".yml file has been saved");

		} catch (IOException e) {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not save the "+folder+"/"+fileName+".yml file");
		}
	}

	public void reloadCustomConfig() {
		cfg = YamlConfiguration.loadConfiguration(file);
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "The "+folder+"/"+fileName+".yml file has been reload");

	}

}
