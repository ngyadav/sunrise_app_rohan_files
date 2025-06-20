package rohan.groups.sunrise.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.ProductDetails.Product;
import rohan.groups.sunrise.ProductDetails.ProductsAdapter;
import rohan.groups.sunrise.R;


public class Grocery extends Fragment {


    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    Session sessions;
    RecyclerView mRecyclerView;
    LinearLayout floatingbar;
    TextView items,itemsprice,viewcart;

    ProgressBar progressBar;
    ImageView h1,h2,h3,h4,h5;

    EditText search;

    private ProductsAdapter productsAdapter;
    ArrayList<Product> products=new ArrayList<Product>();


    public Grocery() {
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
        View v=inflater.inflate(R.layout.fragment_grocery, container, false);

        Toolbar toolbar=(getActivity()).findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        LinearLayout bottomnavigation=(getActivity()).findViewById(R.id.bottomnavigation);
        bottomnavigation.setVisibility(View.GONE);

        ImageView back = v.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        mRecyclerView = v.findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        mLayoutManager.setReverseLayout(true);
//        mLayoutManager.setStackFromEnd(true);
        sessions = new Session(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mref = mFirebaseDatabase.getReference().child("Category");

        floatingbar=v.findViewById(R.id.floatingbar);
        items=v.findViewById(R.id.items);
        itemsprice=v.findViewById(R.id.itemsprice);
        search=v.findViewById(R.id.search);

        progressBar=v.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);


        h1=v.findViewById(R.id.h1);
        h2=v.findViewById(R.id.h2);
        h3=v.findViewById(R.id.h3);
        h4=v.findViewById(R.id.h4);
        h5=v.findViewById(R.id.h5);


        mRecyclerView.setVisibility(View.VISIBLE);




        FirebaseDatabase.getInstance().getReference().child("Users").child(sessions.getusername()).child("CartGrocery")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.exists()){
                            floatingbar.setVisibility(View.VISIBLE);
                            items.setText(sessions.getcartitem()+"Items");
                            itemsprice.setText("\u20b9"+sessions.getcarttotal());
                        }
                        else{
                            floatingbar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



        viewcart=v.findViewById(R.id.viewcart);

        viewcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new GroceryCartFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });



//
//        products.clear();
//        productsAdapter = new ProductsAdapter(products);
//
//        FirebaseDatabase.getInstance().getReference().child("Grocery")
////                .orderByChild("Category").equalTo(sessions.getsub())
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if(dataSnapshot.exists()){
//                            for(DataSnapshot v: dataSnapshot.getChildren()){
//                                products.add(new Product(
//                                        v.child("Name").getValue().toString(),
//                                        v.child("Desc").getValue().toString(),
//                                        v.child("PushId").getValue().toString(),
//                                        v.child("Image").getValue().toString(),
//                                        v.child("Units").getValue().toString(),
//                                        v.child("Category").getValue().toString(),
//                                        v.child("CategoryName").getValue().toString(),
//                                        v.child("W1").getValue().toString(),
//                                        v.child("W2").getValue().toString(),
//                                        v.child("W3").getValue().toString(),
//                                        v.child("W4").getValue().toString(),
//                                        v.child("W5").getValue().toString(),
//                                        v.child("W6").getValue().toString(),
//                                        v.child("W7").getValue().toString(),
//                                        v.child("W8").getValue().toString(),
//                                        v.child("Status").getValue().toString()));
//                            }
//
//                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//                            mRecyclerView.setLayoutManager(mLayoutManager);
//
//                            productsAdapter = new ProductsAdapter(products);
//
//                            mRecyclerView.setAdapter(productsAdapter);
//
//                            progressBar.setVisibility(View.GONE);
//
//
//                        }
//                        else{
//                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//                            mRecyclerView.setLayoutManager(mLayoutManager);
//
//                            productsAdapter = new ProductsAdapter(products);
//
//                            mRecyclerView.setAdapter(productsAdapter);
//
//                            progressBar.setVisibility(View.GONE);
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });


        h1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                products.clear();
                productsAdapter = new ProductsAdapter(products);
                FirebaseDatabase.getInstance().getReference().child("Grocery")
                .orderByChild("Category").equalTo("Dairy")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    for(DataSnapshot v: dataSnapshot.getChildren()){
                                        products.add(new Product(
                                                v.child("Name").getValue().toString(),
                                                v.child("Desc").getValue().toString(),
                                                v.child("PushId").getValue().toString(),
                                                v.child("Image").getValue().toString(),
                                                v.child("Units").getValue().toString(),
                                                v.child("Category").getValue().toString(),
                                                v.child("CategoryName").getValue().toString(),
                                                v.child("W1").getValue().toString(),
                                                v.child("W2").getValue().toString(),
                                                v.child("W3").getValue().toString(),
                                                v.child("W4").getValue().toString(),
                                                v.child("W5").getValue().toString(),
                                                v.child("W6").getValue().toString(),
                                                v.child("W7").getValue().toString(),
                                                v.child("W8").getValue().toString(),
                                                v.child("Status").getValue().toString()));
                                    }

                                    productsAdapter = new ProductsAdapter(products);
                                    mRecyclerView.setAdapter(productsAdapter);
                                    progressBar.setVisibility(View.GONE);

                                }
                                else{
                                   productsAdapter = new ProductsAdapter(products);
                                    mRecyclerView.setAdapter(productsAdapter);
                                    progressBar.setVisibility(View.GONE);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

            }
        });


        h2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                products.clear();
                productsAdapter = new ProductsAdapter(products);
                FirebaseDatabase.getInstance().getReference().child("Grocery")
                        .orderByChild("Category").equalTo("Fruits")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    for(DataSnapshot v: dataSnapshot.getChildren()){
                                        products.add(new Product(
                                                v.child("Name").getValue().toString(),
                                                v.child("Desc").getValue().toString(),
                                                v.child("PushId").getValue().toString(),
                                                v.child("Image").getValue().toString(),
                                                v.child("Units").getValue().toString(),
                                                v.child("Category").getValue().toString(),
                                                v.child("CategoryName").getValue().toString(),
                                                v.child("W1").getValue().toString(),
                                                v.child("W2").getValue().toString(),
                                                v.child("W3").getValue().toString(),
                                                v.child("W4").getValue().toString(),
                                                v.child("W5").getValue().toString(),
                                                v.child("W6").getValue().toString(),
                                                v.child("W7").getValue().toString(),
                                                v.child("W8").getValue().toString(),
                                                v.child("Status").getValue().toString()));
                                    }

                                    productsAdapter = new ProductsAdapter(products);
                                    mRecyclerView.setAdapter(productsAdapter);
                                    progressBar.setVisibility(View.GONE);


                                }
                                else{
                                    productsAdapter = new ProductsAdapter(products);
                                    mRecyclerView.setAdapter(productsAdapter);
                                    progressBar.setVisibility(View.GONE);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

            }
        });


        h3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                products.clear();
                productsAdapter = new ProductsAdapter(products);
                FirebaseDatabase.getInstance().getReference().child("Grocery")
                        .orderByChild("Category").equalTo("Vegetable")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    for(DataSnapshot v: dataSnapshot.getChildren()){
                                        products.add(new Product(
                                                v.child("Name").getValue().toString(),
                                                v.child("Desc").getValue().toString(),
                                                v.child("PushId").getValue().toString(),
                                                v.child("Image").getValue().toString(),
                                                v.child("Units").getValue().toString(),
                                                v.child("Category").getValue().toString(),
                                                v.child("CategoryName").getValue().toString(),
                                                v.child("W1").getValue().toString(),
                                                v.child("W2").getValue().toString(),
                                                v.child("W3").getValue().toString(),
                                                v.child("W4").getValue().toString(),
                                                v.child("W5").getValue().toString(),
                                                v.child("W6").getValue().toString(),
                                                v.child("W7").getValue().toString(),
                                                v.child("W8").getValue().toString(),
                                                v.child("Status").getValue().toString()));
                                    }

                                    productsAdapter = new ProductsAdapter(products);
                                    mRecyclerView.setAdapter(productsAdapter);
                                    progressBar.setVisibility(View.GONE);


                                }
                                else{
                                    productsAdapter = new ProductsAdapter(products);
                                    mRecyclerView.setAdapter(productsAdapter);
                                    progressBar.setVisibility(View.GONE);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

            }
        });


        h4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                products.clear();
                productsAdapter = new ProductsAdapter(products);
                FirebaseDatabase.getInstance().getReference().child("Grocery")
                        .orderByChild("Category").equalTo("Grocery")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    for(DataSnapshot v: dataSnapshot.getChildren()){
                                        products.add(new Product(
                                                v.child("Name").getValue().toString(),
                                                v.child("Desc").getValue().toString(),
                                                v.child("PushId").getValue().toString(),
                                                v.child("Image").getValue().toString(),
                                                v.child("Units").getValue().toString(),
                                                v.child("Category").getValue().toString(),
                                                v.child("CategoryName").getValue().toString(),
                                                v.child("W1").getValue().toString(),
                                                v.child("W2").getValue().toString(),
                                                v.child("W3").getValue().toString(),
                                                v.child("W4").getValue().toString(),
                                                v.child("W5").getValue().toString(),
                                                v.child("W6").getValue().toString(),
                                                v.child("W7").getValue().toString(),
                                                v.child("W8").getValue().toString(),
                                                v.child("Status").getValue().toString()));
                                    }

                                    productsAdapter = new ProductsAdapter(products);
                                    mRecyclerView.setAdapter(productsAdapter);
                                    progressBar.setVisibility(View.GONE);
                                }
                                else{
                                    productsAdapter = new ProductsAdapter(products);
                                    mRecyclerView.setAdapter(productsAdapter);
                                    progressBar.setVisibility(View.GONE);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

            }
        });


        h5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                products.clear();
                productsAdapter = new ProductsAdapter(products);
                FirebaseDatabase.getInstance().getReference().child("Grocery")
                        .orderByChild("Category").equalTo("Food")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    for(DataSnapshot v: dataSnapshot.getChildren()){
                                        products.add(new Product(
                                                v.child("Name").getValue().toString(),
                                                v.child("Desc").getValue().toString(),
                                                v.child("PushId").getValue().toString(),
                                                v.child("Image").getValue().toString(),
                                                v.child("Units").getValue().toString(),
                                                v.child("Category").getValue().toString(),
                                                v.child("CategoryName").getValue().toString(),
                                                v.child("W1").getValue().toString(),
                                                v.child("W2").getValue().toString(),
                                                v.child("W3").getValue().toString(),
                                                v.child("W4").getValue().toString(),
                                                v.child("W5").getValue().toString(),
                                                v.child("W6").getValue().toString(),
                                                v.child("W7").getValue().toString(),
                                                v.child("W8").getValue().toString(),
                                                v.child("Status").getValue().toString()));
                                    }

                                    productsAdapter = new ProductsAdapter(products);
                                    mRecyclerView.setAdapter(productsAdapter);
                                    progressBar.setVisibility(View.GONE);


                                }
                                else{
                                    productsAdapter = new ProductsAdapter(products);
                                    mRecyclerView.setAdapter(productsAdapter);
                                    progressBar.setVisibility(View.GONE);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

            }
        });



        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                productsAdapter.getFilter().filter(charSequence);
                mRecyclerView.setVisibility(View.VISIBLE);

                if(charSequence.length()==0)
                    mRecyclerView.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                products.clear();
                productsAdapter = new ProductsAdapter(products);

                FirebaseDatabase.getInstance().getReference().child("Grocery")
//                .orderByChild("Category").equalTo(sessions.getsub())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    for(DataSnapshot v: dataSnapshot.getChildren()){
                                        products.add(new Product(
                                                v.child("Name").getValue().toString(),
                                                v.child("Desc").getValue().toString(),
                                                v.child("PushId").getValue().toString(),
                                                v.child("Image").getValue().toString(),
                                                v.child("Units").getValue().toString(),
                                                v.child("Category").getValue().toString(),
                                                v.child("CategoryName").getValue().toString(),
                                                v.child("W1").getValue().toString(),
                                                v.child("W2").getValue().toString(),
                                                v.child("W3").getValue().toString(),
                                                v.child("W4").getValue().toString(),
                                                v.child("W5").getValue().toString(),
                                                v.child("W6").getValue().toString(),
                                                v.child("W7").getValue().toString(),
                                                v.child("W8").getValue().toString(),
                                                v.child("Status").getValue().toString()));
                                    }
                                    productsAdapter = new ProductsAdapter(products);
                                    mRecyclerView.setAdapter(productsAdapter);
                                    progressBar.setVisibility(View.GONE);
                                }
                                else{
                                    productsAdapter = new ProductsAdapter(products);
                                    mRecyclerView.setAdapter(productsAdapter);
                                    progressBar.setVisibility(View.GONE);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

            }
        });

        return v;
    }


    @Override
    public void onStart(){
        super.onStart();

        products.clear();
        productsAdapter = new ProductsAdapter(products);

        FirebaseDatabase.getInstance().getReference().child("Grocery")
//                .orderByChild("Category").equalTo(sessions.getsub())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            for(DataSnapshot v: dataSnapshot.getChildren()){
                                products.add(new Product(
                                        v.child("Name").getValue().toString(),
                                        v.child("Desc").getValue().toString(),
                                        v.child("PushId").getValue().toString(),
                                        v.child("Image").getValue().toString(),
                                        v.child("Units").getValue().toString(),
                                        v.child("Category").getValue().toString(),
                                        v.child("CategoryName").getValue().toString(),
                                        v.child("W1").getValue().toString(),
                                        v.child("W2").getValue().toString(),
                                        v.child("W3").getValue().toString(),
                                        v.child("W4").getValue().toString(),
                                        v.child("W5").getValue().toString(),
                                        v.child("W6").getValue().toString(),
                                        v.child("W7").getValue().toString(),
                                        v.child("W8").getValue().toString(),
                                        v.child("Status").getValue().toString()));
                            }

                            productsAdapter = new ProductsAdapter(products);
                            mRecyclerView.setAdapter(productsAdapter);
                            progressBar.setVisibility(View.GONE);
                        }
                        else{
                            productsAdapter = new ProductsAdapter(products);
                            mRecyclerView.setAdapter(productsAdapter);
                            progressBar.setVisibility(View.GONE);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }


    @Override
    public void onResume(){
        super.onResume();

        productsAdapter = new ProductsAdapter(products);
        mRecyclerView.setAdapter(productsAdapter);
        progressBar.setVisibility(View.GONE);

    }

}
