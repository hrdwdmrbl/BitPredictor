package com.bitcoin.predictionmarket.model;


public class Position {
	public final Contract contract;
	public final double netProfitLoss;
	
	public Position(Contract contract, double netProfitLoss) {
		this.contract = contract;
		this.netProfitLoss = netProfitLoss;
	}		
}