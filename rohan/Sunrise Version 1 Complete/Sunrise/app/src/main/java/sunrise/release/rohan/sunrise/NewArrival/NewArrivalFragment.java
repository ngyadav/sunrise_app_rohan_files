package sunrise.release.rohan.sunrise.NewArrival;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
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

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import sunrise.release.rohan.sunrise.Cart.CartFragment;
import sunrise.release.rohan.sunrise.Functionality.LoginSession;
import sunrise.release.rohan.sunrise.R;

public class NewArrivalFragment extends Fragment {
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    LoginSession session;
    ImageView hgifts,hoffice,hothers;
    private ShimmerFrameLayout shimmer;
    public NewArrivalFragment() {
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
        View v = inflater.inflate(R.layout.fragment_new_arrival, container, false);

        hothers=(ImageView)v.findViewById(R.id.hothers);
        hoffice=(ImageView)v.findViewById(R.id.hoffice);
        hgifts=(ImageView)v.findViewById(R.id.hgifts);
        shimmer=v.findViewById(R.id.shimmer);
        mRecyclerView = v.findViewById(R.id.recyclerviewcomputer);
        mRecyclerView.setHasFixedSize(true);
        session=new LoginSession(getContext());

//        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mref = mFirebaseDatabase.getReference("Category");

        hgifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.setVisibility(View.GONE);
                shimmer.setVisibility(View.VISIBLE);
                shimmer.startShimmer();
                session.setsub("NewArrival GIFTS");
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("NewArrival GIFTS");
                FirebaseRecyclerAdapter<NewArrival, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<NewArrival, ViewHolder>(
                                NewArrival.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, NewArrival profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1,profile.Category,profile.CategoryName,profile.Feature1);}
                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView mobile_name=(TextView)v.findViewById(R.id.mobile_nameTV);

                                        String a=mobile_name.getText().toString();


                                        Bundle bundle=new Bundle();
                                        NewArrivalDetails fragment= new NewArrivalDetails();

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

        hoffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.setVisibility(View.GONE);
                shimmer.setVisibility(View.VISIBLE);
                shimmer.startShimmer();
                session.setsub("NewArrival OFFICE");
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("NewArrival OFFICE");
                FirebaseRecyclerAdapter<NewArrival, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<NewArrival, ViewHolder>(
                                NewArrival.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, NewArrival profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1,profile.Category,profile.CategoryName,profile.Feature1);}
                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView mobile_name=(TextView)v.findViewById(R.id.mobile_nameTV);

                                        String a=mobile_name.getText().toString();


                                        Bundle bundle=new Bundle();
                                        NewArrivalDetails fragment= new NewArrivalDetails();

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

        hothers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.setVisibility(View.GONE);
                shimmer.setVisibility(View.VISIBLE);
                shimmer.startShimmer();
                session.setsub("NewArrival OTHERS");
                Query firebasequery=mref.orderByChild("CategoryType").equalTo("NewArrival OTHERS");
                FirebaseRecyclerAdapter<NewArrival, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<NewArrival, ViewHolder>(
                                NewArrival.class,
                                R.layout.cardview_mobile,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, NewArrival profile, int position) {
                                viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1,profile.Category,profile.CategoryName,profile.Feature1);}
                            @Override
                            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position) {
                                        TextView mobile_name=(TextView)v.findViewById(R.id.mobile_nameTV);

                                        String a=mobile_name.getText().toString();


                                        Bundle bundle=new Bundle();
                                        NewArrivalDetails fragment= new NewArrivalDetails();

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
        super.onStart();
        mRecyclerView.setVisibility(View.GONE);
        shimmer.setVisibility(View.VISIBLE);
        shimmer.startShimmer();
        Query firebasequery=mref.orderByChild("Category").equalTo("NewArrival");

        if(!TextUtils.isEmpty(session.getsub()))
        {
            firebasequery=mref.orderByChild("Category").equalTo("NewArrival");
        }

        FirebaseRecyclerAdapter<NewArrival, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<NewArrival, ViewHolder>(
                        NewArrival.class,
                        R.layout.cardview_mobile,
                        ViewHolder.class,
                        firebasequery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, NewArrival profile, int position) {
                        viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1,profile.Category,profile.CategoryName,profile.Feature1);}
                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {
                                TextView mobile_name=(TextView)v.findViewById(R.id.mobile_nameTV);

                                String a=mobile_name.getText().toString();


                                Bundle bundle=new Bundle();
                                NewArrivalDetails fragment= new NewArrivalDetails();

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
        Query firebasesearchquery = mref.orderByChild("Category").equalTo("NewArrival");

        FirebaseRecyclerAdapter<NewArrival,ViewHolder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<NewArrival, ViewHolder>(
                        NewArrival.class,
                        R.layout.cardview_mobile,
                        ViewHolder.class,
                        firebasesearchquery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, NewArrival profile, int position) {
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
                                NewArrivalDetails fragment = new NewArrivalDetails();

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
                Fragment fragment=new CartFragment();
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




//        @Override
//        public boolean onOptionsItemSelected(MenuItem item)
//        {
//            switch (item.getItemId())
//            {
//                case R.id.search:
//                    return true;
//            }
//
//            return super.onOptionsItemSelected(item);
//        }
}

