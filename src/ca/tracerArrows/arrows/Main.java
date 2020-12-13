package ca.tracerArrows.arrows;

import ca.tracerArrows.arrows.Main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  public static final String[] SPARTICLES = new String[] { 
      "Barrier", "Cloud", "Crit", "Crit_Magic", "Damage_Indicator", 
      "Dragon_Breath", "Drip_Lava", "Drip_Water", "Enchantment_Table", "Explosion_Huge", 
      "Explosion_Large", 
      "Explosion_Normal", "Falling_Dust", "Flame", "Heart", "Lava", "Note", "Portal", "Redstone", 
      "Slime", "Smoke_Large", "Smoke_Normal", "Snow_Shovel", "Snowball", "Spell_Instant", "Sweep_Attack", 
      "Villager_Angry", "Water_Bubble", "Water_Drop", 
      "Water_Splash", "Water_Wake" };
  
  public static final Particle[] PARTICLES = new Particle[] { 
      Particle.BARRIER, Particle.CLOUD, Particle.CRIT, Particle.CRIT_MAGIC, 
      Particle.DAMAGE_INDICATOR, Particle.DRAGON_BREATH, Particle.DRIP_LAVA, Particle.DRIP_WATER, 
      Particle.ENCHANTMENT_TABLE, Particle.EXPLOSION_HUGE, 
      Particle.EXPLOSION_LARGE, Particle.EXPLOSION_NORMAL, 
      Particle.FALLING_DUST, Particle.FLAME, Particle.HEART, Particle.LAVA, Particle.NOTE, 
      Particle.PORTAL, Particle.REDSTONE, 
      Particle.SLIME, Particle.SMOKE_LARGE, Particle.SMOKE_NORMAL, 
      Particle.SNOW_SHOVEL, Particle.SNOWBALL, Particle.SPELL_INSTANT, Particle.SWEEP_ATTACK, 
      Particle.VILLAGER_ANGRY, Particle.WATER_BUBBLE, Particle.WATER_DROP, 
      Particle.WATER_SPLASH, 
      Particle.WATER_WAKE };
  
  public static Map<String, String> caseMap;
  
  public static Map<String, Particle> inputMap;
  
  public static Map<Particle, Integer> particles;
  
  public static boolean laser;
  
  public static boolean corrupt;
  
  public static boolean gravity;
  
  public static boolean ik;
  
  public static boolean ka;
  
  public static boolean cr;
  
  public static boolean creep;
  
  public static Server server;
  
  public void onEnable() {
    server = getServer();
    corrupt = false;
    caseMap = new HashMap<>();
    inputMap = new HashMap<>();
    particles = new HashMap<>();
    for (int i = 0; i < SPARTICLES.length; i++) {
      caseMap.put(SPARTICLES[i].toLowerCase(), SPARTICLES[i]);
      inputMap.put(SPARTICLES[i].toLowerCase(), PARTICLES[i]);
      particles.put(PARTICLES[i], Integer.valueOf(0));
    } 
    File dataFolder = getDataFolder();
    if (!dataFolder.exists())
      dataFolder.mkdir(); 
    File saveTo = new File(dataFolder, "config.txt");
    if (!saveTo.exists()) {
      File old = new File(dataFolder, "ArrowTypes.txt");
      if (old.exists()) {
        try {
          System.out.println("[TracerArrows] Config is outdated!");
          System.out.println("[TracerArrows] Updated config");
          BufferedReader read = new BufferedReader(new FileReader(old));
          String line;
          while ((line = read.readLine()) != null) {
            String[] raw = line.split(":");
            if (inputMap.containsKey(raw[0].toLowerCase())) {
              particles.put(inputMap.get(raw[0].toLowerCase()), Integer.valueOf(Math.max(Integer.parseInt(raw[1]), 0)));
              continue;
            } 
            if (raw[0].equalsIgnoreCase("laser")) {
              laser = (Integer.parseInt(raw[1]) != 0);
              continue;
            } 
            if (raw[0].equalsIgnoreCase("gravity")) {
              gravity = (Integer.parseInt(raw[1]) != 0);
              continue;
            } 
            if (raw[0].equalsIgnoreCase("instakill")) {
              ik = (Integer.parseInt(raw[1]) != 0);
              continue;
            } 
            if (raw[0].equalsIgnoreCase("killarrows")) {
              ka = (Integer.parseInt(raw[1]) != 0);
              continue;
            } 
            if (raw[0].equalsIgnoreCase("creeper")) {
              cr = (Integer.parseInt(raw[1]) != 0);
              continue;
            } 
            corrupt = true;
            System.out.println("[TracerArrows] Your config is corrupt! Please restart server.");
          } 
          read.close();
        } catch (IOException e) {
          e.printStackTrace();
        } 
        old.delete();
      } else {
        try {
          System.out.println("[TracerArrows] Arrow Config does not exist! Creating new file");
          saveTo.createNewFile();
          FileWriter fw = new FileWriter(saveTo, true);
          PrintWriter pw = new PrintWriter(fw);
          byte b;
          int j;
          String[] arrayOfString;
          for (j = (arrayOfString = SPARTICLES).length, b = 0; b < j; ) {
            String a = arrayOfString[b];
            pw.println(String.valueOf(a) + ":0");
            b++;
          } 
          pw.println("Laser:0");
          pw.println("Gravity:1");
          pw.println("Instakill:0");
          pw.println("KillArrows:0");
          pw.println("Creeper:0");
          laser = false;
          gravity = true;
          ik = false;
          ka = false;
          cr = false;
          pw.flush();
          pw.close();
        } catch (IOException e) {
          e.printStackTrace();
        } 
      } 
    } else {
      try {
        System.out.println("[TracerArrows] Reading from Arrow Config");
        BufferedReader read = new BufferedReader(new FileReader(saveTo));
        String line;
        while ((line = read.readLine()) != null) {
          String[] raw = line.split(":");
          if (inputMap.containsKey(raw[0].toLowerCase())) {
            particles.put(inputMap.get(raw[0].toLowerCase()), Integer.valueOf(Math.max(Integer.parseInt(raw[1]), 0)));
            continue;
          } 
          if (raw[0].equalsIgnoreCase("laser")) {
            laser = (Integer.parseInt(raw[1]) != 0);
            continue;
          } 
          if (raw[0].equalsIgnoreCase("gravity")) {
            gravity = (Integer.parseInt(raw[1]) != 0);
            continue;
          } 
          if (raw[0].equalsIgnoreCase("instakill")) {
            ik = (Integer.parseInt(raw[1]) != 0);
            continue;
          } 
          if (raw[0].equalsIgnoreCase("killarrows")) {
            ka = (Integer.parseInt(raw[1]) != 0);
            continue;
          } 
          if (raw[0].equalsIgnoreCase("creeper")) {
            cr = (Integer.parseInt(raw[1]) != 0);
            continue;
          } 
          corrupt = true;
          System.out.println("[TracerArrows] Your config is corrupt! Please restart server.");
        } 
        read.close();
      } catch (IOException e) {
        e.printStackTrace();
      } 
    } 
    getServer().getPluginManager().registerEvents((Listener)new TAEvents(), (Plugin)this);
    creep = false;
    if (getServer().getPluginManager().getPlugin("CreeperBombs") != null)
      creep = true; 
    getCommand("tac").setExecutor((CommandExecutor)new TACCommands());
    getCommand("taconfig").setExecutor((CommandExecutor)new TACCommands());
    getCommand("tac").setTabCompleter((TabCompleter)new TACTab());
    getCommand("taconfig").setTabCompleter((TabCompleter)new TACTab());
    //getCommand("tag").setExecutor((CommandExecutor)new TACCommands());
    //getCommand("tagui").setExecutor((CommandExecutor)new TACCommands());
  }
  
  public void onDisable() {
    File dataFolder = getDataFolder();
    File saveTo = new File(dataFolder, "config.txt");
    if (corrupt) {
      System.out.println("[TracerArrows] Config file is corrupt! Regenerating");
      try {
        if (!saveTo.delete())
          System.out.println(
              "[TracerArrows] Failed to remove config file! Please close any programs that may be using it."); 
        saveTo.createNewFile();
      } catch (IOException e) {
        System.out.println("[TracerArrows] Config file could not be regenerated! Here is the error:");
        e.printStackTrace();
      } 
    } 
    try {
      System.out.println("[TracerArrows] Writing to Arrow Config");
      FileWriter fw = new FileWriter(saveTo);
      PrintWriter pw = new PrintWriter(fw);
      byte b;
      int i;
      String[] arrayOfString;
      for (i = (arrayOfString = SPARTICLES).length, b = 0; b < i; ) {
        String a = arrayOfString[b];
        pw.println(String.valueOf(a) + ":" + particles.get(inputMap.get(a.toLowerCase())));
        b++;
      } 
      pw.println("Laser:" + (laser ? 1 : 0));
      pw.println("Gravity:" + (gravity ? 1 : 0));
      pw.println("Instakill:" + (ik ? 1 : 0));
      pw.println("KillArrows:" + (ka ? 1 : 0));
      pw.println("Creeper:" + (cr ? 1 : 0));
      pw.flush();
      pw.close();
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
}
