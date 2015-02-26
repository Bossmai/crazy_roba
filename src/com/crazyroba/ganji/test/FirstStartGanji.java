package com.crazyroba.ganji.test;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.crazyroba.*;
import com.jayway.android.robotium.solo.Solo;

public class FirstStartGanji extends RobaActivityInstrumentationTestCase2 {
	private static final boolean firstStart = false;
	private static final String runType = ""; //randomClick
	
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
	
	public FirstStartGanji() throws ClassNotFoundException {
		super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);
	}
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void firstStartGanji() {
	}
	
	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}
