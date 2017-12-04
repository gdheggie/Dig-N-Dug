// Greg Heggie
// MDF3 - 1708
// CreditFragment

package com.example.gheggie.DigDug;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CreditFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "CreditFragment.TAG";

    public static CreditFragment newInstance() {
        return new CreditFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.credit_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button backBtn = (Button)getActivity().findViewById(R.id.backToMain);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        getActivity().finish();
    }
}
