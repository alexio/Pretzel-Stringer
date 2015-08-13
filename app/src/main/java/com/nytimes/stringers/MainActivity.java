package com.nytimes.stringers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.nytimes.data.entity.InvestigationModel;
import com.nytimes.stringers.views.InvestigationListView;
import com.nytimes.stringers.views.adapter.InvestigationRowAdapter;

public class MainActivity extends BaseActivity
        implements InvestigationRowAdapter.OnItemClickListener {

    public static final String stringer_id = "55c4fae79275cf4626000001";

    private InvestigationListView investigationListView;
    private RecyclerView.LayoutManager layoutManager;
    private InvestigationRowAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        investigationListView = (InvestigationListView) findViewById(R.id.investigation_view);
        investigationListView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        investigationListView.setLayoutManager(layoutManager);

        adapter = new InvestigationRowAdapter(this, InvestigationModel.getDummyData());
        adapter.setItemClickListener(this);

        investigationListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInvestigationClicked(InvestigationModel model) {
        //Navigate to investigation view
        Intent intent = new Intent(this, InvestigationActivity.class);
        intent.putExtra(InvestigationActivity.DATA_EXTRA, model);
        startActivity(intent);
    }
}
