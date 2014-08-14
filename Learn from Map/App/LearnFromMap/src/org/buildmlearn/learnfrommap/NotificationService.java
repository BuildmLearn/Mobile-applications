package org.buildmlearn.learnfrommap;

import org.buildmlearn.learnfrommap.service.AlarmService;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class NotificationService extends BroadcastReceiver {

	public void onReceive(Context context, Intent arg1) {
		Log.d("Autostart", "BOOT_COMPLETED broadcast received. Executing starter service.");
//		Toast.makeText(context, "Learn From Map", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(context, AlarmService.class);
		context.startService(intent);
	}

	private void showNotification(Context context) {


		Intent intent = new Intent(context, SplashActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
		NotificationCompat.Builder mBuilder =
				new NotificationCompat.Builder(context)
		.setSmallIcon(R.drawable.ic_launcher)
		.setContentTitle("My notification")
		.setContentIntent(pIntent)
		.setContentText("Hello World!");
		NotificationManager mNotificationManager =
				(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(23, mBuilder.build());
	}  

}
