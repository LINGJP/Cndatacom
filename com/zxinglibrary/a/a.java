package com.zxinglibrary.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;

final class a implements Camera.AutoFocusCallback {
  private static final String a = "a";
  
  private static final Collection<String> b = new ArrayList<String>(2);
  
  private boolean c;
  
  private boolean d;
  
  private final boolean e;
  
  private final Camera f;
  
  private AsyncTask<?, ?, ?> g;
  
  static {
    b.add("auto");
    b.add("macro");
  }
  
  a(Context paramContext, Camera paramCamera) {
    this.f = paramCamera;
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    String str1 = paramCamera.getParameters().getFocusMode();
    boolean bool = true;
    if (!sharedPreferences.getBoolean("preferences_auto_focus", true) || !b.contains(str1))
      bool = false; 
    this.e = bool;
    String str2 = a;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Current focus mode '");
    stringBuilder.append(str1);
    stringBuilder.append("'; use auto focus? ");
    stringBuilder.append(this.e);
    Log.i(str2, stringBuilder.toString());
    a();
  }
  
  @SuppressLint({"NewApi"})
  private void c() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield c : Z
    //   6: ifne -> 54
    //   9: aload_0
    //   10: getfield g : Landroid/os/AsyncTask;
    //   13: ifnonnull -> 54
    //   16: new com/zxinglibrary/a/a$a
    //   19: dup
    //   20: aload_0
    //   21: aconst_null
    //   22: invokespecial <init> : (Lcom/zxinglibrary/a/a;Lcom/zxinglibrary/a/a$1;)V
    //   25: astore_1
    //   26: aload_1
    //   27: iconst_0
    //   28: anewarray java/lang/Object
    //   31: invokevirtual execute : ([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   34: pop
    //   35: aload_0
    //   36: aload_1
    //   37: putfield g : Landroid/os/AsyncTask;
    //   40: goto -> 54
    //   43: astore_1
    //   44: getstatic com/zxinglibrary/a/a.a : Ljava/lang/String;
    //   47: ldc 'Could not request auto focus'
    //   49: aload_1
    //   50: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   53: pop
    //   54: aload_0
    //   55: monitorexit
    //   56: return
    //   57: astore_1
    //   58: aload_0
    //   59: monitorexit
    //   60: aload_1
    //   61: athrow
    // Exception table:
    //   from	to	target	type
    //   2	26	57	finally
    //   26	40	43	java/util/concurrent/RejectedExecutionException
    //   26	40	57	finally
    //   44	54	57	finally
  }
  
  private void d() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield g : Landroid/os/AsyncTask;
    //   6: ifnull -> 36
    //   9: aload_0
    //   10: getfield g : Landroid/os/AsyncTask;
    //   13: invokevirtual getStatus : ()Landroid/os/AsyncTask$Status;
    //   16: getstatic android/os/AsyncTask$Status.FINISHED : Landroid/os/AsyncTask$Status;
    //   19: if_acmpeq -> 31
    //   22: aload_0
    //   23: getfield g : Landroid/os/AsyncTask;
    //   26: iconst_1
    //   27: invokevirtual cancel : (Z)Z
    //   30: pop
    //   31: aload_0
    //   32: aconst_null
    //   33: putfield g : Landroid/os/AsyncTask;
    //   36: aload_0
    //   37: monitorexit
    //   38: return
    //   39: astore_1
    //   40: aload_0
    //   41: monitorexit
    //   42: aload_1
    //   43: athrow
    // Exception table:
    //   from	to	target	type
    //   2	31	39	finally
    //   31	36	39	finally
  }
  
  void a() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield e : Z
    //   6: ifeq -> 61
    //   9: aload_0
    //   10: aconst_null
    //   11: putfield g : Landroid/os/AsyncTask;
    //   14: aload_0
    //   15: getfield c : Z
    //   18: ifne -> 61
    //   21: aload_0
    //   22: getfield d : Z
    //   25: istore_1
    //   26: iload_1
    //   27: ifne -> 61
    //   30: aload_0
    //   31: getfield f : Landroid/hardware/Camera;
    //   34: aload_0
    //   35: invokevirtual autoFocus : (Landroid/hardware/Camera$AutoFocusCallback;)V
    //   38: aload_0
    //   39: iconst_1
    //   40: putfield d : Z
    //   43: goto -> 61
    //   46: astore_2
    //   47: getstatic com/zxinglibrary/a/a.a : Ljava/lang/String;
    //   50: ldc 'Unexpected exception while focusing'
    //   52: aload_2
    //   53: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   56: pop
    //   57: aload_0
    //   58: invokespecial c : ()V
    //   61: aload_0
    //   62: monitorexit
    //   63: return
    //   64: astore_2
    //   65: aload_0
    //   66: monitorexit
    //   67: aload_2
    //   68: athrow
    // Exception table:
    //   from	to	target	type
    //   2	26	64	finally
    //   30	43	46	java/lang/RuntimeException
    //   30	43	64	finally
    //   47	61	64	finally
  }
  
  void b() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_1
    //   4: putfield c : Z
    //   7: aload_0
    //   8: getfield e : Z
    //   11: ifeq -> 39
    //   14: aload_0
    //   15: invokespecial d : ()V
    //   18: aload_0
    //   19: getfield f : Landroid/hardware/Camera;
    //   22: invokevirtual cancelAutoFocus : ()V
    //   25: goto -> 39
    //   28: astore_1
    //   29: getstatic com/zxinglibrary/a/a.a : Ljava/lang/String;
    //   32: ldc 'Unexpected exception while cancelling focusing'
    //   34: aload_1
    //   35: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   38: pop
    //   39: aload_0
    //   40: monitorexit
    //   41: return
    //   42: astore_1
    //   43: aload_0
    //   44: monitorexit
    //   45: aload_1
    //   46: athrow
    // Exception table:
    //   from	to	target	type
    //   2	18	42	finally
    //   18	25	28	java/lang/RuntimeException
    //   18	25	42	finally
    //   29	39	42	finally
  }
  
  public void onAutoFocus(boolean paramBoolean, Camera paramCamera) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_0
    //   4: putfield d : Z
    //   7: aload_0
    //   8: invokespecial c : ()V
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: astore_2
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_2
    //   18: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	14	finally
  }
  
  private final class a extends AsyncTask<Object, Object, Object> {
    private a(a this$0) {}
    
    protected Object doInBackground(Object... param1VarArgs) {
      try {
        Thread.sleep(2000L);
      } catch (InterruptedException interruptedException) {}
      this.a.a();
      return null;
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\zxinglibrary\a\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */