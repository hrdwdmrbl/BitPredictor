package com.bitcoin.predictionmarket.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.bitcoin.predictionmarket.R;

public class BuySellDialogFragment extends SherlockDialogFragment {	
	private static final String TAG = "SellDialogFragment";
	private static final String BUNDLE_IS_BUY = "BUNDLE_IS_BUY";

	public static void showBuy(FragmentManager fragmentManager) {
		final BuySellDialogFragment fragment = new BuySellDialogFragment();	
		final Bundle bundle = new Bundle();
		bundle.putBoolean(BUNDLE_IS_BUY, true);
		fragment.setArguments(bundle);
		fragment.show(fragmentManager, TAG);
	}
	
	public static void showSell(FragmentManager fragmentManager) {		
		final BuySellDialogFragment fragment = new BuySellDialogFragment();
		final Bundle bundle = new Bundle();
		bundle.putBoolean(BUNDLE_IS_BUY, false);
		fragment.setArguments(bundle);
		fragment.show(fragmentManager, TAG);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final boolean isBuy = getArguments().getBoolean(BUNDLE_IS_BUY, false);
		final LayoutInflater inflater = (LayoutInflater) getSherlockActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View dialogContentView = inflater.inflate(R.layout.buy_sell_dialog, null, false);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getSherlockActivity());
		
		if (isBuy) {
			builder.setTitle(R.string.buy);
		} else {
			builder.setTitle(R.string.sell);
		}
		
		builder.setView(dialogContentView);
		builder.setPositiveButton(android.R.string.ok, null);
		builder.setNegativeButton(android.R.string.cancel, null);
		return builder.create();
	}
	
	
}
