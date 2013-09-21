package com.universalbay.mobbosses;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import com.universalbay.mobbosses.listener.EntityListener;
import com.universalbay.mobbosses.scheduler.SpawnScheduler;

public class MobBosses extends JavaPlugin {
	private static MobSwarmWorld mobWorld = new MobSwarmWorld();
	
	@SuppressWarnings("unused")
	public void onEnable() {
		this.getLogger().info("Loading Configuration File...");
		saveDefaultConfig();
		this.getLogger().info("Enabling Listeners...");
		this.getServer().getPluginManager().registerEvents(new EntityListener(), this);
		this.getLogger().info("Starting Schedulers...");
		BukkitTask task = new SpawnScheduler().runTaskTimer(this, 20, getConfig().getInt("mobSpawnDelay") * 20);
	}
	
	public static MobSwarmWorld getMobWorld() {
		return mobWorld;
	}
}
