package com.bitcoin.predictionmarket.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.bitcoin.predictionmarket.R;

public class BuySellSubmitSuccessfulDialogFragment extends SherlockDialogFragment {	
	private static final String TAG = "BuySellSubmitSuccessfulDialogFragment";
	private static final String BUNDLE_IS_BUY = "BUNDLE_IS_BUY";

	public static void showSuccessfulBuy(FragmentManager fragmentManager) {
		final BuySellSubmitSuccessfulDialogFragment fragment = new BuySellSubmitSuccessfulDialogFragment();	
		final Bundle bundle = new Bundle();
		bundle.putBoolean(BUNDLE_IS_BUY, true);
		fragment.setArguments(bundle);
		fragment.show(fragmentManager, TAG);
	}
	
	public static void showSuccessfulSell(FragmentManager fragmentManager) {		
		final BuySellSubmitSuccessfulDialogFragment fragment = new BuySellSubmitSuccessfulDialogFragment();
		final Bundle bundle = new Bundle();
		bundle.putBoolean(BUNDLE_IS_BUY, false);
		fragment.setArguments(bundle);
		fragment.show(fragmentManager, TAG);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final boolean isBuy = getArguments().getBoolean(BUNDLE_IS_BUY, false);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getSherlockActivity());
		builder.setIcon(android.R.drawable.ic_dialog_info);
		
		if (isBuy) {
			builder.setTitle(R.string.buyComplete);
			builder.setMessage(R.string.buySuccessful);
		} else {
			builder.setTitle(R.string.sellComplete);
			builder.setMessage(R.string.sellSuccessful);
		}		
		
		builder.setPositiveButton(android.R.string.ok, null);
		return builder.create();
	}
	
	
}
