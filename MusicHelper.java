package com.cgc.alarmpro;

import java.io.IOException;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.preference.PreferenceManager;

public class MusicHelper {
	
	
	private static MediaPlayer mp1 = null;
	
	public static void play(Context con, int res)
	{
		
		stop();
		mp1 = MediaPlayer.create(con, res);
		mp1.start();
	
	}
	
	
	public static void playloop(Context con, int res)
	{
	stop();
	
	String tone = PreferenceManager.getDefaultSharedPreferences(con).getString("alarmtone", "");
	
	if(tone != null && !tone.equals(""))
	{
		
		mp1 = new MediaPlayer();
		Uri toneUri = Uri.parse(tone);
		if( toneUri != null)
		{
			try {
				mp1.setDataSource(con, toneUri);
				mp1.setAudioStreamType(AudioManager.STREAM_ALARM);
				mp1.setLooping(true);
				mp1.prepare();
				mp1.start();
				
				
				
				
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}
		
		
		
	}
	
	else
	{
	
		mp1 = MediaPlayer.create(con, res);
		mp1.setLooping(true);
		mp1.start();
	}
	}
	
	
	public static void stop()
	{
		if(mp1 != null)
		{
			mp1.stop();
			mp1.release();
			mp1 = null;		}
	}
	
	
	
	
	
	
	
	
	

}
