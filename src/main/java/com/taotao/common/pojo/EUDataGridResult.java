package com.taotao.common.pojo;

import java.util.List;

public class EUDataGridResult {
	
	// 查询出的信息总数
	private long total;
	
	// 当前页的列表信息
	private List<?> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
