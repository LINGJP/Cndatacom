package com.cndatacom.cnportalclient.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.cndatacom.cnportalclient.plugin.PortalMananger;
import java.lang.ref.WeakReference;

public class NetWorkReceiver extends BroadcastReceiver {
  public static final String ACTION_QRCODE_AUTH_ERROR = "com.cndatacom.campus.netcore.qrcode.action.autherror";
  
  private volatile WeakReference<PortalMananger> a = null;
  
  public NetWorkReceiver(PortalMananger paramPortalMananger) {
    this.a = new WeakReference<PortalMananger>(paramPortalMananger);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent) {
    // Byte code:
    //   0: aload_0
    //   1: getfield a : Ljava/lang/ref/WeakReference;
    //   4: invokevirtual get : ()Ljava/lang/Object;
    //   7: checkcast com/cndatacom/cnportalclient/plugin/PortalMananger
    //   10: astore_1
    //   11: aload_1
    //   12: ifnull -> 215
    //   15: aload_1
    //   16: invokevirtual isInitFinish : ()Z
    //   19: ifne -> 23
    //   22: return
    //   23: iconst_2
    //   24: istore_3
    //   25: iconst_2
    //   26: anewarray java/lang/String
    //   29: dup
    //   30: iconst_0
    //   31: ldc 'receive -> '
    //   33: aastore
    //   34: dup
    //   35: iconst_1
    //   36: aload_2
    //   37: invokevirtual getAction : ()Ljava/lang/String;
    //   40: aastore
    //   41: invokestatic i : ([Ljava/lang/String;)V
    //   44: aload_2
    //   45: invokevirtual getAction : ()Ljava/lang/String;
    //   48: astore #5
    //   50: aload #5
    //   52: invokevirtual hashCode : ()I
    //   55: istore #4
    //   57: iload #4
    //   59: ldc -1875733435
    //   61: if_icmpeq -> 109
    //   64: iload #4
    //   66: ldc -1172645946
    //   68: if_icmpeq -> 94
    //   71: iload #4
    //   73: ldc -343630553
    //   75: if_icmpeq -> 81
    //   78: goto -> 124
    //   81: aload #5
    //   83: ldc 'android.net.wifi.STATE_CHANGE'
    //   85: invokevirtual equals : (Ljava/lang/Object;)Z
    //   88: ifeq -> 124
    //   91: goto -> 126
    //   94: aload #5
    //   96: ldc 'android.net.conn.CONNECTIVITY_CHANGE'
    //   98: invokevirtual equals : (Ljava/lang/Object;)Z
    //   101: ifeq -> 124
    //   104: iconst_1
    //   105: istore_3
    //   106: goto -> 126
    //   109: aload #5
    //   111: ldc 'android.net.wifi.WIFI_STATE_CHANGED'
    //   113: invokevirtual equals : (Ljava/lang/Object;)Z
    //   116: ifeq -> 124
    //   119: iconst_0
    //   120: istore_3
    //   121: goto -> 126
    //   124: iconst_m1
    //   125: istore_3
    //   126: iload_3
    //   127: tableswitch default -> 152, 0 -> 187, 1 -> 170, 2 -> 153
    //   152: return
    //   153: iconst_1
    //   154: anewarray java/lang/String
    //   157: dup
    //   158: iconst_0
    //   159: ldc 'receive -> STATE_CHANGE'
    //   161: aastore
    //   162: invokestatic i : ([Ljava/lang/String;)V
    //   165: aload_1
    //   166: invokevirtual onNetworkChange : ()V
    //   169: return
    //   170: iconst_1
    //   171: anewarray java/lang/String
    //   174: dup
    //   175: iconst_0
    //   176: ldc 'receive -> CONNECTIVITY_CHANGE'
    //   178: aastore
    //   179: invokestatic i : ([Ljava/lang/String;)V
    //   182: aload_1
    //   183: invokevirtual onNetworkChange : ()V
    //   186: return
    //   187: aload_2
    //   188: ldc 'wifi_state'
    //   190: iconst_1
    //   191: invokevirtual getIntExtra : (Ljava/lang/String;I)I
    //   194: iconst_3
    //   195: if_icmpeq -> 214
    //   198: iconst_1
    //   199: anewarray java/lang/String
    //   202: dup
    //   203: iconst_0
    //   204: ldc 'receive -> WIFI_STATE_CHANGED'
    //   206: aastore
    //   207: invokestatic i : ([Ljava/lang/String;)V
    //   210: aload_1
    //   211: invokevirtual onNetworkChange : ()V
    //   214: return
    //   215: return
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\receiver\NetWorkReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */