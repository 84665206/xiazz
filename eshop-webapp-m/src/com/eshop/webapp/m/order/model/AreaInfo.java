package com.eshop.webapp.m.order.model;

import com.eshop.webapp.m.base.BaseModel;

public class AreaInfo extends BaseModel{
 
	private static final long serialVersionUID = 7089282986709099583L;

	private Integer id;

    private String area_name;

    private Byte area_type;

    private Integer parent_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public Byte getArea_type() {
        return area_type;
    }

    public void setArea_type(Byte area_type) {
        this.area_type = area_type;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }
}