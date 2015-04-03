package com.crazyroba.qihoo;

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

public class TestQihoo extends RobaActivityInstrumentationTestCase2 {
	private static final String APK_VERSION = "300030210";

	private static boolean isFirstStart = false;
	
	//private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "cn.kuwo.player/cn.kuwo.ui.guide.GuideActivity";
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.qihoo.appstore.activities.LauncherActivity";
	private static final String TAG = "TestQihoo";
	private static final int WAITTIME = 5000;
	private static final int WAITCOUNT = 3;
	
	private static final int DOWNLOADAPK = 1;
	
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
	
	private void downloadAPK() {
		Random r = new Random();
		Log.d(TAG, "Start to download.");	
		
		int i = DOWNLOADAPK;
		for (; i > 0; i--) {
				Log.d(TAG, "Remain apk: " + i);
				for (int j = 5; j > 0; j--) {
					if (r.nextInt() % 2 == 0) {
						robaDrag(DragDirection.Top);
						solo.sleep(WAITTIME);
						Log.d(TAG, "Drag down");
					}
				}
				
				Log.d(TAG, "Click to download.");
				robaRandomClickInViews(robaGetViewsWithResourceId(TextView.class, "com.qihoo.appstore:id/app_status"));
				
				solo.sleep(WAITTIME * 5);
				Log.d(TAG, "wait for 10M tips.");
				
				if (solo.waitForText("��������")) {
					Log.d(TAG, "Continue found!");
					solo.goBack();
					solo.sleep(WAITTIME);
					i++;
					continue;
				}				
				
				int k = 10;
				while (!solo.waitForText("��װ����")) {
					Log.d(TAG, "Wait for the fast install. remain " + k);
					solo.sleep(WAITTIME);
					if (k == 0) {
						return;
					} else {
						k--;
					}
				}
				
				Log.d(TAG, "Fast install found!");
				solo.clickOnText("��װ����");
				solo.sleep(WAITTIME * 25);
			}
			
			
	}
	
	
	
	
	private void checkFirstStart() {
		Log.d(TAG, "Check apk version for first start: " + APK_VERSION + ".");
		
		if (APK_VERSION.equals("300030210")) {
			while (true) {
				if (solo.getCurrentActivity().toString().startsWith("com.qihoo.appstore.activities.LauncherActivity")) {
					Log.d(TAG, "Found LauncherActivity!");
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
