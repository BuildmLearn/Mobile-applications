package org.buildmlearn.learnfrommap.helper;

import org.buildmlearn.learnfrommap.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.view.Window;

public class CustomDialog {
	
	public static void AboutDialog(Context context)
	{
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.argb(128, 0, 0, 0)));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.setContentView(R.layout.about_dialog);   
        dialog.show();
	}

}
