/*******************************************************************************
 * Copyright (C) 2012 Digipom Inc. All Rights Reserved. Unauthorized distribution is strictly prohibited.
 ******************************************************************************/
package com.bitcoin.predictionmarket.adapter;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import com.bitcoin.predictionmarket.adapter.ContractListAdapter.SyncWithServer.SettableList;
import com.bitcoin.predictionmarket.model.Contract;

public class ContractListAdapter extends BaseAdapter {
	private static final int VIEW_TYPE_COUNT = 2;
	private static final int TYPE_HEADER = 0;
	private static final int TYPE_CONTRACT = 1;			
	
	private final DecimalFormat doubleFormatter = new DecimalFormat("฿ 0.00");
	private final DateFormat dateFormatter = SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG);
	
	private final Context context;
	private final LayoutInflater layoutInflater;
	private final ListView listView;		

	private List<Object> listData = new ArrayList<Object>();
	
	private final SettableList settableList = new SettableList() {
		@Override
		public void setList(List<Object> newListData) {
			ContractListAdapter.this.setList(newListData);
		}
	};
	
	static class Header {
		final String header;
		final int backgroundColor;

		public Header(String header, int backgroundColor) {
			this.header = header;
			this.backgroundColor = backgroundColor;
		}				
	}
	
	static class HeaderViewHolder {
		TextView header;		
	}
	
	static class ContractViewHolder {
		View contractItemLayout;		
		TextView contractName;
		TextView contractExpiration;
		TextView contractPrice;		
	}
	
	public ContractListAdapter(Context context, ListView listView) {
		this.context = context;
		this.layoutInflater = LayoutInflater.from(context);
		this.listView = listView;		
	}		

	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}			

	@Override
	public int getItemViewType(int position) {
		final Object item = listData.get(position);

		if (item instanceof Header) {
			return TYPE_HEADER;
		} else {
			return TYPE_CONTRACT;
		} 
	}

	@Override
	public int getViewTypeCount() {
		return VIEW_TYPE_COUNT;
	}

	public List<Object> getList() {
		return listData;
	}	

	void setList(List<Object> newListData) {				
		listData = newListData;		
		notifyDataSetChanged();
	}	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int itemViewType = getItemViewType(position);
		
		if (convertView == null) {					
			if (itemViewType == TYPE_HEADER) {
				convertView = layoutInflater.inflate(R.layout.header_item, null);
				
				final HeaderViewHolder headerViewHolder = new HeaderViewHolder();
				headerViewHolder.header = (TextView) convertView.findViewById(R.id.header);				
				
				convertView.setTag(headerViewHolder);	
			} else {
				convertView = layoutInflater.inflate(R.layout.contract_item, null);
				
				final ContractViewHolder contractViewHolder = new ContractViewHolder();
				contractViewHolder.contractItemLayout = convertView.findViewById(R.id.contractItemLayout);
				contractViewHolder.contractName = (TextView) convertView.findViewById(R.id.contractName);
				contractViewHolder.contractExpiration = (TextView) convertView.findViewById(R.id.contractExpiration);
				contractViewHolder.contractPrice = (TextView) convertView.findViewById(R.id.contractPrice);
				
				convertView.setTag(contractViewHolder);	
			}									
		}	
		
		if (itemViewType == TYPE_HEADER) {
			final Header header = (Header) listData.get(position);
			final HeaderViewHolder viewHolder = (HeaderViewHolder) convertView.getTag();
			
			viewHolder.header.setText(header.header);
			viewHolder.header.setBackgroundColor(header.backgroundColor);
		} else {
			final Contract contract = (Contract) listData.get(position);
			final ContractViewHolder viewHolder = (ContractViewHolder) convertView.getTag();
			
			viewHolder.contractName.setText(contract.name);
			viewHolder.contractExpiration.setText(context.getString(R.string.expiresOn, dateFormatter.format(contract.expiration)));
			viewHolder.contractPrice.setText(doubleFormatter.format(contract.price));
		}

		return convertView;
	}

	public void syncWithServer(OnSyncCompleteListener listener) {
		new SyncWithServer(listView, listener, settableList).execute();
	}	

	public interface OnSyncCompleteListener {
		void onSyncComplete();
	}

	static class SyncWithServer extends AsyncTask<Void, Void, List<Object>> {
		interface SettableList {
			void setList(List<Object> newListData);
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
		protected List<Object> doInBackground(Void... params) {						
			final List<Object> result = new ArrayList<Object>();	
			
			result.add(new Header("Business", Color.parseColor("#0099CC")));
			result.add(new Contract("Apple share price above 500", new Date(1388606400000L), 8.73));
			result.add(new Contract("Apple share price above 600", new Date(1388606400000L), 6.32));
			result.add(new Contract("Apple share price above 700", new Date(1388606400000L), 3.47));
			result.add(new Header("Economy", Color.parseColor("#9933CC")));
			result.add(new Contract("Dow Jones above 15000", new Date(1388606400000L), 9.74));
			result.add(new Contract("Dow Jones above 20000", new Date(1388606400000L), 4.59));
			result.add(new Contract("Dow Jones above 25000", new Date(1388606400000L), 2.43));
			result.add(new Header("Government", Color.parseColor("#669900")));
			result.add(new Contract("War with Iran declared by 2014", new Date(1388606400000L), 3.23));
			result.add(new Contract("War with Iran declared by 2015", new Date(1420113600000L), 4.58));
			result.add(new Contract("War with Iran declared by 2016", new Date(1451649600000L), 4.92));
			result.add(new Header("Health", Color.parseColor("#FF8800")));
			result.add(new Contract("Marijuana declared legal by 2014", new Date(1388606400000L), 1.42));
			result.add(new Contract("Marijuana declared legal by 2015", new Date(1420113600000L), 2.74));
			result.add(new Contract("Marijuana declared legal by 2016", new Date(1451649600000L), 3.47));
			result.add(new Header("Sports", Color.parseColor("#CC0000")));
			result.add(new Contract("Canada wins at least 5 gold medals in the 2014 Olympics", new Date(1393588800000L), 9.32));
			result.add(new Contract("Canada wins at least 8 gold medals in the 2014 Olympics", new Date(1393588800000L), 6.23));
			result.add(new Contract("Canada wins at least 12 gold medals in the 2014 Olympics", new Date(1393588800000L), 4.438));			
			
			return result;
		}		

		@Override
		protected void onPostExecute(List<Object> result) {
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
