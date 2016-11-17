package com.isil.abcars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.isil.abcars.storage.PreferencesHelper;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private static final long DELAY = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent i;
                boolean session = PreferencesHelper.isSignedIn(SplashActivity.this);
                if(session) {
                    i = new Intent(SplashActivity.this, MainActivity.class);
                } else {
                    i = new Intent(SplashActivity.this, LoginActivity.class);
                }
                startActivity(i);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, DELAY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { return false; }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { return false; }

}
