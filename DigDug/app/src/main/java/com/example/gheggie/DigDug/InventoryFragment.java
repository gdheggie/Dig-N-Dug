// Greg Heggie
// MDF3 - 1708
// InventoryFragment

package com.example.gheggie.DigDug;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class InventoryFragment extends ListFragment{

    public static final String TAG = "InventoryFragment.TAG";

    public static InventoryFragment newInstance() {
        return new InventoryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.inventory_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // grab list view
        // set adapter with array list
        ListView mListView = getListView();
        ArrayAdapter<Treasure> mAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                DrawingSurface.sortArray(DrawingSurface.foundTreasure)
        );
        mListView.setAdapter(mAdapter);

        TextView tv = (TextView) getActivity().findViewById(R.id.goldCount);
        goldCounter(tv);
    }

    private void goldCounter(TextView tv) {
        int gold = 0;
        for(Treasure t : DrawingSurface.foundGold) {
            gold += Integer.parseInt(t.getAmount());
        }

        // set gold count to view
        String goldValue = getString(R.string.gold_title) + " " + gold;
        tv.setText(goldValue);
    }
}
