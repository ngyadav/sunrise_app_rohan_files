package rohan.groups.sunrise.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.Products.Products;
import rohan.groups.sunrise.Products.ViewHolder;
import rohan.groups.sunrise.R;


public class AccesoriesDetails extends Fragment {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    Session session;
    Fragment fragment;

    String a[];

    ImageView hCharger,hBluetooth,hCable,hPowerbank,hConnectors,hPouches,hTemperedGlass,hOthers,hBattery,hEarphone,hPendrive,hMemorycard;

    public AccesoriesDetails() {
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
        View v=inflater.inflate(R.layout.fragment_accesories_details, container, false);

        Toolbar toolbar=(getActivity()).findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        LinearLayout bottomnavigation=(getActivity()).findViewById(R.id.bottomnavigation);
        bottomnavigation.setVisibility(View.GONE);

        hCharger=(ImageView)v.findViewById(R.id.hcharger);
        hBattery=(ImageView)v.findViewById(R.id.hbattery);
        hEarphone=(ImageView)v.findViewById(R.id.hearphone);
        hBluetooth=(ImageView)v.findViewById(R.id.hbluetooth);
        hCable=(ImageView)v.findViewById(R.id.hcable);
        hPowerbank=(ImageView)v.findViewById(R.id.hpowerbank);
        hConnectors=(ImageView)v.findViewById(R.id.hconnectors);
        hPouches=(ImageView)v.findViewById(R.id.hpouches);
        hTemperedGlass=(ImageView)v.findViewById(R.id.hglass);
        hOthers=(ImageView)v.findViewById(R.id.hothers);
        hMemorycard=(ImageView)v.findViewById(R.id.hmemorycard);
        hPendrive=(ImageView)v.findViewById(R.id.hpendrive);
        mRecyclerView = v.findViewById(R.id.recyclerviewaccesories);

        session=new Session(getContext());

//        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mref = mFirebaseDatabase.getReference("CategoryNew");


        ImageView search = v.findViewById(R.id.search);
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


        hPendrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Accesories PENDRIVE");



                FirebaseRecyclerAdapter<Products, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Products, ViewHolder>(
                                Products.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Products profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1, profile.Category, profile.CategoryName, profile.Feature1,profile.PushId);

                            }

                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView Products_name = (TextView) v.findViewById(R.id.mobile_nameTV);
                                        TextView pushid = (TextView) v.findViewById(R.id.pushid);

                                        Bundle bundle = new Bundle();
                                        ProductDetails fragment = new ProductDetails();

                                        bundle.putString("name", pushid.getText().toString());
                                        fragment.setArguments(bundle);
                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                        fragmentManager.beginTransaction()
                                                .addToBackStack(null)
                                                .replace(R.id.frame_container, fragment).commit();
                                    }

                                    @Override
                                    public void onItemLongClick(View v, int position) {

                                    }
                                });
                                return viewHolder;
                            }

                            @Override
                            public void onDataChanged() {

                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });


        hMemorycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Accesories MEMORYCARD");


                FirebaseRecyclerAdapter<Products, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Products, ViewHolder>(
                                Products.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Products profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1, profile.Category, profile.CategoryName, profile.Feature1,profile.PushId);

                            }

                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView Products_name = (TextView) v.findViewById(R.id.mobile_nameTV);
                                        TextView pushid = (TextView) v.findViewById(R.id.pushid);

                                        Bundle bundle = new Bundle();
                                        ProductDetails fragment = new ProductDetails();

                                        bundle.putString("name", pushid.getText().toString());
                                        fragment.setArguments(bundle);
                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                        fragmentManager.beginTransaction()
                                                .addToBackStack(null)
                                                .replace(R.id.frame_container, fragment).commit();
                                    }

                                    @Override
                                    public void onItemLongClick(View v, int position) {

                                    }
                                });
                                return viewHolder;
                            }

                            @Override
                            public void onDataChanged() {

                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });


        hCharger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Accesories CHARGER");



                FirebaseRecyclerAdapter<Products, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Products, ViewHolder>(
                                Products.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Products profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1, profile.Category, profile.CategoryName, profile.Feature1,profile.PushId);

                            }

                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView Products_name = (TextView) v.findViewById(R.id.mobile_nameTV);
                                        TextView pushid = (TextView) v.findViewById(R.id.pushid);

                                        Bundle bundle = new Bundle();
                                        ProductDetails fragment = new ProductDetails();

                                        bundle.putString("name", pushid.getText().toString());
                                        fragment.setArguments(bundle);
                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                        fragmentManager.beginTransaction()
                                                .addToBackStack(null)
                                                .replace(R.id.frame_container, fragment).commit();
                                    }

                                    @Override
                                    public void onItemLongClick(View v, int position) {

                                    }
                                });
                                return viewHolder;
                            }

                            @Override
                            public void onDataChanged() {

                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });


        hEarphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Accesories EARPHONE");




                FirebaseRecyclerAdapter<Products, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Products, ViewHolder>(
                                Products.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Products profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1, profile.Category, profile.CategoryName, profile.Feature1,profile.PushId);

                            }

                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView Products_name = (TextView) v.findViewById(R.id.mobile_nameTV);
                                        TextView pushid = (TextView) v.findViewById(R.id.pushid);

                                        Bundle bundle = new Bundle();
                                        ProductDetails fragment = new ProductDetails();

                                        bundle.putString("name", pushid.getText().toString());
                                        fragment.setArguments(bundle);
                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                        fragmentManager.beginTransaction()
                                                .addToBackStack(null)
                                                .replace(R.id.frame_container, fragment).commit();
                                    }

                                    @Override
                                    public void onItemLongClick(View v, int position) {

                                    }
                                });
                                return viewHolder;
                            }

                            @Override
                            public void onDataChanged() {

                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });


        hBattery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Accesories BATTERY");



                FirebaseRecyclerAdapter<Products, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Products, ViewHolder>(
                                Products.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Products profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1, profile.Category, profile.CategoryName, profile.Feature1,profile.PushId);

                            }

                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView Products_name = (TextView) v.findViewById(R.id.mobile_nameTV);
                                        TextView pushid = (TextView) v.findViewById(R.id.pushid);

                                        Bundle bundle = new Bundle();
                                        ProductDetails fragment = new ProductDetails();

                                        bundle.putString("name", pushid.getText().toString());
                                        fragment.setArguments(bundle);
                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                        fragmentManager.beginTransaction()
                                                .addToBackStack(null)
                                                .replace(R.id.frame_container, fragment).commit();
                                    }

                                    @Override
                                    public void onItemLongClick(View v, int position) {

                                    }
                                });
                                return viewHolder;
                            }

                            @Override
                            public void onDataChanged() {

                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });



        hBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Accesories BLUETOOTH");


                FirebaseRecyclerAdapter<Products, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Products, ViewHolder>(
                                Products.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Products profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1, profile.Category, profile.CategoryName, profile.Feature1,profile.PushId);

                            }

                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView Products_name = (TextView) v.findViewById(R.id.mobile_nameTV);
                                        TextView pushid = (TextView) v.findViewById(R.id.pushid);

                                        Bundle bundle = new Bundle();
                                        ProductDetails fragment = new ProductDetails();

                                        bundle.putString("name", pushid.getText().toString());
                                        fragment.setArguments(bundle);
                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                        fragmentManager.beginTransaction()
                                                .addToBackStack(null)
                                                .replace(R.id.frame_container, fragment).commit();
                                    }

                                    @Override
                                    public void onItemLongClick(View v, int position) {

                                    }
                                });
                                return viewHolder;
                            }

                            @Override
                            public void onDataChanged() {

                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });


        hCable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Accesories CABLES");


                FirebaseRecyclerAdapter<Products, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Products, ViewHolder>(
                                Products.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Products profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1, profile.Category, profile.CategoryName, profile.Feature1,profile.PushId);

                            }

                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView Products_name = (TextView) v.findViewById(R.id.mobile_nameTV);
                                        TextView pushid = (TextView) v.findViewById(R.id.pushid);

                                        Bundle bundle = new Bundle();
                                        ProductDetails fragment = new ProductDetails();

                                        bundle.putString("name", pushid.getText().toString());
                                        fragment.setArguments(bundle);
                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                        fragmentManager.beginTransaction()
                                                .addToBackStack(null)
                                                .replace(R.id.frame_container, fragment).commit();
                                    }

                                    @Override
                                    public void onItemLongClick(View v, int position) {

                                    }
                                });
                                return viewHolder;
                            }

                            @Override
                            public void onDataChanged() {

                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });




        hPowerbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Accesories POWERBANK");


                FirebaseRecyclerAdapter<Products, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Products, ViewHolder>(
                                Products.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Products profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1, profile.Category, profile.CategoryName, profile.Feature1,profile.PushId);

                            }

                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView Products_name = (TextView) v.findViewById(R.id.mobile_nameTV);
                                        TextView pushid = (TextView) v.findViewById(R.id.pushid);

                                        Bundle bundle = new Bundle();
                                        ProductDetails fragment = new ProductDetails();

                                        bundle.putString("name", pushid.getText().toString());
                                        fragment.setArguments(bundle);
                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                        fragmentManager.beginTransaction()
                                                .addToBackStack(null)
                                                .replace(R.id.frame_container, fragment).commit();
                                    }

                                    @Override
                                    public void onItemLongClick(View v, int position) {

                                    }
                                });
                                return viewHolder;
                            }

                            @Override
                            public void onDataChanged() {

                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });


        hConnectors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Accesories CONNECTORS");

                FirebaseRecyclerAdapter<Products, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Products, ViewHolder>(
                                Products.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Products profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1, profile.Category, profile.CategoryName, profile.Feature1,profile.PushId);

                            }

                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView Products_name = (TextView) v.findViewById(R.id.mobile_nameTV);
                                        TextView pushid = (TextView) v.findViewById(R.id.pushid);

                                        Bundle bundle = new Bundle();
                                        ProductDetails fragment = new ProductDetails();

                                        bundle.putString("name", pushid.getText().toString());
                                        fragment.setArguments(bundle);
                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                        fragmentManager.beginTransaction()
                                                .addToBackStack(null)
                                                .replace(R.id.frame_container, fragment).commit();
                                    }

                                    @Override
                                    public void onItemLongClick(View v, int position) {

                                    }
                                });
                                return viewHolder;
                            }

                            @Override
                            public void onDataChanged() {

                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });


        hPouches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Accesories POUCHES");


                FirebaseRecyclerAdapter<Products, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Products, ViewHolder>(
                                Products.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Products profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1, profile.Category, profile.CategoryName, profile.Feature1,profile.PushId);

                            }

                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView Products_name = (TextView) v.findViewById(R.id.mobile_nameTV);
                                        TextView pushid = (TextView) v.findViewById(R.id.pushid);

                                        Bundle bundle = new Bundle();
                                        ProductDetails fragment = new ProductDetails();

                                        bundle.putString("name", pushid.getText().toString());
                                        fragment.setArguments(bundle);
                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                        fragmentManager.beginTransaction()
                                                .addToBackStack(null)
                                                .replace(R.id.frame_container, fragment).commit();
                                    }

                                    @Override
                                    public void onItemLongClick(View v, int position) {

                                    }
                                });
                                return viewHolder;
                            }

                            @Override
                            public void onDataChanged() {

                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });


        hTemperedGlass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Accesories TEMPEREDGLASS");


                FirebaseRecyclerAdapter<Products, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Products, ViewHolder>(
                                Products.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Products profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1, profile.Category, profile.CategoryName, profile.Feature1,profile.PushId);

                            }

                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView Products_name = (TextView) v.findViewById(R.id.mobile_nameTV);
                                        TextView pushid = (TextView) v.findViewById(R.id.pushid);

                                        Bundle bundle = new Bundle();
                                        ProductDetails fragment = new ProductDetails();

                                        bundle.putString("name", pushid.getText().toString());
                                        fragment.setArguments(bundle);
                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                        fragmentManager.beginTransaction()
                                                .addToBackStack(null)
                                                .replace(R.id.frame_container, fragment).commit();
                                    }

                                    @Override
                                    public void onItemLongClick(View v, int position) {

                                    }
                                });
                                return viewHolder;
                            }

                            @Override
                            public void onDataChanged() {

                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });

        hOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Accesories OTHERS");


                FirebaseRecyclerAdapter<Products, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Products, ViewHolder>(
                                Products.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Products profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1, profile.Category, profile.CategoryName, profile.Feature1,profile.PushId);

                            }

                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView Products_name = (TextView) v.findViewById(R.id.mobile_nameTV);
                                        TextView pushid = (TextView) v.findViewById(R.id.pushid);

                                        Bundle bundle = new Bundle();
                                        ProductDetails fragment = new ProductDetails();

                                        bundle.putString("name", pushid.getText().toString());
                                        fragment.setArguments(bundle);
                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                        fragmentManager.beginTransaction()
                                                .addToBackStack(null)
                                                .replace(R.id.frame_container, fragment).commit();
                                    }

                                    @Override
                                    public void onItemLongClick(View v, int position) {

                                    }
                                });
                                return viewHolder;
                            }

                            @Override
                            public void onDataChanged() {

                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        Query firebasequery=mref.orderByChild("CategoryType").equalTo("Accesories PENDRIVE");

        if(!TextUtils.isEmpty(session.getsub()))
        {
            firebasequery=mref.orderByChild("CategoryType").equalTo(session.getsub());
        }
        else if(!TextUtils.isEmpty(session.getrange())){
            a=session.getrange().split(",");
            firebasequery = mref.orderByChild("Category").equalTo("Accesories");
        }


        FirebaseRecyclerAdapter<Products, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Products, ViewHolder>(
                        Products.class,
                        R.layout.cardview_mobile,
                        ViewHolder.class,
                        firebasequery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Products profile, int position) {
                        if(TextUtils.isEmpty(session.getrange())) {
                            viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1, profile.Category, profile.CategoryName, profile.Feature1, profile.PushId);
                        }
                        else{
                            viewHolder.setDetails1(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1, profile.Category, profile.CategoryName, profile.Feature1, profile.PushId,a[0],a[1]);
                        }
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {
                                TextView Products_name = (TextView) v.findViewById(R.id.mobile_nameTV);
                                TextView pushid = (TextView) v.findViewById(R.id.pushid);

                                Bundle bundle = new Bundle();
                                ProductDetails fragment = new ProductDetails();

                                bundle.putString("name", pushid.getText().toString());
                                fragment.setArguments(bundle);
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                fragmentManager.beginTransaction()
                                        .addToBackStack(null)
                                        .replace(R.id.frame_container, fragment).commit();
                            }

                            @Override
                            public void onItemLongClick(View v, int position) {

                            }
                        });
                        return viewHolder;
                    }

                    @Override
                    public void onDataChanged() {

                    }

                };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);


    }
}