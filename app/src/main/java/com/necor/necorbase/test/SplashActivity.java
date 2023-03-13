package com.necor.necorbase.test;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.necor.necorbase.R;

import java.io.File;

public class SplashActivity extends AppCompatActivity {

    public static final String[] SPLASH_PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(SPLASH_PERMISSIONS, 1);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        File fileDir = externalStorageDirectory != null ?
                new File(externalStorageDirectory, "FixTest") :
                new File(getFilesDir(), FixDexUtil.DEX_DIR);
        Log.i("necor_log", "fileDir.exists: " + fileDir.exists());
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        File[] listFiles = fileDir.listFiles();
        if (listFiles != null) {
            Log.i("necor_log", "listFiles: " + listFiles.length);
        }
        Log.i("necor_log", "isGoingToFix: " + FixDexUtil.isGoingToFix(SplashActivity.this));
        if (FixDexUtil.isGoingToFix(SplashActivity.this)) {
            FixDexUtil.loadFixedDex(SplashActivity.this, Environment.getExternalStorageDirectory());
            Toast.makeText(SplashActivity.this, "修复中", Toast.LENGTH_SHORT).show();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, TestActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

    }
}
