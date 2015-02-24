package com.crazyroba.ganji;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.crazyroba.*;
import com.jayway.android.robotium.solo.Solo;

public class TestGanji extends RobaActivityInstrumentationTestCase2 {
	private static final boolean firstStart = false;
	private static final String runType = ""; //randomClick
	
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.ganji.android.control.LaunchActivity";
	private static final String TAG = "TestGanJi";
	
	
	private static Class launcherActivityClass;
	
	static {
		try {
			launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public TestGanji() throws ClassNotFoundException {
		super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);
	}
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testSample() {
		//com.ganji.android/.control.CityActivity com.ganji.android:id/qunzu_feature_start_btn
		if (firstStart) {
			firstStart("6.1.1");
		}
		
		int step = 25;
		while (step > 0) {
			randomClick();
			step--;
			Log.d(TAG, "Remain steps: " + step);
		}	
	}
	
	private void firstStart(String version) {
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
	
	private void randomClick() {
		Log.d(TAG, "Click on random view.");
		try {
			robaRandomClickOnView();
		} catch (Exception e) {
			;
		}		
		
		Log.d(TAG, "Sleep for 10 seconds.");
		solo.sleep(10000);
	}
	
	private void findRandomJob() {
		Log.d(TAG, "Find random job.");
		solo.waitForText("全职工作");
		
		solo.clickOnText("全职工作");
		
		solo.waitForActivity("NewFeatureActivity", 10000);
	}
	
	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}
