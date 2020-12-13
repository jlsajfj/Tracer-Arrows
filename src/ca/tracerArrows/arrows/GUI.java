package ca.tracerArrows.arrows;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI {
  public static Inventory main;
  
  public static Inventory particleM;
  
  public static Inventory lState;
  
  public static Inventory gState;
  
  public static Inventory iState;
  
  public static Map<String, Inventory> particles;
  
  public static Map<String, Inventory> allM;
  
  public static ItemStack t;
  
  public static ItemStack f;
  
  public static ItemStack b;
  
  public static ItemStack z = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)0);
  
  static {
    ItemMeta tMeta = z.getItemMeta();
    tMeta.setDisplayName("Zero");
    z.setItemMeta(tMeta);
  }
  
  public static ItemStack on = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4);
  
  static {
    tMeta = on.getItemMeta();
    tMeta.setDisplayName(ChatColor.RED + "Subtract One");
    on.setItemMeta(tMeta);
  }
  
  public static ItemStack op = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)3);
  
  static {
    tMeta = op.getItemMeta();
    tMeta.setDisplayName(ChatColor.BLUE + "Add One");
    op.setItemMeta(tMeta);
  }
  
  public static ItemStack fn = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)1);
  
  static {
    tMeta = fn.getItemMeta();
    tMeta.setDisplayName(ChatColor.RED + "Subtract Five");
    fn.setItemMeta(tMeta);
  }
  
  public static ItemStack fp = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)9);
  
  static {
    tMeta = fp.getItemMeta();
    tMeta.setDisplayName(ChatColor.BLUE + "Add Five");
    fp.setItemMeta(tMeta);
  }
  
  public static ItemStack tn = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4);
  
  static {
    tMeta = tn.getItemMeta();
    tMeta.setDisplayName(ChatColor.RED + "Subtract Ten");
    tn.setItemMeta(tMeta);
  }
  
  public static ItemStack tp = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)3);
  
  static {
    tMeta = tp.getItemMeta();
    tMeta.setDisplayName(ChatColor.BLUE + "Add Ten");
    tp.setItemMeta(tMeta);
    t = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
    tMeta = t.getItemMeta();
    tMeta.setDisplayName(ChatColor.GREEN + "True");
    t.setItemMeta(tMeta);
    f = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
    tMeta = f.getItemMeta();
    tMeta.setDisplayName(ChatColor.RED + "False");
    f.setItemMeta(tMeta);
    b = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
    tMeta = b.getItemMeta();
    tMeta.setDisplayName(ChatColor.DARK_RED + "Back");
    b.setItemMeta(tMeta);
    particles = new HashMap<>();
    for (Map.Entry<String, Particle> p : (Iterable<Map.Entry<String, Particle>>)Main.inputMap.entrySet()) {
      String name = (String)Main.caseMap.get(p.getKey());
      Inventory inventory = Bukkit.createInventory(null, InventoryType.DISPENSER, name);
      inventory.setItem(0, on);
      inventory.setItem(1, z);
      inventory.setItem(2, op);
      inventory.setItem(3, fn);
      inventory.setItem(5, fp);
      inventory.setItem(6, tn);
      inventory.setItem(7, b);
      inventory.setItem(8, tp);
      particles.put(name, inventory);
    } 
    particleM = Bukkit.createInventory(null, 36, "Particles");
    ItemStack[] tfb = { t, t, t, t, b, f, f, f, f };
    particleM.setItem(0, b);
    ItemStack temp = new ItemStack(Material.BARRIER, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Barrier");
    temp.setItemMeta(tMeta);
    particleM.setItem(1, temp);
    temp = new ItemStack(Material.CONCRETE_POWDER, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Cloud");
    temp.setItemMeta(tMeta);
    particleM.setItem(2, temp);
    temp = new ItemStack(Material.NETHER_STAR, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Crit");
    temp.setItemMeta(tMeta);
    particleM.setItem(3, temp);
    temp = new ItemStack(Material.DIAMOND, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Crit_Magic");
    temp.setItemMeta(tMeta);
    particleM.setItem(4, temp);
    temp = new ItemStack(Material.BEETROOT_SOUP, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Damage_Indicator");
    temp.setItemMeta(tMeta);
    particleM.setItem(5, temp);
    temp = new ItemStack(Material.SKULL_ITEM, 1, (short)5);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Dragon_Breath");
    temp.setItemMeta(tMeta);
    particleM.setItem(6, temp);
    temp = new ItemStack(Material.LAVA_BUCKET, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Drip_Lava");
    temp.setItemMeta(tMeta);
    particleM.setItem(7, temp);
    particleM.setItem(8, b);
    temp = new ItemStack(Material.WATER_BUCKET, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Drip_Water");
    temp.setItemMeta(tMeta);
    particleM.setItem(9, temp);
    temp = new ItemStack(Material.ENCHANTMENT_TABLE, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Enchantment_Table");
    temp.setItemMeta(tMeta);
    particleM.setItem(10, temp);
    temp = new ItemStack(Material.TNT, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Explosion_Huge");
    temp.setItemMeta(tMeta);
    particleM.setItem(11, temp);
    temp = new ItemStack(Material.TNT, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Explosion_Large");
    temp.setItemMeta(tMeta);
    particleM.setItem(12, temp);
    temp = new ItemStack(Material.TNT, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Explosion_Normal");
    temp.setItemMeta(tMeta);
    particleM.setItem(13, temp);
    temp = new ItemStack(Material.SAND, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Falling_Dust");
    temp.setItemMeta(tMeta);
    particleM.setItem(14, temp);
    temp = new ItemStack(Material.FIRE, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Flame");
    temp.setItemMeta(tMeta);
    particleM.setItem(15, temp);
    temp = new ItemStack(Material.LEATHER_BOOTS, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Footstep");
    temp.setItemMeta(tMeta);
    particleM.setItem(16, temp);
    temp = new ItemStack(Material.BEETROOT_SOUP, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Heart");
    temp.setItemMeta(tMeta);
    particleM.setItem(17, temp);
    temp = new ItemStack(Material.LAVA, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Lava");
    temp.setItemMeta(tMeta);
    particleM.setItem(18, temp);
    temp = new ItemStack(Material.NOTE_BLOCK, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Note");
    temp.setItemMeta(tMeta);
    particleM.setItem(19, temp);
    temp = new ItemStack(Material.PORTAL, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Portal");
    temp.setItemMeta(tMeta);
    particleM.setItem(20, temp);
    temp = new ItemStack(Material.REDSTONE, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Redstone");
    temp.setItemMeta(tMeta);
    particleM.setItem(21, temp);
    temp = new ItemStack(Material.SLIME_BALL, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Slime");
    temp.setItemMeta(tMeta);
    particleM.setItem(22, temp);
    temp = new ItemStack(Material.FURNACE, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Smoke_Large");
    temp.setItemMeta(tMeta);
    particleM.setItem(23, temp);
    temp = new ItemStack(Material.TORCH, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Smoke_Normal");
    temp.setItemMeta(tMeta);
    particleM.setItem(24, temp);
    temp = new ItemStack(Material.IRON_SPADE, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Snow_Shovel");
    temp.setItemMeta(tMeta);
    particleM.setItem(25, temp);
    temp = new ItemStack(Material.SNOW_BALL, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Snowball");
    temp.setItemMeta(tMeta);
    particleM.setItem(26, temp);
    particleM.setItem(27, b);
    temp = new ItemStack(Material.LINGERING_POTION, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Spell_Instant");
    temp.setItemMeta(tMeta);
    particleM.setItem(28, temp);
    temp = new ItemStack(Material.IRON_SWORD, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Sweep_Attack");
    temp.setItemMeta(tMeta);
    particleM.setItem(29, temp);
    temp = new ItemStack(Material.MONSTER_EGG, 1, (short)120);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Villager_Angry");
    temp.setItemMeta(tMeta);
    particleM.setItem(30, temp);
    temp = new ItemStack(Material.ENDER_PEARL, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Water_Bubble");
    temp.setItemMeta(tMeta);
    particleM.setItem(31, temp);
    temp = new ItemStack(Material.INK_SACK, 1, (short)4);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Water_Drop");
    temp.setItemMeta(tMeta);
    particleM.setItem(32, temp);
    temp = new ItemStack(Material.INK_SACK, 1, (short)6);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Water_Splash");
    temp.setItemMeta(tMeta);
    particleM.setItem(33, temp);
    temp = new ItemStack(Material.FISHING_ROD, 1);
    tMeta = temp.getItemMeta();
    tMeta.setDisplayName("Water_Wake");
    temp.setItemMeta(tMeta);
    particleM.setItem(34, temp);
    particleM.setItem(35, b);
    allM = new HashMap<>();
    allM.put("Particles", particleM);
    main = Bukkit.createInventory(null, 9, ChatColor.BLUE + "Tracer Arrow Config");
    allM.put(ChatColor.BLUE + "Tracer Arrow Config", main);
    lState = Bukkit.createInventory(null, 9, ChatColor.YELLOW + "Laser state");
    lState.setContents(tfb);
    allM.put(ChatColor.YELLOW + "Laser state", lState);
    gState = Bukkit.createInventory(null, 9, ChatColor.GOLD + "Gravity state");
    gState.setContents(tfb);
    allM.put(ChatColor.GOLD + "Gravity state", gState);
    iState = Bukkit.createInventory(null, 9, ChatColor.GOLD + "Instakill state");
    iState.setContents(tfb);
    allM.put(ChatColor.GOLD + "Instakill state", iState);
  }
}
