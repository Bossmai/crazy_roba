package com.crazyroba.ifengnews;

import java.util.Random;

import android.app.Activity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
	private static final int NEWS_PER_PAGE = 2;
	private static final int TYPE_COUNT = 2;
	private static final boolean isDebug = false;

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
		checkFirstStart();
		readNews();	
	}
	
	private void readNews() {
		if (APK_VERSION.equals("4.4.1_3116")) {
			Log.d(TAG, "Read news");
			
			int i = WAITCOUNT;
			robaWaitForLoaded(5);
			
			while (true) {
				Log.d(TAG, "Wait For the news page");
				
				if (solo.getCurrentActivity().toString().startsWith("com.ifeng.news2.fragment.NewsMasterFragmentActivity")) {
					Log.d(TAG, "List top wrapper found.");
					break;
				}
				

				Log.d(TAG, "Remain wait count: " + i);
				i--;
				
			}
			
			i = TYPE_COUNT;
			
			Random r = new Random();
			
			while (i > 0) {
				robaWaitForLoaded(10);
				Log.d(TAG, "Remain news type: " + i);
				int j = NEWS_PER_PAGE;
				
				while (j > 0) {
					Log.d(TAG, "Remain news in this type: " + j);
					for (int k = 5; k > 0; k--) {
						if (r.nextInt() % 2 == 0) {
							robaDrag(DragDirection.Top);
							solo.sleep(10000);
						}	
					}
					
					robaRandomClickInViews(robaGetViewsWithResourceId(LinearLayout.class, "com.ifeng.news2:id/channel_list_top_wrapper"));
					Log.d(TAG, "Click one news.");
					
					robaWaitForLoaded(10);
					
					boolean picPage = false;
					boolean videoPage = false;
					
					while (true) {
						String currentActivityName = solo.getCurrentActivity().toString();
						Log.d(TAG, "Current activity: " + solo.getCurrentActivity().toString());
						
						if	(currentActivityName.startsWith("com.ifeng.news2.activity.DetailActivity")) {
							Log.d(TAG, "Normal news found.");
							break;
						} else if (currentActivityName.startsWith("com.ifeng.news2.activity.SlideActivity")) {
							picPage = true;
							Log.d(TAG, "Slide News found.");
							break;
						} else if (currentActivityName.startsWith("com.ifeng.news2.activity.VideoDetailNewActivity")) {
							videoPage = true;
							Log.d(TAG, "Video News found.");
							break;
						} else if (currentActivityName.startsWith("com.ifeng.news2.activity.TopicDetailModuleActivity")) {
							Log.d(TAG, "Topic News found.");
							break;
						} else if (currentActivityName.startsWith("com.ifeng.news2.fragment.NewsMasterFragmentActivity")) {
							Log.d(TAG, "Click failed. Retry.");
							robaRandomClickInViews(robaGetViewsWithResourceId(LinearLayout.class, "com.ifeng.news2:id/channel_list_top_wrapper"));
						} else if (currentActivityName.startsWith("com.ifeng.news2.photo_text_live.PhotoTextNewActivity")) {
							Log.d(TAG, "Photo text view found.");
							break;
						} else if (currentActivityName.startsWith("com.ifeng.news2.activity.SurveyActivity")) {
							Log.d(TAG, "Survey view found.");
							break;
						}
						else {
							Log.d(TAG, "Unknown activity. Type: " + currentActivityName);
						}
						
						solo.sleep(5000);						
					}
										
					if (picPage) {
						for (int l = 3; l > 0; l--) {
							Log.d(TAG, "Remain right drag: " + l);
							solo.scrollToSide(solo.RIGHT);
							robaRandomSleep(2, 5);
						}
					} else if (videoPage) {
						for (int l = 5; l > 0; l--) {
							Log.d(TAG, "Remain top drag: " + l);
							robaDrag(DragDirection.Top);
							robaRandomSleep(2, 5);
						}				
					} else {
						for (int l = 5; l > 0; l--) {
							Log.d(TAG, "Remain top drag: " + l);
							robaDrag(DragDirection.Top);
							robaRandomSleep(2, 5);
						}
					}
					
					
					solo.goBack();
					robaRandomSleep(2, 5);
					
					j--;					
				}
				
				for (int k = 5; k > 0; k--) {
					if (r.nextInt() % 2 == 0) {
						Log.d(TAG, "Drag to left for another news type.");
						solo.scrollToSide(solo.RIGHT);
						robaRandomSleep(10, 15);
					}
				}
				
				i--;
			}
		}
		
		Log.d(TAG, "Done.");
	}
	
	private void checkFirstStart() {
		if (APK_VERSION.equals("4.4.1_3116")) {
			Log.d(TAG, "Check apk version for first start: " + APK_VERSION + ".");
			
			robaWaitForLoaded(5);
			Log.d(TAG, "Wait for the initialization.");
			
			int i = WAITCOUNT;
			
			while (i > 0) {				
				Log.d(TAG, "Wait for the guide remain count: " + i);
				
				if (solo.getCurrentActivity().toString().startsWith("com.ifeng.news2.activity.GuideActivity")) {
					Log.d(TAG, "Guide page found!");
					
					int j = 8;
					while (j > 0) {
						solo.scrollToSide(solo.RIGHT);
						//robaDrag(DragDirection.Left);
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
				
				if (isDebug) {
					solo.sleep(2000);
				} else {
					solo.sleep(10000);
				}
			}		
		}
	}

	@Override
	public void tearDown() throws Exception {
		Activity a = super.getActivity();
		if (a != null) {
		a.finish();
		setActivity(null);
		solo.finishOpenedActivities();
		}
		solo.finishOpenedActivities();
	}
}