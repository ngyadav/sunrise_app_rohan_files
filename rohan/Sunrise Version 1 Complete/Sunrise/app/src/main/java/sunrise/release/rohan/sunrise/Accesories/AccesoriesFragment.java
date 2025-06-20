package sunrise.release.rohan.sunrise.Accesories;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import sunrise.release.rohan.sunrise.Cart.CartFragment;
import sunrise.release.rohan.sunrise.Functionality.LoginSession;
import sunrise.release.rohan.sunrise.R;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import sunrise.release.rohan.sunrise.Accesories.ViewHolder;

public class AccesoriesFragment extends Fragment {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    LoginSession session;
    Fragment fragment;
    ImageView hCharger,hBluetooth,hCable,hPowerbank,hConnectors,hPouches,hTemperedGlass,hOthers,hBattery,hEarphone,hPendrive,hMemorycard;
    private ShimmerFrameLayout shimmer;
    public AccesoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_accesories, container, false);
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
        shimmer=v.findViewById(R.id.shimmer);
        mRecyclerView.setHasFixedSize(true);

        session=new LoginSession(getContext());

//        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mref = mFirebaseDatabase.getReference("Category");


        hMemorycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.setVisibility(View.GONE);
                shimmer.setVisibility(View.VISIBLE);
                shimmer.startShimmer();
                session.setsub("Accesories MEMORYCARD");
                Query firebasequery = mref.orderByChild("CategoryType").equalTo("Accesories MEMORYCARD");
                FirebaseRecyclerAdapter<Accesories, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Accesories, ViewHolder>(
                                Accesories.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Accesories profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1,profile.Category,profile.CategoryName,profile.Feature1);
                            }
                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView mobile_name=(TextView)v.findViewById(R.id.mobile_nameTV);

                                        String a=mobile_name.getText().toString();


                                        Bundle bundle=new Bundle();
                                        AccesoriesDetails fragment= new AccesoriesDetails();

                                        bundle.putString("name",a);
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
                                mRecyclerView.setVisibility(View.VISIBLE);
                                shimmer.stopShimmer();
                                shimmer.setVisibility(View.GONE);
                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });


        hPendrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.setVisibility(View.GONE);
                shimmer.setVisibility(View.VISIBLE);
                shimmer.startShimmer();
                session.setsub("Accesories PENDRIVE");
                Query firebasequery = mref.orderByChild("CategoryType").equalTo("Accesories PENDRIVE");
                FirebaseRecyclerAdapter<Accesories, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Accesories, ViewHolder>(
                                Accesories.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Accesories profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1,profile.Category,profile.CategoryName,profile.Feature1);
                            }
                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView mobile_name=(TextView)v.findViewById(R.id.mobile_nameTV);

                                        String a=mobile_name.getText().toString();


                                        Bundle bundle=new Bundle();
                                        AccesoriesDetails fragment= new AccesoriesDetails();

                                        bundle.putString("name",a);
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
                                mRecyclerView.setVisibility(View.VISIBLE);
                                shimmer.stopShimmer();
                                shimmer.setVisibility(View.GONE);
                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });


        hBattery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.setVisibility(View.GONE);
                shimmer.setVisibility(View.VISIBLE);
                shimmer.startShimmer();
                session.setsub("Accesories BATTERY");
                Query firebasequery = mref.orderByChild("CategoryType").equalTo("Accesories BATTERY");
                FirebaseRecyclerAdapter<Accesories, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Accesories, ViewHolder>(
                                Accesories.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Accesories profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1,profile.Category,profile.CategoryName,profile.Feature1);
                            }
                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView mobile_name=(TextView)v.findViewById(R.id.mobile_nameTV);

                                        String a=mobile_name.getText().toString();


                                        Bundle bundle=new Bundle();
                                        AccesoriesDetails fragment= new AccesoriesDetails();

                                        bundle.putString("name",a);
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
                                mRecyclerView.setVisibility(View.VISIBLE);
                                shimmer.stopShimmer();
                                shimmer.setVisibility(View.GONE);
                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });

        hEarphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.setVisibility(View.GONE);
                shimmer.setVisibility(View.VISIBLE);
                shimmer.startShimmer();
                session.setsub("Accesories EARPHONE");
                Query firebasequery = mref.orderByChild("CategoryType").equalTo("Accesories EARPHONE");
                FirebaseRecyclerAdapter<Accesories, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Accesories, ViewHolder>(
                                Accesories.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Accesories profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1,profile.Category,profile.CategoryName,profile.Feature1);
                            }
                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView mobile_name=(TextView)v.findViewById(R.id.mobile_nameTV);

                                        String a=mobile_name.getText().toString();


                                        Bundle bundle=new Bundle();
                                        AccesoriesDetails fragment= new AccesoriesDetails();

                                        bundle.putString("name",a);
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
                                mRecyclerView.setVisibility(View.VISIBLE);
                                shimmer.stopShimmer();
                                shimmer.setVisibility(View.GONE);
                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });

        hCharger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.setVisibility(View.GONE);
                shimmer.setVisibility(View.VISIBLE);
                shimmer.startShimmer();
                session.setsub("Accesories CHARGER");
                Query firebasequery = mref.orderByChild("CategoryType").equalTo("Accesories CHARGER");
                FirebaseRecyclerAdapter<Accesories, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Accesories, ViewHolder>(
                                Accesories.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Accesories profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1,profile.Category,profile.CategoryName,profile.Feature1);
                            }
                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView mobile_name=(TextView)v.findViewById(R.id.mobile_nameTV);

                                        String a=mobile_name.getText().toString();


                                        Bundle bundle=new Bundle();
                                        AccesoriesDetails fragment= new AccesoriesDetails();

                                        bundle.putString("name",a);
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
                                mRecyclerView.setVisibility(View.VISIBLE);
                                shimmer.stopShimmer();
                                shimmer.setVisibility(View.GONE);
                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });

        hBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.setVisibility(View.GONE);
                shimmer.setVisibility(View.VISIBLE);
                shimmer.startShimmer();
                session.setsub("Accesories BLUETOOTH");
                Query firebasequery = mref.orderByChild("CategoryType").equalTo("Accesories BLUETOOTH");
                FirebaseRecyclerAdapter<Accesories, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Accesories, ViewHolder>(
                                Accesories.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Accesories profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1,profile.Category,profile.CategoryName,profile.Feature1);
                            }
                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView mobile_name=(TextView)v.findViewById(R.id.mobile_nameTV);

                                        String a=mobile_name.getText().toString();


                                        Bundle bundle=new Bundle();
                                        AccesoriesDetails fragment= new AccesoriesDetails();

                                        bundle.putString("name",a);
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
                                mRecyclerView.setVisibility(View.VISIBLE);
                                shimmer.stopShimmer();
                                shimmer.setVisibility(View.GONE);
                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });


        hCable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.setVisibility(View.GONE);
                shimmer.setVisibility(View.VISIBLE);
                shimmer.startShimmer();
                session.setsub("Accesories CABLES");
                Query firebasequery = mref.orderByChild("CategoryType").equalTo("Accesories CABLES");
                FirebaseRecyclerAdapter<Accesories, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Accesories, ViewHolder>(
                                Accesories.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Accesories profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1,profile.Category,profile.CategoryName,profile.Feature1);
                            }
                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView mobile_name=(TextView)v.findViewById(R.id.mobile_nameTV);

                                        String a=mobile_name.getText().toString();


                                        Bundle bundle=new Bundle();
                                        AccesoriesDetails fragment= new AccesoriesDetails();

                                        bundle.putString("name",a);
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
                                mRecyclerView.setVisibility(View.VISIBLE);
                                shimmer.stopShimmer();
                                shimmer.setVisibility(View.GONE);
                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });


        hPowerbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.setVisibility(View.GONE);
                shimmer.setVisibility(View.VISIBLE);
                shimmer.startShimmer();
                session.setsub("Accesories POWERBANK");
                Query firebasequery = mref.orderByChild("CategoryType").equalTo("Accesories POWERBANK");
                FirebaseRecyclerAdapter<Accesories, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Accesories, ViewHolder>(
                                Accesories.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Accesories profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1,profile.Category,profile.CategoryName,profile.Feature1);
                            }
                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView mobile_name=(TextView)v.findViewById(R.id.mobile_nameTV);

                                        String a=mobile_name.getText().toString();


                                        Bundle bundle=new Bundle();
                                        AccesoriesDetails fragment= new AccesoriesDetails();

                                        bundle.putString("name",a);
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
                                mRecyclerView.setVisibility(View.VISIBLE);
                                shimmer.stopShimmer();
                                shimmer.setVisibility(View.GONE);
                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });


        hConnectors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.setVisibility(View.GONE);
                shimmer.setVisibility(View.VISIBLE);
                shimmer.startShimmer();
                session.setsub("Accesories CONNECTORS");
                Query firebasequery = mref.orderByChild("CategoryType").equalTo("Accesories CONNECTORS");
                FirebaseRecyclerAdapter<Accesories, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Accesories, ViewHolder>(
                                Accesories.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Accesories profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1,profile.Category,profile.CategoryName,profile.Feature1);
                            }
                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView mobile_name=(TextView)v.findViewById(R.id.mobile_nameTV);

                                        String a=mobile_name.getText().toString();


                                        Bundle bundle=new Bundle();
                                        AccesoriesDetails fragment= new AccesoriesDetails();

                                        bundle.putString("name",a);
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
                                mRecyclerView.setVisibility(View.VISIBLE);
                                shimmer.stopShimmer();
                                shimmer.setVisibility(View.GONE);
                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });

        hPouches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.setVisibility(View.GONE);
                shimmer.setVisibility(View.VISIBLE);
                shimmer.startShimmer();
                session.setsub("Accesories POUCHES");
                Query firebasequery = mref.orderByChild("CategoryType").equalTo("Accesories POUCHES");
                FirebaseRecyclerAdapter<Accesories, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Accesories, ViewHolder>(
                                Accesories.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Accesories profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1,profile.Category,profile.CategoryName,profile.Feature1);
                            }
                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView mobile_name=(TextView)v.findViewById(R.id.mobile_nameTV);

                                        String a=mobile_name.getText().toString();


                                        Bundle bundle=new Bundle();
                                        AccesoriesDetails fragment= new AccesoriesDetails();

                                        bundle.putString("name",a);
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
                                mRecyclerView.setVisibility(View.VISIBLE);
                                shimmer.stopShimmer();
                                shimmer.setVisibility(View.GONE);
                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });


        hTemperedGlass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.setVisibility(View.GONE);
                shimmer.setVisibility(View.VISIBLE);
                shimmer.startShimmer();
                session.setsub("Accesories TEMPEREDGLASS");
                Query firebasequery = mref.orderByChild("CategoryType").equalTo("Accesories TEMPEREDGLASS");
                FirebaseRecyclerAdapter<Accesories, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Accesories, ViewHolder>(
                                Accesories.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Accesories profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1,profile.Category,profile.CategoryName,profile.Feature1);
                            }
                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView mobile_name=(TextView)v.findViewById(R.id.mobile_nameTV);

                                        String a=mobile_name.getText().toString();


                                        Bundle bundle=new Bundle();
                                        AccesoriesDetails fragment= new AccesoriesDetails();

                                        bundle.putString("name",a);
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
                                mRecyclerView.setVisibility(View.VISIBLE);
                                shimmer.stopShimmer();
                                shimmer.setVisibility(View.GONE);
                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });

        hOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.setVisibility(View.GONE);
                shimmer.setVisibility(View.VISIBLE);
                shimmer.startShimmer();
                session.setsub("Accesories OTHERS");
                Query firebasequery = mref.orderByChild("CategoryType").equalTo("Accesories OTHERS");
                FirebaseRecyclerAdapter<Accesories, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Accesories, ViewHolder>(
                                Accesories.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Accesories profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1,profile.Category,profile.CategoryName,profile.Feature1);
                            }
                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView mobile_name=(TextView)v.findViewById(R.id.mobile_nameTV);

                                        String a=mobile_name.getText().toString();


                                        Bundle bundle=new Bundle();
                                        AccesoriesDetails fragment= new AccesoriesDetails();

                                        bundle.putString("name",a);
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
                                mRecyclerView.setVisibility(View.VISIBLE);
                                shimmer.stopShimmer();
                                shimmer.setVisibility(View.GONE);
                            }

                        };

                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });
        return v;
    }


    @Override
    public void onStart() {
        mRecyclerView.setVisibility(View.GONE);
        shimmer.setVisibility(View.VISIBLE);
        shimmer.startShimmer();
        super.onStart();
        Query firebasequery=mref.orderByChild("CategoryType").equalTo("Accesories PENDRIVE");

        if(!TextUtils.isEmpty(session.getsub()))
        {
            firebasequery=mref.orderByChild("CategoryType").equalTo(session.getsub());
        }

        FirebaseRecyclerAdapter<Accesories, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Accesories, ViewHolder>(
                        Accesories.class,
                        R.layout.cardview_mobile,
                        ViewHolder.class,
                        firebasequery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Accesories profile, int position) {
                        viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1,profile.Category,profile.CategoryName,profile.Feature1);
                    }
                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {
                                TextView mobile_name=(TextView)v.findViewById(R.id.mobile_nameTV);

                                String a=mobile_name.getText().toString();


                                Bundle bundle=new Bundle();
                                AccesoriesDetails fragment= new AccesoriesDetails();

                                bundle.putString("name",a);
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
                        mRecyclerView.setVisibility(View.VISIBLE);
                        shimmer.stopShimmer();
                        shimmer.setVisibility(View.GONE);
                    }

                };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private  void firebasesearch(final String searchText)
    {
        mRecyclerView.setVisibility(View.GONE);
        shimmer.setVisibility(View.VISIBLE);
        shimmer.startShimmer();
        Query firebasesearchquery = mref.orderByChild("Category").equalTo("Accesories");

        FirebaseRecyclerAdapter<Accesories,ViewHolder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<Accesories, ViewHolder>(
                        Accesories.class,
                        R.layout.cardview_mobile,
                        ViewHolder.class,
                        firebasesearchquery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Accesories profile, int position) {
                        viewHolder.setDetails1(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1, profile.Category,profile.CategoryName,searchText,profile.Feature1);
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {
                                TextView mobile_name = (TextView) v.findViewById(R.id.mobile_nameTV);

                                String a = mobile_name.getText().toString();


                                Bundle bundle = new Bundle();
                                AccesoriesDetails fragment = new AccesoriesDetails();

                                bundle.putString("name", a);
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
                        mRecyclerView.setVisibility(View.VISIBLE);
                        shimmer.stopShimmer();
                        shimmer.setVisibility(View.GONE);
                    }
                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    @Override
    public  void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {


        inflater.inflate(R.menu.main_menu,menu);
        MenuItem itemMessages = menu.findItem(R.id.cart);
        MenuItem search=menu.findItem(R.id.search);
//        MenuItem signout=menu.findItem(R.id.signout);
        View badgeLayout = (RelativeLayout) itemMessages.getActionView();
        final TextView itemMessagesBadgeTextView = (TextView) badgeLayout.findViewById(R.id.badge_textView);
        LoginSession session=new LoginSession(getContext());
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users").child("+91"+session.getusename()).child("Cart");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // get total available quest
                int size = (int) dataSnapshot.getChildrenCount();
                itemMessagesBadgeTextView.setText(Integer.toString(size));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        itemMessagesBadgeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment=new CartFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!TextUtils.isEmpty(query))
                firebasesearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!TextUtils.isEmpty(newText))
                firebasesearch(newText);
                return false;
            }
        });
     }


}

