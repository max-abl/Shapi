package com.andro.prolapso.shapi.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.andro.prolapso.shapi.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void editProfile(View v) {
        Intent intent = new Intent(this, EditProfileActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "EditProfileActivity");
        startActivity(intent);
    }
}
