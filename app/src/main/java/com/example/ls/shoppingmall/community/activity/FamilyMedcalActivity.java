package com.example.ls.shoppingmall.community.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;

public class FamilyMedcalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_medcal);
        MyApplication.addActivity(this);

    }
}
