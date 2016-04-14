package cn.w28l30.domain;

import java.util.List;

public class QueryResult {
	private List list;
	private int totalRecord;
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

}
