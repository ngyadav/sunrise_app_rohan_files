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


public class FashionDetails extends Fragment {


    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    Session session;
    String a[];
    ImageView hSaree,hWatches,hOthers,hmens,hkids,hwomen;


    public FashionDetails() {
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
        View v=inflater.inflate(R.layout.fragment_fashion_details, container, false);

        Toolbar toolbar=(getActivity()).findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        LinearLayout bottomnavigation=(getActivity()).findViewById(R.id.bottomnavigation);
        bottomnavigation.setVisibility(View.GONE);

        hSaree=v.findViewById(R.id.hsaree);
        hWatches=v.findViewById(R.id.hwatches);
        hOthers=v.findViewById(R.id.hothers);
        hmens=v.findViewById(R.id.hmens);
        hwomen=v.findViewById(R.id.hwomens);
        hkids=v.findViewById(R.id.hkids);
        mRecyclerView = v.findViewById(R.id.recyclerviewfashion);

        //        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mref = mFirebaseDatabase.getReference("CategoryNew");
        session=new Session(getContext());


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


        hSaree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Fashion SAREE");


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


        hmens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Fashion MENSFASHION");


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


        hwomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Fashion WOMENSFASHION");


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


        hkids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Fashion KIDSFASHION");

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


        hWatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Fashion WATCHES");

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

                Query firebasequery=mref.orderByChild("CategoryType").equalTo("Fashion OTHERS");



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

        Query firebasequery=mref.orderByChild("CategoryType").equalTo("Fashion SAREE");

        if(!TextUtils.isEmpty(session.getsub()))
        {
            firebasequery=mref.orderByChild("CategoryType").equalTo(session.getsub());
        }
        else if(!TextUtils.isEmpty(session.getrange())){
            a=session.getrange().split(",");
            firebasequery = mref.orderByChild("Category").equalTo("Fashion");
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