/*******************************************************************************
 * Copyright (C) 2012 Digipom Inc. All Rights Reserved. Unauthorized distribution is strictly prohibited.
 ******************************************************************************/
package com.bitcoin.predictionmarket.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.bitcoin.predictionmarket.R;
import com.bitcoin.predictionmarket.adapter.TabsAdapter;
import com.bitcoin.predictionmarket.fragment.ContractListFragment;

public class MainActivity extends SherlockFragmentActivity {	
	private ViewPager viewPager;
	private TabsAdapter tabsAdapter;
	
	public static void launchFromChild(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
	}

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		setContentView(R.layout.main);

		final ActionBar bar = getSupportActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		viewPager = (ViewPager) findViewById(R.id.pager);

		tabsAdapter = new TabsAdapter(this, viewPager);
//		tabsAdapter.addTab(bar.newTab().setText(R.string.recorder), RecorderFragment.class);
		tabsAdapter.addTab(bar.newTab().setText(R.string.contracts), ContractListFragment.class);		
	}

	@Override
	protected void onResume() {
		super.onResume();

		// Apparently works around a bug that manifests itself on some Android
		// versions.
		getSherlock().dispatchInvalidateOptionsMenu();		
	}
}
