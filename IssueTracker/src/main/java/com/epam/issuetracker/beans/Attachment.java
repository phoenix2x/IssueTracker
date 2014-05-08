package com.epam.issuetracker.beans;

import java.sql.Date;

public class Attachment {
	private final long id;
	private final User addedBy;
	private final Date addDate;

	/**
	 * @param addedBy
	 * @param addDate
	 * @param id
	 */
	public Attachment(long id, User addedBy, Date addDate) {
		super();
		this.id = id;
		this.addedBy = addedBy;
		this.addDate = addDate;
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
			builder.append(addDate);
		return builder.toString();
	}
}
