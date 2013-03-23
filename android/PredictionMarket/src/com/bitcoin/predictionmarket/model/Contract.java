package com.bitcoin.predictionmarket.model;

import java.io.Serializable;
import java.util.Date;

public class Contract implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String contractName;
	public Date contractExpiration;
	public double contractPrice;		
}
