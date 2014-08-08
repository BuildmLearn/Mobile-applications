package org.buildmlearn.learnfrommap.service;

import org.buildmlearn.learnfrommap.R;
import org.buildmlearn.learnfrommap.SplashActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class NotificationBarAlarm extends BroadcastReceiver {

	NotificationManager notifyManager;

	@Override
	public void onReceive(Context context, Intent intent) {

		Log.d("NotificationAlarm", "onReceive");
		Toast.makeText(context, "Notification Manager", Toast.LENGTH_SHORT).show();
		Intent intent1 = new Intent(context, SplashActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent1, 0);
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
