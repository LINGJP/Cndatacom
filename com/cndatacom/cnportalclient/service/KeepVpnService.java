package com.cndatacom.cnportalclient.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import com.cndatacom.cnportalclient.receiver.AppReceiver;
import com.cndatacom.cnportalclient.utils.ExLog;
import com.stub.StubApp;

public class KeepVpnService extends IntentService {
  public static final String START_ACTION = "com.cndatacom.campus.netcore.keepservice.START";
  
  private BroadcastReceiver a;
  
  public KeepVpnService() {
    super("KeepVpnService");
  }
  
  private void a() {
    (new Thread(this) {
        public void run() {
          super.run();
          try {
            Thread.sleep(2000L);
            KeepVpnService.b(this.a);
            Process.killProcess(Process.myPid());
            return;
          } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
            return;
          } 
        }
      }).start();
  }
  
  private void a(Context paramContext) {
    AppReceiver.sendServiceReceiver(paramContext, 6);
  }
  
  public static void alarmKeepStart(Context paramContext, long paramLong) {
    AlarmManager alarmManager = (AlarmManager)paramContext.getSystemService("alarm");
    Intent intent2 = new Intent(paramContext, KeepVpnService.class);
    intent2.setAction("com.cndatacom.campus.netcore.keepservice.START");
    PendingIntent pendingIntent = PendingIntent.getService(paramContext, 0, intent2, 134217728);
    ExLog.i(new String[] { "keep set => ", String.valueOf(paramLong) });
    if (paramLong > 0L) {
      if (Build.VERSION.SDK_INT >= 23) {
        alarmManager.setExactAndAllowWhileIdle(2, SystemClock.elapsedRealtime() + paramLong, pendingIntent);
        return;
      } 
      if (Build.VERSION.SDK_INT >= 19) {
        alarmManager.setExact(2, SystemClock.elapsedRealtime() + paramLong, pendingIntent);
        return;
      } 
      alarmManager.set(2, SystemClock.elapsedRealtime() + paramLong, pendingIntent);
      return;
    } 
    alarmManager.cancel(pendingIntent);
    Intent intent1 = new Intent("com.cndatacom.campus.netcore.keepservice.CANCEL");
    intent1.setFlags(32);
    paramContext.sendBroadcast(intent1, "com.cndatacom.campus.permissions.PORTAL_RECEIVER");
  }
  
  private void b() {
    if (this.a != null)
      unregisterReceiver(this.a); 
  }
  
  public void onCreate() {
    super.onCreate();
    ExLog.i(new String[] { "keepvpn start ..." });
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction("com.cndatacom.campus.netcore.keepservice.CANCEL");
    this.a = new BroadcastReceiver(this) {
        public void onReceive(Context param1Context, Intent param1Intent) {
          if (param1Intent.getAction().equals("com.cndatacom.campus.netcore.keepservice.CANCEL"))
            KeepVpnService.a(this.a); 
        }
      };
    registerReceiver(this.a, intentFilter, "com.cndatacom.campus.permissions.PORTAL_RECEIVER", null);
  }
  
  public void onDestroy() {
    super.onDestroy();
  }
  
  protected void onHandleIntent(Intent paramIntent) {
    if (paramIntent != null && "com.cndatacom.campus.netcore.keepservice.START".equals(paramIntent.getAction()))
      a(StubApp.getOrigApplicationContext(getApplicationContext())); 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\service\KeepVpnService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */