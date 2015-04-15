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
	private static final String APK_VERSION = "472885";

	private static boolean isFirstStart = false;
	
	//private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "cn.kuwo.player/cn.kuwo.ui.guide.GuideActivity";
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.qihoo.appstore.activities.LauncherActivity";
	private static final String TAG = "TestQihoo";
	private static final int WAITTIME = 5000;
	private static final int WAITCOUNT = 3;
	
	private static final int DOWNLOADAPK = 3;
	
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
		if (APK_VERSION.equals("300030210")) {
			boolean hasFastInstallSetted = false;
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
					
					solo.sleep(WAITTIME * 2);
					Log.d(TAG, "Close ad.");
					solo.goBack();
					solo.sleep(WAITTIME * 2);
					Log.d(TAG, "Click to download.");
					
					robaRandomClickInViews(robaGetViewsWithResourceId(TextView.class, "com.qihoo.appstore:id/app_status"));
					
					if (isDebug) {
						solo.sleep(WAITTIME * 5);
					} else {
						solo.sleep(WAITTIME * 15);
					}
					
					solo.sleep(WAITTIME * 4);
				}		
		} else if (APK_VERSION.equals("491610")) {
			Log.d(TAG, solo.getCurrentActivity().toString());
			/*solo.clickOnScreen(300, 350);
			while (true) {
				if (solo.getCurrentActivity().toString().startsWith("com.qihoo.appstore.activities.PopluarizeActivity")) {
					break;
				}
			}
			solo.sleep(5000);
			
			solo.clickOnScreen(175, 430);
			Log.d(TAG, "Go into xianjian.");
			solo.sleep(10000);
			Log.d(TAG, "Download xianjian.");
			solo.clickOnScreen(290, 160);
			Log.d(TAG, "Go back.");
			solo.sleep(10000);
			solo.goBack();
			solo.sleep(5000);*/
			
			boolean hasFastInstallSetted = false;
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
					
					solo.sleep(WAITTIME * 2);
					Log.d(TAG, "Close ad.");
					solo.goBack();
					solo.sleep(WAITTIME * 2);
					Log.d(TAG, "Click to download.");
					
					robaRandomClickInViews(robaGetViewsWithResourceId(TextView.class, "com.qihoo.appstore:id/app_status"));
					
					if (solo.getCurrentActivity().toString().startsWith("com.qihoo.appstore.activities.PopluarizeActivity")) {
						Log.d(TAG, "xianjian again. Go back.");
						solo.goBack();
						i++;
						continue;
					}
					if (isDebug) {
						solo.sleep(WAITTIME * 5);
					} else {
						solo.sleep(WAITTIME * 15);
					}
					
					solo.sleep(WAITTIME * 4);
				}
		} else if (APK_VERSION.equals("472885")) {
			Log.d(TAG, solo.getCurrentActivity().toString());
			/*solo.clickOnScreen(300, 350);
			while (true) {
				if (solo.getCurrentActivity().toString().startsWith("com.qihoo.appstore.activities.PopluarizeActivity")) {
					break;
				}
			}
			solo.sleep(5000);
			solo.clickOnScreen(175, 430);
			Log.d(TAG, "Go into xianjian.");
			solo.sleep(10000);
			Log.d(TAG, "Download xianjian.");
			solo.clickOnScreen(290, 160);
			Log.d(TAG, "Go back.");
			solo.sleep(10000);
			solo.goBack();
			solo.sleep(5000);*/
			
			boolean hasFastInstallSetted = false;
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
					
					solo.sleep(WAITTIME * 2);
					Log.d(TAG, "Close ad.");
					solo.goBack();
					solo.sleep(WAITTIME * 2);
					Log.d(TAG, "Click to download.");
					
					try {
						robaRandomClickInViews(robaGetViewsWithResourceId(TextView.class, "com.qihoo.appstore:id/app_status"));
					} catch (Exception e) {
						i++;
						robaDrag(DragDirection.Top);
						continue;
					}
					
					solo.sleep(10000);
					
					if (solo.getCurrentActivity().toString().startsWith("com.qihoo.appstore.activities.PopluarizeActivity")) {
						Log.d(TAG, "xianjian again. Go back.");
						solo.goBack();
						i++;
						continue;
					}
					if (isDebug) {
						solo.sleep(WAITTIME * 5);
					} else {
						solo.sleep(WAITTIME * 15);
					}
					
					solo.sleep(WAITTIME * 4);
				}
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
			Log.d(TAG, "Close LauncherActivity!");
			solo.sleep(WAITTIME * 5);
			Log.d(TAG, "Close LauncherActivity again for update!");
			solo.goBack();
			solo.sleep(WAITTIME);
		} else if (APK_VERSION.equals("491610")) {
			while (true) {
				if (solo.getCurrentActivity().toString().startsWith("com.qihoo.appstore.activities.LauncherActivity")) {
					Log.d(TAG, "Found LauncherActivity!");
					break;
				}
			}
			
			solo.goBack();
			Log.d(TAG, "Close LauncherActivity!");
			solo.sleep(WAITTIME);
			Log.d(TAG, "Go to config.");
			solo.clickOnScreen(290, 400);
			solo.sleep(5000);
			Log.d(TAG, "Go to config.");
			solo.clickOnScreen(290, 50);
			solo.sleep(5000);
			Log.d(TAG, "Go to app setting.");
			solo.clickOnScreen(160, 160);
			solo.sleep(5000);
			Log.d(TAG, "Close Open installation.");
			solo.clickOnScreen(280, 100);
			solo.sleep(5000);
			Log.d(TAG, "Go back.");
			solo.goBack();
			solo.sleep(5000);
			Log.d(TAG, "Go back.");
			solo.goBack();
			solo.sleep(5000);
			Log.d(TAG, "Go to index.");
			solo.clickOnScreen(32, 400);
			solo.sleep(5000);

		} else if (APK_VERSION.equals("472885")) {
			while (true) {
				if (solo.getCurrentActivity().toString().startsWith("com.qihoo.appstore.activities.LauncherActivity")) {
					Log.d(TAG, "Found LauncherActivity!");
					break;
				}
			}
			
			solo.sleep(10000);
			Log.d(TAG, "Drag to left.");
			robaDrag(DragDirection.Left);
			solo.sleep(5000);
			
			Log.d(TAG, "Drag to left.");
			robaDrag(DragDirection.Left);
			solo.sleep(5000);
			
			Log.d(TAG, "Click date!");
			solo.clickOnScreen(150, 360);
			solo.sleep(5000);
			
			Log.d(TAG, "Go to config.");
			solo.clickOnScreen(290, 400);
			solo.sleep(5000);
			
			Log.d(TAG, "Go to config.");
			solo.clickOnScreen(290, 50);
			solo.sleep(5000);
			
			Log.d(TAG, "click down");
			solo.sendKey(solo.DOWN);
			solo.sleep(5000);
			
			Log.d(TAG, "click down");
			solo.sendKey(solo.DOWN);
			solo.sleep(5000);
			
			Log.d(TAG, "click down");
			solo.sendKey(solo.DOWN);
			solo.sleep(5000);
			
			Log.d(TAG, "slient install.");
			solo.clickOnScreen(280, 170);
			solo.sleep(5000);
			
			solo.goBack();
			
			Log.d(TAG, "Back to index");
			solo.clickOnScreen(30, 400);
			solo.sleep(5000);
			
		}
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}
