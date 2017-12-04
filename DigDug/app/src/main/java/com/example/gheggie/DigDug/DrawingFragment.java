// Greg Heggie
// MDF3 - 1708
// DrawingFragment

package com.example.gheggie.DigDug;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DrawingFragment extends Fragment {

    public static final String TAG = "DrawingFragment.TAG";

    public static DrawingFragment newInstance() {
        return new DrawingFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.draw_fragment, container, false);
    }

}
