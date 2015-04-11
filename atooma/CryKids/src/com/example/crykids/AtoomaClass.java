package com.example.crykids;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.atooma.plugin.IntentBasedTrigger;
import com.atooma.plugin.ParameterBundle;

public class AtoomaClass extends IntentBasedTrigger {

	public AtoomaClass(Context context, String id, int version) {
		super(context, id, version);
	}

	@Override
	public void defineUI() {
		// TODO Auto-generated method stub
		setIcon(R.drawable.ic_launcher);
		setTitle(R.string.trigger_name);
	}

	@Override
	public void onRevoke(String ruleId) {
		// TODO Auto-generated method stub

	}

	public String getIntentFilter() {
		return Intent.ACTION_SEND;
	}

	@Override
	public void onReceive(String ruleId, ParameterBundle parameters,
			Bundle bundleIntent) {
		// TODO Auto-generated method stub
		
	}
}