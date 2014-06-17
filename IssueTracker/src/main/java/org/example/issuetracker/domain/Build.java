package org.example.issuetracker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;



@Entity
@Table(name = "builds")
public class Build extends GenericDomainObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "NAME")
	private String name;
//	
//	@ManyToOne()
//	@JoinColumn(name = "PROJECTID")
//	private Project project;
	
	/**
	 * 
	 */
	public Build() {
		super();
	}
	
	/**
	 * @param id
	 */
	public Build(long id) {
		super(id);
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
//	@JsonIgnore
//	public Project getProject() {
//		return project;
//	}
//	/**
//	 * @param projectId the projectId to set
//	 */
//	public void setProject(Project project) {
//		this.project = project;
//	}
}
