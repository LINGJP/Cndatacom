package com.cndatacom.cnportalclient.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class DialogUtils {
  public static AlertDialog.Builder createCBDialogBuilder(Activity paramActivity, String paramString1, String paramString2, CheckBoxDialogListener paramCheckBoxDialogListener) {
    View view = LayoutInflater.from((Context)paramActivity).inflate(2131296288, null);
    ((TextView)view.findViewById(2131165241)).setText(paramString1);
    CheckBox checkBox = (CheckBox)view.findViewById(2131165231);
    checkBox.setText(paramString2);
    checkBox.setOnClickListener(new View.OnClickListener(paramCheckBoxDialogListener, checkBox) {
          public void onClick(View param1View) {
            if (this.a != null)
              this.a.checkBoxListener(this.b.isChecked()); 
          }
        });
    return (new AlertDialog.Builder((Context)paramActivity)).setView(view);
  }
  
  public static interface CheckBoxDialogListener {
    void checkBoxListener(boolean param1Boolean);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclien\\utils\DialogUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */