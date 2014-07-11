package org.example.issuetracker.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Size;
@Entity
@Table(name = "coments")
public class Comment extends GenericDomainObject implements Comparable<Comment>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne()
	@JoinColumn(name="ADDEDBY", updatable = false)
	private User addedBy;
	
	@Column(name = "CREATEDATE", updatable = false)
	private Date addDate;
	
	@Column(name = "COMMENT")
	@Size(min=1, message="Comment required")
	private String comment;
	
	@ManyToOne
	private Issue issue;
	/**
	 */
	public Comment() {
		super();
	}

	
	/**
	 * 
	 */
	public Comment(long id) {
		super(id);
	}
	
	@PrePersist
	public void addDateGen() {
		this.addDate = new Date(new java.util.Date().getTime());
	}

	/**
	 * @return the addedBy
	 */
	public User getAddedBy() {
		return addedBy;
	}

	/**
	 * @return the addDate
	 */
	public Date getAddDate() {
		return addDate;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}


	/**
	 * @param addedBy the addedBy to set
	 */
	public void setAddedBy(User addedBy) {
		this.addedBy = addedBy;
	}


	/**
	 * @param addDate the addDate to set
	 */
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}


	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}


	/**
	 * @return the issue
	 */
	public Issue getIssue() {
		return issue;
	}


	/**
	 * @param issue the issue to set
	 */
	public void setIssue(Issue issue) {
		this.issue = issue;
	}


	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Comment o) {
		long timeDiff = 0;
		if (this.addDate != null && o.addDate != null) {
			timeDiff = this.addDate.getTime() - o.addDate.getTime();
		} 
		return (int) ((timeDiff != 0) ? timeDiff: this.getId() - o.getId());
	}


	
}
