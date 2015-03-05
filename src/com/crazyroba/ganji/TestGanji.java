package com.crazyroba.ganji;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crazyroba.*;
import com.jayway.android.robotium.solo.Solo;

public class TestGanji extends RobaActivityInstrumentationTestCase2 {
	private static final String APK_VERSION = "6.1.1";
	
	private static boolean isFirstStart = false;
	
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.ganji.android.control.LaunchActivity";
	private static final String TAG = "TestGanJi";
	
	
	private static Class launcherActivityClass;
	
	static {
		try {
			launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public TestGanji() throws ClassNotFoundException {
		super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);
	}
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void test1() {
		CheckFirstStart("6.1.1");
		
		localServiceTest();
	}
	
	private void CheckFirstStart(String version) {
		Log.d(TAG, "Check apk version for first start: " + version + ".");
		
		if (version.equals(version)) {
			if (solo.waitForActivity("NewFeatureActivity", 10000))
			{
				firstStart(version);
				isFirstStart = true;
			}			
		}		
	}
	
	
	private void localServiceTest() {
		Random r = new Random();
		
		solo.waitForActivity("com.ganji.android/.control.MainActivity");
		
		Log.d(TAG, "Main activity loaded.");
		
		solo.clickOnText("���ط���");
		
		Log.d(TAG, "Click local services.");
		
		solo.waitForActivity("com.ganji.android/.control.HomePageActivity");
		
		robaDrag(DragDirection.Top);
		robaRandomSleep(5);
		
		List<View> itemViews = robaGetViewsWithResourceId(LinearLayout.class, "com.ganji.android:id/item_text_lv");
		
		robaRandomClickInViews(itemViews);
		
		Log.d(TAG, "Click two-hands. ");
		
		solo.waitForActivity("com.ganji.android/.control.IntentSearchListActivity");
		
		List<View> views = robaGetViewsWithResourceId(RelativeLayout.class, "com.ganji.android:id/post_list_item_life");
		
		//remove the last one for some of it is behind the menu
		if (views.size() == 0) {
			Log.d(TAG, "No clickable view detected.");
			return;
		}
		
		views.remove(views.size() - 1);
		
		robaRandomClickInViews(views);
		
		solo.waitForActivity("com.ganji.android/.control.PostDetailActivity");
		
		Log.d(TAG, "PostDetailActivity loaded.");
		
		int i = 5;
		while (i > 0) {
			robaDrag(DragDirection.Top);
			robaRandomSleep(5);
			i--;
		}
		
		Log.d(TAG, "Drag to the buttom.");
		
		i = 3;
		while (i > 0) {
			solo.goBack();
			robaRandomSleep(5);
			i--;
		}
		
		Log.d(TAG, "Back to the homepage.");
	}
	
	private void firstStart(String version) {
		if (version.equals("6.1.1")) {
			Log.d(TAG, "First start of Ganji");
			
			solo.waitForActivity("NewFeatureActivity", 10000);
			
			Log.d(TAG, "New feature activity displayed.");
			
			while(true) {
				Log.d(TAG, "Drag up");
				robaDrag(DragDirection.Top);
				if (solo.waitForText("2015.01 �ϼ�Ⱥ����ʽ����", 1, 2000)) {
					robaClickOnView("com.ganji.android:id/qunzu_feature_start_btn");
					break;
				}
			}
			
			solo.clickOnText("�Ϻ�", 1, true);
			
			Log.d(TAG, "Go into City Activity");
			
			Log.d(TAG, "Finish first start activies");
			
			robaWaitForViewByResourceId("com.ganji.android:id/guide_im");
			
			robaClickOnView("com.ganji.android:id/guide_im");
			
			solo.sleep(10000);
			
			return;
		}
		
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}
