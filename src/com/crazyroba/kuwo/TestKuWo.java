package com.crazyroba.kuwo;

import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crazyroba.RobaActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;

public class TestKuWo extends RobaActivityInstrumentationTestCase2 {
	private static final String APK_VERSION = "6.6.7_712";

	private static boolean isFirstStart = false;
	
	//private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "cn.kuwo.player/cn.kuwo.ui.guide.GuideActivity";
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "cn.kuwo.player.activities.EntryActivity";
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
		try {
			checkFirstStart();
			isFirstStart = true;
			Log.d(TAG, "Play random music.");		
			playMusic();
			Log.d(TAG, "Monkey success.");
		} catch (Exception e) {
			Log.d(TAG, "Monkey failed.");
			solo.takeScreenshot();
		}
		
		
		
		//robaWaitForViewByResourceId("cn.kuwo.player:id/guide_skipbtn");
		//robaClickOnView("cn.kuwo.player:id/guide_skipbtn");
		
		//solo.waitForActivity("cn.kuwo.player.activities.MainActivity");
			
		//robaClickOnView("cn.kuwo.player:id/only_wifi_guide_delete");
		
		
		
	}
	
	private void playMusic() {
		if (APK_VERSION.equals("6.3.9.0_changxin09")) {
			robaDrag(DragDirection.Right);
			
			solo.waitForText("日最新单曲");
			
			Log.d(TAG, "Go into newest single song.");
			
			solo.clickOnText("日最新单曲");
			
			robaWaitForLoaded(5);
			//solo.sleep(5000);
			
			//solo.clickOnText("全部");
			
			Log.d(TAG, "Play all.");
			
			//Double click to avoid the ADs.
			
			if (isFirstStart) {
				robaClickOnView("cn.kuwo.player:id/library_music_list_batch_play_text");
			}
			
			robaClickOnView("cn.kuwo.player:id/library_music_list_batch_play_text");
			
			//Click to avoid tips.
			
			if (solo.waitForText("我知道了")) {
				
				
				solo.clickOnText("我知道了");			
			}
			
			Log.d(TAG, "Play songs.");
			
			int remainSongs = 10;
			
			while (remainSongs > 0) {
				robaRandomSleep(50, 100);
				if (remainSongs == 10) {
					solo.goBack();
				}
				
				robaClickOnView("cn.kuwo.player:id/Main_BtnNext");
				
				remainSongs--;
				Log.d(TAG, "Play next song. Remain song count:" + remainSongs +".");
				
			}
			
			robaWaitForLoaded(5);
			Log.d(TAG, "Done.");
		} else if (APK_VERSION.equals("6.6.7_712")) {			
			solo.waitForText("日最新单曲");
			
			solo.clickOnText("日最新单曲");			
			Log.d(TAG, "Go into newest single song.");
			
			robaWaitForLoaded(5);
			//solo.sleep(5000);
			
			//solo.clickOnText("全部");
			
			Log.d(TAG, "Play all.");
			
			//Double click to avoid the ADs.
			
			if (isFirstStart) {
				robaClickOnView("cn.kuwo.player:id/library_music_list_batch_play_text");
			}
			
			robaClickOnView("cn.kuwo.player:id/library_music_list_batch_play_text");
			
			//Click to avoid tips.
			
			if (solo.waitForText("我知道了")) {
				
				
				solo.clickOnText("我知道了");			
			}
			
			Log.d(TAG, "Play songs.");
			
			int remainSongs = 10;
			
			while (remainSongs > 0) {
				robaRandomSleep(50, 100);
				if (remainSongs == 10) {
					solo.goBack();
				}
				
				robaClickOnView("cn.kuwo.player:id/Main_BtnNext");
				
				remainSongs--;
				Log.d(TAG, "Play next song. Remain song count:" + remainSongs +".");
				
			}
			
			robaWaitForLoaded(5);
			Log.d(TAG, "Done.");
		}
		/*
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
		
		solo.goBack();*/	
	}
	
	private void checkFirstStart() {
		Log.d(TAG, "Check apk version for first start: " + APK_VERSION + ".");
		if (APK_VERSION.equals("6.6.6.0")) {
			robaWaitForLoaded(10);
			if (robaWaitForViewByResourceId("cn.kuwo.player:id/guide_skipbtn") == true) {
				robaClickOnView("cn.kuwo.player:id/guide_skipbtn");
				isFirstStart = true;
				solo.waitForActivity("cn.kuwo.player.activities.MainActivity");
				
				robaClickOnView("cn.kuwo.player:id/only_wifi_guide_delete");
			}
		} else if (APK_VERSION.equals("6.3.9.0_changxin09")) {
			robaWaitForLoaded(10);
//			if (robaWaitForViewByResourceId("cn.kuwo.player:id/menu") == true) {
//				Log.d(TAG, "First start of version 6.3.9_changxin09 found.");
				robaWaitForLoaded(15);
				if (solo.waitForText("^取消$")) {
					Log.d(TAG, "Find cancel for the first start!");
					solo.goBack();
				}
				robaClickOnView("cn.kuwo.player:id/downloading_layout");
				robaRandomSleep(8);
				solo.goBack();
				robaWaitForLoaded(5);
				
				isFirstStart = true;
				
//			}
		} else if (APK_VERSION.equals("6.6.7_712")) {
			robaWaitForLoaded(20);
			Log.d(TAG, "Wait for the initialization.");
			
			if (robaWaitForViewByResourceId("cn.kuwo.player:id/guide_skipbtn") == true) {
				robaClickOnView("cn.kuwo.player:id/guide_skipbtn");
				

				if (solo.waitForText("^取消$")) {
					Log.d(TAG, "Find cancel for the first start!");
					solo.goBack();
				}
				
				Log.d(TAG, "Click on skipping guide.");

				isFirstStart = true;
				
				robaWaitForLoaded(20);
				Log.d(TAG, "Waitting for the main activity.");

				robaClickOnView("cn.kuwo.player:id/only_wifi_guide_delete");
				Log.d(TAG, "Click on wifi guide delete.");	
				
				robaWaitForLoaded(20);
				
				solo.scrollToTop();
			}
		}
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}
