package com.bitcoin.predictionmarket.model;


public class Position {
	public final Contract contract;
	public final double netUnitsBoughtOrShortSold;
	public final double netAmountBoughtOrSold;
	
	public Position(Contract contract, double netUnitsBoughtOrShortSold, double netAmountBoughtOrSold) {
		this.contract = contract;	
		this.netUnitsBoughtOrShortSold = netUnitsBoughtOrShortSold;
		this.netAmountBoughtOrSold = netAmountBoughtOrSold;
	}		
}