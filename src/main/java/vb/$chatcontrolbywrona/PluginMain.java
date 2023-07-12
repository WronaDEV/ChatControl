package vb.$chatcontrolbywrona;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.event.*;
import org.bukkit.plugin.java.*;

public class PluginMain extends JavaPlugin implements Listener {

	private static PluginMain instance;

	public static Object GLOBAL_432a18768fff1ac791ac7008262a7864;

	@Override
	public void onEnable() {
		instance = this;
		getServer().getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
	}

	@Override
	public void onDisable() {
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] commandArgs) {
		if (command.getName().equalsIgnoreCase("chat")) {
			try {
				if (((commandArgs.length > ((int) (0d)) ? commandArgs[((int) (0d))] : null) == null)) {
					commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&fCommands:"));
					commandSender.sendMessage(
							ChatColor.translateAlternateColorCodes('&', " &8\u00BB &a/chat &8- &fhelp command"));
					commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',
							" &8\u00BB &a/chat reload &8- &freload config"));
					commandSender.sendMessage(
							ChatColor.translateAlternateColorCodes('&', " &8\u00BB &a/chat clear &8- &fclear chat"));
					commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',
							" &8\u00BB &a/chat on/off &8- &fturn on/off chat"));
					commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&fServer is powered by &aChatControl &fcreated by &aWrona&f!"));
				}
				if (PluginMain.checkEquals((commandArgs.length > ((int) (0d)) ? commandArgs[((int) (0d))] : null),
						"clear")) {
					for (int loopNumber1 = 0; loopNumber1 < ((int) (300d)); loopNumber1++) {
						double FINAL_loopNumber1 = loopNumber1;
						org.bukkit.Bukkit.broadcastMessage(" ");
					}
					org.bukkit.Bukkit.broadcastMessage(String.valueOf(
							((org.bukkit.configuration.ConfigurationSection) (Object) org.bukkit.configuration.file.YamlConfiguration
									.loadConfiguration(new File(
											String.valueOf(PluginMain.getInstance().getDataFolder()), "config.yml")))
													.get("ClearMessage")));
				}
				if (PluginMain.checkEquals((commandArgs.length > ((int) (0d)) ? commandArgs[((int) (0d))] : null),
						"on")) {
					PluginMain.GLOBAL_432a18768fff1ac791ac7008262a7864 = ((java.lang.Object) (Object) false);
					org.bukkit.Bukkit.broadcastMessage(String.valueOf(
							((org.bukkit.configuration.ConfigurationSection) (Object) org.bukkit.configuration.file.YamlConfiguration
									.loadConfiguration(new File(
											String.valueOf(PluginMain.getInstance().getDataFolder()), "config.yml")))
													.get("EnableChatMessage")));
				}
				if (PluginMain.checkEquals((commandArgs.length > ((int) (0d)) ? commandArgs[((int) (0d))] : null),
						"off")) {
					PluginMain.GLOBAL_432a18768fff1ac791ac7008262a7864 = ((java.lang.Object) (Object) true);
					org.bukkit.Bukkit.broadcastMessage(String.valueOf(
							((org.bukkit.configuration.ConfigurationSection) (Object) org.bukkit.configuration.file.YamlConfiguration
									.loadConfiguration(new File(
											String.valueOf(PluginMain.getInstance().getDataFolder()), "config.yml")))
													.get("DisableChatMessage")));
				}
				if (PluginMain.checkEquals((commandArgs.length > ((int) (0d)) ? commandArgs[((int) (0d))] : null),
						"reload")) {
					PluginMain.getInstance().reloadConfig();
					commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPlugin reloaded!"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return true;
	}

	public static void procedure(String procedure, List procedureArgs) throws Exception {
	}

	public static Object function(String function, List functionArgs) throws Exception {
		return null;
	}

	public static List createList(Object obj) {
		if (obj instanceof List) {
			return (List) obj;
		}
		List list = new ArrayList<>();
		if (obj.getClass().isArray()) {
			int length = java.lang.reflect.Array.getLength(obj);
			for (int i = 0; i < length; i++) {
				list.add(java.lang.reflect.Array.get(obj, i));
			}
		} else if (obj instanceof Collection<?>) {
			list.addAll((Collection<?>) obj);
		} else if (obj instanceof Iterator) {
			((Iterator<?>) obj).forEachRemaining(list::add);
		} else {
			list.add(obj);
		}
		return list;
	}

	public static void createResourceFile(String path) {
		Path file = getInstance().getDataFolder().toPath().resolve(path);
		if (Files.notExists(file)) {
			try (InputStream inputStream = PluginMain.class.getResourceAsStream("/" + path)) {
				Files.createDirectories(file.getParent());
				Files.copy(inputStream, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static PluginMain getInstance() {
		return instance;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void event1(org.bukkit.event.player.PlayerChatEvent event) throws Exception {
		if (PluginMain.checkEquals(GLOBAL_432a18768fff1ac791ac7008262a7864, ((java.lang.Object) (Object) true))) {
			event.setCancelled(true);
			((org.bukkit.command.CommandSender) (Object) ((org.bukkit.entity.Player) event.getPlayer()))
					.sendMessage(String.valueOf(
							((org.bukkit.configuration.ConfigurationSection) (Object) org.bukkit.configuration.file.YamlConfiguration
									.loadConfiguration(new File(
											String.valueOf(PluginMain.getInstance().getDataFolder()), "config.yml")))
													.get("MutedChatMessage")));
		}
	}

	public static boolean checkEquals(Object o1, Object o2) {
		if (o1 == null || o2 == null) {
			return false;
		}
		return o1 instanceof Number && o2 instanceof Number
				? ((Number) o1).doubleValue() == ((Number) o2).doubleValue()
				: o1.equals(o2);
	}
}
