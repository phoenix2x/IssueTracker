package org.example.issuetracker.model.enums;

public enum StatusId {
	NEW(1), ASSIGNED(2), INPROGRESS(3), RESOLVED(4), CLOSED(5), REOPENED(6);
	private int id;
	StatusId(int id) {
		this.id = id;
	}
	public int getId(){
		return id;
	}
}
