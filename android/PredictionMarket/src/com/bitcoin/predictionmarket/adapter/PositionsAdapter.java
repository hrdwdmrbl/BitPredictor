/*******************************************************************************
 * Copyright (C) 2012 Digipom Inc. All Rights Reserved. Unauthorized distribution is strictly prohibited.
 ******************************************************************************/
package com.bitcoin.predictionmarket.adapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bitcoin.predictionmarket.R;
import com.bitcoin.predictionmarket.adapter.PositionsAdapter.SyncWithServer.SettableList;
import com.bitcoin.predictionmarket.model.Contract;
import com.bitcoin.predictionmarket.model.Position;

public class PositionsAdapter extends BaseAdapter {
	private final DecimalFormat doubleFormatter = new DecimalFormat("฿ 0.00");	
	
	private final Context context;
	private final LayoutInflater layoutInflater;
	private final ListView listView;		

	private List<Position> listData = new ArrayList<Position>();
	
	private final SettableList settableList = new SettableList() {
		@Override
		public void setList(List<Position> newListData) {
			PositionsAdapter.this.setList(newListData);
		}
	};
	
	static class PositionViewHolder {
		View positionItemLayout;		
		TextView contractName;		
		TextView positionDetailsUnits;
		TextView positionDetailsAveragePrice;
		TextView marketValue;
		TextView netProfitLoss;
	}
	
	public PositionsAdapter(Context context, ListView listView) {
		this.context = context;
		this.layoutInflater = LayoutInflater.from(context);
		this.listView = listView;		
	}		

	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Position getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}				

	public List<Position> getList() {
		return listData;
	}	

	void setList(List<Position> newListData) {				
		listData = newListData;		
		notifyDataSetChanged();
	}	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {		
		if (convertView == null) {					
			convertView = layoutInflater.inflate(R.layout.position_item, null);
			
			final PositionViewHolder positionViewHolder = new PositionViewHolder();
			positionViewHolder.positionItemLayout = convertView.findViewById(R.id.positionItemLayout);
			positionViewHolder.contractName = (TextView) convertView.findViewById(R.id.contractName);			
			positionViewHolder.positionDetailsUnits = (TextView) convertView.findViewById(R.id.positionDetailsUnits);
			positionViewHolder.positionDetailsAveragePrice = (TextView) convertView.findViewById(R.id.positionDetailsAveragePrice);
			positionViewHolder.marketValue = (TextView) convertView.findViewById(R.id.marketValue);
			positionViewHolder.netProfitLoss = (TextView) convertView.findViewById(R.id.netProfitLoss);
			
			convertView.setTag(positionViewHolder);											
		}	
				
		final Position contractPosition = (Position) listData.get(position);
		final PositionViewHolder viewHolder = (PositionViewHolder) convertView.getTag();
		
		viewHolder.contractName.setText(contractPosition.contract.name);				
		
		if (contractPosition.netUnitsBoughtOrShortSold >= 0) {
			viewHolder.positionDetailsUnits.setText(context.getString(R.string.unitsBought, contractPosition.netUnitsBoughtOrShortSold));
		} else {
			viewHolder.positionDetailsUnits.setText(context.getString(R.string.unitsShortSold, Math.abs(contractPosition.netUnitsBoughtOrShortSold)));
		}
		
		viewHolder.positionDetailsAveragePrice.setText(context.getString(R.string.averageAndCurrentPrice, doubleFormatter.format(Math.abs(contractPosition.netAmountBoughtOrSold) / Math.abs(contractPosition.netUnitsBoughtOrShortSold)), doubleFormatter.format(contractPosition.contract.price)));
		
		final double marketValue = contractPosition.contract.price * contractPosition.netUnitsBoughtOrShortSold;		
		viewHolder.marketValue.setText(doubleFormatter.format(Math.abs(marketValue)));	
		final double netProfitLoss = marketValue - contractPosition.netAmountBoughtOrSold;
		
		if (netProfitLoss >= 0) {
			viewHolder.netProfitLoss.setText(doubleFormatter.format(netProfitLoss));
			viewHolder.netProfitLoss.setTextColor(Color.rgb(0, 128, 0));
		} else {
			viewHolder.netProfitLoss.setText(doubleFormatter.format(netProfitLoss));
			viewHolder.netProfitLoss.setTextColor(Color.RED);
		}

		return convertView;
	}

	public void syncWithServer(OnSyncCompleteListener listener) {
		new SyncWithServer(listView, listener, settableList).execute();
	}	

	public interface OnSyncCompleteListener {
		void onSyncComplete();
	}

	static class SyncWithServer extends AsyncTask<Void, Void, List<Position>> {
		interface SettableList {
			void setList(List<Position> newListData);
		}
		
		private final ListView listView;
		private final OnSyncCompleteListener onSyncCompleteListener;
		private final SettableList settableList;			

		public SyncWithServer(ListView listView,
				OnSyncCompleteListener onSyncCompleteListener, SettableList settableList) {
			this.listView = listView;
			this.onSyncCompleteListener = onSyncCompleteListener;
			this.settableList = settableList;			
		}

		@Override
		protected void onPreExecute() {
			((TextView) listView.getEmptyView()).setText(R.string.loading);
		}

		@Override
		protected List<Position> doInBackground(Void... params) {						
			final List<Position> result = new ArrayList<Position>();	
			
			result.add(new Position(new Contract("Apple share price above 500", new Date(1388606400000L), 8.73), -2, -10));			
			result.add(new Position(new Contract("Canada wins at least 8 gold medals in the 2014 Olympics", new Date(1393588800000L), 6.23), 3, 15));							
			
			return result;
		}		

		@Override
		protected void onPostExecute(List<Position> result) {
			if (result.isEmpty()) {
				((TextView) listView.getEmptyView()).setText(R.string.noContractsFound);				
			}

			settableList.setList(result);

			if (onSyncCompleteListener != null) {
				onSyncCompleteListener.onSyncComplete();
			}
		}
	}	
}
