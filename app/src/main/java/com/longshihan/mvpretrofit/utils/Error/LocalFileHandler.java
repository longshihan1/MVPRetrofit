package com.longshihan.mvpretrofit.utils.Error;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.longshihan.mvpretrofit.utils.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

/**
 * 本地异常处理类
 *
 * @author PLUTO
 */
public class LocalFileHandler extends BaseExceptionHandler {

    private Context context;

    public LocalFileHandler(Context context) {
        this.context = context;
    }

    /**
     * 自定义错误处理,手机错误信息,发送错误报告操作均在此完成,开发者可以根据自己的情况来自定义异常处理逻辑
     *
     * @param ex
     * @return
     */
    @Override
    public boolean handleException(Throwable ex) {
        if (ex == null)
            return false;


        //保存错误日志
        saveLog(ex);

        return true;
    }

    /**
     * 保存错误日志发送到服务器
     *
     * @param ex
     */
    private void saveLog(Throwable ex) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            TelephonyManager phoneMgr = (TelephonyManager) context.getSystemService(Context
                    .TELEPHONY_SERVICE);
            stringBuffer.append("手机型号：" + Build.MODEL + "\t");
            stringBuffer.append("SDK版本：" + Build.VERSION.SDK + "\t");
            stringBuffer.append("版本号：" + Build.VERSION.RELEASE + "\t");
            stringBuffer.append("Test:" + "false" + "\t");
            stringBuffer.append("Decives:" + Settings.Secure.getString(context.getContentResolver
                    (), Settings.Secure.ANDROID_ID) + "\t");
            PackageManager manager;
            PackageInfo info = null;
            manager = context.getPackageManager();
            try {
                info = manager.getPackageInfo(context.getPackageName(), 0);
                stringBuffer.append("AppVersion" + info.versionName);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            File path = new File(FileUtil.getDiskCacheDir(context) + "/log");
            if (!path.exists()) {
                path.mkdirs();
            }

            File errorFile = new File(path + "/error.txt");

            if (!errorFile.exists()) {
                errorFile.createNewFile();
            }
            OutputStream out = new FileOutputStream(errorFile, true);
            out.write(("\n\n-----错误分割线:手机信息：" + stringBuffer + dateFormat.format(new Date()) +
                    "-----\n\n").getBytes());
            PrintStream stream = new PrintStream(out);
            ex.printStackTrace(stream);

            stream.flush();
            out.flush();
            stream.close();
            out.close();
            String error_net = dateFormat.format(new Date()) + "\n" + stringBuffer + "\n" + sw
                    .toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getErrorInfoFromException(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return "\r\n" + sw.toString() + "\r\n";
        } catch (Exception e2) {
            return "bad getErrorInfoFromException";
        }
    }
}
