package org.buildmlearn.practicehandwriting.practice;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.buildmlearn.practicehandwriting.R;

public class PracticeActivity extends Activity {

    //TODO add button animation and functionality, make options menu with next and previous, time dependant vibrations, error toast
    private Bitmap drawViewBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        Intent intent = getIntent();

        DrawingView draw = (DrawingView) findViewById(R.id.drawing);
        TextView canvasText = (TextView) findViewById(R.id.canvas);

        canvasText.setText(intent.getStringExtra(getResources().getString(R.string.practice_string)));
        drawViewBitmap = takeScreenshot(R.id.canvas);
        draw.setbitmap(drawViewBitmap);
    }

    private Bitmap takeScreenshot(int ResourceID) {
        View v1 = findViewById(ResourceID);
        //getting size of view
        v1.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        //selecting the part to be saved
        v1.layout(0, 0, v1.getMeasuredWidth(), v1.getMeasuredHeight());
        v1.setDrawingCacheEnabled(true);
        final Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache()); // actually taking the screen shot
        v1.setDrawingCacheEnabled(false);
        return bitmap;
    }

}
