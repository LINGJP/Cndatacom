package com.cndatacom.cnportalclient.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

public class SpannableStringUtils {
  public static void bindLinkText(Context paramContext, TextView paramTextView, String paramString1, String paramString2, String paramString3, boolean paramBoolean, View.OnClickListener paramOnClickListener) {
    SpannableString spannableString = new SpannableString(paramString1);
    if (!TextUtils.isEmpty(paramString2)) {
      ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#ff0000"));
      int k = paramString1.indexOf(paramString2);
      spannableString.setSpan(foregroundColorSpan, k, paramString2.length() + k, 17);
    } 
    ClickableSpan clickableSpan = new ClickableSpan(paramOnClickListener, paramContext) {
        public void onClick(View param1View) {
          if (this.a != null)
            this.a.onClick(param1View); 
        }
        
        public void updateDrawState(TextPaint param1TextPaint) {
          super.updateDrawState(param1TextPaint);
          param1TextPaint.setColor(this.b.getResources().getColor(2130968666));
          param1TextPaint.setUnderlineText(false);
          param1TextPaint.clearShadowLayer();
        }
      };
    int i = paramString1.indexOf(paramString3);
    int j = paramString3.length() + i;
    spannableString.setSpan(clickableSpan, i, j, 18);
    if (paramBoolean)
      spannableString.setSpan(new UnderlineSpan(), i, j, 33); 
    paramTextView.setText((CharSequence)spannableString);
    paramTextView.setMovementMethod(LinkMovementMethod.getInstance());
  }
  
  public static void bindLinkText(Context paramContext, TextView paramTextView, String paramString1, String paramString2, boolean paramBoolean, View.OnClickListener paramOnClickListener) {
    bindLinkText(paramContext, paramTextView, paramString1, null, paramString2, paramBoolean, paramOnClickListener);
  }
  
  public static void bindLinkText(Context paramContext, TextView paramTextView, String paramString, String[] paramArrayOfString, View.OnClickListener paramOnClickListener) {
    SpannableString spannableString = new SpannableString(paramString);
    int i;
    for (i = 0; i < paramArrayOfString.length; i++) {
      String str = paramArrayOfString[i];
      ClickableSpan clickableSpan = new ClickableSpan(paramOnClickListener, i, paramContext) {
          public void onClick(View param1View) {
            if (this.a != null) {
              param1View.setTag(Integer.valueOf(this.b));
              this.a.onClick(param1View);
            } 
          }
          
          public void updateDrawState(TextPaint param1TextPaint) {
            super.updateDrawState(param1TextPaint);
            param1TextPaint.setColor(this.c.getResources().getColor(2130968666));
            param1TextPaint.setUnderlineText(false);
            param1TextPaint.clearShadowLayer();
          }
        };
      int j = paramString.indexOf(str);
      spannableString.setSpan(clickableSpan, j, str.length() + j, 18);
    } 
    paramTextView.setText((CharSequence)spannableString);
    paramTextView.setMovementMethod(LinkMovementMethod.getInstance());
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclien\\utils\SpannableStringUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */