package com.nytimes.stringers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by alexio on 8/9/15.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.RED));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setTitle("Stringer");
        }
    }
}
