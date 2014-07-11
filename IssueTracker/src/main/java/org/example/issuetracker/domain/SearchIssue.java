package org.example.issuetracker.domain;

public class SearchIssue {
	private String summary;
	private String assigneeEmail;
	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}
	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/**
	 * @return the assigneeEmail
	 */
	public String getAssigneeEmail() {
		return assigneeEmail;
	}
	/**
	 * @param assigneeEmail the assigneeEmail to set
	 */
	public void setAssigneeEmail(String assigneeEmail) {
		this.assigneeEmail = assigneeEmail;
	}
	
	
}
