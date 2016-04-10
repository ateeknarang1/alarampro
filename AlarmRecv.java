package com.cgc.alarmpro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmRecv extends BroadcastReceiver{

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		
		
		Intent in = new Intent(arg0, AlarmTrigger.class);
		
		in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		arg0.startActivity(in);
		
		
		
	}
	
	

}
