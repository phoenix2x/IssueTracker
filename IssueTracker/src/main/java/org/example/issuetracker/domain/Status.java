package org.example.issuetracker.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "statuses")
public class Status implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	@GeneratedValue
	private int id;
	
	@Column(name = "NAME")
	private String name;

	
	/**
	 * 
	 */
	public Status() {
		super();
	}

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