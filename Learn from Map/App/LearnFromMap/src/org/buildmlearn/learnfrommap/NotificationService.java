package org.buildmlearn.learnfrommap;

import org.buildmlearn.learnfrommap.service.AlarmService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationService extends BroadcastReceiver {

	public void onReceive(Context context, Intent arg1) {
//		Log.d("Autostart", "BOOT_COMPLETED broadcast received. Executing starter service.");
//		Toast.makeText(context, "Learn From Map", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(context, AlarmService.class);
		context.startService(intent);
	}

}
