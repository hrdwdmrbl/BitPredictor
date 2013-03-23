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
import com.bitcoin.predictionmarket.model.Contract;

public class ContractListAdapter extends BaseAdapter {
	private final DecimalFormat doubleFormatter = new DecimalFormat("฿ #.##");
	private final DateFormat dateFormatter = SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG);
	
	private final Context context;
	private final LayoutInflater layoutInflater;
	private final ListView listView;		

	private List<Contract> listData = new ArrayList<Contract>();
	
	private final SettableList settableList = new SettableList() {
		@Override
		public void setList(List<Contract> newListData) {
			ContractListAdapter.this.setList(newListData);
		}
	};
	
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

	public List<Contract> getList() {
		return listData;
	}	

	void setList(List<Contract> newListData) {				
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
		
		final Contract contract = (Contract) listData.get(position);
		final ContractViewHolder viewHolder = (ContractViewHolder) convertView.getTag();
		
		viewHolder.contractName.setText(contract.name);
		viewHolder.contractExpiration.setText(context.getString(R.string.expiresOn, dateFormatter.format(contract.expiration)));
		viewHolder.contractPrice.setText(doubleFormatter.format(contract.price));

		return convertView;
	}

	public void syncWithServer(OnSyncCompleteListener listener) {
		new SyncWithServer(listView, listener, settableList).execute();
	}	

	public interface OnSyncCompleteListener {
		void onSyncComplete();
	}

	static class SyncWithServer extends AsyncTask<Void, Void, List<Contract>> {
		interface SettableList {
			void setList(List<Contract> newListData);
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
		protected List<Contract> doInBackground(Void... params) {						
			final List<Contract> result = new ArrayList<Contract>();	
			
			Contract a = new Contract();
			a.name = "Dow Jones above 25000";			
			a.expiration = new Date(1388606400000L);
			a.price = 4.50;
			result.add(a);
			
			return result;
		}

		@Override
		protected void onPostExecute(List<Contract> result) {
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
