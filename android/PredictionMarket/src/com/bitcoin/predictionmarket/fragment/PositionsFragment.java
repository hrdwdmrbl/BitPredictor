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
import com.bitcoin.predictionmarket.adapter.PositionsAdapter;
import com.bitcoin.predictionmarket.adapter.PositionsAdapter.OnSyncCompleteListener;
import com.bitcoin.predictionmarket.model.Position;

public class PositionsFragment extends SherlockListFragment implements OnSyncCompleteListener {
	public static final String TAG = "ContractListFragment";
	
	private PositionsAdapter positionsAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View layout = inflater.inflate(R.layout.contract_list_fragment, container, false);

		positionsAdapter = new PositionsAdapter(this.getSherlockActivity(),
				(ListView) layout.findViewById(android.R.id.list));
		setListAdapter(positionsAdapter);
		registerForContextMenu(layout.findViewById(android.R.id.list));

		return layout;
	}

	private void syncWithServer() {
		if (positionsAdapter != null) {
			positionsAdapter.syncWithServer(this);
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
		Object object = positionsAdapter.getItem(position);
		
		if (object instanceof Position) {
			ContractDetailsActivity.launch(getSherlockActivity(), ((Position) object).contract);
		}
	}
}
