/*******************************************************************************
 * Copyright (C) 2012 Digipom Inc. All Rights Reserved. Unauthorized distribution is strictly prohibited.
 ******************************************************************************/
package com.bitcoin.predictionmarket.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.bitcoin.predictionmarket.R;
import com.bitcoin.predictionmarket.activity.ContractDetailsActivity;
import com.bitcoin.predictionmarket.adapter.ContractListAdapter;
import com.bitcoin.predictionmarket.adapter.ContractListAdapter.OnSyncCompleteListener;
import com.bitcoin.predictionmarket.model.Contract;

public class ContractListFragment extends SherlockListFragment implements OnSyncCompleteListener {
	public static final String TAG = "ContractListFragment";
	
	private ContractListAdapter contractListAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View layout = inflater.inflate(R.layout.contract_list_fragment, container, false);

		contractListAdapter = new ContractListAdapter(this.getSherlockActivity(),
				(ListView) layout.findViewById(android.R.id.list));
		setListAdapter(contractListAdapter);
		registerForContextMenu(layout.findViewById(android.R.id.list));

		return layout;
	}

	private void syncWithServer() {
		if (contractListAdapter != null) {
			contractListAdapter.syncWithServer(this);
		}
	}

	@Override
	public void onResume() {
		super.onResume();		

		syncWithServer();		
	}

	@Override
	public void onSyncComplete() {
		if (getSherlockActivity() != null) {
			if (getSherlockActivity() != null) {
				getSherlockActivity().invalidateOptionsMenu();
			}			
		}
	}	

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		final Contract contract = (Contract) contractListAdapter.getItem(position);
		ContractDetailsActivity.launch(getSherlockActivity(), contract);
	}
}
