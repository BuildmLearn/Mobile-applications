package org.buildmlearn.practicehandwriting;

import android.os.Environment;

import org.buildmlearn.practicehandwriting.activities.practice.TimeTrialActivity;
import org.buildmlearn.practicehandwriting.testing.PracticeBaseActivityTest;

import java.io.File;

/**
 * Testing scripts for basic testing of TimeTrialActivity
 */
public class TimeTrialTest extends PracticeBaseActivityTest {

    public TimeTrialTest() {
        super(TimeTrialActivity.class);
    }

    @Override
    public void testDonePress() {
        String practiceString = activity.getPracticeString();
        File tempSaveDir = new File(Environment.getExternalStorageDirectory() + File.separator + mContext.getResources().getString(R.string.app_name) +((TimeTrialActivity) activity).getSaveDir());
        super.testDonePress();
        assertTrue(tempSaveDir.exists() && tempSaveDir.isDirectory());
        File newestFile = null;
        for(File file : tempSaveDir.listFiles())
            if (newestFile == null || file.lastModified() > newestFile.lastModified())
                newestFile = file;
        assertNotNull(newestFile);
        assertEquals(newestFile.getName().split("_")[0], practiceString);
        for (File file : tempSaveDir.listFiles())
            file.delete();
        tempSaveDir.delete();
    }

    /**
     * Function to check the deletion of the temp directory when the activity is exited
     * @throws Throwable
     */
    public void testTempDirectoryDeleteOnBackPress() throws Throwable {
        final File tempSaveDir = new File(Environment.getExternalStorageDirectory() + File.separator + mContext.getResources().getString(R.string.app_name) +((TimeTrialActivity) activity).getSaveDir());
        for(int i=0;i<10;i++)
            super.testDonePress();
        assertTrue(tempSaveDir.exists() && tempSaveDir.isDirectory());
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.onBackPressed();
                assertFalse(tempSaveDir.exists());
            }
        });
    }
}
