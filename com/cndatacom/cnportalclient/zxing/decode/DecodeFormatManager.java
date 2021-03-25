package com.cndatacom.cnportalclient.zxing.decode;

import android.content.Intent;
import android.net.Uri;
import com.a.a.a;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public final class DecodeFormatManager {
  public static final Set<a> PRODUCT_FORMATS;
  
  static final Set<a> a;
  
  static final Set<a> b;
  
  static final Set<a> c;
  
  static final Set<a> d;
  
  static final Set<a> e;
  
  private static final Pattern f = Pattern.compile(",");
  
  private static final Set<a> g;
  
  private static final Map<String, Set<a>> h;
  
  static {
    b = EnumSet.of(a.l);
    c = EnumSet.of(a.f);
    d = EnumSet.of(a.a);
    e = EnumSet.of(a.k);
    PRODUCT_FORMATS = EnumSet.of(a.o, new a[] { a.p, a.h, a.g, a.m, a.n });
    a = EnumSet.of(a.c, a.d, a.e, a.i, a.b);
    g = EnumSet.copyOf(PRODUCT_FORMATS);
    g.addAll(a);
    h = new HashMap<String, Set<a>>();
    h.put("ONE_D_MODE", g);
    h.put("PRODUCT_MODE", PRODUCT_FORMATS);
    h.put("QR_CODE_MODE", b);
    h.put("DATA_MATRIX_MODE", c);
    h.put("AZTEC_MODE", d);
    h.put("PDF417_MODE", e);
  }
  
  private static Set<a> a(Iterable<String> paramIterable, String paramString) {
    if (paramIterable != null) {
      EnumSet<a> enumSet = EnumSet.noneOf(a.class);
      try {
        Iterator<String> iterator = paramIterable.iterator();
        while (iterator.hasNext())
          enumSet.add(a.valueOf(iterator.next())); 
        return enumSet;
      } catch (IllegalArgumentException illegalArgumentException) {}
    } 
    return (paramString != null) ? h.get(paramString) : null;
  }
  
  public static Set<a> parseDecodeFormats(Intent paramIntent) {
    String str = paramIntent.getStringExtra("SCAN_FORMATS");
    if (str != null) {
      List<String> list = Arrays.asList(f.split(str));
    } else {
      str = null;
    } 
    return a((Iterable<String>)str, paramIntent.getStringExtra("SCAN_MODE"));
  }
  
  public static Set<a> parseDecodeFormats(Uri paramUri) {
    List<CharSequence> list2 = paramUri.getQueryParameters("SCAN_FORMATS");
    List<CharSequence> list1 = list2;
    if (list2 != null) {
      list1 = list2;
      if (list2.size() == 1) {
        list1 = list2;
        if (list2.get(0) != null)
          list1 = Arrays.asList(f.split(list2.get(0))); 
      } 
    } 
    return a((Iterable)list1, paramUri.getQueryParameter("SCAN_MODE"));
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\zxing\decode\DecodeFormatManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */