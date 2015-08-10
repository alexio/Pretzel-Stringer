package com.nytimes.stringers;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.nytimes.data.entity.Stringer;
import com.nytimes.data.net.StringerApiClient;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by alexio on 8/9/15.
 */
public class SettingsActivity extends BaseActivity {

    StringerApiClient stringerApiClient;
    Stringer stringer;

    EditText name;
    EditText address;
    EditText location;
    EditText email;
    EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        location = (EditText) findViewById(R.id.currentLocation);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);

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
    }
}
