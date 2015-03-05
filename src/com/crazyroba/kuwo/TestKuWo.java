package com.crazyroba.kuwo;

import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crazyroba.RobaActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;

public class TestKuWo extends RobaActivityInstrumentationTestCase2 {
	private static final String APK_VERSION = "6.6.6.0";

	private static boolean isFirstStart = false;
	
	//private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "cn.kuwo.player/cn.kuwo.ui.guide.GuideActivity";
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "cn.kuwo.player.activities.MainActivity";
	private static final String TAG = "TestKuWo";

	private static Class launcherActivityClass;
	
	static {
		try {
			launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public TestKuWo() throws ClassNotFoundException {
		super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);
	}
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void test1() {
		CheckFirstStart(APK_VERSION);
		
		Log.d(TAG, "Play random music.");
		
		playMusic();
		
	}
	
	private void playMusic() {
		solo.waitForActivity("cn.kuwo.player.activities.MainActivity");
		
		robaRandomSleep(5);
		
		solo.clickOnText("曲库");
		
		robaWaitForViewByResourceId("cn.kuwo.player:id/tv_library_new_classify");
		
		robaRandomClickInViews(robaGetViewsWithResourceId(TextView.class, "cn.kuwo.player:id/tv_library_new_classify"));
		
		Log.d(TAG, "Random choose one song repo.");
		
		robaRandomSleep(5);
		
		robaWaitForViewByResourceId("cn.kuwo.player:id/list_music_item");
		
		robaRandomDrag(DragDirection.TopButtom, 2);	
		
		robaRandomClickInViews(robaGetViewsWithResourceId(RelativeLayout.class, "cn.kuwo.player:id/list_music_item"));		
		
		if (isFirstStart) {
			solo.waitForText("我知道了");
			//robaWaitForViewByResourceId("cn.kuwo.player:id/kuwo_alert_dialog_button1");
			solo.clickOnText("我知道了");
		}
		
		robaRandomSleep(50, 100);
		
		Log.d(TAG, "Random choose one song.");
		
		solo.goBack();	
	}
	
	private void CheckFirstStart(String version) {
		Log.d(TAG, "Check apk version for first start: " + version + ".");
		if (version.equals("6.6.6.0")) {
			if (robaWaitForViewByResourceId("cn.kuwo.player:id/guide_skipbtn") == true) {
				robaClickOnView("cn.kuwo.player:id/guide_skipbtn");
				isFirstStart = true;
				solo.waitForActivity("cn.kuwo.player.activities.MainActivity");
				
				robaClickOnView("cn.kuwo.player:id/only_wifi_guide_delete");
			}
		}	
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

}
