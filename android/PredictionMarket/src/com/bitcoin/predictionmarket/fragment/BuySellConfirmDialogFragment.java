package com.bitcoin.predictionmarket.fragment;

import java.text.DecimalFormat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.bitcoin.predictionmarket.R;

public class BuySellConfirmDialogFragment extends SherlockDialogFragment {	
	private static final String TAG = "BuySellConfirmDialogFragment";
	private static final String BUNDLE_IS_BUY = "BUNDLE_IS_BUY";
	private static final String BUNDLE_QUANTITY = "BUNDLE_QUANTITY";
	private static final String BUNDLE_PRICE = "BUNDLE_PRICE";
	
	private final DecimalFormat doubleFormatter = new DecimalFormat("฿ 0.00");

	public static void showBuyConfirm(FragmentManager fragmentManager, double quantity, double price) {
		final BuySellConfirmDialogFragment fragment = new BuySellConfirmDialogFragment();	
		final Bundle bundle = new Bundle();
		bundle.putBoolean(BUNDLE_IS_BUY, true);
		bundle.putDouble(BUNDLE_QUANTITY, quantity);
		bundle.putDouble(BUNDLE_PRICE, price);
		fragment.setArguments(bundle);
		fragment.show(fragmentManager, TAG);
	}
	
	public static void showSellConfirm(FragmentManager fragmentManager, double quantity, double price) {		
		final BuySellConfirmDialogFragment fragment = new BuySellConfirmDialogFragment();
		final Bundle bundle = new Bundle();
		bundle.putBoolean(BUNDLE_IS_BUY, false);
		bundle.putDouble(BUNDLE_QUANTITY, quantity);
		bundle.putDouble(BUNDLE_PRICE, price);
		fragment.setArguments(bundle);
		fragment.show(fragmentManager, TAG);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final boolean isBuy = getArguments().getBoolean(BUNDLE_IS_BUY, false);
		final double quantity = getArguments().getDouble(BUNDLE_QUANTITY, 0.0);
		final double price = getArguments().getDouble(BUNDLE_PRICE, 0.0);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getSherlockActivity());
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		
		if (isBuy) {
			builder.setTitle(R.string.confirmBuy);
			builder.setMessage(getSherlockActivity().getString(R.string.confirmBuyOrder, 
					doubleFormatter.format(quantity),
					doubleFormatter.format(price),
					doubleFormatter.format(quantity * price)));
		} else {
			builder.setTitle(R.string.confirmSell);
			builder.setMessage(getSherlockActivity().getString(R.string.confirmSellOrder, 
					doubleFormatter.format(quantity),
					doubleFormatter.format(price),
					doubleFormatter.format(quantity * price)));
		}		
		
		builder.setPositiveButton(android.R.string.ok, new OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (isBuy) {
					BuySellSubmitSuccessfulDialogFragment.showSuccessfulBuy(getFragmentManager());
				} else {
					BuySellSubmitSuccessfulDialogFragment.showSuccessfulSell(getFragmentManager());
				}
				
			}
		});
		builder.setNegativeButton(android.R.string.cancel, null);
		return builder.create();
	}
	
	
}
