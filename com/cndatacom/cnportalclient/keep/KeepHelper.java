package com.cndatacom.cnportalclient.keep;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.cndatacom.cnportalclient.http.CdcProtocol;
import com.cndatacom.cnportalclient.model.CodeCheckInfo;
import com.cndatacom.cnportalclient.model.CodeResultInfo;
import com.cndatacom.cnportalclient.model.CodeUploadArgsInfo;
import com.cndatacom.cnportalclient.service.KeepInterface;
import com.cndatacom.cnportalclient.utils.CdcUtils;
import com.cndatacom.cnportalclient.utils.ExLog;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

public class KeepHelper {
  private static KeepHelper p;
  
  private static Context q;
  
  private static String r;
  
  private static int s;
  
  private static int t;
  
  private DatagramChannel a;
  
  private ServerSocketChannel b;
  
  private volatile boolean c = false;
  
  private ConcurrentHashMap<String, CodeResultInfo> d = new ConcurrentHashMap<String, CodeResultInfo>();
  
  private List<CodeUploadArgsInfo> e = new ArrayList<CodeUploadArgsInfo>();
  
  private Vector<String> f = new Vector<String>();
  
  private Vector<SocketChannel> g = new Vector<SocketChannel>();
  
  private final int h = 100;
  
  private final int i = 1;
  
  private final int j = 20000;
  
  private final int k = 5000;
  
  private KeepInterface l;
  
  private ExtraInterface m;
  
  private Handler n;
  
  private HandlerThread o;
  
  private KeepHelper() {
    r = CdcUtils.getIP();
    s = (new Random()).nextInt(10000) + 10000;
    t = (new Random()).nextInt(10000) + 20000;
    this.l = new KeepInterface(this) {
        protected void a(Context param1Context) {
          if (KeepHelper.a(this.a) != null)
            KeepHelper.a(this.a).stopService(param1Context); 
        }
        
        protected void a(CdcProtocol.KeepResult param1KeepResult) {
          if (KeepHelper.a(this.a) != null)
            KeepHelper.a(this.a).startNextKeep(param1KeepResult.getInterval()); 
          KeepHelper.b(this.a);
          KeepHelper.c(this.a).clear();
          KeepHelper.a(this.a, param1KeepResult.getUdpCodeCheckInfos());
          KeepHelper.b(this.a, param1KeepResult.getOtherCodeCheckInfos());
        }
      };
    this.o = new HandlerThread("handleKeepData", 0);
    this.o.start();
    this.n = new Handler(this.o.getLooper(), new Handler.Callback(this) {
          private void a(Message param1Message) {
            if (param1Message.what != 1)
              return; 
            ExLog.i(new String[] { "handle retryHelloCode ->" });
            (new AsyncTask<String, Integer, String>(this) {
                protected String a(String... param2VarArgs) {
                  KeepHelper.d(this.a.a);
                  return null;
                }
              }).executeOnExecutor(Executors.newFixedThreadPool(100), (Object[])new String[0]);
          }
          
          public boolean handleMessage(Message param1Message) {
            a(param1Message);
            return true;
          }
        });
  }
  
  private void a(CodeCheckInfo paramCodeCheckInfo) {
    if (!TextUtils.isEmpty(paramCodeCheckInfo.getArgs())) {
      CodeResultInfo codeResultInfo = CodeResultInfo.newInstance(paramCodeCheckInfo);
      String str = codeResultInfo.madeRandomKeyString();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("TcpChannel handleConnData temp ->");
      stringBuilder.append(str);
      stringBuilder.append("-");
      stringBuilder.append(codeResultInfo.getArgs());
      ExLog.d(new String[] { stringBuilder.toString() });
      this.d.put(str, codeResultInfo);
      (new AsyncTask<String, Integer, String>(this, str) {
          protected String a(String... param1VarArgs) {
            KeepHelper.a(this.b, this.a);
            return null;
          }
        }).executeOnExecutor(Executors.newFixedThreadPool(100), (Object[])new String[0]);
    } 
  }
  
  private void a(CodeResultInfo paramCodeResultInfo) {
    String str = paramCodeResultInfo.getArgs();
    if (!TextUtils.isEmpty(str)) {
      String str1 = paramCodeResultInfo.madeKeyString(r, s);
      if (this.a != null) {
        String str2 = "";
        try {
          int i = str.lastIndexOf(":");
          if (i > -1) {
            str2 = str.substring(0, i);
            i = Integer.parseInt(str.substring(i + 1, str.length()));
          } else {
            i = -1;
          } 
          if (i != -1) {
            String str3 = paramCodeResultInfo.getRandomNum();
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("UdpChannel handleHello send->");
            stringBuilder1.append(str);
            stringBuilder1.append("-");
            stringBuilder1.append(str3.length());
            stringBuilder1.append("-");
            stringBuilder1.append(str3);
            ExLog.d(new String[] { stringBuilder1.toString() });
            ByteBuffer byteBuffer = ByteBuffer.allocate(str3.length());
            byteBuffer.put(str3.getBytes());
            byteBuffer.flip();
            i = this.a.send(byteBuffer, new InetSocketAddress(str2, i));
            if (i > 0) {
              paramCodeResultInfo.setResult(0);
              StringBuilder stringBuilder2 = new StringBuilder();
              stringBuilder2.append("UdpChannel handleHello send data length ->");
              stringBuilder2.append(i);
              ExLog.i(new String[] { stringBuilder2.toString() });
            } 
            byteBuffer.clear();
          } 
        } catch (Exception exception) {
          exception.printStackTrace();
        } 
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("UdpChannel handleHello md5Key ->");
      stringBuilder.append(str1);
      ExLog.i(new String[] { stringBuilder.toString() });
      this.d.put(str1, paramCodeResultInfo);
    } 
  }
  
  private void a(String paramString) {
    if (this.d != null && !this.d.isEmpty() && this.d.containsKey(paramString))
      try {
        CodeResultInfo codeResultInfo = this.d.get(paramString);
        String str2 = codeResultInfo.getArgs();
        String str1 = "";
        int i = str2.lastIndexOf(":");
        if (i > -1) {
          str1 = str2.substring(0, i);
          i = Integer.parseInt(str2.substring(i + 1, str2.length()));
        } else {
          i = -1;
        } 
        if (i != -1) {
          SocketChannel socketChannel = SocketChannel.open();
          socketChannel.configureBlocking(true);
          codeResultInfo.setResult(0);
          this.g.add(socketChannel);
          boolean bool = socketChannel.connect(new InetSocketAddress(str1, i));
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("TcpChannel handleConnReceiveData connResult: ");
          stringBuilder.append(str2);
          stringBuilder.append("-");
          stringBuilder.append(bool);
          ExLog.i(new String[] { stringBuilder.toString() });
          if (bool && this.d != null && !this.d.isEmpty() && this.d.containsKey(paramString)) {
            CodeResultInfo codeResultInfo1 = this.d.get(paramString);
            String str3 = codeResultInfo1.getRandomNum();
            ByteBuffer byteBuffer = ByteBuffer.allocate(str3.length());
            byteBuffer.put(str3.getBytes());
            byteBuffer.flip();
            while (byteBuffer.hasRemaining())
              socketChannel.write(byteBuffer); 
            byteBuffer.clear();
            String str4 = socketChannel.socket().getLocalSocketAddress().toString().replaceAll("/", "");
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("TcpChannel handleConnReceiveData client socketAddress ->");
            stringBuilder3.append(str4);
            ExLog.d(new String[] { stringBuilder3.toString() });
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str3);
            stringBuilder3.append(str4);
            str4 = CdcUtils.byteArrayToHex(CdcUtils.md5(stringBuilder3.toString().getBytes())).toLowerCase();
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append("TcpChannel handleConnReceiveData send->");
            stringBuilder3.append(str2);
            stringBuilder3.append("-");
            stringBuilder3.append(str3);
            ExLog.i(new String[] { stringBuilder3.toString() });
            codeResultInfo1.setResult(0);
            this.d.remove(paramString);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("TcpChannel handleConnReceiveData remove tempKey: ");
            stringBuilder2.append(paramString);
            stringBuilder2.append("-");
            stringBuilder2.append(codeResultInfo1.getArgs());
            ExLog.d(new String[] { stringBuilder2.toString() });
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("TcpChannel handleConnReceiveData add md5Key: ");
            stringBuilder1.append(str4);
            stringBuilder1.append("-");
            stringBuilder1.append(codeResultInfo1.getArgs());
            ExLog.i(new String[] { stringBuilder1.toString() });
            this.d.put(str4, codeResultInfo1);
            a(socketChannel);
            return;
          } 
        } 
      } catch (Exception exception) {
        exception.printStackTrace();
      }  
  }
  
  private void a(SocketChannel paramSocketChannel) {
    (new Thread(this, paramSocketChannel) {
        public void run() {
          try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(32);
            if (!KeepHelper.e(this.b) && this.a != null) {
              ExLog.i(new String[] { "TcpChannel clientReceiveTask socketChannel ->" });
              if (this.a.read(byteBuffer) > 0) {
                int i = byteBuffer.position();
                if (i > 0) {
                  byte[] arrayOfByte = new byte[i];
                  byteBuffer.flip();
                  for (int j = 0; j < i; j++)
                    arrayOfByte[j] = byteBuffer.get(); 
                  String str1 = this.a.socket().getRemoteSocketAddress().toString().replaceAll("/", "");
                  String str2 = new String(arrayOfByte, "UTF-8");
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append("TcpChannel clientReceiveTask received ->");
                  stringBuilder.append(str1);
                  stringBuilder.append("-");
                  stringBuilder.append(i);
                  stringBuilder.append("-");
                  stringBuilder.append(str2);
                  ExLog.i(new String[] { stringBuilder.toString() });
                  if (i == 32 && KeepHelper.c(this.b) != null && !KeepHelper.c(this.b).isEmpty() && KeepHelper.c(this.b).containsKey(str2)) {
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append("TcpChannel clientReceiveTask containsKey ->");
                    stringBuilder1.append(str2);
                    ExLog.i(new String[] { stringBuilder1.toString() });
                    ((CodeResultInfo)KeepHelper.c(this.b).get(str2)).setResult(1);
                  } 
                } 
                byteBuffer.clear();
              } 
            } 
          } catch (Exception exception) {
            exception.printStackTrace();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("TcpChannel clientReceiveTask Exception ->");
            stringBuilder.append(exception.getMessage());
            ExLog.e(new String[] { stringBuilder.toString() });
          } 
          ExLog.i(new String[] { "TcpChannel clientReceiveTask finish ->" });
        }
      }).start();
  }
  
  private void a(List<CodeCheckInfo> paramList) {
    Vector<CodeCheckInfo> vector = new Vector();
    if (paramList != null && paramList.size() > 0) {
      ExLog.i(new String[] { "handleUdpCode ->" });
      int i = 0;
      boolean bool = false;
      while (i < paramList.size()) {
        CodeCheckInfo codeCheckInfo = paramList.get(i);
        String str = codeCheckInfo.getCmd();
        byte b = -1;
        int j = str.hashCode();
        if (j != 3433489) {
          if (j == 99162322 && str.equals("hello"))
            b = 0; 
        } else if (str.equals("pass")) {
          b = 1;
        } 
        switch (b) {
          case 1:
            vector.add(codeCheckInfo);
            break;
          case 0:
            (new AsyncTask<String, Integer, String>(this, codeCheckInfo) {
                protected String a(String... param1VarArgs) {
                  CodeResultInfo codeResultInfo = CodeResultInfo.newInstance(this.a);
                  KeepHelper.a(this.b, codeResultInfo);
                  return null;
                }
              }).executeOnExecutor(Executors.newFixedThreadPool(100), (Object[])new String[0]);
            bool = true;
            break;
        } 
        i++;
      } 
      if (bool)
        this.n.sendEmptyMessageDelayed(1, 20000L); 
    } 
    a(vector);
  }
  
  private void a(Vector<CodeCheckInfo> paramVector) {
    if (paramVector != null && paramVector.size() > 0)
      for (int i = 0; i < paramVector.size(); i++) {
        CodeCheckInfo codeCheckInfo = paramVector.get(i);
        String str = codeCheckInfo.getArgs();
        if (!TextUtils.isEmpty(str)) {
          CodeResultInfo codeResultInfo = CodeResultInfo.newInstance(codeCheckInfo);
          codeResultInfo.setResult(0);
          str = CodeResultInfo.madeIPKeyString(str);
          if (this.f.contains(str))
            codeResultInfo.setResult(1); 
          this.d.put(str, codeResultInfo);
        } 
      }  
    this.f.clear();
  }
  
  private void b() {
    if (this.g != null && this.g.size() > 0) {
      for (int i = 0; i < this.g.size(); i++) {
        SocketChannel socketChannel = this.g.get(i);
        try {
          socketChannel.close();
        } catch (IOException iOException) {
          iOException.printStackTrace();
        } 
      } 
      this.g.clear();
    } 
  }
  
  private void b(CodeCheckInfo paramCodeCheckInfo) {
    String str = paramCodeCheckInfo.getArgs();
    if (!TextUtils.isEmpty(str)) {
      CodeResultInfo codeResultInfo = CodeResultInfo.newInstance(paramCodeCheckInfo);
      String str1 = codeResultInfo.madeRandomKeyString();
      this.d.put(str1, codeResultInfo);
      (new AsyncTask<String, Integer, String>(this, str, str1, codeResultInfo) {
          protected String a(String... param1VarArgs) {
            try {
              Runtime runtime = Runtime.getRuntime();
              StringBuilder stringBuilder2 = new StringBuilder();
              stringBuilder2.append("ping -c 4 -w 20 ");
              stringBuilder2.append(this.a);
              Process process = runtime.exec(stringBuilder2.toString());
              if (KeepHelper.c(this.d) != null && !KeepHelper.c(this.d).isEmpty() && KeepHelper.c(this.d).containsKey(this.b))
                ((CodeResultInfo)KeepHelper.c(this.d).get(this.b)).setResult(0); 
              if (process.waitFor() == 0 && KeepHelper.c(this.d) != null && !KeepHelper.c(this.d).isEmpty() && KeepHelper.c(this.d).containsKey(this.b))
                ((CodeResultInfo)KeepHelper.c(this.d).get(this.b)).setResult(1); 
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("handlePing code and result ->");
              stringBuilder1.append(this.c.getArgs());
              stringBuilder1.append(" ");
              stringBuilder1.append(this.c.getResult());
              ExLog.i(new String[] { stringBuilder1.toString() });
            } catch (Exception exception) {
              exception.printStackTrace();
            } 
            return null;
          }
        }).executeOnExecutor(Executors.newFixedThreadPool(100), (Object[])new String[0]);
    } 
  }
  
  private void b(List<CodeCheckInfo> paramList) {
    // Byte code:
    //   0: aload_1
    //   1: ifnull -> 161
    //   4: aload_1
    //   5: invokeinterface size : ()I
    //   10: ifle -> 161
    //   13: iconst_1
    //   14: anewarray java/lang/String
    //   17: dup
    //   18: iconst_0
    //   19: ldc_w 'handleOtherCode ->'
    //   22: aastore
    //   23: invokestatic i : ([Ljava/lang/String;)V
    //   26: iconst_0
    //   27: istore_3
    //   28: iload_3
    //   29: aload_1
    //   30: invokeinterface size : ()I
    //   35: if_icmpge -> 161
    //   38: aload_1
    //   39: iload_3
    //   40: invokeinterface get : (I)Ljava/lang/Object;
    //   45: checkcast com/cndatacom/cnportalclient/model/CodeCheckInfo
    //   48: astore #4
    //   50: aload #4
    //   52: invokevirtual getCmd : ()Ljava/lang/String;
    //   55: astore #5
    //   57: aload #5
    //   59: invokevirtual hashCode : ()I
    //   62: istore_2
    //   63: iload_2
    //   64: ldc_w 3059500
    //   67: if_icmpeq -> 96
    //   70: iload_2
    //   71: ldc_w 3441010
    //   74: if_icmpeq -> 80
    //   77: goto -> 112
    //   80: aload #5
    //   82: ldc_w 'ping'
    //   85: invokevirtual equals : (Ljava/lang/Object;)Z
    //   88: ifeq -> 112
    //   91: iconst_0
    //   92: istore_2
    //   93: goto -> 114
    //   96: aload #5
    //   98: ldc_w 'conn'
    //   101: invokevirtual equals : (Ljava/lang/Object;)Z
    //   104: ifeq -> 112
    //   107: iconst_1
    //   108: istore_2
    //   109: goto -> 114
    //   112: iconst_m1
    //   113: istore_2
    //   114: iload_2
    //   115: tableswitch default -> 136, 0 -> 148, 1 -> 139
    //   136: goto -> 154
    //   139: aload_0
    //   140: aload #4
    //   142: invokespecial a : (Lcom/cndatacom/cnportalclient/model/CodeCheckInfo;)V
    //   145: goto -> 154
    //   148: aload_0
    //   149: aload #4
    //   151: invokespecial b : (Lcom/cndatacom/cnportalclient/model/CodeCheckInfo;)V
    //   154: iload_3
    //   155: iconst_1
    //   156: iadd
    //   157: istore_3
    //   158: goto -> 28
    //   161: return
  }
  
  private void c() {
    if (this.d != null && !this.d.isEmpty()) {
      Iterator<Map.Entry> iterator = this.d.entrySet().iterator();
      boolean bool = false;
      while (iterator.hasNext()) {
        CodeResultInfo codeResultInfo = (CodeResultInfo)((Map.Entry)iterator.next()).getValue();
        if (codeResultInfo.isHelloCode() && codeResultInfo.getResult() != 1) {
          (new AsyncTask<String, Integer, String>(this, codeResultInfo) {
              protected String a(String... param1VarArgs) {
                KeepHelper.a(this.b, this.a);
                return null;
              }
            }).executeOnExecutor(Executors.newFixedThreadPool(100), (Object[])new String[0]);
          bool = true;
        } 
      } 
      if (bool)
        this.n.sendEmptyMessageDelayed(1, 20000L); 
    } 
  }
  
  private void d() {
    if (this.b == null) {
      ExLog.d(new String[] { "TcpChannel createTcpServer ->" });
      try {
        this.b = ServerSocketChannel.open();
        this.b.socket().bind(new InetSocketAddress(t));
        this.b.configureBlocking(true);
        e();
        this.e.add(CodeUploadArgsInfo.madeConnCodeUploadArgsInfo(t));
      } catch (Exception exception) {
        exception.printStackTrace();
        this.b = null;
      } 
      if (this.b != null) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TcpChannel createTcpServer success ->");
        stringBuilder.append(t);
        ExLog.d(new String[] { stringBuilder.toString() });
        return;
      } 
      ExLog.d(new String[] { "TcpChannel createTcpServer error ->" });
    } 
  }
  
  private void e() {
    (new Thread(this) {
        public void run() {
          try {
            while (!KeepHelper.e(this.a) && KeepHelper.f(this.a) != null) {
              ExLog.d(new String[] { "TcpChannel tcpServerReceive receive ->" });
              SocketChannel socketChannel = KeepHelper.f(this.a).accept();
              ByteBuffer byteBuffer = ByteBuffer.allocate(32);
              if (!KeepHelper.e(this.a) && socketChannel != null && socketChannel.read(byteBuffer) > 0) {
                int i = byteBuffer.position();
                if (i > 0) {
                  byte[] arrayOfByte = new byte[i];
                  byteBuffer.flip();
                  int j;
                  for (j = 0; j < i; j++)
                    arrayOfByte[j] = byteBuffer.get(); 
                  if (i == 8) {
                    byteBuffer.clear();
                    String str1 = socketChannel.socket().getRemoteSocketAddress().toString().replaceAll("/", "");
                    String str2 = new String(arrayOfByte, "UTF-8");
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("TcpChannel tcpServerReceive receive ->");
                    stringBuilder.append(str1);
                    stringBuilder.append("-");
                    stringBuilder.append(str2.length());
                    stringBuilder.append("-");
                    stringBuilder.append(str2);
                    ExLog.d(new String[] { stringBuilder.toString() });
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str2);
                    stringBuilder.append(str1);
                    str2 = CdcUtils.byteArrayToHex(CdcUtils.md5(stringBuilder.toString().getBytes())).toLowerCase();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("TcpChannel tcpServerReceive send ->");
                    stringBuilder.append(str1);
                    stringBuilder.append("-");
                    stringBuilder.append(str2.length());
                    stringBuilder.append("-");
                    stringBuilder.append(str2);
                    ExLog.d(new String[] { stringBuilder.toString() });
                    byteBuffer.put(str2.getBytes());
                    byteBuffer.flip();
                    j = socketChannel.write(byteBuffer);
                    if (j > 0) {
                      StringBuilder stringBuilder1 = new StringBuilder();
                      stringBuilder1.append("TcpChannel tcpServerReceive send data length ->");
                      stringBuilder1.append(j);
                      ExLog.i(new String[] { stringBuilder1.toString() });
                    } 
                  } 
                } 
                byteBuffer.clear();
              } 
            } 
          } catch (Exception exception) {
            exception.printStackTrace();
          } 
          ExLog.d(new String[] { "TcpChannel tcpServerReceive receive finish ->" });
        }
      }).start();
  }
  
  private void f() {
    if (this.a == null) {
      ExLog.i(new String[] { "UdpChannel createUdp ->" });
      try {
        this.a = DatagramChannel.open();
        this.a.socket().bind(new InetSocketAddress(s));
        this.a.configureBlocking(true);
        this.e.add(CodeUploadArgsInfo.madeHelloCodeUploadArgsInfo(s));
        g();
      } catch (IOException iOException) {
        iOException.printStackTrace();
        this.a = null;
      } 
      if (this.a != null) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UdpChannel createUdp success ->");
        stringBuilder.append(s);
        ExLog.i(new String[] { stringBuilder.toString() });
        return;
      } 
      ExLog.i(new String[] { "UdpChannel createUdp error ->" });
    } 
  }
  
  private void g() {
    (new Thread(this) {
        public void run() {
          try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(32);
            while (true) {
              if (!KeepHelper.e(this.a) && KeepHelper.g(this.a) != null) {
                ExLog.i(new String[] { "UdpChannel updReceiveTask receive ->" });
                SocketAddress socketAddress = KeepHelper.g(this.a).receive(byteBuffer);
                if (socketAddress != null) {
                  int i = byteBuffer.position();
                  if (i > 0) {
                    StringBuilder stringBuilder2;
                    byte[] arrayOfByte = new byte[i];
                    byteBuffer.flip();
                    int j;
                    for (j = 0; j < i; j++)
                      arrayOfByte[j] = byteBuffer.get(); 
                    String str2 = new String(arrayOfByte, "UTF-8");
                    String str1 = socketAddress.toString().replaceAll("/", "");
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append("UdpChannel updReceiveTask receive ->");
                    stringBuilder1.append(str1);
                    stringBuilder1.append("-");
                    stringBuilder1.append(i);
                    stringBuilder1.append("-");
                    stringBuilder1.append(str2);
                    ExLog.i(new String[] { stringBuilder1.toString() });
                    if (i == 8) {
                      j = str1.lastIndexOf(":");
                      if (j > -1) {
                        str4 = str1.substring(0, j);
                      } else {
                        str4 = str1;
                      } 
                      String str4 = CodeResultInfo.madeIPKeyString(str4);
                      if (!KeepHelper.h(this.a).contains(str4))
                        KeepHelper.h(this.a).add(str4); 
                      byteBuffer.clear();
                      StringBuilder stringBuilder = new StringBuilder();
                      stringBuilder.append(str2);
                      stringBuilder.append(str1);
                      String str3 = CdcUtils.byteArrayToHex(CdcUtils.md5(stringBuilder.toString().getBytes())).toLowerCase();
                      stringBuilder2 = new StringBuilder();
                      stringBuilder2.append("UdpChannel updReceiveTask send ->");
                      stringBuilder2.append(str1);
                      stringBuilder2.append("-");
                      stringBuilder2.append(str3.length());
                      stringBuilder2.append("-");
                      stringBuilder2.append(str3);
                      ExLog.d(new String[] { stringBuilder2.toString() });
                      byteBuffer.put(str3.getBytes());
                      byteBuffer.flip();
                      j = KeepHelper.g(this.a).send(byteBuffer, socketAddress);
                      if (j > 0) {
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("UdpChannel updReceiveTask send data length ->");
                        stringBuilder3.append(j);
                        ExLog.i(new String[] { stringBuilder3.toString() });
                      } 
                    } else if (i == 32 && KeepHelper.c(this.a) != null && !KeepHelper.c(this.a).isEmpty() && KeepHelper.c(this.a).containsKey(stringBuilder2)) {
                      stringBuilder1 = new StringBuilder();
                      stringBuilder1.append("UdpChannel updReceiveTask containsKey ->");
                      stringBuilder1.append((String)stringBuilder2);
                      ExLog.i(new String[] { stringBuilder1.toString() });
                      ((CodeResultInfo)KeepHelper.c(this.a).get(stringBuilder2)).setResult(1);
                    } 
                    byteBuffer.clear();
                  } 
                } 
                continue;
              } 
              ExLog.i(new String[] { "UdpChannel updReceiveTask finish ->" });
              return;
            } 
          } catch (Exception exception) {
            exception.printStackTrace();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("UdpChannel updReceiveTask Exception ->");
            stringBuilder.append(exception.getMessage());
            ExLog.e(new String[] { stringBuilder.toString() });
          } 
          ExLog.i(new String[] { "UdpChannel updReceiveTask finish ->" });
        }
      }).start();
  }
  
  public static KeepHelper newInstance(Context paramContext) {
    q = paramContext;
    if (p == null)
      p = new KeepHelper(); 
    return p;
  }
  
  public void doFristKeep() {
    ExLog.d(new String[] { "doFristKeep ->" });
    handleKeep(5000);
  }
  
  public void handleKeep(int paramInt) {
    (new AsyncTask<String, Integer, String>(this, paramInt) {
        protected String a(String... param1VarArgs) {
          try {
            Thread.sleep(this.a);
          } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
          } 
          if (KeepHelper.e(this.b))
            return null; 
          KeepHelper.i(this.b).removeMessages(1);
          KeepHelper.j(this.b);
          KeepHelper.k(this.b);
          WifiManager wifiManager = (WifiManager)KeepHelper.a().getSystemService("wifi");
          KeepHelper.m(this.b).reqireKeep(KeepHelper.a(), wifiManager, KeepHelper.l(this.b), KeepHelper.c(this.b), KeepHelper.h(this.b));
          return null;
        }
      }).executeOnExecutor(Executors.newFixedThreadPool(100), (Object[])new String[0]);
  }
  
  public void release() {
    b();
    this.c = true;
    try {
      if (this.a != null) {
        this.a.close();
        this.a = null;
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    try {
      if (this.b != null) {
        this.b.close();
        this.b = null;
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    if (this.n != null)
      this.n.removeCallbacksAndMessages(null); 
    if (this.o != null)
      this.o.quit(); 
    p = null;
  }
  
  public void setExtraInterface(ExtraInterface paramExtraInterface) {
    this.m = paramExtraInterface;
  }
  
  public static interface ExtraInterface {
    void startNextKeep(long param1Long);
    
    void stopService(Context param1Context);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\keep\KeepHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */