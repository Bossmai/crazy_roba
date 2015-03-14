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
    
    public enum DragDirection {
    	Right, Left, Top, Buttom, TopButtom, LeftRight
    }
    
    public RobaActivityInstrumentationTestCase2(String launcherActivityFullClassName, Class activityClass) {
         super(activityClass);    
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
   	 	int sleepTime = r.nextInt(maxSleepSecond) * 1000;
   	 	solo.sleep(sleepTime);
   	 	Log.d(TAG, "Roba random sleep " + sleepTime + " s");
    }
    
    protected void robaRandomSleep(int minSleepSecond, int maxSleepSecond) {
    	Random r = new Random();
    	int sleepTime = (minSleepSecond + r.nextInt(maxSleepSecond - minSleepSecond)) * 1000;
    	solo.sleep(sleepTime);
    	Log.d(TAG, "Roba random sleep " + (sleepTime / 1000) + " s");
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
    
    protected void robaRandomClickInViews(List<View> targetViews) {
    	Random r = new Random();
    	
    	int targetIndex = r.nextInt(targetViews.size());
    	solo.clickOnView(targetViews.get(targetIndex));
    	
    	Log.d(TAG, "Roba Random Click In Views: " + targetIndex);
    }
     
    protected List<View> robaGetViewsWithResourceId(Class viewClass, String resourceName) {
    	List<View> views = solo.getCurrentViews(viewClass);
    	List<View> resultViews = new ArrayList<View>();
    	  	
    	for (View view : views) {
    		if (view.getId() <= 0) {
    			continue;
    		}
    		
    		if (solo.getCurrentActivity().getResources().getResourceName(view.getId()).equals(resourceName)) {
    			resultViews.add(view);
    		}
    	}
    	
    	Log.d(TAG, "Get Views with specific resource name.");
    	
    	return resultViews;    	
    }
    
    protected boolean robaWaitForViewByResourceId(String resourceId) {
    	int remainTime = 4;
    	while (remainTime > 0) {
    		Activity activity = solo.getCurrentActivity();       
        	int viewId = activity.getResources().getIdentifier(resourceId, "id" , activity.getPackageName());
        	View viewInstance = activity.findViewById(viewId);
        	if (solo.waitForView(viewInstance, 5000, true) == true) {
        		Log.d(TAG, resourceId + " found!");
        		return true;
        	} else {
        		remainTime--;
        		Log.d(TAG, "Wait view with name " + resourceId + ".");
        	}
    	}
    	
    	Log.d(TAG, "Failed waiting the target view " + resourceId + ".");
    	return false;
    }
    
    protected int robaGetViewCount(Class viewClass, String resourceId) {
    	List<View> views = solo.getCurrentViews(viewClass);
    	
    	int sum = 0;
    	
    	for (View view : views) {
    		if (solo.getCurrentActivity().getResources().getResourceName(view.getId()).equals(resourceId)) {
    			sum++;
    		}
    	}
    	
    	return sum;
    }
    
    protected void robaRandomDrag(DragDirection dragDirection, int steps) {
    	Random r = new Random();
    	for (int i = 0; i < steps; i++) {
    		robaRandomSleep(5);
    		switch (dragDirection) {
    		case Top:
    		case Buttom:
    		case Left:
    		case Right:
    			robaDrag(dragDirection);
    			Log.d(TAG, "Roba RandomDrag " + dragDirection.toString());
    			break;
    		case TopButtom:
    			if (r.nextInt() % 2 == 0) {
    				robaDrag(dragDirection.Top);
    				Log.d(TAG, "Roba RandomDrag top.");
    			} else {
    				robaDrag(dragDirection.Buttom);
    				Log.d(TAG, "Roba RandomDrag buttom.");
    			}
    			break;
    		case LeftRight:
    			if (r.nextInt() % 2 == 0) {
    				robaDrag(dragDirection.Left);
    				Log.d(TAG, "Roba RandomDrag left.");
    			} else {
    				robaDrag(dragDirection.Right);
    				Log.d(TAG, "Roba RandomDrag right.");
    			}
    			break;
    		default:
    				break;
        	}
    	}
    }
    
    protected boolean waitForLoaded(int timeoutSecond) {
    	return solo.waitForActivity(solo.getCurrentActivity().toString(), timeoutSecond * 1000);
    }
}