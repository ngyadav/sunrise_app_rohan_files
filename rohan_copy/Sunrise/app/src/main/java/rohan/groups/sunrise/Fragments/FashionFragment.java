package rohan.groups.sunrise.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.R;

public class FashionFragment extends Fragment {


    ImageView i1,i2,i3,i4,n1,n2,n3,n4,search;
    ImageView hsaree,hmen,hwomen,hkids,hwatches,hothers;
    ImageView m1,m2,m3,m4,m5,m6;
    TextView nname1,nname2,nname3,nname4;
    TextView nprice1,nprice2,nprice3,nprice4;
    TextView nrewards1,nrewards2,nrewards3,nrewards4;
    LinearLayout l1,l2,l3,l4;
    TextView npushid1,npushid2,npushid3,npushid4;

    Fragment fragment;
    Session session;

    String[] sampleimages=new String[100];
    String[] samplepath=new String[100];
    CarouselView homecarousel;

    public FashionFragment() {
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
        View v=inflater.inflate(R.layout.fragment_fashion, container, false);

        Toolbar toolbar=(getActivity()).findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        LinearLayout bottomnavigation=(getActivity()).findViewById(R.id.bottomnavigation);
        bottomnavigation.setVisibility(View.GONE);


        homecarousel=v.findViewById(R.id.home_carousel);


        session=new Session(getContext());
        search=v.findViewById(R.id.search);


        i1=v.findViewById(R.id.i1);
        i2=v.findViewById(R.id.i2);
        i3=v.findViewById(R.id.i3);
        i4=v.findViewById(R.id.i4);
        n1=v.findViewById(R.id.n1);
        n2=v.findViewById(R.id.n2);
        n3=v.findViewById(R.id.n3);
        n4=v.findViewById(R.id.n4);
        hsaree=v.findViewById(R.id.hsaree);
        hmen=v.findViewById(R.id.hmens);
        hwomen=v.findViewById(R.id.hwomens);
        hkids=v.findViewById(R.id.hkids);
        hwatches=v.findViewById(R.id.hwatches);
        hothers=v.findViewById(R.id.hothers);
        m1=v.findViewById(R.id.m1);
        m2=v.findViewById(R.id.m2);
        m3=v.findViewById(R.id.m3);
        m4=v.findViewById(R.id.m4);
        m5=v.findViewById(R.id.m5);
        m6=v.findViewById(R.id.m6);
        nname1=v.findViewById(R.id.nname1);
        nname2=v.findViewById(R.id.nname2);
        nname3=v.findViewById(R.id.nname3);
        nname4=v.findViewById(R.id.nname4);
        nprice1=v.findViewById(R.id.nprice1);
        nprice2=v.findViewById(R.id.nprice2);
        nprice3=v.findViewById(R.id.nprice3);
        nprice4=v.findViewById(R.id.nprice4);
        nrewards1=v.findViewById(R.id.nrewards1);
        nrewards2=v.findViewById(R.id.nrewards2);
        nrewards3=v.findViewById(R.id.nrewards3);
        nrewards4=v.findViewById(R.id.nrewards4);
        npushid1=v.findViewById(R.id.npushid1);
        npushid2=v.findViewById(R.id.npushid2);
        npushid3=v.findViewById(R.id.npushid3);
        npushid4=v.findViewById(R.id.npushid4);

        l1=v.findViewById(R.id.l1);
        l2=v.findViewById(R.id.l2);
        l3=v.findViewById(R.id.l3);
        l4=v.findViewById(R.id.l4);


        session.setrange("");
        session.setsub("");


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new SearchFragment();
                FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });


        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.child("AppContent").child("Fashion").child("Baner");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;
                for(DataSnapshot v:dataSnapshot.getChildren()){
                    sampleimages[i]=v.child("Name").getValue(String.class);
                    samplepath[i]=v.child("Path").getValue(String.class);
                    i++;
                }
                homecarousel.setImageListener(imageListener);
                homecarousel.setPageCount(i);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        homecarousel.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Bundle bundle=new Bundle();
                ProductDetails fragment= new ProductDetails();
                bundle.putString("name",samplepath[position]);
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });

        FirebaseDatabase.getInstance().getReference().child("AppContent").child("Fashion")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child("Fashion1").exists()){
                            Glide.with(getContext())
                                    .load(dataSnapshot.child("Fashion1").child("Image1").getValue().toString())
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(n1);

                            nname1.setText(dataSnapshot.child("Fashion1").child("Name").getValue().toString());
                            nprice1.setText("\u20b9 "+dataSnapshot.child("Fashion1").child("Price").getValue().toString());
                            nrewards1.setText(dataSnapshot.child("Fashion1").child("Cashback").getValue().toString()+" Rewards Points");
                            npushid1.setText(dataSnapshot.child("Fashion1").child("PushId").getValue().toString());


                        }

                        if(dataSnapshot.child("Fashion2").exists()){
                            Glide.with(getContext())
                                    .load(dataSnapshot.child("Fashion2").child("Image1").getValue().toString())
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(n2);

                            nname2.setText(dataSnapshot.child("Fashion2").child("Name").getValue().toString());
                            nprice2.setText("\u20b9 "+dataSnapshot.child("Fashion2").child("Price").getValue().toString());
                            nrewards2.setText(dataSnapshot.child("Fashion2").child("Cashback").getValue().toString()+" Rewards Points");
                            npushid2.setText(dataSnapshot.child("Fashion2").child("PushId").getValue().toString());


                        }


                        if(dataSnapshot.child("Fashion3").exists()){
                            Glide.with(getContext())
                                    .load(dataSnapshot.child("Fashion3").child("Image1").getValue().toString())
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(n3);

                            nname3.setText(dataSnapshot.child("Fashion3").child("Name").getValue().toString());
                            nprice3.setText("\u20b9 "+dataSnapshot.child("Fashion3").child("Price").getValue().toString());
                            nrewards3.setText(dataSnapshot.child("Fashion3").child("Cashback").getValue().toString()+" Rewards Points");
                            npushid3.setText(dataSnapshot.child("Fashion3").child("PushId").getValue().toString());


                        }


                        if(dataSnapshot.child("Fashion4").exists()){
                            Glide.with(getContext())
                                    .load(dataSnapshot.child("Fashion4").child("Image1").getValue().toString())
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(n4);

                            nname4.setText(dataSnapshot.child("Fashion4").child("Name").getValue().toString());
                            nprice4.setText("\u20b9 "+dataSnapshot.child("Fashion4").child("Price").getValue().toString());
                            nrewards4.setText(dataSnapshot.child("Fashion4").child("Cashback").getValue().toString()+" Rewards Points");
                            npushid4.setText(dataSnapshot.child("Fashion4").child("PushId").getValue().toString());


                        }




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


        hsaree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment=new FashionDetails();
                session.setsub("Fashion SAREE");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container,fragment).commit();

            }
        });


        hmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment=new FashionDetails();
                session.setsub("Fashion MENSFASHION");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container,fragment).commit();

            }
        });

        hwomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=new FashionDetails();
                session.setsub("Fashion WOMENSFASHION");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container,fragment).commit();

            }
        });

        hkids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=new FashionDetails();
                session.setsub("Fashion KIDSFASHION");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container,fragment).commit();

            }
        });

        hwatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=new FashionDetails();
                session.setsub("Fashion WATCHES");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container,fragment).commit();

            }
        });


        hothers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=new FashionDetails();
                session.setsub("Fashion OTHERS");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container,fragment).commit();

            }
        });

        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment=new FashionDetails();
                session.setrange("0,500");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container,fragment).commit();

            }
        });


        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment=new FashionDetails();
                session.setrange("500,1000");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container,fragment).commit();

            }
        });

        m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment=new FashionDetails();
                session.setrange("1000,2000");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container,fragment).commit();

            }
        });

        m4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment=new FashionDetails();
                session.setrange("2000,3000");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container,fragment).commit();

            }
        });

        m5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment=new FashionDetails();
                session.setrange("3000,5000");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container,fragment).commit();

            }
        });

        m6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment=new FashionDetails();
                session.setrange("5000,0");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container,fragment).commit();

            }
        });


        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",npushid1.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",npushid2.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });

        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",npushid3.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",npushid4.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        return v;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
//            imageView.setImageResource(R.drawable.accesories);
            Glide.with(getContext())
                    .load(sampleimages[position])
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    };


}
