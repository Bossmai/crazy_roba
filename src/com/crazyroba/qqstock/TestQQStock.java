package com.crazyroba.qqstock;

import java.util.Random;

import android.app.Activity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.crazyroba.RobaActivityInstrumentationTestCase2;
import com.crazyroba.RobaActivityInstrumentationTestCase2.DragDirection;
import com.jayway.android.robotium.solo.Solo;

public class TestQQStock extends RobaActivityInstrumentationTestCase2 {
	private static final String APK_VERSION = "4.4.1_3116";

	private static boolean isFirstStart = false;
	
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.tencent.portfolio.CSplashActivity";
	private static final String TAG = "TestQQStock";
	private static final int WAITTIME = 5000;
	
	private static final boolean isDebug = false;

	private static Class launcherActivityClass;
	
	static {
		try {
			launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public TestQQStock() throws ClassNotFoundException {
		super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);
	}
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void test1() {
		checkFirst();
		
		addStock();
		
		viewDetailed();
		
		viewNews();
		
		viewMarkets();
	}
	
	private void checkFirst() {
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(160, 270);
		
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(160, 315);
		
		solo.sleep(WAITTIME);
		
		solo.waitForText("ÔÝ²»¸üÐÂ");
		
		solo.clickOnScreen(225, 320);
		
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(264, 350);
		
		solo.sleep(WAITTIME);

		for (int i = 3; i > 0; i--) {
			solo.goBack();
			solo.sleep(WAITTIME);
		}
	}
	
	private void addStock() {
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(160, 175);
		
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(30, 300);
		
		solo.sleep(WAITTIME);
		
		Random r = new Random();
		
		solo.clickOnScreen(290, 140);
		
		solo.sleep(WAITTIME);
		
		for (int i = 5; i > 0; i--) {
			if (r.nextInt() % 2 == 0) {
				solo.clickOnScreen(290, 140 + 50 * i);
				solo.sleep(WAITTIME);
			}
		}
		
		solo.clickOnScreen(280, 92);
		
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(160, 92);
		
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(30, 345);
		
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(290, 140);
		
		solo.sleep(WAITTIME);
		
		for (int i = 5; i > 0; i--) {
			if (r.nextInt() % 2 == 0) {
				solo.clickOnScreen(290, 140 + 50 * i);
				solo.sleep(WAITTIME);
			}
		}		
		
		solo.goBack();
		
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(230, 252);	
		
	}
	
	private void viewDetailed() {
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(160, 131);
		
		solo.sleep(WAITTIME);
		
		solo.goBack();
		
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(160, 131);
		
		solo.sleep(WAITTIME);
		
		for (int i = 3; i > 0; i--) {
			solo.scrollDown();
			solo.sleep(WAITTIME);
		}
		
		solo.goBack();
		
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(160, 180);
		
		solo.sleep(WAITTIME);
		
		for (int i = 3; i > 0; i--) {
			solo.scrollDown();
			solo.sleep(WAITTIME);
		}
		
		solo.goBack();
		
		solo.sleep(WAITTIME);
	}

	private void viewNews() {
		solo.clickOnScreen(120, 400);
		
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(150, 120);
		
		solo.sleep(WAITTIME);
		
		for (int i = 5; i > 0; i--) {
			solo.scrollDown();
			
			solo.sleep(WAITTIME);
		}
		
		solo.goBack();
		
		solo.clickOnScreen(139, 48);
		
		solo.sleep(WAITTIME);
		
		solo.goBack();		
		
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(150, 120);
		
		solo.sleep(WAITTIME);
		
		for (int i = 5; i > 0; i--) {
			solo.scrollDown();
			
			solo.sleep(WAITTIME);
		}
		
		solo.goBack();
	}
	
	private void viewMarkets() {
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(200, 400);
		
		solo.sleep(WAITTIME);
		
		for (int i = 10; i > 0; i--) {
			solo.scrollDown();
			
			solo.sleep(WAITTIME);
		}
	}
	
	@Override
	public void tearDown() throws Exception {
		/*Activity a = super.getActivity();
		if (a != null) {
		a.finish();
		setActivity(null);
		solo.finishOpenedActivities();
		}*/
	}
}
