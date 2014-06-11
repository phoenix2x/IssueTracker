package org.example.issuetracker.domain;

public class IssuePaginationParams {
	
	private Integer page;
	private Integer orderBy;
	private Integer order;
	/**
	 * @param page
	 * @param orderBy
	 * @param order
	 */
	private IssuePaginationParams(Integer page, Integer orderBy, Integer order) {
		super();
		this.page = page;
		this.orderBy = orderBy;
		this.order = order;
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
	public Integer getOrderBy() {
		return orderBy;
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
	public Integer getOrder() {
		return order;
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
