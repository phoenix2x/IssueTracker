package org.example.issuetracker.model.beans;

public class Build {
	private long id;
	private String name;
	private long projectId;
	
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
	 * @param id
	 * @param name
	 * @param projectId
	 */
	public Build(long id, String name, long projectId) {
		super();
		this.id = id;
		this.name = name;
		this.projectId = projectId;
	}
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
	public long getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(long projectId) {
		this.projectId = projectId;
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
		builder.append(projectId);
		return builder.toString();
	}
}
