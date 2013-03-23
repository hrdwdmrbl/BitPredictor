package com.bitcoin.predictionmarket.activity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.bitcoin.predictionmarket.R;
import com.bitcoin.predictionmarket.model.Contract;

public class ContractDetailsActivity extends SherlockFragmentActivity {
	private static final String BUNDLE_CONTRACT = "BUNDLE_CONTRACT";	
	
	private final DateFormat dateFormatter = SimpleDateFormat.getDateInstance(SimpleDateFormat.FULL);
	
	public static void launch(Context context, Contract contract) {
		Intent intent = new Intent(context, ContractDetailsActivity.class);
		intent.putExtra(BUNDLE_CONTRACT, contract);	
		context.startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.contract_details);		
		
		ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
		
		Contract contract = (Contract) getIntent().getSerializableExtra(BUNDLE_CONTRACT);		
		((TextView)findViewById(R.id.contractName)).setText(contract.name);
		((TextView)findViewById(R.id.contractExpiration)).setText(getString(R.string.expiresOn, dateFormatter.format(contract.expiration)));		
	}

	@Override
	protected void onResume() {
		super.onResume();

		// Apparently works around a bug that manifests itself on some Android
		// versions.
		getSherlock().dispatchInvalidateOptionsMenu();		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			MainActivity.launchFromChild(this);
			finish();
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}	
}
