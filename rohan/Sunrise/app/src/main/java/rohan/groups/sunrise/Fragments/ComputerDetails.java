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

public class ComputerDetails extends Fragment {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    Session session;
    String a[];
    ImageView hMonitors,hCpu,hLaptops,hSpeakers,hAccesories,hOthers;


    public ComputerDetails() {
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
        View v=inflater.inflate(R.layout.fragment_computer_details, container, false);

        Toolbar toolbar=(getActivity()).findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        LinearLayout bottomnavigation=(getActivity()).findViewById(R.id.bottomnavigation);
        bottomnavigation.setVisibility(View.GONE);


        hMonitors=(ImageView)v.findViewById(R.id.hmonitor);
        hCpu=(ImageView)v.findViewById(R.id.hcpu);
        hLaptops=(ImageView)v.findViewById(R.id.hlaptop);
        hSpeakers=(ImageView)v.findViewById(R.id.hspeaker);
        hAccesories=(ImageView)v.findViewById(R.id.hcaccesories);
        hOthers=(ImageView)v.findViewById(R.id.hothers);
        mRecyclerView = v.findViewById(R.id.recyclerviewcomputer);
        session=new Session(getContext());

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


        hMonitors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Computer MONITORS");


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


        hCpu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Computer CPU");


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


        hLaptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Computer LAPTOPS");


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


        hSpeakers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Computer SPEAKERS");

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


        hAccesories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Computer ACCESORIES");

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
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Computer OTHERS");

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

        Query firebasequery=mref.orderByChild("CategoryType").equalTo("Computer MONITORS");

        if(!TextUtils.isEmpty(session.getsub()))
        {
            firebasequery=mref.orderByChild("CategoryType").equalTo(session.getsub());
        }
        else if(!TextUtils.isEmpty(session.getrange())){
            a=session.getrange().split(",");
            firebasequery = mref.orderByChild("Category").equalTo("Computer");
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