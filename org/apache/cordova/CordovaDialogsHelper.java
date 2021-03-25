package org.apache.cordova;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class CordovaDialogsHelper {
  private final Context context;
  
  private AlertDialog lastHandledDialog;
  
  public CordovaDialogsHelper(Context paramContext) {
    this.context = paramContext;
  }
  
  public void destroyLastDialog() {
    if (this.lastHandledDialog != null)
      this.lastHandledDialog.cancel(); 
  }
  
  public void showAlert(String paramString, final Result result) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
    builder.setMessage(paramString);
    builder.setTitle("Alert");
    builder.setCancelable(true);
    builder.setPositiveButton(17039370, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            result.gotResult(true, null);
          }
        });
    builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
          public void onCancel(DialogInterface param1DialogInterface) {
            result.gotResult(false, null);
          }
        });
    builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
          public boolean onKey(DialogInterface param1DialogInterface, int param1Int, KeyEvent param1KeyEvent) {
            if (param1Int == 4) {
              result.gotResult(true, null);
              return false;
            } 
            return true;
          }
        });
    this.lastHandledDialog = builder.show();
  }
  
  public void showConfirm(String paramString, final Result result) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
    builder.setMessage(paramString);
    builder.setTitle("Confirm");
    builder.setCancelable(true);
    builder.setPositiveButton(17039370, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            result.gotResult(true, null);
          }
        });
    builder.setNegativeButton(17039360, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            result.gotResult(false, null);
          }
        });
    builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
          public void onCancel(DialogInterface param1DialogInterface) {
            result.gotResult(false, null);
          }
        });
    builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
          public boolean onKey(DialogInterface param1DialogInterface, int param1Int, KeyEvent param1KeyEvent) {
            if (param1Int == 4) {
              result.gotResult(false, null);
              return false;
            } 
            return true;
          }
        });
    this.lastHandledDialog = builder.show();
  }
  
  public void showPrompt(String paramString1, String paramString2, final Result result) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
    builder.setMessage(paramString1);
    final EditText input = new EditText(this.context);
    if (paramString2 != null)
      editText.setText(paramString2); 
    builder.setView((View)editText);
    builder.setCancelable(false);
    builder.setPositiveButton(17039370, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            String str = input.getText().toString();
            result.gotResult(true, str);
          }
        });
    builder.setNegativeButton(17039360, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            result.gotResult(false, null);
          }
        });
    this.lastHandledDialog = builder.show();
  }
  
  public static interface Result {
    void gotResult(boolean param1Boolean, String param1String);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\CordovaDialogsHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */