package com.cndatacom.cnportalclient.service;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import com.cndatacom.cnportalclient.constant.UiErrCode;
import com.cndatacom.cnportalclient.http.CdcProtocol;
import com.cndatacom.cnportalclient.http.PortalConn;
import com.cndatacom.cnportalclient.model.CodeResultInfo;
import com.cndatacom.cnportalclient.model.CodeUploadArgsInfo;
import com.cndatacom.cnportalclient.model.InnerState;
import com.cndatacom.cnportalclient.model.NetInfo;
import com.cndatacom.cnportalclient.model.ReturnUIResult;
import com.cndatacom.cnportalclient.utils.ExLog;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public abstract class KeepInterface {
  public static final String CHANGED_ACTION = "com.cndatacom.campus.netcore.keepservice.CHANGED";
  
  private void a(Context paramContext, ReturnUIResult paramReturnUIResult) {
    Intent intent = new Intent("com.cndatacom.campus.netcore.keepservice.CHANGED");
    if (paramReturnUIResult != null)
      intent.putExtra("error", (Parcelable)paramReturnUIResult); 
    paramContext.sendBroadcast(intent, "com.cndatacom.campus.permissions.PORTAL_RECEIVER");
    a(paramContext);
  }
  
  protected abstract void a(Context paramContext);
  
  protected abstract void a(CdcProtocol.KeepResult paramKeepResult);
  
  public void reqireKeep(Context paramContext, WifiManager paramWifiManager, List<CodeUploadArgsInfo> paramList, ConcurrentHashMap<String, CodeResultInfo> paramConcurrentHashMap, Vector<String> paramVector) {
    if (!paramWifiManager.isWifiEnabled()) {
      ExLog.i(new String[] { "keep when wifi not enable !" });
      return;
    } 
    InnerState innerState2 = InnerState.load();
    if (!innerState2.isOnline()) {
      ExLog.i(new String[] { "keep when not online !" });
      if (CdcProtocol.reqireTerm(innerState2, "3").isSuccess())
        innerState2.setState(0).save(); 
      innerState2.freeDaMod();
      a(paramContext, new ReturnUIResult("200117"));
      return;
    } 
    innerState2 = InnerState.load();
    NetInfo netInfo1 = new NetInfo(paramWifiManager);
    NetInfo netInfo2 = innerState2.getNetInfo();
    netInfo2.setGateway(innerState2.getGateway());
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("doKeep wifi info : ");
    stringBuilder.append(PortalConn.getNetWorkJsonInfo(netInfo1));
    ExLog.i(new String[] { stringBuilder.toString() });
    stringBuilder = new StringBuilder();
    stringBuilder.append("local wifi info : ");
    stringBuilder.append(PortalConn.getNetWorkJsonInfo(netInfo2));
    ExLog.i(new String[] { stringBuilder.toString() });
    if (!netInfo1.equalsNetInfo(innerState2.getNetInfo(), innerState2.getGateway())) {
      ExLog.i(new String[] { "keep when net change !" });
      return;
    } 
    InnerState innerState1 = InnerState.load();
    CdcProtocol.KeepResult keepResult = CdcProtocol.reqireKeep(innerState1, paramList, paramConcurrentHashMap, paramVector);
    ExLog.i(new String[] { "keep => ", keepResult.getError() });
    if ("0".equals(keepResult.getError())) {
      innerState1.save();
      innerState1.freeDaMod();
      a(keepResult);
      return;
    } 
    if (UiErrCode.isProtocolError(keepResult.getError()) && "14".equals(keepResult.getError())) {
      if (CdcProtocol.reqireTerm(innerState1, "3").isSuccess())
        innerState1.setState(0).save(); 
      innerState1.freeDaMod();
      a(paramContext, new ReturnUIResult(keepResult.getError(), keepResult.getSubError()));
      return;
    } 
    if (CdcProtocol.detectConfig(paramContext, InnerState.obtain()) == 1) {
      if (CdcProtocol.reqireTerm(innerState1, "4").isSuccess())
        innerState1.setState(0).save(); 
      innerState1.freeDaMod();
      a(paramContext, new ReturnUIResult("200117"));
      return;
    } 
    innerState1.freeDaMod();
    a(keepResult);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\service\KeepInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */