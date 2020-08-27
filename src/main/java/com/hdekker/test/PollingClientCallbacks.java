package com.hdekker.test;

public class PollingClientCallbacks{
	
	final Runnable canReach;
	final Runnable degraded; 
	final Runnable unreachable;
	
	public PollingClientCallbacks(Runnable canReach, Runnable degraded, Runnable unreachable) {
		super();
		this.canReach = canReach;
		this.degraded = degraded;
		this.unreachable = unreachable;
	}

	public Runnable getCanReach() {
		return canReach;
	}

	public Runnable getDegraded() {
		return degraded;
	}

	public Runnable getUnreachable() {
		return unreachable;
	}
	
}