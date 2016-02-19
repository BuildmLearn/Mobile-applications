package org.buildmlearn.learnfrommap.service;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class AlarmService extends Service {
	


	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Intent i = new Intent(getApplicationContext(), NotificationBarAlarm.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent pi = PendingIntent.getBroadcast(this.getApplicationContext(), 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
		// Repeat the notification every 15 seconds (15000)
		AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Calendar firingCal= Calendar.getInstance();
		Calendar currentCal = Calendar.getInstance();

		firingCal.set(Calendar.HOUR_OF_DAY, 19); // At the hour you wanna fire
		firingCal.set(Calendar.MINUTE, 30); // Particular minute
		firingCal.set(Calendar.SECOND, 0); // particular second
		//firingCal.set(Calendar.)

		long intendedTime = firingCal.getTimeInMillis();
		long currentTime = currentCal.getTimeInMillis();

//		Toast.makeText(this, "Intended Time: " + firingCal.getTime() + "\nCurrent Time: " + currentCal.getTime(), Toast.LENGTH_SHORT).show();
//		Toast.makeText(this, "Intended Time: " + firingCal.getTime() + "\nCurrent Time: " + currentCal.getTime(), Toast.LENGTH_SHORT).show();
//		Toast.makeText(this, "Intended Time: " + firingCal.getTime() + "\nCurrent Time: " + currentCal.getTime(), Toast.LENGTH_SHORT).show();
//		Toast.makeText(this, "Intended Time: " + firingCal.getTime() + "\nCurrent Time: " + currentCal.getTime(), Toast.LENGTH_SHORT).show();
//		Toast.makeText(this, "Intended Time: " + firingCal.getTime() + "\nCurrent Time: " + currentCal.getTime(), Toast.LENGTH_SHORT).show();
//		Toast.makeText(this, "Intended Time: " + firingCal.getTime() + "\nCurrent Time: " + currentCal.getTime(), Toast.LENGTH_SHORT).show();
//		Toast.makeText(this, "Intended Time: " + firingCal.getTime() + "\nCurrent Time: " + currentCal.getTime(), Toast.LENGTH_SHORT).show();
//		Toast.makeText(this, "Intended Time: " + intendedTime + "\nCurrent Time: " + currentTime, Toast.LENGTH_SHORT).show();
//		Toast.makeText(this, "Intended Time: " + intendedTime + "\nCurrent Time: " + currentTime, Toast.LENGTH_SHORT).show();
//		Toast.makeText(this, "Intended Time: " + intendedTime + "\nCurrent Time: " + currentTime, Toast.LENGTH_SHORT).show();
//		Toast.makeText(this, "Intended Time: " + intendedTime + "\nCurrent Time: " + currentTime, Toast.LENGTH_SHORT).show();
//		Toast.makeText(this, "Intended Time: " + intendedTime + "\nCurrent Time: " + currentTime, Toast.LENGTH_SHORT).show();
//		Toast.makeText(this, "Intended Time: " + intendedTime + "\nCurrent Time: " + currentTime, Toast.LENGTH_SHORT).show();
//		Toast.makeText(this, "Intended Time: " + intendedTime + "\nCurrent Time: " + currentTime, Toast.LENGTH_SHORT).show();
		
		
		if(intendedTime >= currentTime) 
		{
			am.setRepeating(AlarmManager.RTC_WAKEUP, intendedTime, AlarmManager.INTERVAL_DAY, pi);

		}
		else{
		   firingCal.add(Calendar.DAY_OF_MONTH, 1);
		   intendedTime = firingCal.getTimeInMillis();
		   am.setRepeating(AlarmManager.RTC_WAKEUP, intendedTime, AlarmManager.INTERVAL_DAY, pi);
		}
		
		return Service.START_NOT_STICKY;
	}
	
	@Override
	public void onDestroy() {
	}
	
	

}
