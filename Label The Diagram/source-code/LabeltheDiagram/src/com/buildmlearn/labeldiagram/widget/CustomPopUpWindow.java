package com.buildmlearn.labeldiagram.widget;

import com.example.labelthediagram.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

public class CustomPopUpWindow extends Activity implements OnClickListener {

	private PopupWindow popupWindow;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.popup_window_view);
		
		
		/*popupWindow = new PopupWindow(layout, 300, 470, true);
		popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
		
		TextView tv = (TextView) layout.findViewById(R.id.badge_txt);
		tv.setText("Master in Biology");
		
		TextView tv1 = (TextView) layout.findViewById(R.id.badge_desc);
		tv1.setText("Badge description");*/
		
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.cancel_btn:
			popupWindow.dismiss();
			break;

		default:
			break;
		}
		
	}

}
