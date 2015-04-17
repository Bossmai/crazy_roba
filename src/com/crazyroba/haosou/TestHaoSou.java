package com.crazyroba.haosou;

import java.util.Random;

import android.util.Log;

import com.crazyroba.RobaActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;

public class TestHaoSou extends RobaActivityInstrumentationTestCase2 {
	private static final String APK_VERSION = "150416";
	
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
		clickHot();
		Log.d(TAG, "Done!");
	}
	
	private void checkFirstStart() {
		if (APK_VERSION.equals("150416")) {
			Log.d(TAG, "Click welcome");
			solo.clickOnScreen(160,  350);
			solo.sleep(WAITTIME);
		}
	}
	
	private void clickHot() {
		if (APK_VERSION.equals("150416")) {
			Log.d(TAG, "Click hot news");
			solo.clickOnScreen(160, 290);
			
			Log.d(TAG, solo.getCurrentActivity().toString());
			solo.sleep(WAITTIME * 2);
			
			
			Random r = new Random();
			
			solo.clickOnScreen(150, 190);
			solo.sleep(WAITTIME);
			solo.clickOnScreen(150, 190);
			solo.sleep(WAITTIME);
			for (int i = 5 + r.nextInt(2); i > 0; i--) {
				solo.scrollDown();
				robaRandomSleep(2, 5);
			}
			solo.goBack();
			
			solo.sleep(WAITTIME);
			
			solo.goBack();
			
			solo.sleep(WAITTIME);
			
			if (r.nextInt() % 2 == 0) {
				solo.clickOnScreen(150, 225);
				solo.sleep(WAITTIME);
				solo.clickOnScreen(150, 225);
				solo.sleep(WAITTIME);
			} else {
				solo.clickOnScreen(150, 270);
				solo.sleep(WAITTIME);
				solo.clickOnScreen(150, 270);
				solo.sleep(WAITTIME);
			}
			
			for (int i = 5 + r.nextInt(2); i > 0; i--) {
				solo.scrollDown();
				robaRandomSleep(2, 5);
			}
			solo.goBack();
			
			solo.sleep(WAITTIME);
			
			solo.goBack();
			
			solo.sleep(WAITTIME);
			
			if (r.nextInt() % 2 == 0) {
				solo.clickOnScreen(150, 300);
				solo.sleep(WAITTIME);
				solo.clickOnScreen(150, 300);
				solo.sleep(WAITTIME);
			} else {
				solo.clickOnScreen(150, 340);
				solo.sleep(WAITTIME);
				solo.clickOnScreen(150, 340);
				solo.sleep(WAITTIME);
			}
			
			for (int i = 5 + r.nextInt(2); i > 0; i--) {
				solo.scrollDown();
				robaRandomSleep(2, 5);
			}
			solo.goBack();
			
			solo.sleep(WAITTIME);
			
			solo.goBack();
			
			solo.sleep(WAITTIME);
		}
	}
	
	private void clickNovel() {
		if (APK_VERSION.equals("150416")) {
			so
		}
	}
	
	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
	
	


}
