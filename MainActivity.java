package com.cgc.alarmpro;

import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {
	
	ImageView im1,im2,im3;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		im1 = (ImageView)findViewById(R.id.img_sms);
		im1.setOnClickListener(this);
		
		im2 = (ImageView)findViewById(R.id.img_puzz);
		im2.setOnClickListener(this);
		
		im3 = (ImageView)findViewById(R.id.img_shake);
		im3.setOnClickListener(this);
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		
		int i = item.getItemId();
		
		if(i == R.id.action_settings)
		{

			Intent in = new Intent(this, Pref.class);
			startActivity(in);
			
		}
		else if(i == R.id.item1 )
		{
			Intent in = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
			startActivityForResult(in, 1);
		}
		
		
		return super.onOptionsItemSelected(item);
	}
	

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId())
		{
		case R.id.img_sms:
			Intent in2 = new Intent(this, PuzzleSetAlarm.class);
			in2.putExtra("alarmtype", 1);
			startActivity(in2);
			break;
			

		case R.id.img_puzz:
			Intent in = new Intent(this, PuzzleSetAlarm.class);
			in.putExtra("alarmtype", 3);
			startActivity(in);
			break;

		case R.id.img_shake:
			Intent in3 = new Intent(this, PuzzleSetAlarm.class);
			in3.putExtra("alarmtype", 2);
			startActivity(in3);
			break;
			
		}
		
		
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == RESULT_OK)
		{
			if(requestCode == 1)
			{
				
				Uri tone = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
				PreferenceManager.getDefaultSharedPreferences(this).edit().putString("alarmtone", tone.toString()).commit();
				
				
				
			}
			
			
		}
		
		
		
		
	}
	

}
