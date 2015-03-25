package com.crazyroba.kuwo;

import java.util.Random;

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
	private static final int WAITCOUNT = 3;
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
			Log.d(TAG, "Play random music.");
			Random r = new Random();
			if (r.nextInt() % 2 == 0) {
				playDailyMusic();
			} else {				
				playBillBoard();
			}
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
	
	private void playDailyMusic() {
		if (APK_VERSION.equals("6.3.9.0_changxin09")) {
			robaDrag(DragDirection.Right);
			
			solo.waitForText("�����µ���");
			
			Log.d(TAG, "Go into newest single song.");
			
			solo.clickOnText("�����µ���");
			
			robaWaitForLoaded(5);
			
			Log.d(TAG, "Play all.");
			
			if (isFirstStart) {
				robaClickOnView("cn.kuwo.player:id/library_music_list_batch_play_text");
			}
			
			robaClickOnView("cn.kuwo.player:id/library_music_list_batch_play_text");
			
			//Click to avoid tips.
			
			if (solo.waitForText("��֪����")) {
				
				
				solo.clickOnText("��֪����");			
			}
			
			Log.d(TAG, "Play songs.");
			
			int remainSongs = PLAYSONGS;
			
			while (remainSongs > 0) {
				robaRandomSleep(MIN_PLAYSEC, MAX_PLAYSEC);
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
			int i = WAITCOUNT;
			robaWaitForLoaded(5);
			while (true) {
				Log.d(TAG, "Wait for daily new song.");
				solo.scrollToTop();
				if (solo.waitForText("�����µ���")) {
					Log.d(TAG, "Find daily new song.");
					break;
				}			
			}
			
			
			solo.scrollToTop();
			solo.clickOnText("�����µ���");
			Log.d(TAG, "Go into newest single song.");
			
			robaWaitForLoaded(5);
			
			Log.d(TAG, "Play all.");
			
			robaClickOnView("cn.kuwo.player:id/library_music_list_batch_play_text");

			if (solo.waitForText("��֪����")) {				
				
				solo.clickOnText("��֪����");			
			}
			
			Log.d(TAG, "Play songs.");
			
			int remainSongs = PLAYSONGS;
			
			while (remainSongs > 0) {
				if (remainSongs == PLAYSONGS) {
					robaClickOnView("cn.kuwo.player:id/clickview");
					robaWaitForLoaded(10);
					if (isFirstStart) {
						solo.goBack();
						robaWaitForLoaded(10);
						robaClickOnView("cn.kuwo.player:id/clickview");
						robaWaitForLoaded(10);
					}			
					robaClickOnView("cn.kuwo.player:id/Nowplay_BtnPlayMode");
					robaWaitForLoaded(10);
					solo.goBack();
				}

				robaRandomSleep(MIN_PLAYSEC, MAX_PLAYSEC);
			
				robaClickOnView("cn.kuwo.player:id/Main_BtnNext");
			
				remainSongs--;
				Log.d(TAG, "Play next song. Remain song count:" + remainSongs +".");
			
			}
		
			robaWaitForLoaded(5);
			Log.d(TAG, "Done.");
				
		}
	}
	
	private void playBillBoard() {
		if (APK_VERSION.equals("6.6.7_712")) {
			int i = WAITCOUNT;
			robaWaitForLoaded(5);
			while (true) {
				Log.d(TAG, "Wait for billboard.");
				solo.scrollToTop();
				if (solo.waitForText("���а�")) {
					Log.d(TAG, "Find billboard.");
					break;
				}			
			}			
			
			solo.scrollToTop();
			solo.clickOnText("���а�");
			Log.d(TAG, "Go into billboard.");
			
			robaWaitForLoaded(5);
			
			Log.d(TAG, "Play all.");
			
			solo.clickOnText("�����ȸ��");
			robaRandomClickInViews(robaGetViewsWithResourceId(android.widget.RelativeLayout.class, "cn.kuwo.player:id/list_content"));

			/*String[] boards = {"�����¸��", "�����ȸ�� ", "���������"};
			Random r = new Random();
			int index = r.nextInt(boards.length);
			
			while (true) {
				Log.d(TAG, "Wait for specific billboard.");
				solo.scrollToTop();
				if (solo.waitForText(boards[index])) {
					Log.d(TAG, "Find billboard.");
					break;
				}			
			}
			
			solo.scrollToTop();
			solo.clickOnText(boards[index]);*/
			
			Log.d(TAG, "Go specific billboard.");
			
			robaWaitForLoaded(10);
			solo.clickOnText("ȫ������");
			//robaClickOnView("cn.kuwo.player:id/library_music_list_batch_play_btn");

			if (solo.waitForText("��֪����")) {
				
				solo.clickOnText("��֪����");			
			}
			
			Log.d(TAG, "Play songs.");
			
			int remainSongs = PLAYSONGS;
			
			while (remainSongs > 0) {
				if (remainSongs == PLAYSONGS) {
					robaClickOnView("cn.kuwo.player:id/clickview");
					robaWaitForLoaded(10);
					if (isFirstStart) {
						solo.goBack();
						robaWaitForLoaded(10);
						robaClickOnView("cn.kuwo.player:id/clickview");
						robaWaitForLoaded(10);
					}			
					robaClickOnView("cn.kuwo.player:id/Nowplay_BtnPlayMode");
					robaWaitForLoaded(10);
					solo.goBack();
				}				

				robaRandomSleep(MIN_PLAYSEC, MAX_PLAYSEC);
				robaClickOnView("cn.kuwo.player:id/Main_BtnNext");
				
				remainSongs--;
				Log.d(TAG, "Play next song. Remain song count:" + remainSongs +".");
				
			}
			
			robaWaitForLoaded(5);
			Log.d(TAG, "Done.");
		}
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
			robaWaitForLoaded(15);
			if (solo.waitForText("^ȡ��$")) {
				Log.d(TAG, "Find cancel for the first start!");
				solo.goBack();
			}
			robaClickOnView("cn.kuwo.player:id/downloading_layout");
			robaRandomSleep(8);
			solo.goBack();
			robaWaitForLoaded(5);
				
			isFirstStart = true;
		} else if (APK_VERSION.equals("6.6.7_712")) {
			robaWaitForLoaded(20);
			Log.d(TAG, "Wait for the initialization.");
			
			int i = 30;
			
			while (i > 0) {
				Log.d(TAG, "Wait for the guide skin button.");
				if (robaWaitForViewByResourceId("cn.kuwo.player:id/guide_skipbtn") == true) {
					Log.d(TAG, "Guide skin button found!");
					robaClickOnView("cn.kuwo.player:id/guide_skipbtn");
					isFirstStart = true;
					Log.d(TAG, "Skip guide.");
					break;
				} else if (solo.waitForText("^��׿�г�$")) {
					Log.d(TAG, "Find android market!");
					solo.clickOnText("��������");
					isFirstStart = false;
					Log.d(TAG, "Skip android market");
					break;
				} else {
					i--;
					solo.sleep(10000);
				}
			}
			
			Log.d(TAG, "Wait for cancel button.");
			
			if (isFirstStart) {
				i = 10;
				while (i > 0) {
					Log.d(TAG, "Wait for the cancel button.");
					if (solo.waitForText("^ȡ��$")) {
						Log.d(TAG, "Cancel button found!");
						Log.d(TAG, "Find cancel for the first start!");
						solo.goBack();
						isFirstStart = true;
						break;
					} else {
						i--;
						solo.sleep(10000);
						solo.scrollToTop();
					}
				}
			}
			
			robaWaitForLoaded(20);
			Log.d(TAG, "Waitting for the main activity.");

			if (isFirstStart) {
				robaClickOnView("cn.kuwo.player:id/only_wifi_guide_delete");
				Log.d(TAG, "Click on wifi guide delete.");
			}
			
			robaWaitForLoaded(20);
			
			solo.scrollToTop();		
		}
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}
