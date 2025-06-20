package sunrise.release.rohan.sunrise.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.Script;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import sunrise.release.rohan.sunrise.Functionality.LoginSession;
import sunrise.release.rohan.sunrise.R;
import sunrise.release.rohan.sunrise.splash.SplashScreen;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ReferFragement extends Fragment {

    private ImageView invite;
    private LoginSession session;
    private AdView mAdView;
    public ReferFragement() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(getContext(), "ca-app-pub-5167989951191945~5928872897");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_refer_fragement, container, false);

        mAdView = v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final TextView content=(TextView)v.findViewById(R.id.content);
        final TextView code=(TextView)v.findViewById(R.id.code);
        invite=(ImageView)v.findViewById(R.id.invite);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.child("AppContent").child("Referral").child("Content");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               String con= (String) dataSnapshot.getValue();
                content.setText(con);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        session = new LoginSession(getActivity());
        code.setText(session.getusename());

        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "SUNRISE");
                    String sAux = "Bored of shopping Offline Give a try\n Use My Referral Code:"+session.getusename()+"\n & Claim your reward at the Store\n";
                    sAux = sAux + "https://play.google.com/store/apps/details?id=sunrise.release.rohan.sunrise";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "Choose One"));
                } catch(Exception e) {
                    //e.toString();
                }

            }
        });


        return v;
    }

}
