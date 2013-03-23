/*******************************************************************************
 * Copyright (C) 2012 Digipom Inc. All Rights Reserved. Unauthorized distribution is strictly prohibited.
 ******************************************************************************/
package com.bitcoin.predictionmarket.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class TabsAdapter extends FragmentPagerAdapter implements ActionBar.TabListener, ViewPager.OnPageChangeListener {
	private final Context context;
	private final ActionBar actionBar;
	private final ViewPager viewPager;
	private final ArrayList<String> tabs = new ArrayList<String>();	

	public TabsAdapter(SherlockFragmentActivity activity, ViewPager pager) {
		super(activity.getSupportFragmentManager());
		context = activity;
		actionBar = activity.getSupportActionBar();
		viewPager = pager;
		viewPager.setAdapter(this);
		viewPager.setOnPageChangeListener(this);
	}

	public void addTab(ActionBar.Tab tab, Class<?> clss) {		
		tabs.add(clss.getName());
		tab.setTabListener(this);		
		actionBar.addTab(tab);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return tabs.size();
	}

	@Override
	public Fragment getItem(int position) {		
		return Fragment.instantiate(context, tabs.get(position), null);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
	}

	@Override
	public void onPageSelected(int position) {
		actionBar.setSelectedNavigationItem(position);
	}

	@Override
	public void onPageScrollStateChanged(int state) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}
}
