package com.way.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class SMSReceiver extends BroadcastReceiver {
	
	private final WayService wayService;

	public SMSReceiver(){
		wayService = new WayService(new SMSService(), new GeoLocationService(new MyGeoCoder()));
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		
		Bundle bundle = intent.getExtras();
		if(bundle == null)return;
		
		wayService.reply(makeWaySMS(bundle), context);
	}

	private WaySms makeWaySMS(Bundle bundle) {
		return new WaySMSFactory().create((Object[])bundle.get("pdus"));
	}

}
