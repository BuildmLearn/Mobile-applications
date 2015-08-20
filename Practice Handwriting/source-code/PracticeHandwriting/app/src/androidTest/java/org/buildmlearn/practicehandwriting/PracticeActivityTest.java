package org.buildmlearn.practicehandwriting;

import android.os.Environment;
import android.widget.TextView;

import org.buildmlearn.practicehandwriting.activities.PracticeActivity;
import org.buildmlearn.practicehandwriting.activities.SplashActivity;
import org.buildmlearn.practicehandwriting.helper.DrawingView;
import org.buildmlearn.practicehandwriting.testing.PracticeBaseActivityTest;

import java.io.File;
import java.util.Arrays;
import java.util.Random;

/**
 * Testing scripts for basic testing of PracticeActivity
 */
public class PracticeActivityTest extends PracticeBaseActivityTest {

    /**
     * Constructor
     */
    public PracticeActivityTest() {
        super(PracticeActivity.class);
    }

    /**
     * Function to test the pressing of the "Next" button in the action bar
     */
    public void testNext() {
        int next = Arrays.asList(SplashActivity.CHARACTER_LIST).indexOf(activity.getPracticeString());
        next = (next + 1) % SplashActivity.CHARACTER_LIST.length;

        solo.clickOnView(solo.getView(R.id.action_next));
        solo.sleep(1000);

        assertEquals(SplashActivity.CHARACTER_LIST[next], activity.getPracticeString());
    }

    /**
     * Function to test the pressing of the "Previous" button in the action bar
     */
    public void testPrevious() {
        int previous = Arrays.asList(SplashActivity.CHARACTER_LIST).indexOf(activity.getPracticeString());
        previous = (previous + SplashActivity.CHARACTER_LIST.length - 1) % SplashActivity.CHARACTER_LIST.length;

        solo.clickOnView(solo.getView(R.id.action_previous));
        solo.sleep(1000);

        assertEquals(SplashActivity.CHARACTER_LIST[previous], activity.getPracticeString());
    }

    @Override
    public void testDonePress() {
        super.testDonePress();
        assertEquals("Score: " + activity.getDrawingView().score(), ((TextView) solo.getView(R.id.score_and_timer_View)).getText());
        assertEquals("Best: " + SplashActivity.mDbHelper.getScore(activity.getPracticeString()), ((TextView) solo.getView(R.id.best_score_View)).getText());
    }

    /**
     * Function to test the pressing of the save button
     */
    public void testSavePress() {
        solo.clickOnView(solo.getView(R.id.done_save_button));
        solo.sleep(1000);
        solo.clickOnView(solo.getView(R.id.done_save_button));
        solo.sleep(500);
        assertTrue(solo.searchText("Trace Saved"));

        File mediaStorageDir = new File(Environment.getExternalStorageDirectory() + File.separator + mContext.getResources().getString(R.string.app_name));
        assertTrue(mediaStorageDir.exists() && mediaStorageDir.isDirectory());
        File newestFile = null;
        for(File file : mediaStorageDir.listFiles())
            if (newestFile == null || file.lastModified() > newestFile.lastModified())
                newestFile = file;
        assertNotNull(newestFile);
        assertEquals(newestFile.getName().split("_")[3].split("\\.")[0], activity.getPracticeString());
        newestFile.delete();
    }

    /**
     * Function to test the pressing of the reset button
     */
    public void testResetPress() {
        DrawingView drawingView = activity.getDrawingView();
        for(int i=0;i<30;i++)
            solo.clickOnScreen(new Random().nextInt(SplashActivity.mDisplayMetrics.widthPixels/2) + SplashActivity.mDisplayMetrics.widthPixels/4,new Random().nextInt(SplashActivity.mDisplayMetrics.heightPixels/2) + SplashActivity.mDisplayMetrics.heightPixels/4);
        solo.clickOnView(solo.getView(R.id.reset_button));
        solo.sleep(100);
        assertEquals(drawingView.score(), 0.0f);
        assertEquals(drawingView.getTouchBounds()[0], SplashActivity.mDisplayMetrics.widthPixels);
        assertEquals(drawingView.getTouchBounds()[1], SplashActivity.mDisplayMetrics.heightPixels);
        assertEquals(drawingView.getTouchBounds()[2], -1);
        assertEquals(drawingView.getTouchBounds()[3],-1);
        assertEquals(drawingView.getTouchesList().size(), 0);
    }
}