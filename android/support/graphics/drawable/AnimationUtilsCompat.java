package android.support.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.support.annotation.RestrictTo;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class AnimationUtilsCompat {
  private static Interpolator createInterpolatorFromXml(Context paramContext, Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser) {
    PathInterpolatorCompat pathInterpolatorCompat;
    int i = paramXmlPullParser.getDepth();
    paramResources = null;
    while (true) {
      int j = paramXmlPullParser.next();
      if ((j != 3 || paramXmlPullParser.getDepth() > i) && j != 1) {
        LinearInterpolator linearInterpolator;
        AccelerateInterpolator accelerateInterpolator;
        AccelerateDecelerateInterpolator accelerateDecelerateInterpolator;
        CycleInterpolator cycleInterpolator;
        AnticipateInterpolator anticipateInterpolator;
        OvershootInterpolator overshootInterpolator;
        BounceInterpolator bounceInterpolator;
        if (j != 2)
          continue; 
        AttributeSet attributeSet = Xml.asAttributeSet(paramXmlPullParser);
        String str = paramXmlPullParser.getName();
        if (str.equals("linearInterpolator")) {
          linearInterpolator = new LinearInterpolator();
          continue;
        } 
        if (str.equals("accelerateInterpolator")) {
          accelerateInterpolator = new AccelerateInterpolator(paramContext, (AttributeSet)linearInterpolator);
          continue;
        } 
        if (str.equals("decelerateInterpolator")) {
          DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(paramContext, (AttributeSet)accelerateInterpolator);
          continue;
        } 
        if (str.equals("accelerateDecelerateInterpolator")) {
          accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
          continue;
        } 
        if (str.equals("cycleInterpolator")) {
          cycleInterpolator = new CycleInterpolator(paramContext, (AttributeSet)accelerateDecelerateInterpolator);
          continue;
        } 
        if (str.equals("anticipateInterpolator")) {
          anticipateInterpolator = new AnticipateInterpolator(paramContext, (AttributeSet)cycleInterpolator);
          continue;
        } 
        if (str.equals("overshootInterpolator")) {
          overshootInterpolator = new OvershootInterpolator(paramContext, (AttributeSet)anticipateInterpolator);
          continue;
        } 
        if (str.equals("anticipateOvershootInterpolator")) {
          AnticipateOvershootInterpolator anticipateOvershootInterpolator = new AnticipateOvershootInterpolator(paramContext, (AttributeSet)overshootInterpolator);
          continue;
        } 
        if (str.equals("bounceInterpolator")) {
          bounceInterpolator = new BounceInterpolator();
          continue;
        } 
        if (str.equals("pathInterpolator")) {
          pathInterpolatorCompat = new PathInterpolatorCompat(paramContext, (AttributeSet)bounceInterpolator, paramXmlPullParser);
          continue;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown interpolator name: ");
        stringBuilder.append(paramXmlPullParser.getName());
        throw new RuntimeException(stringBuilder.toString());
      } 
      break;
    } 
    return pathInterpolatorCompat;
  }
  
  public static Interpolator loadInterpolator(Context paramContext, int paramInt) {
    IOException iOException;
    if (Build.VERSION.SDK_INT >= 21)
      return AnimationUtils.loadInterpolator(paramContext, paramInt); 
    Context context3 = null;
    Context context1 = null;
    Context context2 = null;
    if (paramInt == 17563663) {
      try {
        return (Interpolator)new FastOutLinearInInterpolator();
      } catch (XmlPullParserException xmlPullParserException) {
      
      } catch (IOException null) {
        paramContext = context2;
        context1 = paramContext;
        StringBuilder stringBuilder1 = new StringBuilder();
        context1 = paramContext;
        stringBuilder1.append("Can't load animation resource ID #0x");
        context1 = paramContext;
        stringBuilder1.append(Integer.toHexString(paramInt));
        context1 = paramContext;
        Resources.NotFoundException notFoundException1 = new Resources.NotFoundException(stringBuilder1.toString());
        context1 = paramContext;
        notFoundException1.initCause(iOException);
        context1 = paramContext;
        throw notFoundException1;
      } finally {
        if (context1 != null)
          context1.close(); 
      } 
    } else {
      XmlPullParserException xmlPullParserException;
      if (paramInt == 17563661)
        return (Interpolator)new FastOutSlowInInterpolator(); 
      if (paramInt == 17563662)
        return (Interpolator)new LinearOutSlowInInterpolator(); 
      XmlResourceParser xmlResourceParser = paramContext.getResources().getAnimation(paramInt);
      try {
        return createInterpolatorFromXml(paramContext, paramContext.getResources(), paramContext.getTheme(), (XmlPullParser)xmlResourceParser);
      } catch (XmlPullParserException xmlPullParserException1) {
        XmlResourceParser xmlResourceParser1 = xmlResourceParser;
      } catch (IOException iOException1) {
        XmlPullParserException xmlPullParserException1 = xmlPullParserException;
      } finally {
        IOException iOException1;
        paramContext = null;
      } 
    } 
    context1 = paramContext;
    StringBuilder stringBuilder = new StringBuilder();
    context1 = paramContext;
    stringBuilder.append("Can't load animation resource ID #0x");
    context1 = paramContext;
    stringBuilder.append(Integer.toHexString(paramInt));
    context1 = paramContext;
    Resources.NotFoundException notFoundException = new Resources.NotFoundException(stringBuilder.toString());
    context1 = paramContext;
    notFoundException.initCause(iOException);
    context1 = paramContext;
    throw notFoundException;
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\support\graphics\drawable\AnimationUtilsCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */