package org.example.issuetracker.domain;

public class ProjectPaginationParams extends PaginationParams {
	
	private enum OrderBY {
		NAME, MANAGER, DESCRIPTION
	}

	/**
	 * 
	 */
	public ProjectPaginationParams() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param page
	 * @param orderBy
	 * @param order
	 */
	public ProjectPaginationParams(Integer page, Integer orderBy, Integer order) {
		super(page, orderBy, order);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.example.issuetracker.domain.PaginationParams#getOrderBy()
	 */
	@Override
	public String getStringOrderBy() {
		return OrderBY.values()[getOrderBy()].toString().toLowerCase();
	}
}
