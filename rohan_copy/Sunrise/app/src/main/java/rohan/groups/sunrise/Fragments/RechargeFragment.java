package rohan.groups.sunrise.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import rohan.groups.sunrise.R;


public class RechargeFragment extends Fragment {


    public RechargeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_recharge, container, false);

        Toolbar toolbar=(getActivity()).findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);



        LinearLayout bottomnavigation=(getActivity()).findViewById(R.id.bottomnavigation);
        bottomnavigation.setVisibility(View.VISIBLE);

        return v;
    }

}
