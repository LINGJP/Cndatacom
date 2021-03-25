package com.cndatacom.cnportalclient.battery;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import com.stub.StubApp;

public final class BrandSpec {
  public static final IBrandOper OPER = a();
  
  private static IBrandOper a() {
    byte b;
    String str = Build.BRAND.toUpperCase();
    switch (str.hashCode()) {
      default:
        b = -1;
        break;
      case 2141820391:
        if (str.equals("HUAWEI")) {
          b = 0;
          break;
        } 
      case 1864941562:
        if (str.equals("samsung")) {
          b = 4;
          break;
        } 
      case 74224812:
        if (str.equals("Meizu")) {
          b = 5;
          break;
        } 
      case 3620012:
        if (str.equals("vivo")) {
          b = 3;
          break;
        } 
      case 2582855:
        if (str.equals("Sony")) {
          b = 11;
          break;
        } 
      case 2432928:
        if (str.equals("OPPO")) {
          b = 2;
          break;
        } 
      case 2364891:
        if (str.equals("Letv")) {
          b = 10;
          break;
        } 
      case 89163:
        if (str.equals("ZTE")) {
          b = 6;
          break;
        } 
      case 2427:
        if (str.equals("LG")) {
          b = 7;
          break;
        } 
      case -1636546600:
        if (str.equals("YuLong")) {
          b = 8;
          break;
        } 
      case -1706170181:
        if (str.equals("XIAOMI")) {
          b = 1;
          break;
        } 
      case -2053026509:
        if (str.equals("LENOVO")) {
          b = 9;
          break;
        } 
    } 
    switch (b) {
      case 10:
        return new LetvOper();
      case 9:
        return new LenovOper();
      case 6:
        return new ZTEOper();
      case 5:
        return new MeizuOper();
      case 4:
        return new SamsungOper();
      case 3:
        return new VivoOper();
      case 2:
        return new OPPOOper();
      case 1:
        return new XiaomiOper();
      case 0:
        return new HUAWEIOper();
    } 
    return new DefaultOper();
  }
  
  private static void b(Activity paramActivity) {
    paramActivity.startActivity(new Intent("android.settings.SETTINGS"));
  }
  
  public static class DefaultOper implements IBrandOper {
    public void batteryOptimizations(Activity param1Activity) {
      int i = Build.VERSION.SDK_INT;
      boolean bool2 = true;
      boolean bool1 = bool2;
      if (i >= 23) {
        PowerManager powerManager = (PowerManager)param1Activity.getSystemService("power");
        String str = param1Activity.getPackageName();
        if (powerManager != null && !powerManager.isIgnoringBatteryOptimizations(str)) {
          Intent intent = new Intent("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("package:");
          stringBuilder.append(str);
          intent.setData(Uri.parse(stringBuilder.toString()));
          if (intent.resolveActivity(param1Activity.getPackageManager()) != null)
            try {
              param1Activity.startActivity(intent);
            } catch (Exception exception) {
              exception.printStackTrace();
              bool1 = bool2;
            }  
        } 
        bool1 = false;
      } 
      if (bool1)
        BrandSpec.a(param1Activity); 
    }
    
    public String brand() {
      return (Build.VERSION.SDK_INT >= 23) ? "default" : "";
    }
  }
  
  public static class HUAWEIOper implements IBrandOper {
    public void batteryOptimizations(Activity param1Activity) {
      try {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.putExtra("packageName", StubApp.getOrigApplicationContext(param1Activity.getApplicationContext()).getPackageName());
        intent.putExtra("packageLabel", param1Activity.getResources().getString(2131492905));
        intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.power.ui.HwPowerManagerActivity"));
        param1Activity.startActivity(intent);
        return;
      } catch (Exception exception) {
        exception.printStackTrace();
        (new BrandSpec.DefaultOper()).batteryOptimizations(param1Activity);
        return;
      } 
    }
    
    public String brand() {
      return "HUAWEI";
    }
  }
  
  public static interface IBrandOper {
    void batteryOptimizations(Activity param1Activity);
    
    String brand();
  }
  
  public static class LenovOper implements IBrandOper {
    public void batteryOptimizations(Activity param1Activity) {
      try {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.putExtra("packageName", StubApp.getOrigApplicationContext(param1Activity.getApplicationContext()).getPackageName());
        intent.putExtra("packageLabel", param1Activity.getResources().getString(2131492905));
        intent.setComponent(new ComponentName("com.lenovo.powersetting", "com.lenovo.powersetting.ui.Settings$HighPowerApplicationsActivity"));
        param1Activity.startActivity(intent);
        return;
      } catch (Exception exception) {
        exception.printStackTrace();
        (new BrandSpec.DefaultOper()).batteryOptimizations(param1Activity);
        return;
      } 
    }
    
    public String brand() {
      return "LENOVO";
    }
  }
  
  public static class LetvOper implements IBrandOper {
    public void batteryOptimizations(Activity param1Activity) {
      try {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.putExtra("packageName", StubApp.getOrigApplicationContext(param1Activity.getApplicationContext()).getPackageName());
        intent.putExtra("packageLabel", param1Activity.getResources().getString(2131492905));
        intent.setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.BackgroundAppManageActivity"));
        param1Activity.startActivity(intent);
        return;
      } catch (Exception exception) {
        exception.printStackTrace();
        (new BrandSpec.DefaultOper()).batteryOptimizations(param1Activity);
        return;
      } 
    }
    
    public String brand() {
      return "Letv";
    }
  }
  
  public static class MeizuOper implements IBrandOper {
    public void batteryOptimizations(Activity param1Activity) {
      try {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.putExtra("packageName", StubApp.getOrigApplicationContext(param1Activity.getApplicationContext()).getPackageName());
        intent.putExtra("packageLabel", param1Activity.getResources().getString(2131492905));
        intent.setComponent(new ComponentName("com.meizu.safe", "com.meizu.safe.powerui.PowerAppPermissionActivity"));
        param1Activity.startActivity(intent);
        return;
      } catch (Exception exception) {
        exception.printStackTrace();
        (new BrandSpec.DefaultOper()).batteryOptimizations(param1Activity);
        return;
      } 
    }
    
    public String brand() {
      return "Meizu";
    }
  }
  
  public static class OPPOOper implements IBrandOper {
    public void batteryOptimizations(Activity param1Activity) {
      try {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.putExtra("packageName", StubApp.getOrigApplicationContext(param1Activity.getApplicationContext()).getPackageName());
        intent.putExtra("packageLabel", param1Activity.getResources().getString(2131492905));
        intent.setComponent(new ComponentName("com.coloros.oppoguardelf", "com.coloros.oppoguardelf.MonitoredPkgActivity"));
        param1Activity.startActivity(intent);
        return;
      } catch (Exception exception) {
        exception.printStackTrace();
        (new BrandSpec.DefaultOper()).batteryOptimizations(param1Activity);
        return;
      } 
    }
    
    public String brand() {
      return "OPPO";
    }
  }
  
  public static class SamsungOper implements IBrandOper {
    public void batteryOptimizations(Activity param1Activity) {
      try {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.putExtra("packageName", StubApp.getOrigApplicationContext(param1Activity.getApplicationContext()).getPackageName());
        intent.putExtra("packageLabel", param1Activity.getResources().getString(2131492905));
        intent.setComponent(new ComponentName("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.battery.BatteryActivity"));
        param1Activity.startActivity(intent);
        return;
      } catch (Exception exception) {
        exception.printStackTrace();
        (new BrandSpec.DefaultOper()).batteryOptimizations(param1Activity);
        return;
      } 
    }
    
    public String brand() {
      return "samsung";
    }
  }
  
  public static class VivoOper implements IBrandOper {
    public void batteryOptimizations(Activity param1Activity) {
      try {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.putExtra("packageName", StubApp.getOrigApplicationContext(param1Activity.getApplicationContext()).getPackageName());
        intent.putExtra("packageLabel", param1Activity.getResources().getString(2131492905));
        intent.setComponent(new ComponentName("com.vivo.abe", "com.vivo.applicationbehaviorengine.ui.ExcessivePowerManagerActivity"));
        param1Activity.startActivity(intent);
        return;
      } catch (Exception exception) {
        exception.printStackTrace();
        (new BrandSpec.DefaultOper()).batteryOptimizations(param1Activity);
        return;
      } 
    }
    
    public String brand() {
      return "vivo";
    }
  }
  
  public static class XiaomiOper implements IBrandOper {
    public void batteryOptimizations(Activity param1Activity) {
      try {
        Intent intent = new Intent("huawei.intent.action.SOFT_CONSUMPTION_DETAIL");
        intent.putExtra("package_name", StubApp.getOrigApplicationContext(param1Activity.getApplicationContext()).getPackageName());
        intent.putExtra("package_label", param1Activity.getResources().getString(2131492905));
        param1Activity.startActivity(intent);
        return;
      } catch (Exception exception) {
        exception.printStackTrace();
        (new BrandSpec.DefaultOper()).batteryOptimizations(param1Activity);
        return;
      } 
    }
    
    public String brand() {
      return "Xiaomi";
    }
  }
  
  public static class ZTEOper implements IBrandOper {
    public void batteryOptimizations(Activity param1Activity) {
      try {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.putExtra("packageName", StubApp.getOrigApplicationContext(param1Activity.getApplicationContext()).getPackageName());
        intent.putExtra("packageLabel", param1Activity.getResources().getString(2131492905));
        intent.setComponent(new ComponentName("com.zte.heartyservice", "com.zte.heartyservice.setting.ClearAppSettingsActivity"));
        param1Activity.startActivity(intent);
        return;
      } catch (Exception exception) {
        exception.printStackTrace();
        (new BrandSpec.DefaultOper()).batteryOptimizations(param1Activity);
        return;
      } 
    }
    
    public String brand() {
      return "ZTE";
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\battery\BrandSpec.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */