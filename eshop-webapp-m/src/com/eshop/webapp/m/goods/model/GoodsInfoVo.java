package com.eshop.webapp.m.goods.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



/** 
 * 商品展示信息
 * @author yangmeng
 * @version 创建时间：2017年2月10日 上午10:31:20 
 * 
 */
public class GoodsInfoVo {
	
	private GoodsSku main_goods;
	
	private List<GoodsSku> relate_goods;
	
	/**
	 * 品种
	 */
	private List<GoodsAttribute> goods_varieties;
	
	/**
	 * 规格
	 */
	private List<GoodsAttribute> goods_spec;
	
	/**
	 * 养殖地
	 */
	private List<GoodsAttribute> goods_origin;
	
	/**
	 * 配送级别
	 */
	private List<GoodsAttribute> goods_shipping;
	

	public GoodsSku getMain_goods() {
		return main_goods;
	}

	public void setMain_goods(GoodsSku main_goods) {
		this.main_goods = main_goods;
	}

	public List<GoodsSku> getRelate_goods() {
		return relate_goods;
	}

	public void setRelate_goods(List<GoodsSku> relate_goods) {
		this.relate_goods = relate_goods;
	}

	public List<GoodsAttribute> getGoods_varieties() {
		if(goods_varieties==null){
			goods_varieties=new ArrayList<GoodsAttribute>();
			Set<String> t = new HashSet<String>(); 
			if(relate_goods!=null&&relate_goods.size()>0){
				for (GoodsSku goodsSku : relate_goods) {
					if(goodsSku.getStock_num()<1){
						continue;
					}
					if (t.contains(goodsSku.getGoods_varieties())||!goodsSku.getGoods_shipping().equals(main_goods.getGoods_shipping())
							||!goodsSku.getGoods_origin().equals(main_goods.getGoods_origin())
							||!goodsSku.getGoods_spec().equals(main_goods.getGoods_spec())) {
						continue;
					}
					t.add(goodsSku.getGoods_varieties());
					GoodsAttribute varieties=new GoodsAttribute();
					varieties.setAttr_code(goodsSku.getGoods_varieties());
					varieties.setAttr_name(goodsSku.getGoods_varieties_name());
					goods_varieties.add(varieties);
				}
			}
			
			if(!t.contains(main_goods.getGoods_varieties())){
				GoodsAttribute varieties=new GoodsAttribute();
				varieties.setAttr_code(main_goods.getGoods_varieties());
				varieties.setAttr_name(main_goods.getGoods_varieties_name());
				goods_varieties.add(varieties);
				t.add(main_goods.getGoods_varieties());
			}
		}
		return goods_varieties;
	}


	public List<GoodsAttribute> getGoods_spec() {
		if(goods_spec==null){
			goods_spec=new ArrayList<GoodsAttribute>();
			Set<String> t = new HashSet<String>(); 
			if(relate_goods!=null&&relate_goods.size()>0){
				for (GoodsSku goodsSku : relate_goods) {
					if(goodsSku.getStock_num()<1){
						continue;
					}
					if (t.contains(goodsSku.getGoods_spec())||!goodsSku.getGoods_shipping().equals(main_goods.getGoods_shipping())
							||!goodsSku.getGoods_origin().equals(main_goods.getGoods_origin())
							||!goodsSku.getGoods_varieties().equals(main_goods.getGoods_varieties())) {
						continue;
					}
					t.add(goodsSku.getGoods_spec());
					GoodsAttribute spec=new GoodsAttribute();
					spec.setAttr_code(goodsSku.getGoods_spec());
					spec.setAttr_name(goodsSku.getGoods_spec_name());
					goods_spec.add(spec);
				}
			}
			
			if(!t.contains(main_goods.getGoods_spec())){
				GoodsAttribute spec=new GoodsAttribute();
				spec.setAttr_code(main_goods.getGoods_spec());
				spec.setAttr_name(main_goods.getGoods_spec_name());
				goods_spec.add(spec);
				t.add(main_goods.getGoods_spec());
			}
		}
		return goods_spec;
	}


	public List<GoodsAttribute> getGoods_origin() {
		if(goods_origin==null){
			goods_origin=new ArrayList<GoodsAttribute>();
			Set<String> t = new HashSet<String>(); 
			if(relate_goods!=null&&relate_goods.size()>0){
				for (GoodsSku goodsSku : relate_goods) {
					if(goodsSku.getStock_num()<1){
						continue;
					}
					if (t.contains(goodsSku.getGoods_origin())||!goodsSku.getGoods_shipping().equals(main_goods.getGoods_shipping())
							||!goodsSku.getGoods_spec().equals(main_goods.getGoods_spec())
							||!goodsSku.getGoods_varieties().equals(main_goods.getGoods_varieties())) {
						continue;
					}
					t.add(goodsSku.getGoods_origin());
					GoodsAttribute origin=new GoodsAttribute();
					origin.setAttr_code(goodsSku.getGoods_origin());
					origin.setAttr_name(goodsSku.getGoods_origin_name());
					goods_origin.add(origin);
				}
			}
			
			if(!t.contains(main_goods.getGoods_origin())){
				GoodsAttribute origin=new GoodsAttribute();
				origin.setAttr_code(main_goods.getGoods_origin());
				origin.setAttr_name(main_goods.getGoods_origin_name());
				goods_spec.add(origin);
				t.add(main_goods.getGoods_origin());
			}
		}
		return goods_origin;
	}


	public List<GoodsAttribute> getGoods_shipping() {
		if(goods_shipping==null){
			goods_shipping=new ArrayList<GoodsAttribute>();
			Set<String> t = new HashSet<String>(); 
			if(relate_goods!=null&&relate_goods.size()>0){
				for (GoodsSku goodsSku : relate_goods) {
					if(goodsSku.getStock_num()<1){
						continue;
					}
					if (t.contains(goodsSku.getGoods_shipping())||!goodsSku.getGoods_shipping().equals(main_goods.getGoods_shipping())) {
						continue;
					}
					t.add(goodsSku.getGoods_shipping());
					GoodsAttribute shipping=new GoodsAttribute();
					shipping.setAttr_code(goodsSku.getGoods_shipping());
					shipping.setAttr_name(goodsSku.getGoods_shipping_name());
					goods_shipping.add(shipping);
				}
			}
			
			if(!t.contains(main_goods.getGoods_shipping())){
				GoodsAttribute shipping=new GoodsAttribute();
				shipping.setAttr_code(main_goods.getGoods_shipping());
				shipping.setAttr_name(main_goods.getGoods_shipping_name());
				goods_spec.add(shipping);
				t.add(main_goods.getGoods_shipping());
			}
		}
		return goods_shipping;
	}

}
