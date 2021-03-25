package com.cndatacom.cnportalclient.share;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.cndatacom.cnportalclient.utils.ExLog;

public class ShareHelper {
  public static final int SHARETYPE_APP = 1;
  
  public static final int SHARETYPE_PHONE = 0;
  
  private static ShareHelper b;
  
  private static Context g;
  
  private HandlerThread a = new HandlerThread("handleShareCheckData", 10);
  
  private ShareCallBack c;
  
  private final int d = 0;
  
  private final int e = 30000;
  
  private int f = 120000;
  
  public Handler mWorkerHandler;
  
  private ShareHelper() {
    this.a.start();
    this.mWorkerHandler = new Handler(this.a.getLooper(), new Handler.Callback(this) {
          private void a(Message param1Message) {
            if (param1Message.what != 0)
              return; 
            ExLog.d(new String[] { "check app share start" });
            ShareAppInfo shareAppInfo = ShareUtils.hasShareApp(ShareHelper.a());
            if (shareAppInfo != null) {
              if (ShareHelper.a(this.a) != null)
                ShareHelper.a(this.a).sharedCallBack(1, shareAppInfo); 
              this.a.release();
              return;
            } 
            if (ShareUtils.isNetworkSharing(ShareHelper.a())) {
              if (ShareHelper.a(this.a) != null)
                ShareHelper.a(this.a).sharedCallBack(0, null); 
              this.a.release();
              return;
            } 
            this.a.mWorkerHandler.sendEmptyMessageDelayed(0, ShareHelper.b(this.a));
          }
          
          public boolean handleMessage(Message param1Message) {
            a(param1Message);
            return true;
          }
        });
  }
  
  public static ShareHelper newInstance(Context paramContext) {
    g = paramContext;
    if (b == null)
      b = new ShareHelper(); 
    return b;
  }
  
  public void release() {
    if (this.mWorkerHandler != null)
      this.mWorkerHandler.removeCallbacksAndMessages(null); 
    if (this.a != null)
      this.a.quit(); 
    b = null;
  }
  
  public void setCheckTime(int paramInt) {
    this.f = paramInt;
  }
  
  public void setShareCallBack(ShareCallBack paramShareCallBack) {
    this.c = paramShareCallBack;
  }
  
  public void start() {
    this.mWorkerHandler.sendEmptyMessageDelayed(0, 30000L);
  }
  
  public static interface ShareCallBack {
    void sharedCallBack(int param1Int, ShareAppInfo param1ShareAppInfo);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\share\ShareHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */