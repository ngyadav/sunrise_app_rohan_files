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
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.Products.Products;
import rohan.groups.sunrise.Products.ViewHolder;
import rohan.groups.sunrise.R;

public class MobileDetails extends Fragment {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    Session session;
    DatabaseReference mref;
    SearchView search;
    String a[];
    Fragment fragment;
    ImageView hmi, happle, hsamsung, hhonor, hoppo, hvivo, hkarbon, hlava, hitel, hother, hnokia;

    public MobileDetails() {
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
        View v = inflater.inflate(R.layout.fragment_mobile_details, container, false);

        Toolbar toolbar=(getActivity()).findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        LinearLayout bottomnavigation=(getActivity()).findViewById(R.id.bottomnavigation);
        bottomnavigation.setVisibility(View.GONE);

        hmi = (ImageView) v.findViewById(R.id.hmi);
        hsamsung = (ImageView) v.findViewById(R.id.hsamsung);
        happle = (ImageView) v.findViewById(R.id.happle);
        hhonor = (ImageView) v.findViewById(R.id.hhonor);
        hoppo = (ImageView) v.findViewById(R.id.hoppo);
        hvivo = (ImageView) v.findViewById(R.id.hvivo);
        hkarbon = (ImageView) v.findViewById(R.id.hkarbon);
        hlava = (ImageView) v.findViewById(R.id.hlava);
        hitel = (ImageView) v.findViewById(R.id.hitel);
        hother = (ImageView) v.findViewById(R.id.hothers);
        hnokia = (ImageView) v.findViewById(R.id.hnokia);

        session = new Session(getContext());

        mRecyclerView = v.findViewById(R.id.recyclerviewmobile);

        LinearLayoutManager layoutmanager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutmanager);
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

        hmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Query firebasequery=mref.orderByChild("Brand").equalTo("MI");
                session.setsub("MI");

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



        hnokia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Query firebasequery=mref.orderByChild("Brand").equalTo("NOKIA");
                session.setsub("NOKIA");

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


        happle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Query firebasequery=mref.orderByChild("Brand").equalTo("APPLE");
                session.setsub("APPLE");

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


        hsamsung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Query firebasequery=mref.orderByChild("Brand").equalTo("SAMSUNG");
                session.setsub("SAMSUNG");

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


        hoppo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Query firebasequery=mref.orderByChild("Brand").equalTo("OPPO");
                session.setsub("OPPO");

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


        hhonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Query firebasequery=mref.orderByChild("Brand").equalTo("HONOR");
                session.setsub("HONOR");

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


        hvivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Query firebasequery=mref.orderByChild("Brand").equalTo("VIVO");
                session.setsub("VIVO");

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


        hkarbon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Query firebasequery=mref.orderByChild("Brand").equalTo("KARBON");
                session.setsub("KARBON");

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


        hlava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Query firebasequery=mref.orderByChild("Brand").equalTo("LAVA");
                session.setsub("LAVA");

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


        hother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Query firebasequery=mref.orderByChild("Brand").equalTo("OTHERS");
                session.setsub("OTHERS");

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

        Query firebasequery = mref.orderByChild("Category").equalTo("Mobile");

        if (!TextUtils.isEmpty(session.getsub())) {
            firebasequery = mref.orderByChild("Brand").equalTo(session.getsub());
        }
        else if(!TextUtils.isEmpty(session.getrange())){
            a=session.getrange().split(",");
            firebasequery = mref.orderByChild("Category").equalTo("Mobile");
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