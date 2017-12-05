package com.model2.mvc.common;


public class Search {
	
	///Field
	private int currentPage;
	private String searchCondition;
	private String searchKeyword;
	private int pageSize;
	private String searchOrderbyPrice;
	private String searchOrderbyName;
	private String searchOrderbyRegDate;
	private String searchOrderbyAmount;
	
	///Constructor
	public Search() {
	}
	
	///Method
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int paseSize) {
		this.pageSize = paseSize;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int curruntPage) {
		this.currentPage = curruntPage;
	}

	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getSearchOrderbyPrice() {
		return searchOrderbyPrice;
	}

	public void setSearchOrderbyPrice(String searchOrderbyPrice) {
		this.searchOrderbyPrice = searchOrderbyPrice;
	}

	public String getSearchOrderbyName() {
		return searchOrderbyName;
	}

	public void setSearchOrderbyName(String searchOrderbyName) {
		this.searchOrderbyName = searchOrderbyName;
	}

	public String getSearchOrderbyReaDate() {
		return searchOrderbyRegDate;
	}

	public void setSearchOrderbyReaDate(String searchOrderbyReaDate) {
		this.searchOrderbyRegDate = searchOrderbyReaDate;
	}

	public String getSearchOrderbyAmount() {
		return searchOrderbyAmount;
	}

	public void setSearchOrderbyAmount(String searchOrderbyAmount) {
		this.searchOrderbyAmount = searchOrderbyAmount;
	}

	@Override
	public String toString() {
		return "Search [currentPage=" + currentPage + ", searchCondition=" + searchCondition + ", searchKeyword="
				+ searchKeyword + ", pageSize=" + pageSize + ", searchOrderbyPrice=" + searchOrderbyPrice + "]";
	}
}