package com.crazyroba.qqreader;

import java.util.Random;

import android.util.Log;

import com.crazyroba.RobaActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;

public class TestQQReader extends RobaActivityInstrumentationTestCase2 {
	private static final String APK_VERSION = "10001048";

	private static boolean isFirstStart = false;
	
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.qq.reader.activity.SplashActivity";
	private static final String TAG = "TestQQReader";
	private static int WAITTIME = 5000;
	private static final int WAITCOUNT = 3;
	
	private static final int VIEWPAGE = 50;
	
	private static boolean isDebug = true;

	private static Class launcherActivityClass;
	
	static {
		try {
			launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public TestQQReader() throws ClassNotFoundException {
		super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);
	}
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
		
		if (isDebug) {
			WAITTIME = WAITTIME * 2;
		}
	}
	
	public void test1() {
		checkFirst();
		
		solo.sleep(WAITTIME);
		
		readBook();
	}
	
	private void checkFirst() {
		if (APK_VERSION.equals("10001048")) {
			while (true) {
				if (solo.getCurrentActivity().toString().startsWith("com.qq.reader.activity.SplashActivity")) {
					break;
				}
			}
		}		
		
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(160, 400);
		
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(160, 400);
		
		Random r = new Random();
		
		int selectedIndex = r.nextInt(3);
		
		switch (r.nextInt(3)) {
		case 0:
			solo.clickOnScreen(160, 190);
			break;
		case 1:
			solo.clickOnScreen(160, 280);
			break;
		case 2:
			solo.clickOnScreen(160, 380);
			break;
		}
		
		solo.sleep(WAITTIME);
	}
	
	private void readBook() {
		solo.clickOnScreen(120, 400);
		
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(95, 188);
		
		Random r = new Random();
		
		switch (r.nextInt(3)) {
		case 0:
			solo.clickOnScreen(60, 270);
			break;
		case 1:
			solo.clickOnScreen(160, 270);
			break;
		case 2:
			solo.clickOnScreen(260, 270);
			break;
		}
		
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(50, 400);
		
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(290, 340);
		
		solo.sleep(WAITTIME);
		
		for (int i = VIEWPAGE; i > 0; i--) {
			robaDrag(DragDirection.Left);
			robaRandomSleep(5, 10);
		}
	}
	
	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}
