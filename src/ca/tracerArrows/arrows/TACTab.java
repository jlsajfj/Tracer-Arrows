package ca.tracerArrows.arrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

public class TACTab implements TabCompleter {
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    if (Main.inputMap.containsKey(args[0]) || args[0].equalsIgnoreCase("list"))
      return new ArrayList<>(); 
    if (args[0].equalsIgnoreCase("laser") || args[0].equalsIgnoreCase("gravity") || 
      args[0].equalsIgnoreCase("instakill") || args[0].equalsIgnoreCase("creeper")) {
      String[] temp = { "True", "False" };
      return new ArrayList<>(Arrays.asList(temp));
    }
    List<String> completions = new ArrayList<>(Arrays.asList(Main.SPARTICLES));
    completions.add("List");
    completions.add("Laser");
    completions.add("Gravity");
    completions.add("Instakill");
    completions.add("KillArrows");
    completions.add("Creeper");
    completions.add("Speed");
    List<String> a = new ArrayList<>();
    StringUtil.copyPartialMatches(args[0], completions, a);
    Collections.sort(a);
    return a;
  }
}
