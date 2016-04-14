package cn.w28l30.domain;

import java.util.List;

public class PageBean {

	List list;
	int totalRecord;
	int pageSize;
	int totalPage;
	
	int currentPage;
	int previousPage;
	int nextPage;
	int pageBar[];
	
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPreviousPage() {
		if (currentPage > 1) {
			return currentPage - 1;
		}
		previousPage = 1;
		return previousPage;
	}

	public int getNextPage() {
		if (currentPage < totalPage) {
			return currentPage + 1;
		}
		nextPage = totalPage;
		return nextPage;
	}

	public int[] getPageBar() {
		int startIndex = 0;
		int endIndex = 0;
		if (totalPage < 10) {
			pageBar = new int[totalPage];
			startIndex = 1;
			endIndex = totalPage;
		} else {
			pageBar = new int[10];
			startIndex = this.currentPage - 4;
			endIndex = this.currentPage + 5;
			if (startIndex < 0) {
				startIndex = 1;
				endIndex = 10;
			}
			if (endIndex > totalPage) {
				startIndex = totalPage - 9;
				endIndex = totalPage;
			}
		}
		int count = 0;
		for (int i = startIndex; i <= endIndex; i++) {
			pageBar[count++] = i;
		}

		return pageBar;
	}
	
	public int getTotalPage() {
		this.totalPage = (totalRecord - 1) / pageSize + 1;
		return totalPage;
	}

}
