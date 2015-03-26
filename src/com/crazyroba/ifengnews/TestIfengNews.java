package com.crazyroba.ifengnews;

import java.util.Random;

import android.util.Log;
import android.widget.LinearLayout;

import com.crazyroba.RobaActivityInstrumentationTestCase2;
import com.crazyroba.RobaActivityInstrumentationTestCase2.DragDirection;
import com.jayway.android.robotium.solo.Solo;

public class TestIfengNews extends RobaActivityInstrumentationTestCase2 {
	private static final String APK_VERSION = "4.4.1_3116";

	private static boolean isFirstStart = false;
	
	//private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "cn.kuwo.player/cn.kuwo.ui.guide.GuideActivity";
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.ifeng.news2.activity.SplashActivity";
	private static final String TAG = "TestIfengNews";
	private static final int WAITCOUNT = 10;
	private static final int NEWS_PER_PAGE = 3;
	private static final int TYPE_COUNT = 5;
	private static final int PLAYSONGS = 8;
	private static final int MIN_PLAYSEC = 120;
	private static final int MAX_PLAYSEC = 240;

	private static Class launcherActivityClass;
	
	static {
		try {
			launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public TestIfengNews() throws ClassNotFoundException {
		super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);
	}
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void test1() {
		try {
			checkFirstStart();
			readNews();
		} catch (Exception e) {
			Log.d(TAG, "Monkey failed.");
			solo.takeScreenshot();
		}		
	}
	
	private void readNews() {
		if (APK_VERSION.equals("4.4.1_3116")) {
			Log.d(TAG, "Read news");
			int i = WAITCOUNT;
			robaWaitForLoaded(5);
			while (true) {
				Log.d(TAG, "Wait For the news page");
				
				if (robaWaitForViewByResourceId("com.ifeng.news2:id/channel_list_top_wrapper")) {
					Log.d(TAG, "List top wrapper found.");
					break;
				}
				

				Log.d(TAG, "Remain wait count: " + i);
				i--;
				
			}
			
			i = TYPE_COUNT;
			
			Random r = new Random();
			
			while (i > 0) {
				Log.d(TAG, "Remain news type: " + i);
				int j = NEWS_PER_PAGE;
				while (j > 0) {
					Log.d(TAG, "Remain news in this type: " + j);
					for (int k = 5; k > 0; k--) {
						if (r.nextInt() % 2 == 0) {
							robaDrag(DragDirection.Top);
							robaRandomSleep(2, 5);
						}	
					}
					
					robaRandomClickInViews(robaGetViewsWithResourceId(LinearLayout.class, "com.ifeng.news2:id/channel_list_top_wrapper"));
					
					robaWaitForLoaded(10);
					
					for (int l = 8; l > 0; l--) {
						robaDrag(DragDirection.Top);
						robaRandomSleep(2, 5);
					}
					
					solo.goBack();
					robaRandomSleep(2, 5);
					
					j--;					
				}
				
				for (int k = 3; k > 0; k--) {
					if (r.nextInt() % 2 == 0) {
						robaDrag(DragDirection.Left);
						robaRandomSleep(2, 5);
					}
				}
				
			}
		}
	}
	
	private void checkFirstStart() {
		if (APK_VERSION.equals("4.4.1_3116")) {
			Log.d(TAG, "Check apk version for first start: " + APK_VERSION + ".");
			
			robaWaitForLoaded(5);
			Log.d(TAG, "Wait for the initialization.");
			
			int i = WAITCOUNT;
			
			while (i > 0) {
				Log.d(TAG, "Wait for the guide.");
				
				if (robaWaitForViewByResourceId("com.ifeng.news2:id/channel_list_top_wrapper") == false) {
					Log.d(TAG, "Guide page found!");
					
					int j = 8;
					while (j > 0) {
						robaDrag(DragDirection.Left);
						solo.sleep(2500);
						Log.d(TAG, "Drag to skip the guide page. Remain drag: " + j);
						j--;
					}
					
					robaWaitForLoaded(5);
					
					robaClickOnView("com.ifeng.news2:id/viewpager");
					Log.d(TAG, "Click on guide page.");
					
					robaWaitForLoaded(5);
					break;
				} 
				
				i--;
				solo.sleep(5000);
				Log.d(TAG, "Wait for the guide remain count: " + i);
			}		
		}
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}