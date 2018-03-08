package com.example.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="waiter_sale_count")
//@CompoundIndexes({
//	@CompoundIndex(name = "brand_shop", def = "{'brand_id': 1, 'shop_id': 1}")
//})
public class WaiterSaleCount {

	@Transient
	private static final long serialVersionUID = 1L;
	@Id
  private String id ;
//	@Indexed
	@Field("brand_id")
	private Long brandId;//'品牌id',
//	@Indexed
	@Field("shop_id")
	private Long shopId;//'门店id',
	@Field("waiter_id")
  private Long waiterId ;//'服务员id，权限那边的用户id',
	@Field("waiter_account")
	private String waiterAccount;//'服务员账号',
	@Field("waiter_name")
	private String waiterName;//'服务员姓名',
	@Field("sale_year")
	private Integer saleYear;//'销售日（如 20170601）',
	@Field("sale_month")
	private Integer saleMonth;//'销售日（如 20170601）',
	@Field("sale_day")
	private Integer saleDay;//'销售日（如 20170601）',
	@Field("sale_quantity")
	private Double saleQuantity ;//'一天之内销售商品数量',
	@Field("sale_amount")
	private Double saleAmount ;//'一天之内销售总金额',
	@Field("discount_amount")
	private Double discountAmount ;//'折扣金额',
	@Field("discounted_amount")
	private Double discountedAmount ;//'折后金额',
	@Field("server_create_time")
	private Date serverCreateTime ;// 
	@Field("server_update_time")
	private Date  serverUpdateTime;//
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public Long getWaiterId() {
		return waiterId;
	}
	public void setWaiterId(Long waiterId) {
		this.waiterId = waiterId;
	}
	public String getWaiterAccount() {
		return waiterAccount;
	}
	public void setWaiterAccount(String waiterAccount) {
		this.waiterAccount = waiterAccount;
	}
	public String getWaiterName() {
		return waiterName;
	}
	public void setWaiterName(String waiterName) {
		this.waiterName = waiterName;
	}
	public Integer getSaleDay() {
		return saleDay;
	}
	public void setSaleDay(Integer saleDay) {
		this.saleDay = saleDay;
	}
	public Double getSaleQuantity() {
		return saleQuantity;
	}
	public void setSaleQuantity(Double saleQuantity) {
		this.saleQuantity = saleQuantity;
	}
	public Double getSaleAmount() {
		return saleAmount;
	}
	public void setSaleAmount(Double saleAmount) {
		this.saleAmount = saleAmount;
	}
	public Double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Double getDiscountedAmount() {
		return discountedAmount;
	}
	public void setDiscountedAmount(Double discountedAmount) {
		this.discountedAmount = discountedAmount;
	}
	public Date getServerCreateTime() {
		return serverCreateTime;
	}
	public void setServerCreateTime(Date serverCreateTime) {
		this.serverCreateTime = serverCreateTime;
	}
	public Date getServerUpdateTime() {
		return serverUpdateTime;
	}
	public void setServerUpdateTime(Date serverUpdateTime) {
		this.serverUpdateTime = serverUpdateTime;
	}
	public Integer getSaleYear() {
		return saleYear;
	}
	public void setSaleYear(Integer saleYear) {
		this.saleYear = saleYear;
	}
	public Integer getSaleMonth() {
		return saleMonth;
	}
	public void setSaleMonth(Integer saleMonth) {
		this.saleMonth = saleMonth;
	}	
}
