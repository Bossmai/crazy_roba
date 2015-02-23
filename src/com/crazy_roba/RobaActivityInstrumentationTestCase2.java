package com.crazy_roba;

import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;

@SuppressWarnings("unchecked")
public class RobaActivityInstrumentationTestCase2 extends ActivityInstrumentationTestCase2{
	private String launcherActivityFullClassName;
	private Class launcherActivityClass;
	
	public RobaActivityInstrumentationTestCase2(String launcherActivityFullClassName, Class activityClass) {
		super(activityClass);
		this.launcherActivityFullClassName = launcherActivityFullClassName;
		
		try {
			this.launcherActivityClass = Class.forName(launcherActivityFullClassName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
