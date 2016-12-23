package com.example.joern.blockengine;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import static android.os.Build.VERSION.SDK_INT;



public class Permissions
{
    public static boolean requestExternalStoragePermission(Activity activity)
    {
        if (SDK_INT < 23)
        {
            return true;
        }

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
        {
            String[] strings =
                    {
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    };

            activity.requestPermissions(strings, 1);

            return false;
        }

        return true;
    }
}
