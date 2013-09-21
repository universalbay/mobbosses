package com.universalbay.mobbosses.mob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.ItemStack;

import com.universalbay.mobbosses.MobBosses;
import com.universalbay.mobbosses.MobSwarmWorld;

public class MobSwarm {
	protected MobBosses plugin;
	protected List<SwarmEntity> entities = new ArrayList<SwarmEntity>();
	protected SwarmBossEntity boss;
	protected int swarmsize;
	protected EntityType swarmtype;
	protected HashMap<Player, Double> contributers = new HashMap<Player, Double>();
	protected MobSwarmWorld mobWorld = MobBosses.getMobWorld();
	
	public MobSwarm(EntityType type, int size, MobBosses mplugin) {
		this.plugin = mplugin;
		this.swarmsize = size;
		this.swarmtype = type;
		Location loc = this.newRandomLocation();
		this.spawnBossEntity(loc);
		for (int counter = 1;counter < this.swarmsize;counter++){
			this.spawnEntity(loc);
		}
		this.register();
		Bukkit.broadcastMessage(ChatColor.GOLD + "Quest: " + this.swarmtype + " Swarm at World: " + loc.getWorld().getName() + ", X: " + loc.getBlockX() + ", Y: " + loc.getBlockY() + ", Z: " + loc.getBlockZ() + "!");
	}
	
	public void addEntity(SwarmEntity addentity) {
		if (!this.entities.contains(addentity)) {
			this.entities.add(addentity);
			this.mobWorld.addEntity(addentity);
		}
		
	}
	
	public void removeEntity(SwarmEntity removeentity) {
		if (this.entities.contains(removeentity)) {
			this.entities.remove(removeentity);
			this.mobWorld.removeEntity(removeentity);
		}
	}
	
	public void setEntities(List<SwarmEntity> newentities) {
		this.entities = newentities;
	}
	
	public List<SwarmEntity> getEntities() {
		return this.entities;
	}
	
	public void setBoss(Creature swarmboss) {
		this.boss = new SwarmBossEntity(swarmboss, this);
	}
	
	public SwarmEntity getBoss() {
		return this.boss;
	}
	
	public HashMap<Player, Double> getContributers() {
		return this.contributers;
	}
	
	public void addContribution(Player player, double damage) {
		if (this.contributers.containsKey(player)) {
			this.contributers.put(player, this.contributers.get(player) + damage);
		}
		else {
			this.contributers.put(player, damage);
		}
	}
	
	public double getContribution(Player player) {
		return this.contributers.get(player);
	}
	
	public void setContribution(Player player, double damage) {
		this.contributers.put(player, damage);
	}
	
	public void removeContribution(Player player, double damage) {
		if (this.contributers.containsKey(player)) {
			double contribution = this.contributers.get(player);
			contribution -= damage;
			if (contribution < 1) {
				contribution = 0;
			}
			this.contributers.put(player, contribution);
		}
	}
	
	public void removeContributer(Player player) {
		if (this.contributers.containsKey(player)) {
			this.contributers.remove(player);
		}
	}
	
	public void reward() {
		for (Player player : this.contributers.keySet()) {
			int exp = (int) Math.round(this.contributers.get(player) * 0.1);
			player.giveExp(exp);
			player.sendMessage(ChatColor.GREEN + "You have been rewarded " + ChatColor.GOLD + exp + ChatColor.GREEN + " Exp for participating in a battle!");
		}
	}
	
	public void register() {
		if (!this.mobWorld.getSwarms().contains(this)) {
			this.mobWorld.addSwarm(this);
		}
	}
	
	public void unregister() {
		if (this.mobWorld.getSwarms().contains(this)) {
			this.mobWorld.removeSwarm(this);
		}
	}
	
	public SwarmEntity spawnEntity(Location loc) {
		Creature creature = (Creature) loc.getWorld().spawnEntity(loc, this.swarmtype);
		creature.setCustomName(creature.getType() + " - Minion");
		this.equip(creature);
		SwarmEntity entity = new SwarmEntity(creature, this);
		this.addEntity(entity);
		return entity;
	}
	
	public SwarmBossEntity spawnBossEntity(Location loc) {
		Creature creature = (Creature) loc.getWorld().spawnEntity(loc, this.swarmtype);
		creature.setMaxHealth(creature.getMaxHealth() * 2);
		creature.setHealth(creature.getHealth() * 2);
		creature.setCustomName(creature.getType() + " - Boss");
		this.equip(creature);
		SwarmBossEntity entity = new SwarmBossEntity(creature, this);
		this.addEntity(entity);
		return entity;
	}
	
	public Location newRandomLocation() {
		Player[] players = Bukkit.getOnlinePlayers();
		Location loc;
		if (Bukkit.getOnlinePlayers().length != 0) {
			loc = players[new Random().nextInt(players.length)].getLocation();
		}
		else {
			loc = new Location(Bukkit.getWorld("world"), 0, 0, 0);
		}
		loc.setX(loc.getX() + new Random().nextInt(50));
		loc.setZ(loc.getZ() + new Random().nextInt(50));
		return loc;
	}
	
	public void equip(Creature creature) {
		if (this.swarmtype == EntityType.SKELETON) {
			creature.getEquipment().setItemInHand(new ItemStack(Material.BOW, 1));
		}
		else if (this.swarmtype == EntityType.WOLF) {
			Wolf wolf = (Wolf) creature;
			wolf.setAngry(true);
			wolf.setMaxHealth(wolf.getMaxHealth() * 1.5);
			wolf.setHealth(wolf.getHealth() * 1.5);
		}
	}
}