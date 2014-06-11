package org.example.issuetracker.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "builds")
public class Build implements Serializable{
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
	
	@ManyToOne()
	@JoinColumn(name = "PROJECTID")
	private Project project;
	
	/**
	 * 
	 */
	public Build() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param id
	 */
	public Build(long id) {
		super();
		this.id = id;
	}

	/**
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the projectId
	 */
	public Project getProject() {
		return project;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id).append(";");
		if (name != null)
			builder.append(name).append(";");
		if (project != null)
			builder.append(project);
		return builder.toString();
	}
}
