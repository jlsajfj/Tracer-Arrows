package ca.tracerArrows.arrows;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

public class TAEvents implements Listener {
	private Map<Arrow, Double[]> arrows = new HashMap<Arrow, Double[]>();

	@EventHandler
	public void playerJoin(PlayerJoinEvent e) {
		if (Main.corrupt && e.getPlayer().hasPermission("ta.admin"))
			e.getPlayer().sendMessage(ChatColor.RED
					+ "Your TracerArrow config file is corrupt and should be regenerated! "
					+ "Regeneration is as easy as restarting the server! This is not manditory as there should be no negative "
					+ "side effects from a corrupt config!");
	}

	@EventHandler
	public void arrowShot(EntityShootBowEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		Player p = (Player) e.getEntity();
		Arrow arrow = (Arrow) e.getProjectile();
		if (p.hasPermission("ta.use")) {
			if (Main.laser)
				arrow.setVelocity(arrow.getVelocity().multiply(50));
			arrow.setGravity(Main.gravity);
		}
		Location l = p.getLocation();
		Double[] temp = { Double.valueOf(l.getX()), Double.valueOf(l.getY()), Double.valueOf(l.getZ()) };
		this.arrows.put(arrow, temp);
	}

	@EventHandler
	public void onLand(ProjectileHitEvent e) {
		if (!(e.getEntity() instanceof Arrow))
			return;
		Arrow arrow = (Arrow) e.getEntity();
		ProjectileSource shooter = arrow.getShooter();
		if (!(shooter instanceof Player))
			return;
		if (this.arrows.isEmpty())
			return;
		if (this.arrows.get(arrow) == null)
			return;
		double[] l = ArrayUtils.toPrimitive(this.arrows.get(arrow));
		Location aLoc = arrow.getLocation();
		Location loc = new Location(aLoc.getWorld(), aLoc.getX(), aLoc.getY(), aLoc.getZ());
		if (e.getHitEntity() != null) {
			loc = e.getHitEntity().getLocation();
			if (Main.ik && e.getHitEntity() instanceof Damageable) {
				Damageable d = (Damageable) e.getHitEntity();
				if (d instanceof Player) {
					Player p = (Player) d;
					if (!p.hasPermission("ta.i"))
						d.damage(100.0D, (Player) shooter);
				} else {
					d.damage(100.0D, (Player) shooter);
				}
			}
		}
		loc.setY(loc.getY() + 1.0D);
		Block b = e.getHitBlock();
		if (b != null)
			loc = new Location(arrow.getWorld(), b.getX(), b.getY(), b.getZ());
		if (Main.laser)
			this.arrows.remove(arrow);
		if (((Player) shooter).hasPermission("ta.use")) {
			Location end = new Location(loc.getWorld(), l[0], l[1] + 1.5, l[2]);
			Vector dir = end.subtract(loc).toVector();
			double len = dir.length();
			dir.normalize();
			for (double i = 0.0D; i < len; i += 0.5D) {
				Vector a = dir.clone();
				a.multiply(i);
				loc.add(a);
				for (Map.Entry<Particle, Integer> p : (Iterable<Map.Entry<Particle, Integer>>) Main.particles
						.entrySet()) {
					if (((Integer) p.getValue()).intValue() == 0)
						continue;
					arrow.getWorld().spawnParticle(p.getKey(), loc, ((Integer) p.getValue()).intValue());
				}
				loc.subtract(a);
			}
		}
		if (Main.ka)
			arrow.remove();
	}

	@EventHandler
	public void onInventoryClick(
			InventoryClickEvent event) {/*
										 * Player player = (Player)event.getWhoClicked(); ItemStack clicked =
										 * event.getCurrentItem(); Inventory inventory = event.getInventory(); if
										 * (GUI.allM.containsKey(inventory.getType().getDefaultTitle())) {
										 * event.setCancelled(true); if (clicked.equals(GUI.t)) { String iName =
										 * inventory.getName(); if (iName.equals(ChatColor.GOLD + "Laser state")) {
										 * Main.laser = true; } else if (iName.equals(ChatColor.GOLD + "Gravity state"))
										 * { Main.gravity = true; } else if (iName.equals(ChatColor.GOLD +
										 * "Instakill state")) { Main.ik = true; } player.closeInventory();
										 * player.sendMessage(String.valueOf(inventory.getName()) + ChatColor.GREEN +
										 * " has been set to true."); } else if (clicked.equals(GUI.f)) { String iName =
										 * inventory.getName(); if (iName.equals(ChatColor.GOLD + "Laser state")) {
										 * Main.laser = false; } else if (iName.equals(ChatColor.GOLD +
										 * "Gravity state")) { Main.gravity = false; } else if
										 * (iName.equals(ChatColor.GOLD + "Instakill state")) { Main.ik = false; }
										 * player.closeInventory();
										 * player.sendMessage(String.valueOf(inventory.getName()) + ChatColor.GREEN +
										 * " has been set to false."); } else { String iName = inventory.getName();
										 * player.closeInventory(); if (clicked.equals(GUI.b) &&
										 * (iName.equals(ChatColor.GOLD + "Laser state") || iName.equals(ChatColor.GOLD
										 * + "Gravity state") || iName.equals(ChatColor.GOLD + "Instakill state") ||
										 * iName.equals(ChatColor.GOLD + "Arrow Particles")))
										 * player.openInventory(GUI.main); } } else if
										 * (Arrays.<String>asList(Main.SPARTICLES).contains(inventory.getTitle())) {
										 * event.setCancelled(true); String iName = inventory.getName();
										 * player.closeInventory(); if (clicked.equals(GUI.b) &&
										 * (iName.equals(ChatColor.GOLD + "Laser state") || iName.equals(ChatColor.GOLD
										 * + "Gravity state") || iName.equals(ChatColor.GOLD + "Instakill state") ||
										 * iName.equals(ChatColor.GOLD + "Arrow Particles")))
										 * player.openInventory(GUI.particleM); }
										 */
	}
}
