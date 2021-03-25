package com.cndatacom.cnportalclient;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import com.stub.StubApp;
import org.apache.cordova.CordovaActivity;

public class MainActivity extends CordovaActivity {
  static {
    StubApp.interface11(1510);
  }
  
  private void a() {
    if (Build.VERSION.SDK_INT >= 21) {
      Window window = getWindow();
      window.clearFlags(67108864);
      window.getDecorView().setSystemUiVisibility(1024);
      window.setStatusBarColor(0);
      window.setNavigationBarColor(-16777216);
    } 
  }
  
  public native void onCreate(Bundle paramBundle);
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\MainActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */