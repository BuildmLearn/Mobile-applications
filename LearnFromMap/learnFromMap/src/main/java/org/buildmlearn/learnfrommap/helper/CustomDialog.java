package org.buildmlearn.learnfrommap.helper;

import org.buildmlearn.learnfrommap.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

/**
 * This class contains various custom dialogs
 * 
 * @author Abhishek
 *
 */
public class CustomDialog {
	
	/**
	 * This functions displays the about dialog box
	 * 
	 * @param context
	 */
	public static void AboutDialog(Context context)
	{
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.about_dialog);   
        dialog.show();
	}

}
