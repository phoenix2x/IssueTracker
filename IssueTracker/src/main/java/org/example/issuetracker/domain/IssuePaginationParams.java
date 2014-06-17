package org.example.issuetracker.domain;

public class IssuePaginationParams {
	private enum OrderBY {
		ID, PRIORITY, ASSIGNEE, TYPE, STATUS, SUMMARY
	}
	private User user;
	private Integer page;
	private Integer orderBy;
	private Integer order;
	
	
	/**
	 * 
	 */
	public IssuePaginationParams() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @param user
	 * @param page
	 * @param orderBy
	 * @param order
	 */
	public IssuePaginationParams(User user, Integer page, Integer orderBy, Integer order) {
		super();
		this.user = user;
		this.page = page;
		this.orderBy = orderBy;
		this.order = order;
	}


	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}


	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}
	/**
	 * @return the orderBy
	 */
	public String getOrderBy() {
		return OrderBY.values()[orderBy].toString().toLowerCase();
	}
	/**
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	/**
	 * @return the order
	 */
	public String getOrder() {
		return (order.equals(1)? "ASC":"DESC");
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (page != null)
			builder.append(page).append(";");
		if (orderBy != null)
			builder.append(orderBy).append(";");
		if (order != null)
			builder.append(order);
		return builder.toString();
	}

}
