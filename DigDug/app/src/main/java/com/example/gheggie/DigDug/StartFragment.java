// Greg Heggie
// MDF3 - 1708
// StartFragment

package com.example.gheggie.DigDug;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class StartFragment extends Fragment implements View.OnClickListener{

    public static final String TAG = "StartFragment.TAG";

    public static StartFragment newInstance() {
        return new StartFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.start_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button startGame = (Button)getActivity().findViewById(R.id.start_game);
        startGame.setOnClickListener(this);

        Button creditBtn = (Button)getActivity().findViewById(R.id.to_credits);
        creditBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.start_game) {
            // start game intent
            Intent start = new Intent(getActivity(), DrawActivity.class);
            startActivity(start);
        } else {
            // credit intent
            Intent credit = new Intent(getActivity(), CreditActivity.class);
            startActivity(credit);
        }
    }
}
