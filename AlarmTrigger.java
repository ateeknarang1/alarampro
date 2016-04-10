package com.cgc.alarmpro;

import java.util.Random;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AlarmTrigger extends Activity implements OnClickListener{
	
	Button b1;
	EditText et1;
	TextView t1;
	
	int x,y,z,r;
	Random r1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		final Window win = getWindow();
		
		win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
				| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		
		win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
				      | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
		
		KeyguardManager manager1 = (KeyguardManager)getSystemService(KEYGUARD_SERVICE);
		KeyguardLock kl1 = manager1.newKeyguardLock("abc");
		
		kl1.disableKeyguard();
		
		
		
		
		
		
		
		
      
		
		setContentView(R.layout.alarmtrigger);
		
		r1 = new Random();
		
		
		x = r1.nextInt(20)+ 20;
		y = r1.nextInt(10);
		z = r1.nextInt(20);
		
		r = x + y - z;
		
		
		
		
		b1 = (Button)findViewById(R.id.button1);
		t1 = (TextView)findViewById(R.id.textView1);
		et1 = (EditText)findViewById(R.id.editText1);
		b1.setOnClickListener(this);
		
		t1.setText(String.valueOf(x) + " + " +  String.valueOf(y) + " - " + 
		           String.valueOf(z) + " = ?");
		
		
		
		MusicHelper.playloop(this, R.raw.game);
		
	}

	
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		int myans = Integer.parseInt(et1.getText().toString());
		
		if(myans == r)
		{
			MusicHelper.stop();
			finish();
		}
		
		

	}
	
	

}
