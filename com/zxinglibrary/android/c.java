package com.zxinglibrary.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public final class c {
  private static final String a = "c";
  
  private final Activity b;
  
  private final BroadcastReceiver c;
  
  private boolean d;
  
  private AsyncTask<Object, Object, Object> e;
  
  public c(Activity paramActivity) {
    this.b = paramActivity;
    this.c = new b();
    this.d = false;
    a();
  }
  
  private void f() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield e : Landroid/os/AsyncTask;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnull -> 22
    //   11: aload_1
    //   12: iconst_1
    //   13: invokevirtual cancel : (Z)Z
    //   16: pop
    //   17: aload_0
    //   18: aconst_null
    //   19: putfield e : Landroid/os/AsyncTask;
    //   22: aload_0
    //   23: monitorexit
    //   24: return
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	25	finally
    //   11	22	25	finally
  }
  
  @SuppressLint({"NewApi"})
  public void a() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial f : ()V
    //   6: aload_0
    //   7: new com/zxinglibrary/android/c$a
    //   10: dup
    //   11: aload_0
    //   12: aconst_null
    //   13: invokespecial <init> : (Lcom/zxinglibrary/android/c;Lcom/zxinglibrary/android/c$1;)V
    //   16: putfield e : Landroid/os/AsyncTask;
    //   19: aload_0
    //   20: getfield e : Landroid/os/AsyncTask;
    //   23: iconst_0
    //   24: anewarray java/lang/Object
    //   27: invokevirtual execute : ([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   30: pop
    //   31: aload_0
    //   32: monitorexit
    //   33: return
    //   34: astore_1
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_1
    //   38: athrow
    // Exception table:
    //   from	to	target	type
    //   2	31	34	finally
  }
  
  public void b() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial f : ()V
    //   6: aload_0
    //   7: getfield d : Z
    //   10: ifeq -> 32
    //   13: aload_0
    //   14: getfield b : Landroid/app/Activity;
    //   17: aload_0
    //   18: getfield c : Landroid/content/BroadcastReceiver;
    //   21: invokevirtual unregisterReceiver : (Landroid/content/BroadcastReceiver;)V
    //   24: aload_0
    //   25: iconst_0
    //   26: putfield d : Z
    //   29: goto -> 41
    //   32: getstatic com/zxinglibrary/android/c.a : Ljava/lang/String;
    //   35: ldc 'PowerStatusReceiver was never registered?'
    //   37: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   40: pop
    //   41: aload_0
    //   42: monitorexit
    //   43: return
    //   44: astore_1
    //   45: aload_0
    //   46: monitorexit
    //   47: aload_1
    //   48: athrow
    // Exception table:
    //   from	to	target	type
    //   2	29	44	finally
    //   32	41	44	finally
  }
  
  public void c() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield d : Z
    //   6: ifeq -> 21
    //   9: getstatic com/zxinglibrary/android/c.a : Ljava/lang/String;
    //   12: ldc 'PowerStatusReceiver was already registered?'
    //   14: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   17: pop
    //   18: goto -> 47
    //   21: aload_0
    //   22: getfield b : Landroid/app/Activity;
    //   25: aload_0
    //   26: getfield c : Landroid/content/BroadcastReceiver;
    //   29: new android/content/IntentFilter
    //   32: dup
    //   33: ldc 'android.intent.action.BATTERY_CHANGED'
    //   35: invokespecial <init> : (Ljava/lang/String;)V
    //   38: invokevirtual registerReceiver : (Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   41: pop
    //   42: aload_0
    //   43: iconst_1
    //   44: putfield d : Z
    //   47: aload_0
    //   48: invokevirtual a : ()V
    //   51: aload_0
    //   52: monitorexit
    //   53: return
    //   54: astore_1
    //   55: aload_0
    //   56: monitorexit
    //   57: aload_1
    //   58: athrow
    // Exception table:
    //   from	to	target	type
    //   2	18	54	finally
    //   21	47	54	finally
    //   47	51	54	finally
  }
  
  public void d() {
    f();
  }
  
  private final class a extends AsyncTask<Object, Object, Object> {
    private a(c this$0) {}
    
    protected Object doInBackground(Object... param1VarArgs) {
      try {
        Thread.sleep(300000L);
        Log.i(c.e(), "Finishing activity due to inactivity");
        c.b(this.a).finish();
      } catch (InterruptedException interruptedException) {}
      return null;
    }
  }
  
  private final class b extends BroadcastReceiver {
    private b(c this$0) {}
    
    public void onReceive(Context param1Context, Intent param1Intent) {
      if ("android.intent.action.BATTERY_CHANGED".equals(param1Intent.getAction())) {
        boolean bool;
        if (param1Intent.getIntExtra("plugged", -1) <= 0) {
          bool = true;
        } else {
          bool = false;
        } 
        if (bool) {
          this.a.a();
          return;
        } 
        c.a(this.a);
      } 
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\zxinglibrary\android\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */