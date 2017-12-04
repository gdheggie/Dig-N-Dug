// Greg Heggie
// MDF3 - 1708
// CreditActivity

package com.example.gheggie.DigDug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CreditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        if(savedInstanceState == null) {
            CreditFragment fragment = CreditFragment.newInstance();
            getFragmentManager().beginTransaction()
                    .replace(R.id.credit_frame, fragment, CreditFragment.TAG)
                    .commit();
        }

    }
}
