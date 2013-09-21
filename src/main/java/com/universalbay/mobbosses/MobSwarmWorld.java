package com.universalbay.mobbosses;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Creature;

import com.universalbay.mobbosses.mob.MobSwarm;
import com.universalbay.mobbosses.mob.SwarmEntity;

public class MobSwarmWorld {
	private List<SwarmEntity> entityList;
	private List<MobSwarm> swarmList;
	
	public MobSwarmWorld() {
		this.entityList = new ArrayList<SwarmEntity>();
		this.swarmList = new ArrayList<MobSwarm>();
	}
	
	public void addSwarm(MobSwarm swarm) {
		this.swarmList.add(swarm);
	}
	
	public void removeSwarm(MobSwarm swarm) {
		this.swarmList.remove(swarm);
	}
	
	public void addEntity(SwarmEntity entity) {
		this.entityList.add(entity);
	}
	
	public void removeEntity(SwarmEntity entity) {
		this.entityList.remove(entity);
	}
	
	public List<MobSwarm> getSwarms() {
		return this.swarmList;
	}
	
	public List<SwarmEntity> getEntities() {
		return this.entityList;
	}
	
	public boolean isRegistered(Creature entity) {
		if (this.getEntityByInput(entity) != null) {
			return true;
		}
		return false;
	}
	
	public SwarmEntity getEntityByInput(Creature entity) {
		for (SwarmEntity swarmEntity : this.entityList) {
			if (swarmEntity.getEntity().equals(entity)) {
				return swarmEntity;
			}
		}
		return null;
	}
}
