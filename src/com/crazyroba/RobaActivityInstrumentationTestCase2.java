package com.crazyroba;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jayway.android.robotium.solo.Solo;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

@SuppressWarnings("unchecked")
public class RobaActivityInstrumentationTestCase2 extends ActivityInstrumentationTestCase2{
	private static String TAG = "roba";
    protected Solo solo;
    private String launcherActivityFullClassName;
    private Class launcherActivityClass;
    
    public enum DragDirection {
    	Right, Left, Top, Buttom
    }
    
    public RobaActivityInstrumentationTestCase2(String launcherActivityFullClassName, Class activityClass) {
         super(activityClass);
        
         this.launcherActivityFullClassName = launcherActivityFullClassName;
         this.launcherActivityClass = activityClass;
    }
   
    protected void robaDrag(DragDirection direction){
    	DisplayMetrics displayMetrics = new DisplayMetrics(); 
    	solo.getCurrentActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    	
    	long x = displayMetrics.widthPixels; 
    	long y = displayMetrics.heightPixels;
        	
    	//use middle of the screen to avoid system menu/menu
    	switch (direction) {
    	case Right:
    		solo.drag(x / 5, x * 4 / 5, y / 2, y / 2, 5);
    		break;
    	case Left:
    		solo.drag(x * 4 / 5, x / 5, y / 2, y / 2, 5);
    		break;
    	case Top:
    		solo.drag(x / 2, x / 2, y / 5 * 4, y / 5, 5);
    		break;
    	case Buttom:
    		solo.drag(x / 2, x / 2, y / 5, y / 5 * 4, 5);
    		break;
    	default:
    		break;
         }	
         
    	Log.d(TAG, "robaDrag (" + direction.toString() + ")");
    }
   
    protected void robaClickOnView(String view) {
    	Activity activity = solo.getCurrentActivity();       
    	int imageId = activity.getResources().getIdentifier(view, "id" , activity.getPackageName());
    	View viewInstance = activity.findViewById(imageId);
    	solo.clickOnView(viewInstance);
    	Log.d(TAG, "Roba click on view " + viewInstance.getClass().toString());
    }
    
    protected void robaRandomSleep(int maxSleepSecond) {
    	Random r = new Random();
   	 	solo.sleep(r.nextInt(maxSleepSecond) * 1000);
   	 	Log.d(TAG, "Roba random sleep.");
    }
    
    protected void robaRandomTouch() {
    	DisplayMetrics displayMetrics = new DisplayMetrics(); 
        solo.getCurrentActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
       
        int maxX = displayMetrics.widthPixels; 
        int maxY = displayMetrics.heightPixels;
        
        Random r = new Random();
        
        int x = r.nextInt(maxX);
        int y = r.nextInt(maxY);
         
         solo.clickOnScreen(x, y);
         Log.d(TAG, "Random click on (" + x + ", " + y + ")");
         
     }
     
    protected void robaRandomClickOnView() {
    	List<View> viewList = solo.getCurrentViews();
    	 
    	Random r = new Random();
    	View targetView = viewList.get(r.nextInt(viewList.size()));
    	
    	solo.clickOnView(targetView);    
    	
    	Log.d(TAG, "Roba Random Click on View:" + targetView.getClass().toString());
    }
     
    protected List<View> robaGetViewsWithResourceId(Class viewClass, String resourceName) {
    	List<View> views = solo.getCurrentViews(viewClass);
    	List<View> resultViews = new ArrayList<View>();
    	
    	for (View view : views) {
    		if (solo.getCurrentActivity().getResources().getResourceName(view.getId()).equals(resourceName)) {
    			resultViews.add(view);
    		}
    	}
    	
    	Log.d(TAG, "Get Views with specific resource name.");
    	
    	return resultViews;    	
    }
    
    protected void robaWaitForViewByResourceId(String resourceId) {
    	int remainTime = 5;
    	while (remainTime > 0) {
    		Activity activity = solo.getCurrentActivity();       
        	int viewId = activity.getResources().getIdentifier(resourceId, "id" , activity.getPackageName());
        	View viewInstance = activity.findViewById(viewId);
        	
        	solo.waitForView(viewInstance, 5000, true);
        	remainTime--;
    	}
    	
    	
    	Log.d(TAG, "Wait view with specific resource name.");
    }
}