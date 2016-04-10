package com.cgc.alarmpro;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class ResetAlarms extends BroadcastReceiver{

	
	 private static final String ALARMTIME = "time";
	 private static final String ALARMMODE = "mode";
	 private static final String ALARM_SMS = "smstime";
	 private static final String ALARM_SENS = "sentime3";
	 
	 private static final String DEFTIME = "notset";
	 private static final String DEF_SMS = "notset";
	 private static final String DEF_SENS = "notset";
	 
	 
	 
	 
		public static final String PREF_FILE_NAME = "PrefFile";
		
		
		
		
	
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
	   
		String sms,puzz,sens;
		//nm = PreferenceManager.getDefaultSharedPreferences(arg0).getString(ALARMTIME, "");
		SharedPreferences prefs = arg0.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        puzz = prefs.getString(ALARMTIME, "notset");
        sms = prefs.getString(ALARM_SMS, "notset");
        sens = prefs.getString(ALARM_SENS, "notset");
        
		
        Toast.makeText(arg0, puzz, Toast.LENGTH_LONG).show();
		
		AlarmManager am = (AlarmManager)arg0.getSystemService(Context.ALARM_SERVICE);
		
		if(!(puzz.equals("notset")))
		{
			Intent int1 = new Intent(arg0, AlarmRecv.class);
			
			PendingIntent pi= PendingIntent.getBroadcast(arg0, 10, int1, 0);
			
			
			
			
			
			 try {
				cal1.setTime(sdf.parse(puzz));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			// cal2.setTime(sdf.parse(sdf.format(cal2.getTime())));
			 try  
			 {  
			 Date date1 = sdf.parse(sdf.format(cal1.getTime()));  
			 Date date2 = sdf.parse(sdf.format(cal2.getTime()));
			 
			 if (date1.getTime() > date2.getTime())  
			 {  
			 System.out.println("First date is later.");
			 am.set(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), pi);
				
				Toast.makeText(arg0, "alarm for puzzle", Toast.LENGTH_LONG).show();
			 }  
			 else if(date2.getTime() > date1.getTime())  
			 {  
			 System.out.println("Second date is later.");  
			 }  
			 }
			 
			 catch (ParseException pe)  
			 {  
			 System.out.println("Bad format.");  
			 }  
			
		}
		
		
		
	
		if(!(sms.equals("notset")))
		{
			Intent int1 = new Intent(arg0, SmsalarmRecv.class);
			
			PendingIntent pi= PendingIntent.getBroadcast(arg0, 11, int1, 0);
			
			
			
			
			
			 try {
				cal1.setTime(sdf.parse(puzz));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			 
			 
			 try  
			 {  
			 Date date1 = sdf.parse(sdf.format(cal1.getTime()));  
			 Date date2 = sdf.parse(sdf.format(cal2.getTime()));
			 
			 if (date1.getTime() > date2.getTime())  
			 {  
			 System.out.println("First date is later.");
			 am.set(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), pi);
					
				Toast.makeText(arg0, "alarm for sms", Toast.LENGTH_LONG).show();
			 }  
			 else if(date2.getTime() > date1.getTime())  
			 {  
			 System.out.println("Second date is later.");  
			 }  
			 }
			 
			 catch (ParseException pe)  
			 {  
			 System.out.println("Bad format.");  
			 }  
			 
			 
			 
			 
			
			
		}
		
		
		
		if(!(sens.equals("notset")))
		{
			Intent int1 = new Intent(arg0, ReceiverSense.class);
			
			PendingIntent pi= PendingIntent.getBroadcast(arg0, 12, int1, 0);
			
			
			
			
			
			 try {
				cal1.setTime(sdf.parse(puzz));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			 
			 

			
			 try  
			 {  
			 Date date1 = sdf.parse(sdf.format(cal1.getTime()));  
			 Date date2 = sdf.parse(sdf.format(cal2.getTime()));
			 
			 if (date1.getTime() > date2.getTime())  
			 {  
			 System.out.println("First date is later.");
			 am.set(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), pi);
					
				Toast.makeText(arg0, "alarm for sensors", Toast.LENGTH_LONG).show();
			 }  
			 else if(date2.getTime() > date1.getTime())  
			 {  
			 System.out.println("Second date is later.");  
			 }  
			 }
			 
			 catch (ParseException pe)  
			 {  
			 System.out.println("Bad format.");  
			 }  
		}
		
		
		
		
		
		
		
	}
	
	
	

}
