package com.crazyroba.pingan;

import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crazyroba.RobaActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;

public class TestPingAn extends RobaActivityInstrumentationTestCase2 {
	private static final String APK_VERSION = "0422";

	private static boolean isFirstStart = false;
	
	//private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "cn.kuwo.player/cn.kuwo.ui.guide.GuideActivity";
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.pingan.yzt.SplashActivityPro";
	private static final String TAG = "TestPingAn";
	private static int WAITTIME = 5000;
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
	
	public TestPingAn() throws ClassNotFoundException {
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
		Log.d(TAG, "TestQihoo start.");
		checkFirstStart(); 
		Log.d(TAG, "Done!");
		checkSite();
		
		viewOrange();
		
		viewLux();
		
		
		
	
	}
	
	private void checkFirstStart() {
		Log.d(TAG, "Check apk version for first start: " + APK_VERSION + ".");
		
		if (APK_VERSION.equals("0422")) {
			solo.sleep(WAITTIME * 2);
		}
	}
	
	private void checkSite() {
		if (APK_VERSION.equals("0422")) {
			solo.clickOnScreen(200, 115);
			
			solo.sleep(WAITTIME);
			
			solo.clickOnScreen(236, 92);
			
			solo.sleep(WAITTIME);
			
			solo.clickOnScreen(55, 136);
			
			solo.sleep(WAITTIME);
			
			solo.clickOnScreen(145, 250);
			
			solo.sleep(WAITTIME);
			
			solo.clickOnScreen(150, 135);
			
			solo.sleep(WAITTIME);
			
			solo.clickOnScreen(160, 300);
			
			Random r = new Random();
			for (int i = 5; i > 0; i--) {
				if (r.nextInt() % 2 == 0) {
					robaDrag(DragDirection.Top);
					solo.sleep(WAITTIME);
				}
			}
			
			solo.clickOnScreen(160, 200);
			
			solo.sleep(WAITTIME);
			
			solo.goBack();
			
			for (int i = 5; i > 0; i--) {
				if (r.nextInt() % 2 == 0) {
					robaDrag(DragDirection.Top);
					solo.sleep(WAITTIME);
				}
			}
			
			solo.clickOnScreen(160, 200);
			
			solo.sleep(WAITTIME);
			
			solo.goBack();
			
			solo.clickOnScreen(280, 50);
			
			solo.sleep(WAITTIME);
			
		}
	}

	private void viewOrange() {
		solo.clickOnScreen(280, 115);
		
		solo.sleep(WAITTIME);
		
		robaDrag(DragDirection.Top);
		
		robaDrag(DragDirection.Top);
		
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(235, 180);
		
		solo.sleep(WAITTIME);
		
		solo.goBack();
		
		solo.sleep(WAITTIME);
	}

	private void viewLux() {
		solo.drag(300, 100, 115, 115, 5);
		
		solo.sleep(WAITTIME);
		
		
		solo.clickOnScreen(120, 200);
		
		solo.sleep(WAITTIME);
		
		for (int i = 5; i > 0; i--) {
			robaDrag(DragDirection.Top);
			
			solo.sleep(WAITTIME);
		}
		
		solo.goBack();
		
		solo.sleep(WAITTIME);		
	}
	
	private void viewStock() {
		solo.drag(300, 100, 115, 115, 5);
		
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(120, 115);
		
		solo.sleep(WAITTIME);
		
		solo.clickOnScreen(180, 130);
		
		for (int i = 10; i > 0; i--) {
			robaDrag(DragDirection.Top);
			
			solo.sleep(WAITTIME);
		}
		
		solo.goBack();
		
		solo.sleep(WAITTIME * 2);
		
		
	}
	
	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}