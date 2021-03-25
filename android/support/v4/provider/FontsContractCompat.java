package android.support.v4.provider;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.graphics.TypefaceCompatUtil;
import android.support.v4.util.LruCache;
import android.support.v4.util.Preconditions;
import android.support.v4.util.SimpleArrayMap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class FontsContractCompat {
  private static final int BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS = 10000;
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static final String PARCEL_FONT_RESULTS = "font_results";
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  static final int RESULT_CODE_WRONG_CERTIFICATES = -2;
  
  private static final String TAG = "FontsContractCompat";
  
  private static final SelfDestructiveThread sBackgroundThread;
  
  private static final Comparator<byte[]> sByteArrayComparator;
  
  static final Object sLock;
  
  @GuardedBy("sLock")
  static final SimpleArrayMap<String, ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>>> sPendingReplies;
  
  static final LruCache<String, Typeface> sTypefaceCache = new LruCache(16);
  
  static {
    sBackgroundThread = new SelfDestructiveThread("fonts", 10, 10000);
    sLock = new Object();
    sPendingReplies = new SimpleArrayMap();
    sByteArrayComparator = new Comparator<byte[]>() {
        public int compare(byte[] param1ArrayOfbyte1, byte[] param1ArrayOfbyte2) {
          if (param1ArrayOfbyte1.length != param1ArrayOfbyte2.length)
            return param1ArrayOfbyte1.length - param1ArrayOfbyte2.length; 
          for (int i = 0; i < param1ArrayOfbyte1.length; i++) {
            if (param1ArrayOfbyte1[i] != param1ArrayOfbyte2[i])
              return param1ArrayOfbyte1[i] - param1ArrayOfbyte2[i]; 
          } 
          return 0;
        }
      };
  }
  
  @Nullable
  public static Typeface buildTypeface(@NonNull Context paramContext, @Nullable CancellationSignal paramCancellationSignal, @NonNull FontInfo[] paramArrayOfFontInfo) {
    return TypefaceCompat.createFromFontInfo(paramContext, paramCancellationSignal, paramArrayOfFontInfo, 0);
  }
  
  private static List<byte[]> convertToByteArrayList(Signature[] paramArrayOfSignature) {
    ArrayList<byte[]> arrayList = new ArrayList();
    for (int i = 0; i < paramArrayOfSignature.length; i++)
      arrayList.add(paramArrayOfSignature[i].toByteArray()); 
    return (List<byte[]>)arrayList;
  }
  
  private static boolean equalsByteArrayList(List<byte[]> paramList1, List<byte[]> paramList2) {
    if (paramList1.size() != paramList2.size())
      return false; 
    for (int i = 0; i < paramList1.size(); i++) {
      if (!Arrays.equals(paramList1.get(i), paramList2.get(i)))
        return false; 
    } 
    return true;
  }
  
  @NonNull
  public static FontFamilyResult fetchFonts(@NonNull Context paramContext, @Nullable CancellationSignal paramCancellationSignal, @NonNull FontRequest paramFontRequest) {
    ProviderInfo providerInfo = getProvider(paramContext.getPackageManager(), paramFontRequest, paramContext.getResources());
    return (providerInfo == null) ? new FontFamilyResult(1, null) : new FontFamilyResult(0, getFontFromProvider(paramContext, paramFontRequest, providerInfo.authority, paramCancellationSignal));
  }
  
  private static List<List<byte[]>> getCertificates(FontRequest paramFontRequest, Resources paramResources) {
    return (paramFontRequest.getCertificates() != null) ? paramFontRequest.getCertificates() : FontResourcesParserCompat.readCerts(paramResources, paramFontRequest.getCertificatesArrayResId());
  }
  
  @NonNull
  @VisibleForTesting
  static FontInfo[] getFontFromProvider(Context paramContext, FontRequest paramFontRequest, String paramString, CancellationSignal paramCancellationSignal) {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #14
    //   9: new android/net/Uri$Builder
    //   12: dup
    //   13: invokespecial <init> : ()V
    //   16: ldc 'content'
    //   18: invokevirtual scheme : (Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   21: aload_2
    //   22: invokevirtual authority : (Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   25: invokevirtual build : ()Landroid/net/Uri;
    //   28: astore #16
    //   30: new android/net/Uri$Builder
    //   33: dup
    //   34: invokespecial <init> : ()V
    //   37: ldc 'content'
    //   39: invokevirtual scheme : (Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   42: aload_2
    //   43: invokevirtual authority : (Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   46: ldc 'file'
    //   48: invokevirtual appendPath : (Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   51: invokevirtual build : ()Landroid/net/Uri;
    //   54: astore #17
    //   56: aconst_null
    //   57: astore #15
    //   59: aload #15
    //   61: astore_2
    //   62: getstatic android/os/Build$VERSION.SDK_INT : I
    //   65: bipush #16
    //   67: if_icmple -> 155
    //   70: aload #15
    //   72: astore_2
    //   73: aload_0
    //   74: invokevirtual getContentResolver : ()Landroid/content/ContentResolver;
    //   77: astore_0
    //   78: aload #15
    //   80: astore_2
    //   81: aload_1
    //   82: invokevirtual getQuery : ()Ljava/lang/String;
    //   85: astore_1
    //   86: aload #15
    //   88: astore_2
    //   89: aload_0
    //   90: aload #16
    //   92: bipush #7
    //   94: anewarray java/lang/String
    //   97: dup
    //   98: iconst_0
    //   99: ldc '_id'
    //   101: aastore
    //   102: dup
    //   103: iconst_1
    //   104: ldc 'file_id'
    //   106: aastore
    //   107: dup
    //   108: iconst_2
    //   109: ldc 'font_ttc_index'
    //   111: aastore
    //   112: dup
    //   113: iconst_3
    //   114: ldc 'font_variation_settings'
    //   116: aastore
    //   117: dup
    //   118: iconst_4
    //   119: ldc 'font_weight'
    //   121: aastore
    //   122: dup
    //   123: iconst_5
    //   124: ldc_w 'font_italic'
    //   127: aastore
    //   128: dup
    //   129: bipush #6
    //   131: ldc_w 'result_code'
    //   134: aastore
    //   135: ldc_w 'query = ?'
    //   138: iconst_1
    //   139: anewarray java/lang/String
    //   142: dup
    //   143: iconst_0
    //   144: aload_1
    //   145: aastore
    //   146: aconst_null
    //   147: aload_3
    //   148: invokevirtual query : (Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/database/Cursor;
    //   151: astore_0
    //   152: goto -> 551
    //   155: aload #15
    //   157: astore_2
    //   158: aload_0
    //   159: invokevirtual getContentResolver : ()Landroid/content/ContentResolver;
    //   162: astore_0
    //   163: aload #15
    //   165: astore_2
    //   166: aload_1
    //   167: invokevirtual getQuery : ()Ljava/lang/String;
    //   170: astore_1
    //   171: aload #15
    //   173: astore_2
    //   174: aload_0
    //   175: aload #16
    //   177: bipush #7
    //   179: anewarray java/lang/String
    //   182: dup
    //   183: iconst_0
    //   184: ldc '_id'
    //   186: aastore
    //   187: dup
    //   188: iconst_1
    //   189: ldc 'file_id'
    //   191: aastore
    //   192: dup
    //   193: iconst_2
    //   194: ldc 'font_ttc_index'
    //   196: aastore
    //   197: dup
    //   198: iconst_3
    //   199: ldc 'font_variation_settings'
    //   201: aastore
    //   202: dup
    //   203: iconst_4
    //   204: ldc 'font_weight'
    //   206: aastore
    //   207: dup
    //   208: iconst_5
    //   209: ldc_w 'font_italic'
    //   212: aastore
    //   213: dup
    //   214: bipush #6
    //   216: ldc_w 'result_code'
    //   219: aastore
    //   220: ldc_w 'query = ?'
    //   223: iconst_1
    //   224: anewarray java/lang/String
    //   227: dup
    //   228: iconst_0
    //   229: aload_1
    //   230: aastore
    //   231: aconst_null
    //   232: invokevirtual query : (Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   235: astore_0
    //   236: goto -> 551
    //   239: aload #14
    //   241: astore_1
    //   242: aload_0
    //   243: ifnull -> 516
    //   246: aload #14
    //   248: astore_1
    //   249: aload_0
    //   250: astore_2
    //   251: aload_0
    //   252: invokeinterface getCount : ()I
    //   257: ifle -> 516
    //   260: aload_0
    //   261: astore_2
    //   262: aload_0
    //   263: ldc_w 'result_code'
    //   266: invokeinterface getColumnIndex : (Ljava/lang/String;)I
    //   271: istore #7
    //   273: aload_0
    //   274: astore_2
    //   275: new java/util/ArrayList
    //   278: dup
    //   279: invokespecial <init> : ()V
    //   282: astore_3
    //   283: aload_0
    //   284: astore_2
    //   285: aload_0
    //   286: ldc '_id'
    //   288: invokeinterface getColumnIndex : (Ljava/lang/String;)I
    //   293: istore #8
    //   295: aload_0
    //   296: astore_2
    //   297: aload_0
    //   298: ldc 'file_id'
    //   300: invokeinterface getColumnIndex : (Ljava/lang/String;)I
    //   305: istore #9
    //   307: aload_0
    //   308: astore_2
    //   309: aload_0
    //   310: ldc 'font_ttc_index'
    //   312: invokeinterface getColumnIndex : (Ljava/lang/String;)I
    //   317: istore #10
    //   319: aload_0
    //   320: astore_2
    //   321: aload_0
    //   322: ldc 'font_weight'
    //   324: invokeinterface getColumnIndex : (Ljava/lang/String;)I
    //   329: istore #11
    //   331: aload_0
    //   332: astore_2
    //   333: aload_0
    //   334: ldc_w 'font_italic'
    //   337: invokeinterface getColumnIndex : (Ljava/lang/String;)I
    //   342: istore #12
    //   344: aload_0
    //   345: astore_2
    //   346: aload_0
    //   347: invokeinterface moveToNext : ()Z
    //   352: ifeq -> 514
    //   355: iload #7
    //   357: iconst_m1
    //   358: if_icmpeq -> 554
    //   361: aload_0
    //   362: astore_2
    //   363: aload_0
    //   364: iload #7
    //   366: invokeinterface getInt : (I)I
    //   371: istore #4
    //   373: goto -> 376
    //   376: iload #10
    //   378: iconst_m1
    //   379: if_icmpeq -> 560
    //   382: aload_0
    //   383: astore_2
    //   384: aload_0
    //   385: iload #10
    //   387: invokeinterface getInt : (I)I
    //   392: istore #5
    //   394: goto -> 397
    //   397: iload #9
    //   399: iconst_m1
    //   400: if_icmpne -> 422
    //   403: aload_0
    //   404: astore_2
    //   405: aload #16
    //   407: aload_0
    //   408: iload #8
    //   410: invokeinterface getLong : (I)J
    //   415: invokestatic withAppendedId : (Landroid/net/Uri;J)Landroid/net/Uri;
    //   418: astore_1
    //   419: goto -> 566
    //   422: aload_0
    //   423: astore_2
    //   424: aload #17
    //   426: aload_0
    //   427: iload #9
    //   429: invokeinterface getLong : (I)J
    //   434: invokestatic withAppendedId : (Landroid/net/Uri;J)Landroid/net/Uri;
    //   437: astore_1
    //   438: goto -> 566
    //   441: iload #11
    //   443: iconst_m1
    //   444: if_icmpeq -> 569
    //   447: aload_0
    //   448: astore_2
    //   449: aload_0
    //   450: iload #11
    //   452: invokeinterface getInt : (I)I
    //   457: istore #6
    //   459: goto -> 462
    //   462: iload #12
    //   464: iconst_m1
    //   465: if_icmpeq -> 577
    //   468: aload_0
    //   469: astore_2
    //   470: aload_0
    //   471: iload #12
    //   473: invokeinterface getInt : (I)I
    //   478: iconst_1
    //   479: if_icmpne -> 577
    //   482: iconst_1
    //   483: istore #13
    //   485: goto -> 488
    //   488: aload_0
    //   489: astore_2
    //   490: aload_3
    //   491: new android/support/v4/provider/FontsContractCompat$FontInfo
    //   494: dup
    //   495: aload_1
    //   496: iload #5
    //   498: iload #6
    //   500: iload #13
    //   502: iload #4
    //   504: invokespecial <init> : (Landroid/net/Uri;IIZI)V
    //   507: invokevirtual add : (Ljava/lang/Object;)Z
    //   510: pop
    //   511: goto -> 344
    //   514: aload_3
    //   515: astore_1
    //   516: aload_0
    //   517: ifnull -> 526
    //   520: aload_0
    //   521: invokeinterface close : ()V
    //   526: aload_1
    //   527: iconst_0
    //   528: anewarray android/support/v4/provider/FontsContractCompat$FontInfo
    //   531: invokevirtual toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
    //   534: checkcast [Landroid/support/v4/provider/FontsContractCompat$FontInfo;
    //   537: areturn
    //   538: astore_0
    //   539: aload_2
    //   540: ifnull -> 549
    //   543: aload_2
    //   544: invokeinterface close : ()V
    //   549: aload_0
    //   550: athrow
    //   551: goto -> 239
    //   554: iconst_0
    //   555: istore #4
    //   557: goto -> 376
    //   560: iconst_0
    //   561: istore #5
    //   563: goto -> 397
    //   566: goto -> 441
    //   569: sipush #400
    //   572: istore #6
    //   574: goto -> 462
    //   577: iconst_0
    //   578: istore #13
    //   580: goto -> 488
    // Exception table:
    //   from	to	target	type
    //   62	70	538	finally
    //   73	78	538	finally
    //   81	86	538	finally
    //   89	152	538	finally
    //   158	163	538	finally
    //   166	171	538	finally
    //   174	236	538	finally
    //   251	260	538	finally
    //   262	273	538	finally
    //   275	283	538	finally
    //   285	295	538	finally
    //   297	307	538	finally
    //   309	319	538	finally
    //   321	331	538	finally
    //   333	344	538	finally
    //   346	355	538	finally
    //   363	373	538	finally
    //   384	394	538	finally
    //   405	419	538	finally
    //   424	438	538	finally
    //   449	459	538	finally
    //   470	482	538	finally
    //   490	511	538	finally
  }
  
  @NonNull
  static TypefaceResult getFontInternal(Context paramContext, FontRequest paramFontRequest, int paramInt) {
    try {
      FontFamilyResult fontFamilyResult = fetchFonts(paramContext, null, paramFontRequest);
      int i = fontFamilyResult.getStatusCode();
      byte b = -3;
      if (i == 0) {
        Typeface typeface = TypefaceCompat.createFromFontInfo(paramContext, null, fontFamilyResult.getFonts(), paramInt);
        if (typeface != null)
          b = 0; 
        return new TypefaceResult(typeface, b);
      } 
      if (fontFamilyResult.getStatusCode() == 1)
        b = -2; 
      return new TypefaceResult(null, b);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      return new TypefaceResult(null, -1);
    } 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static Typeface getFontSync(Context paramContext, final FontRequest request, @Nullable final ResourcesCompat.FontCallback fontCallback, @Nullable final Handler handler, boolean paramBoolean, int paramInt1, final int style) {
    final TypefaceResult context;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(request.getIdentifier());
    stringBuilder.append("-");
    stringBuilder.append(style);
    final String id = stringBuilder.toString();
    Typeface typeface = (Typeface)sTypefaceCache.get(str);
    if (typeface != null) {
      if (fontCallback != null)
        fontCallback.onFontRetrieved(typeface); 
      return typeface;
    } 
    if (paramBoolean && paramInt1 == -1) {
      typefaceResult = getFontInternal(paramContext, request, style);
      if (fontCallback != null)
        if (typefaceResult.mResult == 0) {
          fontCallback.callbackSuccessAsync(typefaceResult.mTypeface, handler);
        } else {
          fontCallback.callbackFailAsync(typefaceResult.mResult, handler);
        }  
      return typefaceResult.mTypeface;
    } 
    Callable<TypefaceResult> callable = new Callable<TypefaceResult>() {
        public FontsContractCompat.TypefaceResult call() {
          FontsContractCompat.TypefaceResult typefaceResult = FontsContractCompat.getFontInternal(context, request, style);
          if (typefaceResult.mTypeface != null)
            FontsContractCompat.sTypefaceCache.put(id, typefaceResult.mTypeface); 
          return typefaceResult;
        }
      };
    if (paramBoolean)
      try {
        return ((TypefaceResult)sBackgroundThread.postAndWait((Callable)callable, paramInt1)).mTypeface;
      } catch (InterruptedException interruptedException) {
        return null;
      }  
    if (fontCallback == null) {
      typefaceResult = null;
    } else {
      null = new SelfDestructiveThread.ReplyCallback<TypefaceResult>() {
          public void onReply(FontsContractCompat.TypefaceResult param1TypefaceResult) {
            if (param1TypefaceResult == null) {
              fontCallback.callbackFailAsync(1, handler);
              return;
            } 
            if (param1TypefaceResult.mResult == 0) {
              fontCallback.callbackSuccessAsync(param1TypefaceResult.mTypeface, handler);
              return;
            } 
            fontCallback.callbackFailAsync(param1TypefaceResult.mResult, handler);
          }
        };
    } 
    synchronized (sLock) {
      if (sPendingReplies.containsKey(str)) {
        if (null != null)
          ((ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>>)sPendingReplies.get(str)).add(null); 
        return null;
      } 
      if (null != null) {
        ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>> arrayList = new ArrayList();
        arrayList.add(null);
        sPendingReplies.put(str, arrayList);
      } 
      sBackgroundThread.postAndReply(callable, new SelfDestructiveThread.ReplyCallback<TypefaceResult>() {
            public void onReply(FontsContractCompat.TypefaceResult param1TypefaceResult) {
              synchronized (FontsContractCompat.sLock) {
                ArrayList<SelfDestructiveThread.ReplyCallback<FontsContractCompat.TypefaceResult>> arrayList = (ArrayList)FontsContractCompat.sPendingReplies.get(id);
                if (arrayList == null)
                  return; 
                FontsContractCompat.sPendingReplies.remove(id);
                for (int i = 0; i < arrayList.size(); i++)
                  ((SelfDestructiveThread.ReplyCallback<FontsContractCompat.TypefaceResult>)arrayList.get(i)).onReply(param1TypefaceResult); 
                return;
              } 
            }
          });
      return null;
    } 
  }
  
  @Nullable
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  @VisibleForTesting
  public static ProviderInfo getProvider(@NonNull PackageManager paramPackageManager, @NonNull FontRequest paramFontRequest, @Nullable Resources paramResources) {
    StringBuilder stringBuilder;
    String str = paramFontRequest.getProviderAuthority();
    int i = 0;
    ProviderInfo providerInfo = paramPackageManager.resolveContentProvider(str, 0);
    if (providerInfo == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("No package found for authority: ");
      stringBuilder.append(str);
      throw new PackageManager.NameNotFoundException(stringBuilder.toString());
    } 
    if (!providerInfo.packageName.equals(paramFontRequest.getProviderPackage())) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Found content provider ");
      stringBuilder.append(str);
      stringBuilder.append(", but package was not ");
      stringBuilder.append(paramFontRequest.getProviderPackage());
      throw new PackageManager.NameNotFoundException(stringBuilder.toString());
    } 
    List<byte[]> list = convertToByteArrayList((stringBuilder.getPackageInfo(providerInfo.packageName, 64)).signatures);
    Collections.sort((List)list, (Comparator)sByteArrayComparator);
    List<List<byte[]>> list1 = getCertificates(paramFontRequest, paramResources);
    while (i < list1.size()) {
      ArrayList<byte> arrayList = new ArrayList(list1.get(i));
      Collections.sort(arrayList, (Comparator)sByteArrayComparator);
      if (equalsByteArrayList(list, (List)arrayList))
        return providerInfo; 
      i++;
    } 
    return null;
  }
  
  @RequiresApi(19)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static Map<Uri, ByteBuffer> prepareFontData(Context paramContext, FontInfo[] paramArrayOfFontInfo, CancellationSignal paramCancellationSignal) {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    int j = paramArrayOfFontInfo.length;
    for (int i = 0; i < j; i++) {
      FontInfo fontInfo = paramArrayOfFontInfo[i];
      if (fontInfo.getResultCode() == 0) {
        Uri uri = fontInfo.getUri();
        if (!hashMap.containsKey(uri))
          hashMap.put(uri, TypefaceCompatUtil.mmap(paramContext, paramCancellationSignal, uri)); 
      } 
    } 
    return (Map)Collections.unmodifiableMap(hashMap);
  }
  
  public static void requestFont(@NonNull final Context context, @NonNull final FontRequest request, @NonNull final FontRequestCallback callback, @NonNull Handler paramHandler) {
    paramHandler.post(new Runnable() {
          public void run() {
            try {
              FontsContractCompat.FontFamilyResult fontFamilyResult = FontsContractCompat.fetchFonts(context, null, request);
              if (fontFamilyResult.getStatusCode() != 0) {
                switch (fontFamilyResult.getStatusCode()) {
                  default:
                    callerThreadHandler.post(new Runnable() {
                          public void run() {
                            callback.onTypefaceRequestFailed(-3);
                          }
                        });
                    return;
                  case 2:
                    callerThreadHandler.post(new Runnable() {
                          public void run() {
                            callback.onTypefaceRequestFailed(-3);
                          }
                        });
                    return;
                  case 1:
                    break;
                } 
                callerThreadHandler.post(new Runnable() {
                      public void run() {
                        callback.onTypefaceRequestFailed(-2);
                      }
                    });
                return;
              } 
              FontsContractCompat.FontInfo[] arrayOfFontInfo = fontFamilyResult.getFonts();
              if (arrayOfFontInfo == null || arrayOfFontInfo.length == 0) {
                callerThreadHandler.post(new Runnable() {
                      public void run() {
                        callback.onTypefaceRequestFailed(1);
                      }
                    });
                return;
              } 
              int j = arrayOfFontInfo.length;
              for (final int resultCode = 0; i < j; i++) {
                FontsContractCompat.FontInfo fontInfo = arrayOfFontInfo[i];
                if (fontInfo.getResultCode() != 0) {
                  i = fontInfo.getResultCode();
                  if (i < 0) {
                    callerThreadHandler.post(new Runnable() {
                          public void run() {
                            callback.onTypefaceRequestFailed(-3);
                          }
                        });
                    return;
                  } 
                  callerThreadHandler.post(new Runnable() {
                        public void run() {
                          callback.onTypefaceRequestFailed(resultCode);
                        }
                      });
                  return;
                } 
              } 
              final Typeface typeface = FontsContractCompat.buildTypeface(context, null, arrayOfFontInfo);
              if (typeface == null) {
                callerThreadHandler.post(new Runnable() {
                      public void run() {
                        callback.onTypefaceRequestFailed(-3);
                      }
                    });
                return;
              } 
              callerThreadHandler.post(new Runnable() {
                    public void run() {
                      callback.onTypefaceRetrieved(typeface);
                    }
                  });
              return;
            } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
              callerThreadHandler.post(new Runnable() {
                    public void run() {
                      callback.onTypefaceRequestFailed(-1);
                    }
                  });
              return;
            } 
          }
        });
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static void resetCache() {
    sTypefaceCache.evictAll();
  }
  
  public static final class Columns implements BaseColumns {
    public static final String FILE_ID = "file_id";
    
    public static final String ITALIC = "font_italic";
    
    public static final String RESULT_CODE = "result_code";
    
    public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
    
    public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
    
    public static final int RESULT_CODE_MALFORMED_QUERY = 3;
    
    public static final int RESULT_CODE_OK = 0;
    
    public static final String TTC_INDEX = "font_ttc_index";
    
    public static final String VARIATION_SETTINGS = "font_variation_settings";
    
    public static final String WEIGHT = "font_weight";
  }
  
  public static class FontFamilyResult {
    public static final int STATUS_OK = 0;
    
    public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
    
    public static final int STATUS_WRONG_CERTIFICATES = 1;
    
    private final FontsContractCompat.FontInfo[] mFonts;
    
    private final int mStatusCode;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public FontFamilyResult(int param1Int, @Nullable FontsContractCompat.FontInfo[] param1ArrayOfFontInfo) {
      this.mStatusCode = param1Int;
      this.mFonts = param1ArrayOfFontInfo;
    }
    
    public FontsContractCompat.FontInfo[] getFonts() {
      return this.mFonts;
    }
    
    public int getStatusCode() {
      return this.mStatusCode;
    }
  }
  
  public static class FontInfo {
    private final boolean mItalic;
    
    private final int mResultCode;
    
    private final int mTtcIndex;
    
    private final Uri mUri;
    
    private final int mWeight;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public FontInfo(@NonNull Uri param1Uri, @IntRange(from = 0L) int param1Int1, @IntRange(from = 1L, to = 1000L) int param1Int2, boolean param1Boolean, int param1Int3) {
      this.mUri = (Uri)Preconditions.checkNotNull(param1Uri);
      this.mTtcIndex = param1Int1;
      this.mWeight = param1Int2;
      this.mItalic = param1Boolean;
      this.mResultCode = param1Int3;
    }
    
    public int getResultCode() {
      return this.mResultCode;
    }
    
    @IntRange(from = 0L)
    public int getTtcIndex() {
      return this.mTtcIndex;
    }
    
    @NonNull
    public Uri getUri() {
      return this.mUri;
    }
    
    @IntRange(from = 1L, to = 1000L)
    public int getWeight() {
      return this.mWeight;
    }
    
    public boolean isItalic() {
      return this.mItalic;
    }
  }
  
  public static class FontRequestCallback {
    public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
    
    public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
    
    public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
    
    public static final int FAIL_REASON_MALFORMED_QUERY = 3;
    
    public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
    
    public static final int FAIL_REASON_SECURITY_VIOLATION = -4;
    
    public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final int RESULT_OK = 0;
    
    public void onTypefaceRequestFailed(int param1Int) {}
    
    public void onTypefaceRetrieved(Typeface param1Typeface) {}
    
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static @interface FontRequestFailReason {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface FontRequestFailReason {}
  
  private static final class TypefaceResult {
    final int mResult;
    
    final Typeface mTypeface;
    
    TypefaceResult(@Nullable Typeface param1Typeface, int param1Int) {
      this.mTypeface = param1Typeface;
      this.mResult = param1Int;
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\support\v4\provider\FontsContractCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */