package com.crazyroba.ganji;

import android.util.Log;

import com.crazyroba.RobaActivityInstrumentationTestCase2;
import com.crazyroba.RobaActivityInstrumentationTestCase2.DragDirection;

public class GanjiRobaActivityInstrumentationTestCase2 extends RobaActivityInstrumentationTestCase2 {
	private static String TAG = "ganjiRoba";

	public GanjiRobaActivityInstrumentationTestCase2(String launcherActivityFullClassName, Class activityClass) {
        super(launcherActivityFullClassName, activityClass);
    }
	
	protected void firstStart(String version) {
		if (version.equals("6.1.1")) {
			Log.d(TAG, "First start of Ganji");
			
			solo.waitForActivity("NewFeatureActivity", 10000);
			
			Log.d(TAG, "New feature activity displayed.");
			
			while(true) {
				Log.d(TAG, "Drag up");
				robaDrag(DragDirection.Top);
				if (solo.waitForText("2015.01 赶集群组正式发布", 1, 2000)) {
					robaClickOnView("com.ganji.android:id/qunzu_feature_start_btn");
					break;
				}
			}
			
			solo.clickOnText("上海", 1, true);
			
			Log.d(TAG, "Go into City Activity");
			
			Log.d(TAG, "Finish first start activies");
			solo.clickOnText("上海", 1, true);
			return;
		}		
	}
	
}
