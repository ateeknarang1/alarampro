package com.cgc.alarmpro;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class PuzzleSetAlarm extends Activity implements OnClickListener {
	
	Button b1,b2,b3;
	TextView t1;
	 public static final String PREF_FILE_NAME = "PrefFile";
	 private static final String ALARMTIME = "time";
	 private static final String ALARM_SMS = "smstime";
	 private static final String ALARM_SENS = "sentime3";
	 

	
	
	
	Calendar cal1;
	DateFormat dfm;
	 int altype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmset);
        
        altype= getIntent().getExtras().getInt("alarmtype");
        
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        
        t1 = (TextView)findViewById(R.id.textView1);
        
        
        		
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        
        cal1 = Calendar.getInstance();
        
        dfm = DateFormat.getInstance();
        
        String t = dfm.format(cal1.getTime());
        
        t1.setText(t);
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		SharedPreferences preferences = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
		
		switch(arg0.getId())
		{
		case R.id.button1:
			
			new TimePickerDialog(this,  timeCall, cal1.get(Calendar.HOUR_OF_DAY), 
					cal1.get(Calendar.MINUTE), false).show();
			
			
			break;

		case R.id.button2:
			
			new DatePickerDialog(this, dateCall, cal1.get(Calendar.YEAR), 
					cal1.get(Calendar.MONTH), cal1.get(Calendar.DAY_OF_MONTH)).show();
			
			break;
			

		case R.id.button3:
			
			AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
			
			if(altype == 1)
			{
			Intent int1 = new Intent(this, SmsalarmRecv.class);
			
			PendingIntent pi= PendingIntent.getBroadcast(this, 10, int1, 0);
			
			cal1.set(Calendar.SECOND, 0);
			
			am.set(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), pi);
			
			Toast.makeText(this, "SMS Alarm set", Toast.LENGTH_LONG).show();
			
			SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
			
			
			preferences.edit().putString(ALARM_SMS, sdf.format(cal1.getTime())).commit();
			
			
			}
			
			if(altype == 2)
			{
			Intent int1 = new Intent(this, ReceiverSense.class);
			
			PendingIntent pi= PendingIntent.getBroadcast(this, 0, int1, 0);
			
			cal1.set(Calendar.SECOND, 0);
			
			am.set(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), pi);
			
			Toast.makeText(this, "Sensor Alarm set", Toast.LENGTH_LONG).show();
			
			SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
			
			
			preferences.edit().putString(ALARM_SENS, sdf.format(cal1.getTime())).commit();
			
			
			}
			
			
			if(altype == 3)
			{
			Intent int1 = new Intent(this, AlarmRecv.class);
			
			PendingIntent pi= PendingIntent.getBroadcast(this, 0, int1, 0);
			
			cal1.set(Calendar.SECOND, 0);
			
			am.set(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), pi);
			
			Toast.makeText(this, "Puzzle Alarm set", Toast.LENGTH_LONG).show();
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
			
			preferences.edit().putString(ALARMTIME, sdf.format(cal1.getTime())).commit();
			
			
			}
			
			
			
			
			
			break;
			
				
			
			
		
		}
		
		
	
	
		
	
		
		
		
		
	}
    
	
	TimePickerDialog.OnTimeSetListener timeCall =
			new TimePickerDialog.OnTimeSetListener() {
				
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					// TODO Auto-generated method stub
					
					cal1.set(Calendar.HOUR_OF_DAY, hourOfDay);
					cal1.set(Calendar.MINUTE, minute);
					
					t1.setText(dfm.format(cal1.getTime()));
					
					
					
					
				}
			};
	
	
			DatePickerDialog.OnDateSetListener dateCall
			 = new DatePickerDialog.OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					// TODO Auto-generated method stub
					
					cal1.set(Calendar.YEAR, year);
					cal1.set(Calendar.MONTH, monthOfYear);
					cal1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
					
					t1.setText(dfm.format(cal1.getTime()));
					
					
					
				}
			};
	
	
}
