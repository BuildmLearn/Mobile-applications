package com.buildmlearn.labeldiagram.widget;

import com.buildmlearn.labeldiagram.DiagramCategoryViewer;
import com.example.labelthediagram.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;

public class MessagePopupWindow extends Activity implements OnClickListener {

	ImageView cancelView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		init(savedInstanceState);
		initViews();
	}

	private void init(Bundle savedInstanceState) {

		setCustomTheme();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_popup_view);
		adjustDimOpacity();

	}

	private void setCustomTheme() {
		setTheme(R.style.DialogWithDimmedBack);
	}

	private void adjustDimOpacity() {
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.dimAmount = 0.9f;
		getWindow().setAttributes(lp);
	}
	
	private void initViews() {
		cancelView = (ImageView) findViewById(R.id.cancel_btn);
		cancelView.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
		
		case R.id.cancel_btn:
			
			Intent intent = new Intent(this, DiagramCategoryViewer.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
			
			break;

		default:
			break;
		}
	}
}
