package com.crazyroba.supervpn;

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

public class TestSuperVPN extends RobaActivityInstrumentationTestCase2 {
	private static final String APK_VERSION = "472885";

	private static boolean isFirstStart = false;
	
	//private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "cn.kuwo.player/cn.kuwo.ui.guide.GuideActivity";
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.jrzheng.supervpn.view.MainActivity";
	private static final String TAG = "TestSuperVPN";

	private static Class launcherActivityClass;
	
	static {
		try {
			launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public TestSuperVPN() throws ClassNotFoundException {
		super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);
	}
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void test1() {
		while (true) {
			Log.d(TAG, "Wait for connect button.");
			if (solo.waitForText("Connect")) {
				Log.d(TAG, "Connected found!");
				break;
			}
		}
		
		solo.clickOnText("Connect");
		Log.d(TAG, "Click Button");
		solo.sleep(5000);
		Log.d(TAG, "Trusted");

		robaClickOnView("com.android.vpndialogs:id/check");
		solo.sleep(5000);
		
		Log.d(TAG, "Confirm!");

		robaClickOnView("android:id/button1");
		solo.sleep(5000);
	
	}
	

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}