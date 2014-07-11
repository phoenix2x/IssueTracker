package org.example.issuetracker.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "attachments")
public class Attachment extends GenericDomainObject implements Comparable<Attachment> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne()
	@JoinColumn(name="ADDEDBY", updatable = false)
	private User addedBy;
	
	@Column(name = "CREATEDATE", updatable = false)
	private Date addDate;
	
	@Column(name = "FILEPATH", updatable = false)
	private String filePath;
	
	@Column(name = "FILENAME", updatable = false)
	private String fileName;
	
	@ManyToOne
	private Issue issue;
	/**
	 */
	public Attachment() {
		super();
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
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}


	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}


	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	public int compareTo(Attachment o) {
		long timeDiff = 0;
		if (this.addDate != null && o.addDate != null) {
			timeDiff = this.addDate.getTime() - o.addDate.getTime();
		}
		return (int) ((timeDiff != 0) ? timeDiff: this.getId() - o.getId());
	}
	
}
