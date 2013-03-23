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
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bitcoin.predictionmarket.R;
import com.bitcoin.predictionmarket.adapter.ContractListAdapter.SyncWithServer.SettableList;

public class ContractListAdapter extends BaseAdapter {
	private final DecimalFormat doubleFormatter = new DecimalFormat("฿ #.##");
	private final DateFormat dateFormatter = SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG);
	
	private final Context context;
	private final LayoutInflater layoutInflater;
	private final ListView listView;		

	private List<ListItem> listData = new ArrayList<ListItem>();
	
	private final SettableList settableList = new SettableList() {
		@Override
		public void setList(List<ListItem> newListData) {
			ContractListAdapter.this.setList(newListData);
		}
	};
	
	static class ContractViewHolder {
		View contractItemLayout;		
		TextView contractName;
		TextView contractExpiration;
		TextView contractPrice;		
	}

	public abstract static class ListItem {

	}

	public static class ContractListItem extends ListItem {
		public String contractName;
		public Date contractExpiration;
		public double contractPrice;
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

	public List<ListItem> getList() {
		return listData;
	}	

	void setList(List<ListItem> newListData) {				
		listData = newListData;		
		notifyDataSetChanged();
	}	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {		
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.contract_item, null);
			
			final ContractViewHolder contractViewHolder = new ContractViewHolder();
			contractViewHolder.contractItemLayout = convertView.findViewById(R.id.contractItemLayout);
			contractViewHolder.contractName = (TextView) convertView.findViewById(R.id.contractName);
			contractViewHolder.contractExpiration = (TextView) convertView.findViewById(R.id.contractExpiration);
			contractViewHolder.contractPrice = (TextView) convertView.findViewById(R.id.contractPrice);
			
			convertView.setTag(contractViewHolder);					
		}		
		
		final ContractListItem listItem = (ContractListItem) listData.get(position);
		final ContractViewHolder viewHolder = (ContractViewHolder) convertView.getTag();
		
		viewHolder.contractName.setText(listItem.contractName);
		viewHolder.contractExpiration.setText(context.getString(R.string.expiresOn, dateFormatter.format(listItem.contractExpiration)));
		viewHolder.contractPrice.setText(doubleFormatter.format(listItem.contractPrice));

		return convertView;
	}

	public void syncWithServer(OnSyncCompleteListener listener) {
		new SyncWithServer(listView, listener, settableList).execute();
	}	

	public interface OnSyncCompleteListener {
		void onSyncComplete();
	}

	static class SyncWithServer extends AsyncTask<Void, Void, List<ListItem>> {
		interface SettableList {
			void setList(List<ListItem> newListData);
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
		protected List<ListItem> doInBackground(Void... params) {						
			final List<ListItem> result = new ArrayList<ListItem>();	
			
			ContractListItem a = new ContractListItem();
			a.contractName = "Dow Jones above 25000";			
			a.contractExpiration = new Date(1388606400000L);
			a.contractPrice = 4.50;
			result.add(a);
			
			return result;
		}

		@Override
		protected void onPostExecute(List<ListItem> result) {
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
