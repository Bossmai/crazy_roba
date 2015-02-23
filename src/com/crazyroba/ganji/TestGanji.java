package com.crazyroba.ganji;

import android.util.Log;

import com.crazyroba.*;
import com.jayway.android.robotium.solo.Solo;

public class TestGanji extends RobaActivityInstrumentationTestCase2 {
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
		firstStart();
		
	}
	
	private void firstStart() {
		Log.d(TAG, "First start of Ganji");
		
		solo.waitForActivity("NewFeatureActivity", 10000);
		
		Log.d(TAG, "New feature activity displayed.");
		
		while(true) {
			Log.d(TAG, "Drag up");
			robaDrag(DragDirection.Top);
			if (solo.waitForText("2015.01 �ϼ�Ⱥ����ʽ����", 1, 2000)) {
				robaClickOnView("com.ganji.android:id/qunzu_feature_start_btn");
				break;
			}
		}
		
		solo.clickOnText("�Ϻ�", 1, true);
		
		Log.d(TAG, "Go into City Activity");
		
		Log.d(TAG, "Finish first start activies");
	}
	
	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}
