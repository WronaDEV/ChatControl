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
					commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&fServer is powered by &aChatControl &fcreated by &aWrona&f!"));
					PluginMain.getInstance().reloadConfig();
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

	public static boolean checkEquals(Object o1, Object o2) {
		if (o1 == null || o2 == null) {
			return false;
		}
		return o1 instanceof Number && o2 instanceof Number
				? ((Number) o1).doubleValue() == ((Number) o2).doubleValue()
				: o1.equals(o2);
	}
}
