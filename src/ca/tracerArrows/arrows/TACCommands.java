package ca.tracerArrows.arrows;

import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TACCommands implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("tac") || label.equalsIgnoreCase("taconfig")) {
			if (args.length == 2) {
				if (Main.inputMap.containsKey(args[0].toLowerCase()))
					try {
						int a = Integer.parseInt(args[1]);
						if (a < 0) {
							sender.sendMessage(ChatColor.RED + "Please enter a postitive number!");
							return true;
						}
						Main.particles.put((Particle) Main.inputMap.get(args[0].toLowerCase()), Integer.valueOf(a));
						sender.sendMessage(ChatColor.GREEN + "Updated particle "
								+ (String) Main.caseMap.get(args[0].toLowerCase()) + " to " + args[1] + " particles!");
						return true;
					} catch (NumberFormatException e) {
						sender.sendMessage(ChatColor.RED + "Please enter a number for particle count!");
						return true;
					}
				if (args[0].equalsIgnoreCase("laser")) {
					if (args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("t")) {
						Main.laser = true;
						sender.sendMessage(ChatColor.GREEN + "Lasers have been enabled");
						return true;
					}
					if (args[1].equalsIgnoreCase("false") || args[1].equalsIgnoreCase("f")) {
						Main.laser = false;
						sender.sendMessage(ChatColor.GREEN + "Lasers have been disabled");
						return true;
					}
				} else if (args[0].equalsIgnoreCase("gravity") || args[0].equalsIgnoreCase("g")) {
					if (args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("t")) {
						Main.gravity = true;
						sender.sendMessage(ChatColor.GREEN + "Gravity has been enabled");
						return true;
					}
					if (args[1].equalsIgnoreCase("false") || args[1].equalsIgnoreCase("f")) {
						Main.gravity = false;
						sender.sendMessage(ChatColor.GREEN + "Gravity has been disabled");
						return true;
					}
				} else if (args[0].equalsIgnoreCase("instakill") || args[0].equalsIgnoreCase("ik")) {
					if (args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("t")) {
						Main.ik = true;
						sender.sendMessage(ChatColor.GREEN + "Arrows now instakill");
						return true;
					}
					if (args[1].equalsIgnoreCase("false") || args[1].equalsIgnoreCase("f")) {
						Main.ik = false;
						sender.sendMessage(ChatColor.GREEN + "Arrows no long instakill");
						return true;
					}
				} else if (args[0].equalsIgnoreCase("killarrows") || args[0].equalsIgnoreCase("ka")) {
					if (args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("t")) {
						Main.ka = true;
						sender.sendMessage(ChatColor.GREEN + "Arrows now disappear");
						return true;
					}
					if (args[1].equalsIgnoreCase("false") || args[1].equalsIgnoreCase("f")) {
						Main.ka = false;
						sender.sendMessage(ChatColor.GREEN + "Arrows no long disappear");
						return true;
					}
				} else if (args[0].equalsIgnoreCase("creeper") || args[0].equalsIgnoreCase("cr")) {
					if (args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("t")) {
						Main.cr = true;
						sender.sendMessage(ChatColor.GREEN + "Arrows now cause Creeper Bombs");
						return true;
					}
					if (args[1].equalsIgnoreCase("false") || args[1].equalsIgnoreCase("f")) {
						Main.cr = false;
						sender.sendMessage(ChatColor.GREEN + "Arrows no long cause Creeper Bombs");
						return true;
					}
				} else if (args[0].equalsIgnoreCase("gui")) {
					if (args[1].equalsIgnoreCase("laser") || args[1].equalsIgnoreCase("l")) {
						((Player) sender).openInventory(GUI.lState);
						return true;
					}
					if (args[1].equalsIgnoreCase("instakill") || args[1].equalsIgnoreCase("i")
							|| args[1].equalsIgnoreCase("ik")) {
						((Player) sender).openInventory(GUI.iState);
						return true;
					}
					if (args[1].equalsIgnoreCase("gravity") || args[1].equalsIgnoreCase("g")) {
						((Player) sender).openInventory(GUI.gState);
						return true;
					}
					if (args[1].equalsIgnoreCase("particles") || args[1].equalsIgnoreCase("p")) {
						((Player) sender).openInventory(GUI.particleM);
						return true;
					}
				} else if (args[0].equalsIgnoreCase("speed") || args[0].equalsIgnoreCase("s")) {
					Main.multiplier=Double.parseDouble(args[1]);
					sender.sendMessage(ChatColor.GREEN + "Speed set to "+ Main.multiplier);
					return true;
				}
			}
			if (args.length == 1) {
				if (Main.inputMap.containsKey(args[0].toLowerCase())) {
					sender.sendMessage(ChatColor.GREEN + "The particle "
							+ (String) Main.caseMap.get(args[0].toLowerCase()) + " is currently set to "
							+ Main.particles.get(Main.inputMap.get(args[0].toLowerCase())));
					return true;
				}
				if (args[0].equalsIgnoreCase("list") || args[0].equalsIgnoreCase("l")) {
					sender.sendMessage(ChatColor.GREEN + "Your active particles are:");
					boolean inactive = true;
					for (Map.Entry<String, Particle> p : (Iterable<Map.Entry<String, Particle>>) Main.inputMap
							.entrySet()) {
						int a = ((Integer) Main.particles.get(p.getValue())).intValue();
						if (a != 0) {
							sender.sendMessage(ChatColor.GOLD + (String) Main.caseMap.get(p.getKey()) + ": "
									+ ChatColor.RESET + a);
							inactive = false;
						}
					}
					if (inactive)
						sender.sendMessage(ChatColor.RED + "You have no active particles!");
					return true;
				}
				if (args[0].equalsIgnoreCase("laser") || args[0].equalsIgnoreCase("l")) {
					sender.sendMessage(ChatColor.GREEN + "The laser is "
							+ (Main.laser ? (ChatColor.DARK_BLUE + "Active") : (ChatColor.DARK_RED + "Inactive"))
							+ ChatColor.GREEN + ".");
					return true;
				}
				if (args[0].equalsIgnoreCase("gravity") || args[0].equalsIgnoreCase("g")) {
					sender.sendMessage(
							ChatColor.GREEN + "Arrows fly " + (Main.gravity ? (ChatColor.DARK_BLUE + "with gravity")
									: (ChatColor.DARK_RED + "without gravity")) + ChatColor.GREEN + ".");
					return true;
				}
				if (args[0].equalsIgnoreCase("creeper") || args[0].equalsIgnoreCase("cr")) {
					sender.sendMessage(ChatColor.GREEN + "Arrows "
							+ (Main.gravity ? (ChatColor.DARK_BLUE + "will") : (ChatColor.DARK_RED + "will not"))
							+ ChatColor.GREEN + " create creeper bombs.");
					return true;
				}
				if (args[0].equalsIgnoreCase("instakill") || args[0].equalsIgnoreCase("ik")) {
					sender.sendMessage(ChatColor.GREEN + "Arrows " + (Main.ik ? (ChatColor.DARK_RED + "kill instantly")
							: (ChatColor.DARK_BLUE + "do not kill instantly")) + ChatColor.GREEN + ".");
					return true;
				}
				if (args[0].equalsIgnoreCase("speed") || args[0].equalsIgnoreCase("s")) {
					sender.sendMessage(ChatColor.GREEN + "Arrow speed is set to "+Main.multiplier+".");
					return true;
				}
				if (args[0].equalsIgnoreCase("gui")) {
					((Player) sender).openInventory(GUI.main);
					return true;
				}
			}
			if (args.length == 0) {
				sender.sendMessage(ChatColor.YELLOW + "--- " + ChatColor.BLUE + "Tracer Arrows " + ChatColor.RESET
						+ "by " + ChatColor.RED + "Jlsajfj!" + ChatColor.YELLOW + " ---");
				sender.sendMessage("For laser state, do " + ChatColor.GOLD + "/TAConfig Laser");
				sender.sendMessage("For gravity state, do " + ChatColor.GOLD + "/TAConfig Gravity");
				sender.sendMessage("For creeper bomb state, do " + ChatColor.GOLD + "/TAConfig creeper");
				sender.sendMessage("For active particles, do " + ChatColor.GOLD + "/TAConfig List");
				sender.sendMessage(
						"To disable/enable laser arrows, do " + ChatColor.GOLD + "/TAConfig Laser <True|False>");
				sender.sendMessage(
						"To check a specific particle count, do " + ChatColor.GOLD + "/TAConfig <Particle Name>");
				sender.sendMessage(
						"To modify a particle, do " + ChatColor.GOLD + "/TAConfig <Particle Name> <Particle Count>");
				sender.sendMessage(
						"To disable/enable gravity on arrows, do " + ChatColor.GOLD + "/TAConfig Gravity <True|False>");
				sender.sendMessage("To disable/enable creeper bombs on arrows, do " + ChatColor.GOLD
						+ "/TAConfig Creeper <True|False>");
				return true;
			}
		}
		if (label.equalsIgnoreCase("tag") || label.equalsIgnoreCase("tagui"))

		{
			((Player) sender).openInventory(GUI.main);
			return true;
		}
		return false;
	}
}
