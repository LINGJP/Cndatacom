package com.cndatacom.cnportalclient.plugin;

import android.app.Activity;
import java.util.LinkedList;
import java.util.List;

public class ActivityManager {
  private static ActivityManager b;
  
  private List<Activity> a = new LinkedList<Activity>();
  
  public static ActivityManager getInstance() {
    if (b == null)
      b = new ActivityManager(); 
    return b;
  }
  
  public void addActivity(Activity paramActivity) {
    this.a.add(paramActivity);
  }
  
  public void exit() {
    for (Activity activity : this.a) {
      if (!activity.isFinishing() && activity != null)
        activity.finish(); 
    } 
  }
  
  public void removeActivity(Activity paramActivity) {
    this.a.remove(paramActivity);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\plugin\ActivityManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */