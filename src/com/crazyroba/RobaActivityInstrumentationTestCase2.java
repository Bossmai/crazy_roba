package com.crazyroba;

import com.jayway.android.robotium.solo.Solo;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

@SuppressWarnings("unchecked")
public class RobaActivityInstrumentationTestCase2 extends ActivityInstrumentationTestCase2{
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
     }
    
     protected void robaClickOnView(String view) {
          Activity activity = solo.getCurrentActivity();       
        int imageId = activity.getResources().getIdentifier(view, "id" , activity.getPackageName());
        View viewInstance = activity.findViewById(imageId);
        solo.clickOnView(viewInstance);
     }
}