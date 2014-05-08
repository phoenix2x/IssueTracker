package com.epam.issuetracker.beans;

import java.sql.Date;
import java.util.List;

import com.epam.issuetracker.enums.Priority;
import com.epam.issuetracker.enums.Resolution;
import com.epam.issuetracker.enums.Status;
import com.epam.issuetracker.enums.Type;

public class Issue {
	private final long id;
	private Date createDate;
	private long createdBy;
	private Date modifyDate;
	private long modifiedBy;
	private String summary;
	private String description;
	private Status status;
	private Resolution resolution;
	private Type type;
	private Priority priority;
	private String project;
	private String buildFound;
	private Long assignee;
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
	public Issue(long id, Date createDate, long createdBy, String summary, String description, Status status,
			Type type, Priority priority, String project, String buildFound) {
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
	public long getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy
	 *            the modifiedBy to set
	 */
	public void setModifiedBy(long modifiedBy) {
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
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	public void setStatus(String status) {
		this.status = Status.valueOf(status.trim().toUpperCase());
	}

	/**
	 * @return the resolution
	 */
	public Resolution getResolution() {
		return resolution;
	}

	/**
	 * @param resolution
	 *            the resolution to set
	 */
	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}

	public void setResolution(String resoution) {
		this.resolution = Resolution.valueOf(resoution.trim().toUpperCase());
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	public void setType(String type) {
		this.type = Type.valueOf(type.trim().toUpperCase());
	}

	/**
	 * @return the priority
	 */
	public Priority getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void setPriority(String priority) {
		this.priority = Priority.valueOf(priority.trim().toUpperCase());
	}

	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * @param project
	 *            the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * @return the buildFound
	 */
	public String getBuildFound() {
		return buildFound;
	}

	/**
	 * @param buildFound
	 *            the buildFound to set
	 */
	public void setBuildFound(String buildFound) {
		this.buildFound = buildFound;
	}

	/**
	 * @return the assignee
	 */
	public Long getAssignee() {
		return assignee;
	}

	/**
	 * @param assignee
	 *            the assignee to set
	 */
	public void setAssignee(Long assignee) {
		this.assignee = assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = Long.valueOf(assignee);
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
	public long getCreatedBy() {
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
	public void setCreatedBy(String createdBy) {
		this.createdBy = Long.valueOf(createdBy);
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
		if (createDate != null)
			builder.append(createDate).append(";");
		builder.append(createdBy).append(";");
		if (modifyDate != null)
			builder.append(modifyDate).append(";");
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
