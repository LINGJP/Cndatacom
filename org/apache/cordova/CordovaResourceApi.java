package org.apache.cordova;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Looper;
import android.util.Base64;
import android.webkit.MimeTypeMap;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

public class CordovaResourceApi {
  private static final String[] LOCAL_FILE_PROJECTION = new String[] { "_data" };
  
  private static final String LOG_TAG = "CordovaResourceApi";
  
  public static final String PLUGIN_URI_SCHEME = "cdvplugin";
  
  public static final int URI_TYPE_ASSET = 1;
  
  public static final int URI_TYPE_CONTENT = 2;
  
  public static final int URI_TYPE_DATA = 4;
  
  public static final int URI_TYPE_FILE = 0;
  
  public static final int URI_TYPE_HTTP = 5;
  
  public static final int URI_TYPE_HTTPS = 6;
  
  public static final int URI_TYPE_PLUGIN = 7;
  
  public static final int URI_TYPE_RESOURCE = 3;
  
  public static final int URI_TYPE_UNKNOWN = -1;
  
  public static Thread jsThread;
  
  private final AssetManager assetManager;
  
  private final ContentResolver contentResolver;
  
  private final PluginManager pluginManager;
  
  private boolean threadCheckingEnabled = true;
  
  public CordovaResourceApi(Context paramContext, PluginManager paramPluginManager) {
    this.contentResolver = paramContext.getContentResolver();
    this.assetManager = paramContext.getAssets();
    this.pluginManager = paramPluginManager;
  }
  
  private void assertBackgroundThread() {
    if (this.threadCheckingEnabled) {
      Thread thread = Thread.currentThread();
      if (thread == Looper.getMainLooper().getThread())
        throw new IllegalStateException("Do not perform IO operations on the UI thread. Use CordovaInterface.getThreadPool() instead."); 
      if (thread == jsThread)
        throw new IllegalStateException("Tried to perform an IO operation on the WebCore thread. Use CordovaInterface.getThreadPool() instead."); 
    } 
  }
  
  private static void assertNonRelative(Uri paramUri) {
    if (!paramUri.isAbsolute())
      throw new IllegalArgumentException("Relative URIs are not supported."); 
  }
  
  private String getDataUriMimeType(Uri paramUri) {
    String str = paramUri.getSchemeSpecificPart();
    int i = str.indexOf(',');
    if (i == -1)
      return null; 
    String[] arrayOfString = str.substring(0, i).split(";");
    return (arrayOfString.length > 0) ? arrayOfString[0] : null;
  }
  
  private String getMimeTypeFromPath(String paramString) {
    int i = paramString.lastIndexOf('.');
    String str = paramString;
    if (i != -1)
      str = paramString.substring(i + 1); 
    paramString = str.toLowerCase(Locale.getDefault());
    return paramString.equals("3ga") ? "audio/3gpp" : (paramString.equals("js") ? "text/javascript" : MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramString));
  }
  
  public static int getUriType(Uri paramUri) {
    assertNonRelative(paramUri);
    String str = paramUri.getScheme();
    return "content".equalsIgnoreCase(str) ? 2 : ("android.resource".equalsIgnoreCase(str) ? 3 : ("file".equalsIgnoreCase(str) ? (paramUri.getPath().startsWith("/android_asset/") ? 1 : 0) : ("data".equalsIgnoreCase(str) ? 4 : ("http".equalsIgnoreCase(str) ? 5 : ("https".equalsIgnoreCase(str) ? 6 : ("cdvplugin".equalsIgnoreCase(str) ? 7 : -1))))));
  }
  
  private OpenForReadResult readDataUri(Uri paramUri) {
    byte[] arrayOfByte;
    String str1 = paramUri.getSchemeSpecificPart();
    int j = str1.indexOf(',');
    String str2 = null;
    if (j == -1)
      return null; 
    String[] arrayOfString = str1.substring(0, j).split(";");
    if (arrayOfString.length > 0)
      str2 = arrayOfString[0]; 
    int i = 1;
    boolean bool = false;
    while (i < arrayOfString.length) {
      if ("base64".equalsIgnoreCase(arrayOfString[i]))
        bool = true; 
      i++;
    } 
    String str3 = str1.substring(j + 1);
    if (bool) {
      arrayOfByte = Base64.decode(str3, 0);
    } else {
      try {
        arrayOfByte = str3.getBytes("UTF-8");
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        arrayOfByte = str3.getBytes();
      } 
    } 
    return new OpenForReadResult(paramUri, new ByteArrayInputStream(arrayOfByte), str2, arrayOfByte.length, null);
  }
  
  public void copyResource(Uri paramUri1, Uri paramUri2) {
    copyResource(openForRead(paramUri1), openOutputStream(paramUri2));
  }
  
  public void copyResource(Uri paramUri, OutputStream paramOutputStream) {
    copyResource(openForRead(paramUri), paramOutputStream);
  }
  
  public void copyResource(OpenForReadResult paramOpenForReadResult, OutputStream paramOutputStream) {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial assertBackgroundThread : ()V
    //   4: aload_1
    //   5: getfield inputStream : Ljava/io/InputStream;
    //   8: astore #8
    //   10: aload #8
    //   12: instanceof java/io/FileInputStream
    //   15: ifeq -> 93
    //   18: aload_2
    //   19: instanceof java/io/FileOutputStream
    //   22: ifeq -> 93
    //   25: aload_1
    //   26: getfield inputStream : Ljava/io/InputStream;
    //   29: checkcast java/io/FileInputStream
    //   32: invokevirtual getChannel : ()Ljava/nio/channels/FileChannel;
    //   35: astore #8
    //   37: aload_2
    //   38: checkcast java/io/FileOutputStream
    //   41: invokevirtual getChannel : ()Ljava/nio/channels/FileChannel;
    //   44: astore #9
    //   46: lconst_0
    //   47: lstore #4
    //   49: aload_1
    //   50: getfield length : J
    //   53: lstore #6
    //   55: aload_1
    //   56: getfield assetFd : Landroid/content/res/AssetFileDescriptor;
    //   59: ifnull -> 71
    //   62: aload_1
    //   63: getfield assetFd : Landroid/content/res/AssetFileDescriptor;
    //   66: invokevirtual getStartOffset : ()J
    //   69: lstore #4
    //   71: aload #8
    //   73: lload #4
    //   75: invokevirtual position : (J)Ljava/nio/channels/FileChannel;
    //   78: pop
    //   79: aload #9
    //   81: aload #8
    //   83: lconst_0
    //   84: lload #6
    //   86: invokevirtual transferFrom : (Ljava/nio/channels/ReadableByteChannel;JJ)J
    //   89: pop2
    //   90: goto -> 116
    //   93: sipush #8192
    //   96: newarray byte
    //   98: astore #9
    //   100: aload #8
    //   102: aload #9
    //   104: iconst_0
    //   105: sipush #8192
    //   108: invokevirtual read : ([BII)I
    //   111: istore_3
    //   112: iload_3
    //   113: ifgt -> 132
    //   116: aload_1
    //   117: getfield inputStream : Ljava/io/InputStream;
    //   120: invokevirtual close : ()V
    //   123: aload_2
    //   124: ifnull -> 131
    //   127: aload_2
    //   128: invokevirtual close : ()V
    //   131: return
    //   132: aload_2
    //   133: aload #9
    //   135: iconst_0
    //   136: iload_3
    //   137: invokevirtual write : ([BII)V
    //   140: goto -> 100
    //   143: astore #8
    //   145: aload_1
    //   146: getfield inputStream : Ljava/io/InputStream;
    //   149: invokevirtual close : ()V
    //   152: aload_2
    //   153: ifnull -> 160
    //   156: aload_2
    //   157: invokevirtual close : ()V
    //   160: aload #8
    //   162: athrow
    // Exception table:
    //   from	to	target	type
    //   4	46	143	finally
    //   49	55	143	finally
    //   55	71	143	finally
    //   71	90	143	finally
    //   93	100	143	finally
    //   100	112	143	finally
    //   132	140	143	finally
  }
  
  public HttpURLConnection createHttpConnection(Uri paramUri) {
    assertBackgroundThread();
    return (HttpURLConnection)(new URL(paramUri.toString())).openConnection();
  }
  
  public String getMimeType(Uri paramUri) {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic getUriType : (Landroid/net/Uri;)I
    //   4: tableswitch default -> 48, 0 -> 121, 1 -> 121, 2 -> 112, 3 -> 112, 4 -> 106, 5 -> 51, 6 -> 51
    //   48: goto -> 130
    //   51: new java/net/URL
    //   54: dup
    //   55: aload_1
    //   56: invokevirtual toString : ()Ljava/lang/String;
    //   59: invokespecial <init> : (Ljava/lang/String;)V
    //   62: invokevirtual openConnection : ()Ljava/net/URLConnection;
    //   65: checkcast java/net/HttpURLConnection
    //   68: astore_1
    //   69: aload_1
    //   70: iconst_0
    //   71: invokevirtual setDoInput : (Z)V
    //   74: aload_1
    //   75: ldc_w 'HEAD'
    //   78: invokevirtual setRequestMethod : (Ljava/lang/String;)V
    //   81: aload_1
    //   82: ldc_w 'Content-Type'
    //   85: invokevirtual getHeaderField : (Ljava/lang/String;)Ljava/lang/String;
    //   88: astore_2
    //   89: aload_2
    //   90: astore_1
    //   91: aload_2
    //   92: ifnull -> 104
    //   95: aload_2
    //   96: ldc ';'
    //   98: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   101: iconst_0
    //   102: aaload
    //   103: astore_1
    //   104: aload_1
    //   105: areturn
    //   106: aload_0
    //   107: aload_1
    //   108: invokespecial getDataUriMimeType : (Landroid/net/Uri;)Ljava/lang/String;
    //   111: areturn
    //   112: aload_0
    //   113: getfield contentResolver : Landroid/content/ContentResolver;
    //   116: aload_1
    //   117: invokevirtual getType : (Landroid/net/Uri;)Ljava/lang/String;
    //   120: areturn
    //   121: aload_0
    //   122: aload_1
    //   123: invokevirtual getPath : ()Ljava/lang/String;
    //   126: invokespecial getMimeTypeFromPath : (Ljava/lang/String;)Ljava/lang/String;
    //   129: areturn
    //   130: aconst_null
    //   131: areturn
    //   132: astore_1
    //   133: goto -> 130
    // Exception table:
    //   from	to	target	type
    //   51	89	132	java/io/IOException
    //   95	104	132	java/io/IOException
  }
  
  public boolean isThreadCheckingEnabled() {
    return this.threadCheckingEnabled;
  }
  
  public File mapUriToFile(Uri paramUri) {
    Cursor cursor;
    assertBackgroundThread();
    int i = getUriType(paramUri);
    if (i != 0) {
      if (i == 2) {
        cursor = this.contentResolver.query(paramUri, LOCAL_FILE_PROJECTION, null, null, null);
        if (cursor != null)
          try {
            i = cursor.getColumnIndex(LOCAL_FILE_PROJECTION[0]);
            if (i != -1 && cursor.getCount() > 0) {
              cursor.moveToFirst();
              String str = cursor.getString(i);
              if (str != null)
                return new File(str); 
            } 
          } finally {
            cursor.close();
          }  
      } 
      return null;
    } 
    return new File(cursor.getPath());
  }
  
  public OpenForReadResult openForRead(Uri paramUri) {
    return openForRead(paramUri, false);
  }
  
  public OpenForReadResult openForRead(Uri paramUri, boolean paramBoolean) {
    // Byte code:
    //   0: iload_2
    //   1: ifne -> 8
    //   4: aload_0
    //   5: invokespecial assertBackgroundThread : ()V
    //   8: aload_1
    //   9: invokestatic getUriType : (Landroid/net/Uri;)I
    //   12: tableswitch default -> 60, 0 -> 353, 1 -> 274, 2 -> 228, 3 -> 228, 4 -> 210, 5 -> 130, 6 -> 130, 7 -> 63
    //   60: goto -> 394
    //   63: aload_1
    //   64: invokevirtual getHost : ()Ljava/lang/String;
    //   67: astore #6
    //   69: aload_0
    //   70: getfield pluginManager : Lorg/apache/cordova/PluginManager;
    //   73: aload #6
    //   75: invokevirtual getPlugin : (Ljava/lang/String;)Lorg/apache/cordova/CordovaPlugin;
    //   78: astore #6
    //   80: aload #6
    //   82: ifnonnull -> 123
    //   85: new java/lang/StringBuilder
    //   88: dup
    //   89: invokespecial <init> : ()V
    //   92: astore #6
    //   94: aload #6
    //   96: ldc_w 'Invalid plugin ID in URI: '
    //   99: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: pop
    //   103: aload #6
    //   105: aload_1
    //   106: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   109: pop
    //   110: new java/io/FileNotFoundException
    //   113: dup
    //   114: aload #6
    //   116: invokevirtual toString : ()Ljava/lang/String;
    //   119: invokespecial <init> : (Ljava/lang/String;)V
    //   122: athrow
    //   123: aload #6
    //   125: aload_1
    //   126: invokevirtual handleOpenForRead : (Landroid/net/Uri;)Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;
    //   129: areturn
    //   130: new java/net/URL
    //   133: dup
    //   134: aload_1
    //   135: invokevirtual toString : ()Ljava/lang/String;
    //   138: invokespecial <init> : (Ljava/lang/String;)V
    //   141: invokevirtual openConnection : ()Ljava/net/URLConnection;
    //   144: checkcast java/net/HttpURLConnection
    //   147: astore #8
    //   149: aload #8
    //   151: iconst_1
    //   152: invokevirtual setDoInput : (Z)V
    //   155: aload #8
    //   157: ldc_w 'Content-Type'
    //   160: invokevirtual getHeaderField : (Ljava/lang/String;)Ljava/lang/String;
    //   163: astore #7
    //   165: aload #7
    //   167: astore #6
    //   169: aload #7
    //   171: ifnull -> 185
    //   174: aload #7
    //   176: ldc ';'
    //   178: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   181: iconst_0
    //   182: aaload
    //   183: astore #6
    //   185: aload #8
    //   187: invokevirtual getContentLength : ()I
    //   190: istore_3
    //   191: new org/apache/cordova/CordovaResourceApi$OpenForReadResult
    //   194: dup
    //   195: aload_1
    //   196: aload #8
    //   198: invokevirtual getInputStream : ()Ljava/io/InputStream;
    //   201: aload #6
    //   203: iload_3
    //   204: i2l
    //   205: aconst_null
    //   206: invokespecial <init> : (Landroid/net/Uri;Ljava/io/InputStream;Ljava/lang/String;JLandroid/content/res/AssetFileDescriptor;)V
    //   209: areturn
    //   210: aload_0
    //   211: aload_1
    //   212: invokespecial readDataUri : (Landroid/net/Uri;)Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;
    //   215: astore #6
    //   217: aload #6
    //   219: ifnonnull -> 225
    //   222: goto -> 394
    //   225: aload #6
    //   227: areturn
    //   228: aload_0
    //   229: getfield contentResolver : Landroid/content/ContentResolver;
    //   232: aload_1
    //   233: invokevirtual getType : (Landroid/net/Uri;)Ljava/lang/String;
    //   236: astore #6
    //   238: aload_0
    //   239: getfield contentResolver : Landroid/content/ContentResolver;
    //   242: aload_1
    //   243: ldc_w 'r'
    //   246: invokevirtual openAssetFileDescriptor : (Landroid/net/Uri;Ljava/lang/String;)Landroid/content/res/AssetFileDescriptor;
    //   249: astore #7
    //   251: new org/apache/cordova/CordovaResourceApi$OpenForReadResult
    //   254: dup
    //   255: aload_1
    //   256: aload #7
    //   258: invokevirtual createInputStream : ()Ljava/io/FileInputStream;
    //   261: aload #6
    //   263: aload #7
    //   265: invokevirtual getLength : ()J
    //   268: aload #7
    //   270: invokespecial <init> : (Landroid/net/Uri;Ljava/io/InputStream;Ljava/lang/String;JLandroid/content/res/AssetFileDescriptor;)V
    //   273: areturn
    //   274: aload_1
    //   275: invokevirtual getPath : ()Ljava/lang/String;
    //   278: bipush #15
    //   280: invokevirtual substring : (I)Ljava/lang/String;
    //   283: astore #8
    //   285: aconst_null
    //   286: astore #7
    //   288: aload_0
    //   289: getfield assetManager : Landroid/content/res/AssetManager;
    //   292: aload #8
    //   294: invokevirtual openFd : (Ljava/lang/String;)Landroid/content/res/AssetFileDescriptor;
    //   297: astore #6
    //   299: aload #6
    //   301: invokevirtual createInputStream : ()Ljava/io/FileInputStream;
    //   304: astore #7
    //   306: aload #6
    //   308: invokevirtual getLength : ()J
    //   311: lstore #4
    //   313: goto -> 332
    //   316: aload_0
    //   317: getfield assetManager : Landroid/content/res/AssetManager;
    //   320: aload #8
    //   322: invokevirtual open : (Ljava/lang/String;)Ljava/io/InputStream;
    //   325: astore #7
    //   327: ldc2_w -1
    //   330: lstore #4
    //   332: new org/apache/cordova/CordovaResourceApi$OpenForReadResult
    //   335: dup
    //   336: aload_1
    //   337: aload #7
    //   339: aload_0
    //   340: aload #8
    //   342: invokespecial getMimeTypeFromPath : (Ljava/lang/String;)Ljava/lang/String;
    //   345: lload #4
    //   347: aload #6
    //   349: invokespecial <init> : (Landroid/net/Uri;Ljava/io/InputStream;Ljava/lang/String;JLandroid/content/res/AssetFileDescriptor;)V
    //   352: areturn
    //   353: new java/io/FileInputStream
    //   356: dup
    //   357: aload_1
    //   358: invokevirtual getPath : ()Ljava/lang/String;
    //   361: invokespecial <init> : (Ljava/lang/String;)V
    //   364: astore #6
    //   366: new org/apache/cordova/CordovaResourceApi$OpenForReadResult
    //   369: dup
    //   370: aload_1
    //   371: aload #6
    //   373: aload_0
    //   374: aload_1
    //   375: invokevirtual getPath : ()Ljava/lang/String;
    //   378: invokespecial getMimeTypeFromPath : (Ljava/lang/String;)Ljava/lang/String;
    //   381: aload #6
    //   383: invokevirtual getChannel : ()Ljava/nio/channels/FileChannel;
    //   386: invokevirtual size : ()J
    //   389: aconst_null
    //   390: invokespecial <init> : (Landroid/net/Uri;Ljava/io/InputStream;Ljava/lang/String;JLandroid/content/res/AssetFileDescriptor;)V
    //   393: areturn
    //   394: new java/lang/StringBuilder
    //   397: dup
    //   398: invokespecial <init> : ()V
    //   401: astore #6
    //   403: aload #6
    //   405: ldc_w 'URI not supported by CordovaResourceApi: '
    //   408: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   411: pop
    //   412: aload #6
    //   414: aload_1
    //   415: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   418: pop
    //   419: new java/io/FileNotFoundException
    //   422: dup
    //   423: aload #6
    //   425: invokevirtual toString : ()Ljava/lang/String;
    //   428: invokespecial <init> : (Ljava/lang/String;)V
    //   431: athrow
    //   432: astore #6
    //   434: aload #7
    //   436: astore #6
    //   438: goto -> 316
    //   441: astore #7
    //   443: goto -> 316
    // Exception table:
    //   from	to	target	type
    //   288	299	432	java/io/FileNotFoundException
    //   299	313	441	java/io/FileNotFoundException
  }
  
  public OutputStream openOutputStream(Uri paramUri) {
    return openOutputStream(paramUri, false);
  }
  
  public OutputStream openOutputStream(Uri paramUri, boolean paramBoolean) {
    assertBackgroundThread();
    int i = getUriType(paramUri);
    if (i != 0) {
      StringBuilder stringBuilder;
      String str;
      switch (i) {
        default:
          stringBuilder = new StringBuilder();
          stringBuilder.append("URI not supported by CordovaResourceApi: ");
          stringBuilder.append(paramUri);
          throw new FileNotFoundException(stringBuilder.toString());
        case 2:
        case 3:
          break;
      } 
      ContentResolver contentResolver = this.contentResolver;
      if (paramBoolean) {
        str = "wa";
      } else {
        str = "w";
      } 
      return contentResolver.openAssetFileDescriptor(paramUri, str).createOutputStream();
    } 
    File file1 = new File(paramUri.getPath());
    File file2 = file1.getParentFile();
    if (file2 != null)
      file2.mkdirs(); 
    return new FileOutputStream(file1, paramBoolean);
  }
  
  public String remapPath(String paramString) {
    return remapUri(Uri.fromFile(new File(paramString))).getPath();
  }
  
  public Uri remapUri(Uri paramUri) {
    assertNonRelative(paramUri);
    Uri uri = this.pluginManager.remapUri(paramUri);
    if (uri != null)
      paramUri = uri; 
    return paramUri;
  }
  
  public void setThreadCheckingEnabled(boolean paramBoolean) {
    this.threadCheckingEnabled = paramBoolean;
  }
  
  public static final class OpenForReadResult {
    public final AssetFileDescriptor assetFd;
    
    public final InputStream inputStream;
    
    public final long length;
    
    public final String mimeType;
    
    public final Uri uri;
    
    public OpenForReadResult(Uri param1Uri, InputStream param1InputStream, String param1String, long param1Long, AssetFileDescriptor param1AssetFileDescriptor) {
      this.uri = param1Uri;
      this.inputStream = param1InputStream;
      this.mimeType = param1String;
      this.length = param1Long;
      this.assetFd = param1AssetFileDescriptor;
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\CordovaResourceApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */