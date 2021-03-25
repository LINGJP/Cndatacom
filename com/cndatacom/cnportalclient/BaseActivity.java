package com.cndatacom.cnportalclient;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.cndatacom.cnportalclient.handler.WeakRefHandler;
import com.cndatacom.cnportalclient.plugin.ActivityManager;
import com.cndatacom.cnportalclient.utils.AppBarUtil;
import com.stub.StubApp;

public abstract class BaseActivity extends AppCompatActivity {
  private HandlerThread a;
  
  private int b;
  
  public Context mContext;
  
  public WeakRefHandler mUIHandler;
  
  public WeakRefHandler mWorkerHandler;
  
  private void a(View paramView) {
    AppBarUtil.initBar(getWindow());
    a(paramView, AppBarUtil.isAddStatusBar());
  }
  
  private void a(View paramView, boolean paramBoolean) {
    paramView = paramView.findViewById(2131165343);
    if (paramView == null)
      return; 
    if (!paramBoolean) {
      paramView.setVisibility(8);
      return;
    } 
    ViewParent viewParent = paramView.getParent();
    int i = AppBarUtil.getStatusBarHeight(this.mContext);
    if (viewParent instanceof ConstraintLayout) {
      paramView.setLayoutParams((ViewGroup.LayoutParams)new ConstraintLayout.LayoutParams(-1, i));
    } else if (viewParent instanceof LinearLayout) {
      paramView.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, i));
    } else if (viewParent instanceof RelativeLayout) {
      paramView.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, i));
    } else if (viewParent instanceof FrameLayout) {
      paramView.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, i));
    } else if (viewParent instanceof ViewGroup) {
      paramView.setLayoutParams(new ViewGroup.LayoutParams(-1, i));
    } 
    paramView.setVisibility(0);
    paramView.setBackgroundColor(this.b);
  }
  
  protected void a() {}
  
  protected abstract void a(Bundle paramBundle);
  
  protected abstract void a(Message paramMessage);
  
  protected abstract int b();
  
  protected abstract void b(Message paramMessage);
  
  public void finish() {
    ActivityManager.getInstance().removeActivity((Activity)this);
    if (this.mUIHandler != null)
      this.mUIHandler.removeCallbacksAndMessages(null); 
    if (this.mWorkerHandler != null)
      this.mWorkerHandler.removeCallbacksAndMessages(null); 
    if (this.a != null)
      this.a.quit(); 
    super.finish();
  }
  
  protected void onCreate(@Nullable Bundle paramBundle) {
    super.onCreate(paramBundle);
    if (getActionBar() != null)
      getActionBar().hide(); 
    if (getSupportActionBar() != null)
      getSupportActionBar().hide(); 
    this.mContext = StubApp.getOrigApplicationContext(getApplicationContext());
    this.b = getResources().getColor(2130968666);
    a();
    View view = LayoutInflater.from(this.mContext).inflate(b(), null);
    a(view);
    setContentView(view);
    this.mUIHandler = new WeakRefHandler(Looper.getMainLooper(), this, new Handler.Callback(this) {
          public boolean handleMessage(Message param1Message) {
            this.a.a(param1Message);
            return true;
          }
        });
    this.a = new HandlerThread("loadActivityData", 10);
    this.a.start();
    this.mWorkerHandler = new WeakRefHandler(this.a.getLooper(), this, new Handler.Callback(this) {
          public boolean handleMessage(Message param1Message) {
            this.a.b(param1Message);
            return true;
          }
        });
    a(paramBundle);
    ActivityManager.getInstance().addActivity((Activity)this);
  }
  
  public void setStatusBGColor(int paramInt) {
    this.b = paramInt;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\BaseActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */