package org.example.issuetracker.model.beans;

public class Status {
	private int id;
	private String name;

	/**
	 * @param id
	 */
	public Status(int id) {
		super();
		this.id = id;
	}

	/**
	 * @param id
	 * @param name
	 */
	public Status(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id).append(";");
		if (name != null)
			builder.append(name);
		return builder.toString();
	}
}
