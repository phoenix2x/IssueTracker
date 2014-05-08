package com.epam.issuetracker.beans;

import java.util.List;

public class Project {
	private final long id;
	private String name;
	private String description;
	private List<String> builds;
	private User manager;

	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param builds
	 * @param manager
	 */
	public Project(long id, String name, String description, List<String> builds, User manager) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.builds = builds;
		this.manager = manager;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the builds
	 */
	public List<String> getBuilds() {
		return builds;
	}

	/**
	 * @param builds
	 *            the build to set
	 */
	public void setBuilds(List<String> builds) {
		this.builds = builds;
	}

	/**
	 * @return the manager
	 */
	public User getManager() {
		return manager;
	}

	/**
	 * @param manager
	 *            the manager to set
	 */
	public void setManager(User manager) {
		this.manager = manager;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id).append(";");
		if (name != null)
			builder.append(name).append(";");
		if (description != null)
			builder.append(description).append(";");
		if (builds != null)
			builder.append(builds).append(";");
		if (manager != null)
			builder.append(manager);
		return builder.toString();
	}
}
