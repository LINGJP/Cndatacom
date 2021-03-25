package android.support.v4.graphics;

import android.graphics.Path;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class PathParser {
  private static final String LOGTAG = "PathParser";
  
  private static void addNode(ArrayList<PathDataNode> paramArrayList, char paramChar, float[] paramArrayOffloat) {
    paramArrayList.add(new PathDataNode(paramChar, paramArrayOffloat));
  }
  
  public static boolean canMorph(PathDataNode[] paramArrayOfPathDataNode1, PathDataNode[] paramArrayOfPathDataNode2) {
    if (paramArrayOfPathDataNode1 != null) {
      if (paramArrayOfPathDataNode2 == null)
        return false; 
      if (paramArrayOfPathDataNode1.length != paramArrayOfPathDataNode2.length)
        return false; 
      int i = 0;
      while (i < paramArrayOfPathDataNode1.length) {
        if ((paramArrayOfPathDataNode1[i]).mType == (paramArrayOfPathDataNode2[i]).mType) {
          if ((paramArrayOfPathDataNode1[i]).mParams.length != (paramArrayOfPathDataNode2[i]).mParams.length)
            return false; 
          i++;
          continue;
        } 
        return false;
      } 
      return true;
    } 
    return false;
  }
  
  static float[] copyOfRange(float[] paramArrayOffloat, int paramInt1, int paramInt2) {
    if (paramInt1 > paramInt2)
      throw new IllegalArgumentException(); 
    int i = paramArrayOffloat.length;
    if (paramInt1 < 0 || paramInt1 > i)
      throw new ArrayIndexOutOfBoundsException(); 
    paramInt2 -= paramInt1;
    i = Math.min(paramInt2, i - paramInt1);
    float[] arrayOfFloat = new float[paramInt2];
    System.arraycopy(paramArrayOffloat, paramInt1, arrayOfFloat, 0, i);
    return arrayOfFloat;
  }
  
  public static PathDataNode[] createNodesFromPathData(String paramString) {
    if (paramString == null)
      return null; 
    ArrayList<PathDataNode> arrayList = new ArrayList();
    int j = 1;
    int i = 0;
    while (j < paramString.length()) {
      j = nextStart(paramString, j);
      String str = paramString.substring(i, j).trim();
      if (str.length() > 0) {
        float[] arrayOfFloat = getFloats(str);
        addNode(arrayList, str.charAt(0), arrayOfFloat);
      } 
      i = j;
      j++;
    } 
    if (j - i == 1 && i < paramString.length())
      addNode(arrayList, paramString.charAt(i), new float[0]); 
    return arrayList.<PathDataNode>toArray(new PathDataNode[arrayList.size()]);
  }
  
  public static Path createPathFromPathData(String paramString) {
    Path path = new Path();
    PathDataNode[] arrayOfPathDataNode = createNodesFromPathData(paramString);
    if (arrayOfPathDataNode != null)
      try {
        PathDataNode.nodesToPath(arrayOfPathDataNode, path);
        return path;
      } catch (RuntimeException runtimeException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error in parsing ");
        stringBuilder.append(paramString);
        throw new RuntimeException(stringBuilder.toString(), runtimeException);
      }  
    return null;
  }
  
  public static PathDataNode[] deepCopyNodes(PathDataNode[] paramArrayOfPathDataNode) {
    if (paramArrayOfPathDataNode == null)
      return null; 
    PathDataNode[] arrayOfPathDataNode = new PathDataNode[paramArrayOfPathDataNode.length];
    for (int i = 0; i < paramArrayOfPathDataNode.length; i++)
      arrayOfPathDataNode[i] = new PathDataNode(paramArrayOfPathDataNode[i]); 
    return arrayOfPathDataNode;
  }
  
  private static void extract(String paramString, int paramInt, ExtractFloatResult paramExtractFloatResult) {
    paramExtractFloatResult.mEndWithNegOrDot = false;
    int i = paramInt;
    boolean bool1 = false;
    boolean bool3 = false;
    boolean bool2 = false;
    while (i < paramString.length()) {
      char c = paramString.charAt(i);
      if (c != ' ') {
        if (c != 'E' && c != 'e') {
          switch (c) {
            default:
              bool1 = false;
              break;
            case '.':
              if (!bool3) {
                bool1 = false;
                bool3 = true;
                break;
              } 
              paramExtractFloatResult.mEndWithNegOrDot = true;
            case '-':
            
            case ',':
              bool1 = false;
              bool2 = true;
              break;
          } 
        } else {
          bool1 = true;
        } 
        if (bool2)
          break; 
        continue;
      } 
      i++;
    } 
    paramExtractFloatResult.mEndPosition = i;
  }
  
  private static float[] getFloats(String paramString) {
    if (paramString.charAt(0) == 'z' || paramString.charAt(0) == 'Z')
      return new float[0]; 
    try {
      float[] arrayOfFloat = new float[paramString.length()];
      ExtractFloatResult extractFloatResult = new ExtractFloatResult();
      int k = paramString.length();
      int i = 1;
      for (int j = 0;; j = m) {
        int m;
        int n;
        if (i < k) {
          extract(paramString, i, extractFloatResult);
          n = extractFloatResult.mEndPosition;
          m = j;
          if (i < n) {
            arrayOfFloat[j] = Float.parseFloat(paramString.substring(i, n));
            m = j + 1;
          } 
          if (extractFloatResult.mEndWithNegOrDot) {
            i = n;
            j = m;
            continue;
          } 
        } else {
          return copyOfRange(arrayOfFloat, 0, j);
        } 
        i = n + 1;
      } 
    } catch (NumberFormatException numberFormatException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("error in parsing \"");
      stringBuilder.append(paramString);
      stringBuilder.append("\"");
      throw new RuntimeException(stringBuilder.toString(), numberFormatException);
    } 
  }
  
  private static int nextStart(String paramString, int paramInt) {
    while (paramInt < paramString.length()) {
      char c = paramString.charAt(paramInt);
      if (((c - 65) * (c - 90) <= 0 || (c - 97) * (c - 122) <= 0) && c != 'e' && c != 'E')
        return paramInt; 
      paramInt++;
    } 
    return paramInt;
  }
  
  public static void updateNodes(PathDataNode[] paramArrayOfPathDataNode1, PathDataNode[] paramArrayOfPathDataNode2) {
    for (int i = 0; i < paramArrayOfPathDataNode2.length; i++) {
      (paramArrayOfPathDataNode1[i]).mType = (paramArrayOfPathDataNode2[i]).mType;
      for (int j = 0; j < (paramArrayOfPathDataNode2[i]).mParams.length; j++)
        (paramArrayOfPathDataNode1[i]).mParams[j] = (paramArrayOfPathDataNode2[i]).mParams[j]; 
    } 
  }
  
  private static class ExtractFloatResult {
    int mEndPosition;
    
    boolean mEndWithNegOrDot;
  }
  
  public static class PathDataNode {
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public float[] mParams;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public char mType;
    
    PathDataNode(char param1Char, float[] param1ArrayOffloat) {
      this.mType = param1Char;
      this.mParams = param1ArrayOffloat;
    }
    
    PathDataNode(PathDataNode param1PathDataNode) {
      this.mType = param1PathDataNode.mType;
      this.mParams = PathParser.copyOfRange(param1PathDataNode.mParams, 0, param1PathDataNode.mParams.length);
    }
    
    private static void addCommand(Path param1Path, float[] param1ArrayOffloat1, int param1Char1, int param1Char2, float[] param1ArrayOffloat2) {
      int i;
      byte b;
      float f7 = param1ArrayOffloat1[0];
      float f8 = param1ArrayOffloat1[1];
      float f9 = param1ArrayOffloat1[2];
      float f10 = param1ArrayOffloat1[3];
      float f6 = param1ArrayOffloat1[4];
      float f5 = param1ArrayOffloat1[5];
      float f1 = f7;
      float f2 = f8;
      float f3 = f9;
      float f4 = f10;
      switch (param1Char2) {
        default:
          f4 = f10;
          f3 = f9;
          f2 = f8;
          f1 = f7;
        case 'L':
        case 'M':
        case 'T':
        case 'l':
        case 'm':
        case 't':
          b = 2;
          break;
        case 'Z':
        case 'z':
          param1Path.close();
          param1Path.moveTo(f6, f5);
          f1 = f6;
          f3 = f1;
          f2 = f5;
          f4 = f2;
        case 'Q':
        case 'S':
        case 'q':
        case 's':
          b = 4;
          f1 = f7;
          f2 = f8;
          f3 = f9;
          f4 = f10;
          break;
        case 'H':
        case 'V':
        case 'h':
        case 'v':
          b = 1;
          f1 = f7;
          f2 = f8;
          f3 = f9;
          f4 = f10;
          break;
        case 'C':
        case 'c':
          b = 6;
          f1 = f7;
          f2 = f8;
          f3 = f9;
          f4 = f10;
          break;
        case 'A':
        case 'a':
          b = 7;
          f4 = f10;
          f3 = f9;
          f2 = f8;
          f1 = f7;
          break;
      } 
      f7 = f2;
      int k = 0;
      int j = param1Char1;
      param1Char1 = k;
      f2 = f1;
      f1 = f7;
      while (i < param1ArrayOffloat2.length) {
        int m;
        int n;
        int i1;
        int i2;
        boolean bool1;
        boolean bool2;
        f8 = 0.0F;
        f7 = 0.0F;
        switch (param1Char2) {
          case 'v':
            j = i + 0;
            param1Path.rLineTo(0.0F, param1ArrayOffloat2[j]);
            f1 += param1ArrayOffloat2[j];
            break;
          case 't':
            if (j == 113 || j == 116 || j == 81 || j == 84) {
              f3 = f2 - f3;
              f4 = f1 - f4;
            } else {
              f4 = 0.0F;
              f3 = f7;
            } 
            j = i + 0;
            f7 = param1ArrayOffloat2[j];
            k = i + 1;
            param1Path.rQuadTo(f3, f4, f7, param1ArrayOffloat2[k]);
            f7 = f2 + param1ArrayOffloat2[j];
            f8 = f1 + param1ArrayOffloat2[k];
            f4 += f1;
            f3 += f2;
            f1 = f8;
            f2 = f7;
            break;
          case 's':
            if (j == 99 || j == 115 || j == 67 || j == 83) {
              f4 = f1 - f4;
              f3 = f2 - f3;
            } else {
              f4 = 0.0F;
              f3 = f8;
            } 
            m = i + 0;
            f7 = param1ArrayOffloat2[m];
            n = i + 1;
            f8 = param1ArrayOffloat2[n];
            i1 = i + 2;
            f9 = param1ArrayOffloat2[i1];
            i2 = i + 3;
            param1Path.rCubicTo(f3, f4, f7, f8, f9, param1ArrayOffloat2[i2]);
            f3 = param1ArrayOffloat2[m] + f2;
            f4 = param1ArrayOffloat2[n] + f1;
            f2 += param1ArrayOffloat2[i1];
            f1 += param1ArrayOffloat2[i2];
            break;
          case 'q':
            m = i + 0;
            f3 = param1ArrayOffloat2[m];
            n = i + 1;
            f4 = param1ArrayOffloat2[n];
            i1 = i + 2;
            f7 = param1ArrayOffloat2[i1];
            i2 = i + 3;
            param1Path.rQuadTo(f3, f4, f7, param1ArrayOffloat2[i2]);
            f3 = param1ArrayOffloat2[m] + f2;
            f4 = param1ArrayOffloat2[n] + f1;
            f2 += param1ArrayOffloat2[i1];
            f1 += param1ArrayOffloat2[i2];
            break;
          case 'm':
            m = i + 0;
            f2 += param1ArrayOffloat2[m];
            n = i + 1;
            f1 += param1ArrayOffloat2[n];
            if (i > 0) {
              param1Path.rLineTo(param1ArrayOffloat2[m], param1ArrayOffloat2[n]);
              break;
            } 
            param1Path.rMoveTo(param1ArrayOffloat2[m], param1ArrayOffloat2[n]);
            f5 = f1;
            f6 = f2;
            break;
          case 'l':
            m = i + 0;
            f7 = param1ArrayOffloat2[m];
            n = i + 1;
            param1Path.rLineTo(f7, param1ArrayOffloat2[n]);
            f2 += param1ArrayOffloat2[m];
            f1 += param1ArrayOffloat2[n];
            break;
          case 'h':
            m = i + 0;
            param1Path.rLineTo(param1ArrayOffloat2[m], 0.0F);
            f2 += param1ArrayOffloat2[m];
            break;
          case 'c':
            f3 = param1ArrayOffloat2[i + 0];
            f4 = param1ArrayOffloat2[i + 1];
            m = i + 2;
            f7 = param1ArrayOffloat2[m];
            n = i + 3;
            f8 = param1ArrayOffloat2[n];
            i1 = i + 4;
            f9 = param1ArrayOffloat2[i1];
            i2 = i + 5;
            param1Path.rCubicTo(f3, f4, f7, f8, f9, param1ArrayOffloat2[i2]);
            f3 = param1ArrayOffloat2[m] + f2;
            f4 = param1ArrayOffloat2[n] + f1;
            f2 += param1ArrayOffloat2[i1];
            f1 += param1ArrayOffloat2[i2];
            break;
          case 'a':
            m = i + 5;
            f3 = param1ArrayOffloat2[m];
            n = i + 6;
            f4 = param1ArrayOffloat2[n];
            f7 = param1ArrayOffloat2[i + 0];
            f8 = param1ArrayOffloat2[i + 1];
            f9 = param1ArrayOffloat2[i + 2];
            if (param1ArrayOffloat2[i + 3] != 0.0F) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            if (param1ArrayOffloat2[i + 4] != 0.0F) {
              bool2 = true;
            } else {
              bool2 = false;
            } 
            drawArc(param1Path, f2, f1, f3 + f2, f4 + f1, f7, f8, f9, bool1, bool2);
            f2 += param1ArrayOffloat2[m];
            f1 += param1ArrayOffloat2[n];
            f4 = f1;
            f3 = f2;
            break;
          case 'V':
            m = i + 0;
            param1Path.lineTo(f2, param1ArrayOffloat2[m]);
            f1 = param1ArrayOffloat2[m];
            break;
          case 'T':
            f7 = f1;
            f8 = f2;
            n = i;
          case 'S':
            n = i;
            if (m == 99 || m == 115 || m == 67 || m == 83) {
              f1 = f1 * 2.0F - f4;
              f3 = f2 * 2.0F - f3;
              f2 = f1;
              f1 = f3;
            } else {
              f3 = f1;
              f1 = f2;
              f2 = f3;
            } 
            m = n + 0;
            f3 = param1ArrayOffloat2[m];
            i1 = n + 1;
            f4 = param1ArrayOffloat2[i1];
            i2 = n + 2;
            f7 = param1ArrayOffloat2[i2];
            n += 3;
            param1Path.cubicTo(f1, f2, f3, f4, f7, param1ArrayOffloat2[n]);
            f4 = param1ArrayOffloat2[m];
            f3 = param1ArrayOffloat2[i1];
            f2 = param1ArrayOffloat2[i2];
            f1 = param1ArrayOffloat2[n];
            f7 = f3;
            f3 = f4;
            f4 = f7;
            break;
          case 'Q':
            m = i;
            n = m + 0;
            f1 = param1ArrayOffloat2[n];
            i1 = m + 1;
            f2 = param1ArrayOffloat2[i1];
            i2 = m + 2;
            f3 = param1ArrayOffloat2[i2];
            m += 3;
            param1Path.quadTo(f1, f2, f3, param1ArrayOffloat2[m]);
            f4 = param1ArrayOffloat2[n];
            f3 = param1ArrayOffloat2[i1];
            f2 = param1ArrayOffloat2[i2];
            f1 = param1ArrayOffloat2[m];
            f7 = f3;
            f3 = f4;
            f4 = f7;
            break;
          case 'M':
            m = i;
            n = m + 0;
            f2 = param1ArrayOffloat2[n];
            i1 = m + 1;
            f1 = param1ArrayOffloat2[i1];
            if (m > 0) {
              param1Path.lineTo(param1ArrayOffloat2[n], param1ArrayOffloat2[i1]);
              break;
            } 
            param1Path.moveTo(param1ArrayOffloat2[n], param1ArrayOffloat2[i1]);
            f5 = f1;
            f6 = f2;
            break;
          case 'L':
            m = i;
            n = m + 0;
            f1 = param1ArrayOffloat2[n];
            param1Path.lineTo(f1, param1ArrayOffloat2[++m]);
            f2 = param1ArrayOffloat2[n];
            f1 = param1ArrayOffloat2[m];
            break;
          case 'H':
            m = i + 0;
            param1Path.lineTo(param1ArrayOffloat2[m], f1);
            f2 = param1ArrayOffloat2[m];
            break;
          case 'C':
            m = i;
            f1 = param1ArrayOffloat2[m + 0];
            f2 = param1ArrayOffloat2[m + 1];
            n = m + 2;
            f3 = param1ArrayOffloat2[n];
            i1 = m + 3;
            f4 = param1ArrayOffloat2[i1];
            i2 = m + 4;
            f7 = param1ArrayOffloat2[i2];
            m += 5;
            param1Path.cubicTo(f1, f2, f3, f4, f7, param1ArrayOffloat2[m]);
            f2 = param1ArrayOffloat2[i2];
            f1 = param1ArrayOffloat2[m];
            f3 = param1ArrayOffloat2[n];
            f4 = param1ArrayOffloat2[i1];
            break;
          case 'A':
            m = i;
            n = m + 5;
            f3 = param1ArrayOffloat2[n];
            i1 = m + 6;
            f4 = param1ArrayOffloat2[i1];
            f7 = param1ArrayOffloat2[m + 0];
            f8 = param1ArrayOffloat2[m + 1];
            f9 = param1ArrayOffloat2[m + 2];
            if (param1ArrayOffloat2[m + 3] != 0.0F) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            if (param1ArrayOffloat2[m + 4] != 0.0F) {
              bool2 = true;
            } else {
              bool2 = false;
            } 
            drawArc(param1Path, f2, f1, f3, f4, f7, f8, f9, bool1, bool2);
            f2 = param1ArrayOffloat2[n];
            f1 = param1ArrayOffloat2[i1];
            f4 = f1;
            f3 = f2;
            break;
        } 
        continue;
        i = param1Char1 + b;
        j = param1Char2;
      } 
      param1ArrayOffloat1[0] = f2;
      param1ArrayOffloat1[1] = f1;
      param1ArrayOffloat1[2] = f3;
      param1ArrayOffloat1[3] = f4;
      param1ArrayOffloat1[4] = f6;
      param1ArrayOffloat1[5] = f5;
    }
    
    private static void arcToBezier(Path param1Path, double param1Double1, double param1Double2, double param1Double3, double param1Double4, double param1Double5, double param1Double6, double param1Double7, double param1Double8, double param1Double9) {
      int i = (int)Math.ceil(Math.abs(param1Double9 * 4.0D / Math.PI));
      double d5 = Math.cos(param1Double7);
      double d7 = Math.sin(param1Double7);
      double d1 = Math.cos(param1Double8);
      double d2 = Math.sin(param1Double8);
      param1Double7 = -param1Double3;
      double d9 = param1Double7 * d5;
      double d10 = param1Double4 * d7;
      param1Double7 *= d7;
      double d6 = param1Double4 * d5;
      double d8 = param1Double9 / i;
      int j = 0;
      double d3 = d2 * param1Double7 + d1 * d6;
      d1 = d9 * d2 - d10 * d1;
      d2 = param1Double6;
      param1Double9 = param1Double5;
      double d4 = param1Double8;
      param1Double4 = d7;
      param1Double5 = d5;
      param1Double8 = param1Double7;
      param1Double7 = d8;
      param1Double6 = d6;
      while (true) {
        d6 = param1Double3;
        if (j < i) {
          d5 = d4 + param1Double7;
          d8 = Math.sin(d5);
          double d12 = Math.cos(d5);
          d7 = param1Double1 + d6 * param1Double5 * d12 - d10 * d8;
          double d11 = param1Double2 + d6 * param1Double4 * d12 + param1Double6 * d8;
          d6 = d9 * d8 - d10 * d12;
          d8 = d8 * param1Double8 + d12 * param1Double6;
          d4 = d5 - d4;
          d12 = Math.tan(d4 / 2.0D);
          d4 = Math.sin(d4) * (Math.sqrt(d12 * 3.0D * d12 + 4.0D) - 1.0D) / 3.0D;
          param1Path.rLineTo(0.0F, 0.0F);
          param1Path.cubicTo((float)(param1Double9 + d1 * d4), (float)(d2 + d3 * d4), (float)(d7 - d4 * d6), (float)(d11 - d4 * d8), (float)d7, (float)d11);
          j++;
          d2 = d11;
          param1Double9 = d7;
          d3 = d8;
          d1 = d6;
          d4 = d5;
          continue;
        } 
        break;
      } 
    }
    
    private static void drawArc(Path param1Path, float param1Float1, float param1Float2, float param1Float3, float param1Float4, float param1Float5, float param1Float6, float param1Float7, boolean param1Boolean1, boolean param1Boolean2) {
      double d5 = Math.toRadians(param1Float7);
      double d6 = Math.cos(d5);
      double d7 = Math.sin(d5);
      double d8 = param1Float1;
      double d9 = param1Float2;
      double d10 = param1Float5;
      double d1 = (d8 * d6 + d9 * d7) / d10;
      double d2 = -param1Float1;
      double d11 = param1Float6;
      double d4 = (d2 * d7 + d9 * d6) / d11;
      double d3 = param1Float3;
      d2 = param1Float4;
      double d12 = (d3 * d6 + d2 * d7) / d10;
      double d13 = (-param1Float3 * d7 + d2 * d6) / d11;
      double d15 = d1 - d12;
      double d14 = d4 - d13;
      d3 = (d1 + d12) / 2.0D;
      d2 = (d4 + d13) / 2.0D;
      double d16 = d15 * d15 + d14 * d14;
      if (d16 == 0.0D) {
        Log.w("PathParser", " Points are coincident");
        return;
      } 
      double d17 = 1.0D / d16 - 0.25D;
      if (d17 < 0.0D) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Points are too far apart ");
        stringBuilder.append(d16);
        Log.w("PathParser", stringBuilder.toString());
        float f = (float)(Math.sqrt(d16) / 1.99999D);
        drawArc(param1Path, param1Float1, param1Float2, param1Float3, param1Float4, param1Float5 * f, param1Float6 * f, param1Float7, param1Boolean1, param1Boolean2);
        return;
      } 
      d16 = Math.sqrt(d17);
      d15 *= d16;
      d14 = d16 * d14;
      if (param1Boolean1 == param1Boolean2) {
        d3 -= d14;
        d2 += d15;
      } else {
        d3 += d14;
        d2 -= d15;
      } 
      d14 = Math.atan2(d4 - d2, d1 - d3);
      d4 = Math.atan2(d13 - d2, d12 - d3) - d14;
      if (d4 >= 0.0D) {
        param1Boolean1 = true;
      } else {
        param1Boolean1 = false;
      } 
      d1 = d4;
      if (param1Boolean2 != param1Boolean1)
        if (d4 > 0.0D) {
          d1 = d4 - 6.283185307179586D;
        } else {
          d1 = d4 + 6.283185307179586D;
        }  
      d3 *= d10;
      d2 *= d11;
      arcToBezier(param1Path, d3 * d6 - d2 * d7, d3 * d7 + d2 * d6, d10, d11, d8, d9, d5, d14, d1);
    }
    
    public static void nodesToPath(PathDataNode[] param1ArrayOfPathDataNode, Path param1Path) {
      float[] arrayOfFloat = new float[6];
      char c = 'm';
      for (int i = 0; i < param1ArrayOfPathDataNode.length; i++) {
        addCommand(param1Path, arrayOfFloat, c, (param1ArrayOfPathDataNode[i]).mType, (param1ArrayOfPathDataNode[i]).mParams);
        c = (param1ArrayOfPathDataNode[i]).mType;
      } 
    }
    
    public void interpolatePathDataNode(PathDataNode param1PathDataNode1, PathDataNode param1PathDataNode2, float param1Float) {
      int i;
      for (i = 0; i < param1PathDataNode1.mParams.length; i++)
        this.mParams[i] = param1PathDataNode1.mParams[i] * (1.0F - param1Float) + param1PathDataNode2.mParams[i] * param1Float; 
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\support\v4\graphics\PathParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */