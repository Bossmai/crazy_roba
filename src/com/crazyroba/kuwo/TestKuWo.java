package com.crazyroba.kuwo;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crazyroba.RobaActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;

public class TestKuWo extends RobaActivityInstrumentationTestCase2 {
	private static final boolean firstStart = false;
	
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
		if (firstStart) {
			firstStart("6.6.6.0");
		}
		
		solo.waitForActivity("cn.kuwo.player.activities.MainActivity");
		
		robaRandomSleep(5);
		
		solo.clickOnText("Çú¿â");
		
		robaRandomClickInViews(robaGetViewsWithResourceId(TextView.class, "cn.kuwo.player:id/tv_library_new_classify"));
		
		robaRandomSleep(5);
		
		robaWaitForViewByResourceId("cn.kuwo.player:id/list_music_item");
		
		robaRandomClickInViews(robaGetViewsWithResourceId(RelativeLayout.class, "cn.kuwo.player:id/list_music_item"));
		
		robaRandomSleep(300);
		
		solo.goBack();	
		
	}
	
	private void firstStart(String version) {
		if (version.equals("6.6.6.0")) {
			robaWaitForViewByResourceId("cn.kuwo.player:id/guide_skipbtn");
			robaClickOnView("cn.kuwo.player:id/guide_skipbtn");
		}	
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

}
