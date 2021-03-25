package com.cndatacom.cnportalclient.zxing;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.a.a.a;
import com.a.a.e;
import com.a.a.m;
import com.a.a.p;
import com.cndatacom.cnportalclient.CaptureActivity;
import com.cndatacom.cnportalclient.zxing.decode.DecodeThread;
import com.zxinglibrary.a.d;
import com.zxinglibrary.view.a;
import java.util.Collection;
import java.util.Map;

public final class CaptureActivityHandler extends Handler {
  private static final String a = "CaptureActivityHandler";
  
  private final CaptureActivity b;
  
  private final DecodeThread c;
  
  private State d;
  
  private final d e;
  
  public CaptureActivityHandler(CaptureActivity paramCaptureActivity, Collection<a> paramCollection, Map<e, ?> paramMap, String paramString, d paramd) {
    this.b = paramCaptureActivity;
    this.c = new DecodeThread(paramCaptureActivity, paramCollection, paramMap, paramString, (p)new a(paramCaptureActivity.getViewfinderView()));
    this.c.start();
    this.d = State.SUCCESS;
    this.e = paramd;
    paramd.c();
    restartPreviewAndDecode();
  }
  
  public void handleMessage(Message paramMessage) {
    Bitmap bitmap;
    if (paramMessage.what == 2131165307) {
      restartPreviewAndDecode();
      return;
    } 
    int i = paramMessage.what;
    byte[] arrayOfByte3 = null;
    byte[] arrayOfByte1 = null;
    byte[] arrayOfByte2 = null;
    if (i == 2131165246) {
      this.d = State.SUCCESS;
      Bundle bundle = paramMessage.getData();
      float f = 1.0F;
      arrayOfByte1 = arrayOfByte3;
      if (bundle != null) {
        arrayOfByte3 = bundle.getByteArray("barcode_bitmap");
        arrayOfByte1 = arrayOfByte2;
        if (arrayOfByte3 != null)
          bitmap = BitmapFactory.decodeByteArray(arrayOfByte3, 0, arrayOfByte3.length, null).copy(Bitmap.Config.ARGB_8888, true); 
        f = bundle.getFloat("barcode_scaled_factor");
      } 
      this.b.handleDecode((m)paramMessage.obj, bitmap, f);
      return;
    } 
    if (paramMessage.what == 2131165245) {
      this.d = State.PREVIEW;
      this.e.a(this.c.getHandler(), 2131165244);
      return;
    } 
    if (paramMessage.what == 2131165309) {
      this.b.setResult(-1, (Intent)paramMessage.obj);
      this.b.finish();
      return;
    } 
    if (paramMessage.what == 2131165277) {
      String str1;
      String str2 = (String)paramMessage.obj;
      Intent intent = new Intent("android.intent.action.VIEW");
      intent.addFlags(524288);
      intent.setData(Uri.parse(str2));
      ResolveInfo resolveInfo = this.b.getPackageManager().resolveActivity(intent, 65536);
      Bitmap bitmap1 = bitmap;
      if (resolveInfo != null) {
        bitmap1 = bitmap;
        if (resolveInfo.activityInfo != null) {
          str1 = resolveInfo.activityInfo.packageName;
          String str = a;
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Using browser in package ");
          stringBuilder.append(str1);
          Log.d(str, stringBuilder.toString());
        } 
      } 
      if ("com.android.browser".equals(str1) || "com.android.chrome".equals(str1)) {
        intent.setPackage(str1);
        intent.addFlags(268435456);
        intent.putExtra("com.android.browser.application_id", str1);
      } 
      try {
        this.b.startActivity(intent);
        return;
      } catch (ActivityNotFoundException activityNotFoundException) {
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Can't find anything to handle VIEW of URI ");
        stringBuilder.append(str2);
        Log.w(str, stringBuilder.toString());
      } 
    } 
  }
  
  public void quitSynchronously() {
    this.d = State.DONE;
    this.e.d();
    Message.obtain(this.c.getHandler(), 2131165305).sendToTarget();
    try {
      this.c.join(500L);
    } catch (InterruptedException interruptedException) {}
    removeMessages(2131165246);
    removeMessages(2131165245);
  }
  
  public void restartPreviewAndDecode() {
    if (this.d == State.SUCCESS) {
      this.d = State.PREVIEW;
      this.e.a(this.c.getHandler(), 2131165244);
    } 
  }
  
  private enum State {
    DONE, PREVIEW, SUCCESS;
    
    static {
    
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\zxing\CaptureActivityHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */