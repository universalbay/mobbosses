package com.universalbay.mobbosses.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.universalbay.mobbosses.mob.MobSwarm;

public class MobSwarmDeathEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	String message;
	MobSwarm swarm;
	
	public MobSwarmDeathEvent(MobSwarm mobswarm) {
		swarm = mobswarm;
		message = "";
	}
	
	public MobSwarmDeathEvent(String eventmessage, MobSwarm mobswarm) {
		message = eventmessage;
		swarm = mobswarm;
		swarm.register();
	}
	
	public MobSwarm getMobSwarm() {
		return swarm;
	}
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public HandlerList getHandlerList() {
		return handlers;
	}
	
	public void setCancelled(boolean cancelled) {
		if (cancelled == true) {
			swarm.unregister();
		}
		else {
			swarm.register();
		}
	}

}
