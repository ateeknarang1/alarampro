package com.cgc.alarmpro;



import java.util.List;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Color;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SmsSender extends Activity implements OnClickListener {
	
	
	private static final int PICK_CONTACT =1;
	
	EditText et1,et2;
	TextView t1;
	Button b1,b2;
	
	public static final String SMS_REPORT = "com.example.sendsms.sms_report";
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smssender);
        
        et1 = (EditText)findViewById(R.id.editText1);
        et2 = (EditText)findViewById(R.id.editText2);
        t1 = (TextView)findViewById(R.id.textView2);
        b1 = (Button)findViewById(R.id.button1);
        
        b1.setOnClickListener(this);
    
        
        b2 = (Button)findViewById(R.id.button2);
        
        b2.setOnClickListener(bl1);
        
        String p = PreferenceManager.getDefaultSharedPreferences(this).getString("phone", "Not stored  yet");
        String m = PreferenceManager.getDefaultSharedPreferences(this).getString("mess", "Not stored  yet");
        
        et1.setText(p);
        et2.setText(m);
        
        senMess();
        
        
    }

    OnClickListener bl1 = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			Intent in = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
			startActivityForResult(in, PICK_CONTACT);
			
			
			
			
		}
	};
    
   
	
	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
    	String phonenumber = "";
    	switch(requestCode)
    	{
    	case PICK_CONTACT:
    		
    		boolean hasPh = false;
    	
    		
    		Cursor cursor  = managedQuery(data.getData(), null, null, null, null);
    		
    		while(cursor.moveToNext())
    		{
    			String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
    			
    			String name  = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
    			
    			String hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
    			
    			if(hasPhone.equalsIgnoreCase("1"))
    			{
    				hasPh = true;
    			}
    		
    			
    			Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
    					null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
    					                 null, null);
    			
    			
    			while(phones.moveToNext())
    			{
    				
    				phonenumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
    				
    			}
    			
    			phones.close();
    			
    			
    			
    			
    			
    			
    		}
    		
    		
    		
    		
    		break;
    	
    	
    	}
    	
    	et1.setText(phonenumber);
    	
    	
    	
    }
    
    
    

	@Override
	public void onClick(View arg0) {
		
		
	}
	
	private void senMess()
	{
		// TODO Auto-generated method stub
				if(TextUtils.isEmpty(et1.getText()))
				{
					Toast.makeText(this, "Please enter Phone", Toast.LENGTH_LONG).show();
					return;
				}
				if(TextUtils.isEmpty(et2.getText()))
				{
					Toast.makeText(this, "Please enter Message", Toast.LENGTH_LONG).show();
					return;
				}
				
				
				b1.setEnabled(false);
				
				SmsManager sm1 = SmsManager.getDefault();
				List<String>  messages = sm1.divideMessage(et2.getText().toString());
				
				String phone = et1.getText().toString();
				
				
				for(String eachmessage : messages)
				{
					PendingIntent p1 = PendingIntent.getBroadcast(this, 0, new Intent(SMS_REPORT), 0);
					
					sm1.sendTextMessage(phone, null, eachmessage, p1, null);
				}
				
				
				registerReceiver(br1, new IntentFilter(SMS_REPORT));
				
	}
    
	
	BroadcastReceiver br1 = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String message = null;
			boolean error = true;
			
			switch(getResultCode())
			{
			case Activity.RESULT_OK:
				
				error = false;
				message = "SMS Sent"; 
				break;
			
			case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
				
				message = "SMS Sent"; 
				break;
				

			case SmsManager.RESULT_ERROR_NO_SERVICE:
				
				message = "No network coverage"; 
				break;
				

			case SmsManager.RESULT_ERROR_NULL_PDU:
				message = "Data not found"; 
				
				break;
				

			case SmsManager.RESULT_ERROR_RADIO_OFF:
				
				message = "Phone in Airplane mode"; 
				break;
				
				
				
				
				
			}
			
			t1.setText(message);
			t1.setTextColor(error? Color.RED : Color.GREEN);
			
			b1.setEnabled(true);
			
			
		}
	};
	
	
	
	
}
