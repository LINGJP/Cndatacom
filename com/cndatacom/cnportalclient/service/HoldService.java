package com.cndatacom.cnportalclient.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.Process;
import com.cndatacom.cnportalclient.model.MessageResult;
import com.cndatacom.cnportalclient.receiver.AppReceiver;
import com.cndatacom.cnportalclient.utils.ExLog;
import com.stub.StubApp;

public class HoldService extends Service implements BaseService.ServiceInterface {
  private BaseService a;
  
  private AppReceiver b;
  
  private Handler c;
  
  private HandlerThread d;
  
  private final int e = 0;
  
  private final int f = 1;
  
  private int g = -1;
  
  private boolean h = false;
  
  private void a() {
    this.h = false;
    this.a.release(StubApp.getOrigApplicationContext(getApplicationContext()));
    if (this.c != null)
      this.c.removeCallbacksAndMessages(null); 
    if (this.d != null)
      this.d.quit(); 
  }
  
  public static boolean start(Context paramContext, boolean paramBoolean, String paramString) {
    ExLog.i(new String[] { "hold start ..." });
    Intent intent = new Intent(paramContext, HoldService.class);
    intent.putExtra("selfConnect", paramBoolean);
    intent.putExtra("holdId", paramString);
    int i = Build.VERSION.SDK_INT;
    boolean bool = false;
    paramBoolean = false;
    if (i >= 26) {
      if (paramContext.startForegroundService(intent) != null)
        paramBoolean = true; 
      return paramBoolean;
    } 
    paramBoolean = bool;
    if (paramContext.startService(intent) != null)
      paramBoolean = true; 
    return paramBoolean;
  }
  
  public static void stop(Context paramContext) {
    ExLog.i(new String[] { "hold stop ..." });
    KeepService.alarmKeepStart(paramContext, -1L);
    AppReceiver.sendServiceReceiver(paramContext, 2);
  }
  
  public void loginSuccess(Context paramContext) {
    ExLog.i(new String[] { "hold login success ..." });
    this.c.sendEmptyMessage(0);
  }
  
  public IBinder onBind(Intent paramIntent) {
    return null;
  }
  
  public void onDestroy() {
    super.onDestroy();
    this.h = false;
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2) {
    boolean bool;
    this.g = paramInt2;
    if (!this.h) {
      this.h = true;
      this.a = new BaseService(this, paramIntent) {
          protected String a() {
            return this.a.getStringExtra("holdId");
          }
          
          protected String b() {
            return new String("hold");
          }
          
          protected BaseService.ServiceInterface c() {
            return this.b;
          }
        };
      this.a.onStartCommand(StubApp.getOrigApplicationContext(getApplicationContext()), this);
      this.d = new HandlerThread("holdservice thread", 0);
      this.d.start();
      this.c = new Handler(this.d.getLooper(), new Handler.Callback(this) {
            public boolean handleMessage(Message param1Message) {
              switch (param1Message.what) {
                default:
                  return false;
                case 1:
                  AppReceiver.sendServiceReceiver(StubApp.getOrigApplicationContext(this.a.getApplicationContext()), 3);
                case 0:
                  break;
              } 
              HoldService.a(this.a).initData(StubApp.getOrigApplicationContext(this.a.getApplicationContext()));
              KeepService.alarmKeepStart(StubApp.getOrigApplicationContext(this.a.getApplicationContext()), -1L);
              KeepService.alarmKeepStart(StubApp.getOrigApplicationContext(this.a.getApplicationContext()), 5000L);
            }
          });
      this.b = new AppReceiver();
      this.b.setReceiverListener(new AppReceiver.AppReceiverListener(this) {
            public void onReceive(Context param1Context, Intent param1Intent, int param1Int) {
              if (param1Int == 1)
                HoldService.b(this.a).post(new HoldService$3$$Lambda$0(this, param1Intent)); 
            }
          });
      this.b.registerReceiver(StubApp.getOrigApplicationContext(getApplicationContext()));
    } 
    if (paramIntent != null) {
      bool = paramIntent.getBooleanExtra("selfConnect", false);
    } else {
      bool = false;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("HoldService selfConnect ->");
    stringBuilder.append(bool);
    ExLog.i(new String[] { stringBuilder.toString() });
    if (bool) {
      AppReceiver.sendServiceReceiver(StubApp.getOrigApplicationContext(getApplicationContext()), 12);
      this.c.sendEmptyMessage(0);
    } else {
      this.c.sendEmptyMessage(1);
    } 
    return 3;
  }
  
  public void releaseService(Context paramContext) {
    a();
  }
  
  public void stopService(Context paramContext) {
    this.h = false;
    this.a.stopForeground(this);
    ExLog.i(new String[] { "hold stop ..." });
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("hold lastStartId ->");
    stringBuilder.append(this.g);
    ExLog.d(new String[] { stringBuilder.toString() });
    if (this.g != -1) {
      stopSelf(this.g);
    } else {
      stopSelf();
    } 
    (new Thread(this) {
        public void run() {
          super.run();
          try {
            Thread.sleep(2000L);
            Process.killProcess(Process.myPid());
            return;
          } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
            return;
          } 
        }
      }).start();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\service\HoldService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */