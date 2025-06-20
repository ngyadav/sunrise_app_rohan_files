package rohan.groups.sunrise.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.ArrayList;

import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.MainActivity;
import rohan.groups.sunrise.R;
import rohan.groups.sunrise.SplashScreen.SplashScreen;

public class ShopFragment extends Fragment {


    private ImageView h1,h2,h3,h4,h5,h6,i1,i2,i3,i4,baner1,baner2;
    private ImageView hsamsung,hmi,hoppo,hnokia,hvivo;
    private ImageView n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14,n15,n16;
    private LinearLayout l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16;
    private TextView nname1,nname2,nname3,nname4,nname5,nname6,nname7,nname8,nname9,nname10,nname11,nname12,nname13,nname14,nname15,nname16;
    private TextView nprice1,nprice2,nprice3,nprice4,nprice5,nprice6,nprice7,nprice8,nprice9,nprice10,nprice11,nprice12,nprice13,nprice14,nprice15,nprice16;
    private TextView nrewards1,nrewards2,nrewards3,nrewards4,nrewards5,nrewards6,nrewards7,nrewards8,nrewards9,nrewards10,nrewards11,nrewards12,nrewards13,nrewards14,nrewards15,nrewards16;
    private TextView npushid1,npushid2,npushid3,npushid4,npushid5,npushid6,npushid7,npushid8,npushid9,npushid10,npushid11,npushid12,npushid13,npushid14,npushid15,npushid16;
    private Fragment fragment;
    private Session session;
    private AutoCompleteTextView search;

    private TextView heading1,heading2,heading3,heading4,heading5,heading6,heading7,heading8;
    private LinearLayout z1,z2,z3,z4,z5,z6,z7,z8,z9,z10,z11,z12;
    private ImageView x1,x2,x3,x4,x5,x6,x7,x8,x9,x10,x11,x12;
    private TextView xname1,xname2,xname3,xname4,xname5,xname6,xname7,xname8,xname9,xname10,xname11,xname12;
    private TextView xprice1,xprice2,xprice3,xprice4,xprice5,xprice6,xprice7,xprice8,xprice9,xprice10,xprice11,xprice12;
    private TextView xrewards1,xrewards2,xrewards3,xrewards4,xrewards5,xrewards6,xrewards7,xrewards8,xrewards9,xrewards10,xrewards11,xrewards12;
    private TextView xpushid1,xpushid2,xpushid3,xpushid4,xpushid5,xpushid6,xpushid7,xpushid8,xpushid9,xpushid10,xpushid11,xpushid12;

    private ProgressBar progressBar;


    ArrayList<String> input = new ArrayList<String>();
    ArrayList<String> input1 = new ArrayList<String>();
    ArrayList<String> cat = new ArrayList<String>();

    String[] sampleimages=new String[100];
    String[] samplepath=new String[100];
    CarouselView homecarousel;

    public ShopFragment() {
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
        View v=inflater.inflate(R.layout.fragment_shop, container, false);

        Toolbar toolbar=(getActivity()).findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);

        LinearLayout bottomnavigation=(getActivity()).findViewById(R.id.bottomnavigation);
        bottomnavigation.setVisibility(View.VISIBLE);

        session=new Session(getContext());

        h1=v.findViewById(R.id.h1);
        h2=v.findViewById(R.id.h2);
        h3=v.findViewById(R.id.h3);
        h4=v.findViewById(R.id.h4);
        h5=v.findViewById(R.id.h5);
        h6=v.findViewById(R.id.h6);
        search = v.findViewById(R.id.search);
        progressBar = v.findViewById(R.id.progressbar);

        progressBar.setVisibility(View.VISIBLE);

        i1=v.findViewById(R.id.i1);
        i2=v.findViewById(R.id.i2);
        i3=v.findViewById(R.id.i3);
        i4=v.findViewById(R.id.i4);

        homecarousel=v.findViewById(R.id.home_carousel);

        baner1=v.findViewById(R.id.baner1);
        baner2=v.findViewById(R.id.baner2);

        hsamsung=v.findViewById(R.id.hsamsung);
        hmi=v.findViewById(R.id.hmi);
        hnokia=v.findViewById(R.id.hnokia);
        hoppo=v.findViewById(R.id.hoppo);
        hvivo=v.findViewById(R.id.hvivo);


        l1=v.findViewById(R.id.l1);
        l2=v.findViewById(R.id.l2);
        l3=v.findViewById(R.id.l3);
        l4=v.findViewById(R.id.l4);
        l5=v.findViewById(R.id.l5);
        l6=v.findViewById(R.id.l6);
        l7=v.findViewById(R.id.l7);
        l8=v.findViewById(R.id.l8);
        l9=v.findViewById(R.id.l9);
        l10=v.findViewById(R.id.l10);
        l11=v.findViewById(R.id.l11);
        l12=v.findViewById(R.id.l12);
        l13=v.findViewById(R.id.l13);
        l14=v.findViewById(R.id.l14);
        l15=v.findViewById(R.id.l15);
        l16=v.findViewById(R.id.l16);

        z1=v.findViewById(R.id.z1);
        z2=v.findViewById(R.id.z2);
        z3=v.findViewById(R.id.z3);
        z4=v.findViewById(R.id.z4);
        z5=v.findViewById(R.id.z5);
        z6=v.findViewById(R.id.z6);
        z7=v.findViewById(R.id.z7);
        z8=v.findViewById(R.id.z8);
        z9=v.findViewById(R.id.z9);
        z10=v.findViewById(R.id.z10);
        z11=v.findViewById(R.id.z11);
        z12=v.findViewById(R.id.z12);


        n1=v.findViewById(R.id.n1);
        n2=v.findViewById(R.id.n2);
        n3=v.findViewById(R.id.n3);
        n4=v.findViewById(R.id.n4);
        n5=v.findViewById(R.id.n5);
        n6=v.findViewById(R.id.n6);
        n7=v.findViewById(R.id.n7);
        n8=v.findViewById(R.id.n8);
        n9=v.findViewById(R.id.n9);
        n10=v.findViewById(R.id.n10);
        n11=v.findViewById(R.id.n11);
        n12=v.findViewById(R.id.n12);
        n13=v.findViewById(R.id.n13);
        n14=v.findViewById(R.id.n14);
        n15=v.findViewById(R.id.n15);
        n16=v.findViewById(R.id.n16);


        x1=v.findViewById(R.id.x1);
        x2=v.findViewById(R.id.x2);
        x3=v.findViewById(R.id.x3);
        x4=v.findViewById(R.id.x4);
        x5=v.findViewById(R.id.x5);
        x6=v.findViewById(R.id.x6);
        x7=v.findViewById(R.id.x7);
        x8=v.findViewById(R.id.x8);
        x9=v.findViewById(R.id.x9);
        x10=v.findViewById(R.id.x10);
        x11=v.findViewById(R.id.x11);
        x12=v.findViewById(R.id.x12);


        nname1=v.findViewById(R.id.nname1);
        nname2=v.findViewById(R.id.nname2);
        nname3=v.findViewById(R.id.nname3);
        nname4=v.findViewById(R.id.nname4);
        nname5=v.findViewById(R.id.nname5);
        nname6=v.findViewById(R.id.nname6);
        nname7=v.findViewById(R.id.nname7);
        nname8=v.findViewById(R.id.nname8);
        nname9=v.findViewById(R.id.nname9);
        nname10=v.findViewById(R.id.nname10);
        nname11=v.findViewById(R.id.nname11);
        nname12=v.findViewById(R.id.nname12);
        nname13=v.findViewById(R.id.nname13);
        nname14=v.findViewById(R.id.nname14);
        nname15=v.findViewById(R.id.nname15);
        nname16=v.findViewById(R.id.nname16);

        xname1=v.findViewById(R.id.xname1);
        xname2=v.findViewById(R.id.xname2);
        xname3=v.findViewById(R.id.xname3);
        xname4=v.findViewById(R.id.xname4);
        xname5=v.findViewById(R.id.xname5);
        xname6=v.findViewById(R.id.xname6);
        xname7=v.findViewById(R.id.xname7);
        xname8=v.findViewById(R.id.xname8);
        xname9=v.findViewById(R.id.xname9);
        xname10=v.findViewById(R.id.xname10);
        xname11=v.findViewById(R.id.xname11);
        xname12=v.findViewById(R.id.xname12);


        nprice1=v.findViewById(R.id.nprice1);
        nprice2=v.findViewById(R.id.nprice2);
        nprice3=v.findViewById(R.id.nprice3);
        nprice4=v.findViewById(R.id.nprice4);
        nprice5=v.findViewById(R.id.nprice5);
        nprice6=v.findViewById(R.id.nprice6);
        nprice7=v.findViewById(R.id.nprice7);
        nprice8=v.findViewById(R.id.nprice8);
        nprice9=v.findViewById(R.id.nprice9);
        nprice10=v.findViewById(R.id.nprice10);
        nprice11=v.findViewById(R.id.nprice11);
        nprice12=v.findViewById(R.id.nprice12);
        nprice13=v.findViewById(R.id.nprice13);
        nprice14=v.findViewById(R.id.nprice14);
        nprice15=v.findViewById(R.id.nprice15);
        nprice16=v.findViewById(R.id.nprice16);

        xprice1=v.findViewById(R.id.xprice1);
        xprice2=v.findViewById(R.id.xprice2);
        xprice3=v.findViewById(R.id.xprice3);
        xprice4=v.findViewById(R.id.xprice4);
        xprice5=v.findViewById(R.id.xprice5);
        xprice6=v.findViewById(R.id.xprice6);
        xprice7=v.findViewById(R.id.xprice7);
        xprice8=v.findViewById(R.id.xprice8);
        xprice9=v.findViewById(R.id.xprice9);
        xprice10=v.findViewById(R.id.xprice10);
        xprice11=v.findViewById(R.id.xprice11);
        xprice12=v.findViewById(R.id.xprice12);


        nrewards1=v.findViewById(R.id.nrewards1);
        nrewards2=v.findViewById(R.id.nrewards2);
        nrewards3=v.findViewById(R.id.nrewards3);
        nrewards4=v.findViewById(R.id.nrewards4);
        nrewards5=v.findViewById(R.id.nrewards5);
        nrewards6=v.findViewById(R.id.nrewards6);
        nrewards7=v.findViewById(R.id.nrewards7);
        nrewards8=v.findViewById(R.id.nrewards8);
        nrewards9=v.findViewById(R.id.nrewards9);
        nrewards10=v.findViewById(R.id.nrewards10);
        nrewards11=v.findViewById(R.id.nrewards11);
        nrewards12=v.findViewById(R.id.nrewards12);
        nrewards13=v.findViewById(R.id.nrewards13);
        nrewards14=v.findViewById(R.id.nrewards14);
        nrewards15=v.findViewById(R.id.nrewards15);
        nrewards16=v.findViewById(R.id.nrewards16);


        xrewards1=v.findViewById(R.id.xrewards1);
        xrewards2=v.findViewById(R.id.xrewards2);
        xrewards3=v.findViewById(R.id.xrewards3);
        xrewards4=v.findViewById(R.id.xrewards4);
        xrewards5=v.findViewById(R.id.xrewards5);
        xrewards6=v.findViewById(R.id.xrewards6);
        xrewards7=v.findViewById(R.id.xrewards7);
        xrewards8=v.findViewById(R.id.xrewards8);
        xrewards9=v.findViewById(R.id.xrewards9);
        xrewards10=v.findViewById(R.id.xrewards10);
        xrewards11=v.findViewById(R.id.xrewards11);
        xrewards12=v.findViewById(R.id.xrewards12);



        npushid1=v.findViewById(R.id.npushid1);
        npushid2=v.findViewById(R.id.npushid2);
        npushid3=v.findViewById(R.id.npushid3);
        npushid4=v.findViewById(R.id.npushid4);
        npushid5=v.findViewById(R.id.npushid5);
        npushid6=v.findViewById(R.id.npushid6);
        npushid7=v.findViewById(R.id.npushid7);
        npushid8=v.findViewById(R.id.npushid8);
        npushid9=v.findViewById(R.id.npushid9);
        npushid10=v.findViewById(R.id.npushid10);
        npushid11=v.findViewById(R.id.npushid11);
        npushid12=v.findViewById(R.id.npushid12);
        npushid13=v.findViewById(R.id.npushid13);
        npushid14=v.findViewById(R.id.npushid14);
        npushid15=v.findViewById(R.id.npushid15);
        npushid16=v.findViewById(R.id.npushid16);



        xpushid1=v.findViewById(R.id.xpushid1);
        xpushid2=v.findViewById(R.id.xpushid2);
        xpushid3=v.findViewById(R.id.xpushid3);
        xpushid4=v.findViewById(R.id.xpushid4);
        xpushid5=v.findViewById(R.id.xpushid5);
        xpushid6=v.findViewById(R.id.xpushid6);
        xpushid7=v.findViewById(R.id.xpushid7);
        xpushid8=v.findViewById(R.id.xpushid8);
        xpushid9=v.findViewById(R.id.xpushid9);
        xpushid10=v.findViewById(R.id.xpushid10);
        xpushid11=v.findViewById(R.id.xpushid11);
        xpushid12=v.findViewById(R.id.xpushid12);


        heading1=v.findViewById(R.id.heading1);
        heading2=v.findViewById(R.id.heading2);
        heading3=v.findViewById(R.id.heading3);
        heading4=v.findViewById(R.id.heading4);
        heading5=v.findViewById(R.id.heading5);
        heading6=v.findViewById(R.id.heading6);
        heading7=v.findViewById(R.id.heading7);
        heading8=v.findViewById(R.id.heading8);



        FirebaseDatabase.getInstance().getReference().child("AppContent").child("Heading").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    heading1.setText(dataSnapshot.child("Heading1").getValue().toString());
                    heading2.setText(dataSnapshot.child("Heading2").getValue().toString());
                    heading4.setText(dataSnapshot.child("Heading3").getValue().toString());
                    heading5.setText(dataSnapshot.child("Heading4").getValue().toString());
                    heading6.setText(dataSnapshot.child("Heading5").getValue().toString());
                    heading7.setText(dataSnapshot.child("Heading6").getValue().toString());
                    heading8.setText(dataSnapshot.child("Heading7").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        FragmentManager fm = getActivity().getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount()-1; ++i) {
            fm.popBackStack();
        }


        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    System.exit(0);
                    return true;
                }
                return false;
            }
        } );


        search.setText("");
        FirebaseDatabase.getInstance().getReference().child("CategoryNew")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        input.clear();
                        input1.clear();
                        cat.clear();
                        for (DataSnapshot v : dataSnapshot.getChildren()) {
                            input.add(v.child("Name").getValue().toString());
                            input1.add(v.child("PushId").getValue().toString());
                        }


                        if(getContext()!=null) {
                            ArrayAdapter arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, input);
                            search.setAdapter(arrayAdapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




        search.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {

                final int index = input.indexOf(search.getText().toString());

                if (index < 0) {
                    return;
                }

                    Bundle bundle = new Bundle();
                    ProductDetails fragment = new ProductDetails();
                    bundle.putString("name", input1.get(index));
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.frame_container, fragment).commit();

            }
        });


//        search.setDropDownBackgroundResource(R.color.colorWhite);




        if(!TextUtils.isEmpty(session.getextras())){
            if(session.getextras().equals("Orders"))
            {
                session.setextras("");
                Fragment fragment = new OrderHistoryFragment();
                FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();
            }
        }



        FirebaseDatabase.getInstance().getReference().child("AppContent").child("HomePage1").child("Baner")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(getContext()!=null) {
//                            Glide.with(getContext())
//                                    .load(dataSnapshot.child("Baner1").getValue().toString())
//                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                    .into(i1);
//
//                            Glide.with(getContext())
//                                    .load(dataSnapshot.child("Baner2").getValue().toString())
//                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                    .into(i2);
//
//                            Glide.with(getContext())
//                                    .load(dataSnapshot.child("Baner3").getValue().toString())
//                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                    .into(i3);
//
//                            Glide.with(getContext())
//                                    .load(dataSnapshot.child("Baner4").getValue().toString())
//                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                    .into(i4);

                            Glide.with(getContext())
                                    .load(dataSnapshot.child("Baner5").getValue().toString())
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(baner1);

                            Glide.with(getContext())
                                    .load(dataSnapshot.child("Baner6").getValue().toString())
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(baner2);
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
        });


        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.child("AppContent").child("HomePage").child("Carousel");
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



        FirebaseDatabase.getInstance().getReference().child("AppContent").child("HomePage2")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child("Product1").exists()){
                            if(getContext()!=null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product1").child("Image").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(x1);

                                xname1.setText(dataSnapshot.child("Product1").child("Name").getValue().toString());
                                xrewards1.setText(dataSnapshot.child("Product1").child("Desc").getValue().toString());
                                xprice1.setVisibility(View.GONE);
                                xpushid1.setText(dataSnapshot.child("Product1").child("PushId").getValue().toString());
                            }


                        }

                        if(dataSnapshot.child("Product2").exists()){
                            if(getContext()!=null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product2").child("Image").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(x2);

                                xname2.setText(dataSnapshot.child("Product2").child("Name").getValue().toString());
                                xrewards2.setText(dataSnapshot.child("Product2").child("Desc").getValue().toString());
                                xprice2.setVisibility(View.GONE);
                                xpushid2.setText(dataSnapshot.child("Product2").child("PushId").getValue().toString());
                            }


                        }


                        if(dataSnapshot.child("Product3").exists()){
                            if(getContext()!=null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product3").child("Image").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(x3);

                                xname3.setText(dataSnapshot.child("Product3").child("Name").getValue().toString());
                                xrewards3.setText( dataSnapshot.child("Product3").child("Desc").getValue().toString());
                                xprice3.setVisibility(View.GONE);
                                xpushid3.setText(dataSnapshot.child("Product3").child("PushId").getValue().toString());
                            }


                        }


                        if(dataSnapshot.child("Product4").exists()){
                            if(getContext()!=null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product4").child("Image").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(x4);

                                xname4.setText(dataSnapshot.child("Product4").child("Name").getValue().toString());
                                xrewards4.setText(dataSnapshot.child("Product4").child("Desc").getValue().toString());
                                xprice4.setVisibility(View.GONE);
                                xpushid4.setText(dataSnapshot.child("Product4").child("PushId").getValue().toString());
                            }


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


        FirebaseDatabase.getInstance().getReference().child("AppContent").child("HomePage1")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child("Product1").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product1").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(n1);

                                nname1.setText(dataSnapshot.child("Product1").child("Name").getValue().toString());
                                nprice1.setText("\u20b9 " + dataSnapshot.child("Product1").child("Price").getValue().toString());
                                nrewards1.setText(dataSnapshot.child("Product1").child("Cashback").getValue().toString() + " Rewards Points");
                                npushid1.setText(dataSnapshot.child("Product1").child("PushId").getValue().toString());
                            }


                        }

                        if (dataSnapshot.child("Product2").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product2").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(n2);

                                nname2.setText(dataSnapshot.child("Product2").child("Name").getValue().toString());
                                nprice2.setText("\u20b9 " + dataSnapshot.child("Product2").child("Price").getValue().toString());
                                nrewards2.setText(dataSnapshot.child("Product2").child("Cashback").getValue().toString() + " Rewards Points");
                                npushid2.setText(dataSnapshot.child("Product2").child("PushId").getValue().toString());
                            }


                        }


                        if (dataSnapshot.child("Product3").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product3").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(n3);

                                nname3.setText(dataSnapshot.child("Product3").child("Name").getValue().toString());
                                nprice3.setText("\u20b9 " + dataSnapshot.child("Product3").child("Price").getValue().toString());
                                nrewards3.setText(dataSnapshot.child("Product3").child("Cashback").getValue().toString() + " Rewards Points");
                                npushid3.setText(dataSnapshot.child("Product3").child("PushId").getValue().toString());
                            }


                        }


                        if (dataSnapshot.child("Product4").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product4").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(n4);

                                nname4.setText(dataSnapshot.child("Product4").child("Name").getValue().toString());
                                nprice4.setText("\u20b9 " + dataSnapshot.child("Product4").child("Price").getValue().toString());
                                nrewards4.setText(dataSnapshot.child("Product4").child("Cashback").getValue().toString() + " Rewards Points");
                                npushid4.setText(dataSnapshot.child("Product4").child("PushId").getValue().toString());
                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });


        FirebaseDatabase.getInstance().getReference().child("AppContent").child("HomePage1")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child("Product5").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product5").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(n5);

                                nname5.setText(dataSnapshot.child("Product5").child("Name").getValue().toString());
                                nprice5.setText("\u20b9 " + dataSnapshot.child("Product5").child("Price").getValue().toString());
                                nrewards5.setText(dataSnapshot.child("Product5").child("Cashback").getValue().toString() + " Rewards Points");
                                npushid5.setText(dataSnapshot.child("Product5").child("PushId").getValue().toString());
                            }


                        }

                        if (dataSnapshot.child("Product6").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product6").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(n6);

                                nname6.setText(dataSnapshot.child("Product6").child("Name").getValue().toString());
                                nprice6.setText("\u20b9 " + dataSnapshot.child("Product6").child("Price").getValue().toString());
                                nrewards6.setText(dataSnapshot.child("Product6").child("Cashback").getValue().toString() + " Rewards Points");
                                npushid6.setText(dataSnapshot.child("Product6").child("PushId").getValue().toString());
                            }


                        }


                        if (dataSnapshot.child("Product7").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product7").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(n7);

                                nname7.setText(dataSnapshot.child("Product7").child("Name").getValue().toString());
                                nprice7.setText("\u20b9 " + dataSnapshot.child("Product7").child("Price").getValue().toString());
                                nrewards7.setText(dataSnapshot.child("Product7").child("Cashback").getValue().toString() + " Rewards Points");
                                npushid7.setText(dataSnapshot.child("Product7").child("PushId").getValue().toString());
                            }


                        }


                        if (dataSnapshot.child("Product8").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product8").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(n8);

                                nname8.setText(dataSnapshot.child("Product8").child("Name").getValue().toString());
                                nprice8.setText("\u20b9 " + dataSnapshot.child("Product8").child("Price").getValue().toString());
                                nrewards8.setText(dataSnapshot.child("Product8").child("Cashback").getValue().toString() + " Rewards Points");
                                npushid8.setText(dataSnapshot.child("Product8").child("PushId").getValue().toString());
                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });


        FirebaseDatabase.getInstance().getReference().child("AppContent").child("HomePage1")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child("Product9").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product9").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(n9);

                                nname9.setText(dataSnapshot.child("Product9").child("Name").getValue().toString());
                                nprice9.setText("\u20b9 " + dataSnapshot.child("Product9").child("Price").getValue().toString());
                                nrewards9.setText(dataSnapshot.child("Product9").child("Cashback").getValue().toString() + " Rewards Points");
                                npushid9.setText(dataSnapshot.child("Product9").child("PushId").getValue().toString());
                            }


                        }

                        if (dataSnapshot.child("Product10").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product10").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(n10);

                                nname10.setText(dataSnapshot.child("Product10").child("Name").getValue().toString());
                                nprice10.setText("\u20b9 " + dataSnapshot.child("Product10").child("Price").getValue().toString());
                                nrewards10.setText(dataSnapshot.child("Product10").child("Cashback").getValue().toString() + " Rewards Points");
                                npushid10.setText(dataSnapshot.child("Product10").child("PushId").getValue().toString());
                            }


                        }


                        if (dataSnapshot.child("Product11").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product11").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(n11);

                                nname11.setText(dataSnapshot.child("Product11").child("Name").getValue().toString());
                                nprice11.setText("\u20b9 " + dataSnapshot.child("Product11").child("Price").getValue().toString());
                                nrewards11.setText(dataSnapshot.child("Product11").child("Cashback").getValue().toString() + " Rewards Points");
                                npushid11.setText(dataSnapshot.child("Product11").child("PushId").getValue().toString());
                            }


                        }


                        if (dataSnapshot.child("Product12").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product12").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(n12);

                                nname12.setText(dataSnapshot.child("Product12").child("Name").getValue().toString());
                                nprice12.setText("\u20b9 " + dataSnapshot.child("Product12").child("Price").getValue().toString());
                                nrewards12.setText(dataSnapshot.child("Product12").child("Cashback").getValue().toString() + " Rewards Points");
                                npushid12.setText(dataSnapshot.child("Product12").child("PushId").getValue().toString());
                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });


        FirebaseDatabase.getInstance().getReference().child("AppContent").child("HomePage1")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child("Product13").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product13").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(x5);

                                xname5.setText(dataSnapshot.child("Product13").child("Name").getValue().toString());
                                xprice5.setText("\u20b9 " + dataSnapshot.child("Product13").child("Price").getValue().toString());
                                xrewards5.setText(dataSnapshot.child("Product13").child("Cashback").getValue().toString() + " Rewards Points");
                                xpushid5.setText(dataSnapshot.child("Product13").child("PushId").getValue().toString());
                            }


                        }

                        if (dataSnapshot.child("Product14").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product14").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(x6);

                                xname6.setText(dataSnapshot.child("Product14").child("Name").getValue().toString());
                                xprice6.setText("\u20b9 " + dataSnapshot.child("Product14").child("Price").getValue().toString());
                                xrewards6.setText(dataSnapshot.child("Product14").child("Cashback").getValue().toString() + " Rewards Points");
                                xpushid6.setText(dataSnapshot.child("Product14").child("PushId").getValue().toString());
                            }


                        }


                        if (dataSnapshot.child("Product15").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product15").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(x7);

                                xname7.setText(dataSnapshot.child("Product15").child("Name").getValue().toString());
                                xprice7.setText("\u20b9 " + dataSnapshot.child("Product15").child("Price").getValue().toString());
                                xrewards7.setText(dataSnapshot.child("Product15").child("Cashback").getValue().toString() + " Rewards Points");
                                xpushid7.setText(dataSnapshot.child("Product15").child("PushId").getValue().toString());
                            }


                        }


                        if (dataSnapshot.child("Product16").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product16").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(x8);

                                xname8.setText(dataSnapshot.child("Product16").child("Name").getValue().toString());
                                xprice8.setText("\u20b9 " + dataSnapshot.child("Product16").child("Price").getValue().toString());
                                xrewards8.setText(dataSnapshot.child("Product16").child("Cashback").getValue().toString() + " Rewards Points");
                                xpushid8.setText(dataSnapshot.child("Product16").child("PushId").getValue().toString());
                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });


        FirebaseDatabase.getInstance().getReference().child("AppContent").child("HomePage1")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child("Product17").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product17").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(x9);

                                xname9.setText(dataSnapshot.child("Product17").child("Name").getValue().toString());
                                xprice9.setText("\u20b9 " + dataSnapshot.child("Product17").child("Price").getValue().toString());
                                xrewards9.setText(dataSnapshot.child("Product17").child("Cashback").getValue().toString() + " Rewards Points");
                                xpushid9.setText(dataSnapshot.child("Product17").child("PushId").getValue().toString());
                            }


                        }

                        if (dataSnapshot.child("Product18").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product18").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(x10);

                                xname10.setText(dataSnapshot.child("Product18").child("Name").getValue().toString());
                                xprice10.setText("\u20b9 " + dataSnapshot.child("Product18").child("Price").getValue().toString());
                                xrewards10.setText(dataSnapshot.child("Product18").child("Cashback").getValue().toString() + " Rewards Points");
                                xpushid10.setText(dataSnapshot.child("Product18").child("PushId").getValue().toString());
                            }


                        }


                        if (dataSnapshot.child("Product19").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product19").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(x11);

                                xname11.setText(dataSnapshot.child("Product19").child("Name").getValue().toString());
                                xprice11.setText("\u20b9 " + dataSnapshot.child("Product19").child("Price").getValue().toString());
                                xrewards11.setText(dataSnapshot.child("Product19").child("Cashback").getValue().toString() + " Rewards Points");
                                xpushid11.setText(dataSnapshot.child("Product19").child("PushId").getValue().toString());
                            }


                        }


                        if (dataSnapshot.child("Product20").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product20").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(x12);

                                xname12.setText(dataSnapshot.child("Product20").child("Name").getValue().toString());
                                xprice12.setText("\u20b9 " + dataSnapshot.child("Product20").child("Price").getValue().toString());
                                xrewards12.setText(dataSnapshot.child("Product20").child("Cashback").getValue().toString() + " Rewards Points");
                                xpushid12.setText(dataSnapshot.child("Product20").child("PushId").getValue().toString());
                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });


        FirebaseDatabase.getInstance().getReference().child("AppContent").child("HomePage1")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child("Product21").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product21").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(n13);

                                nname13.setText(dataSnapshot.child("Product21").child("Name").getValue().toString());
                                nprice13.setText("\u20b9 " + dataSnapshot.child("Product21").child("Price").getValue().toString());
                                nrewards13.setText(dataSnapshot.child("Product21").child("Cashback").getValue().toString() + " Rewards Points");
                                npushid13.setText(dataSnapshot.child("Product21").child("PushId").getValue().toString());
                            }


                        }

                        if (dataSnapshot.child("Product22").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product22").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(n14);

                                nname14.setText(dataSnapshot.child("Product22").child("Name").getValue().toString());
                                nprice14.setText("\u20b9 " + dataSnapshot.child("Product22").child("Price").getValue().toString());
                                nrewards14.setText(dataSnapshot.child("Product22").child("Cashback").getValue().toString() + " Rewards Points");
                                npushid14.setText(dataSnapshot.child("Product22").child("PushId").getValue().toString());
                            }


                        }


                        if (dataSnapshot.child("Product23").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product23").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(n15);

                                nname15.setText(dataSnapshot.child("Product23").child("Name").getValue().toString());
                                nprice15.setText("\u20b9 " + dataSnapshot.child("Product23").child("Price").getValue().toString());
                                nrewards15.setText(dataSnapshot.child("Product23").child("Cashback").getValue().toString() + " Rewards Points");
                                npushid15.setText(dataSnapshot.child("Product23").child("PushId").getValue().toString());
                            }


                        }


                        if (dataSnapshot.child("Product24").exists()) {
                            if (getContext() != null) {
                                Glide.with(getContext())
                                        .load(dataSnapshot.child("Product24").child("Image1").getValue().toString())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(n16);

                                nname16.setText(dataSnapshot.child("Product24").child("Name").getValue().toString());
                                nprice16.setText("\u20b9 " + dataSnapshot.child("Product24").child("Price").getValue().toString());
                                nrewards16.setText(dataSnapshot.child("Product24").child("Cashback").getValue().toString() + " Rewards Points");
                                npushid16.setText(dataSnapshot.child("Product24").child("PushId").getValue().toString());
                            }

                            progressBar.setVisibility(View.GONE);


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });



        h1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new MobileFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        h2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new AccessoriesFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });

        h3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new ComputerFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        h4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new FashionFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();


            }
        });


        h5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new HomeappliancesFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        h6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new Grocery();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        hsamsung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fragment=new MobileDetails();
                session.setsub("SAMSUNG");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container,fragment).commit();

            }
        });


        hmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment=new MobileDetails();
                session.setsub("MI");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container,fragment).commit();

            }
        });

        hoppo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment=new MobileDetails();
                session.setsub("OPPO");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container,fragment).commit();

            }
        });

        hnokia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment=new MobileDetails();
                session.setsub("NOKIA");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container,fragment).commit();

            }
        });

        hvivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment=new MobileDetails();
                session.setsub("VIVO");
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


        l5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",npushid5.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        l6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",npushid6.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        l7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",npushid7.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        l8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",npushid8.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        l9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",npushid9.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        l10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",npushid10.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        l11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",npushid11.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        l12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",npushid12.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        l13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",npushid13.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        l14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",npushid14.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        l15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",npushid15.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        l16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",npushid16.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        z1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment= new Grocery();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        z2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment= new Grocery();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        z3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment= new Grocery();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        z4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment= new Grocery();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        z5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",xpushid5.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        z6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",xpushid6.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        z7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",xpushid7.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        z8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",xpushid8.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        z9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",xpushid9.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        z10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",xpushid10.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        z11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",xpushid11.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        z12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                fragment= new ProductDetails();

                bundle.putString("name",xpushid12.getText().toString());
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


    @Override
    public void onResume(){
        super.onResume();
        search.setText("");


    }

}
