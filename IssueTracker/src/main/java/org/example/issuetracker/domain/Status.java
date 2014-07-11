package org.example.issuetracker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonValue;


@Entity
@Table(name = "statuses")
public class Status extends GenericDomainObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "NAME")
	@Size(min = 1, message = "Name required")
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
		super(id);
	}



	/**
	 * @return the name
	 */
	@JsonValue
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

}
