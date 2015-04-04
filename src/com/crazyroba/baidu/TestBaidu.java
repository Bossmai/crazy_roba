package com.crazyroba.baidu;

import android.util.Log;

import com.crazyroba.RobaActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;

public class TestBaidu extends RobaActivityInstrumentationTestCase2{
	private static final String APK_VERSION = "1001605m_6.2.0";
	
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.baidu.appsearch.LauncherActivity";
	private static final String TAG = "TestBaidu";
	
	private static final int WAITTIME = 5000;
	
	private static boolean isDebug = true;
	
	private static Class launcherActivityClass;
	
	static {
		try {
			launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public TestBaidu() throws ClassNotFoundException {
		super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);
	}
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void test1() {
		checkFirstStart();
		downloadAPK();
	}
	
	private void checkFirstStart() {
		if (APK_VERSION.equals("1001605m_6.2.0")) {
			Log.d(TAG, "Wait for the initialization");
			
			while (true) {
				if (solo.getCurrentActivity().toString().startsWith("com.baidu.appsearch.LauncherActivity")) {
					Log.d(TAG, "Guide page found!");
					break;
				} else if (solo.getCurrentActivity().toString().startsWith("com.baidu.appsearch.MainTabActivity")) {
					Log.d(TAG, "Main page found. Not first start.");
					return;
				}
				
				solo.sleep(WAITTIME);
				Log.d(TAG, "Wait for the guide page.");
			}
		}
		
		solo.sleep(WAITTIME);
		
		for (int i = 3; i > 0; i--) {
			robaDrag(DragDirection.Left);
			Log.d(TAG, "Scroll to right.");
			solo.sleep(WAITTIME);
		}
		
		solo.sleep(WAITTIME);
		
		while (true) {
			if (robaWaitForViewByResourceId("com.baidu.appsearch:id/nfw_btn")) {
				Log.d(TAG, "small window found! Click on it.");
				robaClickOnView("com.baidu.appsearch:id/nfw_btn");
				break;
			}
			
			solo.sleep(WAITTIME);
		}
		
		while (true) {
			if (robaWaitForViewByResourceId("com.baidu.appsearch:id/iknow")) {
				Log.d(TAG, "I know button found!. Click on it.");
				robaClickOnView("com.baidu.appsearch:id/iknow");
				break;
			}
			
			solo.sleep(WAITTIME);
		}
		
		
	}
	
	private void downloadAPK() {
		if (APK_VERSION.equals("1001605m_6.2.0")) {
			while (true) {
				if (solo.getCurrentActivity().toString().startsWith("com.baidu.appsearch.manage.mustinstall.MustInstallAppsDialogActivity")) {
					Log.d(TAG, "Must install apps found!");
					break;
				}
			}
			
			solo.goBack();
			solo.sleep(WAITTIME);		
		}
	}
	
	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}
