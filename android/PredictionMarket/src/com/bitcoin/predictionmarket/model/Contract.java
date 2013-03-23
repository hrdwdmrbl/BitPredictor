package com.bitcoin.predictionmarket.model;

import java.io.Serializable;
import java.util.Date;

public class Contract implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String name;
	public Date expiration;
	public double price;		
}
