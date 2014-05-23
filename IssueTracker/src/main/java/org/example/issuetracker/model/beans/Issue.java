package org.example.issuetracker.model.beans;

import java.sql.Date;
import java.util.List;


public class Issue {
	private long id;
	private Date createDate;
	private User createdBy;
	private Date modifyDate;
	private User modifiedBy;
	private String summary;
	private String description;
	private Status status;
	private String resolution;
	private String type;
	private String priority;
	private Project project;
	private Build buildFound;
	private User assignee;
	private List<Comment> comments;
	private List<Attachment> attachments;

	/**
	 * @param id
	 */
	public Issue(long id) {
		super();
		this.id = id;
	}
	/**
	 * @param createdBy
	 * @param summary
	 * @param description
	 * @param status
	 * @param type
	 * @param priority
	 * @param project
	 * @param buildFound
	 * @param assignee
	 */
	public Issue(User createdBy, String summary, String description, Status status, String type, String priority,
			Project project, Build buildFound, User assignee) {
		super();
		this.createdBy = createdBy;
		this.summary = summary;
		this.description = description;
		this.status = status;
		this.type = type;
		this.priority = priority;
		this.project = project;
		this.buildFound = buildFound;
		this.assignee = assignee;
	}

	/**
	 * @param createdBy
	 * @param summary
	 * @param description
	 * @param status
	 * @param type
	 * @param priority
	 * @param project
	 * @param buildFound
	 * @param assignee
	 */
	public Issue(long id, String summary, String description, Status status, String resolution, String type, String priority,
			Project project, Build buildFound, User assignee, User modifiedBy) {
		super();
		this.id = id;
		this.modifiedBy = modifiedBy;
		this.summary = summary;
		this.description = description;
		this.status = status;
		this.resolution = resolution;
		this.type = type;
		this.priority = priority;
		this.project = project;
		this.buildFound = buildFound;
		this.assignee = assignee;
	}

	/**
	 * @param id
	 * @param createDate
	 * @param createdBy
	 * @param summary
	 * @param description
	 * @param status
	 * @param resolution
	 * @param type
	 * @param priority
	 * @param project
	 * @param buildFound
	 */
	public Issue(long id, Date createDate, User createdBy, String summary, String description, Status status,
			String type, String priority, Project project, Build buildFound) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.summary = summary;
		this.description = description;
		this.status = status;
		this.type = type;
		this.priority = priority;
		this.project = project;
		this.buildFound = buildFound;
	}

	/**
	 * @param id
	 * @param createDate
	 * @param createdBy
	 * @param modifyDate
	 * @param modifiedBy
	 * @param summary
	 * @param description
	 * @param status
	 * @param resolution
	 * @param type
	 * @param priority
	 * @param project
	 * @param buildFound
	 * @param assignee
	 */
	public Issue(long id, Date createDate, User createdBy, Date modifyDate, User modifiedBy, String summary,
			String description, Status status, String resolution, String type, String priority, Project project,
			Build buildFound, User assignee) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.modifyDate = modifyDate;
		this.modifiedBy = modifiedBy;
		this.summary = summary;
		this.description = description;
		this.status = status;
		this.resolution = resolution;
		this.type = type;
		this.priority = priority;
		this.project = project;
		this.buildFound = buildFound;
		this.assignee = assignee;
	}

	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate
	 *            the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * @return the modifiedBy
	 */
	public User getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy
	 *            the modifiedBy to set
	 */
	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
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
	
	public Status getStatus(){
		return status;
	}
	/**
	 * @param status
	 *            the status to set
	 */

	public void setStatus(Status status) {
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
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project
	 *            the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @return the buildFound
	 */
	public Build getBuildFound() {
		return buildFound;
	}

	/**
	 * @param buildFound
	 *            the buildFound to set
	 */
	public void setBuildFound(Build buildFound) {
		this.buildFound = buildFound;
	}

	/**
	 * @return the assignee
	 */
	public User getAssignee() {
		return assignee;
	}

	/**
	 * @param assignee
	 *            the assignee to set
	 */
	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	/**
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * @return the attachments
	 */
	public List<Attachment> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments
	 *            the attachments to set
	 */
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @return the createdBy
	 */
	public User getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = Date.valueOf(createDate);
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id).append(";");
		if (createDate != null)
			builder.append(createDate).append(";");
		if (createdBy != null)
			builder.append(createdBy).append(";");
		if (modifyDate != null)
			builder.append(modifyDate).append(";");
		if (modifiedBy != null)
			builder.append(modifiedBy).append(";");
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
		if (project != null)
			builder.append(project).append(";");
		if (buildFound != null)
			builder.append(buildFound).append(";");
		if (assignee != null)
			builder.append(assignee).append(";");
		if (comments != null)
			builder.append(comments).append(";");
		if (attachments != null)
			builder.append(attachments);
		return builder.toString();
	}
}
