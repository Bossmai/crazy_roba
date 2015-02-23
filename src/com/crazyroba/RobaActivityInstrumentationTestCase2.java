package com.crazyroba;

import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;

@SuppressWarnings("unchecked")
public class RobaActivityInstrumentationTestCase2 extends ActivityInstrumentationTestCase2{
	private Solo solo;
	private String launcherActivityFullClassName;
	private Class launcherActivityClass;
	
	public RobaActivityInstrumentationTestCase2(String launcherActivityFullClassName, Class activityClass) {
		super(activityClass);
		
		this.launcherActivityFullClassName = launcherActivityFullClassName;
		this.launcherActivityClass = activityClass;
	}
	
	public Solo getSolo() {
		return this.solo;
	}
	
	public void setSolo(Solo solo) {
		this.solo = solo;
	}
}
