package com.api;

public interface IScans {

	/**
	 * start task
	*/
	public void save();
	
	/**
	 * start task
	 */
	public void launch();

	/**
	 * stop task
	 */
	public void stop();
	
	/**
	 * status task
	 */
	public int status();

	/**
	 * restart task
	 */
	public void restart();

	/**
	 * del task
	 */
	public void trash();
	
	/**
	 * all task
	 */
	public void tasks();
}
