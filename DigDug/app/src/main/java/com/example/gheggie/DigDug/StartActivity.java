// Greg Heggie
// MDF3 - 1708
// StartActivity

package com.example.gheggie.DigDug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if(savedInstanceState == null) {
            StartFragment fragment = StartFragment.newInstance();
            getFragmentManager().beginTransaction()
                    .replace(R.id.start_frame, fragment, StartFragment.TAG)
                    .commit();
        }
    }
}
