package com.zxinglibrary.android;

import android.app.Activity;
import android.content.DialogInterface;

public final class b implements DialogInterface.OnCancelListener, DialogInterface.OnClickListener {
  private final Activity a;
  
  public b(Activity paramActivity) {
    this.a = paramActivity;
  }
  
  private void a() {
    this.a.finish();
  }
  
  public void onCancel(DialogInterface paramDialogInterface) {
    a();
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt) {
    a();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\zxinglibrary\android\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */