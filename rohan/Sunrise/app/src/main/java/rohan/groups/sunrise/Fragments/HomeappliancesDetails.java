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


public class HomeappliancesDetails extends Fragment {


    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    Session session;
    String a[];
    ImageView htv,hwashing,hfridge,hkitchen,hothers,hremote,hmixi,hdth,hcooler,hfans,hiron;

    public HomeappliancesDetails() {
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
        View v=inflater.inflate(R.layout.fragment_homeappliances_details, container, false);

        Toolbar toolbar=(getActivity()).findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        LinearLayout bottomnavigation=(getActivity()).findViewById(R.id.bottomnavigation);
        bottomnavigation.setVisibility(View.GONE);

        htv=(ImageView)v.findViewById(R.id.htv);
        hwashing=(ImageView)v.findViewById(R.id.hwashing);
        hfridge=(ImageView)v.findViewById(R.id.hrefrigarator);
        hkitchen=(ImageView)v.findViewById(R.id.hkitchen);
        hothers=(ImageView)v.findViewById(R.id.hothers);
        hremote=(ImageView)v.findViewById(R.id.hremote);
        hmixi=(ImageView)v.findViewById(R.id.hmixi);
        hdth=(ImageView)v.findViewById(R.id.hdth);
        hcooler=(ImageView)v.findViewById(R.id.hcooler);
        hfans=(ImageView)v.findViewById(R.id.hfans);
        hiron=(ImageView)v.findViewById(R.id.hiron);
        mRecyclerView = v.findViewById(R.id.recyclerviewhomeappliances);
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


        htv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("HomeAppliances TELEVISION");

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


        hwashing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("HomeAppliances WASHING MACHINE");
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


        hfridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("HomeAppliances REFRIGERATOR");

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



        hdth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("HomeAppliances DTH");


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

        hiron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("HomeAppliances IRON");

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


        hfans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("HomeAppliances FANS");

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


        hcooler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("HomeAppliances COOLERS");

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


        hmixi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("HomeAppliances MIXI");

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


        hremote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("HomeAppliances REMOTE");

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


        hkitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("HomeAppliances KITCHEN");


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


        hothers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("HomeAppliances OTHERS");


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

        Query firebasequery=mref.orderByChild("CategoryType").equalTo("HomeAppliances TELEVISION");

        if(!TextUtils.isEmpty(session.getsub()))
        {
            firebasequery=mref.orderByChild("CategoryType").equalTo(session.getsub());
        }
        else if(!TextUtils.isEmpty(session.getrange())){
            a=session.getrange().split(",");
            firebasequery = mref.orderByChild("Category").equalTo("HomeAppliances");
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