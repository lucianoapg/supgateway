package com.sup.gateway.model.entity;

import java.io.Serializable;
import java.math.BigInteger;

public class ManageStock implements Serializable{
	
	private static final long serialVersionUID = 7464616162813424553L;
	
	private Long productId;
	private BigInteger increase;
	private BigInteger decrease;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public BigInteger getIncrease() {
		return increase;
	}
	public void setIncrease(BigInteger increase) {
		this.increase = increase;
	}
	public BigInteger getDecrease() {
		return decrease;
	}
	public void setDecrease(BigInteger decrease) {
		this.decrease = decrease;
	}
	

}
