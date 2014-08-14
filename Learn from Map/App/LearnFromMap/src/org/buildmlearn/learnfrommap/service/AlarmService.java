package org.buildmlearn.learnfrommap.service;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class AlarmService extends Service {
	
	private static final String TAG = "AlarmService";


	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Intent i = new Intent(getApplicationContext(), NotificationBarAlarm.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Toast.makeText(getApplicationContext(), "In Alarm Service", Toast.LENGTH_LONG).show();
		PendingIntent pi = PendingIntent.getBroadcast(this.getApplicationContext(), 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
		// Repeat the notification every 15 seconds (15000)
		AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Calendar firingCal= Calendar.getInstance();
		Calendar currentCal = Calendar.getInstance();

		firingCal.set(Calendar.HOUR, 2); // At the hour you wanna fire
		firingCal.set(Calendar.MINUTE, 0); // Particular minute
		firingCal.set(Calendar.SECOND, 0); // particular second
		//firingCal.set(Calendar.)

		long intendedTime = firingCal.getTimeInMillis();
		long currentTime = currentCal.getTimeInMillis();

		if(intendedTime >= currentTime) 
		{
			am.setRepeating(AlarmManager.RTC_WAKEUP, intendedTime, AlarmManager.INTERVAL_DAY, pi);

		}
		else{
		   firingCal.add(Calendar.DAY_OF_MONTH, 1);
		   intendedTime = firingCal.getTimeInMillis();
		   am.setRepeating(AlarmManager.RTC_WAKEUP, intendedTime, AlarmManager.INTERVAL_DAY, pi);
		}
		
		//am.setRepeating(AlarmManager.RTC_WAKEUP, 1407511828802l, AlarmManager.INTERVAL_DAY, pi);
		Toast.makeText(this, "My Service started", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onStart");
		return Service.START_NOT_STICKY;
	}
	
	@Override
	public void onDestroy() {
	Toast.makeText(this, TAG + " stopped", Toast.LENGTH_LONG).show();
	Log.d(TAG, "onDestroy");
	}
	
	

}
