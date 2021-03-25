package com.cndatacom.cnportalclient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.a.a.a;
import com.a.a.e;
import com.a.a.m;
import com.cndatacom.cnportalclient.http.CdcProtocol;
import com.cndatacom.cnportalclient.model.InnerState;
import com.cndatacom.cnportalclient.model.ReturnUIResult;
import com.cndatacom.cnportalclient.plugin.ActivityManager;
import com.cndatacom.cnportalclient.utils.AppInfoUtils;
import com.cndatacom.cnportalclient.utils.ExLog;
import com.cndatacom.cnportalclient.zxing.CaptureActivityHandler;
import com.stub.StubApp;
import com.zxinglibrary.a.d;
import com.zxinglibrary.android.a;
import com.zxinglibrary.android.b;
import com.zxinglibrary.android.c;
import com.zxinglibrary.android.d;
import com.zxinglibrary.view.ViewfinderView;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Executors;

public class CaptureActivity extends AppCompatActivity implements SurfaceHolder.Callback {
  private d a;
  
  private CaptureActivityHandler b;
  
  private ViewfinderView c;
  
  private boolean d;
  
  private d e;
  
  private Collection<a> f;
  
  private Map<e, ?> g;
  
  private String h;
  
  private c i;
  
  private a j;
  
  private Context k;
  
  private AsyncTask<Object, Object, ReturnUIResult> l = null;
  
  private ProgressDialog m;
  
  private String n;
  
  private String o;
  
  static {
    StubApp.interface11(1509);
  }
  
  private void a(Activity paramActivity, String paramString1, String paramString2, String paramString3) {
    AlertDialog.Builder builder = new AlertDialog.Builder((Context)paramActivity);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("确定要为计算机：");
    stringBuilder.append(paramString2);
    stringBuilder.append("\n认证上网吗？");
    AlertDialog alertDialog = builder.setMessage(stringBuilder.toString()).setPositiveButton(2131493073, new DialogInterface.OnClickListener(this, paramString1, paramString3) {
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            if (CaptureActivity.b(this.c) == null)
              CaptureActivity.a(this.c, this.a, this.b); 
          }
        }).setNegativeButton(2131492940, new DialogInterface.OnClickListener(this) {
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            if (CaptureActivity.a(this.a) != null)
              CaptureActivity.a(this.a).restartPreviewAndDecode(); 
          }
        }).create();
    alertDialog.setCanceledOnTouchOutside(false);
    alertDialog.setCancelable(false);
    alertDialog.show();
  }
  
  private void a(SurfaceHolder paramSurfaceHolder) {
    if (paramSurfaceHolder == null)
      throw new IllegalStateException("No SurfaceHolder provided"); 
    if (this.a.a())
      return; 
    try {
      this.a.a(paramSurfaceHolder);
      if (this.b == null) {
        this.b = new CaptureActivityHandler(this, this.f, this.g, this.h, this.a);
        return;
      } 
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("CDCLogs->initCamera Exception:");
      stringBuilder.append(exception.toString());
      ExLog.e(new String[] { stringBuilder.toString() });
      g();
    } 
  }
  
  private void a(String paramString) {
    String[] arrayOfString = paramString.replace("cctp://", "").split("@");
    String str1 = arrayOfString[0];
    String str2 = arrayOfString[1];
    if (arrayOfString.length > 2) {
      String str = arrayOfString[2];
    } else {
      arrayOfString = null;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("CDCLogs->account:");
    stringBuilder.append(this.n);
    stringBuilder.append(" password:");
    stringBuilder.append(this.o);
    stringBuilder.append(" ticket:");
    stringBuilder.append(str1);
    stringBuilder.append(" hostname:");
    stringBuilder.append(str2);
    stringBuilder.append(" authParam:");
    stringBuilder.append((String)arrayOfString);
    ExLog.d(new String[] { stringBuilder.toString() });
    a((Activity)this, str1, str2, (String)arrayOfString);
  }
  
  private void a(String paramString1, String paramString2) {
    a();
    this.l = new AsyncTask<Object, Object, ReturnUIResult>(this, paramString2, paramString1) {
        protected ReturnUIResult a(Object... param1VarArgs) {
          if (TextUtils.isEmpty(this.a)) {
            InnerState innerState1 = InnerState.load();
            String str1 = CdcProtocol.getOneLevelConfig(innerState1, "auth-url", null);
            return CdcProtocol.authOtherClient(StubApp.getOrigApplicationContext(this.c.getApplicationContext()), innerState1, str1, CaptureActivity.c(this.c), CaptureActivity.d(this.c), this.b);
          } 
          InnerState innerState = InnerState.load();
          String str = (new String(Base64.decode(this.a, 2))).trim();
          return CdcProtocol.authOtherClient(StubApp.getOrigApplicationContext(this.c.getApplicationContext()), innerState, str, CaptureActivity.c(this.c), CaptureActivity.d(this.c), this.b);
        }
        
        protected void a(ReturnUIResult param1ReturnUIResult) {
          super.onPostExecute(param1ReturnUIResult);
          CaptureActivity.a(this.c, (AsyncTask)null);
          this.c.b();
          if (param1ReturnUIResult.isSuccess()) {
            Toast.makeText((Context)this.c, "认证登录成功", 0).show();
          } else {
            Intent intent = new Intent("com.cndatacom.campus.netcore.qrcode.action.autherror");
            intent.putExtra("error", (Parcelable)param1ReturnUIResult);
            this.c.sendBroadcast(intent, "com.cndatacom.campus.permissions.PORTAL_RECEIVER");
          } 
          this.c.finish();
        }
        
        protected void onCancelled() {
          super.onCancelled();
          CaptureActivity.a(this.c, (AsyncTask)null);
          this.c.b();
        }
        
        protected void onPreExecute() {
          super.onPreExecute();
        }
      };
    this.l.executeOnExecutor(Executors.newCachedThreadPool(), new Object[0]);
  }
  
  private void c() {
    this.k = StubApp.getOrigApplicationContext(getApplicationContext());
    this.d = false;
    this.i = new c((Activity)this);
    this.j = new a((Activity)this);
  }
  
  private void d() {
    ((RelativeLayout)findViewById(2131165220)).setOnClickListener(new View.OnClickListener(this) {
          public void onClick(View param1View) {
            this.a.finish();
          }
        });
    ((TextView)findViewById(2131165356)).setText(getString(2131493067));
  }
  
  private void e() {
    if (Build.VERSION.SDK_INT >= 21) {
      Window window = getWindow();
      window.clearFlags(67108864);
      window.getDecorView().setSystemUiVisibility(1024);
      window.setStatusBarColor(0);
      window.setNavigationBarColor(-16777216);
      View view = findViewById(2131165343);
      if (view == null)
        return; 
      view.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, AppInfoUtils.getStatusBarHeight(StubApp.getOrigApplicationContext(getApplicationContext()))));
      view.setBackgroundColor(ContextCompat.getColor(StubApp.getOrigApplicationContext(getApplicationContext()), 2130968666));
      view.setVisibility(0);
    } 
  }
  
  private void f() {
    this.a = new d((Context)getApplication());
    this.c = (ViewfinderView)findViewById(2131165366);
    this.c.setCameraManager(this.a);
    this.b = null;
    SurfaceHolder surfaceHolder = ((SurfaceView)findViewById(2131165302)).getHolder();
    if (this.d) {
      a(surfaceHolder);
    } else {
      surfaceHolder.addCallback(this);
    } 
    this.j.a();
    this.i.c();
    this.e = d.d;
    this.f = null;
    this.h = null;
  }
  
  private void g() {
    AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
    builder.setTitle("温馨提示");
    builder.setMessage(getString(2131492971));
    builder.setPositiveButton(2131492925, (DialogInterface.OnClickListener)new b((Activity)this));
    builder.setOnCancelListener((DialogInterface.OnCancelListener)new b((Activity)this));
    builder.setCancelable(false);
    builder.show();
  }
  
  protected void a() {
    if (this.m == null) {
      this.m = new ProgressDialog((Context)this);
      this.m.setCanceledOnTouchOutside(false);
      this.m.setProgressStyle(0);
      this.m.setMessage("正在通讯中，请稍后！");
    } 
    this.m.show();
  }
  
  protected void b() {
    if (this.m != null && this.m.isShowing())
      this.m.dismiss(); 
  }
  
  public d getCameraManager() {
    return this.a;
  }
  
  public Handler getHandler() {
    return (Handler)this.b;
  }
  
  public ViewfinderView getViewfinderView() {
    return this.c;
  }
  
  public void handleDecode(m paramm, Bitmap paramBitmap, float paramFloat) {
    boolean bool;
    this.i.a();
    if (paramBitmap != null) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      this.j.b();
      String str = paramm.a();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("CDCLogs->handleDecode:");
      stringBuilder.append(str);
      ExLog.d(new String[] { stringBuilder.toString() });
      if (str.contains("cctp://")) {
        a(str);
        return;
      } 
      Toast.makeText((Context)this, "不支持此二维码认证", 1).show();
      if (this.b != null)
        this.b.restartPreviewAndDecode(); 
    } 
  }
  
  protected native void onCreate(Bundle paramBundle);
  
  protected void onDestroy() {
    ActivityManager.getInstance().removeActivity((Activity)this);
    this.i.d();
    super.onDestroy();
    if (this.l != null && !this.l.isCancelled())
      this.l.cancel(true); 
  }
  
  protected void onPause() {
    if (this.b != null) {
      this.b.quitSynchronously();
      this.b = null;
    } 
    this.i.b();
    this.j.close();
    this.a.b();
    if (!this.d)
      ((SurfaceView)findViewById(2131165302)).getHolder().removeCallback(this); 
    super.onPause();
  }
  
  protected void onResume() {
    super.onResume();
    f();
  }
  
  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void surfaceCreated(SurfaceHolder paramSurfaceHolder) {
    if (!this.d) {
      this.d = true;
      a(paramSurfaceHolder);
    } 
  }
  
  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder) {
    this.d = false;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\CaptureActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */