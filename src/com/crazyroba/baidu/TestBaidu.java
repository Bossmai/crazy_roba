package com.crazyroba.baidu;

import com.crazyroba.RobaActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;

public class TestBaidu extends RobaActivityInstrumentationTestCase2{
	private static final String APK_VERSION = "1001605m_6.2.0";
	
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.baidu.appsearch.LauncherActivity";
	private static final String TAG = "TestBaidu";
	
	private static boolean isDebug = true;
	
	private static Class launcherActivityClass;
	
	static {
		try {
			launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public TestBaidu() throws ClassNotFoundException {
		super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);
	}
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void test1() {
		
	}
	
	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}
