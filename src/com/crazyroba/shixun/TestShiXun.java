package com.crazyroba.shixun;

import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crazyroba.RobaActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;

public class TestShiXun extends RobaActivityInstrumentationTestCase2 {
	private static final String APK_VERSION = "2008";

	private static boolean isFirstStart = false;
	
	//private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "cn.kuwo.player/cn.kuwo.ui.guide.GuideActivity";
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.news.newsshixun.FirstActivity";
	private static final String TAG = "TestShiXun";

	private static Class launcherActivityClass;
	
	static {
		try {
			launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public TestShiXun() throws ClassNotFoundException {
		super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);
	}
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void test1() {
		try {
		} catch (Exception e) {
			Log.d(TAG, "Monkey failed.");
			solo.takeScreenshot();
		}
	}
		
		
		
		//robaWaitForViewByResourceId("cn.kuwo.player:id/guide_skipbtn");
		//robaClickOnView("cn.kuwo.player:id/guide_skipbtn");
		
		//solo.waitForActivity("cn.kuwo.player.activities.MainActivity");
			
		//robaClickOnView("cn.kuwo.player:id/only_wifi_guide_delete");
		

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}

