package org.example.issuetracker.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
public class Project implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	@GeneratedValue
	private long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@OneToMany(mappedBy = "project")
	private List<Build> builds;
	
	@ManyToOne()
	@JoinColumn(name="MANAGER")
	private User manager;

	
	/**
	 * 
	 */
	public Project() {
		super();
	}

	/**
	 * @param id
	 */
	public Project(long id) {
		super();
		this.id = id;
	}

	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param builds
	 * @param manager
	 */
	public Project(long id, String name, String description, List<Build> builds, User manager) {
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
	public List<Build> getBuilds() {
		return builds;
	}

	/**
	 * @param builds
	 *            the build to set
	 */
	public void setBuilds(List<Build> builds) {
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
