package com.example.ls.shoppingmall.community.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;

public class MedicalTearmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_tearm);
        MyApplication.addActivity(this);

    }
}
