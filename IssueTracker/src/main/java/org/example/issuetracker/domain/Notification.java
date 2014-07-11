package org.example.issuetracker.domain;

public class Notification {
	
	private String to;
	private String message;
	/**
	 * 
	 */
	public Notification() {
	}
	
	
	/**
	 * @param to
	 * @param message
	 */
	public Notification(String to, String message) {
		super();
		this.to = to;
		this.message = message;
	}


	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (to != null)
			builder.append(to).append(";");
		if (message != null)
			builder.append(message);
		return builder.toString();
	}
	
}
