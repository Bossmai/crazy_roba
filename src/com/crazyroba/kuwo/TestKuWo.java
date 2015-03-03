package com.crazyroba.kuwo;

import com.crazyroba.RobaActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;

public class TestKuWo extends RobaActivityInstrumentationTestCase2 {
	private static final boolean firstStart = true;
	
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "cn.kuwo.player/.activities.MainActivity";
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
	
	/*public void test1() {
		//com.ganji.android/.control.CityActivity com.ganji.android:id/qunzu_feature_start_btn
		if (firstStart) {
			firstStart("6.1.1");
		}
	}*/
	
	public void test1() {
		
	}
	
	private void firstStart(String version) {
		
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

}
