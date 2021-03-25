package org.apache.cordova;

import android.net.Uri;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

public class Whitelist {
  public static final String TAG = "Whitelist";
  
  private ArrayList<URLPattern> whiteList = new ArrayList<URLPattern>();
  
  public void addWhiteListEntry(String paramString, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: getfield whiteList : Ljava/util/ArrayList;
    //   4: ifnull -> 196
    //   7: aload_1
    //   8: ldc '*'
    //   10: invokevirtual compareTo : (Ljava/lang/String;)I
    //   13: ifne -> 29
    //   16: ldc 'Whitelist'
    //   18: ldc 'Unlimited access to network resources'
    //   20: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)V
    //   23: aload_0
    //   24: aconst_null
    //   25: putfield whiteList : Ljava/util/ArrayList;
    //   28: return
    //   29: ldc '^((\*|[A-Za-z-]+):(//)?)?(\*|((\*\.)?[^*/:]+))?(:(\d+))?(/.*)?'
    //   31: invokestatic compile : (Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   34: aload_1
    //   35: invokevirtual matcher : (Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   38: astore #6
    //   40: aload #6
    //   42: invokevirtual matches : ()Z
    //   45: ifeq -> 196
    //   48: aload #6
    //   50: iconst_2
    //   51: invokevirtual group : (I)Ljava/lang/String;
    //   54: astore #5
    //   56: aload #6
    //   58: iconst_4
    //   59: invokevirtual group : (I)Ljava/lang/String;
    //   62: astore #4
    //   64: ldc 'file'
    //   66: aload #5
    //   68: invokevirtual equals : (Ljava/lang/Object;)Z
    //   71: ifne -> 201
    //   74: aload #4
    //   76: astore_3
    //   77: ldc 'content'
    //   79: aload #5
    //   81: invokevirtual equals : (Ljava/lang/Object;)Z
    //   84: ifeq -> 90
    //   87: goto -> 201
    //   90: aload #6
    //   92: bipush #8
    //   94: invokevirtual group : (I)Ljava/lang/String;
    //   97: astore #4
    //   99: aload #6
    //   101: bipush #9
    //   103: invokevirtual group : (I)Ljava/lang/String;
    //   106: astore #6
    //   108: aload #5
    //   110: ifnonnull -> 158
    //   113: aload_0
    //   114: getfield whiteList : Ljava/util/ArrayList;
    //   117: new org/apache/cordova/Whitelist$URLPattern
    //   120: dup
    //   121: ldc 'http'
    //   123: aload_3
    //   124: aload #4
    //   126: aload #6
    //   128: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   131: invokevirtual add : (Ljava/lang/Object;)Z
    //   134: pop
    //   135: aload_0
    //   136: getfield whiteList : Ljava/util/ArrayList;
    //   139: new org/apache/cordova/Whitelist$URLPattern
    //   142: dup
    //   143: ldc 'https'
    //   145: aload_3
    //   146: aload #4
    //   148: aload #6
    //   150: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   153: invokevirtual add : (Ljava/lang/Object;)Z
    //   156: pop
    //   157: return
    //   158: aload_0
    //   159: getfield whiteList : Ljava/util/ArrayList;
    //   162: new org/apache/cordova/Whitelist$URLPattern
    //   165: dup
    //   166: aload #5
    //   168: aload_3
    //   169: aload #4
    //   171: aload #6
    //   173: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   176: invokevirtual add : (Ljava/lang/Object;)Z
    //   179: pop
    //   180: return
    //   181: ldc 'Whitelist'
    //   183: ldc 'Failed to add origin %s'
    //   185: iconst_1
    //   186: anewarray java/lang/Object
    //   189: dup
    //   190: iconst_0
    //   191: aload_1
    //   192: aastore
    //   193: invokestatic d : (Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   196: return
    //   197: astore_3
    //   198: goto -> 181
    //   201: aload #4
    //   203: astore_3
    //   204: aload #4
    //   206: ifnonnull -> 90
    //   209: ldc '*'
    //   211: astore_3
    //   212: goto -> 90
    // Exception table:
    //   from	to	target	type
    //   7	28	197	java/lang/Exception
    //   29	74	197	java/lang/Exception
    //   77	87	197	java/lang/Exception
    //   90	108	197	java/lang/Exception
    //   113	157	197	java/lang/Exception
    //   158	180	197	java/lang/Exception
  }
  
  public boolean isUrlWhiteListed(String paramString) {
    if (this.whiteList == null)
      return true; 
    Uri uri = Uri.parse(paramString);
    Iterator<URLPattern> iterator = this.whiteList.iterator();
    while (iterator.hasNext()) {
      if (((URLPattern)iterator.next()).matches(uri))
        return true; 
    } 
    return false;
  }
  
  private static class URLPattern {
    public Pattern host;
    
    public Pattern path;
    
    public Integer port;
    
    public Pattern scheme;
    
    public URLPattern(String param1String1, String param1String2, String param1String3, String param1String4) {
      // Byte code:
      //   0: aload_0
      //   1: invokespecial <init> : ()V
      //   4: aload_1
      //   5: ifnull -> 37
      //   8: ldc '*'
      //   10: aload_1
      //   11: invokevirtual equals : (Ljava/lang/Object;)Z
      //   14: ifeq -> 20
      //   17: goto -> 37
      //   20: aload_0
      //   21: aload_0
      //   22: aload_1
      //   23: iconst_0
      //   24: invokespecial regexFromPattern : (Ljava/lang/String;Z)Ljava/lang/String;
      //   27: iconst_2
      //   28: invokestatic compile : (Ljava/lang/String;I)Ljava/util/regex/Pattern;
      //   31: putfield scheme : Ljava/util/regex/Pattern;
      //   34: goto -> 42
      //   37: aload_0
      //   38: aconst_null
      //   39: putfield scheme : Ljava/util/regex/Pattern;
      //   42: ldc '*'
      //   44: aload_2
      //   45: invokevirtual equals : (Ljava/lang/Object;)Z
      //   48: ifeq -> 59
      //   51: aload_0
      //   52: aconst_null
      //   53: putfield host : Ljava/util/regex/Pattern;
      //   56: goto -> 127
      //   59: aload_2
      //   60: ldc '*.'
      //   62: invokevirtual startsWith : (Ljava/lang/String;)Z
      //   65: ifeq -> 113
      //   68: new java/lang/StringBuilder
      //   71: dup
      //   72: invokespecial <init> : ()V
      //   75: astore_1
      //   76: aload_1
      //   77: ldc '([a-z0-9.-]*\.)?'
      //   79: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   82: pop
      //   83: aload_1
      //   84: aload_0
      //   85: aload_2
      //   86: iconst_2
      //   87: invokevirtual substring : (I)Ljava/lang/String;
      //   90: iconst_0
      //   91: invokespecial regexFromPattern : (Ljava/lang/String;Z)Ljava/lang/String;
      //   94: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   97: pop
      //   98: aload_0
      //   99: aload_1
      //   100: invokevirtual toString : ()Ljava/lang/String;
      //   103: iconst_2
      //   104: invokestatic compile : (Ljava/lang/String;I)Ljava/util/regex/Pattern;
      //   107: putfield host : Ljava/util/regex/Pattern;
      //   110: goto -> 127
      //   113: aload_0
      //   114: aload_0
      //   115: aload_2
      //   116: iconst_0
      //   117: invokespecial regexFromPattern : (Ljava/lang/String;Z)Ljava/lang/String;
      //   120: iconst_2
      //   121: invokestatic compile : (Ljava/lang/String;I)Ljava/util/regex/Pattern;
      //   124: putfield host : Ljava/util/regex/Pattern;
      //   127: aload_3
      //   128: ifnull -> 159
      //   131: ldc '*'
      //   133: aload_3
      //   134: invokevirtual equals : (Ljava/lang/Object;)Z
      //   137: ifeq -> 143
      //   140: goto -> 159
      //   143: aload_0
      //   144: aload_3
      //   145: bipush #10
      //   147: invokestatic parseInt : (Ljava/lang/String;I)I
      //   150: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   153: putfield port : Ljava/lang/Integer;
      //   156: goto -> 164
      //   159: aload_0
      //   160: aconst_null
      //   161: putfield port : Ljava/lang/Integer;
      //   164: aload #4
      //   166: ifnull -> 197
      //   169: ldc '/*'
      //   171: aload #4
      //   173: invokevirtual equals : (Ljava/lang/Object;)Z
      //   176: ifeq -> 182
      //   179: goto -> 197
      //   182: aload_0
      //   183: aload_0
      //   184: aload #4
      //   186: iconst_1
      //   187: invokespecial regexFromPattern : (Ljava/lang/String;Z)Ljava/lang/String;
      //   190: invokestatic compile : (Ljava/lang/String;)Ljava/util/regex/Pattern;
      //   193: putfield path : Ljava/util/regex/Pattern;
      //   196: return
      //   197: aload_0
      //   198: aconst_null
      //   199: putfield path : Ljava/util/regex/Pattern;
      //   202: return
      //   203: new java/net/MalformedURLException
      //   206: dup
      //   207: ldc 'Port must be a number'
      //   209: invokespecial <init> : (Ljava/lang/String;)V
      //   212: athrow
      //   213: astore_1
      //   214: goto -> 203
      // Exception table:
      //   from	to	target	type
      //   8	17	213	java/lang/NumberFormatException
      //   20	34	213	java/lang/NumberFormatException
      //   37	42	213	java/lang/NumberFormatException
      //   42	56	213	java/lang/NumberFormatException
      //   59	110	213	java/lang/NumberFormatException
      //   113	127	213	java/lang/NumberFormatException
      //   131	140	213	java/lang/NumberFormatException
      //   143	156	213	java/lang/NumberFormatException
      //   159	164	213	java/lang/NumberFormatException
      //   169	179	213	java/lang/NumberFormatException
      //   182	196	213	java/lang/NumberFormatException
      //   197	202	213	java/lang/NumberFormatException
    }
    
    private String regexFromPattern(String param1String, boolean param1Boolean) {
      StringBuilder stringBuilder = new StringBuilder();
      int i;
      for (i = 0; i < param1String.length(); i++) {
        char c = param1String.charAt(i);
        if (c == '*' && param1Boolean) {
          stringBuilder.append(".");
        } else if ("\\.[]{}()^$?+|".indexOf(c) > -1) {
          stringBuilder.append('\\');
        } 
        stringBuilder.append(c);
      } 
      return stringBuilder.toString();
    }
    
    public boolean matches(Uri param1Uri) {
      // Byte code:
      //   0: iconst_0
      //   1: istore_3
      //   2: aload_0
      //   3: getfield scheme : Ljava/util/regex/Pattern;
      //   6: ifnull -> 28
      //   9: iload_3
      //   10: istore_2
      //   11: aload_0
      //   12: getfield scheme : Ljava/util/regex/Pattern;
      //   15: aload_1
      //   16: invokevirtual getScheme : ()Ljava/lang/String;
      //   19: invokevirtual matcher : (Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
      //   22: invokevirtual matches : ()Z
      //   25: ifeq -> 112
      //   28: aload_0
      //   29: getfield host : Ljava/util/regex/Pattern;
      //   32: ifnull -> 54
      //   35: iload_3
      //   36: istore_2
      //   37: aload_0
      //   38: getfield host : Ljava/util/regex/Pattern;
      //   41: aload_1
      //   42: invokevirtual getHost : ()Ljava/lang/String;
      //   45: invokevirtual matcher : (Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
      //   48: invokevirtual matches : ()Z
      //   51: ifeq -> 112
      //   54: aload_0
      //   55: getfield port : Ljava/lang/Integer;
      //   58: ifnull -> 80
      //   61: iload_3
      //   62: istore_2
      //   63: aload_0
      //   64: getfield port : Ljava/lang/Integer;
      //   67: aload_1
      //   68: invokevirtual getPort : ()I
      //   71: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   74: invokevirtual equals : (Ljava/lang/Object;)Z
      //   77: ifeq -> 112
      //   80: aload_0
      //   81: getfield path : Ljava/util/regex/Pattern;
      //   84: ifnull -> 110
      //   87: aload_0
      //   88: getfield path : Ljava/util/regex/Pattern;
      //   91: aload_1
      //   92: invokevirtual getPath : ()Ljava/lang/String;
      //   95: invokevirtual matcher : (Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
      //   98: invokevirtual matches : ()Z
      //   101: istore #4
      //   103: iload_3
      //   104: istore_2
      //   105: iload #4
      //   107: ifeq -> 112
      //   110: iconst_1
      //   111: istore_2
      //   112: iload_2
      //   113: ireturn
      //   114: astore_1
      //   115: ldc 'Whitelist'
      //   117: aload_1
      //   118: invokevirtual toString : ()Ljava/lang/String;
      //   121: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)V
      //   124: iconst_0
      //   125: ireturn
      // Exception table:
      //   from	to	target	type
      //   2	9	114	java/lang/Exception
      //   11	28	114	java/lang/Exception
      //   28	35	114	java/lang/Exception
      //   37	54	114	java/lang/Exception
      //   54	61	114	java/lang/Exception
      //   63	80	114	java/lang/Exception
      //   80	103	114	java/lang/Exception
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\Whitelist.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */