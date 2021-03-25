package com.cndatacom.cnportalclient.download.interfaces;

import com.cndatacom.cnportalclient.download.entity.DownloadTask;

public interface IDownloadThreadEvent {
  int getTaskThreadDownloadedSize(DownloadTask paramDownloadTask, int paramInt);
  
  void taskThreadDownloading(DownloadTask paramDownloadTask, int paramInt1, int paramInt2);
  
  void taskThreadError(DownloadTask paramDownloadTask, int paramInt, String paramString);
  
  void taskThreadFinish(DownloadTask paramDownloadTask, int paramInt1, int paramInt2);
  
  void taskThreadPause(DownloadTask paramDownloadTask, int paramInt1, int paramInt2);
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\download\interfaces\IDownloadThreadEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */