package com.hither.baike.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hither.baike.R;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //  setContentView(R.layout.activity_base);
    }
}
