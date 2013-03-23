package com.bitcoin.predictionmarket.activity;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.bitcoin.predictionmarket.R;
import com.bitcoin.predictionmarket.model.Contract;

public class ContractDetailsActivity extends SherlockFragmentActivity {
	public static void launch(Contract contract) {
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		setContentView(R.layout.contract_details);
	}

	@Override
	protected void onResume() {
		super.onResume();

		// Apparently works around a bug that manifests itself on some Android
		// versions.
		getSherlock().dispatchInvalidateOptionsMenu();		
	}
}
