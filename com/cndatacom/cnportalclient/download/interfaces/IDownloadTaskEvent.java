package com.cndatacom.cnportalclient.download.interfaces;

import com.cndatacom.cnportalclient.download.entity.DownloadTask;

public interface IDownloadTaskEvent extends IDownloadThreadEvent {
  boolean getAskWifi();
  
  void taskCancel(DownloadTask paramDownloadTask);
  
  void taskDownloading(DownloadTask paramDownloadTask, int paramInt);
  
  void taskError(DownloadTask paramDownloadTask, String paramString);
  
  void taskFinish(DownloadTask paramDownloadTask, int paramInt);
  
  void taskPause(DownloadTask paramDownloadTask, int paramInt);
  
  void taskWaiting(DownloadTask paramDownloadTask);
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\download\interfaces\IDownloadTaskEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */