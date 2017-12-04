// Greg Heggie
// MDF3 - 1708
// Inventory Activity

package com.example.gheggie.DigDug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class InventoryActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        if(savedInstanceState == null) {
            InventoryFragment fragment = InventoryFragment.newInstance();
            getFragmentManager().beginTransaction()
                    .replace(R.id.inventory_frame, fragment, InventoryFragment.TAG)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
