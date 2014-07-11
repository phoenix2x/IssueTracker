package org.example.issuetracker.domain;

public class IssuePaginationParams extends PaginationParams {
	
	private enum OrderBY {
		ID, PRIORITY, ASSIGNEE, TYPE, STATUS, SUMMARY
	}

	/**
	 * 
	 */
	public IssuePaginationParams() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param page
	 * @param orderBy
	 * @param order
	 */
	public IssuePaginationParams(Integer page, Integer orderBy, Integer order) {
		super(page, orderBy, order);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.example.issuetracker.domain.PaginationParams#getStringOrderBy()
	 */
	@Override
	public String getStringOrderBy() {
		return OrderBY.values()[getOrderBy()].toString().toLowerCase();
	}
}
