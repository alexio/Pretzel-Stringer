package com.nytimes.stringers;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.nytimes.data.entity.EmptyRequestResponse;
import com.nytimes.data.entity.Stringer;
import com.nytimes.data.net.StringerApiClient;
import com.rey.material.widget.Switch;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by alexio on 8/9/15.
 */
public class SettingsActivity extends BaseActivity implements Switch.OnCheckedChangeListener {

    StringerApiClient stringerApiClient;
    Stringer stringer;

    EditText name;
    EditText address;
    EditText location;
    EditText email;
    EditText phone;
    Switch availibilityToggle;
    ActionProcessButton updateLocation;

    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setTitle("Profile");

        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        location = (EditText) findViewById(R.id.currentLocation);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        availibilityToggle = (Switch) findViewById(R.id.availability_toggle);
        availibilityToggle.setOnCheckedChangeListener(this);

        updateLocation = (ActionProcessButton) findViewById(R.id.updateLocation);
        updateLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateLocation.setMode(ActionProcessButton.Mode.ENDLESS);
                Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        updateLocation.setProgress(1);
                        updateLocation.setProgress(50);
                        updateLocation.setProgress(100);
                    }
                };
                worker.schedule(task, 10, TimeUnit.SECONDS);
            }
        });


        stringerApiClient = new StringerApiClient();
        stringerApiClient.getStringer(MainActivity.stringer_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Stringer>() {
                    @Override
                    public void call(Stringer stringer) {
                        loadData(stringer);
                        Log.e("**", "Got Stringer Info!");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("**", "Error", throwable);
                        Toast.makeText(SettingsActivity.this, "Error retrieving Stringer info", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void loadData(Stringer stringer) {
        this.stringer = stringer;
        name.setText(stringer.getName());
        address.setText(stringer.getBaseLocation().toString());
        location.setText(stringer.getCurrentLocation().toString());
        email.setText(stringer.getEmail());
        phone.setText(stringer.getPhone());
        availibilityToggle.setChecked(stringer.isAvailable());
    }

    @Override
    public void onCheckedChanged(Switch aSwitch, boolean isChecked) {
        Log.e("**", "Why u go off?");
        stringerApiClient.setAvailability(MainActivity.stringer_id, isChecked)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<EmptyRequestResponse>() {
                    @Override
                    public void call(EmptyRequestResponse o) {
                        Log.e("**", "Setting it worked!");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("**", "ERROR Check", throwable);
                    }
                });
    }
}
