package com.universalbay.mobbosses.scheduler;

import org.bukkit.scheduler.BukkitRunnable;

import com.universalbay.mobbosses.mob.generator.RandomMobSwarm;

public class SpawnScheduler extends BukkitRunnable {
	
	public void run() {
		new RandomMobSwarm().newRandomMobSwarm();
	}

}
