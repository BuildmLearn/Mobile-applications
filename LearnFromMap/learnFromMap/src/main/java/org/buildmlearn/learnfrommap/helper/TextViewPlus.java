package org.buildmlearn.learnfrommap.helper;

import org.buildmlearn.learnfrommap.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Custom implementation of textview with fonts
 * 
 * @author Abhishek
 *
 */
public class TextViewPlus extends TextView {

	public TextViewPlus(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setCustomFont(context, attrs);
	}

	public TextViewPlus(Context context, AttributeSet attrs) {
		super(context, attrs);
		setCustomFont(context, attrs);
	}

	public TextViewPlus(Context context) {
		super(context);
	}
	
	private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.TextViewPlus);
        String customFont = a.getString(R.styleable.TextViewPlus_customFont);
        setCustomFont(ctx, customFont);
        a.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        Typeface tf = null;
        try {
        tf = Typeface.createFromAsset(ctx.getAssets(), asset);  
        } catch (Exception e) {
//            Log.e("TextViewPlus Error", "Could not get typeface: "+e.getMessage());
            return false;
        }

        setTypeface(tf);  
        return true;
    }
    
    @Override
    public void setBackgroundResource(int resid) {
        int pl = getPaddingLeft();
        int pt = getPaddingTop();
        int pr = getPaddingRight();
        int pb = getPaddingBottom();

        super.setBackgroundResource(resid);

        this.setPadding(pl, pt, pr, pb);
    }

	@SuppressLint("NewApi") @Override
	public void setBackground(Drawable background) {

        int pl = getPaddingLeft();
        int pt = getPaddingTop();
        int pr = getPaddingRight();
        int pb = getPaddingBottom();
		super.setBackground(background);
		this.setPadding(pl, pt, pr, pb);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setBackgroundDrawable(Drawable background) {

        int pl = getPaddingLeft();
        int pt = getPaddingTop();
        int pr = getPaddingRight();
        int pb = getPaddingBottom();
		super.setBackgroundDrawable(background);
		this.setPadding(pl, pt, pr, pb);
	}

}
