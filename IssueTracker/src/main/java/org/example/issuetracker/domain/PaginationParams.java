package org.example.issuetracker.domain;

public abstract class PaginationParams {
	private Integer orderBy;
	private Integer page;
	private Integer order;
	
	
	/**
	 * 
	 */
	public PaginationParams() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @param user
	 * @param page
	 * @param orderBy
	 * @param order
	 */
	public PaginationParams(Integer page, Integer orderBy, Integer order) {
		super();
		this.page = page;
		this.orderBy = orderBy;
		this.order = order;
	}
	
	public abstract String getStringOrderBy();

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
