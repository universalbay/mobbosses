package com.universalbay.mobbosses.mob.generator;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;

import com.universalbay.mobbosses.MobBosses;
import com.universalbay.mobbosses.mob.ArmoredMobSwarm;
import com.universalbay.mobbosses.mob.MobSwarm;

public class RandomMobSwarm {
	MobBosses plugin = Bukkit.getServer().getServicesManager().load(MobBosses.class);
	
	@SuppressWarnings("unused")
	public void newRandomMobSwarm() {
		EntityType type = this.newRandomEntityType();
		if (type == EntityType.SKELETON || type == EntityType.ZOMBIE) {
			ArmoredMobSwarm mobSwarm = new ArmoredMobSwarm(newRandomEntityType(), new Random().nextInt(6) + 5, plugin);
		}
		else {
			MobSwarm mobSwarm = new MobSwarm(newRandomEntityType(), new Random().nextInt(6) + 5, plugin);
		}
	}
	
	public EntityType newRandomEntityType() {
		Random random = new Random();
		
		switch (random.nextInt(8) + 1) {
		    default: return EntityType.CREEPER;
		    case 1:  return EntityType.CAVE_SPIDER;
		    case 2:  return EntityType.ENDERMAN;
		    case 3:  return EntityType.SILVERFISH;
		    case 4:  return EntityType.SKELETON;
		    case 5:  return EntityType.SPIDER;
		    case 6:  return EntityType.WITCH;
		    case 7:  return EntityType.ZOMBIE;
		    case 8:  return EntityType.WOLF;
		}
	}
}
