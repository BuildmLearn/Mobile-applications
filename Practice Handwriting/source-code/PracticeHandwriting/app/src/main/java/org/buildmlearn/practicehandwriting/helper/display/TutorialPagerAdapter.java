package org.buildmlearn.practicehandwriting.helper.display;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.activities.selection.LanguageActivity;

import info.hoang8f.widget.FButton;

/**
 * PagerAdapter to display the tutorials
 */
public class TutorialPagerAdapter extends PagerAdapter {

    /**
     * The context of the Adapter
     */
    private Context mContext;

    /**
     * The number of fragments that are going to be displayed
     */
    public static int CHILD_COUNT = 15;

    /**
     * Constructor
     * @param context The context of the activity that called the Adapter
     */
    public TutorialPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_tutorial, null);
        if(position == CHILD_COUNT - 1) {
            LinearLayout parent = (LinearLayout)view.findViewById(R.id.TutorialRoot);
            FButton fButton = new FButton(mContext);
            fButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            fButton.setTextSize(20);
            fButton.setTextColor(Color.WHITE);
            fButton.setButtonColor(mContext.getResources().getColor(R.color.ButtonBg));
            fButton.setText("Continue");
            fButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, LanguageActivity.class));
                }
            });
            parent.addView(fButton);
        } else {
            int resourceId = mContext.getResources().getIdentifier("screen" + String.valueOf(position + 1), "drawable", mContext.getPackageName());
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext).build();
            ImageLoader.getInstance().init(config);
            final ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage("drawable://" + resourceId, ((ImageView) view.findViewById(R.id.TutorialImage)));
        }
        collection.addView(view, 0);
        return view;
    }

    /* (non-Javadoc)
     * @see android.support.v4.view.PagerAdapter#destroyItem(android.view.View, int, java.lang.Object)
     */
    @Override
    public void destroyItem(ViewGroup arg0, int arg1, Object arg2) {
        arg0.removeView((View) arg2);

    }

    /* (non-Javadoc)
     * @see android.support.v4.view.PagerAdapter#isViewFromObject(android.view.View, java.lang.Object)
     */
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;

    }

    /* (non-Javadoc)
     * @see android.support.v4.view.PagerAdapter#saveState()
     */
    @Override
    public Parcelable saveState() {
        return null;
    }

    /* (non-Javadoc)
     * @see android.support.v4.view.PagerAdapter#getCount()
     */
    @Override
    public int getCount() {
        return CHILD_COUNT;
    }

}