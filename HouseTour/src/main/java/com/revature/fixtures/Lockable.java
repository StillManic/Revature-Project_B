package com.revature.fixtures;

public interface Lockable {
	void unlock();
	boolean lock();
	boolean isLocked();
}
