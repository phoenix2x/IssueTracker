package org.example.issuetracker.domain;

import java.io.Serializable;
import java.sql.Date;

public class Comment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final long id;
	private final User addedBy;
	private final Date addDate;
	private final String comment;

	/**
	 * @param addedBy
	 * @param addDate
	 * @param comment
	 */
	public Comment(long id, User addedBy, Date addDate, String comment) {
		super();
		this.id = id;
		this.addedBy = addedBy;
		this.addDate = addDate;
		this.comment = comment;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id).append(";");
		if (addedBy != null)
			builder.append(addedBy).append(";");
		if (addDate != null)
			builder.append(addDate).append(";");
		if (comment != null)
			builder.append(comment);
		return builder.toString();
	}
}
