package com.universalbay.mobbosses.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import com.universalbay.mobbosses.MobBosses;
import com.universalbay.mobbosses.MobSwarmWorld;
import com.universalbay.mobbosses.event.MobSwarmDeathEvent;
import com.universalbay.mobbosses.mob.MobSwarm;
import com.universalbay.mobbosses.mob.SwarmBossEntity;
import com.universalbay.mobbosses.mob.SwarmEntity;

public class EntityListener implements Listener {
	private MobSwarmWorld mobWorld = MobBosses.getMobWorld();
	
	@EventHandler
	public void entityDeath(EntityDeathEvent event) {
		Entity killed = event.getEntity();
		Entity killer = null;
		if (event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent) {
			EntityDamageByEntityEvent damageEvent = (EntityDamageByEntityEvent) event.getEntity().getLastDamageCause();
			killer = damageEvent.getDamager();
		}
		if (killed instanceof Creature) {
			Creature entity = (Creature) event.getEntity();
			if (mobWorld.isRegistered(entity)) {
				SwarmEntity swarmEntity = mobWorld.getEntityByInput(entity);
				MobSwarm swarm = swarmEntity.getMobSwarm();
				swarm.removeEntity(swarmEntity);
				if (swarmEntity instanceof SwarmBossEntity) {
					event.setDroppedExp(event.getDroppedExp() * 2);
				}
				if (swarm.getEntities().size() < 1) {
					swarm.unregister();
					swarm.reward();
					if (killer != null && killer instanceof Player) {
						Player player = (Player) killer;
						Bukkit.getServer().getPluginManager().callEvent(new MobSwarmDeathEvent(swarm));
						player.sendMessage(ChatColor.GREEN + "You have defeated a MobSwarm!");
					}
				}
			}
		}
	}
	
	@EventHandler
	public void entityDamage(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			if (event.getEntity() instanceof Creature) {
				Creature creature = (Creature) event.getEntity();
				if (this.mobWorld.isRegistered(creature)) {
					MobSwarm swarm = mobWorld.getEntityByInput(creature).getMobSwarm();
					swarm.addContribution(player, event.getDamage());
				}
			}
			
		}
	}
}
