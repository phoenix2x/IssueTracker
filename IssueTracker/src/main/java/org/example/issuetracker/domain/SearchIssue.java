package org.example.issuetracker.domain;

public class SearchIssue {
	private String createdByEmail;
	private String modifiedByEmail;
	private String summary;
	private String description;
	private String status;
	private String resolution;
	private String type;
	private String priority;
	private String projectName;
	private String assigneeEmail;

	/**
	 * @return the createdByEmail
	 */
	public String getCreatedByEmail() {
		return createdByEmail;
	}

	/**
	 * @param createdByEmail
	 *            the createdByEmail to set
	 */
	public void setCreatedByEmail(String createdByEmail) {
		this.createdByEmail = createdByEmail;
	}

	/**
	 * @return the modifiedByEmail
	 */
	public String getModifiedByEmail() {
		return modifiedByEmail;
	}

	/**
	 * @param modifiedByEmail
	 *            the modifiedByEmail to set
	 */
	public void setModifiedByEmail(String modifiedByEmail) {
		this.modifiedByEmail = modifiedByEmail;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the resolution
	 */
	public String getResolution() {
		return resolution;
	}

	/**
	 * @param resolution
	 *            the resolution to set
	 */
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName
	 *            the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the assigneeEmail
	 */
	public String getAssigneeEmail() {
		return assigneeEmail;
	}

	/**
	 * @param assigneeEmail
	 *            the assigneeEmail to set
	 */
	public void setAssigneeEmail(String assigneeEmail) {
		this.assigneeEmail = assigneeEmail;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (createdByEmail != null)
			builder.append(createdByEmail).append(";");
		if (modifiedByEmail != null)
			builder.append(modifiedByEmail).append(";");
		if (summary != null)
			builder.append(summary).append(";");
		if (description != null)
			builder.append(description).append(";");
		if (status != null)
			builder.append(status).append(";");
		if (resolution != null)
			builder.append(resolution).append(";");
		if (type != null)
			builder.append(type).append(";");
		if (priority != null)
			builder.append(priority).append(";");
		if (projectName != null)
			builder.append(projectName).append(";");
		if (assigneeEmail != null)
			builder.append(assigneeEmail);
		return builder.toString();
	}
}
