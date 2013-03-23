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

public class SellDialogFragment extends SherlockDialogFragment {	
	private static final String TAG = "SellDialogFragment";

	public static void show(FragmentManager fragmentManager) {
		final SellDialogFragment fragment = new SellDialogFragment();		
		fragment.show(fragmentManager, TAG);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final LayoutInflater inflater = (LayoutInflater) getSherlockActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View dialogContentView = inflater.inflate(R.layout.sell_dialog, null, false);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getSherlockActivity());
		builder.setTitle(R.string.sell);
		builder.setView(dialogContentView);
		builder.setPositiveButton(android.R.string.ok, null);
		builder.setPositiveButton(android.R.string.cancel, null);
		return builder.create();
	}
	
	
}
