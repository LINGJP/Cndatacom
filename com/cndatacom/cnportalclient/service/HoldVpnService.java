package com.cndatacom.cnportalclient.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.VpnService;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.text.TextUtils;
import com.cndatacom.cnportalclient.keep.KeepHelper;
import com.cndatacom.cnportalclient.model.InnerState;
import com.cndatacom.cnportalclient.model.MessageResult;
import com.cndatacom.cnportalclient.model.ReturnUIResult;
import com.cndatacom.cnportalclient.receiver.AppReceiver;
import com.cndatacom.cnportalclient.utils.ExLog;
import com.stub.StubApp;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.util.Random;

public class HoldVpnService extends VpnService implements BaseService.ServiceInterface {
  private Handler a;
  
  private HandlerThread b;
  
  private final int c = 0;
  
  private final int d = 1;
  
  private ParcelFileDescriptor e;
  
  private volatile boolean f = false;
  
  private BaseService g;
  
  private AppReceiver h;
  
  private int i = -1;
  
  private boolean j = false;
  
  private KeepHelper k = null;
  
  private void a() {
    if (this.k != null)
      this.k.handleKeep(0); 
  }
  
  private void a(Intent paramIntent) {
    this.g = new BaseService(this, paramIntent) {
        protected String a() {
          return this.a.getStringExtra("holdId");
        }
        
        protected String b() {
          return new String("holdvpn");
        }
        
        protected BaseService.ServiceInterface c() {
          return this.b;
        }
      };
    this.g.onStartCommand(StubApp.getOrigApplicationContext(getApplicationContext()), (Service)this);
    this.b = new HandlerThread("loadHoldVpnServiceData", 0);
    this.b.start();
    this.a = new Handler(this.b.getLooper(), new Handler.Callback(this) {
          public boolean handleMessage(Message param1Message) {
            switch (param1Message.what) {
              default:
                return false;
              case 1:
                AppReceiver.sendServiceReceiver(StubApp.getOrigApplicationContext(this.a.getApplicationContext()), 3);
                return false;
              case 0:
                break;
            } 
            boolean bool = HoldVpnService.d(this.a);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("HoldVpnService initVpnData ->");
            stringBuilder.append(bool);
            ExLog.d(new String[] { stringBuilder.toString() });
            if (bool) {
              HoldVpnService.b(this.a).initData(StubApp.getOrigApplicationContext(this.a.getApplicationContext()));
              if (HoldVpnService.e(this.a) != null) {
                HoldVpnService.e(this.a).doFristKeep();
                return false;
              } 
            } else {
              HoldVpnService.termAndStop(StubApp.getOrigApplicationContext(this.a.getApplicationContext()), 4, new ReturnUIResult("200124"));
            } 
            return false;
          }
        });
    this.k = KeepHelper.newInstance(StubApp.getOrigApplicationContext(getApplicationContext()));
    this.k.setExtraInterface(new KeepHelper.ExtraInterface(this) {
          public void startNextKeep(long param1Long) {
            KeepVpnService.alarmKeepStart(StubApp.getOrigApplicationContext(this.a.getApplicationContext()), param1Long);
          }
          
          public void stopService(Context param1Context) {
            AppReceiver.sendServiceReceiver(param1Context, 5);
          }
        });
  }
  
  private boolean b() {
    try {
      String str2 = InnerState.load().getFuncfgUrl("TunnelAddress", "10.68.99.246");
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("HoldVpnService initVpnData tunnelIP->");
      stringBuilder1.append(str2);
      ExLog.i(new String[] { stringBuilder1.toString() });
      String[] arrayOfString = str2.split("\\.");
      if (arrayOfString.length < 4)
        return false; 
      String str1 = arrayOfString[arrayOfString.length - 1];
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("HoldVpnService initVpnData last->");
      stringBuilder2.append(str1);
      ExLog.d(new String[] { stringBuilder2.toString() });
      while (true) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append((new Random()).nextInt(25500) % 255 + 1);
        stringBuilder2.append("");
        String str = stringBuilder2.toString();
        if (!str.equals(str1)) {
          str1 = "";
          for (int i = 0;; i++) {
            if (i < arrayOfString.length - 1) {
              if (TextUtils.isEmpty(str1)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str1);
                stringBuilder.append(arrayOfString[i]);
                str1 = stringBuilder.toString();
              } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str1);
                stringBuilder.append(".");
                stringBuilder.append(arrayOfString[i]);
                str1 = stringBuilder.toString();
              } 
            } else {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append(str1);
              stringBuilder.append(".");
              stringBuilder.append(str);
              str1 = stringBuilder.toString();
              stringBuilder = new StringBuilder();
              stringBuilder.append("HoldVpnService initVpnData routeIP->");
              stringBuilder.append(str1);
              ExLog.i(new String[] { stringBuilder.toString() });
              VpnService.Builder builder = new VpnService.Builder(this);
              builder.setMtu(1400);
              builder.addAddress(str2, 32);
              builder.addRoute(str1, 32);
              builder.setSession(getString(2131492904));
              if (Build.VERSION.SDK_INT >= 21)
                builder.setBlocking(true); 
              this.e = builder.establish();
              c();
              return true;
            } 
          } 
          break;
        } 
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("HoldVpnService initVpnData Exception->");
      stringBuilder.append(exception.getMessage());
      ExLog.i(new String[] { stringBuilder.toString() });
      return false;
    } 
  }
  
  private void c() {
    (new Thread(this) {
        public void run() {
          super.run();
          try {
            HoldVpnService.a(this.a, false);
            FileInputStream fileInputStream = new FileInputStream(HoldVpnService.f(this.a).getFileDescriptor());
            ByteBuffer byteBuffer = ByteBuffer.allocate(32767);
            while (!HoldVpnService.g(this.a)) {
              ExLog.d(new String[] { "HoldVpnService startVpnTask ->read" });
              int i = fileInputStream.read(byteBuffer.array());
              ExLog.d(new String[] { "HoldVpnService startVpnTask ->read finish" });
              if (i > 0) {
                byteBuffer.limit(i);
                byteBuffer.clear();
              } 
            } 
          } catch (Exception exception) {
            exception.printStackTrace();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("HoldVpnService startVpnTask Exception->");
            stringBuilder.append(exception.getMessage());
            ExLog.d(new String[] { stringBuilder.toString() });
          } 
        }
      }).start();
  }
  
  private void d() {
    this.j = false;
    this.f = true;
    this.g.release(StubApp.getOrigApplicationContext(getApplicationContext()));
    if (this.e != null)
      try {
        this.e.close();
      } catch (Exception exception) {
        exception.printStackTrace();
      }  
    if (this.h != null) {
      this.h.unregisterReceiver(StubApp.getOrigApplicationContext(getApplicationContext()));
      this.h = null;
    } 
    if (this.a != null)
      this.a.removeCallbacksAndMessages(null); 
    if (this.b != null)
      this.b.quit(); 
  }
  
  public static boolean start(Context paramContext, boolean paramBoolean, String paramString) {
    ExLog.i(new String[] { "holdvpn start ..." });
    Intent intent = new Intent(paramContext, HoldVpnService.class);
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
    ExLog.i(new String[] { "holdvpn stop ..." });
    AppReceiver.sendServiceReceiver(paramContext, 2);
  }
  
  public static void termAndStop(Context paramContext, int paramInt, ReturnUIResult paramReturnUIResult) {
    ExLog.i(new String[] { "holdvpn stop and term ..." });
    AppReceiver.sendServiceReceiver(paramContext, paramInt, paramReturnUIResult);
  }
  
  public void loginSuccess(Context paramContext) {
    ExLog.i(new String[] { "holdvpn login success ..." });
    this.a.sendEmptyMessage(0);
  }
  
  public IBinder onBind(Intent paramIntent) {
    return null;
  }
  
  public void onDestroy() {
    super.onDestroy();
    this.j = false;
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2) {
    boolean bool;
    this.i = paramInt2;
    if (this.h == null) {
      this.h = new AppReceiver();
      this.h.setReceiverListener(new AppReceiver.AppReceiverListener(this) {
            public void onReceive(Context param1Context, Intent param1Intent, int param1Int) {
              if (param1Int == 1) {
                MessageResult messageResult = (MessageResult)param1Intent.getBundleExtra("com.cndatacom.campus.receiver.action.bundle.key").getParcelable("com.cndatacom.campus.receiver.action.data.key");
                if (messageResult.getType() == 6) {
                  HoldVpnService.a(this.a).post(new HoldVpnService$1$$Lambda$0(this));
                  return;
                } 
                HoldVpnService.a(this.a).post(new HoldVpnService$1$$Lambda$1(this, messageResult));
              } 
            }
          });
      this.h.registerReceiver(StubApp.getOrigApplicationContext(getApplicationContext()));
      a(paramIntent);
    } 
    if (paramIntent != null) {
      bool = paramIntent.getBooleanExtra("selfConnect", false);
    } else {
      bool = false;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("HoldVpnService selfConnect ->");
    stringBuilder.append(bool);
    ExLog.i(new String[] { stringBuilder.toString() });
    if (bool) {
      AppReceiver.sendServiceReceiver(StubApp.getOrigApplicationContext(getApplicationContext()), 12);
      this.a.sendEmptyMessage(0);
    } else {
      this.a.sendEmptyMessage(1);
    } 
    return 3;
  }
  
  public void releaseService(Context paramContext) {
    d();
  }
  
  public void stopService(Context paramContext) {
    this.j = false;
    this.g.stopForeground((Service)this);
    ExLog.i(new String[] { "holdvpn stop ..." });
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("hold lastStartId ->");
    stringBuilder.append(this.i);
    ExLog.d(new String[] { stringBuilder.toString() });
    if (this.i != -1) {
      stopSelf(this.i);
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


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\service\HoldVpnService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */