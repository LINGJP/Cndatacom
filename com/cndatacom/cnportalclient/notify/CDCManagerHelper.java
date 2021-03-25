package com.cndatacom.cnportalclient.notify;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.cndatacom.cnportalclient.model.InnerState;
import com.cndatacom.cnportalclient.utils.CdcUtils;
import com.cndatacom.cnportalclient.utils.ExLog;
import java.util.Arrays;

public class CDCManagerHelper {
  private static CDCManagerHelper b;
  
  private HandlerThread a = new HandlerThread("handleCDCMessageData", 10);
  
  private CDCMessager c;
  
  private int d = 0;
  
  private int e = 3;
  
  private final int f = 30000;
  
  private int g = -1;
  
  private final int h = 0;
  
  private CDCMessageCallBack i;
  
  public Handler mWorkerHandler;
  
  private CDCManagerHelper() {
    this.a.start();
    this.mWorkerHandler = new Handler(this.a.getLooper(), new Handler.Callback(this) {
          private void a() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("reverseNotifyRegister time -> ");
            stringBuilder.append(CDCManagerHelper.a(this.a));
            ExLog.i(new String[] { stringBuilder.toString() });
            if (CDCManagerHelper.a(this.a) > 0) {
              CDCManagerHelper.a(this.a, 1);
              CDCManagerHelper.b(this.a);
              this.a.mWorkerHandler.sendEmptyMessageDelayed(0, (CDCManagerHelper.a(this.a) * 1000));
              return;
            } 
            if (CDCManagerHelper.a(this.a) == 0) {
              CDCManagerHelper.a(this.a, 1);
              return;
            } 
            stringBuilder = new StringBuilder();
            stringBuilder.append("reverseNotifyRegister errorCount : ");
            stringBuilder.append(CDCManagerHelper.c(this.a));
            ExLog.i(new String[] { stringBuilder.toString() });
            if (CDCManagerHelper.c(this.a) == CDCManagerHelper.d(this.a)) {
              ExLog.i(new String[] { "reverseNotifyRegister error and finish " });
              return;
            } 
            CDCManagerHelper.e(this.a);
            CDCManagerHelper.b(this.a);
            this.a.mWorkerHandler.sendEmptyMessageDelayed(0, 30000L);
          }
          
          private void a(Message param1Message) {
            if (param1Message.what != 0)
              return; 
            a();
          }
          
          public boolean handleMessage(Message param1Message) {
            a(param1Message);
            return true;
          }
        });
  }
  
  private void a() {
    String str2 = InnerState.load().getTicket();
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
      str1 = ""; 
    this.c.send((short)0, (short)1, str1);
  }
  
  private void b() {
    this.c.receive(new IMessageRecipient(this) {
          public void onRecv(CDCMessageEntity param1CDCMessageEntity) {
            StringBuilder stringBuilder;
            short s = param1CDCMessageEntity.cmdType;
            if (s != 2) {
              if (s != 129)
                return; 
              s = param1CDCMessageEntity.getErrorCode();
              short s1 = param1CDCMessageEntity.getTime();
              stringBuilder = new StringBuilder();
              stringBuilder.append("reverseNotifyReceive error : ");
              stringBuilder.append(s);
              stringBuilder.append(" time : ");
              stringBuilder.append(s1);
              ExLog.i(new String[] { stringBuilder.toString() });
              if (s == 0) {
                CDCManagerHelper.b(this.a, s1);
                return;
              } 
              CDCManagerHelper.b(this.a, -1);
              return;
            } 
            byte[] arrayOfByte = ((CDCMessageEntity)stringBuilder).data.array();
            ExLog.i(new String[] { "reverseNotifyReceive change" });
            String str = InnerState.load().getTicket();
            if (!TextUtils.isEmpty(str) && Arrays.equals(arrayOfByte, CdcUtils.hexToByteArray(str))) {
              CDCManagerHelper.g(this.a).send((short)0, (short)130, str);
              if (CDCManagerHelper.h(this.a) != null)
                CDCManagerHelper.h(this.a).termCallBack(); 
              this.a.release();
            } 
          }
        });
  }
  
  public static CDCManagerHelper newInstance() {
    if (b == null)
      b = new CDCManagerHelper(); 
    return b;
  }
  
  public void init(String paramString, int paramInt) {
    if (this.c == null) {
      this.c = new CDCMessager(paramString, paramInt);
      (new Thread(this) {
          public void run() {
            CDCManagerHelper.b(this.a);
            CDCManagerHelper.f(this.a);
          }
        }).start();
      this.mWorkerHandler.sendEmptyMessageDelayed(0, 30000L);
    } 
  }
  
  public void release() {
    if (this.c != null)
      this.c.close(); 
    if (this.mWorkerHandler != null)
      this.mWorkerHandler.removeCallbacksAndMessages(null); 
    if (this.a != null)
      this.a.quit(); 
    b = null;
    this.c = null;
  }
  
  public void setCDCMessageCallBack(CDCMessageCallBack paramCDCMessageCallBack) {
    this.i = paramCDCMessageCallBack;
  }
  
  public void setErrorCount(int paramInt) {
    this.d = paramInt;
  }
  
  public void setMaxErrorCount(int paramInt) {
    this.e = paramInt;
  }
  
  public static interface CDCMessageCallBack {
    void termCallBack();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\notify\CDCManagerHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */