package rohan.groups.sunrise.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zopim.android.sdk.api.ZopimChat;
import com.zopim.android.sdk.model.VisitorInfo;
import com.zopim.android.sdk.prechat.ZopimChatActivity;

import rohan.groups.sunrise.Functionality.AccountKey;
import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.R;

public class HelpFragment extends Fragment {


    public HelpFragment() {
        // Required empty public constructor
    }


    Session session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_help, container, false);
        Toolbar toolbar=(getActivity()).findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        ImageView back=v.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        LinearLayout bottomnavigation=(getActivity()).findViewById(R.id.bottomnavigation);
        bottomnavigation.setVisibility(View.GONE);

        ZopimChat.init(AccountKey.ACCOUNT_KEY);


        TextView live=v.findViewById(R.id.live);

        session=new Session(getContext());

        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                VisitorInfo visitorData = new VisitorInfo.Builder()
                        .name(session.getname())
                        .phoneNumber(session.getusername())
                        .note("User Help")
                        .build();

                ZopimChat.setVisitorInfo(visitorData);



                startActivity(new Intent(getActivity(), ZopimChatActivity.class));

                ZopimChat.setPushToken(session.gettoken());

            }
        });

        return v;
    }


}
