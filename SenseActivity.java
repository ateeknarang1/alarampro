package com.cgc.alarmpro;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SenseActivity extends Activity implements SensorEventListener {
	
    SensorManager sm1;
	
	Sensor s1;
	
	int left, right;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.senseact);
		
		
        sm1 = (SensorManager)getSystemService(SENSOR_SERVICE);
		
		s1 = sm1.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
	
		
	}
	

	@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			
			sm1.registerListener(this, s1, SensorManager.SENSOR_DELAY_UI);
			
			MusicHelper.playloop(this, R.raw.tibetan_06);
			
		}
	
	
	@Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			
			sm1.unregisterListener(this);
			
			
		}
	


	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onSensorChanged(SensorEvent arg0) {
		// TODO Auto-generated method stub
		
		int x = (int) arg0.values[0];
		
		if(x > 3)
		{
			left++;
		}
		if(x < -3)
		{
			right++;
		}
		
		if(left > 3 && right > 3)
		{
			MusicHelper.stop();
		}
		
		
		
		
	}
	
	
	

}
