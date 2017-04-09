package com.eshop.webapp.admin.util;

import java.util.List;

import com.eshop.webapp.admin.base.BaseModel;

/** 
 * 分页响应需求
 * @author yangmeng
 * @version 创建时间：2016年12月19日 下午3:13:52 
 * 
 */
public class PageResponse<T> extends BaseModel{

	private static final long serialVersionUID = 1L;
	protected PageRequest request;
	private List<T> data;
    private long recordsTotal;   
    private long recordsFiltered;
    private int draw;
    
    public PageResponse(){
    }
    
    public PageResponse(PageRequest request){
    	this.request=request;
    }
    
	public PageRequest getRequest() {
		return request;
	}
	public void setRequest(PageRequest request) {
		this.request = request;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public long getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public long getRecordsFiltered() {
		recordsFiltered=recordsTotal;
		return recordsFiltered;
	}
	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
}
