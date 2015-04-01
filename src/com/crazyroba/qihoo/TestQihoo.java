package com.crazyroba.qihoo;

import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crazyroba.RobaActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;

public class TestQihoo extends RobaActivityInstrumentationTestCase2 {
	private static final String APK_VERSION = "300030210";

	private static boolean isFirstStart = false;
	
	//private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "cn.kuwo.player/cn.kuwo.ui.guide.GuideActivity";
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.qihoo.appstore.activities.LauncherActivity";
	private static final String TAG = "TestQihoo";
	private static final int WAITTIME = 5000;
	private static final int WAITCOUNT = 3;
	
	private static final int DOWNLOADAPK = 2;
	
	private static boolean isDebug = true;

	private static Class launcherActivityClass;
	
	static {
		try {
			launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public TestQihoo() throws ClassNotFoundException {
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
		
		downloadAPK();
	
	}
	
	private void searchAndDownload() {
	}
	
	private void closeWifi() {
		solo.scrollToSide(solo.LEFT);
		solo.sleep(WAITTIME);
		solo.scrollToBottom();
		solo.sleep(WAITTIME);
		solo.clickOnText("设置");
		solo.sleep(WAITTIME);
	}
	
	private void downloadAPK() {
		Random r = new Random();
		Log.d(TAG, "Start to download.");	
		
		for (int i = DOWNLOADAPK; i > 0; i--) {
			
				for (int j = r.nextInt(3); j > 0; j--) {
					if (r.nextInt() % 2 == 0) {
						robaDrag(DragDirection.Top);
						Log.d(TAG, "Drag down");
					}
				}
			}
			
			Log.d(TAG, "Click to download.");
			robaRandomClickInViews(robaGetViewsWithResourceId(TextView.class, "com.qihoo.appstore:id/app_status"));
			
			solo.sleep(WAITTIME);
			if (solo.waitForText("继续下载")) {
				Log.d(TAG, "Continue found!");
				
			}
				
			
			if (isDebug) {
				solo.sleep(WAITTIME * 12);
			} else {
				solo.sleep(WAITTIME * 12 * 5);
			}
			
			if (solo.waitForText("秒装功能")) {
				Log.d(TAG, "Fast install found!");
				solo.clickOnText("取消");
				solo.sleep(WAITTIME);
			}
	}
	
	
	
	
	private void checkFirstStart() {
		Log.d(TAG, "Check apk version for first start: " + APK_VERSION + ".");
		
		if (APK_VERSION.equals("300030210")) {
			while (true) {
				if (solo.getCurrentActivity().toString().startsWith("com.qihoo.appstore.activities.LauncherActivity")) {
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
