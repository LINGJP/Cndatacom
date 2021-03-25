package com.zxinglibrary.android;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import com.stub.StubApp;
import com.zxinglibrary.R;
import java.io.Closeable;
import java.io.IOException;

public final class a implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, Closeable {
  private static final String a = "a";
  
  private final Activity b;
  
  private MediaPlayer c;
  
  private boolean d;
  
  private boolean e;
  
  public a(Activity paramActivity) {
    this.b = paramActivity;
    this.c = null;
    a();
  }
  
  private MediaPlayer a(Context paramContext) {
    MediaPlayer mediaPlayer = new MediaPlayer();
    mediaPlayer.setAudioStreamType(3);
    mediaPlayer.setOnCompletionListener(this);
    mediaPlayer.setOnErrorListener(this);
    try {
      AssetFileDescriptor assetFileDescriptor = paramContext.getResources().openRawResourceFd(R.raw.beep);
      try {
        mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        assetFileDescriptor.close();
        mediaPlayer.setVolume(0.1F, 0.1F);
        return mediaPlayer;
      } finally {
        assetFileDescriptor.close();
      } 
    } catch (IOException iOException) {
      Log.w(a, iOException);
      mediaPlayer.release();
      return null;
    } 
  }
  
  private static boolean a(SharedPreferences paramSharedPreferences, Context paramContext) {
    boolean bool2 = paramSharedPreferences.getBoolean("preferences_play_beep", true);
    boolean bool1 = bool2;
    if (bool2) {
      bool1 = bool2;
      if (((AudioManager)paramContext.getSystemService("audio")).getRingerMode() != 2)
        bool1 = false; 
    } 
    return bool1;
  }
  
  private boolean a(String paramString) {
    return (ContextCompat.checkSelfPermission(StubApp.getOrigApplicationContext(this.b.getApplicationContext()), paramString) == 0 && PermissionChecker.checkSelfPermission(StubApp.getOrigApplicationContext(this.b.getApplicationContext()), paramString) == 0);
  }
  
  public void a() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield b : Landroid/app/Activity;
    //   6: invokestatic getDefaultSharedPreferences : (Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   9: astore_1
    //   10: aload_0
    //   11: aload_1
    //   12: aload_0
    //   13: getfield b : Landroid/app/Activity;
    //   16: invokestatic a : (Landroid/content/SharedPreferences;Landroid/content/Context;)Z
    //   19: putfield d : Z
    //   22: aload_0
    //   23: aload_1
    //   24: ldc 'preferences_vibrate'
    //   26: iconst_0
    //   27: invokeinterface getBoolean : (Ljava/lang/String;Z)Z
    //   32: putfield e : Z
    //   35: aload_0
    //   36: getfield d : Z
    //   39: ifeq -> 69
    //   42: aload_0
    //   43: getfield c : Landroid/media/MediaPlayer;
    //   46: ifnonnull -> 69
    //   49: aload_0
    //   50: getfield b : Landroid/app/Activity;
    //   53: iconst_3
    //   54: invokevirtual setVolumeControlStream : (I)V
    //   57: aload_0
    //   58: aload_0
    //   59: aload_0
    //   60: getfield b : Landroid/app/Activity;
    //   63: invokespecial a : (Landroid/content/Context;)Landroid/media/MediaPlayer;
    //   66: putfield c : Landroid/media/MediaPlayer;
    //   69: aload_0
    //   70: monitorexit
    //   71: return
    //   72: astore_1
    //   73: aload_0
    //   74: monitorexit
    //   75: aload_1
    //   76: athrow
    // Exception table:
    //   from	to	target	type
    //   2	69	72	finally
  }
  
  public void b() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield d : Z
    //   6: ifeq -> 23
    //   9: aload_0
    //   10: getfield c : Landroid/media/MediaPlayer;
    //   13: ifnull -> 23
    //   16: aload_0
    //   17: getfield c : Landroid/media/MediaPlayer;
    //   20: invokevirtual start : ()V
    //   23: aload_0
    //   24: getfield e : Z
    //   27: ifeq -> 62
    //   30: aload_0
    //   31: ldc 'android.permission.VIBRATE'
    //   33: invokespecial a : (Ljava/lang/String;)Z
    //   36: istore_1
    //   37: iload_1
    //   38: ifne -> 44
    //   41: aload_0
    //   42: monitorexit
    //   43: return
    //   44: aload_0
    //   45: getfield b : Landroid/app/Activity;
    //   48: ldc 'vibrator'
    //   50: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
    //   53: checkcast android/os/Vibrator
    //   56: ldc2_w 200
    //   59: invokevirtual vibrate : (J)V
    //   62: aload_0
    //   63: monitorexit
    //   64: return
    //   65: astore_2
    //   66: aload_0
    //   67: monitorexit
    //   68: aload_2
    //   69: athrow
    // Exception table:
    //   from	to	target	type
    //   2	23	65	finally
    //   23	37	65	finally
    //   44	62	65	finally
  }
  
  public void close() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield c : Landroid/media/MediaPlayer;
    //   6: ifnull -> 21
    //   9: aload_0
    //   10: getfield c : Landroid/media/MediaPlayer;
    //   13: invokevirtual release : ()V
    //   16: aload_0
    //   17: aconst_null
    //   18: putfield c : Landroid/media/MediaPlayer;
    //   21: aload_0
    //   22: monitorexit
    //   23: return
    //   24: astore_1
    //   25: aload_0
    //   26: monitorexit
    //   27: aload_1
    //   28: athrow
    // Exception table:
    //   from	to	target	type
    //   2	21	24	finally
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer) {
    paramMediaPlayer.seekTo(0);
  }
  
  public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iload_2
    //   3: bipush #100
    //   5: if_icmpne -> 18
    //   8: aload_0
    //   9: getfield b : Landroid/app/Activity;
    //   12: invokevirtual finish : ()V
    //   15: goto -> 31
    //   18: aload_1
    //   19: invokevirtual release : ()V
    //   22: aload_0
    //   23: aconst_null
    //   24: putfield c : Landroid/media/MediaPlayer;
    //   27: aload_0
    //   28: invokevirtual a : ()V
    //   31: aload_0
    //   32: monitorexit
    //   33: iconst_1
    //   34: ireturn
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_1
    //   38: athrow
    //   39: astore_1
    //   40: goto -> 35
    // Exception table:
    //   from	to	target	type
    //   8	15	39	finally
    //   18	31	39	finally
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\zxinglibrary\android\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */