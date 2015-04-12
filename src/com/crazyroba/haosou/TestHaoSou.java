package com.crazyroba.haosou;

import android.util.Log;

import com.crazyroba.RobaActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;

public class TestHaoSou extends RobaActivityInstrumentationTestCase2 {
	private static final String APK_VERSION = "150412";
	
	private static boolean isFirstStart = false;
	
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.qihoo.haosou.activity.SplashActivity";
	private static final String TAG = "TestHaoSou";
	
	private static final int WAITTIME = 5000;
	private static final int WAITCOUNT = 3;
	
	private static boolean isDebug = true;
	private static Class launcherActivityClass;
	
	static {
		try {
			launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public TestHaoSou() throws ClassNotFoundException {
		super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);
	}
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void test1() {
		Log.d(TAG, "TestQihoo start.");
		checkFirstStart(); 
		Log.d(TAG, "Done!");
	}
	
	private void checkFirstStart() {
		
	}
	
	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
	
	


}
