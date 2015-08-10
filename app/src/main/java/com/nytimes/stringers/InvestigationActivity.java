package com.nytimes.stringers;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.widget.Toolbar;

import com.astuetz.PagerSlidingTabStrip;
import com.nytimes.data.entity.InvestigationModel;
import com.nytimes.stringers.views.adapter.InvestigationPageAdapter;

/**
 * Created by alexio on 8/9/15.
 */
public class InvestigationActivity extends BaseActivity {

    public static final String DATA_EXTRA = "data_extra";
    ViewPager viewPager;
    InvestigationModel investigationModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investigation);

        //initialize view pager and set adapter
        viewPager = (ViewPager) findViewById(R.id.investigation_view_pager);
        viewPager.setAdapter(new InvestigationPageAdapter(this));

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(viewPager);

        if (!getIntent().getExtras().containsKey(DATA_EXTRA)) {
            Toast.makeText(this, "Invalid state", Toast.LENGTH_SHORT).show();
        }

        investigationModel = (InvestigationModel) getIntent().getSerializableExtra(DATA_EXTRA);
    }
}
