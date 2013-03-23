package com.bitcoin.predictionmarket.model;

import java.io.Serializable;
import java.util.Date;

public class Contract implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public final String name;
	public final Date expiration;
	public final double price;
	
	public Contract(String name, Date expiration, double price) {
		this.name = name;
		this.expiration = expiration;
		this.price = price;
	}		
}
