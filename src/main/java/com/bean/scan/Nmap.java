package com.bean.scan;

public class Nmap{
	// {"name":"test", "description":"", "target":"172.16.80.1/24", "ports":"445", "option":[]}
	private String name;
	private String description;
	private String target;
	private String ports;
	private String[] option;

	public Nmap(String name, String description, String target, String ports, String[] option) {
		super();
		this.name = name;
		this.description = description;
		this.target = target;
		this.ports = ports;
		this.option = option;
	}

	public Nmap() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getPorts() {
		return ports;
	}

	public void setPorts(String ports) {
		this.ports = ports;
	}

	public String[] getOption() {
		return option;
	}

	public void setOption(String[] option) {
		this.option = option;
	}

}
