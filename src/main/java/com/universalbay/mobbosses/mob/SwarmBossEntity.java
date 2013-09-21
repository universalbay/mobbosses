package com.universalbay.mobbosses.mob;

import org.bukkit.entity.Creature;

import com.universalbay.mobbosses.mob.SwarmEntity;

public class SwarmBossEntity extends SwarmEntity {

	public SwarmBossEntity(Creature entity, MobSwarm swarm) {
		super(entity, swarm);
		this.isBoss = true;
	}

}
