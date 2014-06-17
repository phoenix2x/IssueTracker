package org.example.issuetracker.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;



@Entity
@Table(name = "issues")
public class Issue extends GenericDomainObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "CREATEDATE", updatable = false)
	@JsonIgnore
	private Date createDate;
	
	@ManyToOne()
	@JoinColumn(name="CREATEDBY", updatable = false)
	@JsonIgnore
	private User createdBy;
	
	@Column(name = "MODIFYDATE")
	@JsonIgnore
	private Date modifyDate;
	
	@ManyToOne()
	@JoinColumn(name="MODIFIEDBY")
	@JsonIgnore
	private User modifiedBy;
	
	@Column(name = "SUMMARY")
	private String summary;
	
	@Column(name = "DESCRIPTION")
	@JsonIgnore
	private String description;
	
	@ManyToOne()
	@JoinColumn(name="STATUS")
	private Status status;
	
	@ManyToOne()
	@JoinColumn(name = "RESOLUTION")
	@JsonIgnore
	private Resolution resolution;
	
	@ManyToOne()
	@JoinColumn(name = "TYPE")
	private Type type;
	
	@ManyToOne()
	@JoinColumn(name = "PRIORITY")
	private Priority priority;
	
	@ManyToOne()
	@JoinColumn(name="PROJECT")
	@JsonIgnore
	private Project project;
	
	@ManyToOne()
	@JoinColumn(name="BUILDFOUND")
	@JsonIgnore
	private Build buildFound;
	
	@ManyToOne()
	@JoinColumn(name="ASSIGNEE")
	private User assignee;
	
	@Transient
	@JsonIgnore
	private List<Comment> comments;
	
	@Transient
	@JsonIgnore
	private List<Attachment> attachments;

	/**
	 * 
	 */
	public Issue() {
		super();
	}

	@PrePersist
	public void createDateGen() {
		this.createDate = new Date(new java.util.Date().getTime());
	}
	
	@PreUpdate
	public void modifyDateGen() {
		this.modifyDate = new Date(new java.util.Date().getTime());
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
	 * @return the resolution
	 */
	public Resolution getResolution() {
		return resolution;
	}

	/**
	 * @param resolution the resolution to set
	 */
	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * @return the priority
	 */
	public Priority getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}


	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

}
