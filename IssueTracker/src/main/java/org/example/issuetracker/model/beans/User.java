package org.example.issuetracker.model.beans;

import java.io.Serializable;

import org.example.issuetracker.model.enums.UserRoles;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final long id;
	private final String emailAddress;
	private String firstName;
	private String lastName;
	private String password;
	private UserRoles userRole;

	/**
	 * @param id
	 * @param emailAddress
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param userRole
	 */
	public User(long id, String emailAddress, String firstName, String lastName, String password, String userRole) {
		this(id, emailAddress, password, userRole);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * @param id
	 * @param emailAddress
	 * @param password
	 * @param userRole
	 */
	public User(long id, String emailAddress, String password, String userRole) {
		super();
		this.id = id;
		this.emailAddress = emailAddress;
		this.password = password;
		setUserRole(userRole);
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userRole
	 */
	public UserRoles getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole
	 *            the userRole to set
	 */
	public void setUserRole(UserRoles userRole) {
		this.userRole = userRole;
	}

	/**
	 * @param userRole
	 *            the userRole to set
	 */
	public void setUserRole(String userRole) {
		this.userRole = UserRoles.valueOf(userRole.toUpperCase());
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (emailAddress != null)
			builder.append(emailAddress).append(";");
		if (password != null)
			builder.append(password).append(";");
		if (userRole != null)
			builder.append(userRole);
		return builder.toString();
	}
}
