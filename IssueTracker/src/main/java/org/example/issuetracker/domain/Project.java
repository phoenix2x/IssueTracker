package org.example.issuetracker.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "projects")
public class Project extends GenericDomainObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "NAME")
	@Size(min = 1, message = "Name requred")
	private String name;
	
	@Column(name = "DESCRIPTION")
	@Size(min = 1, message = "Description requred")
	private String description;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<Build> builds;
	
//	@OneToMany(fetch = FetchType.EAGER)
//	@JoinColumn(name = "projectid", referencedColumnName = "id", updatable = false)
//	private List<Build> builds;
	
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
		super(id);
	}

//	@PrePersist
//	@PreUpdate
//	public void setBuildThis() {
//		for (Build build: builds) {
//			build.setProject(this);
//		}
//	}
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

}
