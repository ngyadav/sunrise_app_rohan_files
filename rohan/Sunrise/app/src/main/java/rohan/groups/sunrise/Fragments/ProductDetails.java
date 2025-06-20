package rohan.groups.sunrise.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.googledirection.model.Line;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;

import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.Products.Products;
import rohan.groups.sunrise.R;


public class ProductDetails extends Fragment {

    private ImageView i1,i2,i3,bigimage,back,cart;
    TextView pname,price,mrp,discount,rewards;
    private TextView mobile_feature1_details,mobile_feature2_details,mobile_feature3_details,mobile_feature4_details,mobile_feature5_details,mobile_feature6_details,mobile_stock_details,mobile_feature7_details,mobile_feature8_details,mobile_feature9_details,mobile_feature10_details,mobile_feature11_details,mobile_feature12_details,mobile_feature13_details,mobile_feature14_details,mobile_feature15_details;
   
    private TextView mobile_feature1,mobile_feature2,mobile_feature3,mobile_feature4,mobile_feature5,mobile_feature6,mobile_stock,mobile_feature7,mobile_feature8,mobile_feature9,mobile_feature10,mobile_feature11,mobile_feature12,mobile_feature13,mobile_feature14,mobile_feature15;
   
   
   
    private LinearLayout addtocart,buynow;
    Session session;
    private Products products;


    MaterialSpinner size,color;
    String name="";


    public ProductDetails() {
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
        final View v=inflater.inflate(R.layout.fragment_product_details, container, false);

        Toolbar toolbar=(getActivity()).findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        LinearLayout bottomnavigation=(getActivity()).findViewById(R.id.bottomnavigation);
        bottomnavigation.setVisibility(View.GONE);

        i1=v.findViewById(R.id.i1);
        i2=v.findViewById(R.id.i2);
        i3=v.findViewById(R.id.i3);
        bigimage=v.findViewById(R.id.bigimage);
        pname=v.findViewById(R.id.pname);
        price=v.findViewById(R.id.price);
        mrp=v.findViewById(R.id.mrp);
        discount=v.findViewById(R.id.discount);
        rewards=v.findViewById(R.id.rewards);
        back=v.findViewById(R.id.back);
        cart=v.findViewById(R.id.cart);

        mobile_feature2_details=(TextView)v.findViewById(R.id.mobile_feature2_details);
        mobile_feature3_details=(TextView)v.findViewById(R.id.mobile_feature3_details);
        mobile_feature4_details=(TextView)v.findViewById(R.id.mobile_feature4_details);
        mobile_feature5_details=(TextView)v.findViewById(R.id.mobile_feature5_details);
        mobile_feature6_details=(TextView)v.findViewById(R.id.mobile_feature6_details);
        mobile_feature7_details=(TextView)v.findViewById(R.id.mobile_feature7_details);
        mobile_feature8_details=(TextView)v.findViewById(R.id.mobile_feature8_details);
        mobile_feature9_details=(TextView)v.findViewById(R.id.mobile_feature9_details);
        mobile_feature10_details=(TextView)v.findViewById(R.id.mobile_feature10_details);
        mobile_feature11_details=(TextView)v.findViewById(R.id.mobile_feature11_details);
        mobile_feature12_details=(TextView)v.findViewById(R.id.mobile_feature12_details);
        mobile_feature13_details=(TextView)v.findViewById(R.id.mobile_feature13_details);
        mobile_feature14_details=(TextView)v.findViewById(R.id.mobile_feature14_details);
        mobile_feature15_details=(TextView)v.findViewById(R.id.mobile_feature15_details);



        mobile_feature2=(TextView)v.findViewById(R.id.mobile_feature2);
        mobile_feature3=(TextView)v.findViewById(R.id.mobile_feature3);
        mobile_feature4=(TextView)v.findViewById(R.id.mobile_feature4);
        mobile_feature5=(TextView)v.findViewById(R.id.mobile_feature5);
        mobile_feature6=(TextView)v.findViewById(R.id.mobile_feature6);
        mobile_feature7=(TextView)v.findViewById(R.id.mobile_feature7);
        mobile_feature8=(TextView)v.findViewById(R.id.mobile_feature8);
        mobile_feature9=(TextView)v.findViewById(R.id.mobile_feature9);
        mobile_feature10=(TextView)v.findViewById(R.id.mobile_feature10);
        mobile_feature11=(TextView)v.findViewById(R.id.mobile_feature11);
        mobile_feature12=(TextView)v.findViewById(R.id.mobile_feature12);
        mobile_feature13=(TextView)v.findViewById(R.id.mobile_feature13);
        mobile_feature14=(TextView)v.findViewById(R.id.mobile_feature14);
        mobile_feature15=(TextView)v.findViewById(R.id.mobile_feature15);
        
        
        
        
        mobile_stock_details=(TextView)v.findViewById(R.id.mobile_stock_details);
        
        
        
        

        addtocart=v.findViewById(R.id.addtocart);
        buynow=v.findViewById(R.id.buynow);
        session = new Session(getContext());

        if(getArguments()!=null) {
            name = getArguments().getString("name");
        }


        size=v.findViewById(R.id.size);
        color=v.findViewById(R.id.color);


        ImageView plus=v.findViewById(R.id.plus);
        ImageView minus=v.findViewById(R.id.minus);
        final TextView qty=v.findViewById(R.id.qty);


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a=Integer.parseInt(qty.getText().toString());
                a++;
                qty.setText(""+a);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a=Integer.parseInt(qty.getText().toString());
                if(a>1) {
                    a--;
                    qty.setText("" + a);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(session.getusername())) {
                    Toast.makeText(getContext(),"Please register to continue",Toast.LENGTH_LONG).show();
                    return;
                }

                CartFragment fragment = new CartFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.child("CategoryNew").child(name);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {
                    products = dataSnapshot.getValue(Products.class);

                    pname.setText(products.Name);
                    price.setText("\u20b9" + products.Price);
                    mrp.setText("\u20b9" + products.Mrp);
                    rewards.setText("Get Upto " + products.Cashback + " Reward Points");

                    discount.setText("" + Math.round(((Integer.parseInt(products.Mrp) - Integer.parseInt(products.Price)) / (Float.parseFloat(products.Mrp))) * 100) + "%");

                    String str2 = "\u2022";

                    if (!products.Feature2.equals("")) {
                        String a[] = products.Feature2.split(":");
                        if (a.length == 2) {
                            mobile_feature2_details.setText(a[1]);
                            mobile_feature2.setText(a[0]);
                        }
                    }
                    if (!products.Feature3.equals("")) {
                        String a[] = products.Feature3.split(":");
                        if (a.length == 2) {
                            mobile_feature3_details.setText(a[1]);
                            mobile_feature3.setText(a[0]);
                        }
                    }
                    if (!products.Feature4.equals("")) {
                        String a[] = products.Feature4.split(":");
                        if (a.length == 2) {
                            mobile_feature4_details.setText(a[1]);
                            mobile_feature4.setText(a[0]);
                        }
                    }
                    if (!products.Feature5.equals("")) {
                        String a[] = products.Feature5.split(":");
                        if (a.length == 2) {
                            mobile_feature5_details.setText(a[1]);
                            mobile_feature5.setText(a[0]);
                        }
                    }
                    if (!products.Feature6.equals("")) {
                        String a[] = products.Feature6.split(":");
                        if (a.length == 2) {
                            mobile_feature6_details.setText(a[1]);
                            mobile_feature6.setText(a[0]);
                        }
                    }
                    if (!products.Feature7.equals("")) {
                        String a[] = products.Feature7.split(":");
                        if (a.length == 2) {
                            mobile_feature7_details.setText(a[1]);
                            mobile_feature7.setText(a[0]);
                        }
                    }
                    if (!products.Feature8.equals("")) {
                        String a[] = products.Feature8.split(":");
                        if (a.length == 2) {
                            mobile_feature8_details.setText(a[1]);
                            mobile_feature8.setText(a[0]);
                        }
                    }
                    if (!products.Feature9.equals("")) {
                        String a[] = products.Feature9.split(":");
                        mobile_feature9_details.setText(a[1]);
                        mobile_feature9.setText(a[0]);
                    }
                    if (!products.Feature10.equals("")) {
                        String a[] = products.Feature10.split(":");
                        if (a.length == 2) {
                            mobile_feature10_details.setText(a[1]);
                            mobile_feature10.setText(a[0]);
                        }
                    }
                    if (!products.Feature11.equals("")) {
                        String a[] = products.Feature11.split(":");
                        if (a.length == 2) {
                            mobile_feature11_details.setText(a[1]);
                            mobile_feature11.setText(a[0]);
                        }
                    }
                    if (!products.Feature12.equals("")) {
                        String a[] = products.Feature12.split(":");
                        if (a.length == 2) {
                            mobile_feature12_details.setText(a[1]);
                            mobile_feature12.setText(a[0]);
                        }
                    }
                    if (!products.Feature13.equals("")) {
                        String a[] = products.Feature13.split(":");
                        if (a.length == 2) {
                            mobile_feature13_details.setText(a[1]);
                            mobile_feature13.setText(a[0]);
                        }
                    }
                    if (!products.Feature14.equals("")) {
                        String a[] = products.Feature14.split(":");
                        if (a.length == 2) {
                            mobile_feature14_details.setText(a[1]);
                            mobile_feature14.setText(a[0]);
                        }
                    }
                    if (!products.Feature15.equals("")) {
                        String a[] = products.Feature15.split(":");
                        if (a.length == 2) {
                            mobile_feature15_details.setText(a[1]);
                            mobile_feature15.setText(a[0]);
                        }
                    }


                    if (products.Stock.equalsIgnoreCase("In Stock")) {
                        mobile_stock_details.setText(products.Stock);
                        mobile_stock_details.setTextColor(Color.parseColor("#23C348"));
                    }
                    if (products.Stock.equalsIgnoreCase("Out of Stock")) {
                        mobile_stock_details.setText(products.Stock);
                        mobile_stock_details.setTextColor(Color.parseColor("#E90303"));
                        addtocart.setClickable(false);
                        buynow.setClickable(false);
                    }


                    if (!TextUtils.isEmpty(products.Colour)) {
                        String currentString = products.Colour;
                        if (!TextUtils.isEmpty(currentString)) {
                            String[] color1 = currentString.split(",");
                            color.setItems(color1);
                        }
                    } else {
                        LinearLayout l3 = v.findViewById(R.id.l3);
                        l3.setVisibility(View.GONE);
                    }

                    if (!TextUtils.isEmpty(products.Size)) {
                        String current = products.Size;
                        if (!TextUtils.isEmpty(current)) {
                            String[] size1 = current.split(",");
                            size.setItems(size1);
                        }
                    } else {
                        LinearLayout l4 = v.findViewById(R.id.l4);
                        l4.setVisibility(View.GONE);
                    }


                    Glide.with(getContext())
                            .load(products.Image1)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(i1);

                    Glide.with(getContext())
                            .load(products.Image2)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(i2);

                    Glide.with(getContext())
                            .load(products.Image3)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(i3);

                    Glide.with(getContext())
                            .load(products.Image1)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(bigimage);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(getContext())
                        .load(products.Image1)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(bigimage);
            }
        });


        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(getContext())
                        .load(products.Image2)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(bigimage);
            }
        });

        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(getContext())
                        .load(products.Image3)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(bigimage);
            }
        });



        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(session.getusername())) {
                    Toast.makeText(getContext(),"Please register to continue",Toast.LENGTH_LONG).show();
                    return;
                }

                if(products.Stock.equals("Out Of Stock")){
                    Toast.makeText(getContext(),"Item Not Available",Toast.LENGTH_SHORT).show();
                    return;
                }


                DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("Cart1").child(products.PushId);
                int a=Integer.parseInt(qty.getText().toString());
                int p=Integer.parseInt(products.Price);
                int r=Integer.parseInt(products.Cashback);

                mref.child("ItemName").setValue(products.Name);
                mref.child("Price").setValue(products.Price);
                mref.child("Total").setValue(""+(a*p));
                mref.child("Qty").setValue(""+a);
                mref.child("Size").setValue(size.getText().toString());
                mref.child("Color").setValue(color.getText().toString());
                mref.child("Image").setValue(products.Image1);
                mref.child("PushId").setValue(products.PushId);
                mref.child("Rewards").setValue(products.Cashback);
                mref.child("RewardsTotal").setValue(""+(a*r));

                Toast.makeText(getContext(),"Added to cart",Toast.LENGTH_SHORT).show();

                getActivity().onBackPressed();

            }
        });



        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(TextUtils.isEmpty(session.getusername())) {
                        Toast.makeText(getContext(),"Please register to continue",Toast.LENGTH_LONG).show();
                    return;
                }

                if(products.Stock.equals("Out Of Stock")){
                    Toast.makeText(getContext(),"Item Not Available",Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("Cart1").child(products.PushId);
                int a=Integer.parseInt(qty.getText().toString());
                int p=Integer.parseInt(products.Price);
                int r=Integer.parseInt(products.Cashback);


                mref.child("ItemName").setValue(products.Name);
                mref.child("Price").setValue(products.Price);
                mref.child("Total").setValue(""+(a*p));
                mref.child("Qty").setValue(""+a);
                mref.child("Size").setValue(size.getText().toString());
                mref.child("Color").setValue(color.getText().toString());
                mref.child("Image").setValue(products.Image1);
                mref.child("PushId").setValue(products.PushId);
                mref.child("Rewards").setValue(products.Cashback);
                mref.child("RewardsTotal").setValue(""+(a*r));

                CartFragment fragment = new CartFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();


            }
        });


        return v;
    }


}
