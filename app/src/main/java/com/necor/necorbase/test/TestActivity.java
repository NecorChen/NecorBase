package com.necor.necorbase.test;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.necor.necorbase.R;

public class TestActivity extends AppCompatActivity {

    TextView mTextView;
    String fixBefore = "修复前";
    String fixAfter = "修复后";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.tv_home);
        mTextView.setText(fixAfter);
//        mTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(TestActivity.this, "已完成修复", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
