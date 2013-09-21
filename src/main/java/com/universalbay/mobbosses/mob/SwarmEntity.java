package com.universalbay.mobbosses.mob;

import org.bukkit.entity.Creature;

public class SwarmEntity {
	protected MobSwarm mobSwarm;
	protected Creature swarmEntity;
	protected boolean isBoss;
	
	public SwarmEntity(Creature entity, MobSwarm swarm) {
		mobSwarm = swarm;
		swarmEntity = entity;
		isBoss = false;
	}
	
	public boolean isInMobSwarm() {
		if (mobSwarm != null) {
			return true;
		}
		return false;
	}
	
	public boolean isBoss() {
		return isBoss;
	}
	
	public MobSwarm getMobSwarm() {
		return mobSwarm;
	}
	
	public Creature getEntity() {
		return swarmEntity;
	}
}
