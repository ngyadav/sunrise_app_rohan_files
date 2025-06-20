package rohan.groups.sunrise.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.R;

public class RewardsFragment extends Fragment {

    public RewardsFragment() {
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
        View v=inflater.inflate(R.layout.fragment_rewards, container, false);

        final Session session=new Session(getContext());

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

        Button add,transaction;
        add=v.findViewById(R.id.add);
        transaction=v.findViewById(R.id.transaction);

        ImageView invite=v.findViewById(R.id.invite);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                Fragment fragment = new RewardsDetails();
                FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();


            }
        });


        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String link = "https://sunrisegauribidanur.page.link?invitedby=" + session.getusername();
                FirebaseDynamicLinks.getInstance().createDynamicLink()
                        .setLink(Uri.parse(link))
                        .setDomainUriPrefix("https://sunrisegauribidanur.page.link")
                        .setAndroidParameters(
                                new DynamicLink.AndroidParameters.Builder("sunrise.release.rohan.sunrise")
                                        .build())
                        .setIosParameters(
                                new DynamicLink.IosParameters.Builder("com.example.ios")
                                        .setAppStoreId("123456789")
                                        .setMinimumVersion("1.0.1")
                                        .build())
                        .buildShortDynamicLink()
                        .addOnSuccessListener(new OnSuccessListener<ShortDynamicLink>() {
                            @Override
                            public void onSuccess(ShortDynamicLink shortDynamicLink) {
                                Uri mInvitationUrl = shortDynamicLink.getShortLink();

                                try {

//                                    Uri imageUri = Uri.parse("android.resource://" + getActivity().getPackageName()
//                                            + "/drawable/" + "ic_launcher");
//

                                    Intent i = new Intent(Intent.ACTION_SEND);
                                    i.setType("text/plain");
                                    i.putExtra(Intent.EXTRA_SUBJECT, "Sunrise");
//                                    i.putExtra(Intent.EXTRA_STREAM, imageUri);
                                    String sAux = "Bored of shopping Offline Give us a try & Claim your reward at the Store\n";
                                    sAux = sAux + mInvitationUrl.toString();
                                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                                    startActivity(Intent.createChooser(i, "Choose One"));
                                } catch(Exception e) {
                                    //e.toString();
                                }


                            }
                        });

            }
        });


        Button share=v.findViewById(R.id.share);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String link = "https://sunrisegauribidanur.page.link?invitedby=" + session.getusername();
                FirebaseDynamicLinks.getInstance().createDynamicLink()
                        .setLink(Uri.parse(link))
                        .setDomainUriPrefix("https://sunrisegauribidanur.page.link")
                        .setAndroidParameters(
                                new DynamicLink.AndroidParameters.Builder("sunrise.release.rohan.sunrise")
                                        .build())
                        .setIosParameters(
                                new DynamicLink.IosParameters.Builder("com.example.ios")
                                        .setAppStoreId("123456789")
                                        .setMinimumVersion("1.0.1")
                                        .build())
                        .buildShortDynamicLink()
                        .addOnSuccessListener(new OnSuccessListener<ShortDynamicLink>() {
                            @Override
                            public void onSuccess(ShortDynamicLink shortDynamicLink) {
                                Uri mInvitationUrl = shortDynamicLink.getShortLink();

                                try {

//                                    Uri imageUri = Uri.parse("android.resource://" + getActivity().getPackageName()
//                                            + "/drawable/" + "ic_launcher");
//

                                    Intent i = new Intent(Intent.ACTION_SEND);
                                    i.setType("text/plain");
                                    i.putExtra(Intent.EXTRA_SUBJECT, "Sunrise");
//                                    i.putExtra(Intent.EXTRA_STREAM, imageUri);
                                    String sAux = "Bored of shopping Offline Give us a try & Claim your reward at the Store\n";
                                    sAux = sAux + mInvitationUrl.toString();
                                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                                    startActivity(Intent.createChooser(i, "Choose One"));
                                } catch(Exception e) {
                                    //e.toString();
                                }


                            }
                        });


            }
        });


        final TextView rewards=v.findViewById(R.id.rewards);


        FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("Rewards1")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                        rewards.setText("#"+dataSnapshot.getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        return v;
    }


}
