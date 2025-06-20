package rohan.groups.sunrise.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.Products.Products;
import rohan.groups.sunrise.Products.ViewHolder;
import rohan.groups.sunrise.R;
public class SearchFragment extends Fragment {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    Session session;
    DatabaseReference mref;

    private AutoCompleteTextView search;


    ArrayList<String> input = new ArrayList<String>();
    ArrayList<String> input1 = new ArrayList<String>();
    ArrayList<String> cat = new ArrayList<String>();

    public SearchFragment() {
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
        View v=inflater.inflate(R.layout.fragment_search, container, false);


        Toolbar toolbar=(getActivity()).findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);

        LinearLayout bottomnavigation=(getActivity()).findViewById(R.id.bottomnavigation);
        bottomnavigation.setVisibility(View.GONE);

        search = v.findViewById(R.id.search);

        session=new Session(getContext());

        mRecyclerView = v.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutmanager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutmanager);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mref = mFirebaseDatabase.getReference("AppContent").child("Recommended");


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
                            ArrayAdapter arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner, input);
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




        return v;
    }


    @Override
    public void onStart() {
        super.onStart();


        FirebaseRecyclerAdapter<Products, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Products, ViewHolder>(
                        Products.class,
                        R.layout.cardview_mobile,
                        ViewHolder.class,
                        mref
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Products profile, int position) {
                            viewHolder.setDetails(getContext(), profile.Name, profile.Price, profile.Cashback, profile.Image1, profile.Category, profile.CategoryName, profile.Feature1, profile.PushId);
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


    @Override
    public void onResume(){
        super.onResume();
        search.setText("");
    }


}
